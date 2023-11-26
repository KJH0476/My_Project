package hello.myproject.web.board;

import hello.myproject.domain.board.Board;
import hello.myproject.domain.board.BoardService;
import hello.myproject.domain.user.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //글쓰기 양식
    @GetMapping("/board-form")
    public String boardForm(){
        return "board/boardForm";
    }

    //게시글 저장
    @PostMapping("/board-save")
    public void saveBoard(@RequestBody Board board, HttpServletResponse response) throws IOException {
        log.info("run BoardController Save");
        boardService.saveBoard(board);
        response.getWriter().write("ok");
    }

    //마이페이지
    @GetMapping("/myPage")
    public String goToMyPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){
        List<Board> board = boardService.findAllUserBoard(loginUser.getLoginId());
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("board", board);
        log.info("loginUser={}", loginUser);
        for (Board board1 : board) {
            log.info("board={}", board1);
        }
        return "page/myPage";
    }

    //검색
    @GetMapping("/search")
    public String search(@RequestParam String title, @SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){
        List<Board> board = boardService.searchBoard(title);
        model.addAttribute("board", board);
        log.info("title={}", title);
        for (Board board1 : board) {
            log.info("board={}", board1);
        }
        return "redirect:/";
    }

    //메뉴별 검색
    @GetMapping("/menu/{part}")
    public String menu(@PathVariable String part, Model model){
        List<Board> board = boardService.findPartBoard(part);
        model.addAttribute("board", board);
        log.info("part={}", part);
        log.info("board={}", board);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String boardView(@PathVariable String id, Model model){
        Board board = boardService.findByBoardId(id);
        log.info("boardView={}", board);
        model.addAttribute("board", board);
        return "board/boardView";
    }
}
