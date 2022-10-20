package modtrekt.logic.parser.tasks;

import static java.util.Objects.requireNonNull;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import modtrekt.logic.commands.tasks.PrioritizeTaskCommand;
import modtrekt.logic.parser.exceptions.ParseException;

/**
 * Parser for a command string input for a PrioritizeTaskCommand object.
 */
public class PrioritizeTaskCommandParser {
    /**
     * Returns a new UnarchiveTaskCommand object parsed from the given command string.
     */
    public PrioritizeTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        PrioritizeTaskCommand command = new PrioritizeTaskCommand();
        try {
            JCommander.newBuilder().addObject(command).build().parse(args.strip().split(" "));
        } catch (ParameterException ex) {
            StringBuilder sb = new StringBuilder();
            ex.getJCommander().getUsageFormatter().usage(sb);
            throw new ParseException(sb.toString());
        }
        return command;
    }
}
