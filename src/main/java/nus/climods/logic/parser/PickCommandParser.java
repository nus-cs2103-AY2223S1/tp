package nus.climods.logic.parser;

import nus.climods.logic.commands.PickCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.logic.parser.parameters.LessonTypeParameter;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;
import nus.climods.model.module.LessonTypeEnum;

/**
 * Parses input arguments and creates a new PickCommand object
 */
public class PickCommandParser implements Parser<PickCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PickCommand and returns an PickCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PickCommand parse(String args) throws ParseException {
        String[] arg = args.split(" ", 4);

        if (arg.length != 4) {
            throw new ParseException("You need 3 arguments: <module-code> <lesson-type> <classNo>");
        }

        ModuleCodeParameter mcp = new ModuleCodeParameter(args);
        String mc = mcp.getArgValue();

        LessonTypeParameter ltp = new LessonTypeParameter(args);
        LessonTypeEnum lt = ltp.getArgValue();

        String classNo = arg[3].trim().toUpperCase();

        return new PickCommand(mc, lt, classNo);
    }
}
