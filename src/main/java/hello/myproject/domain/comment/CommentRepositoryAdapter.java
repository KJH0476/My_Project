package hello.myproject.domain.comment;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentRepositoryAdapter implements CommentRepository{

    /**
     * 기존 CommentRepository 유지하면서 SpringDataJpaCommentRepository 사용해주기 위해
     * Adapter 패턴을 사용하였다. CommentRepository 를 구현한 클래스를 생성하고 SpringDataJpaCommentRepository 를 주입받아
     * CommentRepository 의 메서드를 SpringDataJpaCommentRepository 의 메서드를 사용하여 구현하였다.
     * --------------------------------------------------------------
     * Adapter 패턴은 기존의 코드를 수정하지 않고 새로운 코드를 재사용하기 위해 사용한다.
     * 즉, 기존 코드를 클라이언트가 사용하는 인터페이스의 구현체로 바꿔주는 패턴이다.
     */
    private final SpringDataJpaCommentRepository springDataJpaCommentRepository;

    @Override
    public Comment save(Comment comment) {
        log.info("CommentRepositoryAdapter save");
        return springDataJpaCommentRepository.save(comment);
    }

    @Override
    public List<Comment> findByBoardId(Long id) {
        log.info("CommentRepositoryAdapter findByBoardId");
        return springDataJpaCommentRepository.findByBoardId(id);
    }

    @Override
    public Comment findById(Long id) {
        log.info("CommentRepositoryAdapter findById");
        return springDataJpaCommentRepository.findById(id).get();
    }

    @Override
    public void deleteComment(Long id) {
        log.info("CommentRepositoryAdapter deleteComment");
        springDataJpaCommentRepository.deleteById(id);
    }

    @Override
    public void deleteCommentAllBoardId(Long boardId) {
        log.info("CommentRepositoryAdapter deleteCommentAllBoardId");
        springDataJpaCommentRepository.deleteByBoardId(boardId);
    }
}
