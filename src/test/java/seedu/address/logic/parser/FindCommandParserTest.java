package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.MESSAGE_NO_FIELD_GIVEN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.CapContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GenderContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GraduationDateContainsKeywordsPredicate;
import seedu.address.model.person.predicates.JobIdContainsKeywordsPredicate;
import seedu.address.model.person.predicates.JobTitleContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ListOfContainsKeywordsPredicates;
import seedu.address.model.person.predicates.MajorContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;
import seedu.address.model.person.predicates.UniversityContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyFieldSpecifier_throwsParseException() {
        assertParseFailure(parser, "alice Bob ", String.format(MESSAGE_NO_FIELD_GIVEN, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        ListOfContainsKeywordsPredicates predicateList = ListOfContainsKeywordsPredicates
                .newListOfContainsKeywordsPredicates();
        AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(
                Arrays.asList("Main", "Street"));
        CapContainsKeywordsPredicate capPredicate = new CapContainsKeywordsPredicate(Arrays.asList("3.5"));
        EmailContainsKeywordsPredicate emailPredicate = new EmailContainsKeywordsPredicate(Arrays.asList("gmail"));
        GenderContainsKeywordsPredicate genderPredicate = new GenderContainsKeywordsPredicate(Arrays.asList("Female"));
        GraduationDateContainsKeywordsPredicate graduationDatePredicate = new GraduationDateContainsKeywordsPredicate(
                Arrays.asList("05-2024"));
        JobIdContainsKeywordsPredicate jobIdPredicate = new JobIdContainsKeywordsPredicate(Arrays.asList("JID1234"));
        JobTitleContainsKeywordsPredicate jobTitlePredicate = new JobTitleContainsKeywordsPredicate(
                Arrays.asList("Software", "Engineer"));
        MajorContainsKeywordsPredicate majorPredicate = new MajorContainsKeywordsPredicate(
                Arrays.asList("Computer", "Science"));
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(Arrays.asList("12345"));
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(Arrays.asList("offered", "KIV"));
        UniversityContainsKeywordsPredicate universityPredicate = new UniversityContainsKeywordsPredicate(
                Arrays.asList("University"));

        // no leading and trailing whitespaces
        predicateList.addPredicate(addressPredicate);
        FindCommand expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street", expectedFindCommand);

        predicateList.addPredicate(capPredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5", expectedFindCommand);

        predicateList.addPredicate(emailPredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail", expectedFindCommand);

        predicateList.addPredicate(genderPredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female", expectedFindCommand);

        predicateList.addPredicate(graduationDatePredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024", expectedFindCommand);

        predicateList.addPredicate(jobIdPredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024 "
                + "ji/JID1234", expectedFindCommand);

        predicateList.addPredicate(jobTitlePredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024 "
                + "ji/JID1234 jt/Software Engineer", expectedFindCommand);

        predicateList.addPredicate(majorPredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024 "
                + "ji/JID1234 jt/Software Engineer m/Computer Science", expectedFindCommand);

        predicateList.addPredicate(namePredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024 "
                + "ji/JID1234 jt/Software Engineer m/Computer Science n/Alice Bob", expectedFindCommand);

        predicateList.addPredicate(phonePredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024 "
                + "ji/JID1234 jt/Software Engineer m/Computer Science n/Alice Bob p/12345", expectedFindCommand);

        predicateList.addPredicate(tagPredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024 "
                + "ji/JID1234 jt/Software Engineer m/Computer Science n/Alice Bob p/12345 "
                + "t/offered KIV", expectedFindCommand);

        predicateList.addPredicate(universityPredicate);
        expectedFindCommand = new FindCommand(predicateList);
        assertParseSuccess(parser, "a/Main Street c/3.5 e/gmail g/Female gd/05-2024 "
                + "ji/JID1234 jt/Software Engineer m/Computer Science n/Alice Bob p/12345 "
                + "t/offered KIV u/University", expectedFindCommand);


        // multiple whitespaces between keywords and fields
        assertParseSuccess(parser, "a/Main \n Street c/3.5 e/gmail \n \t g/Female gd/05-2024 "
                + "ji/JID1234 jt/Software  \t Engineer m/Computer \t Science \n  n/Alice Bob p/12345 "
                + "t/offered \t KIV  \n \t u/University", expectedFindCommand);
    }
}
