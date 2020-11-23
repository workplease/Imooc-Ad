package com.imooc.ad.search;

import com.imooc.ad.search.vo.SearchRequest;
import com.imooc.ad.search.vo.SearchResponse;

/**
 * 将来会用于广告检索请求
 */
public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);
}
