package hello.myproject.domain.board;

import java.util.List;

public interface BoardRepository {

    void save(Board board);
    List<Board> findAll();
    List<Board> findByTitle(String title);
    List<Board> findByLoginMember(String loginId);
    List<Board> findByPart(String part);
    Board findById(Long id);
    void updateBoard(Long id, Board board);
    void deleteBoard(Long id);
    void clearBoardStore();

}
