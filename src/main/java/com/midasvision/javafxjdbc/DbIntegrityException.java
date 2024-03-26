package com.midasvision.javafxjdbc;

public class DbIntegrityException extends RuntimeException{

    public DbIntegrityException(String msg) {
        super(msg);
    }
}
