package com.hqyj.javaSpringBoot.modules.test.controller;

import com.hqyj.javaSpringBoot.modules.test.entity.City;
import com.hqyj.javaSpringBoot.modules.test.entity.Country;
import com.hqyj.javaSpringBoot.modules.test.service.CityService;
import com.hqyj.javaSpringBoot.modules.test.service.CountryService;
import com.hqyj.javaSpringBoot.modules.test.vo.ApplicationTest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/test")
public class TestSpringBootController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestSpringBootController.class);

    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test111111";
    }

    //测试log 日志

    /**
     * 127.0.0.1:8080/test/testOne
     * 测试框架搭建是否成功
     */
    @GetMapping("/testOne")
    @ResponseBody
    public String testOne() {
        return "Test SpringBoot Successfully!";
    }

    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    //引入ApplicationTest组件
    @Autowired
    private ApplicationTest applicationTest;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }


    /**
     * 127.0.0.1:8080/test/configTest
     */
    @GetMapping("/configTest")
    @ResponseBody
    public String configTest() {
        return new StringBuffer().append(name).append("-----")
                .append(age).append("--------")
                .append(desc).append("------")
                .append(random).append("------").toString();
    }

    /**
     * 127.0.0.1:8080/test/configTest2
     */
    @GetMapping("/configTest2")
    @ResponseBody
    public String configTest2() {
        StringBuffer s1 = new StringBuffer();
        return s1.append(applicationTest.getName()).append("-----")
                .append(applicationTest.getAge()).append("--------")
                .append(applicationTest.getDesc()).append("------")
                .append(applicationTest.getRandom()).append("------").toString();
    }

    /**
     * 127.0.0.1/test/index1
     */

    @GetMapping("/index1")
    public String indexpage1() {
        return "test/indexpage1";
    }

    /**
     * 127.0.0.1/test/index2
     */
    @GetMapping("/index2")
    public String indexpage2(ModelMap modelMap) {
        modelMap.addAttribute("template", "test/index2");
        return "index";
    }

    /**
     * 127.0.0.1/test/index
     */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap) {
        int countryId = 522;
        List<City> cities = cityService.getCitiesByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);

        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo",
                "/upload/11111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/updateCity");
        //modelMap.addAttribute("template", "test/index");
        // 返回外层的碎片组装器
        return "index";
    }

    /**
     * 127.0.0.1/test/testDec?paramKey=fuck
     */
    @GetMapping("/testDec")
    @ResponseBody
    public String testDec(HttpServletRequest request, @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2 = request.getParameter("paramKey");
        return "This is test module desc." + paramValue + "==" + paramValue2;
    }

    /**
     * 127.0.0.1/test/file
     */
    @PostMapping(value = "/file", consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "please select file");
            return "redirect:/test/index";
        }
        try {
            String objectFilePath = "E:\\upload\\" + file.getOriginalFilename();//上传之后保存文件的地址
            File objectFile = new File(objectFilePath);
            file.transferTo(objectFile);
            redirectAttributes.addFlashAttribute("message", "upload file success");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "upload file failed");

        }
        return "redirect:/test/index";
    }

    /**
     * 127.0.0.1/test/files
     */
    @PostMapping(value = "/files", consumes = "multipart/form-data")//弊端，不能同时点击多个文件，直接上传，改进
    public String uploadFiles(@RequestParam MultipartFile[] files, RedirectAttributes redirectAttributes) {
        boolean empty=true;
        try {
            for (MultipartFile file : files) {
                if(file.isEmpty()){
                    continue;
                }
                String objectFilePath = "E:\\upload\\" + file.getOriginalFilename();//上传之后保存文件的地址
                File objectFile = new File(objectFilePath);
                file.transferTo(objectFile);
                empty=false;
            }
            if(empty){
                redirectAttributes.addFlashAttribute("message", "please select files");
            }else {
                redirectAttributes.addFlashAttribute("message", "upload files success");
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "upload files failed");
        }
        return "redirect:/test/index";
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName){
        Resource resource=null;
        try {
            resource=new UrlResource(Paths.get("E:\\upload\\"+fileName).toUri());
            if(resource.exists()&&resource.isReadable()){
                return ResponseEntity.ok()
                                     .header(HttpHeaders.CONTENT_TYPE,"application/octet-stream")
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                String.format("attachment; filename=\"%s\"", resource.getFilename()))
                        .body(resource);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
