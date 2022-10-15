package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.interest.Interest;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM + person.getTelegram().handle + " ");
        sb.append(PREFIX_GITHUB + person.getGitHub().username + " ");
        person.getInterests().stream().forEach(
            s -> sb.append(PREFIX_INTEREST + s.interestName + " ")
        );
        person.getMods().stream().forEach(
                s -> sb.append(PREFIX_MOD + s.modName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(PREFIX_TELEGRAM).append(telegram.handle).append(" "));
        descriptor.getGitHub().ifPresent(github -> sb.append(PREFIX_GITHUB).append(github.username).append(" "));
        if (descriptor.getInterests().isPresent()) {
            Set<Interest> tags = descriptor.getInterests().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_INTEREST);
            } else {
                tags.forEach(s -> sb.append(PREFIX_INTEREST).append(s.interestName).append(" "));
            }
        }
        if (descriptor.getMods().isPresent()) {
            ObservableList<Mod> mods = descriptor.getMods().get();
            if (mods.isEmpty()) {
                sb.append(PREFIX_MOD);
            } else {
                mods.forEach(s -> sb.append(PREFIX_MOD).append(s.modName).append(" "));
            }
        }
        return sb.toString();
    }
}
