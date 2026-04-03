package com.example.BookMyShow.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonResponse<T> {

    private boolean error;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>(false, message, data);
    }

    public static <T> CommonResponse<T> failure(String message) {
        return new CommonResponse<>(true, message, null);
    }
}
