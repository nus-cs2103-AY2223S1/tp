package seedu.masslinkers.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_MOD_CS2100;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.logic.commands.AddCommand;
import seedu.masslinkers.logic.commands.ClearCommand;
import seedu.masslinkers.logic.commands.DeleteCommand;
import seedu.masslinkers.logic.commands.EditCommand;
import seedu.masslinkers.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.masslinkers.logic.commands.ExitCommand;
import seedu.masslinkers.logic.commands.FindCommand;
import seedu.masslinkers.logic.commands.HelpCommand;
import seedu.masslinkers.logic.commands.ListCommand;
import seedu.masslinkers.logic.commands.ModAddCommand;
import seedu.masslinkers.logic.commands.ModCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.student.DetailsContainsKeywordsPredicate;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.testutil.EditStudentDescriptorBuilder;
import seedu.masslinkers.testutil.StudentBuilder;
import seedu.masslinkers.testutil.StudentUtil;

//@@author
public class MassLinkersParserTest {

    private final MassLinkersParser parser = new MassLinkersParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new DetailsContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_mod() throws Exception {
        assertTrue(parser.parseCommand(
                ModCommand.COMMAND_WORD + " " + ModAddCommand.COMMAND_WORD
                        + " " + INDEX_FIRST_STUDENT.getOneBased() + " " + VALID_MOD_CS2100) instanceof ModAddCommand);
        // TODO: Update to include other mod commands
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
