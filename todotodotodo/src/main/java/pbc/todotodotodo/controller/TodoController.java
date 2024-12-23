package pbc.todotodotodo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbc.todotodotodo.dto.*;
import pbc.todotodotodo.entity.Todo;
import pbc.todotodotodo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    /**
     * 클라이언트 요청 -> 컨트롤러 -> 서비스 -> 레포지토리 -> 데이터베이스
     *                                                 |
     * 클라이언트 응답 <- 컨트롤러 <- 서비스 <- 레포지토리 <- 데이터베이스
     */

    private final TodoService todoService;

    /**
     * 1.
     POST localhost:8080/todos
     JSON 데이터 형식
     {
        "username" : "김다빈",
        "title" : "일정 제목",
        "content" : "일정 내용"
     }
     */

    @PostMapping //POST /todos
    // request = username = 김다빈; title = 일정 제목; content = 일정 내용;
    // 2. @RequestBody TodoRequest request : 사용자가 요청한 데이터를 받음
    public ResponseEntity<TodoCreateResponseDto> createTodo(@RequestBody TodoRequest request) {

        // 3. 저장을 해야하기 때문에 데이터를 서비스로 넘겨줌
        // TodoCreateResponseDto : 제목, 내용
        TodoCreateResponseDto todoDto = todoService.createTodo(request); // todoService.createTodo(request) ==todoCreateResponseDto

        // 8. 진짜로 사용자에게 응답값을 보내줌.(가공한 데이터와 응답코드) 응답 코드는 ex. OK(200), 400, 500
        return new ResponseEntity<>(todoDto, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoFindByIdResponseDto> findTodoById(@PathVariable Long id) {

        // todoService.findTodoById(id) == todoFindByIdResponseDto
        TodoFindByIdResponseDto todoByIdDto = todoService.findTodoById(id);

        return new ResponseEntity<>(todoByIdDto, HttpStatus.OK);
    }

    //전체 조회 기능
    @GetMapping
    public ResponseEntity<List<TodoDto>> findAllTodo() {
        List<TodoDto> todoDtoList = todoService.findAllTodo(); // list
        return new ResponseEntity<>(todoDtoList, HttpStatus.OK);
    }

    //수정 기능
    // 요구사항 : content만 수정
    @PutMapping("/{id}")
    public ResponseEntity<TodoUpdatedResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoUpdatedRequestDto request) {
        TodoUpdatedResponseDto dto = todoService.updateTodo(id, request); //dto

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}