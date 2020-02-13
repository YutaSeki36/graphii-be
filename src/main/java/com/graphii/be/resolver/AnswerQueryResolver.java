package com.graphii.be.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphii.be.resolver.payload.AnswerResponse;
import com.graphii.be.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerQueryResolver implements GraphQLQueryResolver {

    private final AnswerService answerService;

    public AnswerResponse getAnswer(Integer id){
        return answerService.getAnswer(id);
    }
}
