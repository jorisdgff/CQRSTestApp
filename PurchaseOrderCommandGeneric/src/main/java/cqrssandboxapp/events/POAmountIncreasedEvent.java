package cqrssandboxapp.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class POAmountIncreasedEvent extends AbstractPOEvent {

    private int increasedAmount;
    private int newAmount;

    public POAmountIncreasedEvent(){

        super(0);
    }

    public POAmountIncreasedEvent(int orderNumber, int increasedAmount, int newAmount){

        super(orderNumber);
        this.increasedAmount = increasedAmount;
        this.newAmount = newAmount;
    }

    public int getIncreasedAmount() {
        return increasedAmount;
    }

    public int getNewAmount() {
        return newAmount;
    }



    @Override
    public String getEventType() {

        return "AMOUNTCHANGED";
    }
}