package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Lesson;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {
    public static final String DEFAULT_TYPE = "tut";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_DAY = "4";
    public static final String DEFAULT_START_TIME = "14:00";
    public static final String DEFAULT_END_TIME = "15:00";

    private String type;
    private String module;
    private String day;
    private String startTime;
    private String endTime;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        type = DEFAULT_TYPE;
        module = DEFAULT_MODULE;
        day = DEFAULT_DAY;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
    }

    /**
     * Sets the {@code type} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the {@code module} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withModule(String module) {
        this.module = module;
        return this;
    }

    /**
     * Sets the {@code day} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withDay(String day) {
        this.day = day;
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Builds a Lesson object with the given fields.
     *
     * @return a Lesson object with the given fields.
     */
    public Lesson build() {
        try {
            return ParserUtil.parseLesson(type, module, day, startTime, endTime);
        } catch (ParseException p) {
            p.printStackTrace();
            return null;
        }
    }

}
