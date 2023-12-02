package hello.myproject.domain.board;

import hello.myproject.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    /**
     * 게시글 저장
     */
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
    public List<Board> findAllUserBoard(String loginId) {
        List<Board> findUserBoard = new ArrayList<>();
        for (Board board : boardRepository.findByLoginUser(loginId)) {
            findUserBoard.add(board);
        }
        return findUserBoard;
    }

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

    public void updateBoardService(String id, Board board){
        boardRepository.updateBoard(Long.parseLong(id), board);
    }

    public void deleteBoardService(String id){
        commentRepository.deleteCommentAllBoardId(Long.parseLong(id));
        boardRepository.deleteBoard(Long.parseLong(id));
    }
}
