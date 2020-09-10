package hello.services;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class StreamService {

    public void findUniqueSquares() {
        Stream
                .of(1, 2, 3, 2, 1)
                .map(s -> s * s)
                .distinct()
                .forEach(System.out::println);
    }

    public void charsStream() {
        String str = "some new my string";
        str = str.chars().sorted().toString();
        System.out.println(str);
    }
}
