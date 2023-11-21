package hello.myproject.domain.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class BoardRepository {

    // <사용자아이디, 게시글내용>
    private Map<String, Board> boardStore = new ConcurrentHashMap<>();
    private static long seq = 0L;

    //알고리즘 저장
    public void save(Board board){
        boardStore.put(board.getLoginId(), board);
    }

    public Map<String, Board> getBoardStore(){
        return boardStore;
    }

    public void clearBoardStore(){
        boardStore.clear();
    }
}
