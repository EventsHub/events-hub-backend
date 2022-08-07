package com.saxakiil.eventshubbackend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String PAGE_SIZE = "20";
    public static String WRONG_OPERATION_EXCEPTION = "This service operation is wrong";
    public static String USER_IS_NOT_FOUND_EXCEPTION = "The User with id='%l' is not found";
    public static String CARD_IS_ADDED = "The card with id = '%s' was successfully added";
    public static String CARD_IS_UPDATED = "The card with id = '%s' was successfully updated";
    public static String CARD_IS_DELETED = "The card with id = '%s' was successfully deleted";
//    public static String CARD_IS_PUBLISHED = "The card with id = '%s' was successfully published";
}
