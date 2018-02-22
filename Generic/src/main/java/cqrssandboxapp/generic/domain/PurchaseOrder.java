package cqrssandboxapp.generic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrder {

    private int purchaseOrderNumber;
    private Article article;
    private int amountOrdered;

    public PurchaseOrder(){


    }

    public PurchaseOrder(int purchaseOrderNumber, Article article, int amountOrdered){

        this.purchaseOrderNumber = purchaseOrderNumber;
        this.article = article;
        this.amountOrdered = amountOrdered;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }

    public Article getArticle() {
        return article;
    }

    public int getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }
}