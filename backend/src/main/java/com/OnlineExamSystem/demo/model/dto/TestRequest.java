package com.OnlineExamSystem.demo.model.dto;

import java.util.List;

public class TestRequest {
    private String testname;
    private List<QuestionRequest> questions;

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public List<QuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequest> questions) {
        this.questions = questions;
    }
}

