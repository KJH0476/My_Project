package hello.myproject.domain.comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);
    List<Comment> findByBoardId(Long id);
    Comment findById(Long id);
    void deleteComment(Long id);
    void deleteCommentAllBoardId(Long boardId);
}
