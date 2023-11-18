package hello.myproject.domain.board;

import lombok.Data;

@Data
public class Board {

    private String id;          //사용자아이디
    private String part;
    private String title;       //제목
    private String name;        //사용자이름
    private String content;     //내용
    private String writeTime;   //작성일자
    private Integer views;      //조회수
}
