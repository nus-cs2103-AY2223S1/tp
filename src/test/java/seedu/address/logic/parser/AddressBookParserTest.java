package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_Q1;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTORIAL;
import static seedu.address.testutil.TypicalQuestions.Q1;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddQCommand;
import seedu.address.logic.commands.AddResponseCommand;
import seedu.address.logic.commands.AddStuCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteQCommand;
import seedu.address.logic.commands.DeleteStuCommand;
import seedu.address.logic.commands.DeleteTutorialCommand;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindStuCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListStuCommand;
import seedu.address.logic.commands.MarkQCommand;
import seedu.address.logic.commands.MarkTutorialCommand;
import seedu.address.logic.commands.UnmarkQCommand;
import seedu.address.logic.commands.UnmarkTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuNameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;


public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_addstu() throws Exception {
        AddStuCommand command = (AddStuCommand) parser.parseCommand(
                AddStuCommand.COMMAND_WORD + " " + NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB);
        assertEquals(new AddStuCommand(BOB), command);
    }

    @Test
    public void parseCommand_editstu() throws Exception {
        Student student = new StudentBuilder().build();
        EditStuCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditStuCommand command = (EditStuCommand) parser.parseCommand(EditStuCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditStuCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_deletestu() throws Exception {
        DeleteStuCommand command = (DeleteStuCommand) parser.parseCommand(
                DeleteStuCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStuCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_findstu() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStuCommand command = (FindStuCommand) parser.parseCommand(
                FindStuCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStuCommand(new StuNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_liststu() throws Exception {
        assertTrue(parser.parseCommand(ListStuCommand.COMMAND_WORD) instanceof ListStuCommand);
        assertTrue(parser.parseCommand(ListStuCommand.COMMAND_WORD + " 3") instanceof ListStuCommand);
    }

    @Test
    public void parseCommand_addq() throws Exception {
        AddQCommand command = (AddQCommand) parser.parseCommand(
                AddQCommand.COMMAND_WORD + " " + DESCRIPTION_Q1);
        assertEquals(new AddQCommand(Q1), command);
    }

    @Test
    public void parseCommand_deleteq() throws Exception {
        DeleteQCommand command = (DeleteQCommand) parser.parseCommand(
                DeleteQCommand.COMMAND_WORD + " " + INDEX_FIRST_QUESTION.getOneBased());
        assertEquals(new DeleteQCommand(INDEX_FIRST_QUESTION), command);
    }

    @Test
    public void parseCommand_markq() throws Exception {
        MarkQCommand command = (MarkQCommand) parser.parseCommand(
                MarkQCommand.COMMAND_WORD + " " + INDEX_FIRST_QUESTION.getOneBased()
        );
        assertEquals(new MarkQCommand(INDEX_FIRST_QUESTION), command);
    }

    @Test
    public void parseCommand_unmarkq() throws Exception {
        UnmarkQCommand command = (UnmarkQCommand) parser.parseCommand(
                UnmarkQCommand.COMMAND_WORD + " " + INDEX_FIRST_QUESTION.getOneBased()
        );
        assertEquals(new UnmarkQCommand(INDEX_FIRST_QUESTION), command);
    }

    @Test
    public void parseCommand_deletetut() throws Exception {
        DeleteTutorialCommand command = (DeleteTutorialCommand) parser.parseCommand(
                DeleteTutorialCommand.COMMAND_WORD + " " + INDEX_FIRST_TUTORIAL.getOneBased());
        assertEquals(new DeleteTutorialCommand(INDEX_FIRST_TUTORIAL), command);
    }

    @Test
    public void parseCommand_addresponse() throws Exception {
        AddResponseCommand command = (AddResponseCommand) parser.parseCommand(
            AddResponseCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased()
            + " m/7"
        );
        assertEquals(new AddResponseCommand(INDEX_FIRST_STUDENT, new Response("7")), command);
    }

    @Test
    public void parseCommand_marktut() throws Exception {
        MarkTutorialCommand command = (MarkTutorialCommand) parser.parseCommand(
            MarkTutorialCommand.COMMAND_WORD + " " + INDEX_FIRST_TUTORIAL.getOneBased()
        );
        assertEquals(new MarkTutorialCommand(INDEX_FIRST_TUTORIAL), command);

    }

    @Test
    public void parseCommand_unmarktut() throws Exception {
        UnmarkTutorialCommand command = (UnmarkTutorialCommand) parser.parseCommand(
            UnmarkTutorialCommand.COMMAND_WORD + " " + INDEX_FIRST_TUTORIAL.getOneBased()
        );
        assertEquals(new UnmarkTutorialCommand(INDEX_FIRST_TUTORIAL), command);

    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
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
