package seedu.rc4hdb.logic.commands.modelcommands;

import java.util.List;

public class HideOnlyCommand extends ColumnManipulatorCommand {
    public static final String COMMAND_WORD = "hideonly";
    public static final String COMMAND_VERBS = "hidden only";

    public HideOnlyCommand(List<String> fieldsToShow, List<String> fieldsToHide) {
        super(fieldsToShow, fieldsToHide);
    }

    @Override
    public String getCommandVerbs() {
        return COMMAND_VERBS;
    }
}
