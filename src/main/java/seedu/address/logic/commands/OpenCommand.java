package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

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
    public static final String MESSAGE_WRONG_SOCIAL = "No such Social media";
    public static final String MESSAGE_MISSING_LINK = "There is no link at the specified entry";
    public static final String MESSAGE_BAD_LINK =
            "The link at the specified location does not work, try entering a new link";

    private static final String SCHEME = "http://";
    private static final String SCHEMES = "https://";

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
    public Socials findSocial(String social) throws CommandException {
        for (Socials s : Socials.values()) {
            if (social.equalsIgnoreCase(s.name())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Converts the string link to the URL to be ran.
     * @param link To be converted
     * @throws CommandException
     */
    public void convert(String link) throws CommandException {
        try {
            URI uri = new URI(link);
            this.uri = uri;
        } catch (URISyntaxException e) {
            throw new CommandException(MESSAGE_BAD_LINK);
        }
    }

    /**
     * Gets the link of the Social s of the Person p
     * @param p Person to get
     * @param s Socials to get
     * @return link of the Social s of the Person p
     * @throws CommandException
     */
    public String getLink(Person p, Socials s) throws CommandException {
        switch(s) {

        case WHATSAPP: {
            String link = p.getSocial().getWhatsapp();
            if (link.startsWith(SCHEME) || link.startsWith(SCHEMES)) {
                return link;
            } else {
                System.out.println(SCHEME + link);
                return SCHEME + link;
            }
        }

        case TELEGRAM: {
            String link = p.getSocial().getTelegram();
            if (link.startsWith(SCHEME) || link.startsWith(SCHEMES)) {
                return link;
            } else {
                System.out.println(SCHEME + link);
                return SCHEME + link;
            }
        }

        case EMAIL:
            return p.getSocial().getEmail();

        case INSTAGRAM: {
            String link = p.getSocial().getInstagram();
            if (link.startsWith(SCHEME) || link.startsWith(SCHEMES)) {
                return link;
            } else {
                System.out.println(SCHEME + link);
                return SCHEME + link;
            }
        }

        case PREFERRED: {
            String link = p.getSocial().getPreferredLink();
            if (link.startsWith(SCHEME) || link.startsWith(SCHEMES)) {
                return link;
            } else {
                System.out.println(SCHEME + link);
                return SCHEME + link;
            }
        }
        default:
            throw new CommandException(MESSAGE_WRONG_SOCIAL);
        }
    }

    /**
     * Opens the URI
     * @param uri to be opened
     * @throws CommandException
     */
    public void open(URI uri) throws CommandException {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            desktop.browse(uri);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_BAD_LINK);
        }
    }

    /**
     * Runs the command after we get the Person p and Socials s
     * @param p Person p
     * @param s Socials s
     * @throws CommandException
     */
    public void run(Person p, Socials s) throws CommandException {
        String link = getLink(p, s);
        if (link == null || link == "null") {
            throw new CommandException(MESSAGE_MISSING_LINK);
        }
        convert(link);
        open(this.uri);
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

        run(personToOpen, socialToOpen);

        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
