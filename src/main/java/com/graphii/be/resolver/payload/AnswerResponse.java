package com.graphii.be.resolver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    private Integer id;
    private String answerer;
    private String imageUrl;
    private List<AnswerItem> answerItems;
    private Theme theme;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerItem {

        private Integer id;
        private Integer itemId;
        private Integer val;
    }
}
