package com.OnlineExamSystem.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", userid=" + userid +
                ", testDetail=" + testDetail +
                ", mark=" + mark +
                ", date=" + date +
                ", cheatingCount=" + cheatingCount +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Register userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Register getUserid() {
        return userid;
    }

    public void setUserid(Register userid) {
        this.userid = userid;
    }

    public TestDetail getTestDetail() {
        return testDetail;
    }

    public void setTestDetail(TestDetail testDetail) {
        this.testDetail = testDetail;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCheatingCount() {
        return cheatingCount;
    }

    public void setCheatingCount(int cheatingCount) {
        this.cheatingCount = cheatingCount;
    }

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private TestDetail testDetail;

    private int mark;

    private LocalDateTime date;

    private int cheatingCount;
}
