package com.ccstudy.qna.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class StringConverter {
    public static String toStringDate(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return Optional.ofNullable(dateTime)
                .map(dateTimeFormatter::format)
                .orElse("");
    }
}
