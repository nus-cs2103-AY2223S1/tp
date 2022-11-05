package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FindMemberCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.util.List;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.parser.EmailConverter;
import seedu.address.logic.parser.NameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalPersons;

public class FindMemberCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new FindMemberCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Name.class, new NameConverter())
            .registerConverter(Email.class, new EmailConverter());

    @Test
    public void execute_multipleNameKeywords_multipleMembersFound() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        model.getTeam().addMember(TypicalPersons.BENSON);
        model.getTeam().addMember(TypicalPersons.CARL);
        String[] keywords = {FLAG_NAME_STR, "Alice", "Carl"};
        commandLine.parseArgs(keywords);
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Alice", "Carl"));
        model.updateFilteredMembersList(predicate);
        CommandResult expectedResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(), " Alice Carl"));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_multipleEmailKeywords_multipleMemberFound() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        model.getTeam().addMember(TypicalPersons.BENSON);
        model.getTeam().addMember(TypicalPersons.CARL);
        String[] keywords = {FLAG_EMAIL_STR, "Alice", "Heinz"};
        commandLine.parseArgs(keywords);
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(List.of("Alice", "Heinz"));
        model.updateFilteredMembersList(predicate);
        CommandResult expectedResult = new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(), " Alice Heinz"));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
}
