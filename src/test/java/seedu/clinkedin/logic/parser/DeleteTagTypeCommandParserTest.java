package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.CreateTagTypeCommand;
import seedu.clinkedin.logic.commands.DeleteTagTypeCommand;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.tag.TagType;

public class DeleteTagTypeCommandParserTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final DeleteTagTypeCommandParser parser = new DeleteTagTypeCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void stringLengthIsZero() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagTypeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs() throws CommandException {
        Prefix oldPrefix = new Prefix("oldt/");
        TagType oldTag = new TagType("OldTag", oldPrefix);
        CreateTagTypeCommand cmd = new CreateTagTypeCommand(oldTag, oldPrefix);
        cmd.execute(model);
        DeleteTagTypeCommand expected = new DeleteTagTypeCommand(oldTag);
        assertParseSuccess(parser, "OldTag", expected);
    }

}
