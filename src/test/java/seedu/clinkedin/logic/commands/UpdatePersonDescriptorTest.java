package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.AddToCommand.UpdatePersonDescriptor;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

public class UpdatePersonDescriptorTest {

    @Test
    public void constructor_noParams_success() {
        assertDoesNotThrow(() -> new UpdatePersonDescriptor());
    }

    @Test
    public void constructor_nullUpdatePersonDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UpdatePersonDescriptor(null));
    }

    @Test
    public void constructor_copy_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setNote(new Note("Test Note"));
        updatePersonDescriptor.setRating(new Rating("5"));
        assertDoesNotThrow(() -> new UpdatePersonDescriptor(updatePersonDescriptor));
    }

    @Test
    public void isAnyFieldEdited_noParams_false() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertFalse(updatePersonDescriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_someParams_true() throws MalformedURLException {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setNote(new Note("Test Note"));
        assertTrue(updatePersonDescriptor.isAnyFieldEdited());

        updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setRating(new Rating("5"));
        assertTrue(updatePersonDescriptor.isAnyFieldEdited());

        updatePersonDescriptor = new UpdatePersonDescriptor();
        Set<Link> links = Set.of(new Link(new URL("https://www.google.com")));
        updatePersonDescriptor.setLinks(links);
        assertTrue(updatePersonDescriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_allParams_true() throws MalformedURLException {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setNote(new Note("Test Note"));
        updatePersonDescriptor.setRating(new Rating("5"));
        Set<Link> links = Set.of(new Link(new URL("https://www.google.com")));
        updatePersonDescriptor.setLinks(links);
        assertTrue(updatePersonDescriptor.isAnyFieldEdited());
    }

    @Test
    public void setNote_nullNote_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertDoesNotThrow(() -> updatePersonDescriptor.setNote(null));
    }

    @Test
    public void setNote_validNote_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Note note = new Note("");
        updatePersonDescriptor.setNote(note);
        assertEquals(note, updatePersonDescriptor.getNote().get());
    }

    @Test
    public void getNote_nullNote_returnsEmptyOptional() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertFalse(updatePersonDescriptor.getNote().isPresent());
    }

    @Test
    public void getNote_validNote_returnsOptional() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Note note = new Note("");
        updatePersonDescriptor.setNote(note);
        assertTrue(updatePersonDescriptor.getNote().isPresent());
    }

    @Test
    public void setRating_nullRating_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertDoesNotThrow(() -> updatePersonDescriptor.setRating(null));
    }

    @Test
    public void setRating_validRating_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Rating rating = new Rating("5");
        updatePersonDescriptor.setRating(rating);
        assertEquals(rating, updatePersonDescriptor.getRating().get());
    }

    @Test
    public void getRating_nullRating_returnsEmptyOptional() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertFalse(updatePersonDescriptor.getRating().isPresent());
    }

    @Test
    public void getRating_validRating_returnsOptional() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Rating rating = new Rating("5");
        updatePersonDescriptor.setRating(rating);
        assertTrue(updatePersonDescriptor.getRating().isPresent());
    }

    @Test
    public void setLinks_nullLinks_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertDoesNotThrow(() -> updatePersonDescriptor.setLinks(null));
    }

    @Test
    public void setLinks_validLinks_success() throws MalformedURLException {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Set<Link> links = Set.of(new Link(new URL("https://www.google.com")));
        updatePersonDescriptor.setLinks(links);
        assertEquals(links, updatePersonDescriptor.getLinks().get());
    }

    @Test
    public void getLinks_nullLinks_returnsEmptyOptional() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertFalse(updatePersonDescriptor.getLinks().isPresent());
    }

    @Test
    public void getLinks_validLinks_returnsOptional() throws MalformedURLException {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Set<Link> links = Set.of(new Link(new URL("https://www.google.com")));
        updatePersonDescriptor.setLinks(links);
        assertTrue(updatePersonDescriptor.getLinks().isPresent());
    }

    @Test
    public void setTagTypeMap_nullTagTypeMap_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertDoesNotThrow(() -> updatePersonDescriptor.setTagTypeMap(null));
    }

    @Test
    public void setTagTypeMap_validTagTypeMap_success() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setTagTypeMap(new UniqueTagTypeMap());
        assertEquals(new UniqueTagTypeMap(), updatePersonDescriptor.getTagTypeMap().get());
    }

    @Test
    public void getTagTypeMap_nullTagTypeMap_returnsOptional() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        // By default, a tagTypeMap is created in the empty constructor, so it should not be null
        assertTrue(updatePersonDescriptor.getTagTypeMap().isPresent());
    }

    @Test
    public void getTagTypeMap_validTagTypeMap_returnsOptional() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setTagTypeMap(new UniqueTagTypeMap());
        assertTrue(updatePersonDescriptor.getTagTypeMap().isPresent());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertTrue(updatePersonDescriptor.equals(updatePersonDescriptor));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertFalse(updatePersonDescriptor.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertFalse(updatePersonDescriptor.equals(5));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        UpdatePersonDescriptor updatePersonDescriptorCopy = new UpdatePersonDescriptor();
        assertTrue(updatePersonDescriptor.equals(updatePersonDescriptorCopy));
    }

    @Test
    public void equals_differentValues_returnsFalse() throws MalformedURLException {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        UpdatePersonDescriptor updatePersonDescriptorCopy = new UpdatePersonDescriptor();

        // Different Note
        updatePersonDescriptorCopy.setNote(new Note("Test Note"));
        assertFalse(updatePersonDescriptor.equals(updatePersonDescriptorCopy));
        updatePersonDescriptorCopy = new UpdatePersonDescriptor();

        // Different Rating
        updatePersonDescriptorCopy.setRating(new Rating("5"));
        assertFalse(updatePersonDescriptor.equals(updatePersonDescriptorCopy));
        updatePersonDescriptorCopy = new UpdatePersonDescriptor();

        // Different Links
        updatePersonDescriptorCopy.setLinks(Set.of(new Link(new URL("https://www.google.com"))));
        assertFalse(updatePersonDescriptor.equals(updatePersonDescriptorCopy));
    }
}
