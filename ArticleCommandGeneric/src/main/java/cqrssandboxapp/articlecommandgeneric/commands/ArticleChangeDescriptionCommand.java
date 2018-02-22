package cqrssandboxapp.articlecommandgeneric.commands;

public class ArticleChangeDescriptionCommand {

    private final int articleNumber;
    private final String newDescription;

    public ArticleChangeDescriptionCommand(int articleNumber, String newDescription){

        this.articleNumber = articleNumber;
        this.newDescription = newDescription;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getNewDescription() {
        return newDescription;
    }
}