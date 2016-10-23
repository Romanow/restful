package ru.romanow.restful.model;

import com.google.common.base.MoreObjects;

/**
 * Created by ronin on 22.10.16
 */
public class ErrorResponse {
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("message", message)
                          .toString();
    }
}
