package com.codeit.blog.entity;

public enum Category {

    TECH("기술"),
    LIFE("일상"),
    TRAVEL("여행"),
    FOOD("음식"),
    HOBBY("취미");


    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
