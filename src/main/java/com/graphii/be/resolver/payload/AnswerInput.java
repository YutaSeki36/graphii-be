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
public class AnswerInput {

    private Integer themeId;
    private String answerer;
    private String imageUrl;
    private List<AnswerItemInput> answerItemInputs;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerItemInput {

        private Integer itemId;
        private Integer val;
    }
}
