package com.itheima.pojo;

/**
 * @author: wuguangyuan
 * @create-date: 2022/9/10 12:32
 */
public class Brand {
    private Integer  id;;
    private String brand_name;
    private String company_name;
    private Integer  orderer;
    private String description;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getOrderer() {
        return orderer;
    }

    public void setOrderer(Integer orderer) {
        this.orderer = orderer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brand_name='" + brand_name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", orderer=" + orderer +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
