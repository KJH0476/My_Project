package hello.myproject.domain.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemoryBoardRepository implements BoardRepository{

    // <사용자아이디, 게시글내용>
    private static Map<Long, Board> boardStore = new ConcurrentHashMap<>();
    private static long seq = 0L;

    /**
     * 게시글 저장
     */
    public void save(Board board){
        log.info("run BoardRepository Save");
        board.setId(seq++);
        boardStore.put(board.getId(), board);
        log.info("save board={}", board);
    }

    /**
     * 저장된 게시 글 모두 반환
     */
    public List<Board> findAll(){
        return new ArrayList<>(boardStore.values());
    }

    /**
     * 제목으로 게시글 찾기(검색)
     */
    public List<Board> findByTitle(String title){
        List<Board> boards = new ArrayList<>();
        for (Board board : boardStore.values()) {
            if(board.getTitle().equals(title)){
                log.info("find board={}", board);
                boards.add(board);
            }
        }
        return boards;
    }

    /**
     * 로그인 아이디로 게시글 찾기(마이페이지)
     */
    public List<Board> findByLoginUser(String loginId){
        List<Board> boards = new ArrayList<>();
        for (Board board : boardStore.values()) {
            if(board.getLoginId().equals(loginId)){
                log.info("find board={}", board);
                boards.add(board);
            }
        }
        return boards;
    }

    /**
     * 파트별로 게시글 찾기(메뉴)
     */
    public List<Board> findByPart(String part){
        List<Board> boards = new ArrayList<>();
        for (Board board : boardStore.values()) {
            if(board.getPart().equals(part)){
                log.info("find board={}", board);
                boards.add(board);
            }
        }
        return boards;
    }

    /**
     * 게시글 아이디로 검색
     */
    public Board findById(Long id){
        for (Long storeId : boardStore.keySet()) {
            if(storeId.equals(id)) {
                return boardStore.get(id);
            }
        }
        return null;
    }

    /**
     * 게시글 초기화
     */
    public void clearBoardStore(){
        boardStore.clear();
    }
}
