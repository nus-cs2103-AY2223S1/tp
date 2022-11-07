package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


class PictureStorageLoaderTest {

    @Test
    public void constructor_nullOccupation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PictureStorageLoader(null));
    }

    @Test
    void getImageUrl_validOccupations() {

        //Initialize three Persons with the three different occupations
        Person alice = new PersonBuilder().withOccupation("TA").build();
        Person bob = new PersonBuilder().withOccupation("PROFESSOR").build();
        Person charlie = new PersonBuilder().withOccupation("STUDENT").build();

        //check alice's imageUrl
        PictureStorageLoader aliceStorage = new PictureStorageLoader(alice.getOccupation().toString());
        assertTrue(aliceStorage.getImageUrl().equals("/images/ta_pic.png"));
        assertFalse(aliceStorage.getImageUrl().equals(null));

        //check bob's imageUrl
        PictureStorageLoader bobStorage = new PictureStorageLoader(bob.getOccupation().toString());
        assertTrue(bobStorage.getImageUrl().equals("/images/professor_pic.png"));
        assertFalse(bobStorage.getImageUrl().equals(null));

        //check charlie's imageUrl
        PictureStorageLoader charlieStorage = new PictureStorageLoader(charlie.getOccupation().toString());
        assertTrue(charlieStorage.getImageUrl().equals("/images/student_pic.png"));
        assertFalse(charlieStorage.getImageUrl().equals(null));
    }

    @Test
    void getImageUrl_invalidOccupations() {

        //Initialize three Persons with the three different occupations
        Person alice = new PersonBuilder().withOccupation("TA").build();
        Person bob = new PersonBuilder().withOccupation("PROFESSOR").build();
        Person charlie = new PersonBuilder().withOccupation("STUDENT").build();

        //check alice's imageUrl
        PictureStorageLoader aliceStorage = new PictureStorageLoader(alice.getOccupation().toString());
        assertFalse(aliceStorage.getImageUrl().equals("/images/professor_pic.png"));

        //check bob's imageUrl
        PictureStorageLoader bobStorage = new PictureStorageLoader(bob.getOccupation().toString());
        assertFalse(bobStorage.getImageUrl().equals("/images/student_pic.png"));

        //check charlie's imageUrl
        PictureStorageLoader charlieStorage = new PictureStorageLoader(charlie.getOccupation().toString());
        assertFalse(charlieStorage.getImageUrl().equals("/images/ta_pic.png"));
    }

    @Test
    void equals() {
        PictureStorageLoader storage1 = new PictureStorageLoader("TA");
        PictureStorageLoader storage2 = new PictureStorageLoader("STUDENT");

        // same object -> returns true
        assertTrue(storage1.equals(storage1));

        // same values -> returns false
        PictureStorageLoader storage1Copy = new PictureStorageLoader("TA");
        assertFalse(storage1.equals(storage1Copy));

        // different types -> returns false
        assertFalse(storage1.equals(1));

        // null -> returns false
        assertFalse(storage1.equals(null));

        // different storageLoader -> returns false
        assertFalse(storage1.equals(storage2));
    }
}
