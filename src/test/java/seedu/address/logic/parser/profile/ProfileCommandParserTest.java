package seedu.address.logic.parser.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.DeleteProfileCommand;
import seedu.address.logic.commands.profile.EditProfileCommand;
import seedu.address.logic.commands.profile.EditProfileCommand.EditProfileDescriptor;
import seedu.address.logic.commands.profile.FindProfileCommand;
import seedu.address.logic.commands.profile.ProfileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.profile.NameContainsKeywordsPredicate;
import seedu.address.model.profile.Profile;
import seedu.address.testutil.EditProfileDescriptorBuilder;
import seedu.address.testutil.ProfileBuilder;
import seedu.address.testutil.ProfileUtil;

public class ProfileCommandParserTest {

    private ProfileCommandParser parser = new ProfileCommandParser();

    @Test
    public void parse_missingOptionFlag_failure() {
        assertParseFailure(parser, " HELLO WORLD",
                String.format(Messages.MESSAGE_FLAG_NOT_SPECIFIED,
                        ProfileCommand.PROFILE_FORMAT + ProfileCommand.VALID_FLAGS));

        assertParseFailure(parser, " TEXT -e", ProfileCommand.OPTION_WRONG_ORDER);

        assertParseFailure(parser, " TEXT -e -e", ProfileCommand.OPTION_WRONG_ORDER_NO_MULTIPLE);
    }

    @Test
    public void parse_multipleOptionFlags_failure() {
        assertParseFailure(parser, " -e -e", ProfileCommand.OPTION_NO_MULTIPLE);
        assertParseFailure(parser, " -e Hello -e", ProfileCommand.OPTION_NO_MULTIPLE);
        assertParseFailure(parser, " -hello -world -cs2103t", ProfileCommand.OPTION_NO_MULTIPLE);
    }

    @Test
    public void parse_profileOption_failure() {
        assertParseFailure(parser, " -unknowncommand",
                ProfileCommand.OPTION_UNKNOWN + ProfileCommand.VALID_FLAGS);
    }

    @Test
    public void parse_addOption_success() throws Exception {
        Profile profile = new ProfileBuilder().build();
        AddProfileCommand command =
                (AddProfileCommand) parser.parse(ProfileUtil.getAddProfileCommand(profile));
        assertEquals(new AddProfileCommand(profile), command);
    }

    @Test
    public void parse_deleteOption_success() throws Exception {
        DeleteProfileCommand command = (DeleteProfileCommand) parser.parse(
                " " + PREFIX_OPTION + DeleteProfileCommand.COMMAND_OPTION + " "
                        + INDEX_FIRST_PROFILE.getOneBased());
        assertEquals(new DeleteProfileCommand(INDEX_FIRST_PROFILE), command);
    }

    @Test
    public void parse_editOption_success() throws Exception {
        Profile profile = new ProfileBuilder().build();
        EditProfileDescriptor descriptor = new EditProfileDescriptorBuilder(profile).build();
        EditProfileCommand command = (EditProfileCommand) parser.parse(
                " " + PREFIX_OPTION + EditProfileCommand.COMMAND_OPTION + " "
                        + INDEX_FIRST_PROFILE.getOneBased() + " "
                        + ProfileUtil.getEditProfileDescriptorDetails(descriptor));
        assertEquals(new EditProfileCommand(INDEX_FIRST_PROFILE, descriptor), command);
    }

    @Test
    public void parse_findOption_success() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindProfileCommand command = (FindProfileCommand) parser.parse(
                " " + PREFIX_OPTION + FindProfileCommand.COMMAND_OPTION + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindProfileCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }
}
