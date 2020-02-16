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
public class ThemeInput {

    private String title;
    private String creator;
    private String imageUrl;
    private List<ItemInput> itemInputs;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemInput {
        private String name;
    }
}
