package cqrssandboxapp.articlecommandgeneric.commands;

public class ArticleCreateCommand {

    private final int articleNumber;
    private final String description;

    public ArticleCreateCommand(int articleNumber, String description){

        this.articleNumber = articleNumber;
        this.description = description;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getDescription() {
        return description;
    }
}