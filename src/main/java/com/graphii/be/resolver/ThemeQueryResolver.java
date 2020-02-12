package com.graphii.be.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphii.be.repository.ThemeRepository;
import com.graphii.be.resolver.payload.Theme;
import com.graphii.be.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ThemeQueryResolver implements GraphQLQueryResolver {

    private final ThemeService themeService;

    public Theme getTheme(Integer id){

        return themeService.getTheme(id);
    }
}
