package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * Excludes a Social media to an existing person in uNivUSal.
 */
public class ExcludeCommand extends Command {

    enum Socials {
        WHATSAPP,
        TELEGRAM,
        EMAIL,
        INSTAGRAM,
        PREFERRED
    }

    public static final String COMMAND_WORD = "exclude";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Excludes the Social media Link to a specified entry in uNivUSal.\n"
            + "Example: " + COMMAND_WORD + " 1 s/TELEGRAM";

    public static final String MESSAGE_SUCCESS = "Link deleted";
    public static final String MESSAGE_WRONG_SOCIAL = "No such Social media.\n"
            + "Valid Social media includes: WHATSAPP, TELEGRAM, EMAIL, INSTAGRAM, PREFERRED";

    private final Index index;
    private final String social;

    /**
     * @param index of the person in the filtered person list to edit
     * @param social details to edit the person with
     */
    public ExcludeCommand(Index index, String social) {
        requireNonNull(index);
        requireNonNull(social);

        this.index = index;
        this.social = social;
    }

    /**
     * Finds the Social Media of the person to be edited
     * @param social To be edited
     * @return Socials to be edited
     */
    public Socials findSocial(String social) {
        for (Socials s : Socials.values()) {
            if (social.equalsIgnoreCase(s.name())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Excludes the link to the Socials s of the Person p
     * @param p Person to be edited
     * @param s Socials to be edited
     * @throws CommandException
     */
    public void exclude(Person p, Socials s) throws CommandException {
        if (s == null) {
            throw new CommandException(MESSAGE_WRONG_SOCIAL);
        }

        switch(s) {

        case WHATSAPP:
            p.getSocial().deleteWhatsapp();
            break;

        case TELEGRAM:
            p.getSocial().deleteTelegram();
            break;

        case EMAIL:
            p.getSocial().deleteEmail();
            break;

        case INSTAGRAM:
            p.getSocial().deleteInstagram();
            break;

        case PREFERRED:
            p.getSocial().deletePreferred();
            break;

        default:
            throw new CommandException(MESSAGE_WRONG_SOCIAL);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        //gets the person to be edited;
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Socials socialToEdit = findSocial(social);
        exclude(personToEdit, socialToEdit); //Includes the new social to the person to edit.

        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.setPerson(personToEdit, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
