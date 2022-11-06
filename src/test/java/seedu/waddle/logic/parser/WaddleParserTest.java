package seedu.waddle.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.ClearCommand;
import seedu.waddle.logic.commands.DeleteCommand;
import seedu.waddle.logic.commands.EditCommand;
import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.logic.commands.ExitCommand;
import seedu.waddle.logic.commands.FindCommand;
import seedu.waddle.logic.commands.HelpCommand;
import seedu.waddle.logic.commands.ListCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.NameContainsKeywordsPredicate;
import seedu.waddle.testutil.EditItineraryDescriptorBuilder;
import seedu.waddle.testutil.ItineraryBuilder;
import seedu.waddle.testutil.ItineraryUtil;

public class WaddleParserTest {

    private final WaddleParser parser = new WaddleParser();

    @Test
    public void parseCommand_add() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        Itinerary itinerary = new ItineraryBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ItineraryUtil.getAddCommand(itinerary));
        assertEquals(new AddCommand(itinerary), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ITINERARY.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_ITINERARY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        Itinerary itinerary = new ItineraryBuilder().build();
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(itinerary).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ITINERARY.getOneBased() + " "
                + ItineraryUtil.getEditItineraryDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ITINERARY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }
}
