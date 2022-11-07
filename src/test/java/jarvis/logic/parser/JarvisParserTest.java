package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jarvis.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.CONSULT_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.CONSULT_STUDENTS;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalLessons.STUDIO_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.TP1;
import static jarvis.testutil.TypicalLessons.TP2;
import static jarvis.testutil.TypicalLessons.TP3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.AddConsultCommand;
import jarvis.logic.commands.AddLessonCommandTest;
import jarvis.logic.commands.AddMasteryCheckCommand;
import jarvis.logic.commands.AddStudentCommand;
import jarvis.logic.commands.AddStudioCommand;
import jarvis.logic.commands.AddTaskCommand;
import jarvis.logic.commands.ClearCommand;
import jarvis.logic.commands.DeleteLessonCommand;
import jarvis.logic.commands.DeleteStudentCommand;
import jarvis.logic.commands.DeleteTaskCommand;
import jarvis.logic.commands.EditStudentCommand;
import jarvis.logic.commands.EditStudentCommand.EditStudentDescriptor;
import jarvis.logic.commands.ExitCommand;
import jarvis.logic.commands.FindStudentCommand;
import jarvis.logic.commands.HelpCommand;
import jarvis.logic.commands.ListAllCommand;
import jarvis.logic.commands.ListLessonCommand;
import jarvis.logic.commands.ListStudentCommand;
import jarvis.logic.commands.ListTaskCommand;
import jarvis.logic.commands.MarkLessonCommand;
import jarvis.logic.commands.MarkTaskCommand;
import jarvis.logic.commands.UnmarkLessonCommand;
import jarvis.logic.commands.UnmarkTaskCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.Consult;
import jarvis.model.MasteryCheck;
import jarvis.model.NameContainsKeywordsPredicate;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.Task;
import jarvis.testutil.EditStudentDescriptorBuilder;
import jarvis.testutil.LessonBuilder;
import jarvis.testutil.LessonUtil;
import jarvis.testutil.StudentBuilder;
import jarvis.testutil.StudentUtil;
import jarvis.testutil.TaskBuilder;
import jarvis.testutil.TaskUtil;

public class JarvisParserTest {

    private final JarvisParser parser = new JarvisParser();

    @Test
    public void parseCommand_addStudent() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddStudentCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_addStudio() throws Exception {
        Studio studio = new LessonBuilder(STUDIO_1).buildStudio();
        AddStudioCommand command = (AddStudioCommand) parser.parseCommand(LessonUtil.getAddStudioCommand(studio));
        assertEquals(new AddStudioCommand(STUDIO_DESCRIPTION_1, TP2), command);
    }

    @Test
    public void parseCommand_addMasteryCheck() throws Exception {
        // create a MasteryCheck for 1st student in typical student list
        MasteryCheck mc = new LessonBuilder(MC_1).withStudents(CONSULT_STUDENTS).buildMasteryCheck();
        AddMasteryCheckCommand command = (AddMasteryCheckCommand) parser.parseCommand(
                LessonUtil.getPartialAddMasteryCheckCommand(mc) + " " + "si/1");
        assertEquals(new AddMasteryCheckCommand(MASTERY_CHECK_DESCRIPTION_1,
                TP1, AddLessonCommandTest.getFirstStudentIndex()), command);
    }

    @Test
    public void parseCommand_addConsult() throws Exception {
        // create a Consult for 1st in typical student list
        Consult consult = new LessonBuilder(CONSULT_1).withStudents(CONSULT_STUDENTS).buildConsult();
        AddConsultCommand command = (AddConsultCommand) parser.parseCommand(
                LessonUtil.getPartialAddConsultCommand(consult) + " " + "si/1");
        assertEquals(new AddConsultCommand(CONSULT_DESCRIPTION_1,
                TP3, AddLessonCommandTest.getFirstStudentIndex()), command);
    }

    @Test
    public void parseCommand_editStudent() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditStudentCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_deleteStudent() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_deleteLesson() throws Exception {
        DeleteLessonCommand command = (DeleteLessonCommand) parser.parseCommand(
                DeleteLessonCommand.COMMAND_WORD + " " + INDEX_FIRST_LESSON.getOneBased());
        assertEquals(new DeleteLessonCommand(INDEX_FIRST_LESSON), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_findStudent() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(
                FindStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listStudent() throws Exception {
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD) instanceof ListStudentCommand);
        assertTrue(parser.parseCommand(ListStudentCommand.COMMAND_WORD + " 3") instanceof ListStudentCommand);
    }

    @Test
    public void parseCommand_listTask() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD + " random") instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_listLesson() throws Exception {
        assertTrue(parser.parseCommand(ListLessonCommand.COMMAND_WORD) instanceof ListLessonCommand);
        assertTrue(parser.parseCommand(ListLessonCommand.COMMAND_WORD + " /s") instanceof ListLessonCommand);
    }

    @Test
    public void parseCommand_listAll() throws Exception {
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD + " random") instanceof ListAllCommand);
    }

    @Test
    public void parseCommand_markTask() throws Exception {
        MarkTaskCommand command = (MarkTaskCommand) parser.parseCommand(
                MarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new MarkTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_markLesson() throws Exception {
        MarkLessonCommand command = (MarkLessonCommand) parser.parseCommand(
                MarkLessonCommand.COMMAND_WORD + " " + INDEX_FIRST_LESSON.getOneBased());
        assertEquals(new MarkLessonCommand(INDEX_FIRST_LESSON), command);
    }

    @Test
    public void parseCommand_unmarkTask() throws Exception {
        UnmarkTaskCommand command = (UnmarkTaskCommand) parser.parseCommand(
                UnmarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new UnmarkTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_unmarkLesson() throws Exception {
        UnmarkLessonCommand command = (UnmarkLessonCommand) parser.parseCommand(
                UnmarkLessonCommand.COMMAND_WORD + " " + INDEX_FIRST_LESSON.getOneBased());
        assertEquals(new UnmarkLessonCommand(INDEX_FIRST_LESSON), command);
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
