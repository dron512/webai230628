package com.teamntp.firstproject.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyllabusDTO {

    private String uuid; // 고유 번호 생성

    private String syllabusFileName; // 강의계획서 파일이름

    private String syllabusFilePath; // 강의계획서 파일경로(년/월/일)

    public String getSyllabusURL() { // 전체 경로 가져오는 Method
        try {
            // URLEncoder
            return URLEncoder.encode(syllabusFilePath+"/"+uuid+"_"+syllabusFileName, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
