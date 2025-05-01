package com.OnlineExamSystem.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "testdetail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testname;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "teacherid", referencedColumnName = "id")
    private Register teacher;

    @Override
    public String toString() {
        return "TestDetail{" +
                "id=" + id +
                ", testname='" + testname + '\'' +
                ", teacherId=" + (teacher != null ? teacher.getId() : null) +
                '}';
    }

    @OneToMany(mappedBy = "testDetail", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnoreProperties("testDetail")
    private List<Test> tests;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public Register getTeacher() {
        return teacher;
    }

    public void setTeacher(Register teacher) {
        this.teacher = teacher;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
