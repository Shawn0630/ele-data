package com.ele.data.repositories;

import java.util.concurrent.CompletionException;

public class RepositoryException extends CompletionException {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
