package com.teamntp.firstproject.repository;

import com.teamntp.firstproject.course.entity.Course;
import com.teamntp.firstproject.course.entity.Syllabus;
import com.teamntp.firstproject.course.repository.CourseRepository;
import com.teamntp.firstproject.course.repository.SyllabusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SyllabusRepository syllabusRepository;

//    @Commit
//    @Transactional
//    @Test
    public void insertCourse() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Course course = Course.builder()
                    .courseName("Course..."+i)
                    .startDate(LocalDate.now().plusDays(i))
                    .endDate(LocalDate.now().plusMonths(i))
                    .build();
            System.out.println("===============================");

            courseRepository.save(course);

            int count = (int)(Math.random()*5) +1; // 1,2,3,4

            for(int j=0;j<count;j++) {
                Syllabus syllabus = Syllabus.builder()
                        .uuid(UUID.randomUUID().toString())
                        .course(course)
                        .syllabusFileName("test"+j+".pdf") // 일단 pdf 인 척 올려보자
                        .build();
                syllabusRepository.save(syllabus);
            }
        });
    }


//    @Test
    public void testGetCourseList() {
        List<Course> result = courseRepository.findAll();
        System.out.println(result);

        // 100개까지 출력됨
        for (Course arr: result) {
            System.out.println(arr);
        }

    }

    @Test
    public void testGetListPage() {
        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "courseIdx"));
        Page<Object[]> result = courseRepository.getListPage(pageRequest);

        for(Object[] objects: result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }


}
