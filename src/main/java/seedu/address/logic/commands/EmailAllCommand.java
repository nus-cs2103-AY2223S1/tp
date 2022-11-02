package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.IsPersonInGroup;
import seedu.address.model.person.Person;
import seedu.address.model.social.exceptions.SocialException;


/**
 * Emails the entire group.
 */
public class EmailAllCommand extends Command {

    public static final String COMMAND_WORD = "emailall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens email in web browser containing people "
            + "of the identified group. "
            + "Group names should not contain spaces.\n"
            + "Parameters: GROUPNAME\n"
            + "Example: " + COMMAND_WORD + " friends";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_SUCCESS = "Successfully opened web browser.";
    public static final String MESSAGE_GROUP_NOT_FOUND = "The group does not exist.";
    public static final String MESSAGE_ERROR = "One or more members of this group does not have an email set!\n"
            + "Try adding an email using the edit command!";

    private final Group groupToEmail;
    private final IsPersonInGroup predicate;

    /**
     * @param groupToEmail group to email
     */
    public EmailAllCommand(Group groupToEmail) {
        requireNonNull(groupToEmail);
        this.groupToEmail = groupToEmail;
        this.predicate = new IsPersonInGroup(groupToEmail);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getAddressBook().getPersonList();
        List<Group> groupList = new ArrayList<>();

        personList.forEach((person) -> groupList.addAll(person.getGroups()));
        if (!groupList.contains(groupToEmail)) {
            throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
        }

        model.updateGroupedPersonList(predicate);
        emailAll(model);

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    private void emailAll(Model model) throws CommandException {
        ObservableList<Person> groupPersons = model.getGroupedPersonList();
        StringBuilder sb = new StringBuilder().append("mailto:");
        try {
            for (Person person : groupPersons) {
                if (person.getEmail() == null) {
                    throw new SocialException("No Email Link");
                }
                sb.append(",").append(person.getEmail());
            }
            URI uri = new URI(sb.toString());
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(uri);
        } catch (SocialException | URISyntaxException | IOException s) {
            throw new CommandException(MESSAGE_ERROR);
        }
    }

}
