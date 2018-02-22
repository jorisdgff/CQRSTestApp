package cqrssandboxapp.generic.framework;

public abstract class AbstractAggregate {

    private AbstractRepository listener;

    public void setListener(AbstractRepository listener){

        this.listener = listener;
    }

    protected String emitEvent(AbstractEvent event){

        return listener.receiveEvent(event);
    }
}