package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.LINK_FACEBOOK;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.logic.parser.LinkNameConverter;
import seedu.address.logic.parser.UrlConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Link;
import seedu.address.model.team.LinkName;
import seedu.address.model.team.Url;
import seedu.address.testutil.LinkBuilder;
import seedu.address.testutil.LinkUtil;

class EditLinkCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new EditLinkCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter())
            .registerConverter(LinkName.class, new LinkNameConverter())
            .registerConverter(Url.class, new UrlConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addLink(LINK_GOOGLE);
        expectedModel.getTeam().addLink(LINK_GOOGLE);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Link validLink = new LinkBuilder(LINK_FACEBOOK).build();
        expectedModel.getTeam().setLink(LINK_GOOGLE, LINK_FACEBOOK);
        commandLine.parseArgs(LinkUtil.convertEditLinkToArgs(validLink, 1));
        CommandResult expectedResult = new CommandResult(String.format(EditLinkCommand.MESSAGE_EDIT_LINK_SUCCESS,
                validLink));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Link validLink = new LinkBuilder(LINK_GOOGLE).withName(VALID_NAME_FACEBOOK).build();
        expectedModel.getTeam().setLink(LINK_GOOGLE, validLink);
        commandLine.parseArgs(LinkUtil.convertEditPartialLinkToArgs(validLink, 1));
        CommandResult expectedResult = new CommandResult(String.format(EditLinkCommand.MESSAGE_EDIT_LINK_SUCCESS,
                validLink));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_duplicateLinkUnfilteredList_throwsCommandException() {
        Link googleLink = LINK_GOOGLE;
        Link facebookLink = LINK_FACEBOOK;
        model.addLink(facebookLink);
        Link editedGoogleToFacebook = new LinkBuilder(googleLink).withName(VALID_NAME_FACEBOOK).build();
        commandLine.parseArgs(LinkUtil.convertEditLinkToArgs(editedGoogleToFacebook, 1));
        assertThrows(CommandException.class, EditLinkCommand.MESSAGE_DUPLICATE_LINK, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_outOfBounds_throwsCommandException() {
        Link validLink = new LinkBuilder().build();
        commandLine.parseArgs(LinkUtil.convertEditLinkToArgs(validLink, 2));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_LINK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    void testEquals() {
    }
}
