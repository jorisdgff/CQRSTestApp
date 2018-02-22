package cqrssandboxapp.articlecommandgeneric.events;

import cqrssandboxapp.generic.framework.AbstractEvent;

public class ArticleCreatedEvent extends AbstractEvent {

    private final int articleNumber;
    private final String description;
    private final boolean isHistorical;

    public ArticleCreatedEvent(int articleNumber, String description, boolean isHistorical){

        this.articleNumber = articleNumber;
        this.description = description;
        this.isHistorical = isHistorical;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHistorical() {
        return isHistorical;
    }
}