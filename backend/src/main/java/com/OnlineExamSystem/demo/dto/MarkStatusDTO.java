package com.OnlineExamSystem.demo.dto;

import com.OnlineExamSystem.demo.model.Mark;

import java.util.List;

public class MarkStatusDTO {
    private String testName;
    private List<Mark> marks;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public MarkStatusDTO(String testName, List<Mark> marks) {
        this.testName = testName;
        this.marks = marks;
    }
}
