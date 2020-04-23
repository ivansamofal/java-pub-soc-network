package hello;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

class FileStreamExample {
    private final static String FILE_PATH = "/tmp/test.txt";
    private final static String FILE_PATH2 = "/tmp/test2.txt";

    public void execute() {
        try (FileInputStream fin = new FileInputStream(FILE_PATH);
            FileOutputStream fos = new FileOutputStream(FILE_PATH2))
        {
            byte[] buffer = new byte[fin.available()];
            System.out.println(buffer);
            // считываем буфер
            fin.read(buffer, 0, buffer.length);
            // записываем из буфера в файл
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
