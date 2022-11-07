package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.DateUtil.dateIsBeforeToday;
import static seedu.address.commons.util.DateUtil.dateIsEqualAndAfterToday;
import static seedu.address.commons.util.StockUtil.StockLevel.HIGH;
import static seedu.address.commons.util.StockUtil.StockLevel.LOW;
import static seedu.address.commons.util.StockUtil.StockLevel.MEDIUM;
import static seedu.address.commons.util.StockUtil.determineStockHealth;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.util.StockUtil;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of an {@code Inventory} and {@code TaskList}.
 */
public class StatsCard extends UiPart<Region> {
    private static final String FXML = "StatsCard.fxml";

    @FXML
    private Label itemsLowInStock;
    @FXML
    private Label itemsMediumInStock;
    @FXML
    private Label itemsHighInStock;
    @FXML
    private Label imcompleteTasks;
    @FXML
    private Label overdueTasks;
    @FXML
    private Label upcomingTask;

    /**
     * Creates a {@code StatsCard} with the given {@code SupplyItem} and {@code TaskList} to display.
     */
    public StatsCard(ObservableList<SupplyItem> inventory, ObservableList<Task> taskList) {
        super(FXML);
        Integer[] stockStatus = getStockStatus(inventory);
        this.itemsLowInStock.setText(String.format("%s", stockStatus[0]));
        this.itemsMediumInStock.setText(String.format("%s", stockStatus[1]));
        this.itemsHighInStock.setText(String.format("%s", stockStatus[2]));
        this.imcompleteTasks.setText(String.format("No. of incomplete task(s): %s", countNotCompleteTasks(taskList)));
        this.overdueTasks.setText(String.format("No. of Overdue task(s): %s", countOverdueTasks(taskList)));

        Optional<Task> task = findNearestIncompleteTask(taskList);

        if (task.isPresent()) {
            Task incompleteTask = task.get();
            this.upcomingTask.setText(String.format("Upcoming task @%s: %s",
                    incompleteTask.getDeadline(), incompleteTask.getTitle()));
        } else {
            this.upcomingTask.setText(String.format("No Tasks due soon"));
        }
    }

    /**
     * Finds the number of tasks in {@code taskList} that are not complete.
     */
    private static String countNotCompleteTasks(ObservableList<Task> taskList) {
        int count = taskList.stream()
                .filter(task -> task.getStatus() == false)
                .collect(Collectors.toList()).size();
        return String.format("%s", count);
    }

    /**
     * Finds the number of tasks in {@code taskList} that are overdue.
     * Overdue means past the deadline.
     */
    private static String countOverdueTasks(ObservableList<Task> taskList) {
        int count = taskList.stream()
                .filter(task -> task.getStatus() == false && dateIsBeforeToday(task.getDeadline()))
                .collect(Collectors.toList()).size();

        return String.format("%s", count);
    }

    /**
     * Finds the upcoming incomplete task nearest to deadline. Deadline must fall on today or after today's date.
     */
    private static Optional<Task> findNearestIncompleteTask(ObservableList<Task> taskList) {
        requireNonNull(taskList);

        List<Task> result = taskList.stream().sorted(Comparator.comparing(Task::getDeadline))
                .filter(task -> task.getStatus() == false && dateIsEqualAndAfterToday(task.getDeadline()))
                .collect(Collectors.toList());

        if (result.size() == 0) {
            return Optional.empty();
        }

        return Optional.ofNullable(result.get(0));
    }

    /**
     * Returns an array of stockStatus arranged in order of [LOW_COUNT, MEDIUM_COUNT, HIGH_COUNT].
     */
    private static Integer[] getStockStatus(ObservableList<SupplyItem> supplyItems) {
        Integer[] stockStatus = new Integer[]{0, 0, 0};

        supplyItems.forEach(item -> {
            StockUtil.StockLevel stockLevel = determineStockHealth(item.getCurrentStock(), item.getMinStock());
            if (stockLevel.equals(LOW)) {
                stockStatus[0]++;
            } else if (stockLevel.equals(MEDIUM)) {
                stockStatus[1]++;
            } else {
                assert stockLevel.equals(HIGH);
                stockStatus[2]++;
            }
        });

        return stockStatus;
    }
}
