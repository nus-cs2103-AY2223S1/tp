package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;

/**
 * Adds an internship to InterNUS.
 */
public class AddInternshipCommand extends Command {
    public static final String COMMAND_WORD = "add -i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a internship to InterNUS.\n"
            + "Parameters: "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_INTERNSHIP_ROLE + "INTERNSHIP_ROLE "
            + PREFIX_INTERNSHIP_STATUS + "INTERNSHIP_STATUS "
            + "[" + PREFIX_INTERVIEW_DATE + "INTERVIEW_DATE] "
            + "[" + PREFIX_LINK_INDEX + "LINK_INDEX]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_INTERNSHIP_ROLE + "Frontend Engineer "
            + PREFIX_INTERNSHIP_STATUS + "PENDING "
            + PREFIX_INTERVIEW_DATE + "2022-10-21 12:00 "
            + PREFIX_LINK_INDEX + "1 ";

    public static final String MESSAGE_SUCCESS = "New internship added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in InterNUS";

    private final CompanyName companyName;
    private final InternshipRole internshipRole;
    private final InternshipStatus internshipStatus;
    private final PersonId contactPersonId;
    private final InterviewDate interviewDate;
    private final Index linkIndex;

    /**
     * Creates an AddInternshipCommand to add the specified {@code Internship}
     */
    public AddInternshipCommand(Internship internship) {
        requireNonNull(internship);
        this.companyName = internship.getCompanyName();
        this.internshipRole = internship.getInternshipRole();
        this.internshipStatus = internship.getInternshipStatus();
        this.contactPersonId = internship.getContactPersonId();
        this.interviewDate = internship.getInterviewDate();
        this.linkIndex = null;
    }

    /**
     * Creates an AddInternshipCommand to add an {@code Internship} with the specified
     * {@code companyName}, {@code internshipRole}, {@code internshipStatus},
     * {@code interviewDate}, and {@code linkIndex}.
     */
    public AddInternshipCommand(
            CompanyName companyName,
            InternshipRole internshipRole,
            InternshipStatus internshipStatus,
            InterviewDate interviewDate,
            Index linkIndex) {
        requireAllNonNull(companyName, internshipRole, internshipStatus);
        this.companyName = companyName;
        this.internshipRole = internshipRole;
        this.internshipStatus = internshipStatus;

        // contactPersonId is to be gotten using the linkIndex in this case
        this.contactPersonId = null;

        this.interviewDate = interviewDate;
        this.linkIndex = linkIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // By default, use the contactPersonId field in the command
        PersonId idToLink = contactPersonId;

        List<Person> lastShownList = model.getFilteredPersonList();
        Person contactPerson = null;
        // If a linkIndex is supplied to the command,
        // attempt to find a contactPerson via the provided index in the filtered person list
        if (linkIndex != null && linkIndex.getZeroBased() < lastShownList.size()) {
            contactPerson = lastShownList.get(linkIndex.getZeroBased());

            // The person to link to must not already be linked to an internship
            if (contactPerson.getInternshipId() == null) {
                idToLink = contactPerson.getPersonId();
            }
        }

        Internship toAdd = new Internship(
                new InternshipId(model.getNextInternshipId()),
                companyName,
                internshipRole,
                internshipStatus,
                idToLink,
                interviewDate
        );

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        String linkMessage = "";
        if (contactPerson != null) {
            if (contactPerson.getInternshipId() == null) {
                linkMessage = String.format("\nContact person linked successfully: "
                        + LinkCommand.MESSAGE_SUCCESS, contactPerson.getName(), toAdd.getDisplayName());
            } else {
                linkMessage = String.format("\nWarning: Failed to link contact person: "
                        + LinkCommand.MESSAGE_LINKED_PERSON,
                        contactPerson.getName(),
                        model.findInternshipById(contactPerson.getInternshipId()).getDisplayName());
            }
        }

        model.addInternship(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd) + linkMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInternshipCommand // instanceof handles nulls
                && companyName.equals(((AddInternshipCommand) other).companyName)
                && internshipRole.equals(((AddInternshipCommand) other).internshipRole)
                && internshipStatus.equals(((AddInternshipCommand) other).internshipStatus)
                && contactPersonId != null && contactPersonId.equals(((AddInternshipCommand) other).contactPersonId)
                && interviewDate.equals(((AddInternshipCommand) other).interviewDate));
    }
}
