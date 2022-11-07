package seedu.watson.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.watson.testutil.Assert.assertThrows;
import static seedu.watson.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.AddCommand;
import seedu.watson.logic.commands.ClearCommand;
import seedu.watson.logic.commands.DeleteCommand;
import seedu.watson.logic.commands.EditCommand;
import seedu.watson.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.watson.logic.commands.ExitCommand;
import seedu.watson.logic.commands.HelpCommand;
import seedu.watson.logic.commands.ListCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.Student;
import seedu.watson.testutil.EditStudentDescriptorBuilder;
import seedu.watson.testutil.StudentBuilder;
import seedu.watson.testutil.StudentUtil;

public class DatabaseParserTest {

    private final DatabaseParser parser = new DatabaseParser();

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
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                                                                + INDEX_FIRST_PERSON.getOneBased() + " "
                                                                + StudentUtil.getEditPersonDescriptorDetails(
            descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    //    @Test
    //    public void parseCommand_find() throws Exception {
    //        List<String> keywords = Arrays.asList("foo", "bar", "baz");
    //        FindCommand command = (FindCommand) parser.parseCommand(
    //            FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
    //        assertEquals(new FindCommand(new FindCommandPredicate(keywords)), command);
    //    }

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
