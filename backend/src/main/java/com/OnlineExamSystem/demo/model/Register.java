package com.OnlineExamSystem.demo.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String institute;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('student','teacher')")
    private Role role;
    private String empid;

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<TestDetail> testDetails;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", institute='" + institute + '\'' +
                ", role=" + role +
                ", empid='" + empid + '\'' +
                ", testDetails=" + testDetails +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public List<TestDetail> getTestDetails() {
        return testDetails;
    }

    public void setTestDetails(List<TestDetail> testDetails) {
        this.testDetails = testDetails;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;


    public enum Role {
        student, teacher
    }


}
