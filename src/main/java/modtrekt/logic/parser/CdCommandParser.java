package modtrekt.logic.parser;

import modtrekt.logic.commands.CdModuleCommand;
import modtrekt.logic.commands.Command;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.module.ModCode;

/**
 * Parser for a cd command that returns a CdModuleCommand
 */
public class CdCommandParser implements Parser<CdModuleCommand> {

    /**
     * Parses the give {@code string} in the context of the CdModuleCommand.
     *
     * @throws ParseException if the provided module code is invalid
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args);
        String preamble = argMultiMap.getPreamble();
        if (preamble.equals("")) {
            throw new ParseException("Please provide a module code.");
        }
        if (preamble.equals("..")) {
            return new CdModuleCommand();
        }
        try {
            ModCode moduleCode = new ModCode(preamble);
            return new CdModuleCommand(moduleCode);
        } catch (IllegalArgumentException exception) {
            throw new ParseException(String.format("%s is not a valid module code.\n"
                    + ModCode.MESSAGE_CONSTRAINTS, preamble));
        }
    }


}
