package com.example.market.exception;

public class CommentNotFound extends TopException{

    private static final String MESSAGE = "존재하지 않는 댓글";

    public CommentNotFound(){
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
