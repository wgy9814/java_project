package com.itheima.pattern.builder.demo1;

/**
 * @author: wuguangyuan
 * @create-date: 2022/11/23 18:31
 */
public class MobileBuilder extends  Builder{
    public void buildFrame() {
        bike.setFrame("碳纤维车架");
    }

    public void buildSeat() {
        bike.setSeat("真皮车座");
    }

    public Bike createBike() {
        return bike;
    }
}
