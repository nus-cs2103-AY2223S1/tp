package seedu.address.logic.commands;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Retriever;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.Parser;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class FindCommandParserTest {

    private Changer<Predicate<Person>> changerStub = ((model, item) -> model.updateFilteredPersonList(item));
    private Retriever<Integer> getSizeStub = ((model) -> model.getFilteredPersonList().size());

    private final Parser<FindCommand<Person>> parser = new FindCommandParser<>(changerStub, getSizeStub);


    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand<Person> expectedFindCommand = new FindCommand<>(
            new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Bob")), changerStub, getSizeStub);

        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
