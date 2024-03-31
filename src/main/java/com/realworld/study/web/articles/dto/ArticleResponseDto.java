package com.realworld.study.web.articles.dto;

import com.realworld.study.domain.articles.Articles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleResponseDto {
    private String slug;
    private String title;
    private String description;
    private String body;
    @Setter
    private List<String> tagList;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ArticleResponseDto(Articles entity) {
        this.slug = entity.getSlug() + "-" + entity.getArticleId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.body = entity.getBody();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getCreatedAt();
        this.tagList = entity.getTags();
    }

}
