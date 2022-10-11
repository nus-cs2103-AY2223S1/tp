package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonCategory;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException2() {
        assertParseFailure(parser, "Buyer", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommandBuyer() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate<>(Arrays.asList("Buyer", "Alice", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Buyer", "Alice", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Buyer", "Alice", "Bob")),
                        new PersonCategory("Buyer"));
        assertParseSuccess(parser, "Buyer Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "Buyer \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommandDeliverer() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate<>(Arrays.asList("Deliverer", "Charlie", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Deliverer", "Charlie", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Deliverer", "Charlie", "Bob")),
                        new PersonCategory("Deliverer"));
        assertParseSuccess(parser, "Deliverer Charlie Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "Deliverer \n Charlie \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommandSupplier() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate<>(Arrays.asList("Supplier", "Charlie", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Supplier", "Charlie", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Supplier", "Charlie", "Bob")),
                        new PersonCategory("Supplier"));
        assertParseSuccess(parser, "Supplier Charlie Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "Supplier \n Charlie \n \t Bob  \t", expectedFindCommand);
    }

}
