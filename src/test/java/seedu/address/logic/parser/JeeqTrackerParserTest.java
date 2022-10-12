package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalPoc.ALICE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;
import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Company;
import seedu.address.model.company.NameContainsKeywordsPredicate;
import seedu.address.model.transaction.*;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.CompanyUtil;
import seedu.address.testutil.EditCompanyDescriptorBuilder;
import seedu.address.testutil.PocUtil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;

public class JeeqTrackerParserTest {

    private final JeeqTrackerParser parser = new JeeqTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Company company = new CompanyBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CompanyUtil.getAddCommand(company));
        assertEquals(new AddCommand(company), command);
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
        Company company = new CompanyBuilder().build();
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(company).build();
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
        BuyCommand command = (BuyCommand) parser.parseCommand(BuyCommand.COMMAND_WORD + " "
                + INDEX_FIRST_COMPANY.getOneBased() + " " + PREFIX_QUANTITY + "100 " + PREFIX_GOODS + "Apples " + PREFIX_PRICE +
                "1.5 ");
        Goods goods = new Goods("Apples");
        Price price = new Price("1.5");
        Quantity quantity = new Quantity("100");
        Transaction transaction = new BuyTransaction(goods, price, quantity);
        assertEquals(new BuyCommand(INDEX_FIRST_COMPANY, transaction), command);
    }

    @Test
    public void parseCommand_sell() throws Exception {
        SellCommand command = (SellCommand) parser.parseCommand(SellCommand.COMMAND_WORD + " "
                + INDEX_FIRST_COMPANY.getOneBased() + " " + PREFIX_QUANTITY + "200 " + PREFIX_GOODS + "Orange " + PREFIX_PRICE +
                "2.5 ");
        Goods goods = new Goods("Orange");
        Price price = new Price("2.5");
        Quantity quantity = new Quantity("200");
        Transaction transaction = new SellTransaction(goods, price, quantity);
        assertEquals(new SellCommand(INDEX_FIRST_COMPANY, transaction), command);
    }
}
