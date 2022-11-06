package jarvis.model;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.DT1;
import static jarvis.testutil.TypicalLessons.DT2;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.DuplicateLessonException;
import jarvis.model.exceptions.LessonClashException;
import jarvis.model.exceptions.LessonNotFoundException;
import jarvis.testutil.LessonBuilder;

public class UniqueLessonListTest {

    private final UniqueLessonList uniqueLessonList = new UniqueLessonList();

    @Test
    public void contains_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.contains(null));
    }

    @Test
    public void contains_lessonNotInList_returnsFalse() {
        assertFalse(uniqueLessonList.contains(STUDIO_1));
    }

    @Test
    public void contains_lessonInList_returnsTrue() {
        uniqueLessonList.add(STUDIO_1);
        assertTrue(uniqueLessonList.contains(STUDIO_1));
    }

    @Test
    public void contains_lessonWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLessonList.add(STUDIO_1);
        Lesson editedLesson = new LessonBuilder(STUDIO_1).buildStudio();
        assertTrue(uniqueLessonList.contains(editedLesson));
    }

    @Test
    public void hasPeriodClash() {
        // empty list
        assertFalse(uniqueLessonList.hasPeriodClash(STUDIO_1));

        uniqueLessonList.add(STUDIO_1);
        Lesson lessonWithPeriodClash =
                new LessonBuilder(CONSULT_1).withTimePeriod(new TimePeriod(DT1, DT2)).buildConsult();
        assertTrue(uniqueLessonList.hasPeriodClash(lessonWithPeriodClash));
    }

    @Test
    public void add_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.add(null));
    }

    @Test
    public void add_duplicateLesson_throwsDuplicateLessonException() {
        uniqueLessonList.add(STUDIO_1);
        assertThrows(DuplicateLessonException.class, () -> uniqueLessonList.add(STUDIO_1));
    }

    @Test
    public void add_validLesson_success() {
        uniqueLessonList.add(STUDIO_1);
        assertTrue(uniqueLessonList.contains(STUDIO_1));
    }

    @Test
    public void setLesson_nullTargetLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.setLesson(null, STUDIO_1));
    }

    @Test
    public void setLesson_nullEditedLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.setLesson(STUDIO_1, null));
    }

    @Test
    public void setLesson_targetLessonNotInList_throwsLessonNotFoundException() {
        assertThrows(LessonNotFoundException.class, () -> uniqueLessonList.setLesson(STUDIO_1, STUDIO_1));
    }

    @Test
    public void setLesson_editedLessonIsSameLesson_success() {
        uniqueLessonList.add(STUDIO_1);
        uniqueLessonList.setLesson(STUDIO_1, STUDIO_1);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(STUDIO_1);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLesson_editedLessonHasSameIdentity_success() {
        uniqueLessonList.add(STUDIO_1);
        Lesson editedLesson = new LessonBuilder(STUDIO_1).buildStudio();
        uniqueLessonList.setLesson(STUDIO_1, editedLesson);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(editedLesson);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLesson_editedLessonHasDifferentIdentity_success() {
        uniqueLessonList.add(STUDIO_1);
        uniqueLessonList.setLesson(STUDIO_1, CONSULT_1);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(CONSULT_1);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLesson_editedLessonHasNonUniqueIdentity_throwsDuplicateLessonException() {
        uniqueLessonList.add(STUDIO_1);
        uniqueLessonList.add(CONSULT_1);
        assertThrows(DuplicateLessonException.class, () -> uniqueLessonList.setLesson(STUDIO_1, CONSULT_1));
    }

    @Test
    public void setLesson_editedLessonHasClashTiming_throwsLessonClashException() {
        uniqueLessonList.add(STUDIO_1);
        uniqueLessonList.add(CONSULT_1);
        Lesson editedLesson = new LessonBuilder(CONSULT_1).withTimePeriod(new TimePeriod(DT1, DT2)).buildConsult();
        assertThrows(LessonClashException.class, () -> uniqueLessonList.setLesson(CONSULT_1, editedLesson));
    }

    @Test
    public void remove_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.remove(null));
    }

    @Test
    public void remove_lessonDoesNotExist_throwsLessonNotFoundException() {
        assertThrows(LessonNotFoundException.class, () -> uniqueLessonList.remove(STUDIO_1));
    }

    @Test
    public void remove_existingLesson_removesLesson() {
        uniqueLessonList.add(STUDIO_1);
        uniqueLessonList.remove(STUDIO_1);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLessons_nullUniqueLessonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.setLessons((UniqueLessonList) null));
    }

    @Test
    public void setLessons_uniqueLessonList_replacesOwnListWithProvidedUniqueLessonList() {
        uniqueLessonList.add(STUDIO_1);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(CONSULT_1);
        uniqueLessonList.setLessons(expectedUniqueLessonList);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLessons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.setLessons((List<Lesson>) null));
    }

    @Test
    public void setLessons_list_replacesOwnListWithProvidedList() {
        uniqueLessonList.add(STUDIO_1);
        List<Lesson> lessonList = Collections.singletonList(CONSULT_1);
        uniqueLessonList.setLessons(lessonList);
        UniqueLessonList expectedUniqueLessonList = new UniqueLessonList();
        expectedUniqueLessonList.add(CONSULT_1);
        assertEquals(expectedUniqueLessonList, uniqueLessonList);
    }

    @Test
    public void setLessons_listWithDuplicateLessons_throwsDuplicateLessonException() {
        List<Lesson> listWithDuplicateLessons = Arrays.asList(STUDIO_1, STUDIO_1);
        assertThrows(DuplicateLessonException.class, () -> uniqueLessonList.setLessons(listWithDuplicateLessons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueLessonList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(uniqueLessonList.equals(uniqueLessonList));

        // same internal list -> returns true
        UniqueLessonList uniqueLessonListCopy = new UniqueLessonList();
        assertTrue(uniqueLessonList.equals(uniqueLessonListCopy));

        // different type -> returns false
        assertFalse(uniqueLessonList.equals(5));

        // null -> returns false
        assertFalse(uniqueLessonList.equals(null));

        // different internal list -> returns false
        uniqueLessonListCopy.add(STUDIO_1);
        assertFalse(uniqueLessonList.equals(uniqueLessonListCopy));
    }
}
