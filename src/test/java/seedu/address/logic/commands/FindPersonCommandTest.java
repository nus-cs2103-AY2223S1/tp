package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.util.List;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.EmailConverter;
import seedu.address.logic.parser.NameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new FindPersonCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Name.class, new NameConverter())
            .registerConverter(Email.class, new EmailConverter());

    @Test
    public void execute_multipleNameKeywords_multipleMembersFound() {
        String[] keywords = {"Alice", "Carl"};
        commandLine.parseArgs(keywords);
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Alice", "Carl"));
        model.updateFilteredPersonList(predicate);
        CommandResult expectedResult = new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
}
