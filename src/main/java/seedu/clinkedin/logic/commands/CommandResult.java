package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final Map<String, Map<String, Integer>> stats;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Export Window should open. */
    private final boolean isExport;

    /** Import Window should open. */
    private final boolean isImport;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, false, new HashMap<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isExport) {
        this(feedbackToUser, showHelp, exit, isExport, false, new HashMap<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isExport, boolean isImport) {
        this(feedbackToUser, showHelp, exit, isExport, isImport, new HashMap<>());
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code stats}.
     */
    public CommandResult(String feedbackToUser, Map<String, Map<String, Integer>> stats) {
        this(feedbackToUser, false, false, false, false, stats);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isExport, boolean isImport,
                         Map<String, Map<String, Integer>> stats) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isExport = isExport;
        this.isImport = isImport;
        this.stats = stats;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false,
                new HashMap<>());
    }

    //@@author emptygx-reused
    // Reused from https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/188/files#
    // with minor modifications
    public List<String> getStatsTitles() {
        return new ArrayList<>(stats.keySet());
    }

    public List<ObservableList<PieChart.Data>> getPieChartStats() {
        if (stats.isEmpty()) {
            return null;
        }

        List<ObservableList<PieChart.Data>> datas = new ArrayList<>();

        for (Map.Entry<String, Map<String, Integer>> entry : stats.entrySet()) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
                pieChartData.add(new PieChart.Data(entry2.getKey(), entry2.getValue()));
            }
            datas.add(pieChartData);
        }

        return datas;
    }
    //@@author

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowStats() {
        return !stats.isEmpty();
    }

    public boolean isShowFeedback() {
        return stats.isEmpty();
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isExport() {
        return isExport;
    }
    public boolean isImport() {
        return isImport;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;

        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
