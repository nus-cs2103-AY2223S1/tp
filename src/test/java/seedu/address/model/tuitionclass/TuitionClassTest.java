package seedu.address.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TuitionClassBuilder;

public class TuitionClassTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        TuitionClass tuitionClass = new TuitionClassBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> tuitionClass.getTags().remove(0));
    }

    @Test
    public void isSameTuitionClass() {
        // same object -> returns true
        assertTrue(TUITIONCLASS1.isSameTuitionClass(TUITIONCLASS1));

        // null -> returns false
        assertFalse(TUITIONCLASS1.isSameTuitionClass(null));

        // same name, all other attributes different -> returns true
        TuitionClass editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1)
                .withSubject("BIOLOGY").withLevel("PRIMARY3")
                .withDay("TUESDAY").withTime("18:00", "20:00")
                .withTags("tough").build();
        assertTrue(TUITIONCLASS1.isSameTuitionClass(editedTuitionClass1));

        // different name, all other attributes same -> returns false
        editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1).withName("P4BIO").build();
        assertFalse(TUITIONCLASS1.isSameTuitionClass(editedTuitionClass1));

        // name differs in case, all other attributes same -> returns false
        TuitionClass editedTuitionClass2 = new TuitionClassBuilder(TUITIONCLASS2)
                .withName("P6CHEM".toLowerCase()).build();
        assertFalse(TUITIONCLASS2.isSameTuitionClass(editedTuitionClass2));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = "P6CHEM" + " ";
        editedTuitionClass2 = new TuitionClassBuilder(TUITIONCLASS2).withName(nameWithTrailingSpaces).build();
        assertFalse(TUITIONCLASS2.isSameTuitionClass(editedTuitionClass2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TuitionClass tuitionClass1Copy = new TuitionClassBuilder(TUITIONCLASS1).build();
        assertTrue(TUITIONCLASS1.equals(tuitionClass1Copy));

        // same object -> returns true
        assertTrue(TUITIONCLASS1.equals(TUITIONCLASS1));

        // null -> returns false
        assertFalse(TUITIONCLASS1.equals(null));

        // different type -> returns false
        assertFalse(TUITIONCLASS1.equals(5));

        // different tuition class -> returns false
        assertFalse(TUITIONCLASS1.equals(TUITIONCLASS2));

        // different name -> returns false
        TuitionClass editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1).withName("P4CHEM").build();
        assertFalse(TUITIONCLASS1.equals(editedTuitionClass1));

        // different subject -> returns false
        editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1).withSubject("PHYSICS").build();
        assertFalse(TUITIONCLASS1.equals(editedTuitionClass1));

        // different level -> returns false
        editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1).withLevel("PRIMARY4").build();
        assertFalse(TUITIONCLASS1.equals(editedTuitionClass1));

        // different day -> returns false
        editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1).withDay("SATURDAY").build();
        assertFalse(TUITIONCLASS1.equals(editedTuitionClass1));

        // different time -> returns false
        editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1).withTime("15:00", "17:00").build();
        assertFalse(TUITIONCLASS1.equals(editedTuitionClass1));

        // different tags -> returns false
        editedTuitionClass1 = new TuitionClassBuilder(TUITIONCLASS1).withTags("boring").build();
        assertFalse(TUITIONCLASS1.equals(editedTuitionClass1));
    }
}
