package cqrssandboxapp.articlecommandgeneric.events;

import cqrssandboxapp.generic.framework.AbstractEvent;

public class ArticleDescriptionChangedEvent extends AbstractEvent{

    private final int articleNumber;
    private final String newDescription;

    public ArticleDescriptionChangedEvent(int articleNumber, String newDescription){

        this.articleNumber = articleNumber;
        this.newDescription = newDescription;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getNewDescription() {
        return newDescription;
    }
}