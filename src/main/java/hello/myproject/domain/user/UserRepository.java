package hello.myproject.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    User findId(Long id);
    List<User> findAll();
    Optional<User> findLoginId(String loginId);
    void clearStore();
}
