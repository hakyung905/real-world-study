package com.realworld.study.web.articles;

import com.realworld.study.service.ArticlesService;
import com.realworld.study.web.articles.dto.ArticleResponseDto;
import com.realworld.study.web.articles.dto.ArticleSaveRequestDto;
import com.realworld.study.web.articles.dto.ArticlesGetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    @Autowired
    private final ArticlesService articlesService;

    @PostMapping("")
    public ArticleResponseDto saveArticle(@RequestBody ArticleSaveRequestDto requestDto) {
        return articlesService.saveArticle(requestDto);
    }

    @GetMapping("/{slug}")
    public ArticleResponseDto getArticle(@PathVariable String slug) {
        return articlesService.findArticleBySlug(slug);
    }

    @GetMapping("")
    public List<ArticleResponseDto> getArticles(
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "favorited", required = false) String favorited,
            @RequestParam(name = "limit", defaultValue = "20") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset
    ) {
        ArticlesGetRequestDto requestDto = new ArticlesGetRequestDto().builder()
                .tag(tag)
                .author(author)
                .favorited(favorited)
                .limit(limit)
                .offset(offset)
                .build();
        return articlesService.findArticlesByParam(requestDto);
    }

    @PutMapping("/{slug}")
    public ArticleResponseDto updateArticle(@PathVariable String slug,
                                             @RequestBody ArticleSaveRequestDto requestDto) {
        return articlesService.updateArticle(slug, requestDto);
    }

    @DeleteMapping("/{slug}")
    public void deleteArticle(@PathVariable String slug) {
        articlesService.deleteArticle(slug);
    }
}
