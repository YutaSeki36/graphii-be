package com.graphii.be.repository;

import com.graphii.be.dao.AnswerItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerItemRepository extends JpaRepository<AnswerItem, Integer> {

    List<AnswerItem> findAllByAnswerId(Integer answerId);
}
