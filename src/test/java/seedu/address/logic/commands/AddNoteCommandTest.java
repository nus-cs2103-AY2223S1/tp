package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddNoteCommandTest {

    @Test
    public void execute_addNoteWithTag_addsTagIntoTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertFalse(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }

    // Ensure that if a Note is added with a Tag that already exists in the address book, the
    // Tag that the Note points to is the same object as the Tag that exists in the address book.
    @Test
    public void execute_addNoteWithTag_tagAlreadyExistsInTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addTag(new Tag(tagName));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromNote = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNote = listOfTagsFromNote.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNote);
    }

    // Tag being added already exists in UniqueTagMapping because a Person has it
    @Test
    public void execute_addNoteWithTag_tagAlreadyExistsInTagMappingDueToPerson() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        List<Tag> listOfTagsFromNotes = new ArrayList<>(model.getAddressBook().getNoteBook().get(0).getTags());
        Tag tagFromNotes = listOfTagsFromNotes.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromNotes);
    }

}
