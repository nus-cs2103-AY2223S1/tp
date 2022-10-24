package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.EditNoteCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_editNoteWithTag_addsTagIntoTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addNote(new NoteBuilder().build());

        assertFalse(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }

    // Ensure that if a Note is edited with a Tag that already exists in the address book, the
    // Tag that the Note points to is the same object as the Tag that exists in the address book.
    @Test
    public void execute_editNoteWithTag_tagAlreadyExistsInTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addNote(new NoteBuilder().build());
        model.addTag(new Tag(tagName));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromNote = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNote = listOfTagsFromNote.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNote);
    }

    @Test
    public void execute_editNoteWithTag_deletesTagFromTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_NOTES_TAG + " ")
                .execute(model));

        assertFalse(model.getTagMapping().containsKey(tagName));
    }

    // Tag being added already exists in UniqueTagMapping because a Person has it
    @Test
    public void execute_editNoteWithTag_tagAlreadyExistsInTagMappingDueToPerson() throws Exception {
        Model model = new ModelManager();
        model.addNote(new NoteBuilder().build());
        String tagName = "Operations";

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromNote = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNote = listOfTagsFromNote.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNote);
    }

    // Tag is not deleted from UniqueTagMapping when there still exists a Person with that Tag
    @Test
    public void execute_editNoteWithTag_doesNotDeletesTagFromTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditNoteCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_NOTES_TAG + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }

}
