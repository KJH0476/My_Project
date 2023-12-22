package hello.myproject.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaCommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoardId(Long id);

    void deleteByBoardId(Long boardId);
}
