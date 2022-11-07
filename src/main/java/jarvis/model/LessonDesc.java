package jarvis.model;

import static jarvis.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Lesson description in JARVIS.
 */
public class LessonDesc {

    public static final String MESSAGE_CONSTRAINTS = "Lesson descriptions should not be blank";

    public final String lessonDesc;

    /**
     * Constructs a {@code LessonDesc}.
     *
     * @param desc A valid description.
     */
    public LessonDesc(String desc) {
        requireNonNull(desc);
        checkArgument(isValidLessonDesc(desc), MESSAGE_CONSTRAINTS);
        lessonDesc = desc.strip();
    }

    /**
     * Returns true if a given string is a valid lesson description.
     */
    public static boolean isValidLessonDesc(String lessonDesc) {
        if (lessonDesc == null) {
            return false;
        }
        return !lessonDesc.isBlank();
    }

    @Override
    public String toString() {
        return lessonDesc;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonDesc // instanceof handles nulls
                && lessonDesc.equals(((LessonDesc) other).lessonDesc)); // state check
    }

    @Override
    public int hashCode() {
        return lessonDesc.hashCode();
    }

}
