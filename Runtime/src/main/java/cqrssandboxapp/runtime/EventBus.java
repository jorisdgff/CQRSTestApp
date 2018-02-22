package cqrssandboxapp.runtime;

import cqrssandboxapp.articlecommandservice.ArticleCommandHandler;
import cqrssandboxapp.articlecommandservice.ArticleRepository;
import cqrssandboxapp.purchaseordercommandservice.POCommandHandler;
import cqrssandboxapp.purchaseordercommandservice.PORepository;
import cqrssandboxapp.purchaseorderqueryservice.POQueryServiceEventHandler;

public class EventBus {

    private static EventBus instance;

    private EventBus(){

        PORepository.getInstance().addListener(POQueryServiceEventHandler.getInstance());
        PORepository.getInstance().addListener(ArticleCommandHandler.getInstance());

        ArticleRepository.getInstance().addListener(POCommandHandler.getInstance());
        ArticleRepository.getInstance().addListener(POQueryServiceEventHandler.getInstance());
    }

    public static EventBus getInstance() {

        if(instance == null){

            instance = new EventBus();
        }

        return instance;
    }
}