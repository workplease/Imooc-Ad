package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;

    private Integer positionType;
    private Long budget;

    /**
     * 对输入参数进行验证
     * @return
     */
    public boolean createValidate(){
        return planId != null && !StringUtils.isEmpty(unitName) && positionType != null && budget != null;
    }
}
