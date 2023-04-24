package com.swm.mini.dto;

import com.swm.mini.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class UserDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String userId;
        @NotNull
        private String nickname;
        @NotNull
        private String password;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        @NotNull
        private String userId;
        @NotNull
        private String nickname;

        public static Response fromEntity(User user) {
            return Response.builder()
                    .userId(user.getId())
                    .nickname(user.getNickname())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Login {
        @NotNull
        private String userId;
        @NotNull
        private String password;
    }
}
