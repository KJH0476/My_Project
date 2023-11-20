package hello.myproject.board;

import hello.myproject.domain.board.Board;
import hello.myproject.domain.board.BoardRepository;
import hello.myproject.domain.board.BoardService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;
    Board board1 = new Board();
    Board board2 = new Board();

    @Test
    void 전체검색(){
        List<Board> boards = boardService.searchBoard(board2.getTitle());
        assertThat(boards.get(0).getTitle()).isSameAs("백준 5번");
    }

    @Test
    void 메뉴검색(){
        List<Board> partBoard = boardService.findPartBoard(board1.getPart());
        assertThat(partBoard.get(0).getPart()).isSameAs("완전탐색");
    }

    @Test
    void 사용자게시글검색(){
        List<Board> allUserBoard = boardService.findAllUserBoard(board2.getId());
        assertThat(allUserBoard.get(0).getId()).isSameAs("userB");
    }

    @PostConstruct
    void init(){
        board1.setId("userA");
        board1.setContent("----알고리즘----");
        board1.setName("홍길동");
        board1.setPart("완전탐색");
        board1.setTitle("백준 1055번");
        board1.setViews(60);
        board1.setWriteTime("2023-11-16");

        board2.setId("userB");
        board2.setContent("----알고리즘----");
        board2.setName("춘향이");
        board2.setPart("그리디");
        board2.setTitle("백준 5번");
        board2.setViews(30);
        board2.setWriteTime("2023-10-16");

        boardRepository.save(board1);
        boardRepository.save(board2);
    }
}