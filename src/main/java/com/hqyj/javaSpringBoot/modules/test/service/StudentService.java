package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Result<Student> insertStudent(Student student);
    Student getStudentByStudentId(int studentId);
    Page<Student> getStudentsBySearchVo(SearchVo searchVo);
    //List<Student> getStudentsByStudentName(String studentName);

    List<Student> getStudentsByStudentName(String studentName,int cardId);


}
