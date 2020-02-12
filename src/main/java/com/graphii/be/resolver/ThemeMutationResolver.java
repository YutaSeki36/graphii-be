package com.graphii.be.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphii.be.resolver.payload.ThemeInput;
import com.graphii.be.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThemeMutationResolver implements GraphQLMutationResolver {

    private final ThemeService themeService;

    public Integer insertTheme(ThemeInput input){
        return themeService.insertTheme(input);
    }
}
