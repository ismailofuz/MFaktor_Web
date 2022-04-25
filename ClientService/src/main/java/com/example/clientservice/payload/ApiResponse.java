package com.example.clientservice.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse<T> {

    private String message;

    private boolean success;

    private T object;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
