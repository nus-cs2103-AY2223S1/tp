package seedu.address.logic.commands.project.find;

import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;

import seedu.address.logic.commands.project.ProjectCommand;

/**
 * Represents an abstract class to find and filter project list.
 */
public abstract class FindProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of projects shown.";
    private static final String MESSAGE_PROJECT_NOT_FOUND = "A project matching requirements not found.";

    public static final String MESSAGE_FIND_PROJECT_USAGE = COMMAND_WORD + ": Finds and filters projects by keyword "
            + "from the "
            + "address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_REPOSITORY + "REPOSITORY "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "DevEnable "
            + PREFIX_REPOSITORY + "tp/devenable ";
}
