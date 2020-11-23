package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    /**
     * 验证参数是否有效
     * @return
     */
    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }
}
