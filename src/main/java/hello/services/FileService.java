package hello.services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;

public class FileService {
    public void copyFile() {
        File file = new File("/project/deploy.sh");
        Path path = file.toPath();
        File fileTarget = new File("/project/deploy2.sh");
        try {
            if (!fileTarget.exists()) {
                Files.copy(path, fileTarget.toPath());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyDirNames() {
        File dir = new File("/project");
        File newFile = new File("/project/tst.txt");
        File newFile2 = new File("/project/tst2.txt");
        File newFile3 = new File("/project/tst3.txt");
        if (dir.isDirectory()) {
            String[] names = dir.list();
            StringBuilder sb = new StringBuilder();
            try {
                boolean res = newFile.createNewFile();
                boolean res2 = newFile2.createNewFile();

                //one way
                if (res && names != null) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                    try {
                        writer.write(String.join("\n", names));
                    } finally {
                        writer.close();
                    }
                }

                if (res2 && names != null) {
                    //another way
                    try (OutputStream out = new FileOutputStream(newFile2)) {
                        byte[] bytes = String.join("\n", names).getBytes();
                        out.write(bytes);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }

                //third way
                try (InputStream is = new FileInputStream(newFile2)) {
                    int data = is.read();
                    while (data != -1) {
                        sb.append((char) data);
                        data = is.read();
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }


            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void randomAccessFile() {
        File file = new File("/project/tst2.txt");
        StringBuilder sb = new StringBuilder();

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            int data = randomAccessFile.read();
            while (data != -1) {
                sb.append((char) data);
                data = randomAccessFile.read();
            }

            randomAccessFile.write("test".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputStreamReader() {
        String path = "/project/tst2.txt";
        StringBuilder sb = new StringBuilder();

        try (Reader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
            int data = reader.read();
            while (data != -1) {
                sb.append((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputStreamWriter() {
        String path = "/project/tst2.txt";

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8)) {
            writer.write("Hello some text");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void charArrayReader() {
        StringBuilder sb = new StringBuilder();

        try (Reader reader = new CharArrayReader(new char[]{'a', 'b', 'c'})) {
            int data = reader.read();

            while (data != -1) {
                sb.append((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void charArrayWriter() {
        try (CharArrayWriter writer = new CharArrayWriter(1024)) {
            writer.write("Hello test");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fileReader() {
        StringBuilder sb = new StringBuilder();
        String path = "/project/tst2.txt";

        try (Reader reader = new FileReader(path)) {
            int data = reader.read();

            while (data != -1) {
                sb.append((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fileWriter() {
        String path = "/project/tst2.txt";
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("Hello test");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void bufferedRead() {
        String path = "/project/tst2.txt";
        StringBuilder sb = new StringBuilder();

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path))) {
            byte[] bytes = new byte[1024];
            int amountData = inputStream.read(bytes, 0, 1024);

            while (amountData == 1024) {
                sb.append(new String(bytes, StandardCharsets.UTF_8));
                amountData = inputStream.read(bytes, 0, 1024);
            }

            if (amountData != -1) {
                byte[] residue = new byte[amountData];
                System.arraycopy(bytes, 0, residue, 0, residue.length);
                sb.append(new String(residue, StandardCharsets.UTF_8));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyDataFromFileToFile() {
        String path = "/project/tst2.txt";
//        ZoneOffset zoneOffset = ZoneId.of("Europe/Moscow").getRules().getOffset(LocalDateTime.now());
//        Instant.now().getEpochSecond();
//        LocalDateTime.now().format();
//        Calendar.getInstance().getTimeInMillis()
        File file1 = new File(path);
        File file2 = new File("/project/test2.txt");

        int bytesLength = 1024;
        int offset = 0;

        try {
            boolean res = file2.createNewFile();
            if (res) {
                try (
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file1));
                        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file2))
                ) {
                    byte[] bytes = new byte[bytesLength];
                    int amount = in.read(bytes, offset, bytesLength);
                    while (amount != -1) {
                        out.write(bytes);
                        amount = in.read(bytes, offset, bytesLength);
                    }
                    //another way
//                    int data = in.read();
//
//                    while (data != -1) {
//                        out.write(data);
//                        out.flush();
//                        data = in.read();
//                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
