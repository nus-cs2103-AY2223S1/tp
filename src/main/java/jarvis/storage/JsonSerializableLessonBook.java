package jarvis.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;

import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.Lesson;
import jarvis.model.LessonBook;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.ReadOnlyStudentBook;


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
    public JsonSerializableLessonBook(ReadOnlyLessonBook source, ReadOnlyStudentBook studentBook) throws JsonProcessingException {
        lessons.addAll(source.getLessonList().stream()
                .map(x -> {
                    try {
                        return JsonAdaptedLesson.createLesson(x, studentBook);
                    } catch (JsonProcessingException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Lesson book into the model's {@code LessonBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LessonBook toModelType(ReadOnlyStudentBook studentBook) throws IllegalValueException, IOException {
        LessonBook lessonBook = new LessonBook();
        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType(studentBook);
            if (lessonBook.hasLesson(lesson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LESSONS);
            }
            lessonBook.addLesson(lesson);
        }
        return lessonBook;
    }
}
