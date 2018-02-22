package cqrssandboxapp.generic.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Utils {

    public static String parseObjectToJson(Object object){

        try{

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        }catch (JsonProcessingException ex){

            System.err.println(ex);
            return null;
        }
    }

    public static <T> T parseJsonToObject(String json, Class<T> valueType){

        try {

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json,valueType);
        }catch (IOException ex){

            System.err.println(ex);
            return null;
        }
    }
}