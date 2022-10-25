package nus.climods.model.module;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LessonType {
    TUT("Tutorial"),
    LEC("Lecture"),
    LAB("Laboratory"),
    REC("Recitation"),
    UNSUPPORTED("");

    private final String valueStr;

    LessonType(String valueStr) {
        this.valueStr = valueStr;
    }

    @JsonCreator
    public static LessonType fromValue(String value) {
        for (LessonType l : LessonType.values()) {
            if (l.valueStr.equals(value)) {
                return l;
            }
        }

        return LessonType.UNSUPPORTED;
    }

    @Override
    public String toString() {
        return this.valueStr;
    }
}
