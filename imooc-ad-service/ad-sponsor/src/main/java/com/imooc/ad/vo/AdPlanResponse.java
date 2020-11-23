package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//对创建更新操作的响应
public class AdPlanResponse {

    private Long id;
    private String planName;
}
