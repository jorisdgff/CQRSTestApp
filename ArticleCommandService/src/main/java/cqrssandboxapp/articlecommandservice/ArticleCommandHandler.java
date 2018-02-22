package cqrssandboxapp.articlecommandservice;

import cqrssandboxapp.events.POCreatedEvent;
import cqrssandboxapp.articlecommandgeneric.commands.*;
import cqrssandboxapp.generic.framework.AbstractEvent;
import cqrssandboxapp.generic.framework.AbstractEventHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ArticleCommandHandler extends AbstractEventHandler {

    private static ArticleCommandHandler instance;

    private ArticleCommandHandler(){

    }

    public String handle(ArticleCreateCommand command){

        Article article = ArticleRepository.getInstance().getArticle(command.getArticleNumber());
        return article.handle(command);
    }

    public void handle(ArticleChangeDescriptionCommand command){

        Article article = ArticleRepository.getInstance().getArticle(command.getArticleNumber());
        article.handle(command);
    }

    public String handle(ArticleOrderCommand command){

        Article article = ArticleRepository.getInstance().getArticle(command.getArticleNumber());
        return article.order(command);
    }

    public static ArticleCommandHandler getInstance() {

        if(instance == null){

            instance = new ArticleCommandHandler();
        }

        return instance;
    }

    @Override
    public String handleEvent(AbstractEvent event) {

        String returnValue = "OK";

        if(event instanceof POCreatedEvent){

            POCreatedEvent createdEvent = (POCreatedEvent) event;
            ArticleOrderCommand command = new ArticleOrderCommand(createdEvent.getArticleNumber(), createdEvent.getPurchaseOrderNumber(), createdEvent.getGuid());
            returnValue = ArticleCommandHandler.getInstance().handle(command);
        }

        return returnValue;
    }
}