package com.graphii.be.service;

import com.graphii.be.dao.Item;
import com.graphii.be.dao.Theme;
import com.graphii.be.repository.ItemRepository;
import com.graphii.be.repository.ThemeRepository;
import com.graphii.be.resolver.payload.ThemeInput;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.internal.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    private final ItemRepository itemRepository;

    public com.graphii.be.resolver.payload.Theme getTheme(Integer id){
        Optional<Theme> theme = themeRepository.findById(id);
        List<Item> items = itemRepository.findAllByThemeId(theme.get().getId());

        List<com.graphii.be.resolver.payload.Theme.Item> themeItems =
                items.stream().map(e-> com.graphii.be.resolver.payload.Theme.Item.builder()
                        .id(e.getId()).name(e.getName()).themeId(e.getThemeId()).build()).collect(Collectors.toList());

        System.out.println("Items dayo: "+themeItems);

        return com.graphii.be.resolver.payload.Theme
                .builder()
                .id(theme.get().getId())
                .title(theme.get().getTitle())
                .creator(theme.get().getCreator())
                .items(themeItems)
                .build();
    }

    @Transactional
    public Integer insertTheme(ThemeInput themeInput){
        Theme theme = Theme.builder()
                .title(themeInput.getTitle())
                .creator(themeInput.getCreator())
                .build();
        
        Theme themeResult = themeRepository.save(theme);
        Integer themeId = themeResult.getId();

        List<Item> items = themeInput.getItemInputs().stream()
                .map(e->Item.builder().name(e.getName()).themeId(themeId).build())
                .collect(Collectors.toList());

        itemRepository.saveAll(items);

        return themeId;
    }
}
