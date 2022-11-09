package seedu.guest.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guest.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.guest.testutil.Assert.assertThrows;
import static seedu.guest.testutil.TypicalIndexes.INDEX_FIRST_GUEST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.guest.logic.commands.AddCommand;
import seedu.guest.logic.commands.ClearCommand;
import seedu.guest.logic.commands.DeleteCommand;
import seedu.guest.logic.commands.EditCommand;
import seedu.guest.logic.commands.ExitCommand;
import seedu.guest.logic.commands.FindCommand;
import seedu.guest.logic.commands.HelpCommand;
import seedu.guest.logic.commands.ListCommand;
import seedu.guest.logic.commands.MarkRoomsUncleanCommand;
import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.GuestContainsKeywordsPredicate;
import seedu.guest.testutil.EditGuestDescriptorBuilder;
import seedu.guest.testutil.GuestBuilder;
import seedu.guest.testutil.GuestUtil;

public class GuestBookParserTest {

    private final GuestBookParser parser = new GuestBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Guest guest = new GuestBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(GuestUtil.getAddCommand(guest));
        assertEquals(new AddCommand(guest), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_GUEST.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_GUEST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Guest guest = new GuestBuilder().build();
        EditCommand.EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(guest).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_GUEST.getOneBased() + " " + GuestUtil.getEditGuestDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_GUEST, descriptor), command);
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
        assertEquals(new FindCommand(new GuestContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_resetRoomClean() throws Exception {
        assertTrue(parser.parseCommand(MarkRoomsUncleanCommand.COMMAND_WORD) instanceof MarkRoomsUncleanCommand);
        assertTrue(parser.parseCommand(MarkRoomsUncleanCommand.COMMAND_WORD + " 3")
                instanceof MarkRoomsUncleanCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
