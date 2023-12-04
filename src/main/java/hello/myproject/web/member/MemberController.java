package hello.myproject.web.member;

import hello.myproject.domain.member.Member;
import hello.myproject.domain.member.MemberRepository;
import hello.myproject.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member){
        return "add/addForm";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute Member member, BindingResult bindingResult){

        //아이디 중복 로직
        Optional<Member> loginId = memberService.findLoginId(member.getLoginId());
        if(loginId.isPresent()){
            log.info("아이디 중복");
            bindingResult.reject("AddFail", "사용하실 수 없는 아이디입니다.");
        }
        //비밀번호 검증 로직
        if(!(member.getPassword().equals(member.getPasswordVerify()))){
            log.info("비밀번호 검증 실패");
            bindingResult.reject("VerifyFail", "비밀번호가 일치하지 않습니다.");
        }
        if(bindingResult.hasErrors()) return "add/addForm";

        //성공 로직
        Member saveMember = memberService.save(member);
        log.info("Add Ok!! saveMember={}", saveMember);

        return "redirect:/";
    }

    @GetMapping("/memberInfo/{loginId}")
    public String memberInfoPage(@PathVariable String loginId, Model model){
        Optional<Member> member = memberService.findLoginId(loginId);
        model.addAttribute("member", member.get());
        log.info("memberInfo={}", member.get());

        return "page/memberInfoPage";
    }

    @GetMapping("/edit")
    public String editPasswordForm(@ModelAttribute PasswordForm passwordForm){
        return "edit/editPasswordForm";
    }

    @PostMapping("/edit/{loginId}")
    public String editPassword(@Validated @ModelAttribute PasswordForm passwordForm,
                               BindingResult bindingResult, @PathVariable String loginId){
        log.info("editPassword");

        if(!(passwordForm.getPassword().equals(passwordForm.getPasswordVerify()))){
            log.info("비밀번호 검증 실패");
            bindingResult.reject("VerifyFail", "비밀번호가 일치하지 않습니다.");
        }
        if(bindingResult.hasErrors()){ return "edit/editPasswordForm"; }

        memberService.update(loginId, passwordForm);
        return "redirect:/memberInfo/"+loginId;
    }
}
