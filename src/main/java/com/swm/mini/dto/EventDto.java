package com.swm.mini.dto;

import com.swm.mini.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EventDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String title;
        @NotNull
        private String category;
        @NotNull
        private int limit;
        @NotNull
        private Date startDate;
        @NotNull
        private Date endDate;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        @NotNull
        private String title;

        @NotNull
        private String category;
        @NotNull
        private int limit;
        @NotNull
        private Date startDate;
        @NotNull
        private Date endDate;
        private int nowParticipant;

        public static Response fromEntity(Event event) {
            return Response.builder()
                    .title(event.getTitle())
                    .category(event.getCategory())
                    .limit(event.getLimit())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
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

