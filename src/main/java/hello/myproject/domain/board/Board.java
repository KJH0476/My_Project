package hello.myproject.domain.board;

import hello.myproject.domain.member.Member;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String part;        //분야
    private String title;       //제목
    private String content;     //내용
    private String timestamp;   //작성일자
    private int commentCount;  //댓글 수
}