package cqrssandboxapp.generic.framework;

import java.util.UUID;

public abstract class AbstractEvent {

    protected final String guid;

    public AbstractEvent(){

        guid = UUID.randomUUID().toString();
    }

    public String getGuid() {
        return guid;
    }
}
