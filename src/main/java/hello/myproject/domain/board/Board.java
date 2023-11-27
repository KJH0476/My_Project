package hello.myproject.domain.board;

import lombok.Data;

@Data
public class Board {

    private Long id;
    private String loginId;     //로그인사용자아이디
    private String part;        //분야
    private String title;       //제목
    private String content;     //내용
    private String timestamp;   //작성일자
    private int commentCount;  //댓글 수
}
