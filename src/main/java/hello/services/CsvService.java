package hello.services;

import hello.dtos.CsvDto;
import hello.entities.Row;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CsvService {

    final private RowService rowService;

    public CsvService(RowService rowService) {
        this.rowService = rowService;
    }

    public List<Row> parseCsv(String filePath, Integer threadsCount) throws IOException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        //Загружаем строки из файла
//        List<CsvDto> lines = new ArrayList<>();

        List<Row> lines = Collections.synchronizedList(new ArrayList<>());
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        System.out.println("ALL LINES COUNT " + fileLines.size());
        AtomicInteger counter = new AtomicInteger(0);
        Integer originCounter = 0;
        for (String fileLine : fileLines) {
            executorService.submit(() -> {
                synchronized (this) {
                    if (lines.size() >= 1000) {
                        rowService.saveAll(lines);
                        lines.clear();
                    }
                }

                computeLine(fileLine, lines, counter);
                counter.incrementAndGet();
            });
            originCounter++;
        }

        if (!lines.isEmpty()) {
            rowService.saveAll(lines);
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("THREADS ERROR " + e.getMessage());
        }

        System.out.println("TOTAL ITERATIONS: " + counter.get());
        System.out.println("ORIGIN COUNTER: " + originCounter);

        System.out.println(System.currentTimeMillis() - start);

        System.out.println(lines.size());

        return lines;
    }

    @SneakyThrows
    private void computeLine(String fileLine, List<Row> lines, AtomicInteger counter) {
        String[] splitedText = fileLine.split(",");
        ArrayList<String> columnList = new ArrayList<String>();

        for (AtomicInteger i = new AtomicInteger(0); i.get() < splitedText.length; i.incrementAndGet()) {
            //Если колонка начинается на кавычки или заканчиваеться на кавычки
            if (IsColumnPart(splitedText[i.get()])) {
                String lastText = columnList.get(columnList.size() - 1);
                columnList.set(columnList.size() - 1, lastText + ","+ splitedText[i.get()]);
            } else {
                columnList.add(splitedText[i.get()]);
            }
        }

        try {
            Row row = new Row();

            row.setCreated_at(LocalDateTime.now());
            row.setName(counter.toString());
            row.setHeight(columnList.get(1));
            row.setWeight(columnList.get(2));

            lines.add(row);
        } catch (Exception e) {
            //
        }
    }

    private boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна ковычка и текст на нее заканчиваеться значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
