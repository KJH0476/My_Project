package hello.myproject.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class UserRepository {

    private static Map<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public User save(User user){
        user.setId(sequence++);
        store.put(user.getId(), user);
        log.info("store user={}", user);
        return user;
    }

    public User findId(Long id){
        return store.get(id);
    }

    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }

    public Optional<User> findLoginId(String loginId){
        List<User> users = findAll();
        for (User u : users) {
            if(loginId.equals(u.getLoginId())) return Optional.of(u);
        }
        return Optional.empty();
    }

    public void clearStore(){
        store.clear();
    }
}
