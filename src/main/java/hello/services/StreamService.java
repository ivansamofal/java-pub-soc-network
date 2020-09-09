package hello.services;

import java.util.stream.Stream;

public class StreamService {

    public void findUniqueSquares() {
        Stream
                .of(1, 2, 3, 2, 1)
                .map(s -> s * s)
                .distinct()
                .forEach(System.out::println);
    }
}
