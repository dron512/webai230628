package com.teamntp.firstproject.member.dto;

import com.teamntp.firstproject.common.SelectionData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.teamntp.firstproject.constants.Regexp.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpDTO {
    // **데이터 유효성 검증 Annotation
    // @NotBlank: null, 빈값, 공백 불가
    // @Pattern: 정규표현식 검사

    // **커스텀 어노테이션
    // @SelectionData: 필수 입력 값이 아닌 선택 값 정규식 검사

    @NotBlank(message = "(*필수)")
    @Pattern(regexp = REGEXP_LOGIN_ID)
    private String loginId; // 아이디

    @NotBlank(message = "(*필수)")
    @Pattern(regexp = REGEXP_PASSWORD)
    private String password; // 비밀번호

    @NotBlank(message = "(*필수)")
    @Pattern(regexp = REGEXP_PASSWORD)
    private String passwordCheck; // 비밀번호 확인

    private String path; // 가입경로: form, google

    private String type; // 회원유형: student, teacher

    @NotBlank(message = "(*필수)")
    @Pattern(regexp = REGEXP_NAME)
    private String name; // 이름

    @NotBlank(message = "(*필수)")
    @Pattern(regexp = REGEXP_MOBILE_NO)
    private String mobileNo; // 연락처

    @NotBlank(message = "(*필수)")
    @Pattern(regexp = REGEXP_EMAIL)
    private String email; // 이메일


    // 속성에 message 나중에 설정할 것
    @SelectionData(regexp = REGEXP_ZIPCODE)
    private Long zipcode; // 우편번호

    @SelectionData(regexp = REGEXP_ADDRESS)
    private String address; // 주소

    @SelectionData(regexp = REGEXP_DETAIL_ADDRESS)
    private String detailAddress;// 상세주소


    // 비밀번호 일치 확인 method
    public boolean passwordChecker(String password, String passwordCheck) {
        if(password.equals(passwordCheck)) {
            return true;
        } else {
            return false;
        }
    }

}
