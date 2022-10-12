package seedu.phu.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.AddCommand;
import seedu.phu.logic.commands.ClearCommand;
import seedu.phu.logic.commands.DeleteCommand;
import seedu.phu.logic.commands.EditCommand;
import seedu.phu.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.phu.logic.commands.ExitCommand;
// import seedu.internship.logic.commands.FindCommand;s
import seedu.phu.logic.commands.HelpCommand;
import seedu.phu.logic.commands.ListCommand;
import seedu.phu.logic.parser.exceptions.ParseException;
// import seedu.internship.model.internship.NameContainsKeywordsPredicate;
import seedu.phu.model.internship.Internship;
import seedu.phu.testutil.EditInternshipDescriptorBuilder;
import seedu.phu.testutil.InternshipBuilder;
import seedu.phu.testutil.InternshipUtil;

public class InternshipBookParserTest {

    private final InternshipBookParser parser = new InternshipBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Internship internship = new InternshipBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(InternshipUtil.getAddCommand(internship));
        assertEquals(new AddCommand(internship), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new DeleteCommand(INDEXES_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Internship internship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(internship).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_INTERNSHIP.getOneBased() + " "
                + InternshipUtil.getEditInternshipDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_INTERNSHIP, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }
    /*
//    @Test
//    public void parseCommand_find() throws Exception {
//        List<String> keywords = Arrays.asList("foo", "bar", "baz");
//        FindCommand command = (FindCommand) parser.parseCommand(
//                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
//        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
//    }
    */

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " true") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
