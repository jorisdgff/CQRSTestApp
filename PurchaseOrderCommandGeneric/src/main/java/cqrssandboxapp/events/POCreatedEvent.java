package cqrssandboxapp.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class POCreatedEvent extends AbstractPOEvent {

    private int articleNumber;
    private int amount;

    public POCreatedEvent(){

        super(0);
    }

    public POCreatedEvent(int orderNumber, int articleNumber, int amount){

        super(orderNumber);
        this.articleNumber = articleNumber;
        this.amount = amount;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String getEventType() {

        return "CREATED";
    }
}