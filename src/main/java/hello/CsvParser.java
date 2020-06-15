package hello;

import hello.dtos.CsvDto;
import hello.entities.Row;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CsvParser {



    public static List<Row> parseProductCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<Row> products = new ArrayList<>();
//        List<String> fileLines = Files.readAllLines(Paths.get(filePath));

        BufferedReader br = new BufferedReader(new FileReader(filePath));
//        for (String fileLine : fileLines) {
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] splitedText = line.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                //Если колонка начинается на кавычки или заканчиваеться на кавычки
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + ","+ splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            Row product = new Row();
            System.out.println(columnList.get(1));
            product.setName(columnList.get(1));
            product.setId(Integer.valueOf(columnList.get(0)));
            product.setCreated_at(LocalDateTime.now());
            products.add(product);
            //todo save in db by chunks
        }

        return products;
    }

//    public static List<CsvDto> parseCsv(String filePath) throws IOException {
//        long start = System.currentTimeMillis();
//        //Загружаем строки из файла
//        List<CsvDto> lines = new ArrayList<>();
//        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
//        for (String fileLine : fileLines) {
//            String[] splitedText = fileLine.split(",");
//            ArrayList<String> columnList = new ArrayList<String>();
//            for (int i = 0; i < splitedText.length; i++) {
//                //Если колонка начинается на кавычки или заканчиваеться на кавычки
//                if (IsColumnPart(splitedText[i])) {
//                    String lastText = columnList.get(columnList.size() - 1);
//                    columnList.set(columnList.size() - 1, lastText + ","+ splitedText[i]);
//                } else {
//                    columnList.add(splitedText[i]);
//                }
//            }
//
//            try {
//                CsvDto line = new CsvDto();
//                Integer index = Integer.valueOf(columnList.get(0));
//                Float height = Float.valueOf(columnList.get(1));
//                Float weight = Float.valueOf(columnList.get(1));
//                line.setIndex(index);
//                line.setHeight(height);
//                line.setWeight(weight);
//
//                lines.add(line);
//            } catch (Exception e) {
//                //
//            }
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
//
//        System.out.println(lines.size());
//
//        return lines;
//    }

    public static List<CsvDto> parseCsv(String filePath, Integer threadsCount) throws IOException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        //Загружаем строки из файла
//        List<CsvDto> lines = new ArrayList<>();

        List<CsvDto> lines = Collections.synchronizedList(new ArrayList<CsvDto>());
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        System.out.println("ALL LINES COUNT " + fileLines.size());
        AtomicInteger counter = new AtomicInteger(0);
        Integer originCounter = 0;
        for (String fileLine : fileLines) {
            executorService.submit(() -> {
                computeLine(fileLine, lines);
                counter.incrementAndGet();
            });
            originCounter++;
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
    private static void computeLine(String fileLine, List<CsvDto> lines) {
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
            CsvDto line = new CsvDto();
            Integer index = Integer.valueOf(columnList.get(0));
            Float height = Float.valueOf(columnList.get(1));
            Float weight = Float.valueOf(columnList.get(1));
            line.setIndex(index);
            line.setHeight(height);
            line.setWeight(weight);

            lines.add(line);
        } catch (Exception e) {
            //
        }
    }

    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна ковычка и текст на нее заканчиваеться значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
