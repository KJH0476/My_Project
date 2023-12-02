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

    //댓글 저장
    public Comment save(Comment comment) {
        log.info("Comment={}", comment);
        comment.setId(seq++);
        Comment saveComment = commentStore.put(comment.getId(), comment);
        return saveComment;
    }

    //게시글에 달린 댓글 모두 찾기
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

    //댓글 아이디로 찾기
    public Comment findById(Long id){
        return commentStore.get(id);
    }

    //댓글 삭제
    public void deleteComment(Long id){
        commentStore.remove(id);
    }

    //게시글의 댓글 모두 삭제
    public void deleteCommentAllBoardId(Long boardId){
        for (Map.Entry<Long, Comment> commentEntry : commentStore.entrySet()) {
            if(commentEntry.getValue().getBoardId().equals(boardId)){
                commentStore.remove(commentEntry.getKey());
            }
        }
        log.info("commentStore={}", commentStore);
    }
}
