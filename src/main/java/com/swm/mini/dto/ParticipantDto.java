package com.swm.mini.dto;

import com.swm.mini.entity.Event;
import com.swm.mini.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class ParticipantDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String userId;
        @NotNull
        private long eventId;
        private String memo;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        @NotNull
        private String userNickName;
        private String memo;
        public static Response fromEntity(Participant participant) {
            return Response.builder()
                    .userNickName(participant.getUser().getNickname())
                    .memo(participant.getMemo())
                    .build();
        }
    }
}