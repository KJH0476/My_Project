package hello.myproject.domain.board;

import lombok.Data;

@Data
public class Board {

    private String loginId;     //로그인사용자아이디
    private String part;
    private String title;       //제목
    private String content;     //내용
    private String timestamp;   //작성일자
}
