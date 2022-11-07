package seedu.intrack.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.AddCommand;
import seedu.intrack.logic.commands.AddTaskCommand;
import seedu.intrack.logic.commands.ClearCommand;
import seedu.intrack.logic.commands.DeleteCommand;
import seedu.intrack.logic.commands.DeleteTaskCommand;
import seedu.intrack.logic.commands.EditCommand;
import seedu.intrack.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.intrack.logic.commands.ExitCommand;
import seedu.intrack.logic.commands.FilterCommand;
import seedu.intrack.logic.commands.FindNameCommand;
import seedu.intrack.logic.commands.FindPositionCommand;
import seedu.intrack.logic.commands.FindTagCommand;
import seedu.intrack.logic.commands.HelpCommand;
import seedu.intrack.logic.commands.ListCommand;
import seedu.intrack.logic.commands.MailCommand;
import seedu.intrack.logic.commands.RemarkCommand;
import seedu.intrack.logic.commands.SelectCommand;
import seedu.intrack.logic.commands.SortSalaryCommand;
import seedu.intrack.logic.commands.SortTimeCommand;
import seedu.intrack.logic.commands.StatsCommand;
import seedu.intrack.logic.commands.StatusCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.NameContainsKeywordsPredicate;
import seedu.intrack.model.internship.PositionContainsKeywordsPredicate;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;
import seedu.intrack.model.internship.TagsContainKeywordsPredicate;
import seedu.intrack.model.internship.Task;
import seedu.intrack.testutil.EditInternshipDescriptorBuilder;
import seedu.intrack.testutil.InternshipBuilder;
import seedu.intrack.testutil.InternshipUtil;

public class InTrackParserTest {

    private final InTrackParser parser = new InTrackParser();

    @Test
    public void parseCommand_add() throws Exception {
        Internship internship = new InternshipBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(InternshipUtil.getAddCommand(internship));
        assertEquals(new AddCommand(internship), command);
    }

    @Test
    public void parseCommand_addtask() throws Exception {
        Task task = new Task("Technical Interview", "20-10-2022 10:00");
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(InternshipUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
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
        assertEquals(new DeleteCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_deletetask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + "1");
        assertEquals(new DeleteTaskCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Internship internship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(internship).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + InternshipUtil.getEditInternshipDescriptorDetails(descriptor));
        assertEquals(new EditCommand(descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_mail() throws Exception {
        assertTrue(parser.parseCommand(MailCommand.COMMAND_WORD) instanceof MailCommand);
        assertTrue(parser.parseCommand(MailCommand.COMMAND_WORD + " 3") instanceof MailCommand);
    }

    @Test
    public void parseCommand_filter_progress() throws Exception {
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD + " " + "p");
        StatusIsKeywordPredicate predicate = new StatusIsKeywordPredicate("Progress");
        assertEquals(new FilterCommand(predicate), command);
    }

    @Test
    public void parseCommand_filter_offered() throws Exception {
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD + " " + "o");
        StatusIsKeywordPredicate predicate = new StatusIsKeywordPredicate("Offered");
        assertEquals(new FilterCommand(predicate), command);
    }

    @Test
    public void parseCommand_filter_rejected() throws Exception {
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD + " " + "r");
        StatusIsKeywordPredicate predicate = new StatusIsKeywordPredicate("Rejected");
        assertEquals(new FilterCommand(predicate), command);
    }

    @Test
    public void parseCommand_findName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindNameCommand command = (FindNameCommand) parser.parseCommand(
                FindNameCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindNameCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findPosition() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPositionCommand command = (FindPositionCommand) parser.parseCommand(
                FindPositionCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPositionCommand(new PositionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTag() throws Exception {
        List<String> keywords = Arrays.asList("Urgent", "Remote");
        FindTagCommand command = (FindTagCommand) parser.parseCommand(FindTagCommand.COMMAND_WORD + " "
                + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTagCommand(new TagsContainKeywordsPredicate(keywords)), command);
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
    public void parseCommand_status() throws Exception {
        StatusCommand command = (StatusCommand) parser.parseCommand(StatusCommand.COMMAND_WORD + " "
                + INDEX_FIRST_INTERNSHIP.getOneBased() + " o");
        assertEquals(new StatusCommand(INDEX_FIRST_INTERNSHIP, new Status("Offered")), command);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        final Remark remark = new Remark("New remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + PREFIX_REMARK + remark.value);
        assertEquals(new RemarkCommand(remark), command);
    }

    @Test
    public void parseCommand_select() throws Exception {
        SelectCommand command = (SelectCommand) parser.parseCommand(SelectCommand.COMMAND_WORD + " "
                + INDEX_FIRST_INTERNSHIP);
        assertEquals(new SelectCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_sort_time() throws Exception {
        SortTimeCommand command = (SortTimeCommand) parser.parseCommand(SortTimeCommand.COMMAND_WORD + " "
                + "time" + " " + "a");
        assertEquals(new SortTimeCommand("a"), command);
    }

    @Test
    public void parseCommand_sort_salary() throws Exception {
        SortSalaryCommand command = (SortSalaryCommand) parser.parseCommand(SortSalaryCommand.COMMAND_WORD + " "
                + "salary" + " " + "a");
        assertEquals(new SortSalaryCommand("a"), command);
    }

    @Test
    public void parseCommand_stats() throws Exception {
        assertTrue(parser.parseCommand(StatsCommand.COMMAND_WORD) instanceof StatsCommand);
        assertTrue(parser.parseCommand(StatsCommand.COMMAND_WORD + " 3") instanceof StatsCommand);
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
