package cn.itcast.domain;

public class UserCount {
    private String company;
    private Long count;

    public UserCount(String company, Long count) {
        this.company = company;
        this.count = count;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
