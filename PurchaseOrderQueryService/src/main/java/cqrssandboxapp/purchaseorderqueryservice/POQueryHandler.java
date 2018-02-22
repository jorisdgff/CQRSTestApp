package cqrssandboxapp.purchaseorderqueryservice;

import cqrssandboxapp.generic.domain.PurchaseOrder;
import cqrssandboxapp.purchaseorderqueryservice.queries.PurchaseOrderFindQuery;

public class POQueryHandler {

    private static POQueryHandler instance;

    private POQueryHandler(){


    }

    public PurchaseOrder execute(PurchaseOrderFindQuery query){

        return PODBManager.getInstance().find(query.getPurchaseOrderNumber());
    }

    public static POQueryHandler getInstance() {

        if(instance == null){

            instance = new POQueryHandler();
        }

        return instance;
    }
}
