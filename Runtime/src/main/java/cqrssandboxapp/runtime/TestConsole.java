package cqrssandboxapp.runtime;

import cqrssandboxapp.articlecommandgeneric.commands.ArticleCreateCommand;
import cqrssandboxapp.commands.POCreateCommand;

public class TestConsole {

    public static void main(String[] args){

        EventBus.getInstance();

        CommandBus.getInstance().handle(new ArticleCreateCommand(111123456, "Banana"));
        //bus.handle(new ArticleChangeDescriptionCommand(111123456, "Other fruit")   );


        CommandBus.getInstance().createPO(new POCreateCommand(123456, 111123456, 10));
        //CommandBus.getInstance().handle(new POIncreaseAmountCommand(123456, 5));


        //PurchaseOrder po = QueryBus.getInstance().execute(new PurchaseOrderFindQuery(223459));

    }
}