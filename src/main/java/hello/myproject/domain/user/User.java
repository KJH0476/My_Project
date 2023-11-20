package hello.myproject.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    private Long id;

    @NotEmpty(message = "아이디는 필수로 입력하여야 합니다.")
    private String loginId;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    @NotEmpty
    private String passwordVerify;

    @NotEmpty(message = "이름은 필수로 입력하여야 합니다.")
    private String name;

    @NotEmpty(message = "생년월일은 필수로 입력하여야 합니다.")
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$", message = "유효하지 않은 날짜 형식입니다.")
    private String birth;

    @NotEmpty(message = "전화번호는 필수로 입력하여야 합니다.")
    private String phoneNumber;

    @NotEmpty(message = "이메일은 필수로 입력하여야 합니다.")
    @Email(message = "유효한 이메일 주소를 입력하여야 합니다.")
    private String email;
}
