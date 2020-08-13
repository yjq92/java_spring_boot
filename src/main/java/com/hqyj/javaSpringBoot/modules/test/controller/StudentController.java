package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import com.hqyj.javaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * @param student
     * 127.0.0.1/api/insertStudent ---post
     * {"studentName":"lisa1","studentCard":{"cardId":"1"}}
     */
    @PostMapping(value = "insertStudent",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }
}
