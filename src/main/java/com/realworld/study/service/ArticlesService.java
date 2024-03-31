package com.realworld.study.service;

import com.realworld.study.domain.articles.Articles;
import com.realworld.study.domain.articles.ArticlesRepository;
import com.realworld.study.domain.tag.Tags;
import com.realworld.study.domain.tag.TagsRepository;
import com.realworld.study.domain.tagMapping.TagMapping;
import com.realworld.study.domain.tagMapping.TagMappingRepository;
import com.realworld.study.web.articles.dto.ArticleResponseDto;
import com.realworld.study.web.articles.dto.ArticleSaveRequestDto;
import com.realworld.study.web.articles.dto.ArticlesGetRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticlesService {
    private final ArticlesRepository articlesRepository;
    private final TagsRepository tagsRepository;
    private final TagMappingRepository tagMappingRepository;

    @Transactional
    public ArticleResponseDto saveArticle(ArticleSaveRequestDto requestDto){
        Articles article = articlesRepository.save(requestDto.toEntity());
        List<TagMapping> tagList = new ArrayList<>();
        for(String saveTag : requestDto.getTagList()){
            Optional<Tags> findTag = tagsRepository.findByTagName(saveTag);
            Tags tag = new Tags();
            tag = findTag.orElseGet(() -> tagsRepository.save(new Tags(saveTag)));

            TagMapping tagMapping = tagMappingRepository.save(new TagMapping(article, tag));
            tagList.add(tagMapping);

        }
        article.addTagMapping(tagList);

        return new ArticleResponseDto(article);
    }

    public ArticleResponseDto findArticleBySlug(String slug) {
        int index = slug.lastIndexOf("-");
        String articleId = slug.substring(index + 1);
        String title = slug.substring(0,index);
        Articles articles = articlesRepository.findByArticleIdAndTitle(Long.parseLong(articleId), title)
                .orElseThrow(() -> new IllegalArgumentException("해당 아티클이 없습니다. slug = " + slug));


        ArticleResponseDto response = new ArticleResponseDto(articles);
        return response;
    }

    public List<ArticleResponseDto> findArticlesByParam(ArticlesGetRequestDto requestDto){
        return articlesRepository.findArticlesByParam(requestDto).stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ArticleResponseDto updateArticle(String slug, ArticleSaveRequestDto requestDto) {
        int index = slug.lastIndexOf("-");
        String articleId = slug.substring(index + 1);
        String title = slug.substring(0,index);
        Articles articles = articlesRepository.findByArticleIdAndTitle(Long.parseLong(articleId), title)
                .orElseThrow(() -> new IllegalArgumentException("해당 아티클이 없습니다. slug = " + slug));

        String updateTitle = Optional.ofNullable(requestDto.getTitle()).orElse(articles.getTitle());
        String updateDescription = Optional.ofNullable(requestDto.getDescription()).orElse(articles.getDescription());
        String updateBody = Optional.ofNullable(requestDto.getBody()).orElse(articles.getBody());

        articles.updateArticle(updateTitle, updateDescription, updateBody);
        return new ArticleResponseDto(articles);
    }

    @Transactional
    public void deleteArticle(String slug) {
        int index = slug.lastIndexOf("-");
        String articleId = slug.substring(index + 1);
        String title = slug.substring(0,index);
        articlesRepository.deleteByArticleIdAndTitle(Long.parseLong(articleId), title);
    }
}
