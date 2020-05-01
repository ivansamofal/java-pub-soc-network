package hello.dtos;

import hello.annotations.InjectRandomInt;
import lombok.Data;

@Data
public class RowDto {
    private Integer id;
    private String name;
    @InjectRandomInt(min = 2, max = 7)
    private int test;
}
