package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.ArrayList;
import java.util.List;

public class ShowOnlyCommand extends ColumnManipulatorCommand {
    public static final String COMMAND_WORD = "showonly";
    public static final String COMMAND_VERBS = "shown only";

    public ShowOnlyCommand() {
        super(ColumnManipulatorCommand.ALL_FIELDS, new ArrayList<>()); // very sus
    }
    public ShowOnlyCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public String getCommandVerbs() {
        return COMMAND_VERBS;
    }
}
