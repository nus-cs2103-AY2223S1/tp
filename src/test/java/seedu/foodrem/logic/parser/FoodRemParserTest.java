package seedu.foodrem.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TagUtil.getTagDetails;
import static seedu.foodrem.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodrem.enums.CommandType;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.generalcommands.ExitCommand;
import seedu.foodrem.logic.commands.generalcommands.HelpCommand;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.commands.itemcommands.DeleteCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.logic.commands.itemcommands.FindCommand;
import seedu.foodrem.logic.commands.itemcommands.ListCommand;
import seedu.foodrem.logic.commands.itemcommands.NewCommand;
import seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand;
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.NameContainsKeywordsPredicate;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.ItemUtil;
import seedu.foodrem.testutil.TagBuilder;


public class FoodRemParserTest {

    private final FoodRemParser parser = new FoodRemParser();

    @Test
    public void parseCommand_add() {
        Item item = new ItemBuilder().build();
        NewCommand command = (NewCommand) parser.parseCommand(ItemUtil.getAddCommand(item));
        assertEquals(new NewCommand(item), command);
    }

    @Test
    public void parseCommand_clear() {
        assertTrue(parser.parseCommand(CommandType.RESET_COMMAND.getCommandWord()) instanceof ResetCommand);
        assertTrue(parser.parseCommand(CommandType.RESET_COMMAND.getCommandWord() + " 3") instanceof ResetCommand);
    }

    @Test
    public void parseCommand_delete() {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                CommandType.DELETE_COMMAND.getCommandWord() + " " + INDEX_FIRST_ITEM.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_ITEM), command);
    }

    @Test
    public void parseCommand_edit() {
        Item item = new ItemBuilder().build();
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(item).build();
        EditCommand command = (EditCommand) parser.parseCommand(CommandType.EDIT_COMMAND.getCommandWord() + " "
                + INDEX_FIRST_ITEM.getOneBased() + " " + ItemUtil.getEditItemDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ITEM, descriptor), command);
    }

    @Test
    public void parseCommand_exit() {
        assertTrue(parser.parseCommand(CommandType.EXIT_COMMAND.getCommandWord()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(CommandType.EXIT_COMMAND.getCommandWord() + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                CommandType.FIND_COMMAND.getCommandWord() + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() {
        assertTrue(parser.parseCommand(CommandType.HELP_COMMAND.getCommandWord()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(CommandType.HELP_COMMAND.getCommandWord() + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() {
        assertTrue(parser.parseCommand(CommandType.LIST_COMMAND.getCommandWord()) instanceof ListCommand);
        assertTrue(parser.parseCommand(CommandType.LIST_COMMAND.getCommandWord() + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_deleteTagCommand() {
        assertTrue(parser.parseCommand(CommandType.DELETE_TAG_COMMAND.getCommandWord()
                + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS) instanceof DeleteTagCommand);
    }

    @Test
    public void parseCommand_addTag() {
        Tag tag = new TagBuilder().build();
        assertTrue(parser.parseCommand(CommandType.NEW_TAG_COMMAND.getCommandWord() + " " + getTagDetails(tag))
                instanceof NewTagCommand);
    }

    @Test
    public void parseCommand_renameTag() {
        Tag originalTag = new TagBuilder().withTagName(CommandTestUtil.VALID_TAG_NAME_FRUITS).build();
        Tag renamedTag = new TagBuilder().withTagName(CommandTestUtil.VALID_TAG_NAME_NUMBERS).build();
        assertTrue(parser.parseCommand(CommandType.RENAME_TAG_COMMAND.getCommandWord()
                + " " + getTagDetails(originalTag)
                + " " + getTagDetails(renamedTag)) instanceof RenameTagCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.getUsage()), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
