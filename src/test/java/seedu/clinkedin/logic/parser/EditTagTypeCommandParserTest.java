package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.CreateTagTypeCommand;
import seedu.clinkedin.logic.commands.EditTagTypeCommand;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.tag.TagType;

public class EditTagTypeCommandParserTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final EditTagTypeCommandParser parser = new EditTagTypeCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void stringLengthIsZero() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagTypeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Only tag type
        assertParseFailure(parser, "GPA-Grade",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagTypeCommand.MESSAGE_USAGE));

        // Only tag alias
        assertParseFailure(parser, "gpat-grdt", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditTagTypeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs() throws CommandException {
        //
        Prefix oldPrefix = new Prefix("firstt/");
        TagType oldTag = new TagType("FirstTag", oldPrefix);
        Prefix newPrefix = new Prefix("secondt/");
        TagType newTag = new TagType("SecondTag", newPrefix);
        CreateTagTypeCommand cmd = new CreateTagTypeCommand(oldTag, oldPrefix);
        cmd.execute(model);
        EditTagTypeCommand expected = new EditTagTypeCommand(oldPrefix, oldTag, newPrefix, newTag);
        assertParseSuccess(parser, "FirstTag-SecondTag firstt-secondt", expected);
    }

}
