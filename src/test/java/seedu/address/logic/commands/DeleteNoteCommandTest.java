package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.DeleteNoteCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.PersonBuilder;

public class DeleteNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_removeLastNoteFromModel_removesTagInMapping() {
        Model model = new ModelManager();
        String tagName = "TagRemovedOnLastNote";
        String titleA = "noteA";
        String titleB = "noteB";

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + titleA + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + titleB + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        assertAll(() -> new DeleteNoteCommandParser().parse("2").execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new DeleteNoteCommandParser().parse("1").execute(model));

        assertFalse(model.getTagMapping().containsKey(tagName));
    }

    // Tag is not deleted from UniqueTagMapping when there still exists a Person with that Tag
    @Test
    public void execute_deleteNoteWithTag_doesNotDeletesTagFromTagMapping() {
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

        assertAll(() -> new DeleteNoteCommandParser().parse("1").execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }
}
