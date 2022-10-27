package nus.climods.logic.parser.parameters;

import nus.climods.logic.parser.ParserUtil;
import nus.climods.model.module.LessonTypeEnum;

/**
 * Represents Lesson Type Parameter
 */
public class LessonTypeParameter extends PositionalParameter<LessonTypeEnum> {
    public static final String INVALID_INPUT_MESSAGE = "Lesson type should not be empty!";
    public static final String PARSE_EXCEPTION_MESSAGE = "Invalid lesson type: %s";
    private static final int LESSON_TYPE_INDEX = 1;

    /**
     * Creates a LessonCodeParameter with argumentsString
     *
     * @param argumentsString Arguments string to parse lesson type from
     */
    public LessonTypeParameter(String argumentsString) {
        super(LESSON_TYPE_INDEX, argumentsString, ParserUtil::parseLessonType, PARSE_EXCEPTION_MESSAGE);
        this.setEmptyInputMessage(INVALID_INPUT_MESSAGE);
    }
}
