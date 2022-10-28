package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_PROJECT_STATUS;
import static jeryl.fyp.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.AddStudentCommand;
import jeryl.fyp.logic.commands.ClearCommand;
import jeryl.fyp.logic.commands.DeleteStudentCommand;
import jeryl.fyp.logic.commands.EditCommand;
import jeryl.fyp.logic.commands.EditCommand.EditStudentDescriptor;
import jeryl.fyp.logic.commands.ExitCommand;
import jeryl.fyp.logic.commands.FindProjectNameCommand;
import jeryl.fyp.logic.commands.FindTagsCommand;
import jeryl.fyp.logic.commands.HelpCommand;
import jeryl.fyp.logic.commands.ListCommand;
import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.commands.SortProjectNameCommand;
import jeryl.fyp.logic.commands.SortProjectStatusCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.ProjectNameContainsKeywordsPredicate;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.TagsContainKeywordsPredicate;
import jeryl.fyp.testutil.EditStudentDescriptorBuilder;
import jeryl.fyp.testutil.StudentBuilder;
import jeryl.fyp.testutil.StudentUtil;


public class FypManagerParserTest {

    private final FypManagerParser parser = new FypManagerParser();

    @Test
    public void parseCommand_addStudent() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddStudentCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        Student student = new StudentBuilder().build();
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                StudentUtil.getDeleteStudentCommand(student));
        assertEquals(new DeleteStudentCommand(student.getStudentId()), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + student.getStudentId() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(student.getStudentId(), descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findProjectName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindProjectNameCommand command = (FindProjectNameCommand) parser.parseCommand(
                FindProjectNameCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining("/")));
        assertEquals(new FindProjectNameCommand(new ProjectNameContainsKeywordsPredicate(keywords)), command);
        command = (FindProjectNameCommand) parser.parseCommand(FindProjectNameCommand.ALTERNATIVE_COMMAND_WORD
                + " " + keywords.stream().collect(Collectors.joining("/")));
        assertEquals(new FindProjectNameCommand(new ProjectNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTags() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTagsCommand command = (FindTagsCommand) parser.parseCommand(
                FindTagsCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining("/")));
        assertEquals(new FindTagsCommand(new TagsContainKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(
                HelpCommand.COMMAND_WORD + " " + DeleteStudentCommand.COMMAND_WORD) instanceof HelpCommand);
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
    public void parseCommand_mark() throws Exception {
        final StudentId studentId = new StudentId("A0123456G");
        final ProjectStatus projectStatus = new ProjectStatus("DONE");
        MarkCommand command = (MarkCommand) parser.parseCommand(MarkCommand.COMMAND_WORD + " "
                + PREFIX_STUDENT_ID + studentId + " " + PREFIX_PROJECT_STATUS + projectStatus);
        assertEquals(new MarkCommand(studentId, projectStatus), command);
    }

    @Test
    public void parseCommand_sortProjectStatus() throws Exception {
        assertTrue(parser.parseCommand(SortProjectStatusCommand.COMMAND_WORD) instanceof SortProjectStatusCommand);
        assertTrue(parser.parseCommand(SortProjectStatusCommand.COMMAND_WORD + " 3")
                instanceof SortProjectStatusCommand);
    }

    @Test
    public void parseCommand_sortProjectName() throws Exception {
        assertTrue(parser.parseCommand(SortProjectNameCommand.COMMAND_WORD) instanceof SortProjectNameCommand);
        assertTrue(parser.parseCommand(SortProjectNameCommand.COMMAND_WORD + " 3")
                instanceof SortProjectNameCommand);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
