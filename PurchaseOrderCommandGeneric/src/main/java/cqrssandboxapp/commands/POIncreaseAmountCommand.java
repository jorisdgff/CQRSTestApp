package cqrssandboxapp.commands;

public class POIncreaseAmountCommand {

    private int orderNumber;
    private int amountToIncrease;

    public POIncreaseAmountCommand(int orderNumber, int amountToIncrease){

        this.orderNumber = orderNumber;
        this.amountToIncrease = amountToIncrease;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getAmountToIncrease() {
        return amountToIncrease;
    }
}
