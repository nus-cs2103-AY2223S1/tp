package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAttributes.AGE;
import static seedu.address.testutil.TypicalAttributes.POSITION;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.attribute.exceptions.AttributeException;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class AbstractDisplayItemTest {
    private Set<Tag> tags;

    Person buildDefaultPerson(String name, String... tags) {
        return new PersonBuilder().withName(name)
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253")
                .withTags(tags).build();
    }

    @Test
    void rename_validName_success() {
        Person alice = buildDefaultPerson("Alice");
        alice.rename("Bob");
        assertEquals(alice.getName().fullName, "Bob");
    }

    @Test
    void getTags() {
        tags = new HashSet<>();
        tags.add(new Tag("friends"));
        assertEquals(buildDefaultPerson("Charlie", "friends").getTags(), tags);
    }

    @Test
    void addTags() {
        tags = new HashSet<>();
        tags.add(new Tag("friends"));
        tags.add(new Tag("colleagues"));
        Person donny = buildDefaultPerson("Donny", "friends");
        donny.addTags("colleagues");
        assertEquals(donny.getTags(), tags);
    }

    @Test
    void deleteTag_existingTag_success() {
        Person echo = buildDefaultPerson("Echo", "friends");
        echo.deleteTag("friends");
        assertTrue(echo.getTags().isEmpty());
    }

    @Test
    void deleteTag_tagNotFound_doesNothing() {
        Set<Tag> prevTags = ALICE.getTags();
        ALICE.deleteTag("doctor");
        assertEquals(ALICE.getTags(), prevTags);
    }

    @Test
    void getAttribute_attributeNotFound_success() {
        Person george = buildDefaultPerson("George", "friends");
        assertFalse(george.getAttribute("dummy").isPresent());
    }

    @Test
    void getAttribute_attributeFound_success() throws AttributeException {
        Person dummy = new PersonBuilder(ALICE).withAttribute("Github", "dummy123").build();
        assertEquals(dummy.getAttribute("Github").get().toString(), "Github: dummy123");
    }

    @Test
    void editAttribute_existingAttribute_success() throws AttributeException {
        Person dummy = new PersonBuilder(ALICE).withAttribute("Github", "dummy123").build();
        dummy.editAttribute("Github", "dummy321");
        assertEquals(dummy.getAttribute("Github").get().toString(), "Github: dummy321");
    }

    @Test
    void addAttribute_newAttributeInstance_success() {
        Person dummy = buildDefaultPerson("dummy", "friends");
        dummy.addAttribute(AGE);
        assertEquals(dummy.getAttribute("Age").get().toString(), "Age: 20");
    }

    @Test
    void addAttribute_stringTypeAndValue_success() throws AttributeException {
        Person dummy = buildDefaultPerson("dummy", "friends");
        dummy.addAttribute("Position", "CEO");
        assertEquals(dummy.getAttribute("Position").get(), POSITION);
    }

    @Test
    void addAttribute_existingAttribute_throwsAttributeException() throws AttributeException {
        Person dummy = buildDefaultPerson("dummy", "friends");
        dummy.addAttribute("Position", "CEO");
        assertThrows(AttributeException.class, () -> dummy.addAttribute("Position", "President"));
    }

    @Test
    void setTags() {
        Person dummy = buildDefaultPerson("dummy");
        Set<Tag> tags = new HashSet<Tag>();
        tags.add(new Tag("president"));
        tags.add(new Tag("boss"));
        dummy.setTags(tags);
        assertEquals(dummy.getTags(), tags);
    }

    @Test
    void canBeChildOf() {

    }

    @Test
    void getTitle() {
    }

    @Test
    void getTypeFlag() {
    }

    @Test
    void deleteAttribute() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getName() {
    }

    @Test
    void stronglyEqual() {
    }

    @Test
    void weaklyEqual() {
    }

    @Test
    void isPartOfContext() {
    }

    @Test
    void getAttributes() {
    }

    @Test
    void getSavedAttributes() {
    }

    @Test
    void testEquals() {
    }
}
