package com.saxakiil.eventshubbackend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.saxakiil.eventshubbackend.util.Constants.dateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    private static boolean isDate(String date) {
        try {
            LocalDate.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isNotFutureDate(String date) {
        return isDate(date) && LocalDate.parse(date, dateTimeFormatter).equals(LocalDate.now());
    }

    public static String getPublicId(String url) {
        final String[] urlElements = StringUtils.split(url, "/");
        final String lastUrlElement = urlElements[urlElements.length - 1];
        return lastUrlElement.split("\\.")[0];
    }
}
