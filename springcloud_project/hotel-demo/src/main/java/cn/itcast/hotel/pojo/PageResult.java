package cn.itcast.hotel.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author: wuguangyuan
 * @create-date: 2022/12/12 15:45
 */

@Data
public class PageResult {
    private Long total;
    private List<HotelDoc> hotels;

    public PageResult() {
    }

    public PageResult(Long total, List<HotelDoc> hotels) {
        this.total = total;
        this.hotels = hotels;
    }
}
