package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void deepCopy_notSameButEqual() {
        String tagName = "House";
        Person personA = new PersonBuilder().withName("PersonA").withTags(tagName).build();
        Person deepCopy = personA.deepCopy();

        assertNotSame(personA, deepCopy);
        assertEquals(personA, deepCopy);
    }

    @Test
    public void deepCopy_tagsCopiedNotSame() {
        String tagName = "House";
        Tag tag = new Tag(tagName);
        Person personA = new Person(
                new Name("PersonA"),
                new Phone(PersonBuilder.DEFAULT_PHONE),
                new Email(PersonBuilder.DEFAULT_EMAIL),
                new Address(PersonBuilder.DEFAULT_ADDRESS),
                Set.of(tag),
                new Loan(PersonBuilder.DEFAULT_LOAN));

        tag.addPerson(personA);

        Person deepCopy = personA.deepCopy();

        assertEquals(1, personA.getTags().size());
        assertEquals(1, deepCopy.getTags().size());
        assertNotSame(personA.getTags().toArray()[0], deepCopy.getTags().toArray()[0]);
        assertEquals(personA.getTags().toArray()[0], deepCopy.getTags().toArray()[0]);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deepCopy_tagsCopiedPointToOtherSamePerson() {
        String tagName = "House";
        Tag tag = new Tag(tagName);
        Set<Tag> tagSet = Set.of(tag);
        Person personA = new Person(
                new Name("PersonA"),
                new Phone(PersonBuilder.DEFAULT_PHONE),
                new Email(PersonBuilder.DEFAULT_EMAIL),
                new Address(PersonBuilder.DEFAULT_ADDRESS),
                tagSet,
                new Loan(PersonBuilder.DEFAULT_LOAN));

        Person personB = new Person(
                new Name("PersonB"),
                new Phone(PersonBuilder.DEFAULT_PHONE),
                new Email(PersonBuilder.DEFAULT_EMAIL),
                new Address(PersonBuilder.DEFAULT_ADDRESS),
                tagSet,
                new Loan(PersonBuilder.DEFAULT_LOAN));

        tag.addPerson(personA);
        tag.addPerson(personB);

        Person deepCopy = personA.deepCopy();
        Tag deepCopyTag = deepCopy.getTags().toArray(Tag[]::new)[0];

        assertEquals(1, personA.getTags().size());
        assertEquals(1, deepCopy.getTags().size());

        // By equality, personA and personB exist
        assertTrue(deepCopyTag.getDeepCopiedPersonList().contains(personA));
        assertTrue(deepCopyTag.getDeepCopiedPersonList().contains(personB));

        // for testing purposes, use reflection to set accessibility of a normally hidden field to true
        List<Person> personList;
        try {
            Field personListField = deepCopyTag.getClass().getDeclaredField("personList");
            personListField.setAccessible(true);
            personList = (List<Person>) personListField.get(deepCopyTag);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            personList = new ArrayList<>();
        }

        // By reference, personB exists, but personA does not exist and is
        // instead replaced by deepCopy
        assertSame(personB, personList
                .stream()
                .filter(p -> p.equals(personB))
                .findFirst()
                .orElseGet(() -> new PersonBuilder().build()));

        assertNotSame(personA, personList
                .stream()
                .filter(p -> p.equals(personA))
                .findFirst()
                .orElseGet(() -> new PersonBuilder().build()));

        assertSame(deepCopy, personList
                .stream()
                .filter(p -> p.equals(personA))
                .findFirst()
                .orElseGet(() -> new PersonBuilder().build()));
    }
}
