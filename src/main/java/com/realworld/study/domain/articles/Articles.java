package com.realworld.study.domain.articles;

import com.realworld.study.domain.BaseTimeEntity;
import com.realworld.study.domain.tagMapping.TagMapping;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Articles extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    private Long articleId;

    private String title;

    private String description;

    private String body;

    private String slug;

    @OneToMany(mappedBy="articles", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagMapping> tagMappings;

//    @OneToMany(mappedBy = "articleTag")
//    private List<ArticleTag> articleTagList = new ArrayList<ArticleTag>();

    @Builder
    public Articles(String title, String description, String body) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.slug = title.replaceAll("[!?@$^,. ]", "-");
    }

    public void updateArticle(String title, String description, String body) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.slug = title.replaceAll("[!?@$^,. ]", "-");
    }

    public void addTagMapping(List<TagMapping> tagMappings) {
        this.tagMappings = tagMappings;
    }

    public List<String> getTags(){
        List<String> tagList = new ArrayList<>();
        for(TagMapping tagMapping : this.tagMappings) {
            tagList.add(tagMapping.getTags().getTagName());
        }
        return tagList;
    }
}
