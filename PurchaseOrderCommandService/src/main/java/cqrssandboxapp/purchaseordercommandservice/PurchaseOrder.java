package cqrssandboxapp.purchaseordercommandservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cqrssandboxapp.commands.POIncreaseAmountCommand;
import cqrssandboxapp.commands.POCreateCommand;
import cqrssandboxapp.events.POAmountIncreasedEvent;
import cqrssandboxapp.events.POCreatedEvent;
import cqrssandboxapp.generic.framework.AbstractAggregate;
import cqrssandboxapp.generic.framework.AbstractEvent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseOrder extends AbstractAggregate {

    private int purchaseOrderNumber;
    private int articleNumber;
    private int amount;

    public int getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public int getAmount() {
        return amount;
    }

    public String initialize(POCreateCommand command){

        this.purchaseOrderNumber = command.getOrderNumber();
        this.articleNumber = command.getArticleNumber();
        this.amount = command.getAmount();
        return emitEvent(new POCreatedEvent(command.getOrderNumber(), command.getArticleNumber(), command.getAmount()));
    }

    public void changeAmount(POIncreaseAmountCommand command){

        this.amount = this.amount + command.getAmountToIncrease();
        emitEvent(new POAmountIncreasedEvent(purchaseOrderNumber, command.getAmountToIncrease(), this.amount));
    }

    public void projectEvent(AbstractEvent abstractEvent){

        if(abstractEvent instanceof POCreatedEvent){

            POCreatedEvent event = (POCreatedEvent) abstractEvent;
            this.purchaseOrderNumber = event.getPurchaseOrderNumber();
            this.articleNumber = event.getArticleNumber();
            this.amount = event.getAmount();
        }else if(abstractEvent instanceof POAmountIncreasedEvent){

            POAmountIncreasedEvent event = (POAmountIncreasedEvent) abstractEvent;
            this.amount = this.amount + event.getIncreasedAmount();
        }
    }
}
