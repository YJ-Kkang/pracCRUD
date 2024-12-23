package pbc.todotodotodo.dto;


import lombok.Getter;

@Getter
public class TodoRequest {

    private String username; // 작성 유저명
    private String title; // 할 일 제목
    private String content; // 할 일 내용
}
