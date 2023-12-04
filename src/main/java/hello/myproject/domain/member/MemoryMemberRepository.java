package hello.myproject.domain.member;

import hello.myproject.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Repository
public class MemoryMemberRepository {
    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(sequence++);
        store.put(member.getId(), member);
        log.info("store Member={}", member);
        return member;
    }

    public Member findId(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findLoginId(String loginId){
        List<Member> members = findAll();
        for (Member m : members) {
            if(loginId.equals(m.getLoginId())) return Optional.of(m);
        }
        return Optional.empty();
    }

    public void update(String loginId, Member updatePassword){
        Optional<Member> memberOp = findLoginId(loginId);
        Member member = memberOp.get();

        member.setPassword(updatePassword.getPassword());
        member.setPasswordVerify(updatePassword.getPasswordVerify());
    }

    public void clearStore(){
        store.clear();
    }
}
