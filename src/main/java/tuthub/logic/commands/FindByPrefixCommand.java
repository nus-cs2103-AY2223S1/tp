package tuthub.logic.commands;

import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;

import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;

/**
 * Represents a FindByPrefixCommand with hidden internal logic and the ability to be executed
 */
public abstract class FindByPrefixCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds the tutor profiles with matches to search keywords."
            + "Please specify one attribute to search for by indicating the prefix of the attribute.\n"
            + "Parameters "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_YEAR + "YEAR] "
            + "[" + PREFIX_STUDENTID + "STUDENT ID] "
            + "[" + PREFIX_TEACHINGNOMINATION + "TEACHING NOMINATIONS] "
            + "[" + PREFIX_RATING + "RATING] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John ";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;
}
