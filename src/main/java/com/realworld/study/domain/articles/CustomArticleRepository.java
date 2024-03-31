package com.realworld.study.domain.articles;

import com.realworld.study.web.articles.dto.ArticlesGetRequestDto;

import java.util.List;

public interface CustomArticleRepository {
    public List<Articles> findArticlesByParam(ArticlesGetRequestDto requestDto);

}
