package hello.myproject.domain.board;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BoardRepositoryAdapter implements BoardRepository {

    /**
     * 기존 BoardRepository 유지하면서 SpringDataJpaBoardRepository 사용해주기 위해
     * Adapter 패턴을 사용하였다. BoardRepository 를 구현한 클래스를 생성하고 SpringDataJpaBoardRepository 를 주입받아
     * BoardRepository 의 메서드를 SpringDataJpaBoardRepository 의 메서드를 사용하여 구현하였다.
     * --------------------------------------------------------------
     * Adapter 패턴은 기존의 코드를 수정하지 않고 새로운 코드를 재사용하기 위해 사용한다.
     * 즉, 기존 코드를 클라이언트가 사용하는 인터페이스의 구현체로 바꿔주는 패턴이다.
     */
    private final SpringDataJpaBoardRepository springDataJpaBoardRepository;

    @Override
    public void save(Board board) {
        log.info("BoardRepositoryAdapter save");
        springDataJpaBoardRepository.save(board);
    }

    @Override
    public List<Board> findAll() {
        log.info("BoardRepositoryAdapter findAll");
        return springDataJpaBoardRepository.findAll();
    }

    @Override
    public List<Board> findByTitle(String title) {
        log.info("BoardRepositoryAdapter findByTitle");
        return springDataJpaBoardRepository.findByTitle(title);
    }

    @Override
    public List<Board> findByLoginMember(String loginId) {
        log.info("BoardRepositoryAdapter findByLoginMember");
        return springDataJpaBoardRepository.findByLoginId(loginId);
    }

    @Override
    public List<Board> findByPart(String part) {
        log.info("BoardRepositoryAdapter findByPart");
        return springDataJpaBoardRepository.findByPart(part);
    }

    @Override
    public Board findById(Long id) {
        log.info("BoardRepositoryAdapter findById");
        return springDataJpaBoardRepository.findById(id).get();
    }

    @Override
    public void updateBoard(Long id, Board board) {
        log.info("BoardRepositoryAdapter updateBoard");
        springDataJpaBoardRepository.updateBoard(board.getPart(), board.getTitle(), board.getContent(), board.getTimestamp(), id);
    }

    @Override
    public void updateBoardCommentCount(int cnt, Long id) {
        log.info("BoardRepositoryAdapter updateBoardCommentCount");
        springDataJpaBoardRepository.updateBoardCommentCountById(cnt, id);
    }

    @Override
    public void deleteBoard(Long id) {
        log.info("BoardRepositoryAdapter deleteBoard");
        springDataJpaBoardRepository.delete(findById(id));
    }

    @Override
    public void clearBoardStore() {
        log.info("BoardRepositoryAdapter clearBoardStore");
        springDataJpaBoardRepository.deleteAll();
    }
}
