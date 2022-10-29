package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.EMPTY_FIELDS_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

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
        //empty all tags search
        assertParseFailure(parser, " t/all/  ", FindCommand.INVALID_TAGS_MESSAGE);
        //empty module codes
        assertParseFailure(parser, " m/  ", expectedMessage);
        //empty all module search
        assertParseFailure(parser, " m/all/  ", FindCommand.INVALID_MODULES_MESSAGE);
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

    @Test
    public void parse_validArgsForRating_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withRatingsList(Arrays.asList("1 2 3 4 5".split("\\s+"))).build());
        assertParseSuccess(parser, " " + PREFIX_RATING + "1 2 3 4 5",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_RATING + " \n 1 \n \t"
                + " 2           3   \t 4    5", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForUserName_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withUserNamesList(Arrays.asList("wongwong githubmaster coder4Life".split("\\s+"))).build());
        assertParseSuccess(parser, " " + PREFIX_GITHUBUSERNAME + "wongwong githubmaster coder4Life",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_GITHUBUSERNAME +"\n wongwong \t githubmaster \t coder4Life \n", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForTypes_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withTypesList(Arrays.asList("stu ta prof".split("\\s+"))).build());
        assertParseSuccess(parser, " " + PREFIX_TYPE + "stu ta prof",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TYPE +"\n stu  \t ta \t prof \n", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgsForTypes_throwsParseException() {
        String expectedMessage = FindCommand.INVALID_TYPES_MESSAGE;
        assertParseFailure(parser, " typ/professor ", expectedMessage);
        assertParseFailure(parser, " typ/student ", expectedMessage);
        assertParseFailure(parser, " typ/teaching assistant ", expectedMessage);
        assertParseFailure(parser, " typ/student professor teaching assistant ", expectedMessage);
    }

    @Test
    public void parse_validArgsForOfficeHour_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withOfficeHoursList(Arrays.asList("1-15:00-2 2:12:00-2".split("\\s+"))).build());
        assertParseSuccess(parser, " " + PREFIX_OFFICEHOUR + "1-15:00-2 2:12:00-2",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_OFFICEHOUR + "\n    1-15:00-2 \t  2:12:00-2 \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForModuleCodeSet_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withModulesSet(new HashSet<>(Arrays.asList("CS1231S CS2100 CS2103T"
                        .toLowerCase().split("\\s+"))), false).build());
        assertParseSuccess(parser, " " + PREFIX_MODULE_CODE + "CS1231S CS2100 CS2103T",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MODULE_CODE+ "\n    CS1231S \t  CS2100 \t CS2103T", expectedFindCommand);

        //all search parse success
        expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withModulesSet(new HashSet<>(Arrays.asList("CS1231S CS2100 CS2103T"
                        .toLowerCase().split("\\s+"))), true).build());
        assertParseSuccess(parser, " " + PREFIX_MODULE_CODE + "all/CS1231S CS2100 CS2103T",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MODULE_CODE + "\n    all/    CS1231S \t  CS2100 \t CS2103T", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForTagSet_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withTagsSet(new HashSet<>(Arrays.asList("friends goodCoder owesMoney"
                        .toLowerCase().split("\\s+"))), false).build());
        assertParseSuccess(parser, " " + PREFIX_TAG + "friends goodCoder owesMoney",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + "\n    friends \t  goodCoder \t owesMoney", expectedFindCommand);

        //all search parse success
        expectedFindCommand = new FindCommand(new PersonMatchesPredicateBuilder()
                .withTagsSet(new HashSet<>(Arrays.asList("friends goodCoder owesMoney"
                        .toLowerCase().split("\\s+"))), true).build());
        assertParseSuccess(parser, " " + PREFIX_TAG + " all/friends goodCoder owesMoney",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + " \nall/   friends \t  goodCoder \t owesMoney", expectedFindCommand);
    }

    @Test
    public void parse_allArgsValid_returnsFindCommand() {
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name1")).withEmailsList(Collections.singletonList("email1"))
                .withGenderList(Collections.singletonList("gender1")).withLocationsList(Collections.singletonList("location1"))
                .withModulesSet(Collections.singleton("module1"), true).withPhonesList(Collections.singletonList("111"))
                .withOfficeHoursList(Collections.singletonList("officeHour1")).withRatingsList(Collections.singletonList("1"))
                .withSpecList(Collections.singletonList("spec1")).withTypesList(Collections.singletonList("stu"))
                .withTagsSet(Collections.singleton("tag1"), true).withYearsList(Collections.singletonList("year1"))
                .withUserNamesList(Collections.singletonList("username1")).build();
        FindCommand expectedFindCommand = new FindCommand(predicate);
        String userInput = " n/name1 e/email1 g/gender1 l/location1 m/all/module1 p/111 o/officeHour1 r/1 s/spec1 "
                + "typ/stu y/year1 git/username1 t/all/tag1";

        assertParseSuccess(parser, userInput, expectedFindCommand);
        //multiple whitespaces between fields
        userInput = " \n n/name1 \n e/email1 \n g/gender1 \n l/location1 \n m/all/module1 \n p/111 \n o/officeHour1 \n r/1 \n s/spec1 "
                 + "\n typ/stu \n  y/year1 \n  git/username1 \n  t/all/tag1";
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_oneArgsEmpty_throwsParseException() {

        String userInput = " n/name1 e/email1 g/gender1 l/ m/all/module1 p/111 o/officeHour1 r/1 s/spec1 "
                + "typ/stu y/year1 git/username1 t/tags1  ";

        assertParseFailure(parser, userInput, EMPTY_FIELDS_MESSAGE);
    }
}
