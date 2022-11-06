package soconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import soconnect.model.Model;
import soconnect.model.SoConnect;
import soconnect.model.tag.Tag;

/**
 * Clears the SoConnect.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SoConnect has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Tag> tagList = model.getTagList();
        model.setSoConnect(new SoConnect());
        model.setTagList(tagList);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
