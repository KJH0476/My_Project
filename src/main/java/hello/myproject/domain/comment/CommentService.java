package hello.myproject.domain.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment saveComment(Comment comment){
        log.info("Save Comment");
        Comment com = assignCommentNumber(comment);
        com.setTimeStamp(LocalDateTime.now().toString());
        Comment saveComment = commentRepository.save(com);
        return saveComment;
    }

    public List<Comment> commentByBoardId(String id){
        List<Comment> comments = commentRepository.findByBoardId(Long.parseLong(id));
        if(comments==null) return null;

        return comments;
    }

    public Comment assignCommentNumber(Comment comment){
        List<Comment> comments = commentRepository.findByBoardId(comment.getBoardId());
        comment.setCommentNumber(comments.size());
        return comment;
    }
}