package cn.itcast.domain;

public class User {

    private String id;
    private String username;
    private String company;
    private String dept;
    private String mobile;

    public User(String id, String username, String company, String dept, String mobile) {
        this.id = id;
        this.username = username;
        this.company = company;
        this.dept = dept;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
