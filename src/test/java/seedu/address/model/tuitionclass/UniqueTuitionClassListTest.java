package seedu.address.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.tuitionclass.exceptions.DuplicateTuitionClassException;
import seedu.address.model.tuitionclass.exceptions.TuitionClassNotFoundException;
import seedu.address.testutil.TuitionClassBuilder;

public class UniqueTuitionClassListTest {

    private final UniqueTuitionClassList uniqueTuitionClassList = new UniqueTuitionClassList();

    @Test
    public void contains_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionClassList.contains(null));
    }

    @Test
    public void contains_tuitionClassNotInList_returnsFalse() {
        assertFalse(uniqueTuitionClassList.contains(TUITIONCLASS1));
    }

    @Test
    public void contains_tuitionClassInList_returnsTrue() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        assertTrue(uniqueTuitionClassList.contains(TUITIONCLASS1));
    }

    @Test
    public void contains_tuitionClassWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        TuitionClass editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1)
                .withSubject("PHYSICS")
                .withLevel("SECONDARY1")
                .withDay("SUNDAY")
                .withTime("20:00", "22:00")
                .withTags("tiring")
                .build();
        assertTrue(uniqueTuitionClassList.contains(editedTuitionClass1));
    }

    @Test
    public void add_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionClassList.add(null));
    }

    @Test
    public void add_duplicateTuitionClass_throwsDuplicateTuitionClassException() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        assertThrows(DuplicateTuitionClassException.class, () ->
                uniqueTuitionClassList.add(TUITIONCLASS1));
    }

    @Test
    public void setTuitionClass_nullTargetTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTuitionClassList.setTuitionClass(null, TUITIONCLASS1));
    }

    @Test
    public void setTuitionClass_nullEditedTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTuitionClassList.setTuitionClass(TUITIONCLASS1, null));
    }

    @Test
    public void setTuitionClass_targetTuitionClassNotInList_throwsTuitionClassNotFoundException() {
        assertThrows(TuitionClassNotFoundException.class, () ->
                uniqueTuitionClassList.setTuitionClass(TUITIONCLASS1, TUITIONCLASS1));
    }

    @Test
    public void setTuitionClass_editedTuitionClassIsSameTuitionClass_success() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        uniqueTuitionClassList.setTuitionClass(TUITIONCLASS1, TUITIONCLASS1);
        UniqueTuitionClassList expectedUniqueTuitionClassList = new UniqueTuitionClassList();
        expectedUniqueTuitionClassList.add(TUITIONCLASS1);
        assertEquals(expectedUniqueTuitionClassList, uniqueTuitionClassList);
    }

    @Test
    public void setTuitionClass_editedTuitionClassHasSameIdentity_success() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        TuitionClass editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1)
                .withSubject("PHYSICS")
                .withLevel("SECONDARY1")
                .withDay("SUNDAY")
                .withTime("20:00", "22:00")
                .withTags("tiring")
                .build();
        uniqueTuitionClassList.setTuitionClass(TUITIONCLASS1, editedTuitionClass1);
        UniqueTuitionClassList expectedUniqueTuitionClassList = new UniqueTuitionClassList();
        expectedUniqueTuitionClassList.add(editedTuitionClass1);
        assertEquals(expectedUniqueTuitionClassList, uniqueTuitionClassList);
    }

    @Test
    public void setTuitionClass_editedTuitionClassHasDifferentIdentity_success() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        uniqueTuitionClassList.setTuitionClass(TUITIONCLASS1, TUITIONCLASS2);
        UniqueTuitionClassList expectedUniqueTuitionClassList = new UniqueTuitionClassList();
        expectedUniqueTuitionClassList.add(TUITIONCLASS2);
        assertEquals(expectedUniqueTuitionClassList, uniqueTuitionClassList);
    }

    @Test
    public void setTuitionClass_editedTuitionClassHasNonUniqueIdentity_throwsDuplicateTuitionClassException() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        uniqueTuitionClassList.add(TUITIONCLASS2);
        assertThrows(DuplicateTuitionClassException.class, () ->
                uniqueTuitionClassList.setTuitionClass(TUITIONCLASS1, TUITIONCLASS2));
    }

    @Test
    public void remove_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTuitionClassList.remove(null));
    }

    @Test
    public void remove_tuitionClassDoesNotExist_throwsTuitionClassNotFoundException() {
        assertThrows(TuitionClassNotFoundException.class, () ->
                uniqueTuitionClassList.remove(TUITIONCLASS1));
    }

    @Test
    public void remove_existingTuitionClass_removesTuitionClass() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        uniqueTuitionClassList.remove(TUITIONCLASS1);
        UniqueTuitionClassList expectedUniqueTuitionClassList = new UniqueTuitionClassList();
        assertEquals(expectedUniqueTuitionClassList, uniqueTuitionClassList);
    }

    @Test
    public void setTuitionClasses_nullUniqueTuitionClassList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTuitionClassList.setTuitionClasses((UniqueTuitionClassList) null));
    }

    @Test
    public void setTuitionClasses_uniqueTuitionClassList_replacesOwnListWithProvidedUniqueTuitionClassList() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        UniqueTuitionClassList expectedUniqueTuitionClassList = new UniqueTuitionClassList();
        expectedUniqueTuitionClassList.add(TUITIONCLASS2);
        uniqueTuitionClassList.setTuitionClasses(expectedUniqueTuitionClassList);
        assertEquals(expectedUniqueTuitionClassList, uniqueTuitionClassList);
    }

    @Test
    public void setTuitionClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTuitionClassList.setTuitionClasses((List<TuitionClass>) null));
    }

    @Test
    public void setTuitionClasses_list_replacesOwnListWithProvidedList() {
        uniqueTuitionClassList.add(TUITIONCLASS1);
        List<TuitionClass> tuitionClassList = Collections.singletonList(TUITIONCLASS2);
        uniqueTuitionClassList.setTuitionClasses(tuitionClassList);
        UniqueTuitionClassList expectedUniqueTuitionClassList = new UniqueTuitionClassList();
        expectedUniqueTuitionClassList.add(TUITIONCLASS2);
        assertEquals(expectedUniqueTuitionClassList, uniqueTuitionClassList);
    }

    @Test
    public void setTuitionClasses_listWithDuplicateTuitionClasses_throwsDuplicateTuitionClassesException() {
        List<TuitionClass> listWithDuplicateTuitionClasses = Arrays.asList(TUITIONCLASS1, TUITIONCLASS1);
        assertThrows(DuplicateTuitionClassException.class, () ->
                uniqueTuitionClassList.setTuitionClasses(listWithDuplicateTuitionClasses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueTuitionClassList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void sort_default_success() {
        ArrayList<TuitionClass> expected = new ArrayList<>(Arrays.asList(
                new TuitionClassBuilder().withName("P5ENG").build(),
                new TuitionClassBuilder().withName("S2MATH").build(),
                new TuitionClassBuilder().withName("P1ART").build()));
        expected.forEach(uniqueTuitionClassList::add);
        uniqueTuitionClassList.sort(SortCommand.SortBy.ALPHA);
        uniqueTuitionClassList.sort(SortCommand.SortBy.REVERSE);
        uniqueTuitionClassList.sort(SortCommand.SortBy.DEFAULT);
        ArrayList<TuitionClass> actual = new ArrayList<>(uniqueTuitionClassList.internalList);
        assertEquals(expected, actual);
    }
}
