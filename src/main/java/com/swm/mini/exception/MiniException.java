package com.swm.mini.exception;

import lombok.Getter;

@Getter
public class MiniException extends RuntimeException {
    private MiniErrorCode miniErrorCode;
    private String detailMessage;

    public MiniException(MiniErrorCode miniErrorCode) {
        // for 일반적인 에러상황
        super(miniErrorCode.getMessage());
        this.miniErrorCode = miniErrorCode;
        this.detailMessage = miniErrorCode.getMessage();
    }

    public MiniException(MiniErrorCode miniErrorCode, String detailMessage) {
        // for 커스텀한 에러메세지를 출력해야할 때 사용
        super(miniErrorCode.getMessage());
        this.miniErrorCode = miniErrorCode;
        this.detailMessage = detailMessage;
    }
}
