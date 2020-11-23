package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
//增删改操作的请求
public class AdPlanRequest {

    private Long id;
    private Long userId;
    private String planName;
    private String startDate;
    private String endDate;

    /**
     * 对创建操作的输入参数进行校验
     * @return
     */
    public boolean createValidate(){
        return userId != null
                && !StringUtils.isEmpty(planName)
                && !StringUtils.isEmpty(startDate)
                && !StringUtils.isEmpty(endDate);
    }

    /**
     * 对更新操作的输入参数进行校验
     * @return
     */
    public boolean updateValidate(){
        return id != null && userId != null;
    }

    /**
     * 对删除操作的输入参数进行校验
     * @return
     */
    public boolean deleteValidate(){
        return id != null && userId != null;
    }
}
