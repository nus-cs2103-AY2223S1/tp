package seedu.travelr.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.travelr.logic.commands.AddCommand;
import seedu.travelr.logic.commands.ClearCommand;
import seedu.travelr.logic.commands.DeleteCommand;
import seedu.travelr.logic.commands.EditCommand;
import seedu.travelr.logic.commands.EditCommand.EditTripDescriptor;
import seedu.travelr.logic.commands.ExitCommand;
import seedu.travelr.logic.commands.FindCommand;
import seedu.travelr.logic.commands.HelpCommand;
import seedu.travelr.logic.commands.ListCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.trip.TitleContainsKeywordsPredicate;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.EditTripDescriptorBuilder;
import seedu.travelr.testutil.TripBuilder;
import seedu.travelr.testutil.TripUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_FIRST_TRIP;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Trip trip = new TripBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(TripUtil.getAddCommand(trip));
        assertEquals(new AddCommand(trip), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_TRIP.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_TRIP), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Trip trip = new TripBuilder().build();
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder(trip).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TRIP.getOneBased() + " " + TripUtil.getEditTripDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_TRIP, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new TitleContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
