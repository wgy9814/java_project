package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.itcast")
public class JasperApplication {
    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(JasperApplication.class,args);
    }
}
