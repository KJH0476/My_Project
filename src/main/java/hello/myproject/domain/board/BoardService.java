package hello.myproject.domain.board;

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

    public void saveBoard(Board board){ boardRepository.save(board); }

    //검색창에서 전체 알고리즘 검색
    public List<Board> searchBoard(String title){
        List<Board> sBoard = new ArrayList<>();
        for (Board value : boardRepository.getBoardStore().values()) {
            if(value.getTitle().equals(title)){
                sBoard.add(value);
            }
        }
        return sBoard;
    }

    //로그인 사용자가 게시한 글 찾기
    public List<Board> findAllUserBoard(String loginId) {
        List<Board> findUserBoard = new ArrayList<>();
        for (Map.Entry<String, Board> boardEntry : boardRepository.getBoardStore().entrySet()) {
            if(boardEntry.getKey().equals(loginId)){ findUserBoard.add(boardEntry.getValue()); }
        }
        return findUserBoard;
    }

    //메뉴 바에서 파트별로 검색
    public List<Board> findPartBoard(String part){
        List<Board> mBoard = new ArrayList<>();
        for (Board value : boardRepository.getBoardStore().values()) {
            if(value.getPart().equals(part)){ mBoard.add(value); }
        }
        return mBoard;
    }
}
