package com.ccstudy.qna.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateTimeConverter { //네이밍
    public static String toStringDate(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return Optional.ofNullable(dateTime)
                .map(dateTimeFormatter::format)
                .orElse("");
    }
}
