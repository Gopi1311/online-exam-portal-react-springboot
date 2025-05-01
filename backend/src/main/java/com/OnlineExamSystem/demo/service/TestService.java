package com.OnlineExamSystem.demo.service;

import com.OnlineExamSystem.demo.model.dto.TestDetailResponse;
import com.OnlineExamSystem.demo.model.dto.TestRequest;

import com.OnlineExamSystem.demo.model.Mark;
import com.OnlineExamSystem.demo.model.Register;
import com.OnlineExamSystem.demo.model.Test;
import com.OnlineExamSystem.demo.model.TestDetail;
import com.OnlineExamSystem.demo.repo.MarkRepo;
import com.OnlineExamSystem.demo.repo.RegisterRepo;
import com.OnlineExamSystem.demo.repo.TestDetailRepo;
import com.OnlineExamSystem.demo.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private RegisterRepo registerRepo;
    @Autowired
    private TestDetailRepo testDetailRepo;

    @Autowired
    private TestRepo testRepo;

    @Autowired
    private MarkRepo markRepo;


    public void saveTest(TestRequest testRequest, Long userId) {
        TestDetail testDetail=new TestDetail();
        testDetail.setTestname(testRequest.getTestname());
        Register register=registerRepo.findById(userId).orElseThrow(()->new RuntimeException("Teacher not found with id: " + userId));
        testDetail.setTeacher(register);
        TestDetail saveTestDetail=testDetailRepo.save(testDetail);

        List<Test> questions = testRequest.getQuestions().stream().map(q -> {
            Test test=new Test();
            test.setQuestion(q.getQuestion());
            test.setOption1(q.getOption1());
            test.setOption2(q.getOption2());
            test.setOption3(q.getOption3());
            test.setOption4(q.getOption4());
            test.setAnswer(q.getAnswer());
            test.setTestDetail(saveTestDetail);
            test.setLevel(q.getLevel());
            return test;
        }).collect(Collectors.toList());

        testRepo.saveAll(questions);


    }


    public List<TestDetail> viewAllTestByTeacher(Long userId) {
        return testDetailRepo.findByTeacherId(userId);
    }

    public TestDetailResponse testById(Long id) {
        TestDetail testDetail=testDetailRepo.findById(id).orElseThrow(()->new RuntimeException("TestDetails Not Found By ID"));
        List<Test> tests=testRepo.findByTestDetail(testDetail);
        return new TestDetailResponse(testDetail.getId(),testDetail.getTestname(),tests);
    }


    public void updateTestById(Long id, TestDetailResponse testDetailResponse) {
        TestDetail testDetail=testDetailRepo.findById(id).orElseThrow(()->new RuntimeException("TestDetails Not Founnd By ID"));
        testDetail.setTestname(testDetailResponse.getTestname());
        List<Test> updateTest=testDetailResponse.getTests() != null
                ? testDetailResponse.getTests().stream().map(q->{
            Test existingTest = testRepo.findById(q.getId())
                    .orElseThrow(() -> new RuntimeException("Test Not Found By ID: " + q.getId()));
            existingTest.setQuestion(q.getQuestion());
            existingTest.setOption1(q.getOption1());
            existingTest.setOption2(q.getOption2());
            existingTest.setOption3(q.getOption3());
            existingTest.setOption4(q.getOption4());
            existingTest.setAnswer(q.getAnswer());
            existingTest.setLevel(q.getLevel());
            return existingTest;
        }).collect(Collectors.toList()): Collections.emptyList();;
        testRepo.saveAll(updateTest);
        testDetailRepo.save(testDetail);

    }

    public void deleteTestById(Long id) {
        TestDetail testDetailResponse=testDetailRepo.findById(id)
                .orElseThrow(()->new RuntimeException("TestDetail Not Found By ID"));
        testDetailRepo.delete(testDetailResponse);

    }

    public List<TestDetail> allTestList() {
        return testDetailRepo.findAll();
    }

    public void submitTest(Long id, Long userId, Mark marks) {
        Register register=registerRepo.findById(userId).orElseThrow(()->new RuntimeException("User ID Not Found"));
        TestDetail testDetail=testDetailRepo.findById(id).orElseThrow(()->new RuntimeException("TestDetail Not Found By ID"));
        Mark mark=new Mark();
        mark.setCheatingCount(marks.getCheatingCount());
        mark.setUserid(register);
        mark.setTestDetail(testDetail);
        mark.setMark(marks.getMark());
        mark.setDate(marks.getDate());
        markRepo.save(mark);

    }
}
