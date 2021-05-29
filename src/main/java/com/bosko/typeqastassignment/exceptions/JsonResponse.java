package com.bosko.typeqastassignment.exceptions;

import lombok.Data;

@Data
public class JsonResponse {

    private String error;

    public JsonResponse(String error) {
        this.error = error;
    }
}
