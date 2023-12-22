package hello.myproject.domain.board;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataJpaBoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title);

    List<Board> findByLoginId(String loginId);

    List<Board> findByPart(String part);

    @Transactional
    @Modifying
    @Query("update Board b set b.part = :part, b.title = :title, b.content = :content, b.timestamp = :timestamp where b.id = :id")
    void updateBoard(String part, String title, String content, String timestamp, Long id);

    @Transactional
    @Modifying
    @Query("update Board b set b.commentCount = :commentCount where b.id = :id")
    void updateBoardCommentCountById(int commentCount, Long id);
}
