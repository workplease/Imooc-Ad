package com.imooc.ad.sender;

import com.imooc.ad.mysql.dto.MySqlRowData;

public interface ISender {

    /**
     * 实现数据的投递
     * @param rowData
     */
    void sender(MySqlRowData rowData);
}
