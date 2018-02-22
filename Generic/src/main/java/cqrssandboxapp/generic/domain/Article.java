package cqrssandboxapp.generic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {

    private int articleNumber;
    private String description;

    public Article(){


    }

    public Article(int articleNumber, String description){

        this.articleNumber = articleNumber;
        this.description = description;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getDescription() {
        return description;
    }
}