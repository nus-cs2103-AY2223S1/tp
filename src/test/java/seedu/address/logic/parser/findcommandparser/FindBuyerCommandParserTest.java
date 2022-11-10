package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommands.FindBuyerCommand;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindBuyerCommandParserTest {
    private FindBuyerCommandParser parser = new FindBuyerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException2() {
        assertParseFailure(parser, "b/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOnePrefix_throwsParseException() {
        assertParseFailure(parser, PREFIX_ADDRESS.getPrefix() + PREFIX_PHONE.getPrefix(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, "More than 1 prefix present"));
    }

    @Test
    public void parse_validArgs_returnsFindCommandBuyer() {
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Supplier> supplierPredicate = new Predicate<Supplier>() {
            @Override
            public boolean test(Supplier supplier) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindBuyerCommand(new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/\n Alice Bob  \t", expectedFindCommand);
    }
}
