package com.graphii.be.resolver.payload.exception;

import graphql.GraphQLError;
import graphql.servlet.GenericGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphQLExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public static GraphQLError handleException(Throwable e){
        return new GenericGraphQLError(e.getMessage());
    }
}
