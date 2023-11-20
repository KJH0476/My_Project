package hello.myproject.web.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty(message = "아이디는 필수로 입력하여야 합니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수로 입력하여야 합니다.")
    private String password;
}
