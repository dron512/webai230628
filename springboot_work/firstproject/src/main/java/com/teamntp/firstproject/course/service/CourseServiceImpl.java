package com.teamntp.firstproject.course.service;

import com.teamntp.firstproject.common.dto.PageRequestDTO;
import com.teamntp.firstproject.common.dto.PageResultDTO;
import com.teamntp.firstproject.course.dto.CourseDTO;
import com.teamntp.firstproject.course.entity.Course;
import com.teamntp.firstproject.course.entity.Syllabus;
import com.teamntp.firstproject.course.repository.CourseRepository;
import com.teamntp.firstproject.course.repository.SyllabusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final SyllabusRepository syllabusRepository;

    @Transactional
    @Override
    public Long register(CourseDTO courseDTO) {

        Map<String,Object> entityMap = dtoToEntity(courseDTO);
        Course course = (Course) entityMap.get("course");
        List<Syllabus> syllabusList = (List<Syllabus>) entityMap.get("syllabusList");

        courseRepository.save(course);
        syllabusList.forEach(syllabus -> {
            syllabusRepository.save(syllabus);
        });

        return course.getCourseIdx(); // courseIdx 반환하고 마무리
    }

    @Override
    public PageResultDTO<CourseDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("courseIdx").descending());
        Page<Object[]> result = courseRepository.getListPage(pageable);

        Function<Object[], CourseDTO> fn = (arr -> entitiesToDTO(
                (Course) arr[0]
                , Arrays.asList(syllabusRepository.findById((Long)arr[1]).get())
        ));

        return new PageResultDTO<>(result, fn);
    }
}
