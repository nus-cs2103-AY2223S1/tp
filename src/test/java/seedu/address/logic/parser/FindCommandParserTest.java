package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.EMPTY_FIELDS_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonMatchesPredicate;
import seedu.address.testutil.PersonMatchesPredicateBuilder;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String expectedMessage = EMPTY_FIELDS_MESSAGE;
        //blank input
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        //empty name
        assertParseFailure(parser, " n/", expectedMessage);
        //empty email
        assertParseFailure(parser, " e/  ", expectedMessage);
        //empty phone
        assertParseFailure(parser, " p/  ", expectedMessage);
        //empty location
        assertParseFailure(parser, " l/  ", expectedMessage);
        //empty gender
        assertParseFailure(parser, " g/  ", expectedMessage);
        //empty rating
        assertParseFailure(parser, " r/  ", expectedMessage);
        //empty type
        assertParseFailure(parser, " typ/  ", expectedMessage);
        //empty username
        assertParseFailure(parser, " git/  ", expectedMessage);
        //empty specialisation
        assertParseFailure(parser, " s/  ", expectedMessage);
        //empty tags
        assertParseFailure(parser, " t/  ", expectedMessage);
        //empty module codes
        assertParseFailure(parser, " m/  ", expectedMessage);
        //empty year
        assertParseFailure(parser, " y/  ", expectedMessage);
        //empty username
        assertParseFailure(parser, " git/  ", expectedMessage);
        //empty office hour
        assertParseFailure(parser, " o/  ", expectedMessage);
        //all fields empty
        assertParseFailure(parser, " n/ m/ t/ e/ p/ l/ git/ typ/ o/ s/ y/ r/ g/  ", expectedMessage);
    }

    @Test
    public void parse_validArgsForName_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withNamesList(Arrays.asList("Alice Bob".split("\\s+"))).build());
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForEmail_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withEmailsList(Arrays.asList("alice@example.com", "alex@example.com")).build());
        assertParseSuccess(parser, " " + PREFIX_EMAIL + "alice@example.com alex@example.com ",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_EMAIL + " \n alice@example.com \n \t"
                + " alex@example.com  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForPhone_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withPhonesList(Arrays.asList("123456789", "987654321")).build());
        assertParseSuccess(parser, " " + PREFIX_PHONE + "123456789 987654321 ",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_PHONE + " \n 123456789 \n \t"
                + " 987654321  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForGender_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withGenderList(Arrays.asList("m", "f")).build());
        assertParseSuccess(parser, " " + PREFIX_GENDER + "m f ",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_GENDER + " \n m \n \t"
                + " f  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForLocation_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withLocationsList(Arrays.asList("UTown Residences COM3 - 01".split("\\s+"))).build());
        assertParseSuccess(parser, " " + PREFIX_LOCATION + "UTown Residences COM3 - 01 ",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_LOCATION + " \n UTown Residences \n \t"
                + " COM3 - 01  \t", expectedFindCommand);
    }
    @Test
    public void parse_validArgsForYear_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withYearsList(Arrays.asList("1 2 3 4".split("\\s+"))).build());
        assertParseSuccess(parser, " " + PREFIX_YEAR + "1 2 3 4",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_YEAR + " \n 1 \n \t"
                + " 2           3   \t 4", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForSpecialisation_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withSpecList(Arrays.asList("Discrete Math, Networks".toLowerCase()
                        .trim().split("\\s*,\\s*"))).build());
        assertParseSuccess(parser, " " + PREFIX_SPECIALISATION + "discrete math, networks",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_SPECIALISATION + " \n discrete    math, \n \t"
                + "        \t networks", expectedFindCommand);
    }
}
