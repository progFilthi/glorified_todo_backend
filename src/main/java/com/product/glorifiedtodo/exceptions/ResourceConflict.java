package com.product.glorifiedtodo.exceptions;

public class ResourceConflict extends RuntimeException {
    public ResourceConflict(String message) {
        super(message);
    }
}
