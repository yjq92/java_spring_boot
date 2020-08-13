package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;

public interface StudentService {
    Result<Student> insertStudent(Student student);
}
