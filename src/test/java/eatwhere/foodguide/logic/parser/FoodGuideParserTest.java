package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static eatwhere.foodguide.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.logic.commands.AddCommand;
import eatwhere.foodguide.logic.commands.ClearCommand;
import eatwhere.foodguide.logic.commands.DeleteCommand;
import eatwhere.foodguide.logic.commands.EditCommand;
import eatwhere.foodguide.logic.commands.ExitCommand;
import eatwhere.foodguide.logic.commands.FindCommand;
import eatwhere.foodguide.logic.commands.HelpCommand;
import eatwhere.foodguide.logic.commands.ListCommand;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.NameContainsKeywordsPredicate;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.EateryUtil;
import eatwhere.foodguide.testutil.EditEateryDescriptorBuilder;
import eatwhere.foodguide.testutil.TypicalIndexes;

public class FoodGuideParserTest {

    private final FoodGuideParser parser = new FoodGuideParser();

    @Test
    public void parseCommand_add() throws Exception {
        Eatery eatery = new EateryBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EateryUtil.getAddCommand(eatery));
        assertEquals(new AddCommand(eatery), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_EATERY.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_EATERY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Eatery eatery = new EateryBuilder().build();
        EditCommand.EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder(eatery).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_EATERY.getOneBased()
                + " " + EateryUtil.getEditEateryDescriptorDetails(descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_EATERY, descriptor), command);
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
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
        Assert.assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
