package jarvis.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a lesson book
 */
public interface ReadOnlyLessonBook {

    /**
     * Returns an unmodifiable view of the lessons list.
     * This list will not contain any duplicate lesson.
     */
    ObservableList<Lesson> getLessonList();

}
