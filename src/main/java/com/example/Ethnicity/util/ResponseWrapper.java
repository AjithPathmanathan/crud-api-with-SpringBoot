package com.example.Ethnicity.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class ResponseWrapper<T> {
        private int statusCode;
        private String statusMessage;
        private T data;
    }

