package com.graphii.be.repository;

import com.graphii.be.dao.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    List<Theme> findFirst2ByOrderByCreatedDateDesc();
}
