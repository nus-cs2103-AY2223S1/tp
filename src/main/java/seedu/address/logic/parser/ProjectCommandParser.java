package seedu.address.logic.parser;

import seedu.address.logic.commands.project.*;
import seedu.address.logic.parser.exceptions.ParseException;

public class ProjectCommandParser implements Parser<ProjectCommand> {
    /**
     * Parse any commands that have to do with Projects
     * @param flag flag used in command
     * @param arguments arguments used in command
     * @return a ProjectCommand
     * @throws ParseException
     */
    @Override
    public ProjectCommand parse(String flag, String arguments) throws ParseException {
        // Strip is just there for good measure.
        switch (flag.strip()) {
            case AddProjectCommand.COMMAND_FLAG:
                return parseAddProjectCommand(arguments);
            case EditProjectCommand.COMMAND_FLAG:
                return parseEditProjectComand(arguments);
            case DeleteProjectCommand.COMMAND_FLAG:
                return parseDeleteProjectComand(arguments);
            case ListProjectCommand.COMMAND_FLAG:
                return parseListProjectCommand(arguments);
        }

        return null;
    }

    //TODO: implement
    private AddProjectCommand parseAddProjectCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private EditProjectCommand parseEditProjectComand(String arguments) {
        return null;
    }

    //TODO: implement
    private DeleteProjectCommand parseDeleteProjectComand(String arguments) {
        return null;
    }

    //TODO: implement
    private ListProjectCommand parseListProjectCommand(String arguments) {
        return null;
    }
}
