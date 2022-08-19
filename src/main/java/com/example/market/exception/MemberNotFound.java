package com.example.market.exception;

public class MemberNotFound extends TopException{
    private static final String MESSAGE = "존재하지 않는 멤버";

    public MemberNotFound(){
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
