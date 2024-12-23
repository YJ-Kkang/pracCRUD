package pbc.todotodotodo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pbc.todotodotodo.entity.Todo;

@Getter
@AllArgsConstructor
public class TodoCreateResponseDto {

    private String title;
    private String content;

}
