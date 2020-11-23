package com.imooc.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class BinlogRowData {

    //表的名称
    private TableTemplate table;
    //操作类型
    private EventType eventType;

    private List<Map<String, String>> after;

    private List<Map<String, String>> before;
}
