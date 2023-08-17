package com.teamntp.firstproject.member.controller;

import com.teamntp.firstproject.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

    @GetMapping("/all")
    public void exAll() {
        log.info("sample for all......................");
    }

    @GetMapping("/student")
    public void exStudent() {
        log.info("sample for student......................");
    }

    @GetMapping("/teacher")
    public void exTeacher() {
        log.info("sample for teacher......................");
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("sample for admin......................");
    }

    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        // @AuthenticationPrincipal 타입은 getPrincipal() method 를 통해서 Object 타입의 반환이 있습니다.
        // 따라서 별도의 캐스팅 없이 AuthMemberDTO 타입을 사용할 수 있습니다.
        // 와! 편하다!
        log.info("sample for admin......................");
        log.info("=====================================================");
        log.info("=====================================================");
        log.info(authMemberDTO);
    }

    @GetMapping("/member/modify")
    @ResponseBody
    public String exMemberModify(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("==============================================================================");
        log.info("되나?????????");
        log.info("fromSocial -> " + authMemberDTO.isFromSocial());
        // todo 아 권한을 안받아온 게 아니고 소셜에서 온 애라서 아직 권한을 안 줬네유ㅇㅅㅇ;

        return "너,, 소셜에서 왔군하,,, 회원 정보 수정해줄ㄹ ㅐ?..plz";
    }

}
