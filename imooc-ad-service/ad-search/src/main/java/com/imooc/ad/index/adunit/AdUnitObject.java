package com.imooc.ad.index.adunit;

import com.imooc.ad.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject newObject){
        if (null != newObject.getUnitId()){
            this.unitId = newObject.getUnitId();
        }
        if (null != newObject.getUnitStatus()){
            this.unitStatus = newObject.getUnitStatus();
        }
        if (null != newObject.getPositionType()){
            this.positionType = newObject.getPositionType();
        }
        if (null != newObject.getPlanId()){
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getAdPlanObject()){
            this.adPlanObject = newObject.getAdPlanObject();
        }
    }

    private static boolean isKaiping(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.KAIPING) > 0;
    }


    private static boolean isTiePian(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN) > 0;
    }

    private static boolean isTiePianMiddle(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPLAN_MIDDLE) > 0;
    }

    private static boolean isTiePianPause(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPLAN_PAUSE) > 0;
    }

    private static boolean isTiePianPost(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPLAN_POST) > 0;
    }

    public static boolean isAdSlotTypeOK(int adSlotType,int positionType){
        switch (adSlotType){
            case AdUnitConstants.POSITION_TYPE.KAIPING:
                return isKaiping(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN:
                return isTiePian(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPLAN_MIDDLE:
                return isTiePianMiddle(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPLAN_PAUSE:
                return isTiePianPause(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPLAN_POST:
                return isTiePianPost(positionType);
            default:
                return false;
        }
    }
}
