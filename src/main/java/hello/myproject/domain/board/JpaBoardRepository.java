package hello.myproject.domain.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaBoardRepository implements BoardRepository{

    private final EntityManager em;

    @Override
    public void save(Board board) {
        em.persist(board);
    }

    @Override
    public List<Board> findAll() {
        return em.createQuery("select b form Board b", Board.class).getResultList();
    }

    @Override
    public List<Board> findByTitle(String title) {
        List<Board> result = em.createQuery("select b from Board b where b.title = :title", Board.class)
                .setParameter("title", title)
                .getResultList();
        return result;
    }

    @Override
    public List<Board> findByLoginMember(String loginId) {
        List<Board> result = em.createQuery("select b from Board b where b.loginId = :loginId", Board.class)
                .setParameter("loginId", loginId)
                .getResultList();
        return result;
    }

    @Override
    public List<Board> findByPart(String part) {
        List<Board> result = em.createQuery("select b from Board b where b.part = :part", Board.class)
                .setParameter("part", part)
                .getResultList();
        return result;
    }

    @Override
    public Board findById(Long id) {
        Board board = em.find(Board.class, id);
        return board;
    }

    @Override
    public void updateBoard(Long id, Board board) {
        em.createQuery("UPDATE Board b SET b.part = :part, b.title = :title, b.content = :content, b.timestamp = :timestamp WHERE b.id = :id")
                .setParameter("part", board.getPart())
                .setParameter("title", board.getTitle())
                .setParameter("content", board.getContent())
                .setParameter("timestamp", board.getTimestamp())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteBoard(Long id) {
        em.createQuery("DELETE FROM Board b where b.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void clearBoardStore() {
        em.createQuery("DELETE FROM Board").executeUpdate();
    }
}
