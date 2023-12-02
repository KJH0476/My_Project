package hello.myproject.domain.comment;

import lombok.Data;

@Data
public class Comment {

    private Long id;
    private Long boardId;
    private String loginId;
    private String content;
    private String timeStamp;
}
