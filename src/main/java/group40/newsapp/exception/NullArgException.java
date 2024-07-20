package group40.newsapp.exception;

public class NullArgException extends RuntimeException{
    public NullArgException(String field){
        super("{\"fieldName\" : \""+field+"\", \"message\" : \"must not be null\"}");
    }
}