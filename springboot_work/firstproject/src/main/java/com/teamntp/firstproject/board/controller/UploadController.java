package com.teamntp.firstproject.board.controller;

import com.teamntp.firstproject.board.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    @Value("${com.teamntp.upload.path}") // application.properties 변수를 가져옴
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        // 파라미터로 MultipartFile[] 배열을 받도록 함
        // -> 여러 개의 파일 정보를 동시에 처리하기 위해 배열로 처리
        for (MultipartFile uploadFile : uploadFiles) {
            // 업로드한 파일의 확장자 검사
            // '쉘(shell) 스크립트' 파일 등을 업로드 공격을 대비하기 위해서
            // 이미지, pdf, word, 엑셀, 한글, ... 등등만 가능하게
            if (!uploadFile.getContentType().startsWith("image")
                    && !uploadFile.getContentType().startsWith("application/pdf")
                    && !uploadFile.getContentType().startsWith("application/msword")
                    && !uploadFile.getContentType().startsWith("application/pdf")
                    && !uploadFile.getContentType().startsWith("application/vnd.ms-powerpoint")
                    && !uploadFile.getContentType().startsWith("application/vnd.ms-excel")
                    && !uploadFile.getContentType().startsWith("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                log.warn("This file has incorrect extension");
                // 위에서 지정한 파일 형식이 아닌 경우에 예외처리 대신 '403 FORBIDDEN' 반환하도록 함
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // originalName: 실제 파일 이름
            // 전체 경로가 들어올 경우를 대비한 처리
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("File Name: " + fileName);

            // 업로드 폴더 처리
            // 날짜 폴더 생성
            String folderPath = makeFolder();

            // 동일한 이름의 파일 처리
            // UUID
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try {
                // 업로드 된 파일 저장: MultipartFile 자체에 있는 transferTo() 이용
                uploadFile.transferTo(savePath);
                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // End of For
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/",File.separator);

        // make folder
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (!uploadPathFolder.exists()){
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
