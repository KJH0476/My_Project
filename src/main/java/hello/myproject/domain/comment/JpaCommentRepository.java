package hello.myproject.domain.comment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
//@Repository
@RequiredArgsConstructor
public class JpaCommentRepository implements CommentRepository{

    private final EntityManager em;

    //댓글 저장
    @Override
    public Comment save(Comment comment) {
        log.info("save comment={}", comment);
        em.persist(comment);
        return comment;
    }

    //게시글 별 댓글 찾기
    //createQuery 메서드에서 SELECT 사용할 때 결과 타입필요 -> Comment.class 로 조회할 타입 지정
    @Override
    public List<Comment> findByBoardId(Long id) {
        List<Comment> result = em.createQuery("select c from Comment c where c.board.id = :boardId", Comment.class)
                .setParameter("boardId", id)
                .getResultList();
        return result;
    }

    //특정 댓글 찾기
    @Override
    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    //댓글 삭제
    @Override
    public void deleteComment(Long id) {
        Comment comment = findById(id);
        if(comment!=null) em.remove(comment);
    }

    //특정 게시글 댓글 모두 삭제(게시물 삭제 시 게시물과 같이 삭제되기 위함)
    //DELETE, UPDATE 같은 경우 SELECT 와 달리 결과 타입을 반환하지 않기 때문에 타입 지정 X
    @Override
    public void deleteCommentAllBoardId(Long boardId) {
        em.createQuery("delete from Comment c where c.board.id = :boardId")
                .setParameter("boardId", boardId)
                .executeUpdate();
    }
}
