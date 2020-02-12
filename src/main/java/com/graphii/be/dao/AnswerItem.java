package com.graphii.be.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnswerItem {

    @Id
    @GeneratedValue
    private Integer id;
    private int itemId;
    private int val;
}
