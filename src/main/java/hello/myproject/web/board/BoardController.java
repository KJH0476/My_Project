package hello.myproject.web.board;

import hello.myproject.domain.board.Board;
import hello.myproject.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board-form")
    public String boardForm(){ return "board-form"; }

    @PostMapping("/board-save")
    public String saveBoard(@ModelAttribute Board board){
        boardService.saveBoard(board);
        return "save-ok";
    }

    @GetMapping("/{title}")
    public String search(@PathVariable String title, Model model){
        List<Board> searchBoards = boardService.searchBoard(title);
        model.addAttribute("searchBoards", searchBoards);
        return "redirect:/{title}";
    }

    @GetMapping("/{part}")
    public String menu(@PathVariable String part, Model model){
        List<Board> partBoard = boardService.findPartBoard(part);
        model.addAttribute("partBoard", partBoard);
        return "redirect:/{part}";
    }

    @GetMapping("/{loginId}")
    public String userBoard(@PathVariable String loginId, Model model){
        List<Board> allUserBoard = boardService.findAllUserBoard(loginId);
        model.addAttribute("allUserBoard", allUserBoard);
        return "redirect:/{loginId}";
    }
}
