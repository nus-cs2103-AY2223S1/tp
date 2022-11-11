package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.stubs.ModelStub;
import seedu.address.logic.commands.stubs.ModelStubWithLink;
import seedu.address.logic.parser.LinkNameConverter;
import seedu.address.logic.parser.UrlConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.team.Link;
import seedu.address.model.team.LinkName;
import seedu.address.model.team.Url;
import seedu.address.testutil.LinkBuilder;
import seedu.address.testutil.LinkUtil;

class AddLinkCommandTest {
    private final Model model = new ModelManager();

    private final Model expectedModel = new ModelManager();
    private final Command commandToBeTested = new AddLinkCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(LinkName.class, new LinkNameConverter())
            .registerConverter(Url.class, new UrlConverter());
    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                AddLinkCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_linkAcceptedByModel_addSuccessful() {
        Link validLink = new LinkBuilder().build();
        expectedModel.addLink(validLink);
        commandLine.parseArgs(LinkUtil.convertLinkToArgs(validLink));
        CommandResult expectedResult = new CommandResult(String.format(AddLinkCommand.MESSAGE_SUCCESS, validLink));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    void execute_linkAlreadyExist_throwsCommandException() {
        Link validLink = new LinkBuilder().build();
        ModelStub modelStub = new ModelStubWithLink(validLink);
        commandLine.parseArgs(LinkUtil.convertLinkToArgs(validLink));
        assertThrows(CommandException.class, AddLinkCommand.MESSAGE_DUPLICATE_LINK, ()
                -> commandToBeTested.execute(modelStub));
    }

}
