package com.teamntp.firstproject.course.repository;

import com.teamntp.firstproject.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // (에러) MySQL8.0, ONLY_FULL_GROUP_BY 준수할 것. 최대한 standard 한 쿼리문을 짤 것.
    @Query("select c, max(s.snum) from Course c left join Syllabus s on s.course=c group by c ")
    Page<Object[]> getListPage(Pageable pageable); // 페이지 처리한 리스트
    // syllabusRepository.findById 로 최신 강의계획서만 보여주도록 처리

}
