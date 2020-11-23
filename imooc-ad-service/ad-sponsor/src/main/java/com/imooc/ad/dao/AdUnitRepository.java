package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUnitRepository extends JpaRepository<AdUnit,Long> {

    /**
     * 根据推广计划 ID 和推广单元名称查询推广单元
     * @param planId
     * @param userName
     * @return
     */
    AdUnit findByPlanIdAndUnitName(Long planId,String userName);

    /**
     * 根据推广单元状态查询推广单元组
     * @param unitStatus
     * @return
     */
    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
