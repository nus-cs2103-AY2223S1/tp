package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.net.URI;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.social.exceptions.SocialException;

/**
 * Goes to a Social media link of an existing person in uNivUSal.
 */
public class OpenCommand extends Command {

    enum Socials {
        WHATSAPP,
        TELEGRAM,
        EMAIL,
        INSTAGRAM,
        PREFERRED
    }

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a Social media link of a specified entry in uNivUSal.\n"
            + "Example: " + COMMAND_WORD + " 1 s/TELEGRAM";

    public static final String MESSAGE_SUCCESS = "Opening link";
    public static final String MESSAGE_WRONG_SOCIAL = "No such Social media.\n"
            + "Valid Social media includes: WHATSAPP, TELEGRAM, EMAIL, INSTAGRAM, PREFERRED";
    public static final String MESSAGE_MISSING_LINK = "There is no link at the specified entry";
    public static final String MESSAGE_BAD_LINK =
            "The link at the specified location does not work, try entering a new link";

    private static final String SCHEME = "http://";

    private static final String WHATSAPP_DOMAIN = "wa.me/";

    private static final String TELEGRAM_DOMAIN = "t.me/";

    private static final String EMAIL_DOMAIN = "mailto:";

    private static final String INSTAGRAM_DOMAIN = "instagram.com/";

    private final Index index;
    private final String social;
    private URI uri;

    /**
     * @param index of the person in the filtered person list to edit
     * @param social details to edit the person with
     */
    public OpenCommand(Index index, String social) {
        requireNonNull(index);
        requireNonNull(social);

        this.index = index;
        this.social = social;
    }

    /**
     * Finds the Social Media of the person to be edited
     * @param social To be edited
     * @return Socials to be edited
     * @throws CommandException
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
     * Opens the link of the Social s of the Person p
     * @param p Person to get
     * @param s Socials to get
     * @throws CommandException
     */
    public void open(Person p, Socials s) throws CommandException {
        if (s == null) {
            throw new CommandException(MESSAGE_WRONG_SOCIAL);
        } else {
            try {
                switch(s) {

                case WHATSAPP: {
                    p.getSocial().openWhatsapp();
                    break;
                }

                case TELEGRAM: {
                    p.getSocial().openTelegram();
                    break;
                }

                case EMAIL:
                    p.getSocial().openEmail();
                    break;

                case INSTAGRAM: {
                    p.getSocial().openInstagram();
                    break;
                }

                case PREFERRED: {
                    p.getSocial().openPreferred();
                    break;
                }
                default:
                    throw new CommandException(MESSAGE_WRONG_SOCIAL);
                }
            } catch (CommandException e) {
                throw new CommandException(MESSAGE_WRONG_SOCIAL);
            } catch (SocialException e) {
                throw new CommandException(e.getMessage());
            }
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
        Person personToOpen = lastShownList.get(index.getZeroBased());
        Socials socialToOpen = findSocial(social);

        open(personToOpen, socialToOpen);

        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
