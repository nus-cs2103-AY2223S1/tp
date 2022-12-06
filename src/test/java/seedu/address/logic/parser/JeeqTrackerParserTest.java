package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalRemark.BAD_SELLER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BuyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterTransCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.SellCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UserGuideCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.transaction.BuyTransaction;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.SellTransaction;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ClientUtil;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.RemarkUtil;


public class JeeqTrackerParserTest {

    private final JeeqTrackerParser parser = new JeeqTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Client client = new ClientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ClientUtil.getAddCommand(client));
        assertEquals(new AddCommand(client, ""), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased() + " m/client");
        assertEquals(new DeleteClientCommand(INDEX_FIRST_CLIENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Client client = new ClientBuilder().build();
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(client).build();
        EditClientCommand command = (EditClientCommand) parser.parseCommand(EditClientCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " m/client "
                + ClientUtil.getEditClientDescriptorDetails(descriptor));
        assertEquals(new EditClientCommand(INDEX_FIRST_CLIENT, descriptor, ""), command);
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
    public void parseCommand_user_guide() throws Exception {
        assertTrue(parser.parseCommand(UserGuideCommand.COMMAND_WORD) instanceof UserGuideCommand);
        assertTrue(parser.parseCommand(UserGuideCommand.COMMAND_WORD + " 3") instanceof UserGuideCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " add") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        RemarkCommand command = (RemarkCommand) parser.parseCommand(
                RemarkUtil.getRemarkCommand(BAD_SELLER, INDEX_FIRST_CLIENT));
        assertEquals(new RemarkCommand(INDEX_FIRST_CLIENT, BAD_SELLER), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_CLIENT), command);
    }

    @Test
    public void parseCommand_filterTrans() throws Exception {
        FilterTransCommand command = (FilterTransCommand) parser.parseCommand(
                FilterTransCommand.COMMAND_WORD + " buy");
        assertEquals(new FilterTransCommand(true), command);

        FilterTransCommand command2 = (FilterTransCommand) parser.parseCommand(
                FilterTransCommand.COMMAND_WORD + " sell");
        assertEquals(new FilterTransCommand(false), command2);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UserGuideCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_buy() throws Exception {

        Goods goods = new Goods("Orange");
        Price price = new Price("2.5");
        Quantity quantity = new Quantity("200");
        Date date = new Date("09/11/2000");
        Transaction transaction = new BuyTransaction(goods, price, quantity, date);
        BuyCommand command = (BuyCommand) parser.parseCommand(BuyCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " " + PREFIX_QUANTITY + "200 " + PREFIX_GOODS + "Orange "
                + PREFIX_PRICE
                + "2.5 "
                + PREFIX_DATE
                + "09/11/2000 ");
        assertEquals(new BuyCommand(INDEX_FIRST_CLIENT, transaction), command);
    }

    @Test
    public void parseCommand_sell() throws Exception {

        Goods goods = new Goods("Orange");
        Price price = new Price("2.5");
        Quantity quantity = new Quantity("200");
        Date date = new Date("09/11/2000");
        Transaction transaction = new SellTransaction(goods, price, quantity, date);
        SellCommand command = (SellCommand) parser.parseCommand(SellCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " " + PREFIX_QUANTITY + "200 " + PREFIX_GOODS + "Orange "
                + PREFIX_PRICE
                + "2.5 "
                + PREFIX_DATE
                + "09/11/2000 ");
        assertEquals(new SellCommand(INDEX_FIRST_CLIENT, transaction), command);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased() + " " + "latest");
        assertEquals(new SortCommand((INDEX_FIRST_CLIENT), true), command);
    }
}
