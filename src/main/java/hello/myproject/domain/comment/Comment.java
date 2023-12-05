package hello.myproject.domain.comment;

import hello.myproject.domain.board.Board;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
    private String loginId;
    private String content;
    private String timeStamp;
}
