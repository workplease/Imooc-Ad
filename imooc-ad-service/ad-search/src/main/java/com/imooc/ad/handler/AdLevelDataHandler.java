package com.imooc.ad.handler;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.index.DataTable;
import com.imooc.ad.index.IndexAware;
import com.imooc.ad.index.adplan.AdPlanIndex;
import com.imooc.ad.index.adplan.AdPlanObject;
import com.imooc.ad.index.adunit.AdUnitIndex;
import com.imooc.ad.index.adunit.AdUnitObject;
import com.imooc.ad.index.creative.CreativeIndex;
import com.imooc.ad.index.creative.CreativeObject;
import com.imooc.ad.index.creativeunit.CreativeUnitIndex;
import com.imooc.ad.index.creativeunit.CreativeUnitObject;
import com.imooc.ad.index.district.UnitDistrictIndex;
import com.imooc.ad.mysql.constant.OpType;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. 索引之间存在着层级的划分，也就是依赖关系的划分
 * 2. 加载全量索引其实是增量索引 “增加” 的一种特殊实现
 */
@Slf4j
public class AdLevelDataHandler {

     public static void handleLevel2(AdPlanTable planTable,OpType type){
         AdPlanObject planObject = new AdPlanObject(
                 planTable.getUserId(),
                 planTable.getUserId(),
                 planTable.getPlanStatus(),
                 planTable.getStartDate(),
                 planTable.getEndDate()
         );
         handleBinlogEvent(
                 DataTable.of(AdPlanIndex.class),
                 planObject.getPlanId(),
                 planObject,
                 type
         );
     }

    public static void handleLevel2(CreativeTable creativeTable, OpType type){
        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }

    public static void handleLevel3(AdUnitTable unitTable,OpType type){
         AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(unitTable.getPlanId());
         if (null == adPlanObject){
             log.error("handleLevel3 found AdPlanObject error:{}",unitTable.getPlanId());
             return;
         }
        AdUnitObject unitObject = new AdUnitObject(
                unitTable.getUnitId(),
                unitTable.getUnitStatus(),
                unitTable.getPositionType(),
                unitTable.getPlanId(),
                adPlanObject
        );
         handleBinlogEvent(
                 DataTable.of(AdUnitIndex.class),
                 unitObject.getUnitId(),
                 unitObject,
                 type
         );
    }

    public static void handleLevel3(CreativeUnitTable creativeUnitTable,OpType type){
         //先判断是否是更新操作，该索引无更新操作
        if (type == OpType.UPDATE){
            log.error("CreativeUnitIndex not support update");
            return;
        }
        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(creativeUnitTable.getAdId());
        if (null == unitObject || null == creativeObject){
            log.error("AdCreativeUnitTable index error:{}", JSON.toJSONString(creativeUnitTable));
            return;
        }
        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
                creativeUnitTable.getAdId(),
                creativeUnitTable.getUnitId()
        );
        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(
                        creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()
                ),
                creativeUnitObject,
                type
        );
     }

     public static void handleLevel4(UnitDistrictTable unitDistrictTable, OpType type){
         if (type == OpType.UPDATE){
             log.error("district index can not support update");
             return;
         }
         AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitDistrictTable.getUnitId());
         if (unitObject == null){
             log.error("UnitDistrictTable index error:{}",unitDistrictTable.getUnitId());
             return;
         }
         String key = CommonUtils.stringConcat(
                 unitDistrictTable.getProvince(),
                 unitDistrictTable.getCity()
         );
         Set<Long> value = new HashSet<>(Collections.singleton(unitDistrictTable.getUnitId()));
         handleBinlogEvent(
                 DataTable.of(UnitDistrictIndex.class),
                 key,value,
                 type
         );
     }

    public static void handleLevel4(UnitItTable unitItTable, OpType type){
        if (type == OpType.UPDATE){
            log.error("it index can not support update");
            return;
        }
        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitItTable.getUnitId());
        if (unitObject == null){
            log.error("UnitItTable index error:{}",unitItTable.getUnitId());
            return;
        }
        Set<Long> value = new HashSet<>(Collections.singleton(unitItTable.getUnitId()));
        handleBinlogEvent(
                DataTable.of(UnitDistrictIndex.class),
                unitItTable.getItTag(),
                value,
                type
        );
    }

    public static void handleLevel4(UnitKeywordTable unitKeywordTable, OpType type){
        if (type == OpType.UPDATE){
            log.error("keyword index can not support update");
            return;
        }
        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(unitKeywordTable.getUnitId());
        if (unitObject == null){
            log.error("UnitKeywordTable index error:{}",unitKeywordTable.getUnitId());
            return;
        }
        Set<Long> value = new HashSet<>(Collections.singleton(unitKeywordTable.getUnitId()));
        handleBinlogEvent(
                DataTable.of(UnitDistrictIndex.class),
                unitKeywordTable.getKeyword(),
                value,
                type
        );
    }

     private static <K,V> void handleBinlogEvent(IndexAware<K,V> index, K key, V value, OpType type){
         switch (type){
             case ADD:
                 index.add(key, value);
                 break;
             case UPDATE:
                 index.update(key, value);
                 break;
             case DELETE:
                 index.delete(key, value);
                 break;
             default:
                 break;
         }
     }
}
