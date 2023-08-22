package com.teamntp.firstproject.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    private String fileName;
    private String uuid;
    private String folderPath;


    // 전체 경로가 필요한 경우를 대비한 getURL()
    public String getURL() {
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
