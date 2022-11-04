package seedu.address.logic.parser;


import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.*;
import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.Address;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.testutil.TypicalIndexes.*;

public class AddressBookParserTest {
    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }


    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand("t" + " " +
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        DeleteTaskCommand commandTwo = (DeleteTaskCommand) parser.parseCommand("T" + " " +
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_TASK), command);
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_TASK), commandTwo);
    }



    @Test
    public void parseCommand_editTask() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand("t" + " "
                + EditTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + " "
                + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST_TASK, descriptor), command);
    }

    @Test
    public void parseCommand_listTasks() throws Exception {
        assertTrue(parser.parseCommand("t" + " " + ListTasksCommand.COMMAND_WORD) instanceof ListTasksCommand);
        assertTrue(parser.parseCommand("t" + " " + ListTasksCommand.COMMAND_WORD + " 3") instanceof ListTasksCommand);
    }


//    @Test
//    public void parseCommand_sortTasks() throws Exception {
//        SortTaskCommand sortTaskCommand = (SortTaskCommand) parser.parseCommand("t" + " " +
//                SortTaskCommand.COMMAND_WORD + " " + "c/priority" );
//        assertEquals(new SortTaskCommand(new Criteria("priority")), sortTaskCommand);
//        SortTaskCommand sortTaskCommandTwo = (SortTaskCommand) parser.parseCommand("t" + " " +
//                SortTaskCommand.COMMAND_WORD + " " + "c/deadline ");
//        assertEquals(new SortTaskCommand(new Criteria("deadline")), sortTaskCommandTwo);
//
//    }

    @Test
    public void parseCommand_findTasks() throws Exception {
        FindTasksCommand command = (FindTasksCommand) parser.parseCommand("t " +
                FindTasksCommand.COMMAND_WORD + " " + "homework");
        assertEquals(new FindTasksCommand(new DescriptionContainsKeywordsPredicate(
                Collections.singletonList("homework"))), command);
    }

    @Test
    public void parseCommand_markTask() throws Exception {
        MarkCommand command = (MarkCommand) parser.parseCommand("t " +
                MarkCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new MarkCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_unmarkTask() throws Exception {
        UnmarkCommand command = (UnmarkCommand) parser.parseCommand("t " +
                UnmarkCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new UnmarkCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_clearTasks() throws Exception {
        assertTrue(parser.parseCommand("t" + " " + ClearTasksCommand.COMMAND_WORD) instanceof ClearTasksCommand);
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
    public void parseCommand_clearAll() throws Exception {
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD) instanceof ClearAllCommand);
        assertTrue(parser.parseCommand(ClearAllCommand.COMMAND_WORD + " 3") instanceof ClearAllCommand);
    }

    @Test
    public void parseCommand_addTag() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        AddTagCommand command = (AddTagCommand) parser.parseCommand("t " + AddTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_PRIORITY_STATUS + "HIGH" );
        assertEquals(new AddTagCommand(new PriorityTag("High"), null, INDEX_FIRST_TASK), command);

        AddTagCommand commandTwo = (AddTagCommand) parser.parseCommand("t " + AddTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_PRIORITY_STATUS + "low" );
        assertEquals(new AddTagCommand(new PriorityTag("Low"), null, INDEX_FIRST_TASK), commandTwo);
        AddTagCommand commandThree = (AddTagCommand) parser.parseCommand("t " + AddTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_PRIORITY_STATUS + "HIGH " + PREFIX_DEADLINE + "20-08-2023");
        assertEquals(new AddTagCommand(new PriorityTag("High"),
                new DeadlineTag(LocalDate.parse("20-08-2023", dateTimeFormatter)), INDEX_FIRST_TASK), commandThree);
        AddTagCommand commandFour = (AddTagCommand) parser.parseCommand("t " + AddTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_DEADLINE + "20-08-2023");
        assertEquals(new AddTagCommand(null, new DeadlineTag(LocalDate.parse("20-08-2023", dateTimeFormatter)),
                INDEX_FIRST_TASK), commandFour);
    }

    @Test
    public void parseCommand_editTag() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        EditTagCommand command = (EditTagCommand) parser.parseCommand("t " + EditTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_PRIORITY_STATUS + "HIGH" );
        assertEquals(new EditTagCommand(INDEX_FIRST_TASK, new PriorityTag("High"), null), command);

        EditTagCommand commandTwo = (EditTagCommand) parser.parseCommand("t " + EditTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_PRIORITY_STATUS + "low" );
        assertEquals(new EditTagCommand(INDEX_FIRST_TASK, new PriorityTag("Low"), null), commandTwo);

        EditTagCommand commandThree = (EditTagCommand) parser.parseCommand("t " + EditTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_PRIORITY_STATUS + "HIGH " + PREFIX_DEADLINE + "20-08-2023");
        assertEquals(new EditTagCommand(INDEX_FIRST_TASK, new PriorityTag("High"),
                new DeadlineTag(LocalDate.parse("20-08-2023", dateTimeFormatter))), commandThree);
        EditTagCommand commandFour = (EditTagCommand) parser.parseCommand("t " + EditTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_DEADLINE + "20-08-2023");
        assertEquals(new EditTagCommand(INDEX_FIRST_TASK, null, new DeadlineTag(LocalDate.parse("20-08-2023", dateTimeFormatter))
                ), commandFour);
    }

    @Test
    public void parseCommand_deleteTag() throws Exception {
        DeleteTagCommand command = (DeleteTagCommand) parser.parseCommand("t " +
                DeleteTagCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_TAG + "deadline");
        assertEquals(new DeleteTagCommand(INDEX_FIRST_TASK, Set.of("deadline")), command);

        DeleteTagCommand commandTwo = (DeleteTagCommand) parser.parseCommand("t " +
                DeleteTagCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_TAG + "priority");
        assertEquals(new DeleteTagCommand(INDEX_FIRST_TASK, Set.of("priority")), commandTwo);

        DeleteTagCommand commandThree = (DeleteTagCommand) parser.parseCommand("t " +
                DeleteTagCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased() + " " + PREFIX_TAG + "priority " + "deadline");
        assertEquals(new DeleteTagCommand(INDEX_FIRST_TASK, Set.of("deadline", "priority")), commandThree);

    }

    //m add and edit

    @Test
    public void parseCommand_deleteModule() throws Exception {
        DeleteModuleCommand command = (DeleteModuleCommand) parser.parseCommand("m" + " " +
                DeleteModuleCommand.COMMAND_WORD + " " + INDEX_THIRD_MODULE.getOneBased());
        DeleteModuleCommand commandTwo = (DeleteModuleCommand) parser.parseCommand("M" + " " +
                DeleteModuleCommand.COMMAND_WORD + " " + INDEX_THIRD_MODULE.getOneBased());
        assertEquals(new DeleteModuleCommand(INDEX_THIRD_MODULE), command);
        assertEquals(new DeleteModuleCommand(INDEX_THIRD_MODULE), commandTwo);
    }

    @Test
    public void parseCommand_listModules() throws Exception {
        assertTrue(parser.parseCommand("m" + " " + ListModulesCommand.COMMAND_WORD) instanceof ListModulesCommand);
        assertTrue(parser.parseCommand("m" + " " + ListModulesCommand.COMMAND_WORD + " 3") instanceof ListModulesCommand);
    }

    @Test
    public void parseCommand_findModules() throws Exception {
        FindModulesCommand command = (FindModulesCommand) parser.parseCommand("m " +
                FindModulesCommand.COMMAND_WORD + " " + "cs2030s");
        assertEquals(new FindModulesCommand(new ModuleCodeContainsKeywordsPredicate(
                Collections.singletonList("cs2030s"))), command);
    }

    @Test
    public void parseCommand_addExam() throws Exception {
        Exam exam = new ExamBuilder().build();
        AddExamCommand command = (AddExamCommand) parser.parseCommand(ExamUtil.getAddExamCommand(exam));
        assertEquals(new AddExamCommand(exam), command);
    }

    @Test
    public void parseCommand_editExam() throws Exception {
        Exam exam = new ExamBuilder().build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand command = (EditExamCommand) parser.parseCommand("e" + " "
                + EditExamCommand.COMMAND_WORD + " " + INDEX_SECOND_EXAM.getOneBased() + " "
                + ExamUtil.getEditExamDescriptorDetails(descriptor));
        assertEquals(new EditExamCommand(INDEX_SECOND_EXAM, descriptor), command);
    }

    @Test
    public void parseCommand_deleteExam() throws Exception {
        DeleteExamCommand command = (DeleteExamCommand) parser.parseCommand("e " +
                DeleteExamCommand.COMMAND_WORD + " " + INDEX_FIRST_EXAM.getOneBased());
        DeleteExamCommand commandTwo = (DeleteExamCommand) parser.parseCommand("E" + " " +
                DeleteExamCommand.COMMAND_WORD + " " + INDEX_FIRST_EXAM.getOneBased());
        assertEquals(new DeleteExamCommand(INDEX_FIRST_EXAM), command);
        assertEquals(new DeleteExamCommand(INDEX_FIRST_EXAM), commandTwo);
    }

    @Test
    public void parseCommand_linkExam() throws Exception {
        LinkExamCommand command = (LinkExamCommand) parser.parseCommand("e " +
                LinkExamCommand.COMMAND_WORD + " " + PREFIX_EXAM_INDEX + INDEX_FIRST_EXAM.getOneBased() + " "
                + PREFIX_TASK_INDEX + INDEX_FIRST_TASK.getOneBased());
       assertEquals(new LinkExamCommand(INDEX_FIRST_EXAM, INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_unlinkExam() throws Exception {
        UnlinkExamCommand command = (UnlinkExamCommand) parser.parseCommand("e " +
                UnlinkExamCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new UnlinkExamCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_showT() throws Exception {
        ListExamTasksCommand command = (ListExamTasksCommand) parser.parseCommand("e " +
                ListExamTasksCommand.COMMAND_WORD + " " + INDEX_FIRST_EXAM.getOneBased());
        assertEquals(new ListExamTasksCommand(INDEX_FIRST_EXAM), command);
    }

    //filter and sort


//
//    @Test
//    public void parseCommand_unrecognisedInput_throwsParseException() {
//        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
//            -> parser.parseCommand(""));
//    }
//
//    @Test
//    public void parseCommand_unknownCommand_throwsParseException() {
//        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
//    }

}
