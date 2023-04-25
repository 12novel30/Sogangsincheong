package com.swm.mini.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MiniErrorCode {
    ALREADY_REGISTERED("is already registered."),
    NO_USER("There is no corresponding USER."),
    NO_EVENT("There is no corresponding EVENT."),
    LIMIT_OVER("cannot join this event."),
    ALREADY_JOINED("You already joined this Event."),
    WRONG_PASSWORD("Invalid password."),
    ;

    private String message;
}
