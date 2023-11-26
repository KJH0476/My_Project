package hello.myproject.domain.board;

import java.util.List;

public interface BoardRepository {

    void save(Board board);
    List<Board> findAll();
    List<Board> findByTitle(String title);
    List<Board> findByLoginUser(String loginId);
    List<Board> findByPart(String part);
    Board findById(Long id);
    void clearBoardStore();

}
