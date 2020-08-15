package com.hqyj.javaSpringBoot.modules.test.service.Impl;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.javaSpringBoot.modules.test.entity.Student;
import com.hqyj.javaSpringBoot.modules.test.repository.StudentRespository;
import com.hqyj.javaSpringBoot.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRespository studentRespository;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRespository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStaus.SUCCESS.status, "inset success.", student);
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        return studentRespository.findById(studentId).get();
    }

    @Override
    public Page<Student> getStudentsBySearchVo(SearchVo searchVo) {
        //根据studentId排序
        String orderBy = StringUtils.isBlank(searchVo.getOrderBy()) ? "studentId" : searchVo.getOrderBy();
        //排序方式
        Sort.Direction direction = StringUtils.isBlank(searchVo.getSort()) || searchVo.getSort().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = new Sort(direction, orderBy);
        //创建分页对象（当前页起始为0）
        Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1, searchVo.getPageSize(),sort);
        //创建一个example对象,按照模板查询
        Student student = new Student();
        //如果keyword为null，则设置的studentName不参与查询条件
        student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher=ExampleMatcher.matching()
                //全部模糊查询，即 %{studentName}%
                .withMatcher("studentName",match->match.contains())
                // 忽略字段，即不管id是什么值都不加入查询条件
                .withIgnorePaths("studentId");
        Example<Student> example=Example.of(student,matcher);
        return studentRespository.findAll(example,pageable);
    }

    @Override
    public List<Student> getStudentsByStudentName(String studentName,int cardId) {
/*
        return Optional.ofNullable(studentRespository.findByStudentName(studentName))
                                                     .orElse(Collections.emptyList());
*/
        /*return Optional.ofNullable(studentRespository.findByStudentNameLike(
                String.format("%s%S%s","%",studentName,"%")))
                .orElse(Collections.emptyList());*/


        if(cardId>0){
            return studentRespository.getStudentsByParams(studentName,cardId);
        } else {
            return Optional.ofNullable(studentRespository.findTop2ByStudentNameLike(
                    String.format("%s%S%s","%",studentName,"%")))
                    .orElse(Collections.emptyList());
        }
    }
}
