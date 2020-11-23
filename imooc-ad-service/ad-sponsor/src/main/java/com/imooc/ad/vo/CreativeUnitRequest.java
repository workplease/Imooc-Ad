package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitRequest {

    private List<CreativeUnitItem> unitItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreativeUnitItem{

        private Long creativeId;
        private Long unitId;
    }
}
