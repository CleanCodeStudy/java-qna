package com.ccstudy.qna.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeConverter {
    private static final String DATE_TIME = "yyyy/MM/dd";

    public static String convertLocalDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME);
        return dateTime.toLocalDate().format(formatter);
    }
}
