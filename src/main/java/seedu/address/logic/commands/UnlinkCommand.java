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
 * Unlinks a Person and an Internship.
 */
public class UnlinkCommand extends Command {

    public static final String COMMAND_WORD = "unlink";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a Person and an Internship. "
            + "Parameters: "
            + PREFIX_PERSON + "PERSON_INDEX "
            + PREFIX_INTERNSHIP + "INTERNSHIP_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON + "1 "
            + PREFIX_INTERNSHIP + "1 ";
    public static final String MESSAGE_SUCCESS = "Person %1$s and Internship %2$s has been unlinked";
    // when only 1 person/internship is provided and person/internship has no link
    public static final String MESSAGE_UNLINKED_INTERNSHIP = "Internship %1$s does not have a contact person";
    public static final String MESSAGE_UNLINKED_PERSON = "Person %1$s is not linked to any internship";
    // when both person and internship is provided but incorrect link is given
    public static final String MESSAGE_INCORRECT_LINK_PERSON = "Person %1$s is not linked to Internship %2$s";
    public static final String MESSAGE_INCORRECT_LINK_INTERNSHIP = "Internship %2$s is not linked to Person %1$s";

    private final Index personIndex;
    private final Index internshipIndex;

    /*
    Not sure if UnlinkCommand(Person, Internship) is needed, hence commenting this for now
    /**
     * Creates an UnlinkCommand to link the specified {@code Person} {@code Internship}
     */
    /*
    public unLinkCommand(Person person, Internship internship) {
    }
     */

    /**
     * Creates an UnlinkCommand to unlink a {@code Person} and an {@code Internship}
     * based on the given {@code personIndex} and {@code internshipIndex}
     */
    public UnlinkCommand(Index personIndex, Index internshipIndex) {
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

        Person personToUnlink = personIndex != null
                ? lastShownPersonList.get(personIndex.getZeroBased())
                : null;
        Internship internshipToUnlink = internshipIndex != null
                ? lastShownInternshipList.get(internshipIndex.getZeroBased())
                : null;

        assert(personToUnlink != null || internshipToUnlink != null);

        personToUnlink = personToUnlink == null
                ? model.findPersonById(internshipToUnlink.getContactPersonId())
                : personToUnlink;
        internshipToUnlink = internshipToUnlink == null
                ? model.findInternshipById(personToUnlink.getInternshipId())
                : internshipToUnlink;

        if (personToUnlink == null) {
            throw new CommandException(String.format(MESSAGE_UNLINKED_INTERNSHIP, internshipToUnlink.getDisplayName()));
        } else if (internshipToUnlink == null) {
            throw new CommandException(String.format(MESSAGE_UNLINKED_PERSON, personToUnlink.getName()));
        }

        if (personToUnlink.getInternshipId() == null) {
            throw new CommandException(String.format(MESSAGE_UNLINKED_PERSON, personToUnlink.getName()));
        } else if (internshipToUnlink.getContactPersonId() == null) {
            throw new CommandException(String.format(MESSAGE_UNLINKED_INTERNSHIP, internshipToUnlink.getDisplayName()));
        }

        if (!personToUnlink.getInternshipId().equals(internshipToUnlink.getInternshipId())) {
            throw new CommandException(String.format(
                    MESSAGE_INCORRECT_LINK_PERSON,
                    personToUnlink.getName(),
                    internshipToUnlink.getDisplayName()));
        } else if (!internshipToUnlink.getContactPersonId().equals(personToUnlink.getPersonId())) {
            throw new CommandException(String.format(
                    MESSAGE_INCORRECT_LINK_INTERNSHIP,
                    personToUnlink.getName(),
                    internshipToUnlink.getDisplayName()));
        }

        Person unlinkedPerson = new Person(
                personToUnlink.getPersonId(),
                personToUnlink.getName(),
                personToUnlink.getEmail(),
                personToUnlink.getPhone(),
                null,
                personToUnlink.getTags(),
                personToUnlink.getCompany()
        );

        Internship unlinkedInternship = new Internship(
                internshipToUnlink.getInternshipId(),
                internshipToUnlink.getCompanyName(),
                internshipToUnlink.getInternshipRole(),
                internshipToUnlink.getInternshipStatus(),
                null,
                internshipToUnlink.getInterviewDate()
        );

        if (!personToUnlink.isSamePerson(unlinkedPerson) && model.hasPerson(unlinkedPerson)) {
            throw new CommandException(MESSAGE_UNLINKED_PERSON);
        }

        if (!internshipToUnlink.isSameInternship(unlinkedInternship) && model.hasInternship(unlinkedInternship)) {
            throw new CommandException(MESSAGE_UNLINKED_INTERNSHIP);
        }

        model.setPerson(personToUnlink, unlinkedPerson);
        model.setInternship(internshipToUnlink, unlinkedInternship);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, unlinkedPerson.getName(), unlinkedInternship.getDisplayName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnlinkCommand)) {
            return false;
        }

        UnlinkCommand otherCommand = (UnlinkCommand) other;
        return personIndex.equals(otherCommand.personIndex)
                && internshipIndex.equals(otherCommand.internshipIndex);
    }
}
