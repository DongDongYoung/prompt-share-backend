package ddy.promptsharebackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
public class Prompt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false) private String detail;
    @Setter @Column(nullable = false) private String userInput;
    @Setter @Column(nullable = false) private String gptOutput;

    private int viewCount; // TODO: 조회수 처리 공부 후 추가하기
    private int likeCount; // TODO: 유저 테이블 생성 후, 연관관계 매핑으로 수정

    // TODO: Auditor 기능 알아보고 적용하기
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(updatable = false) private LocalDateTime createdAt;
    @Column(updatable = false) private String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    protected Prompt() {
    }

    private Prompt(
            String title,
            String detail,
            String userInput,
            String gptOutput) {
        this.title = title;
        this.detail = detail;
        this.userInput = userInput;
        this.gptOutput = gptOutput;
    }

    public static Prompt of(
            String title,
            String detail,
            String userInput,
            String gptOutput) {
        return new Prompt(title, detail, userInput, gptOutput);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prompt prompt)) return false;
        return id == prompt.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
