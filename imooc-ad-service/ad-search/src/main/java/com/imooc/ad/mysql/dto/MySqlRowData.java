package com.imooc.ad.mysql.dto;

import com.imooc.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySqlRowData {
    //数据库名
    private String tableName;
    //数据表的层级
    private String level;
    //定义的操作数据类型
    private OpType opType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
