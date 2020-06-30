package duksung.backend.hororok.ugeubi.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailForm {
    SIGN_UP("[우급이] 회원가입 인증번호 입니다.","회원가입 인증번호 : "),
    FIND_USER_PASSWORD("[우급이] 회원 비밀번호 찾기 인증코드 입니다.", "비밀번호 찾기 인증코드 : ");

    private String subject; //제목
    private String content; //내용
}
