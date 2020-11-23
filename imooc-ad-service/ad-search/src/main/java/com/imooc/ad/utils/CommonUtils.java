package com.imooc.ad.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class CommonUtils {

    /**
     * 传进来一个 map，如果这个 map 的 key 不存在，返回一个新的 value 对象
     * @param key
     * @param map
     * @param factory
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> V getOrCreate(K key, Map<K,V> map, Supplier<V> factory){
        return map.computeIfAbsent(key,k -> factory.get());
    }

    /**
     * 拼接字符串
     * @param args
     * @return
     */
    public static String stringConcat(String... args){
        StringBuffer result = new StringBuffer();
        for (String arg : args) {
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * 将 String 类型转化为 Date 类型
     * @param dateString
     * @return
     */
    public static Date parseStringDate(String dateString){
        try{
            DateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zzz yyyy",
                    Locale.US
            );
            return DateUtils.addHours(
                    dateFormat.parse(dateString),
                    -8
            );
        }catch (ParseException ex){
            log.error("parseStringDate error:{}",dateString);
            return null;
        }
    }
}
