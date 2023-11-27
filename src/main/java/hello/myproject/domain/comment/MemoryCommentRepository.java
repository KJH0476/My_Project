package hello.myproject.domain.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemoryCommentRepository implements CommentRepository{

    private static Map<Long, Comment> commentStore = new ConcurrentHashMap<>();
    private static long seq = 0L;

    @Override
    public Comment save(Comment comment) {
        log.info("Comment={}", comment);
        comment.setId(seq++);
        Comment saveComment = commentStore.put(comment.getId(), comment);
        return saveComment;
    }

    @Override
    public List<Comment> findByBoardId(Long id) {
        List<Comment> comments = new ArrayList<>();
        if(commentStore==null) return null;

        for (Comment comment : commentStore.values()) {
            if(comment.getBoardId().equals(id)){
                comments.add(comment);
            }
        }
        return comments;
    }
}
