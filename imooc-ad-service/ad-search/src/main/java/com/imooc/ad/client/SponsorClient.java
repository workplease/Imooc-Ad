package com.imooc.ad.client;

import com.imooc.ad.client.vo.AdPlan;
import com.imooc.ad.client.vo.AdPlanGetRequest;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//指定调用的微服务
@FeignClient(value = "eureka-client-ad-sponsor",fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    //调用的服务接口
    @RequestMapping(value = "/ad-sponsor/get/adPlan",method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request);
}
