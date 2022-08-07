package com.saxakiil.eventshubbackend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {

    public static String getPublicId(String url) {
        final String[] urlElements = StringUtils.split(url, "/");
        final String lastUrlElement = urlElements[urlElements.length - 1];
        return lastUrlElement.split("\\.")[0];
    }
}
