package tuthub.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import tuthub.logic.commands.AddCommand;
import tuthub.logic.commands.ClearCommand;
import tuthub.logic.commands.DeleteCommand;
import tuthub.logic.commands.EditCommand;
import tuthub.logic.commands.EditCommand.EditTutorDescriptor;
import tuthub.logic.commands.ExitCommand;
import tuthub.logic.commands.FindByEmailCommand;
import tuthub.logic.commands.FindByModuleCommand;
import tuthub.logic.commands.FindByNameCommand;
import tuthub.logic.commands.FindByPhoneCommand;
import tuthub.logic.commands.FindByRatingCommand;
import tuthub.logic.commands.FindByStudentIdCommand;
import tuthub.logic.commands.FindByTeachingNominationCommand;
import tuthub.logic.commands.FindByYearCommand;
import tuthub.logic.commands.HelpCommand;
import tuthub.logic.commands.ListCommand;
import tuthub.logic.commands.ViewCommand;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tutor.EmailContainsKeywordsPredicate;
import tuthub.model.tutor.ModuleContainsKeywordsPredicate;
import tuthub.model.tutor.NameContainsKeywordsPredicate;
import tuthub.model.tutor.PhoneContainsKeywordsPredicate;
import tuthub.model.tutor.RatingContainsKeywordsPredicate;
import tuthub.model.tutor.StudentIdContainsKeywordsPredicate;
import tuthub.model.tutor.TeachingNominationContainKeywordsPredicate;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.YearContainsKeywordsPredicate;
import tuthub.testutil.EditTutorDescriptorBuilder;
import tuthub.testutil.TutorBuilder;
import tuthub.testutil.TutorUtil;

public class TuthubParserTest {

    private final TuthubParser parser = new TuthubParser();

    @Test
    public void parseCommand_add() throws Exception {
        Tutor tutor = new TutorBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(TutorUtil.getAddCommand(tutor));
        assertEquals(new AddCommand(tutor), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_TUTOR.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_TUTOR), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Tutor tutor = new TutorBuilder().build();
        EditTutorDescriptor descriptor = new EditTutorDescriptorBuilder(tutor).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TUTOR.getOneBased() + " " + TutorUtil.getEditTutorDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_TUTOR, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findByName() throws Exception {
        List<String> keywords = Arrays.asList("alex", "john", "baz");
        FindByNameCommand command = (FindByNameCommand) parser.parseCommand(
                FindByNameCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindByNameCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByPhone() throws Exception {
        List<String> keywords = Arrays.asList("98765432", "99999999");
        FindByPhoneCommand command = (FindByPhoneCommand) parser.parseCommand((
                FindByPhoneCommand.COMMAND_WORD + " p/" + keywords.stream().collect(Collectors.joining(" "))));
        assertEquals(new FindByPhoneCommand(new PhoneContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByEmail() throws Exception {
        List<String> keywords = Arrays.asList("alice@example.com", "benson@example.com");
        FindByEmailCommand command = (FindByEmailCommand) parser.parseCommand((
                FindByEmailCommand.COMMAND_WORD + " e/" + keywords.stream().collect(Collectors.joining(" "))));
        assertEquals(new FindByEmailCommand(new EmailContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByModule() throws Exception {
        List<String> keywords = Arrays.asList("cs2100", "cs2105");
        FindByModuleCommand command = (FindByModuleCommand) parser.parseCommand((
                FindByModuleCommand.COMMAND_WORD + " m/" + keywords.stream().collect(Collectors.joining(" "))));
        assertEquals(new FindByModuleCommand(new ModuleContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByYear() throws Exception {
        List<String> keywords = Arrays.asList("2", "3");
        FindByYearCommand command = (FindByYearCommand) parser.parseCommand((
                FindByYearCommand.COMMAND_WORD + " y/" + keywords.stream().collect(Collectors.joining(" "))));
        assertEquals(new FindByYearCommand(new YearContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByStudentId() throws Exception {
        List<String> keywords = Arrays.asList("A0123456X", "A0123456Y");
        FindByStudentIdCommand command = (FindByStudentIdCommand) parser.parseCommand((
                FindByStudentIdCommand.COMMAND_WORD + " s/" + keywords.stream().collect(Collectors.joining(" "))));
        assertEquals(new FindByStudentIdCommand(new StudentIdContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByTeachingNomination() throws Exception {
        List<String> keywords = Arrays.asList("1", "2");
        FindByTeachingNominationCommand command = (FindByTeachingNominationCommand) parser.parseCommand((
                FindByTeachingNominationCommand.COMMAND_WORD + " tn/"
                        + keywords.stream().collect(Collectors.joining(" "))));
        assertEquals(new FindByTeachingNominationCommand(
                new TeachingNominationContainKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findByRating() throws Exception {
        List<String> keywords = Arrays.asList("2.5", "3.0");
        FindByRatingCommand command = (FindByRatingCommand) parser.parseCommand((
                FindByRatingCommand.COMMAND_WORD + " r/" + keywords.stream().collect(Collectors.joining(" "))));
        assertEquals(new FindByRatingCommand(new RatingContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_TUTOR.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_TUTOR), command);
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
