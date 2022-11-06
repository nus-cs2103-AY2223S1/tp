package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.link.exceptions.DuplicateLinkException;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

/**
 * Adds new links to an existing person in the address book.
 */
public class AddLinkCommand extends Command {

    public static final String COMMAND_WORD = "addlink";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds links to the person identified"
            + "by the index number in the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "addLink INDEX l/LINK\n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "l/https://www.instagram.com"
            + "l/https://github.com";

    public static final String MESSAGE_ADD_LINKS_SUCCESS = "Added links to the Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists";

    private final Index index;
    private final Set<Link> links;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param links to be added to the person
     */
    public AddLinkCommand(Index index, Set<Link> links) {
        requireAllNonNull(index, links);
        this.index = index;
        this.links = links;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUpdate = lastShownList.get(index.getZeroBased());
        UniqueTagTypeMap tagMap = new UniqueTagTypeMap();
        tagMap.setTagTypeMap(personToUpdate.getTags());
        Set<Link> updatedLinks;
        try {
            updatedLinks = personToUpdate.mergeLinks(links);
        } catch (DuplicateLinkException d) {
            throw new CommandException(d.getMessage());
        }
        Person updatedPerson = new Person(personToUpdate.getName(), personToUpdate.getPhone(),
                personToUpdate.getEmail(), personToUpdate.getAddress(), tagMap, personToUpdate.getStatus(),
                personToUpdate.getNote(), personToUpdate.getRating(), updatedLinks);
        if (!personToUpdate.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToUpdate, updatedPerson);

        return new CommandResult(String.format(MESSAGE_ADD_LINKS_SUCCESS, updatedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLinkCommand)) {
            return false;
        }

        // state check
        AddLinkCommand e = (AddLinkCommand) other;
        return index.equals(e.index)
                && links.equals(e.links);
    }
}
