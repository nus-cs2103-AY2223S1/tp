package seedu.workbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_LANGUAGE_TAG;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.workbook.model.internship.util.StageUtil.stagesWithTipsToString;

import seedu.workbook.logic.commands.exceptions.CommandException;
import seedu.workbook.model.Model;
import seedu.workbook.model.internship.Internship;

/**
 * Adds an Internship to WorkBook.
 */
public class AddCommand extends Command {

    /** Command word to execute the add command */
    public static final String COMMAND_WORD = "add";

    /** Help message to execute the add command */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship application to WorkBook.\n"
            + "Parameters: "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_STAGE + "STAGE "
            + "[" + PREFIX_DATETIME + "DATE AND TIME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_LANGUAGE_TAG + "PROGRAMMING LANGUAGE]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "Meta "
            + PREFIX_ROLE + "Software Engineer "
            + PREFIX_STAGE + "Interview "
            + PREFIX_DATETIME + "10-Dec-2022 12:10 "
            + PREFIX_EMAIL + "hr@meta.com "
            + PREFIX_LANGUAGE_TAG + "javascript "
            + PREFIX_TAG + "MANGA "
            + PREFIX_TAG + "frontend";

    /** Message string displaying successful execution of the add command */
    public static final String MESSAGE_SUCCESS = "New internship application added: %1$s, "
            + "check out some tips for the stage!";

    /** Message string displaying successful execution of the add command, but stage is not in our pre-defined list */
    public static final String MESSAGE_SUCCESS_STAGE_NO_TIPS = "New internship application added: %1$s.\n"
            + "However, do you mean " + stagesWithTipsToString() + " for the Stage? \nWe curated "
            + "some nifty tips for those stages!";

    /** Message string displaying error message for unsuccessful execution of the add command for a duplicate */
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship application already exists in WorkBook";

    /** Internship to be added to work book */
    private final Internship toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Internship}
     */
    public AddCommand(Internship internship) {
        requireNonNull(internship);
        toAdd = internship;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.addInternship(toAdd);
        model.commitWorkBook();

        if (toAdd.stageHasNoTips()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_STAGE_NO_TIPS, toAdd));
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && toAdd.equals(((AddCommand) other).toAdd));
    }
}
