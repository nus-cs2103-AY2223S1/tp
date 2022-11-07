package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADUATION_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAXIMUM_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_KIV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.predicates.SamePersonPredicate;
import seedu.address.testutil.PersonBuilder;

public class SamePersonPredicateTest {

    @Test
    public void equals() {
        SamePersonPredicate firstPredicate = new SamePersonPredicate(AMY);
        SamePersonPredicate secondPredicate = new SamePersonPredicate(BOB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(secondPredicate.equals(secondPredicate));

        // same values -> returns true
        SamePersonPredicate firstPredicateCopy = new SamePersonPredicate(AMY);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(0));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isSamePerson_returnsTrue() {
        SamePersonPredicate firstPredicate = new SamePersonPredicate(AMY);
        SamePersonPredicate secondPredicate = new SamePersonPredicate(ALICE);
        SamePersonPredicate thirdPredicate = new SamePersonPredicate(BOB);

        // same Person object
        assertTrue(firstPredicate.test(AMY));

        // same email and job ID, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withTitle(VALID_JOB_TITLE_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withTags(VALID_TAG_KIV).build();
        assertTrue(secondPredicate.test(editedAlice));
    }

    @Test
    public void test_isSamePerson_returnsFalse() {
        SamePersonPredicate firstPredicate = new SamePersonPredicate(AMY);
        SamePersonPredicate secondPredicate = new SamePersonPredicate(ALICE);

        // null
        assertFalse(firstPredicate.test(null));

        // different email, all other attributes same
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(secondPredicate.test(editedAlice));

        // different job ID, all other attributes same
        editedAlice = new PersonBuilder(ALICE).withId(VALID_JOB_ID_BOB).build();
        assertFalse(secondPredicate.test(editedAlice));

    }
}
