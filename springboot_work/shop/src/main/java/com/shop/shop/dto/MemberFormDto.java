package com.shop.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotEmpty(message = "이메일 필수")
    @Email(message = "이메일 형식으로 입력하시오")
    private String email;

    @NotEmpty(message = "비밀번호 필수")
    @Size(min = 8, max = 16,message = "8자 이상 16자 이하로 입력하시오")
    private String password;

    @NotEmpty(message = "주소는 필수 입력")
    private String address;
}






