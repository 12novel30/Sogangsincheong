package com.swm.mini.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiniErrorResponse {
    private MiniErrorCode errorCode;
    private String errorMessage;
}