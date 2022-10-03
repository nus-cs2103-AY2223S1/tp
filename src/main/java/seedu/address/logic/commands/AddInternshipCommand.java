package seedu.address.logic.commands;

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


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;

public class AddInternshipCommand extends Command {
    public static final String COMMAND_WORD = "addi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a internship to the address book. "
            + "Parameters: "
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_INTERNSHIP_ROLE + "INTERNSHIP_ROLE "
            + PREFIX_INTERNSHIP_STATUS + "INTERNSHIP_STATUS "
            + PREFIX_PERSON_ID + "PERSON_ID "
            + PREFIX_INTERVIEW_DATE + "INTERVIEW_DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_INTERNSHIP_ROLE + "Frontend Engineer "
            + PREFIX_INTERNSHIP_STATUS + "PENDING "
            + PREFIX_PERSON_ID + "1 "
            + PREFIX_INTERVIEW_DATE + "2022-10-21 12:00 ";

    public static final String MESSAGE_SUCCESS = "New internship added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the address book";

    private final CompanyName companyName;
    private final InternshipRole internshipRole;
    private final InternshipStatus internshipStatus;
    private final PersonId contactPersonId;
    private final InterviewDate interviewDate;

    /**
     * Creates an AddCommand to add the specified {@code Internship}
     */
    public AddInternshipCommand(Internship internship) {
        requireNonNull(internship);
        this.companyName = internship.getCompanyName();
        this.internshipRole = internship.getInternshipRole();
        this.internshipStatus = internship.getInternshipStatus();
        this.contactPersonId = internship.getContactPersonId();
        this.interviewDate = internship.getInterviewDate();
    }

    /**
     * Creates an AddCommand to add a {@code Internship} with the specified
     * {@code name}, {@code phone}, {@code email}, {@code address},
     * {@code internshipId}, and {@code tags}.
     */
    public AddInternshipCommand(
            CompanyName companyName,
            InternshipRole internshipRole,
            InternshipStatus internshipStatus,
            PersonId contactPersonId,
            InterviewDate interviewDate) {
        requireAllNonNull(companyName, internshipRole, internshipStatus);
        this.companyName = companyName;
        this.internshipRole = internshipRole;
        this.internshipStatus = internshipStatus;
        this.contactPersonId = contactPersonId;
        this.interviewDate = interviewDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person contactPerson = model.findPersonById(contactPersonId);

        Internship toAdd;
        if (contactPerson == null) {
            toAdd = new Internship(
                    new InternshipId(model.getNextInternshipId()),
                    companyName,
                    internshipRole,
                    internshipStatus,
                    null,
                    interviewDate
            );
        } else {
            toAdd = new Internship(
                    new InternshipId(model.getNextInternshipId()),
                    companyName,
                    internshipRole,
                    internshipStatus,
                    contactPersonId,
                    interviewDate
            );
        }

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.addInternship(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInternshipCommand // instanceof handles nulls
                && companyName.equals(((AddInternshipCommand) other).companyName)
                && internshipRole.equals(((AddInternshipCommand) other).internshipRole)
                && internshipStatus.equals(((AddInternshipCommand) other).internshipStatus)
                && contactPersonId.equals(((AddInternshipCommand) other).contactPersonId)
                && interviewDate.equals(((AddInternshipCommand) other).interviewDate));
    }
}
