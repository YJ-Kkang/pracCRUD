package pbc.todotodotodo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // 작성일이라 업데이트가 되면 안됨!
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column //updatable 속성은 항상 true가 기본값. 기본값은 굳이 명시 안해줘도됨.
    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;
}
