package cqrssandboxapp.runtime;

import cqrssandboxapp.generic.domain.PurchaseOrder;
import cqrssandboxapp.purchaseorderqueryservice.POQueryHandler;
import cqrssandboxapp.purchaseorderqueryservice.queries.PurchaseOrderFindQuery;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryBus {

    private static QueryBus instance;

    private QueryBus(){


    }

    public PurchaseOrder execute(PurchaseOrderFindQuery query){

        return POQueryHandler.getInstance().execute(query);
    }

    public static QueryBus getInstance() {

        if(instance == null){

            instance = new QueryBus();
        }

        return instance;
    }
}
