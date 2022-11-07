package seedu.classify.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classify.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.classify.logic.commands.AddStudentCommand;
import seedu.classify.logic.commands.ClearCommand;
import seedu.classify.logic.commands.DeleteCommand;
import seedu.classify.logic.commands.EditCommand;
import seedu.classify.logic.commands.ExitCommand;
import seedu.classify.logic.commands.FindCommand;
import seedu.classify.logic.commands.HelpCommand;
import seedu.classify.logic.commands.ToggleViewCommand;
import seedu.classify.logic.commands.ViewAllCommand;
import seedu.classify.logic.commands.ViewClassCommand;
import seedu.classify.logic.commands.ViewStatsCommand;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.IdPredicate;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.NameContainsKeywordsPredicate;
import seedu.classify.model.student.NamePredicate;
import seedu.classify.model.student.Student;
import seedu.classify.testutil.EditStudentDescriptorBuilder;
import seedu.classify.testutil.StudentBuilder;
import seedu.classify.testutil.StudentUtil;

public class StudentRecordParserTest {

    private final StudentRecordParser parser = new StudentRecordParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student person = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddCommand(person));
        assertEquals(new AddStudentCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Student student = new StudentBuilder().build();
        Id studentId = student.getId();
        Name studentName = student.getStudentName();

        DeleteCommand idCommand = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " id/" + studentId);
        assertEquals(new DeleteCommand(studentId, new IdPredicate(studentId)), idCommand);

        DeleteCommand nameCommand = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " nm/" + studentName);
        assertEquals(new DeleteCommand(studentName, new NamePredicate(studentName)), nameCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student person = new StudentBuilder().withExams("SA1 20").build();
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findByName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " nm/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findById() throws Exception {
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD + " id/ 123a");
        assertEquals(new FindCommand(new IdPredicate(new Id("123A"))), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_viewAll() throws Exception {
        assertTrue(parser.parseCommand(ViewAllCommand.COMMAND_WORD) instanceof ViewAllCommand);
        assertTrue(parser.parseCommand(ViewAllCommand.COMMAND_WORD + " 3") instanceof ViewAllCommand);
    }

    @Test
    public void parseCommand_toggleView() throws Exception {
        assertTrue(parser.parseCommand(ToggleViewCommand.COMMAND_WORD) instanceof ToggleViewCommand);
        assertTrue(parser.parseCommand(ToggleViewCommand.COMMAND_WORD + " 3") instanceof ToggleViewCommand);
    }

    @Test
    public void parseCommand_viewClass() throws Exception {
        ViewClassCommand viewClassCommand = (ViewClassCommand) parser.parseCommand(
                ViewClassCommand.COMMAND_WORD + " 3A");
        assertEquals(new ViewClassCommand(new ClassPredicate(new Class("3A"))), viewClassCommand);
    }

    @Test
    public void parseCommand_viewStats() throws Exception {
        ViewStatsCommand viewStatsCommand = (ViewStatsCommand) parser.parseCommand(
                ViewStatsCommand.COMMAND_WORD + " class/4a exam/ca1 filter/off");
        assertEquals(new ViewStatsCommand(new Class("4A"), "CA1", false), viewStatsCommand);
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
