package cqrssandboxapp.articlecommandgeneric.events;

import cqrssandboxapp.generic.framework.AbstractEvent;

public class ArticleInvalidOrderedEvent extends AbstractEvent{

    private int purchaseOrderNumber;
    private String originalEventGuid;

    public ArticleInvalidOrderedEvent(int purchaseOrderNumber, String originalEventGuid){

        this.purchaseOrderNumber = purchaseOrderNumber;
        this.originalEventGuid = originalEventGuid;
    }

    public int getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public String getOriginalEventGuid() {
        return originalEventGuid;
    }
}