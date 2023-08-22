package com.teamntp.firstproject.course.service;

import com.teamntp.firstproject.common.dto.PageRequestDTO;
import com.teamntp.firstproject.common.dto.PageResultDTO;
import com.teamntp.firstproject.course.dto.CourseDTO;
import com.teamntp.firstproject.course.dto.SyllabusDTO;
import com.teamntp.firstproject.course.entity.Course;
import com.teamntp.firstproject.course.entity.Syllabus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface CourseService {

    Long register(CourseDTO courseDTO); // 훈련과정등록


    PageResultDTO<CourseDTO, Object[]> getList(PageRequestDTO pageRequestDTO); // 목록처리


    default CourseDTO entitiesToDTO(Course course, List<Syllabus> syllabusList) {
        // List<Syllabus> : 조회 화면에서 여러 개의 강의계획서 파일을 처리하기 위해 리스트로 받음

        CourseDTO courseDTO = CourseDTO.builder()
                .courseIdx(course.getCourseIdx())
                .courseName(course.getCourseName())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .regDate(course.getRegDate())
                .modDate(course.getModDate())
                .build();

//        List<SyllabusDTO> syllabusDTOList = syllabusList.stream().map(syllabus -> {
//            return SyllabusDTO.builder()
//                    .syllabusFileName(syllabus.getSyllabusFileName())
//                    .syllabusFilePath(syllabus.getSyllabusFilePath())
//                    .uuid(syllabus.getUuid())
//                    .build();
//        }).collect(Collectors.toList());
//
//        courseDTO.setSyllabusDTOList(syllabusDTOList);

        return courseDTO;
    }


    default Map<String, Object> dtoToEntity(CourseDTO courseDTO) {
        // Course, Syllabus 같이 처리하기 위해 Map 타입을 이용해서 반환
        Map<String, Object> entityMap = new HashMap<>();

        Course course = Course.builder()
                .courseIdx(courseDTO.getCourseIdx())
                .courseName(courseDTO.getCourseName())
                .startDate(courseDTO.getStartDate())
                .endDate(courseDTO.getEndDate())
                .build();

        entityMap.put("course", course);

        List<SyllabusDTO> syllabusDTOList = courseDTO.getSyllabusDTOList();

        // SyllabusDTO 처리
        if (syllabusDTOList != null && syllabusDTOList.size() > 0) {
            List<Syllabus> syllabusList = syllabusDTOList.stream().map(syllabusDTO -> {
                Syllabus syllabus = Syllabus.builder()
                        .syllabusFileName(syllabusDTO.getSyllabusFileName())
                        .syllabusFilePath(syllabusDTO.getSyllabusFilePath())
                        .uuid(syllabusDTO.getUuid())
                        .course(course)
                        .build();
                return syllabus;
            }).collect(Collectors.toList());

            entityMap.put("syllabusList", syllabusList);
        }
        return entityMap;
        // dtoToEntity() Map 타입을 반환, Course 랑 Syllabus 객체의 List 처리
    }
}
