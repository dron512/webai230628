package com.teamntp.firstproject.course.controller;

import com.teamntp.firstproject.course.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
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

    // 파일 업로드 처리
    @PostMapping("/uploadAjax") // 업로드 ajax 처리
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        // 파라미터로 MultipartFile[] 배열을 받도록 함
        // -> 여러 개의 파일 정보를 동시에 처리하기 위해 배열로 처리
        for (MultipartFile uploadFile : uploadFiles) {
            // 업로드한 파일의 확장자 검사
            // '쉘(shell) 스크립트' 파일 등을 업로드 공격을 대비하기 위해서
            // 이미지, pdf, word, 엑셀, 한글 파일만 업로드 가능하게 설정
            if (!uploadFile.getContentType().startsWith("image")
                    && !uploadFile.getContentType().startsWith("application/pdf")
                    && !uploadFile.getContentType().startsWith("application/msword")
                    && !uploadFile.getContentType().startsWith("application/vnd.ms-excel")
                    && !uploadFile.getContentType().startsWith("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    && !uploadFile.getContentType().startsWith("application/vnd.hancom.hwp")
                    && !uploadFile.getContentType().startsWith("application/haansofthwp")
                    && !uploadFile.getContentType().startsWith("application/x-hwp")) {
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

    // 날짜별 폴더 생성 method
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

    // 업로드 파일 화면 처리
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {
        // getFile() URL 인코딩된 파일 이름 파라미터로 받아서
        // 해당 파일을 byte[] 타입으로 만들어서 브라우저로 전송한다.

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName: "+srcFileName);

            File file = new File(uploadPath+File.separator+srcFileName);

            log.info("file: "+file);

            // HttpHeaders 에 정보를 담아서 전송
            HttpHeaders header = new HttpHeaders();

            // MIME 타입 처리: 파일 확장자에 따라 MIME 타입이 달라져야 한다.
            // java.nio.file, Files.probeContentType() 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));

            // 파일 데이터 처리
            // org.springframework.util.FileCopyUtils 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);


        } catch(Exception e) {
            log.error(e.getMessage()); // 로그에 오류 메시지 출력
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    // 업로드 파일 삭제
    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {

        String srcFileName = null;

        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");

            File file = new File(uploadPath+File.separator+srcFileName);
            boolean result = file.delete();
            return new ResponseEntity<>(result, HttpStatus.OK);


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
