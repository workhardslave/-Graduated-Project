package com.example.demo.item.overlap;

public class NotEnoughAmountException extends RuntimeException {
    public NotEnoughAmountException() {
    }
    public NotEnoughAmountException(String message) {
        super(message);
    }
    public NotEnoughAmountException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotEnoughAmountException(Throwable cause) {
        super(cause);
    }
}
