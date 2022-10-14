package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalPoc.ALICE;
import static seedu.address.testutil.TypicalTransaction.BUY_ORANGE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BuyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SellCommand;
import seedu.address.logic.commands.UserGuideCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;
import seedu.address.model.transaction.SellTransaction;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.CompanyUtil;
import seedu.address.testutil.EditCompanyDescriptorBuilder;
import seedu.address.testutil.PocUtil;
import seedu.address.testutil.TransactionUtil;

public class JeeqTrackerParserTest {

    private final JeeqTrackerParser parser = new JeeqTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Client client = new CompanyBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CompanyUtil.getAddCommand(client));
        assertEquals(new AddCommand(client), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_COMPANY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Client client = new CompanyBuilder().build();
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(client).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_COMPANY.getOneBased() + " " + CompanyUtil.getEditCompanyDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_COMPANY, descriptor), command);
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
    public void parseCommand_create() throws Exception {
        CreateCommand command = (CreateCommand) parser.parseCommand(
                PocUtil.getCreateCommand(ALICE, INDEX_FIRST_COMPANY));
        assertEquals(new CreateCommand(INDEX_FIRST_COMPANY, ALICE), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_COMPANY), command);
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
        BuyCommand command = (BuyCommand) parser.parseCommand(
                TransactionUtil.getBuyCommand(INDEX_FIRST_COMPANY, BUY_ORANGE));
        assertEquals(new BuyCommand(INDEX_FIRST_COMPANY, BUY_ORANGE), command);
    }

    @Test
    public void parseCommand_sell() throws Exception {

        Goods goods = new Goods("Orange");
        Price price = new Price("2.5");
        Quantity quantity = new Quantity("200");
        Transaction transaction = new SellTransaction(goods, price, quantity);
        SellCommand command = (SellCommand) parser.parseCommand(SellCommand.COMMAND_WORD + " "
                + INDEX_FIRST_COMPANY.getOneBased() + " " + PREFIX_QUANTITY + "200 " + PREFIX_GOODS + "Orange "
                + PREFIX_PRICE
                + "2.5 ");
        assertEquals(new SellCommand(INDEX_FIRST_COMPANY, transaction), command);
    }
}
