package com.springboot.restservice.messagerieapp.dto;

public class RestApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public RestApiResponse() {}

    public RestApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> RestApiResponse<T> ok(T data) {
        return new RestApiResponse<>(true, "OK", data);
    }

    public static <T> RestApiResponse<T> ok(String message, T data) {
        return new RestApiResponse<>(true, message, data);
    }

    public static <T> RestApiResponse<T> error(String message) {
        return new RestApiResponse<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
