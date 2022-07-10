package com.example.market.exception;

public class ItemNotFound extends TopException{

    private static final String MESSAGE = "존재하지 않는 상품";

    public ItemNotFound(){
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
