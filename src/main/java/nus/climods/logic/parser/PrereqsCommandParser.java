package nus.climods.logic.parser;

import nus.climods.logic.commands.PrereqsCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;

public class PrereqsCommandParser implements Parser<PrereqsCommand> {
    @Override
    public PrereqsCommand parse(String args) throws ParseException {
        ModuleCodeParameter mcp = new ModuleCodeParameter(args);
        String moduleCode = mcp.getArgValue();
        return new PrereqsCommand(moduleCode);
    }
}
