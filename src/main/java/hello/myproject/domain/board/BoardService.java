package hello.myproject.domain.board;

import hello.myproject.domain.comment.Comment;
import hello.myproject.domain.comment.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    //게시글 저장
    public void saveBoard(Board board){
        log.info("run BoardService Save");
        boardRepository.save(board);
    }

    //검색창에서 전체 알고리즘 검색
    public List<Board> searchBoard(String title){
        List<Board> searchBoard = new ArrayList<>();
        for (Board board : boardRepository.findByTitle(title)) {
            searchBoard.add(board);
        }
        return searchBoard;
    }

    //로그인 사용자가 게시한 글 찾기
    public List<Board> findAllMemberBoard(String loginId) {
        List<Board> findMemberBoard = new ArrayList<>();
        for (Board board : boardRepository.findByLoginMember(loginId)) {
            findMemberBoard.add(board);
        }
        return findMemberBoard;
    }

    //게시글 id로 찾기
    public Board findByBoardId(String id){
        Long Lid = Long.parseLong(id);

        Board board = boardRepository.findById(Lid);

        if(board==null) return null;

        return board;
    }

    //메뉴 바에서 파트별로 검색
    public List<Board> findPartBoard(String part){
        List<Board> partBoard = new ArrayList<>();
        for (Board board : boardRepository.findByPart(part)) {
            partBoard.add(board);
        }
        return partBoard;
    }

    //게시글 수정
    public void updateBoardService(String id, Board board){
        boardRepository.updateBoard(Long.parseLong(id), board);
    }

    //게시글 삭제
    public void deleteBoardService(String id){
        commentRepository.deleteCommentAllBoardId(Long.parseLong(id));
        boardRepository.deleteBoard(Long.parseLong(id));
    }

    //게시글에 달린 댓글 반환
    public List<Comment> commentListByBoardId(Long id){
        List<Comment> comments = commentRepository.findByBoardId(id);
        return comments;
    }

    //댓글 저장 및 게시글 댓글 수 증가
    public void saveCommentAndCommentCount(Comment comment){
        commentRepository.save(comment);
        log.info("service comment save={}", comment);

        Long boardId = comment.getBoard().getId();

        int cnt = commentRepository.findByBoardId(boardId).size();

        boardRepository.updateBoardCommentCount(cnt, boardId);
    }

    //댓글 삭제
    public void deleteComment(Long commentId){
        commentRepository.deleteComment(commentId);
    }
}
