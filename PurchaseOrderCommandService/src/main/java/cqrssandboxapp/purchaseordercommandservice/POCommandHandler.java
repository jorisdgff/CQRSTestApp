package cqrssandboxapp.purchaseordercommandservice;

import cqrssandboxapp.commands.*;
import cqrssandboxapp.articlecommandgeneric.events.ArticleDescriptionChangedEvent;
import cqrssandboxapp.articlecommandgeneric.events.ArticleInvalidOrderedEvent;
import cqrssandboxapp.articlecommandgeneric.events.ArticleOrderedEvent;
import cqrssandboxapp.generic.framework.AbstractEvent;
import cqrssandboxapp.generic.framework.AbstractEventHandler;

import java.util.concurrent.CompletableFuture;

public class POCommandHandler extends AbstractEventHandler {

    private static POCommandHandler instance;
    private PORepository repo;

    private POCommandHandler(){

        repo = PORepository.getInstance();
    }

    public static POCommandHandler getInstance() {

        if(instance == null){

            instance = new POCommandHandler();
        }

        return instance;
    }

    public String handle(POCreateCommand command){

        PurchaseOrder po = repo.getPurchaseOrder(command.getOrderNumber());
        return po.initialize(command);
    }

    public void handle(POIncreaseAmountCommand command){

        PurchaseOrder purchaseOrder = repo.getPurchaseOrder(command.getOrderNumber());
        purchaseOrder.changeAmount(command);
    }

    @Override
    public String handleEvent(AbstractEvent ae) {

        if(ae instanceof ArticleOrderedEvent){


        }else if(ae instanceof ArticleInvalidOrderedEvent){

            PORepository.getInstance().handleEvent(ae);
        }else if(ae instanceof ArticleDescriptionChangedEvent){


        }

        return "OK";
    }
}