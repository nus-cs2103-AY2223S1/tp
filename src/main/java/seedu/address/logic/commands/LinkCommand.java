package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.Internship;
import seedu.address.model.person.Person;

/**
 * Links a Person and an Internship.
 */
public class LinkCommand extends Command {

    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a Person and an Internship.\n"
            + "Parameters: "
            + PREFIX_PERSON + "PERSON_INDEX "
            + PREFIX_INTERNSHIP + "INTERNSHIP_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON + "1 "
            + PREFIX_INTERNSHIP + "1 ";
    public static final String MESSAGE_SUCCESS = "Person %1$s linked to Internship %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in InterNUS";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in InterNUS";
    // person before internship
    public static final String MESSAGE_LINKED_PERSON = "Person %1$s is already linked to Internship %2$s";
    public static final String MESSAGE_LINKED_INTERNSHIP = "Internship %2$s is already linked to Person %1$s";


    private final Index personIndex;
    private final Index internshipIndex;

    /**
     * Creates a LinkCommand to link a {@code Person} and an {@code Internship}
     * based on the given {@code personIndex} and {@code internshipIndex}
     */
    public LinkCommand(Index personIndex, Index internshipIndex) {
        this.personIndex = personIndex;
        this.internshipIndex = internshipIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownPersonList = model.getFilteredPersonList();

        //checking if person index and internship index are valid
        if (personIndex != null && personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        List<Internship> lastShownInternshipList = model.getFilteredInternshipList();

        if (internshipIndex != null && internshipIndex.getZeroBased() >= lastShownInternshipList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Person personToLink = lastShownPersonList.get(personIndex.getZeroBased());
        Internship internshipToLink = lastShownInternshipList.get(internshipIndex.getZeroBased());

        // Throws an exception when specified person or internship is already linked
        if (personToLink.getInternshipId() != null) {
            throw new CommandException(String.format(
                    MESSAGE_LINKED_PERSON,
                    personToLink.getName(),
                    model.findInternshipById(personToLink.getInternshipId()).getDisplayName()));
        } else if (internshipToLink.getContactPersonId() != null) {
            throw new CommandException(String.format(
                    MESSAGE_LINKED_INTERNSHIP,
                    model.findPersonById(internshipToLink.getContactPersonId()).getName(),
                    internshipToLink.getDisplayName()));
        }

        Person linkedPerson = new Person(
                personToLink.getPersonId(),
                personToLink.getName(),
                personToLink.getEmail(),
                personToLink.getPhone(),
                internshipToLink.getInternshipId(),
                personToLink.getTags(),
                personToLink.getCompany()
        );

        Internship linkedInternship = new Internship(
                internshipToLink.getInternshipId(),
                internshipToLink.getCompanyName(),
                internshipToLink.getInternshipRole(),
                internshipToLink.getInternshipStatus(),
                personToLink.getPersonId(),
                internshipToLink.getInterviewDate()
        );

        if (!personToLink.isSamePerson(linkedPerson) && model.hasPerson(linkedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!internshipToLink.isSameInternship(linkedInternship) && model.hasInternship(linkedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.setPerson(personToLink, linkedPerson);
        model.setInternship(internshipToLink, linkedInternship);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, linkedPerson.getName(), linkedInternship.getDisplayName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LinkCommand)) {
            return false;
        }

        LinkCommand otherCommand = (LinkCommand) other;
        return personIndex.equals(otherCommand.personIndex)
                && internshipIndex.equals(otherCommand.internshipIndex);
    }
}
