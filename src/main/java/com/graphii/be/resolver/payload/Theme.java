package com.graphii.be.resolver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theme {

    private Integer id;
    private String title;
    private String creator;
    private String imageUrl;
    private List<Item> items;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {

        private Integer id;
        private Integer themeId;
        private String name;
    }
}
