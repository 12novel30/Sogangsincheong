package com.swm.mini.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MiniErrorCode {
    ALREADY_REGISTERED(500, "CREATE-USER-ERR-500", "is already registered."),
    NO_USER(500, "USER-ERR-500", "There is no corresponding USER."),

    WRONG_PASSWORD(500, "LOGIN-ERR-500", "Invalid password."),
    WRONG_EMAIL(500, "LOGIN-ERR-500", "There is no such email information."),
    NO_CATEGORY(500, "CATEGORY-ERR-500", "There is no corresponding CATEGORY."),
    NO_QUEST(500, "QUEST-ERR-500", "There is no corresponding QUEST."),
    NO_MEM_DO(500, "MEM_DO-ERR-500", "There is no quest that user challenging."),
    NO_BADGE(500, "BADGE-SUPERUSER-ERROR", "There is no corresponding BADGE."),
    NO_PRIORITY(500, "CATEGORY-ERR-500", "Please set PRIORITY of categories."),
    ALREADY_ADDED(500, "ADD-QUEST-ERR-500", "This quest already been added to your QuestList."),

    ;

    private int status;
    private String errorCode;
    private String message;
}
