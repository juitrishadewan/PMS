package com.coderbd.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String companyName;

    @NotEmpty
    @Email
    @NotEmpty(message = "Enter An Email")
    @Column(nullable = false, unique = true)
    private String email;

    private String mobile;
    private String address;
    private String website;
    private int noOfStaffs;
    private Date companyRegistrationDate;
    private long registrationNo;
    //////File Upload==============
    @Column(nullable = true)
    private long fileSize;
    private String fileName;
    //  @Lob
    // private byte[] file;
    private String filePath;
    private String fileExtension;

    @OneToMany(mappedBy = "company")
    private List<Project> projectList;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getNoOfStaffs() {
        return noOfStaffs;
    }

    public void setNoOfStaffs(int noOfStaffs) {
        this.noOfStaffs = noOfStaffs;
    }

    public Date getCompanyRegistrationDate() {
        return companyRegistrationDate;
    }

    public void setCompanyRegistrationDate(Date companyRegistrationDate) {
        this.companyRegistrationDate = companyRegistrationDate;
    }

    public long getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(long registrationNo) {
        this.registrationNo = registrationNo;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return noOfStaffs == company.noOfStaffs &&
                registrationNo == company.registrationNo &&
                fileSize == company.fileSize &&
                Objects.equals(id, company.id) &&
                Objects.equals(companyName, company.companyName) &&
                Objects.equals(email, company.email) &&
                Objects.equals(mobile, company.mobile) &&
                Objects.equals(address, company.address) &&
                Objects.equals(website, company.website) &&
                Objects.equals(companyRegistrationDate, company.companyRegistrationDate) &&
                Objects.equals(fileName, company.fileName) &&
                Objects.equals(filePath, company.filePath) &&
                Objects.equals(fileExtension, company.fileExtension) &&
                Objects.equals(projectList, company.projectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, email, mobile, address, website, noOfStaffs, companyRegistrationDate, registrationNo, fileSize, fileName, filePath, fileExtension, projectList);
    }
}
