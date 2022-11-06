package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTE;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.AddNoteCommand;
import jarvis.logic.commands.AddParticipationCommand;
import jarvis.logic.commands.AddStudentCommand;
import jarvis.logic.commands.AddTaskCommand;
import jarvis.logic.commands.ClearCommand;
import jarvis.logic.commands.DeleteNoteCommand;
import jarvis.logic.commands.DeleteStudentCommand;
import jarvis.logic.commands.EditStudentCommand;
import jarvis.logic.commands.EditStudentCommand.EditStudentDescriptor;
import jarvis.logic.commands.ExitCommand;
import jarvis.logic.commands.FindStudentCommand;
import jarvis.logic.commands.HelpCommand;
import jarvis.logic.commands.ListStudentCommand;
import jarvis.logic.commands.MarkLessonCommand;
import jarvis.logic.commands.MarkStudentCommand;
import jarvis.logic.commands.MarkTaskCommand;
import jarvis.logic.commands.UnmarkLessonCommand;
import jarvis.logic.commands.UnmarkStudentCommand;
import jarvis.logic.commands.UnmarkTaskCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.NameContainsKeywordsPredicate;
import jarvis.model.Student;
import jarvis.model.Task;
import jarvis.testutil.EditStudentDescriptorBuilder;
import jarvis.testutil.StudentBuilder;
import jarvis.testutil.StudentUtil;
import jarvis.testutil.TaskBuilder;

public class StudentBookParserTest {

    private final JarvisParser parser = new JarvisParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        String taskDesc = "Test description";
        Task task = new TaskBuilder().withDesc(taskDesc).withoutDeadline().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(AddTaskCommand.COMMAND_WORD
                + " " + PREFIX_TASK_DESC + taskDesc);
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_addParticipation() throws Exception {
        AddParticipationCommand command = (AddParticipationCommand) parser.parseCommand(
                AddParticipationCommand.COMMAND_WORD
                + " " + PREFIX_PARTICIPATION + 100
                + " " + PREFIX_STUDENT_INDEX + INDEX_FIRST.getOneBased()
                + " " + PREFIX_LESSON_INDEX + INDEX_FIRST.getOneBased());
        assertEquals(new AddParticipationCommand(100, INDEX_FIRST, INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_addNote() throws Exception {
        AddNoteCommand command = (AddNoteCommand) parser.parseCommand(
                AddNoteCommand.COMMAND_WORD
                + " " + PREFIX_STUDENT_INDEX + INDEX_FIRST.getOneBased()
                + " " + PREFIX_LESSON_INDEX + INDEX_FIRST.getOneBased()
                + " " + PREFIX_NOTE + "Test");
        assertEquals(new AddNoteCommand(INDEX_FIRST, INDEX_FIRST, "Test"), command);
    }

    @Test
    public void parseCommand_deleteNote() throws Exception {
        DeleteNoteCommand command = (DeleteNoteCommand) parser.parseCommand(
                DeleteNoteCommand.COMMAND_WORD
                + " " + PREFIX_NOTE_INDEX + INDEX_FIRST.getOneBased()
                + " " + PREFIX_STUDENT_INDEX + INDEX_FIRST.getOneBased()
                + " " + PREFIX_LESSON_INDEX + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteNoteCommand(INDEX_FIRST, INDEX_FIRST, INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_unmarkTask() throws Exception {
        UnmarkTaskCommand command = (UnmarkTaskCommand) parser.parseCommand(
                UnmarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new UnmarkTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_markTask() throws Exception {
        MarkTaskCommand command = (MarkTaskCommand) parser.parseCommand(
                MarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new MarkTaskCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_markLesson() throws Exception {
        MarkLessonCommand command = (MarkLessonCommand) parser.parseCommand(
                MarkLessonCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new MarkLessonCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_unmarkLesson() throws Exception {
        UnmarkLessonCommand command = (UnmarkLessonCommand) parser.parseCommand(
                UnmarkLessonCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new UnmarkLessonCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_markStudent() throws Exception {
        MarkStudentCommand command = (MarkStudentCommand) parser.parseCommand(
                MarkStudentCommand.COMMAND_WORD
                + " " + PREFIX_STUDENT_INDEX + INDEX_FIRST.getOneBased()
                + " " + PREFIX_LESSON_INDEX + INDEX_FIRST.getOneBased());
        assertEquals(new MarkStudentCommand(INDEX_FIRST, INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_unmarkStudent() throws Exception {
        UnmarkStudentCommand command = (UnmarkStudentCommand) parser.parseCommand(
                UnmarkStudentCommand.COMMAND_WORD
                + " " + PREFIX_STUDENT_INDEX + INDEX_FIRST.getOneBased()
                + " " + PREFIX_LESSON_INDEX + INDEX_FIRST.getOneBased());
        assertEquals(new UnmarkStudentCommand(INDEX_FIRST, INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditStudentCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(
                FindStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3") instanceof ListStudentCommand);
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
