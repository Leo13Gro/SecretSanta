package ru.kuimov.secretsanta.exception.custom;

public class TossException extends RuntimeException {
    public TossException(){
        super("Toss error");
    }
}
