package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.application.model.Model;
import seedu.application.model.application.Application;
import seedu.application.model.application.Status;

/**
 * Display statistics of the applications to user.
 */
public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Showing Statistics as requested...\n"
            + "Total Applications: %d\n" + "Archived Applications: %d\n" + "Interviews Received: %d\n"
            + "Offered Applications: %d\n" + "Pending Applications: %d\n" + "Rejected Applications: %d";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Application> currentList = model.getAllApplicationsInBook();
        int totalApplications = currentList.size();
        int totalInterviews = countItem(Application::hasInterview, currentList);
        int totalArchived = countItem(Application::isArchived, currentList);
        int totalOffered = countItem(application -> application.getStatus().equals(Status.OFFERED), currentList);
        int totalRejected = countItem(application -> application.getStatus().equals(Status.REJECTED), currentList);
        int totalPending = countItem(application -> application.getStatus().equals(Status.PENDING), currentList);
        return new CommandResult(String.format(MESSAGE_SUCCESS, totalApplications,
                totalArchived, totalInterviews, totalOffered, totalPending,
                totalRejected));
    }

    private int countItem(Predicate<Application> predicate, ObservableList<Application> listToCount) {
        return (int) listToCount.stream().filter(predicate).count();
    }
}
