package jarvis.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.Lesson;
import jarvis.model.LessonBook;
import jarvis.model.ReadOnlyLessonBook;


/**
 * An Immutable LessonBook that is serializable to JSON format.
 */
@JsonRootName(value = "lessonbook")
public class JsonSerializableLessonBook {

    public static final String MESSAGE_DUPLICATE_LESSONS = "Lessons list contains duplicate Lesson(s).";

    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLessonBook} with the given Lessons.
     */
    @JsonCreator
    public JsonSerializableLessonBook(@JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.lessons.addAll(lessons);
    }

    /**
     * Converts a given {@code ReadOnlyLessonBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLessonBook}.
     */
    public JsonSerializableLessonBook(ReadOnlyLessonBook source) {
        lessons.addAll(source.getLessonList().stream()
                .map(x -> JsonAdaptedLesson.createLesson(x))
                .collect(Collectors.toList()));
        if (!lessons.stream().allMatch(jsonAdaptedLesson -> {
            if (jsonAdaptedLesson == null) {
                return false;
            }
            return true;
        })) {
            throw new AssertionError();
        }
    }

    /**
     * Converts this Lesson book into the model's {@code LessonBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LessonBook toModelType() throws IllegalValueException, IOException {
        LessonBook lessonBook = new LessonBook();
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            if (lessonBook.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSONS);
            }
            lessonBook.addLesson(lesson);
        }
        return lessonBook;
    }
}
