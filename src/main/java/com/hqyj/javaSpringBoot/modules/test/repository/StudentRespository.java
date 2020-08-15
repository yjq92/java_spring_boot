package com.hqyj.javaSpringBoot.modules.test.repository;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRespository extends JpaRepository<Student,Integer> {
    List<Student> findByStudentName(String studentName);

    List<Student> findByStudentNameLike(String studentName);

    List<Student> findTop2ByStudentNameLike(String studentName);

   // @Query(value = "select s from Student s where s.studentName=?1 and s.studentCard.cardId=?2")

    //@Query(value = "select s from Student s where s.studentName = :studentName " +
    //       "and s.studentCard.cardId = :cardId")

    @Query(nativeQuery = true,
            value = "select * from h_student where student_name = :studentName " +
                    "and card_id = :cardId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName,
                                      @Param("cardId") int cardId);

}
