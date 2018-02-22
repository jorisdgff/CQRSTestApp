package cqrssandboxapp.events;

import cqrssandboxapp.generic.framework.AbstractEvent;

public abstract class AbstractPOEvent extends AbstractEvent{

    protected final int purchaseOrderNumber;

    protected AbstractPOEvent(int purchaseOrderNumber){

        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public int getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public abstract String getEventType();
}
