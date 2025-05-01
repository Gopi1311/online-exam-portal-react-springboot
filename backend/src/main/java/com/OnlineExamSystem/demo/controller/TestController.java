package com.OnlineExamSystem.demo.controller;


import com.OnlineExamSystem.demo.model.dto.TestDetailResponse;
import com.OnlineExamSystem.demo.model.dto.TestRequest;
import com.OnlineExamSystem.demo.model.Mark;
import com.OnlineExamSystem.demo.model.TestDetail;
import com.OnlineExamSystem.demo.service.TestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTest(@RequestBody TestRequest testRequest, HttpSession session){

        Long userId= (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized. Please login.");
        }

        try{
            System.out.println(testRequest);
            testService.saveTest(testRequest,userId);
            return ResponseEntity.ok("Test added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/view")
    public List<TestDetail> testDetail(HttpSession session){
        Long userId= (Long) session.getAttribute("userId");
        System.out.println(testService.viewAllTestByTeacher(userId));
       return  testService.viewAllTestByTeacher(userId);
    }

    @GetMapping("/getQuestions/{id}")
    public ResponseEntity<TestDetailResponse> testById(@PathVariable Long id) {
        TestDetailResponse response = testService.testById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> testUpdate(@PathVariable Long id, @RequestBody TestDetailResponse testDetailResponse) {
        System.out.println("Gopinath : " + testDetailResponse);
        testService.updateTestById(id, testDetailResponse);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/removeTest/{id}")
    public ResponseEntity<Void> deleteTestById(@PathVariable Long id){
        testService.deleteTestById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/studentTestList")
    public List<TestDetail> testListForStudent(){

        return testService.allTestList();

    }

    @PostMapping("/markSubmit/{id}")
    public ResponseEntity<Void> submitTest(@PathVariable Long id ,@RequestBody Mark mark,HttpSession session){
        Long userId= (Long) session.getAttribute("userId");
        testService.submitTest(id,userId,mark);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}

