package com.imooc.ad.mysql.listener;

import com.imooc.ad.mysql.dto.BinlogRowData;


public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
