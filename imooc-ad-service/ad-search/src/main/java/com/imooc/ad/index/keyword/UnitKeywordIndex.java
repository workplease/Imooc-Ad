package com.imooc.ad.index.keyword;

import com.imooc.ad.index.IndexAware;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    //关键词到推广单元 id 的映射
    private static Map<String,Set<Long>> keywordUnitMap;
    //推广单元 id 到关键词的映射，一个推广单元也可以设置很多个限制
    private static Map<Long,Set<String>> unitKeywordMap;

    static {
        keywordUnitMap = new ConcurrentHashMap<>();
        unitKeywordMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        if (StringUtils.isEmpty(key)){
            return Collections.emptySet();
        }
        Set<Long> result = keywordUnitMap.get(key);
        if (result == null){
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitKeywordIndex,before add: {}",unitKeywordMap);
        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key,keywordUnitMap, ConcurrentSkipListSet::new
        );
        unitIdSet.addAll(value);
        for (Long unitId:value){
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    unitId,unitKeywordMap,ConcurrentSkipListSet::new
            );
            keywordSet.add(key);
        }
        log.info("UnitKeywordIndex,after add: {}",unitKeywordMap);
    }

    /**
     * 由于更新成本比较高，不允许更新，更新操作可以通过删除加添加功能实现
     * @param key
     * @param value
     */
    @Override
    public void update(String key, Set<Long> value) {
        log.info("keyword index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitKeywordIndex,before delete: {}",unitKeywordMap);
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key,keywordUnitMap,ConcurrentSkipListSet::new
        );
        unitIds.removeAll(value);
        for (Long unitId : value){
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    unitId,unitKeywordMap,ConcurrentSkipListSet::new
            );
            keywordSet.remove(key);
        }
        log.info("UnitKeywordIndex,after delete: {}",unitKeywordMap);
    }

    /**
     * 匹配推广单元是否包含这些关键词
     * @param unitId
     * @param keywords
     * @return
     */
    public boolean match(Long unitId, List<String> keywords){
        if (unitKeywordMap.containsKey(unitId) && CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))){
            Set<String> unitKeywords = unitKeywordMap.get(unitId);
            return CollectionUtils.isSubCollection(keywords,unitKeywords);
        }
        return false;
    }
}
