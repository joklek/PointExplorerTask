package com.joklek.pointexplorer.exception;

import org.springframework.lang.NonNull;

public class IncorrectModuleArgumentException extends Exception {
    public IncorrectModuleArgumentException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
