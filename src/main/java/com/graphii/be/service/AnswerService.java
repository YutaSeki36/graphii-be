package com.graphii.be.service;

import com.graphii.be.dao.Answer;
import com.graphii.be.dao.AnswerItem;
import com.graphii.be.dao.Item;
import com.graphii.be.dao.Theme;
import com.graphii.be.repository.AnswerItemRepository;
import com.graphii.be.repository.AnswerRepository;
import com.graphii.be.repository.ItemRepository;
import com.graphii.be.repository.ThemeRepository;
import com.graphii.be.resolver.payload.AnswerInput;
import com.graphii.be.resolver.payload.AnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerItemRepository answerItemRepository;
    private final ThemeRepository themeRepository;
    private final ItemRepository itemRepository;

    public AnswerResponse getAnswer(Integer id){

        Optional<Answer> answer = answerRepository.findById(id);
        Optional<Theme> theme = themeRepository.findById(answer.get().getThemeId());

        List<Item> items = itemRepository.findAllByThemeId(theme.get().getId());

        List<com.graphii.be.resolver.payload.Theme.Item> themeItems =
                items.stream().map(e-> com.graphii.be.resolver.payload.Theme.Item.builder()
                        .id(e.getId()).name(e.getName()).themeId(e.getThemeId()).build()).collect(Collectors.toList());


        List<AnswerItem> answerItems = answerItemRepository.findAllByAnswerId(id);

        com.graphii.be.resolver.payload.Theme themeResponse = com.graphii.be.resolver.payload.Theme
                .builder()
                .id(theme.get().getId())
                .title(theme.get().getTitle())
                .creator(theme.get().getCreator())
                .imageUrl(theme.get().getImageUrl())
                .items(themeItems)
                .build();

        return AnswerResponse.builder()
                .id(answer.get().getId())
                .answerer(answer.get().getAnswerer())
                .imageUrl(answer.get().getImageUrl())
                .answerItems(answerItems
                        .stream()
                        .map(e->AnswerResponse.AnswerItem
                                .builder()
                                .id(e.getId())
                                .itemId(e.getItemId())
                                .val(e.getVal())
                                .build())
                        .collect(Collectors.toList()))
                .theme(themeResponse)
                .build();
    }

    @Transactional
    public Integer insertAnswer(AnswerInput answerInput){

        Answer answer = Answer.builder()
                .answerer(answerInput.getAnswerer())
                .themeId(answerInput.getThemeId())
                .imageUrl(answerInput.getImageUrl())
                .build();

        Answer result = answerRepository.save(answer);

        List<AnswerItem> answerItem =
                answerInput.getAnswerItemInputs()
                        .stream()
                        .map(e->AnswerItem.builder()
                                .answerId(result.getId())
                                .itemId(e.getItemId())
                                .val(e.getVal())
                                .build())
                        .collect(Collectors.toList());

        List<AnswerItem> resultItem = answerItemRepository.saveAll(answerItem);

        System.out.println("結果だよ: "+ resultItem +"  "+result);
        return result.getId();
    }
}
