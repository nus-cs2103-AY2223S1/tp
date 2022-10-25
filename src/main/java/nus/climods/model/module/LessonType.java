package nus.climods.model.module;

/**
 * Module lesson type
 */
public enum LessonType {
    TUT("Tutorial"),
    LEC("Lecture"),
    LAB("Laboratory"),
    REC("Recitation"),
    UNSUPPORTED("");

    private final String valueStr;

    /**
     * Creates a LessonType enum
     *
     * @param valueStr lesson type description
     */
    LessonType(String valueStr) {
        this.valueStr = valueStr;
    }

    /**
     * Creates a LessonType from a given value
     *
     * @param value value
     * @return lesson type
     */
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
