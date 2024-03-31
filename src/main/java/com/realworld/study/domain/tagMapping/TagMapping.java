package com.realworld.study.domain.tagMapping;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.realworld.study.domain.articles.Articles;
import com.realworld.study.domain.tag.Tags;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
public class TagMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagMappingId;

//    private Long articleId;

    @JsonIgnore
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Articles articles;

    @JsonIgnore
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id")
    private Tags tags;

    public TagMapping(Articles articles, Tags tag) {
        this.articles = articles;
        this.tags = tag;
    }


//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "ARTICLE_ID")
//    private Articles article;
//
//
//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "TAG_ID")
//    private Tags tag;
//
//    public ArticleTag(Articles article, Tags tag){
//        this.article = article;
//        this.tag = tag;
//    }

}
