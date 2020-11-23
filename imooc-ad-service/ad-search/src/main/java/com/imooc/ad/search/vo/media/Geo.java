package com.imooc.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地域信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geo {

    //纬度
    private Float latitude;
    //经度
    private Float longitude;
    //城市
    private String city;
    //省份
    private String province;
}
