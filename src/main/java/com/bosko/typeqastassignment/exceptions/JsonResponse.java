package com.bosko.typeqastassignment.exceptions;

import lombok.Data;

@Data
public class JsonResponse {

    private String message;

    public JsonResponse(String message) {
        this.message = message;
    }
}
