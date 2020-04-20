package com.graphii.be.service;

import com.graphii.be.dao.Item;
import com.graphii.be.dao.Theme;
import com.graphii.be.repository.AnswerRepository;
import com.graphii.be.repository.ItemRepository;
import com.graphii.be.repository.ThemeRepository;
import com.graphii.be.resolver.payload.ThemeInput;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.internal.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final AnswerRepository answerRepository;

    private final ItemRepository itemRepository;

    public com.graphii.be.resolver.payload.Theme getTheme(Integer id){
        Optional<Theme> theme = themeRepository.findById(id);
        List<Item> items = itemRepository.findAllByThemeId(theme.get().getId());

        List<com.graphii.be.resolver.payload.Theme.Item> themeItems =
                items.stream().map(e-> com.graphii.be.resolver.payload.Theme.Item.builder()
                        .id(e.getId()).name(e.getName()).themeId(e.getThemeId()).build()).collect(Collectors.toList());

        System.out.println("作成日: "+theme.get().getCreatedDate());
        System.out.println("最新2けん: " + themeRepository.findFirst2ByOrderByCreatedDateDesc());
        return com.graphii.be.resolver.payload.Theme
                .builder()
                .id(theme.get().getId())
                .title(theme.get().getTitle())
                .creator(theme.get().getCreator())
                .imageUrl(theme.get().getImageUrl())
                .items(themeItems)
                .answerCount(answerRepository.countByThemeId(theme.get().getId()))
                .build();
    }

    public List<com.graphii.be.resolver.payload.Theme> getThemeList() {
        List<Theme> themeList = themeRepository.findFirst2ByOrderByCreatedDateDesc();

        return themeList.stream()
                .map(e-> com.graphii.be.resolver.payload.Theme.builder()
                        .id(e.getId())
                        .imageUrl(e.getImageUrl())
                        .title(e.getTitle())
                        .creator(e.getCreator())
                        .build()).collect(Collectors.toList());
    }

    @Transactional
    public Integer insertTheme(ThemeInput themeInput){
        Theme theme = Theme.builder()
                .title(themeInput.getTitle())
                .creator(themeInput.getCreator())
                .imageUrl(themeInput.getImageUrl())
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
