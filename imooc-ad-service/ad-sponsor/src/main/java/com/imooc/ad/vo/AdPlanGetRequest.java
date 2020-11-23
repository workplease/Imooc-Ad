package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//查询操作的请求
public class AdPlanGetRequest {

    private Long userId;
    private List<Long> ids;

    /**
     * 对查询操作的输入参数进行验证
     * @return
     */
    public boolean validate(){
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
