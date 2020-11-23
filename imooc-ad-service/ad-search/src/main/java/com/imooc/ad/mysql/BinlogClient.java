package com.imooc.ad.mysql;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.imooc.ad.mysql.listener.AggregationListener;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class BinlogClient {

    private BinaryLogClient client;

    private final BinlogConfig config;
    private final AggregationListener listener;

    public BinlogClient(AggregationListener listener, BinlogConfig config) {
        this.listener = listener;
        this.config = config;
    }

    public void connect(){
        new Thread(() -> {
            client = new BinaryLogClient(
                    config.getHost(),
                    config.getPort(),
                    config.getUsername(),
                    config.getPassword()
            );
            if (!StringUtils.isEmpty(config.getBinlogName()) && !config.getPosition().equals(-1L)){
                client.setBinlogFilename(config.getBinlogName());
                client.setBinlogPosition(config.getPosition());
            }
            client.registerEventListener(listener);
            try{
                log.info("Connecting to mysql start");
                client.connect();
                log.info("Connecting to mysql done");
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }).start();
    }

    public void close(){
        try{
            client.disconnect();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
