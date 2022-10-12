package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Lists all persons in the address book to the user.
 */
public class ListMeetingCommand extends Command {

    public static final String COMMAND_WORD = "listmeeting";

    public static final String MESSAGE_SUCCESS = "Listed all meetings";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);

        StringBuilder str = new StringBuilder();
        List<Meeting> list = model.getFilteredMeetingList().stream()
                .collect(Collectors.toList());
        list.forEach(str::append);

        return new CommandResult(MESSAGE_SUCCESS + "\n" + str);
    }
}
