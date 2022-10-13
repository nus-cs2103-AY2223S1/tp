package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.FindContactCommand;
import seedu.address.logic.parser.contact.FindContactCommandParser;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.Phone;

public class FindContactCommandParserTest {

    private FindContactCommandParser parser = new FindContactCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String nameKeyword1 = "ali";
        String nameKeyword2 = "baba";
        String phoneKeyword1 = "999";
        String phoneKeyword2 = "62353535";
        String emailKeyword1 = "john@gmail.com";
        String emailKeyword2 = "colonel-sanders-123@kfc.co.uk";
        String addressKeyword1 = "17A Lower Kent Ridge Road, #01-01, S(119081)";
        String addressKeyword2 = "17A Lower Kent Ridge Road, c/o reception@sally, #01-01, S(119081)";

        List<Name> nameKeywords = Arrays.asList(new Name(nameKeyword1), new Name(nameKeyword2));
        List<Phone> phoneKeywords = Arrays.asList(new Phone(phoneKeyword1), new Phone(phoneKeyword2));
        List<Email> emailKeywords = Arrays.asList(new Email(emailKeyword1), new Email(emailKeyword2));
        List<Address> addressKeywords = Arrays.asList(new Address(addressKeyword1), new Address(addressKeyword2));

        FindContactCommand expectedFindCommand = new FindContactCommand(new PersonContainsKeywordsPredicate(
                nameKeywords, phoneKeywords, emailKeywords, addressKeywords));

        // multiple whitespaces between keywords
        // TODO: Add test case

        // no prefix
        // TODO: throw error

        // repeated prefixes
        // TODO: throw error
    }
}
