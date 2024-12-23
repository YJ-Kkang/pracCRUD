package pbc.todotodotodo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoFindByIdResponseDto {

    private String title;
    private String content;
    private String username;
}
