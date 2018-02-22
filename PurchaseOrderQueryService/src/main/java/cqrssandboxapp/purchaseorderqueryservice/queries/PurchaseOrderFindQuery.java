package cqrssandboxapp.purchaseorderqueryservice.queries;

public class PurchaseOrderFindQuery {

    private int purchaseOrderNumber;

    public PurchaseOrderFindQuery(int purchaseOrderNumber){

        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public int getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }
}
