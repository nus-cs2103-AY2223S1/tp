package seedu.studmap.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.studmap.testutil.Assert.assertThrows;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.logic.commands.AddCommand;
import seedu.studmap.logic.commands.ClearCommand;
import seedu.studmap.logic.commands.DeleteCommand;
import seedu.studmap.logic.commands.EditCommand;
import seedu.studmap.logic.commands.ExitCommand;
import seedu.studmap.logic.commands.FindCommand;
import seedu.studmap.logic.commands.HelpCommand;
import seedu.studmap.logic.commands.ListCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.NameContainsKeywordsPredicate;
import seedu.studmap.model.student.Student;
import seedu.studmap.testutil.EditStudentDescriptorBuilder;
import seedu.studmap.testutil.StudentBuilder;
import seedu.studmap.testutil.StudentUtil;

public class StudMapParserTest {

    private final StudMapParser parser = new StudMapParser();

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
        assertEquals(new DeleteCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditCommand.EditCommandStudentEditor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditstudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), descriptor),
                command);
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
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
