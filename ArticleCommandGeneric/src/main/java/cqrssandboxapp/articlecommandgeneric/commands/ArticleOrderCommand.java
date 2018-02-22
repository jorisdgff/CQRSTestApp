package cqrssandboxapp.articlecommandgeneric.commands;

public class ArticleOrderCommand {

    private int articleNumber;
    private int orderNumber;
    private String originalEventGuid;

    public ArticleOrderCommand(int articleNumber, int orderNumber, String originalEventGuid){

        this.articleNumber = articleNumber;
        this.orderNumber = orderNumber;
        this.originalEventGuid = originalEventGuid;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getOriginalEventGuid() {
        return originalEventGuid;
    }
}
