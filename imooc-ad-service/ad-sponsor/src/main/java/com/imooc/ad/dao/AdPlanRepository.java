package com.imooc.ad.dao;

import com.imooc.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdPlanRepository extends JpaRepository<AdPlan,Long> {

    /**
     * 根据 ID 和用户 ID 查询推广计划
     * @param id
     * @param userId
     * @return
     */
    AdPlan findByIdAndUserId(Long id,Long userId);

    /**
     * 根据 ID 和用户 ID 查询推广计划组
     * @param ids
     * @param userId
     * @return
     */
    List<AdPlan> findAllByIdAndUserId(List<Long> ids,Long userId);

    /**
     * 根据用户 ID 和推广计划名查询推广计划
     * @param userId
     * @param planName
     * @return
     */
    AdPlan findByUserIdAndPlanName(Long userId,String planName);

    /**
     * 根据推广计划状态查询推广计划组
     * @param status
     * @return
     */
    List<AdPlan> findAllByPlanStatus(Integer status);
}
