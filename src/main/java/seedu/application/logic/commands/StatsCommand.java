package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import javafx.collections.ObservableList;
import seedu.application.model.Model;
import seedu.application.model.application.Application;
import seedu.application.model.application.Status;

public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_SUCCESS = "Here is the statistics as requested!\n"
            + "Total Applications: %d\n" + "Archived Applications: %d\n" + "Interviews Received: %d\n"
            + "Offered Applications: %d\n" + "Pending Applications: %d\n" + "Rejected Applications: %d";

    private static final int ARCHIVE_POS = 0;
    private static final int REJECTED_POS = 1;
    private static final int OFFERED_POS = 2;
    private static final int PENDING_POS = 3;
    private static final int INTERVIEW_POS = 4;
    private static final int TOTAL_APPLICATIONS_POS = 5;

    private final int[] countList = new int[6];




    public StatsCommand() {
        Arrays.fill(countList, 0);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Application> currentList = model.getAllApplicationsInBook();
        countList[TOTAL_APPLICATIONS_POS] = currentList.size();
        for (Application app : currentList) {
            if (app.isArchived()) {
                countList[ARCHIVE_POS]++;
            }
            if (app.getStatus().equals(Status.REJECTED)) {
                countList[REJECTED_POS]++;
            }
            if (app.getStatus().equals(Status.OFFERED)) {
                countList[OFFERED_POS]++;
            }
            if (app.getStatus().equals(Status.PENDING)) {
                countList[PENDING_POS]++;
            }
            if (app.hasInterview()) {
                countList[INTERVIEW_POS]++;
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, countList[TOTAL_APPLICATIONS_POS],
                countList[ARCHIVE_POS], countList[INTERVIEW_POS], countList[OFFERED_POS], countList[PENDING_POS],
                countList[REJECTED_POS]));
    }
}
