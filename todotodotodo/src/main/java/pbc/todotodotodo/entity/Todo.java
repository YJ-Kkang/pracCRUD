package pbc.todotodotodo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pbc.todotodotodo.dto.TodoUpdatedRequestDto;

@Entity
@Table(name = "todo")
@Getter
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String username; // 작성 유저명
    private String title; // 할 일 제목
    private String content; // 할 일 내용


    /**
     * @NoArgsConstructor
     */
    public Todo() {}

    /**
     * @AllArgsConstructor
     */


    // 메서드 명은 같은데 파라미터 값이나, 타입이 다른 것 = 오버로딩
    public Todo(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public void updateContent(TodoUpdatedRequestDto request) {
        this.content = request.getContent();
    }


    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
