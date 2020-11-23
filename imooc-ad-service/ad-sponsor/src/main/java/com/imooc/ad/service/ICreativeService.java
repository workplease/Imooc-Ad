package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.CreativeRequest;
import com.imooc.ad.vo.CreativeResponse;

public interface ICreativeService  {

    /**
     * 创建一个新创意
     * @param request
     * @return
     * @throws AdException
     */
    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
