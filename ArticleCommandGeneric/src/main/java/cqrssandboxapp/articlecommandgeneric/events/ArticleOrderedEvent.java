package cqrssandboxapp.articlecommandgeneric.events;

import cqrssandboxapp.generic.framework.AbstractEvent;

public class ArticleOrderedEvent extends AbstractEvent{

    private final int articleNumber;
    private final int orderNumber;
    private final String description;

    public ArticleOrderedEvent(int articleNumber, int orderNumber, String description){

        this.articleNumber = articleNumber;
        this.orderNumber = orderNumber;
        this.description = description;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getDescription() {
        return description;
    }
}