package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindSupplierCommandParserTest {
    private FindSupplierCommandParser parser = new FindSupplierCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException2() {
        assertParseFailure(parser, "b/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommandDeliverer() {
        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(buyerPredicate, delivererPredicate,
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Charlie Bob")) , PersonCategory.SUPPLIER);
        assertParseSuccess(parser, "n/Charlie Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/ \n Charlie Bob  \t", expectedFindCommand);
    }
}
