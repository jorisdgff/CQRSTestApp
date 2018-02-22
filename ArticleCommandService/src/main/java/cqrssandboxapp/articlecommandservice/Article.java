package cqrssandboxapp.articlecommandservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cqrssandboxapp.articlecommandgeneric.commands.ArticleChangeDescriptionCommand;
import cqrssandboxapp.articlecommandgeneric.commands.ArticleCreateCommand;
import cqrssandboxapp.articlecommandgeneric.commands.ArticleOrderCommand;
import cqrssandboxapp.articlecommandgeneric.events.ArticleCreatedEvent;
import cqrssandboxapp.articlecommandgeneric.events.ArticleDescriptionChangedEvent;
import cqrssandboxapp.articlecommandgeneric.events.ArticleInvalidOrderedEvent;
import cqrssandboxapp.articlecommandgeneric.events.ArticleOrderedEvent;
import cqrssandboxapp.generic.framework.AbstractAggregate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article extends AbstractAggregate  {

    private int articleNumber;
    private String description;
    private boolean isHistorical;

    public Article(){

    }

    public Article(int articleNumber, String description, boolean isHistorical){

        this.articleNumber = articleNumber;
        this.description = description;
        this.isHistorical = isHistorical;
    }

    public String handle(ArticleCreateCommand command){

        this.articleNumber = command.getArticleNumber();
        this.description = command.getDescription();
        return emitEvent(new ArticleCreatedEvent(articleNumber, description, isHistorical));
    }

    public void handle(ArticleChangeDescriptionCommand command){

        this.description = command.getNewDescription();
        emitEvent(new ArticleDescriptionChangedEvent(command.getArticleNumber(), command.getNewDescription()));
    }

    public String order(ArticleOrderCommand command){

        if(!this.isHistorical){

            emitEvent(new ArticleOrderedEvent(this.articleNumber, command.getOrderNumber(), this.description));
            return "OK";
        }else{

            emitEvent(new ArticleInvalidOrderedEvent(command.getOrderNumber(), command.getOriginalEventGuid()));
            return "ARTICLEISHISTORICAL";
        }
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHistorical() {
        return isHistorical;
    }
}
