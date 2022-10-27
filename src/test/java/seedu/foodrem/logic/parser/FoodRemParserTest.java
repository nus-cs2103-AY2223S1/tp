package seedu.foodrem.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.enums.CommandType;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.generalcommands.ExitCommand;
import seedu.foodrem.logic.commands.generalcommands.HelpCommand;
import seedu.foodrem.logic.commands.generalcommands.ResetCommand;
import seedu.foodrem.logic.commands.itemcommands.DecrementCommand;
import seedu.foodrem.logic.commands.itemcommands.DeleteCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.logic.commands.itemcommands.FilterTagCommand;
import seedu.foodrem.logic.commands.itemcommands.FindCommand;
import seedu.foodrem.logic.commands.itemcommands.IncrementCommand;
import seedu.foodrem.logic.commands.itemcommands.ListCommand;
import seedu.foodrem.logic.commands.itemcommands.NewCommand;
import seedu.foodrem.logic.commands.itemcommands.RemarkCommand;
import seedu.foodrem.logic.commands.itemcommands.SortCommand;
import seedu.foodrem.logic.commands.itemcommands.ViewCommand;
import seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand;
import seedu.foodrem.logic.commands.tagcommands.ListTagCommand;
import seedu.foodrem.logic.commands.tagcommands.NewTagCommand;
import seedu.foodrem.logic.commands.tagcommands.RenameTagCommand;
import seedu.foodrem.logic.commands.tagcommands.TagCommand;
import seedu.foodrem.logic.commands.tagcommands.UntagCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.NameContainsKeywordsPredicate;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.ItemUtil;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.testutil.TagUtil;
import seedu.foodrem.testutil.TypicalIndexes;

public class FoodRemParserTest {
    private final FoodRemParser parser = new FoodRemParser();

    // General Commands
    @Test
    public void parseCommand_help() {
        assertTrue(parser.parseCommand(CommandType.HELP_COMMAND.getCommandWord()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(CommandType.HELP_COMMAND.getCommandWord() + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_clear() {
        assertTrue(parser.parseCommand(CommandType.RESET_COMMAND.getCommandWord()) instanceof ResetCommand);
    }

    @Test
    public void parseCommand_exit() {
        assertTrue(parser.parseCommand(CommandType.EXIT_COMMAND.getCommandWord()) instanceof ExitCommand);
    }

    // Item Commands
    @Test
    public void parseCommand_add() {
        Item item = new ItemBuilder().build();
        NewCommand command = (NewCommand) parser.parseCommand(ItemUtil.getAddCommand(item));
        assertEquals(new NewCommand(item), command);
    }

    @Test
    public void parseCommand_delete() {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                CommandType.DELETE_COMMAND.getCommandWord() + " "
                        + TypicalIndexes.INDEX_FIRST_ITEM.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_ITEM), command);
    }

    @Test
    public void parseCommand_edit() {
        Item item = new ItemBuilder().build();
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(item).build();
        EditCommand command = (EditCommand) parser.parseCommand(CommandType.EDIT_COMMAND.getCommandWord() + " "
                + TypicalIndexes.INDEX_FIRST_ITEM.getOneBased() + " "
                + ItemUtil.getEditItemDescriptorDetails(descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM, descriptor), command);
    }

    @Test
    public void parseCommand_find() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                CommandType.FIND_COMMAND.getCommandWord() + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_list() {
        assertTrue(parser.parseCommand(CommandType.LIST_COMMAND.getCommandWord()) instanceof ListCommand);
    }

    @Test
    public void parseCommand_inc() {
        assertTrue(parser.parseCommand(CommandType.INCREMENT_COMMAND.getCommandWord() + " 1")
                instanceof IncrementCommand);
    }

    @Test
    public void parseCommand_dec() {
        assertTrue(parser.parseCommand(CommandType.DECREMENT_COMMAND.getCommandWord() + " 1")
                instanceof DecrementCommand);
    }

    @Test
    public void parseCommand_sort() {
        assertTrue(parser.parseCommand(CommandType.SORT_COMMAND.getCommandWord() + " n/")
                instanceof SortCommand);
    }

    @Test
    public void parseCommand_view() {
        assertTrue(parser.parseCommand(CommandType.VIEW_COMMAND.getCommandWord()
                + " 1") instanceof ViewCommand);
    }

    @Test
    public void parseCommand_remark() {
        assertTrue(parser.parseCommand(CommandType.REMARK_COMMAND.getCommandWord()
                + " 1") instanceof RemarkCommand);
    }

    // Tag Commands
    @Test
    public void parseCommand_deleteTagCommand() {
        assertTrue(parser.parseCommand(CommandType.DELETE_TAG_COMMAND.getCommandWord()
                + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS) instanceof DeleteTagCommand);
    }

    @Test
    public void parseCommand_newTag() {
        Tag tag = new TagBuilder().build();
        assertTrue(parser.parseCommand(CommandType.NEW_TAG_COMMAND.getCommandWord() + " "
                + TagUtil.getTagDetails(tag))
                instanceof NewTagCommand);
    }

    @Test
    public void parseCommand_renameTag() {
        Tag originalTag = new TagBuilder().withTagName(CommandTestUtil.VALID_TAG_NAME_FRUITS).build();
        Tag renamedTag = new TagBuilder().withTagName(CommandTestUtil.VALID_TAG_NAME_NUMBERS).build();
        assertTrue(parser.parseCommand(CommandType.RENAME_TAG_COMMAND.getCommandWord()
                + " " + TagUtil.getTagDetails(originalTag)
                + " " + TagUtil.getTagDetails(renamedTag)) instanceof RenameTagCommand);
    }

    @Test
    public void parseCommand_tag() {
        assertTrue(parser.parseCommand(CommandType.TAG_COMMAND.getCommandWord()
                + " 1" + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS) instanceof TagCommand);
    }

    @Test
    public void parseCommand_unTag() {
        assertTrue(parser.parseCommand(CommandType.UNTAG_COMMAND.getCommandWord()
                + " 1" + CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS) instanceof UntagCommand);
    }

    @Test
    public void parseCommand_listTag() {
        assertTrue(parser.parseCommand(CommandType.LIST_TAG_COMMAND.getCommandWord())
                instanceof ListTagCommand);
    }

    @Test
    public void parseCommand_filterTag() {
        assertTrue(parser.parseCommand(CommandType.FILTER_TAG_COMMAND.getCommandWord()
                + " "
                + CliSyntax.PREFIX_NAME
                + "fruits") instanceof FilterTagCommand);
    }

    // Others
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        HelpCommand.getUsage()), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                     Messages.MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
