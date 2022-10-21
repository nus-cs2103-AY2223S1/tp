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
 * Includes a Social media to an existing person in uNivUSal.
 */
public class IncludeCommand extends Command {

    enum Socials {
        WHATSAPP,
        TELEGRAM,
        EMAIL,
        INSTAGRAM
    }

    public static final String COMMAND_WORD = "include";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Includes a Social media link to a specified entry in uNivUSal.\n"
            + "Example: " + COMMAND_WORD + " 1 s/TELEGRAM #/t.me/JohnDoe321";

    public static final String MESSAGE_SUCCESS = "New link added";
    public static final String MESSAGE_WRONG_SOCIAL = "No such Social media";

    private final Index index;
    private final String social;
    private final String link;

    /**
     * @param index of the person in the filtered person list to edit
     * @param social details to edit the person with
     * @param link details to edit the person with
     */
    public IncludeCommand(Index index, String social, String link) {
        requireNonNull(index);
        requireNonNull(social);
        requireNonNull(link);

        this.index = index;
        this.social = social;
        this.link = link;
    }

    /**
     * Finds the Social Media of the person to be edited
     * @param social To be edited
     * @return Socials to be edited
     * @throws CommandException
     */
    public Socials findSocial(String social) throws CommandException {
        System.out.println(social);
        for (Socials s : Socials.values()) {
            System.out.println(s);
            if (social.equalsIgnoreCase(s.name())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Includes the link to the Socials s of the person p
     * @param p Person to be edited
     * @param s Socials to be edited
     * @throws CommandException
     */
    public void include(Person p, Socials s) throws CommandException {
        switch(s) {

        case WHATSAPP:
            p.getSocial().addWhatsapp(link);
            break;

        case TELEGRAM:
            p.getSocial().addTelegram(link);
            break;

        case EMAIL:
            p.getSocial().addEmail(link);
            break;

        case INSTAGRAM:
            p.getSocial().addInstagram(link);
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
        include(personToEdit, socialToEdit); //Includes the new social to the person to edit.

        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        UndoCommand.saveBeforeMod(this, pastAddressBook, model.getAddressBook());
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
