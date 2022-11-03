package seedu.trackascholar.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.AddCommand;
import seedu.trackascholar.logic.commands.ClearCommand;
import seedu.trackascholar.logic.commands.DeleteCommand;
import seedu.trackascholar.logic.commands.EditCommand;
import seedu.trackascholar.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.trackascholar.logic.commands.ExitCommand;
import seedu.trackascholar.logic.commands.FilterCommand;
import seedu.trackascholar.logic.commands.FindCommand;
import seedu.trackascholar.logic.commands.HelpCommand;
import seedu.trackascholar.logic.commands.ImportCommand;
import seedu.trackascholar.logic.commands.ListCommand;
import seedu.trackascholar.logic.commands.PinCommand;
import seedu.trackascholar.logic.commands.RemoveCommand;
import seedu.trackascholar.logic.commands.SortCommand;
import seedu.trackascholar.logic.commands.UnPinCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.ApplicationStatusPredicate;
import seedu.trackascholar.model.applicant.NameContainsKeywordsPredicate;
import seedu.trackascholar.testutil.ApplicantBuilder;
import seedu.trackascholar.testutil.ApplicantUtil;
import seedu.trackascholar.testutil.EditApplicantDescriptorBuilder;
import seedu.trackascholar.testutil.TypicalApplicants;

public class TrackAScholarParserTest {

    private final TrackAScholarParser parser = new TrackAScholarParser();

    @Test
    public void parseCommand_add() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ApplicantUtil.getAddCommand(applicant));
        assertEquals(new AddCommand(applicant), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicant).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICANT.getOneBased() + " "
                + ApplicantUtil.getEditApplicantDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_APPLICANT, descriptor), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICANT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_APPLICANT), command);
    }

    @Test
    public void parseCommand_remove() throws Exception {
        RemoveCommand command = (RemoveCommand) parser.parseCommand(
                RemoveCommand.COMMAND_WORD + " " + ApplicationStatus.ACCEPTED);
        assertEquals(new RemoveCommand(new ApplicationStatus(ApplicationStatus.ACCEPTED)), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + "accepted");
        assertEquals(new FilterCommand(new ApplicationStatusPredicate("accepted")), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + SortCommandParser.NAME);
        assertEquals(new SortCommand(Applicant.sortByName()), command);
    }

    @Test
    public void parseCommand_import() throws Exception {
        ImportCommand command = (ImportCommand) parser.parseCommand(
                ImportCommand.COMMAND_WORD + " " + ImportCommand.KEEP);
        assertEquals(new ImportCommand(ImportCommand.KEEP), command);
    }

    @Test
    public void parseCommand_pin() throws Exception {
        PinCommand command = (PinCommand) parser.parseCommand(
                PinCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICANT.getOneBased());
        assertEquals(new PinCommand(INDEX_FIRST_APPLICANT), command);
    }

    @Test
    public void parseCommand_unpin() throws Exception {
        UnPinCommand command = (UnPinCommand) parser.parseCommand(
                UnPinCommand.COMMAND_WORD + " " + TypicalApplicants.ALICE.getName());
        assertEquals(new UnPinCommand(TypicalApplicants.ALICE.getName()), command);
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
