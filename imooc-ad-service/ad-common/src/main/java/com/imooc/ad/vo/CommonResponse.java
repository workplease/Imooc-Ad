package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//实现get，set
@Data
//无参构造函数
@NoArgsConstructor
//全参构造函数
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
