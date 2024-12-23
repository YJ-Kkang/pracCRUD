package pbc.todotodotodo.service;

import org.springframework.stereotype.Service;
import pbc.todotodotodo.dto.*;
import pbc.todotodotodo.entity.Todo;
import pbc.todotodotodo.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * @RequiredArgsConstructor 이걸로 대신할 수 있다.
     */
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // create
    // request = username = 김예제; title = 일정 제목; content = 일정 내용;
    // 4. 컨트롤러에서 보낸 데이터를 서비스가 받음(매개변수를 통해서)
    public TodoCreateResponseDto createTodo(TodoRequest request) {

        // 5. 받은 데이터를 가공해서 새로운 엔티티를 만들어줌.
        Todo todo = new Todo(request.getUsername(), request.getTitle(), request.getContent());

        // 6. 새롭게 만든 엔티티를 데이터베이스에 저장
        todoRepository.save(todo); // 데이터베이스에 저장

        // 7. 사용자에게 잘 저장되었다고 응답을 보냄.(보낼 데이터와, 응답코드)
        /**
         * 사용자 요구사항
         * 일정을 생성한 뒤 응답데이터 (제목이랑, 내용)
         */
        TodoCreateResponseDto todoCreateResponseDto = new TodoCreateResponseDto(todo.getTitle(), todo.getContent());
        return todoCreateResponseDto;

        // 줄인거
        // return new TodoCreateResponseDto(todo.getTitle(), todo.getContent());
    }

    /**
     * 아이디로 특정 일정 찾기
     * @param id
     * @return
     */
    public TodoFindByIdResponseDto findTodoById(Long id) {
        //Optional은 null 예외 방지를 위해
        Todo findTodo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("찾는 일정 없습니다."));

        /**
         * 사용자 요구사항
         * 제목이랑 내용, 이름을 달래요.
         */
        TodoFindByIdResponseDto todoFindByIdResponseDto
                = new TodoFindByIdResponseDto(findTodo.getTitle(), findTodo.getContent(), findTodo.getUsername());

        return todoFindByIdResponseDto;

        // 줄인거
        //return new TodoFindByIdResponseDto(findTodo.getTitle(), findTodo.getContent(), findTodo.getUsername());
    }


    /**
     * 전체 저회
     */
    public List<TodoDto> findAllTodo() {

        List<Todo> todoList = todoRepository.findAll();

        List<TodoDto> list = todoList.stream()
                .map(TodoDto::from)
                .toList();

        return list;

        //줄인거
        //return todoList.stream()
        //          .map(TodoDto::from)
        //          .toList();
    }

    /**
     * 수정된 내용 반환하도록
     */
    public TodoUpdatedResponseDto updateTodo(Long id, TodoUpdatedRequestDto request) {
        Todo findTodo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("찾는 일정이 없습니다."));
        findTodo.updateContent(request);
        todoRepository.save(findTodo);

        TodoUpdatedResponseDto dto = new TodoUpdatedResponseDto(findTodo.getContent());

        return dto;
    }


    /**
     * 클라이언트 요청 -> 컨트롤러 -> 서비스 -> 레포지토리 -> 데이터베이스
     *                                                 |
     * 클라이언트 응답 <- 컨트롤러 <- 서비스 <- 레포지토리 <- 데이터베이스
     */

    public void deleteTodo(Long id) {

        Todo findTodo = todoRepository.findById(id).orElseThrow(()-> new IllegalStateException("삭제 하는 부분이 문제임"));
        todoRepository.delete(findTodo);
    }
}
