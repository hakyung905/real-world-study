package com.realworld.study.domain.articles;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realworld.study.domain.tag.QTags;
import com.realworld.study.domain.tagMapping.QTagMapping;
import com.realworld.study.web.articles.dto.ArticlesGetRequestDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class CustomArticleRepositoryImpl implements CustomArticleRepository {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<Articles> findArticlesByParam(ArticlesGetRequestDto requestDto) {
        QArticles articles = QArticles.articles;
        return queryFactory.selectFrom(articles)
                .where(
                        tagLike(articles, requestDto.getTag())
                )
                .limit(requestDto.getLimit())
                .offset(requestDto.getOffset())
                .fetch();
    }

    private BooleanExpression tagLike(QArticles articles, String tag) {
        QTags tags = QTags.tags;
        QTagMapping mapping = QTagMapping.tagMapping;
        if(hasText(tag)){
            List<String> tagNames = queryFactory.select(tags.tagName)
                    .from(tags)
                    .where(tags.tagName.eq(tag))
                    .fetch();
            List<Long> mappings = queryFactory.select(mapping.articles.articleId)
                    .from(mapping)
                    .where(mapping.tags.tagName.in(tagNames))
                    .fetch();
            return articles.articleId.in(mappings);
        } else {
            return null;
        }
    }

//    private BooleanExpression authorEq(QArticles articles, String author) {
//        return hasText(author) ?
//    }

}
