package cqrssandboxapp.articlecommandgeneric.commands;

public class ArticleMakeHistoricCommand {

    private final int articleNumber;

    public ArticleMakeHistoricCommand(int articleNumber){

        this.articleNumber = articleNumber;
    }

    public int getArticleNumber() {
        return articleNumber;
    }
}