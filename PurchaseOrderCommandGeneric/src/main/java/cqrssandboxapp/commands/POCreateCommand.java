package cqrssandboxapp.commands;

public class POCreateCommand {

    private final int orderNumber;
    private final int articleNumber;
    private final int amount;

    public POCreateCommand(int orderNumber, int articleNumber, int amount){

        this.orderNumber = orderNumber;
        this.articleNumber = articleNumber;
        this.amount = amount;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public int getAmount() {
        return amount;
    }
}
