package com.inventory.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage<T> {

    private int status;
    private Date timestamp;
    private String title;
    private List<T> errors;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Error {

        private String code;
        private String detail;

    }

}
