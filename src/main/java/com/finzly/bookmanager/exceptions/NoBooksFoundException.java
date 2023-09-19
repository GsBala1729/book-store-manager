package com.finzly.bookmanager.exceptions;

public class NoBooksFoundException extends RuntimeException{
    public NoBooksFoundException(String msg){
        super(msg);
    }
}
