package com.graphii.be.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphii.be.resolver.payload.AnswerInput;
import com.graphii.be.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerMutationResolver implements GraphQLMutationResolver {

    private final AnswerService answerService;

    public Integer insertAnswer(AnswerInput answerInput){
        return answerService.insertAnswer(answerInput);
    }
}
