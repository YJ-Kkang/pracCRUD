package pbc.todotodotodo.dto;

import lombok.Getter;
import pbc.todotodotodo.entity.Todo;

@Getter
public class TodoDto {

    private String username;
    private String title;
    private String content;

    private TodoDto(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public static TodoDto from(Todo todo) {
        return new TodoDto(
                todo.getUsername(),
                todo.getTitle(),
                todo.getContent()
        );
    }
}
