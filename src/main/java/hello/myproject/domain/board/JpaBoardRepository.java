package hello.myproject.domain.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
//@Repository
@RequiredArgsConstructor
public class JpaBoardRepository implements BoardRepository{

    private final EntityManager em;

    //게시글 저장
    @Override
    public void save(Board board) {
        em.persist(board);
    }

    //게시글 전체 조회
    @Override
    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    //게시글 제목으로 조회
    @Override
    public List<Board> findByTitle(String title) {
        List<Board> result = em.createQuery("select b from Board b where b.title = :title", Board.class)
                .setParameter("title", title)
                .getResultList();
        return result;
    }

    //로그인한 사용자 게시글 조회
    @Override
    public List<Board> findByLoginMember(String loginId) {
        List<Board> result = em.createQuery("select b from Board b where b.loginId = :loginId", Board.class)
                .setParameter("loginId", loginId)
                .getResultList();
        return result;
    }

    //파트별 게시글 조회
    @Override
    public List<Board> findByPart(String part) {
        List<Board> result = em.createQuery("select b from Board b where b.part = :part", Board.class)
                .setParameter("part", part)
                .getResultList();
        return result;
    }

    //게시글 id로 죄회
    @Override
    public Board findById(Long id) {
        Board board = em.find(Board.class, id);
        return board;
    }

    //게시글 업데이트
    @Override
    public void updateBoard(Long id, Board board) {
        em.createQuery("update Board b set b.part = :part, b.title = :title, b.content = :content, b.timestamp = :timestamp where b.id = :id")
                .setParameter("part", board.getPart())
                .setParameter("title", board.getTitle())
                .setParameter("content", board.getContent())
                .setParameter("timestamp", board.getTimestamp())
                .setParameter("id", id)
                .executeUpdate();
    }

    //댓글 달았을 때 댓글 수 업데이트
    @Override
    public void updateBoardCommentCount(int cnt, Long id){
        em.createQuery("update Board b set b.commentCount = :commentCount where b.id = :id")
                .setParameter("commentCount", cnt)
                .setParameter("id", id)
                .executeUpdate();
    }

    //게시글 삭제
    @Override
    public void deleteBoard(Long id) {
        em.createQuery("delete from Board b where b.id = :id").setParameter("id", id).executeUpdate();
    }

    //게시글 초기화
    @Override
    public void clearBoardStore() {
        em.createQuery("delete from Board").executeUpdate();
    }
}
