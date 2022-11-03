package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVEMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.UserCommand;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Module;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Person;
import seedu.address.model.person.user.User;
import seedu.address.model.tag.Tag;

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
     * Returns a user command string for adding the {@code user}.
     */
    public static String getUserCommand(User user) {
        return UserCommand.COMMAND_WORD + " " + getUserDetails(user);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_GITHUB + person.getGithub().value + " ");
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getCurrModules().stream().forEach(
                m -> sb.append(PREFIX_CURRENTMOD + m.moduleName + " ")
        );
        person.getPrevModules().stream().forEach(
                m -> sb.append(PREFIX_PREVIOUSMOD + m.moduleName + " ")
        );
        person.getPlanModules().stream().forEach(
                m -> sb.append(PREFIX_PLANNEDMOD + m.moduleName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code user}'s details.
     */
    public static String getUserDetails(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + user.getName().fullName + " ");
        sb.append(PREFIX_PHONE + user.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + user.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + user.getAddress().value + " ");
        sb.append(PREFIX_GITHUB + user.getGithub().value + " ");
        user.getCurrModules().stream().forEach(
                m -> sb.append(PREFIX_CURRENTMOD + m.moduleName + " ")
        );
        user.getPrevModules().stream().forEach(
                m -> sb.append(PREFIX_PREVIOUSMOD + m.moduleName + " ")
        );
        user.getPlanModules().stream().forEach(
                m -> sb.append(PREFIX_PLANNEDMOD + m.moduleName + " ")
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
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getGithub().ifPresent(github -> sb.append(PREFIX_GITHUB).append(github.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    public static String getEditModuleDescriptorDetails(EditModuleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        Set<CurrentModule> currModules = descriptor.getCurrModules();
        Set<PreviousModule> prevModules = descriptor.getPrevModules();
        Set<PlannedModule> planModules = descriptor.getPlanModules();
        Set<Module> modsToRemove = descriptor.getModulesToRemove();

        if (!currModules.isEmpty()) {
            currModules.forEach(s -> sb.append(PREFIX_CURRENTMOD).append(s.moduleName).append(" "));
        }

        if (!prevModules.isEmpty()) {
            prevModules.forEach(s -> sb.append(PREFIX_PREVIOUSMOD).append(s.moduleName).append(" "));
        }

        if (!planModules.isEmpty()) {
            planModules.forEach(s -> sb.append(PREFIX_PLANNEDMOD).append(s.moduleName).append(" "));
        }

        if (!modsToRemove.isEmpty()) {
            modsToRemove.forEach(s -> sb.append(PREFIX_REMOVEMOD).append(s.moduleName).append(" "));
        }
        return sb.toString();
    }

}
