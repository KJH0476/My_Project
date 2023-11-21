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

    @GetMapping("/board-form")
    public String boardForm(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){
        log.info("loginUser={}", loginUser);
        model.addAttribute("loginUser", loginUser);
        return "board/boardForm";
    }

    @PostMapping("/board-save")
    public void saveBoard(@RequestBody Board board, HttpServletResponse response) throws IOException {
        log.info("board={}", board);
        boardService.saveBoard(board);
        response.getWriter().write("ok");
    }

    @GetMapping("/myPage")
    public String goToMyPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){
        model.addAttribute("loginUser", loginUser);
        return "page/myPage";
    }

    @GetMapping("/search/{title}")
    public String search(@PathVariable String title, Model model){
        List<Board> searchBoards = boardService.searchBoard(title);
        model.addAttribute("searchBoards", searchBoards);
        return "redirect:/{title}";
    }

    @GetMapping("/menu/{part}")
    public String menu(@PathVariable String part, Model model){
        List<Board> partBoard = boardService.findPartBoard(part);
        model.addAttribute("partBoard", partBoard);
        return "redirect:/{part}";
    }

    @GetMapping("/myPage/{loginId}")
    public String myPage(@PathVariable String loginId, Model model){
        log.info("loginId={}", loginId);
        List<Board> allUserBoard = boardService.findAllUserBoard(loginId);
        model.addAttribute("allUserBoard", allUserBoard);
        return "redirect:/{loginId}";
    }
}
