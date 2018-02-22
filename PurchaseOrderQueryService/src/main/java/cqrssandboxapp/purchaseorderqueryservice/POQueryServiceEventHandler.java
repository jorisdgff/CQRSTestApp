package cqrssandboxapp.purchaseorderqueryservice;

import cqrssandboxapp.events.POAmountIncreasedEvent;
import cqrssandboxapp.events.POCreatedEvent;
import cqrssandboxapp.articlecommandgeneric.events.ArticleOrderedEvent;
import cqrssandboxapp.generic.domain.Article;
import cqrssandboxapp.generic.domain.PurchaseOrder;
import cqrssandboxapp.generic.framework.AbstractEvent;
import cqrssandboxapp.generic.framework.AbstractEventHandler;

import java.util.concurrent.CompletableFuture;

public class POQueryServiceEventHandler extends AbstractEventHandler {

    private static POQueryServiceEventHandler instance;

    private POQueryServiceEventHandler(){


    }

    public static POQueryServiceEventHandler getInstance() {

        if(instance == null){

            instance = new POQueryServiceEventHandler();
        }

        return instance;
    }

    @Override
    public String handleEvent(AbstractEvent event) {

        if(event instanceof POCreatedEvent){

            POCreatedEvent createdEvent = (POCreatedEvent) event;
            PurchaseOrder po = new PurchaseOrder(createdEvent.getPurchaseOrderNumber(), null, createdEvent.getAmount());
            PODBManager.getInstance().registerPO(po);

        }else if(event instanceof ArticleOrderedEvent){

            ArticleOrderedEvent orderedEvent = (ArticleOrderedEvent) event;
            Article article = new Article(orderedEvent.getArticleNumber(), orderedEvent.getDescription());
            PODBManager.getInstance().addArticleToPO(orderedEvent.getOrderNumber(), article);
        }else if(event instanceof POAmountIncreasedEvent){

            POAmountIncreasedEvent poAmountIncreasedEvent = (POAmountIncreasedEvent) event;
            PODBManager.getInstance().setPOAmount(poAmountIncreasedEvent.getPurchaseOrderNumber(), poAmountIncreasedEvent.getNewAmount());
        }

        return "OK";
    }
}
