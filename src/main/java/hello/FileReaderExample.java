package hello;

import java.io.FileReader;
import java.io.IOException;

class FileReaderExample
{
    public void execute() {
        try(FileReader reader = new FileReader("/tmp/test.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
