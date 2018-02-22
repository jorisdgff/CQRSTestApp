package cqrssandboxapp.runtime;

import cqrssandboxapp.commands.*;
import cqrssandboxapp.articlecommandgeneric.commands.ArticleChangeDescriptionCommand;
import cqrssandboxapp.articlecommandgeneric.commands.ArticleCreateCommand;
import cqrssandboxapp.articlecommandservice.ArticleCommandHandler;
import cqrssandboxapp.purchaseordercommandservice.POCommandHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commandbus")
public class CommandBus {

    private static CommandBus instance;

    private CommandBus(){


    }

    public static CommandBus getInstance() {

        if(instance == null){

            instance = new CommandBus();
        }

        return instance;
    }

    public String handle(ArticleCreateCommand command){

        return ArticleCommandHandler.getInstance().handle(command);
    }

    public String handle(ArticleChangeDescriptionCommand command){

        ArticleCommandHandler.getInstance().handle(command);
        return "OK";
    }

    @RequestMapping(value = "/purchaseOrder/", method = RequestMethod.POST)
    public String createPO(@RequestBody() POCreateCommand command){

        return POCommandHandler.getInstance().handle(command);
    }

    public String handle(POIncreaseAmountCommand command){

        POCommandHandler.getInstance().handle(command);
        return "OK";
    }
}