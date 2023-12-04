package hello.myproject.web.board;

import hello.myproject.domain.board.Board;
import hello.myproject.domain.board.BoardService;
import hello.myproject.domain.comment.Comment;
import hello.myproject.domain.comment.CommentRepository;
import hello.myproject.domain.member.Member;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentRepository commentRepository;

    //글쓰기 폼
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

    //게시글 수정폼
    @GetMapping("/board-edit/{id}")
    public String editBoardForm(@PathVariable String id, Model model){
        Board board = boardService.findByBoardId(id);
        model.addAttribute("board", board);

        return "/edit/editBoardForm";
    }

    //게시글 수정
    @PostMapping("/board-edit/{id}")
    public void editBoard(@RequestBody Board board, @PathVariable String id, HttpServletResponse response) throws IOException {
        log.info("edit BoardController");
        boardService.updateBoardService(id, board);

        response.getWriter().write("ok");
    }

    @GetMapping("/board-delete/{id}")
    public String deleteBoard(@PathVariable String id){
        log.info("deleteBoard");
        boardService.deleteBoardService(id);

        return "page/myPage";
    }

    //마이페이지
    @GetMapping("/myPage")
    public String goToMyPage(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model){
        List<Board> board = boardService.findAllMemberBoard(loginMember.getLoginId());
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("board", board);
        log.info("loginMember={}", loginMember);
        for (Board board1 : board) {
            log.info("board={}", board1);
        }
        return "page/myPage";
    }

    //검색
    @GetMapping("/search")
    public String search(@RequestParam String title, Model model){
        List<Board> board = boardService.searchBoard(title);
        model.addAttribute("board", board);
        log.info("title={}", title);
        for (Board board1 : board) {
            log.info("board={}", board1);
        }
        return "page/searchPage";
    }

    //메뉴별 검색
    @GetMapping("/menu/{part}")
    public String menu(@PathVariable String part, Model model){
        List<Board> board = boardService.findPartBoard(part);
        model.addAttribute("board", board);
        log.info("part={}", part);
        log.info("board={}", board);
        return "page/menuPage";
    }

    //게시글, 댓글 보여주기
    @GetMapping("/board/{id}")
    public String boardView(@PathVariable String id, Model model){
        Board board = boardService.findByBoardId(id);
        List<Comment> comments = commentRepository.findByBoardId(Long.parseLong(id));

        board.setCommentCount(comments.size());
        log.info("boardView={}", board);
        model.addAttribute("board", board);

        log.info("comments={}", comments);
        if(comments != null) model.addAttribute("comments", comments);
        else model.addAttribute("comments", null);
        return "board/boardView";
    }

    //댓글 작성
    @PostMapping("/board/{id}")
    public String saveComment(@ModelAttribute Comment comment, @PathVariable String id){
        log.info("saveComment");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd E HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);

        comment.setTimeStamp(formattedDateTime);

        commentRepository.save(comment);
        return "redirect:/board/"+id;
    }

    //댓글 삭제
    @GetMapping("/comment/delete/{boardId}/{commentId}")
    public String removeComment(@PathVariable String boardId, @PathVariable("commentId") String id){
        commentRepository.deleteComment(Long.parseLong(id));

        return "redirect:/board/"+Long.parseLong(boardId);
    }
}