package seedu.nutrigoals.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_FIRST_MEAL;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.logic.commands.AddCommand;
import seedu.nutrigoals.logic.commands.ClearCommand;
import seedu.nutrigoals.logic.commands.DeleteCommand;
import seedu.nutrigoals.logic.commands.EditCommand;
import seedu.nutrigoals.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.nutrigoals.logic.commands.ExitCommand;
import seedu.nutrigoals.logic.commands.FindCommand;
import seedu.nutrigoals.logic.commands.HelpCommand;
import seedu.nutrigoals.logic.commands.ListCommand;
import seedu.nutrigoals.logic.commands.ProfileCommand;
import seedu.nutrigoals.logic.commands.ReviewCommand;
import seedu.nutrigoals.logic.commands.SetupCommand;
import seedu.nutrigoals.logic.commands.SuggestCommand;
import seedu.nutrigoals.logic.commands.TargetCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.testutil.EditFoodDescriptorBuilder;
import seedu.nutrigoals.testutil.FoodBuilder;
import seedu.nutrigoals.testutil.FoodUtil;

public class NutriGoalsParserTest {

    private final NutriGoalsParser parser = new NutriGoalsParser();

    @Test
    public void parseCommand_add() throws Exception {
        Food food = new FoodBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(FoodUtil.getAddCommand(food));
        assertEquals(new AddCommand(food), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_MEAL.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_MEAL), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Food food = new FoodBuilder().build();
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(food).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_MEAL.getOneBased() + " " + FoodUtil.getEditFoodDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_MEAL, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD + " rice");
        assertEquals(new FindCommand(new Name("rice")), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 2022-11-11") instanceof ListCommand);
    }

    @Test
    public void parseCommand_review() throws Exception {
        assertTrue(parser.parseCommand(ReviewCommand.COMMAND_WORD) instanceof ReviewCommand);
        assertTrue(parser.parseCommand(ReviewCommand.COMMAND_WORD + " 3") instanceof ReviewCommand);
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

    @Test
    public void parseCommand_setup() throws Exception {
        assertTrue(parser.parseCommand(SetupCommand.COMMAND_WORD + " h/150 w/50 i/60 g/M a/20"
        ) instanceof SetupCommand);
    }

    @Test
    public void parseCommand_target() throws Exception {
        assertTrue(parser.parseCommand(TargetCommand.COMMAND_WORD + " 1000") instanceof TargetCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(TargetCommand.COMMAND_WORD + "av"));
    }

    @Test
    public void parseCommand_suggest() throws Exception {
        assertTrue(parser.parseCommand(SuggestCommand.COMMAND_WORD) instanceof SuggestCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(SuggestCommand.COMMAND_WORD + "blah"));
    }

    @Test
    public void parseCommand_profile() throws Exception {
        assertTrue(parser.parseCommand(ProfileCommand.COMMAND_WORD) instanceof ProfileCommand);
    }
}
