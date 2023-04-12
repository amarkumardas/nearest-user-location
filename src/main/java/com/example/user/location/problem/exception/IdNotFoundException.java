package com.example.user.location.problem.exception;

public class IdNotFoundException extends Exception{
    public IdNotFoundException(){//non parameterized constructor
        super();
    }
    public IdNotFoundException(String message){
        super(message);
    }
}