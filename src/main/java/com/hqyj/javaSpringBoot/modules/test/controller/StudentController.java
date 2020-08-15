package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import com.hqyj.javaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * @param studentId
     * 127.0.0.1/api/student/2 ----post
     */
    @GetMapping(value = "/student/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId){
        return studentService.getStudentByStudentId(studentId);
    }

    /**
     * 127.0.0.1/api/students ---- post
     * {"currentPage":"1","pageSize":"5","keyWord":"li","orderBy":"studentName","sort":"desc"}
     * @return
     */
    @PostMapping(value = "/students", consumes = "application/json")
    public Page<Student> getStudentsBySearchVo(@RequestBody SearchVo searchVo) {
        return studentService.getStudentsBySearchVo(searchVo);
    }

    /**
     *127.0.0.1/api/students?studentName=lisa1
     */
   /* @GetMapping(value = "/students")
    public List<Student> getStudentsByStudentName(@RequestParam String studentName) {
        return studentService.getStudentsByStudentName(studentName);
    }*/


    /**
     *127.0.0.1/api/students?studentName=lisa1&cardId=1
     */
    @GetMapping("/students")
    public List<Student> getStudentsByParams(
            @RequestParam String studentName,
            @RequestParam(required = false, defaultValue = "0") Integer cardId) {
        return studentService.getStudentsByStudentName(studentName, cardId);
    }
}
