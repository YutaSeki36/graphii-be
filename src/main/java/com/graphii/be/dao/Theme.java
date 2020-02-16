package com.graphii.be.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theme {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String imageUrl;
    private String creator;
}
