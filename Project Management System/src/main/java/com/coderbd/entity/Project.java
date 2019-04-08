package com.coderbd.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    private String requirements;
    private String analysisReport;
    private String useCaseAsJpg;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "project_status_id", nullable = false)
    private ProjectStatus projectStatus;

    @ManyToOne
    @JoinColumn(name = "pm_id", nullable = false)
    private User projectManger;

    @ManyToOne
    @JoinColumn(name = "team_lead_id", nullable = false)
    private User teamLead;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_consultant",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "consultant_id"))
    private Set<User> consultants;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_developer",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<User> developers;


    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "project")
    private List<ProjectEngineering> projectEngineeringList;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "project")
    private List<ProjectModule> projectModules;

    //////File Upload==============
    @Column(nullable = true)
    private long fileSize;
    private String fileName;
    private String filePath;
    private String fileExtension;

    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getAnalysisReport() {
        return analysisReport;
    }

    public void setAnalysisReport(String analysisReport) {
        this.analysisReport = analysisReport;
    }

    public String getUseCaseAsJpg() {
        return useCaseAsJpg;
    }

    public void setUseCaseAsJpg(String useCaseAsJpg) {
        this.useCaseAsJpg = useCaseAsJpg;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public User getProjectManger() {
        return projectManger;
    }

    public void setProjectManger(User projectManger) {
        this.projectManger = projectManger;
    }

    public User getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(User teamLead) {
        this.teamLead = teamLead;
    }

    public Set<User> getConsultants() {
        return consultants;
    }

    public void setConsultants(Set<User> consultants) {
        this.consultants = consultants;
    }

    public Set<User> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<User> developers) {
        this.developers = developers;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<ProjectEngineering> getProjectEngineeringList() {
        return projectEngineeringList;
    }

    public void setProjectEngineeringList(List<ProjectEngineering> projectEngineeringList) {
        this.projectEngineeringList = projectEngineeringList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<ProjectModule> getProjectModules() {
        return projectModules;
    }

    public void setProjectModules(List<ProjectModule> projectModules) {
        this.projectModules = projectModules;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return fileSize == project.fileSize &&
                Objects.equals(id, project.id) &&
                Objects.equals(title, project.title) &&
                Objects.equals(description, project.description) &&
                Objects.equals(requirements, project.requirements) &&
                Objects.equals(analysisReport, project.analysisReport) &&
                Objects.equals(useCaseAsJpg, project.useCaseAsJpg) &&
                Objects.equals(startDate, project.startDate) &&
                Objects.equals(projectStatus, project.projectStatus) &&
                Objects.equals(projectManger, project.projectManger) &&
                Objects.equals(teamLead, project.teamLead) &&
                Objects.equals(consultants, project.consultants) &&
                Objects.equals(developers, project.developers) &&
                Objects.equals(client, project.client) &&
                Objects.equals(projectEngineeringList, project.projectEngineeringList) &&
                Objects.equals(company, project.company) &&
                Objects.equals(projectModules, project.projectModules) &&
                Objects.equals(fileName, project.fileName) &&
                Objects.equals(filePath, project.filePath) &&
                Objects.equals(fileExtension, project.fileExtension);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, requirements, analysisReport, useCaseAsJpg, startDate, projectStatus, projectManger, teamLead, consultants, developers, client, projectEngineeringList, company, projectModules, fileSize, fileName, filePath, fileExtension);
    }
}
