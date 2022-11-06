package nus.climods.model.module;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Module lesson type
 */
public enum LessonTypeEnum {
    LAB("Laboratory"),
    LEC("Lecture"),
    TUT("Tutorial"),
    REC("Recitation"),
    SEC("Sectional Teaching"),
    OTHERS("Others");

    private final String valueStr;

    /**
     * Creates a LessonTypeEnum enum
     *
     * @param valueStr lesson type description
     */
    LessonTypeEnum(String valueStr) {
        this.valueStr = valueStr;
    }

    /**
     * Creates a LessonTypeEnum from a given value
     *
     * @param value value
     * @return lesson type
     */
    public static LessonTypeEnum fromValue(String value) {
        for (LessonTypeEnum l : LessonTypeEnum.values()) {
            if (l.valueStr.equals(value)) {
                return l;
            }
        }

        return LessonTypeEnum.OTHERS;
    }

    /**
     * Creates a LessonTypeEnum from a given name
     *
     * @param name name
     * @return lesson type
     */
    @JsonCreator
    public static LessonTypeEnum fromName(String name) {
        for (LessonTypeEnum l : LessonTypeEnum.values()) {
            if (l.name().equals(name)) {
                return l;
            }
        }

        return LessonTypeEnum.OTHERS;
    }

    @Override
    public String toString() {
        return this.valueStr;
    }
}
