package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.Phone;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
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

        FindCommand expectedFindCommand = new FindCommand(new PersonContainsKeywordsPredicate(
                nameKeywords, phoneKeywords, emailKeywords, addressKeywords));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, String.format("%s %s %s %s %s %s %s %s %s %s %s %s",
                PREFIX_NAME, nameKeyword1, nameKeyword2,
                PREFIX_PHONE, phoneKeyword1, phoneKeyword2,
                PREFIX_EMAIL, emailKeyword1, emailKeyword2,
                PREFIX_ADDRESS, addressKeyword1, addressKeyword2),
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
        assertParseSuccess(parser, String.format("\n%s %s \n %s \n %s \t %s   %s %s \n\n %s %s %s %s %s ",
                        PREFIX_NAME, nameKeyword1, nameKeyword2,
                        PREFIX_PHONE, phoneKeyword1, phoneKeyword2,
                        PREFIX_EMAIL, emailKeyword1, emailKeyword2,
                        PREFIX_ADDRESS, addressKeyword1, addressKeyword2),
                expectedFindCommand);

        // no prefix
        //todo throw error

        // repeated prefixes
        assertParseSuccess(parser, String.format("%s %s %s %s %s %s %s %s %s %s %s %s %s %s %s",
                        PREFIX_NAME, nameKeyword1, nameKeyword2,
                        PREFIX_PHONE, phoneKeyword1, phoneKeyword2,
                        PREFIX_PHONE, phoneKeyword1, phoneKeyword2,
                        PREFIX_EMAIL, emailKeyword1, emailKeyword2,
                        PREFIX_ADDRESS, addressKeyword1, addressKeyword2),
                expectedFindCommand);
    }

}
