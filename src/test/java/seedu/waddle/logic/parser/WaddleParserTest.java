package seedu.waddle.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNAVAILABLE_COMMAND_HOME;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNAVAILABLE_COMMAND_ITINERARY;
import static seedu.waddle.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.waddle.logic.commands.CommandTestUtil.COST_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.DAY_NUMBER_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DESC_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DURATION_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.PRIORITY_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.START_TIME_DESC_1200;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DAY_NUMBER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_TIME_1200;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;
import static seedu.waddle.testutil.TypicalItems.getShopping;
import static seedu.waddle.testutil.TypicalItineraries.getAutumn;
import static seedu.waddle.testutil.TypicalItineraries.getGraduation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.AddItemCommand;
import seedu.waddle.logic.commands.ClearCommand;
import seedu.waddle.logic.commands.CopyCommand;
import seedu.waddle.logic.commands.DeleteCommand;
import seedu.waddle.logic.commands.DeleteItemCommand;
import seedu.waddle.logic.commands.EditCommand;
import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.logic.commands.EditItemCommand;
import seedu.waddle.logic.commands.ExitCommand;
import seedu.waddle.logic.commands.FindCommand;
import seedu.waddle.logic.commands.FreeCommand;
import seedu.waddle.logic.commands.HelpCommand;
import seedu.waddle.logic.commands.HomeCommand;
import seedu.waddle.logic.commands.ListCommand;
import seedu.waddle.logic.commands.PdfCommand;
import seedu.waddle.logic.commands.PlanCommand;
import seedu.waddle.logic.commands.UnplanCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.itinerary.DayNumber;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.NameContainsKeywordsPredicate;
import seedu.waddle.testutil.EditItemDescriptorBuilder;
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

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_ITINERARY, ()
                -> parser.parseCommand(ClearCommand.COMMAND_WORD));
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_ITINERARY, ()
                -> parser.parseCommand(ClearCommand.COMMAND_WORD + " 3"));
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
        // switch to home page
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
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

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_ITINERARY, ()
                -> parser.parseCommand(FindCommand.COMMAND_WORD
                + " " + keywords.stream().collect(Collectors.joining(" "))));
    }

    @Test
    public void parseCommand_help() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_ITINERARY, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD));
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_ITINERARY, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());

        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());

        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_addItem() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());

        AddItemCommand command = (AddItemCommand) parser.parseCommand(
                AddItemCommand.COMMAND_WORD + " " + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                        + COST_DESC_SHOPPING + PRIORITY_DESC_SHOPPING);
        assertEquals(new AddItemCommand(getShopping()), command);
    }

    @Test
    public void parseCommand_deleteItem() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
        MultiIndex multiIndex = new MultiIndex();
        multiIndex.addIndex(Index.fromZeroBased(0));

        DeleteItemCommand command = (DeleteItemCommand) parser.parseCommand(
                DeleteItemCommand.COMMAND_WORD + " " + multiIndex);
        assertEquals(new DeleteItemCommand(multiIndex), command);
    }

    @Test
    public void parseCommand_editItem() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
        MultiIndex multiIndex = new MultiIndex();
        multiIndex.addIndex(Index.fromZeroBased(0));

        EditItemCommand command = (EditItemCommand) parser.parseCommand(
                EditItemCommand.COMMAND_WORD + " " + multiIndex + ITEM_DESC_DESC_SHOPPING);
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withDescription(VALID_ITEM_DESC_SHOPPING).build();
        assertEquals(new EditItemCommand(multiIndex, descriptor), command);
    }

    @Test
    public void parseCommand_free() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());

        FreeCommand command = (FreeCommand) parser.parseCommand(FreeCommand.COMMAND_WORD);
        assertEquals(new FreeCommand(), command);

        // switch to home page
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_HOME, ()
                -> parser.parseCommand(FreeCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_planItem() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getAutumn());
        MultiIndex multiIndex = new MultiIndex();
        multiIndex.addIndex(Index.fromZeroBased(0));

        PlanCommand command = (PlanCommand) parser.parseCommand(
                PlanCommand.COMMAND_WORD + " " + multiIndex + DAY_NUMBER_DESC + START_TIME_DESC_1200);
        assertEquals(new PlanCommand(multiIndex.getTaskIndex(),
                new DayNumber(VALID_DAY_NUMBER), VALID_START_TIME_1200), command);

        // switch to home page
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_HOME, ()
                -> parser.parseCommand(PlanCommand.COMMAND_WORD + " "
                + multiIndex + DAY_NUMBER_DESC + START_TIME_DESC_1200));
    }

    @Test
    public void parseCommand_unplanItem() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());
        MultiIndex multiIndex = new MultiIndex();
        multiIndex.addIndex(Index.fromZeroBased(0));
        multiIndex.addIndex(Index.fromZeroBased(0));

        UnplanCommand command = (UnplanCommand) parser.parseCommand(
                UnplanCommand.COMMAND_WORD + " " + multiIndex);
        assertEquals(new UnplanCommand(multiIndex), command);

        // switch to home page
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_HOME, ()
                -> parser.parseCommand(UnplanCommand.COMMAND_WORD + " " + multiIndex));
    }

    @Test
    public void parseCommand_copy() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());

        CopyCommand command = (CopyCommand) parser.parseCommand(CopyCommand.COMMAND_WORD);
        assertEquals(new CopyCommand(), command);

        // switch to home page
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_HOME, ()
                -> parser.parseCommand(CopyCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_pdf() throws Exception {
        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());

        PdfCommand command = (PdfCommand) parser.parseCommand(PdfCommand.COMMAND_WORD);
        assertEquals(new PdfCommand(), command);

        // switch to home page
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class, MESSAGE_UNAVAILABLE_COMMAND_HOME, ()
                -> parser.parseCommand(PdfCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_home() throws Exception {
        // switch to home page
        StageManager.getInstance().setHomeStage();

        HomeCommand command = (HomeCommand) parser.parseCommand(
                HomeCommand.COMMAND_WORD);
        assertEquals(new HomeCommand(), command);

        // switch to itinerary page
        StageManager.getInstance().setWishStage(getGraduation());

        command = (HomeCommand) parser.parseCommand(
                HomeCommand.COMMAND_WORD);
        assertEquals(new HomeCommand(), command);
    }
}
