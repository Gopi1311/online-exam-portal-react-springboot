package com.OnlineExamSystem.demo.dto;

import com.OnlineExamSystem.demo.model.Test;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class TestDetailResponse {

    private Long testId;
    private String testname;
    private List<Test> tests;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public TestDetailResponse(Long testId, String testname, List<Test> tests) {
        this.testId = testId;
        this.testname = testname;
        this.tests = tests;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
