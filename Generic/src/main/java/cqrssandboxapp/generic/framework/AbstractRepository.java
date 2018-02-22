package cqrssandboxapp.generic.framework;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository {

    private List<AbstractEventHandler> eventHandlers;

    public AbstractRepository(){

        eventHandlers = new ArrayList<AbstractEventHandler>();
    }

    public void addListener(AbstractEventHandler handler){

        eventHandlers.add(handler);
    }

    public String receiveEvent(AbstractEvent event){

        handleEvent(event);

        String result = "OK";

        for (AbstractEventHandler handler: eventHandlers) {

            result = handler.handleEvent(event);

            if(!result.equals("OK")){

                break;
            }

        }

        return result;
    }

    public abstract void handleEvent(AbstractEvent event);
}