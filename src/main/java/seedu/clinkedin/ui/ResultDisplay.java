package seedu.clinkedin.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private static final String DEFAULT_INIT_DISPLAY = "Welcome to CLInkedIn!\n\n"
            + "CLInkedIn is a desktop contact management application for Recruiters to "
            + "manage their contact list of candidates.\n"
            + "1. Keep track of the candidates/contacts you have met through events or recruitment applications.\n"
            + "2. Tag candidates by their skills, experience and stage in the recruitment process.\n"
            + "3. Filter candidates according to a specific skill, experience or stage in the recruitment process.\n"
            + "4. Edit/remove details of the candidates/contacts.\n\n"
            + "To get started:\n"
            + "- Type 'help' to see the list of commands available.\n"
            + "- Type 'help COMMAND' to see the usage of a specific command.\n"
            + "- Type 'exit' to exit the application.\n";

    @FXML
    private StackPane placeHolder;

    @FXML
    private TextArea resultDisplay;

    /**
     * Creates a {@code ResultDisplay} to display the result of a command execution.
     * We initialize the result display with a welcome message for users.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplay.setText(DEFAULT_INIT_DISPLAY);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

    //@@author emptygx-reused
    // Reused from https://github.com/AY2122S1-CS2103T-W08-4/tp/pull/188/files#
    // with minor modifications
    public void setChartToUser(List<ObservableList<PieChart.Data>> datas, List<String> titles,
                               String feedbackToUser) {
        ScrollPane scroll = new ScrollPane();
        resultDisplay.setText(feedbackToUser);

        for (int i = 0; i < 1; i++) {
            ObservableList<PieChart.Data> pieChartData = datas.get(i);
            double total = pieChartData.stream().map(PieChart.Data::getPieValue).reduce(0.0, Double::sum);

            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    String.format("%s (%.2f%%)", data.getName(), data.getPieValue() / total * 100)
                            )
                    )
            );

            PieChart pieChart = new PieChart(pieChartData);

            pieChart.setTitle(titles.get(i));
            pieChart.setLabelsVisible(true);
            pieChart.setStartAngle(180);

            placeHolder.getChildren().add(pieChart);

            VBox spacing = new VBox(500);
            spacing.setSpacing(150);

            placeHolder.getChildren().add(spacing);
        }
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setContent(placeHolder);
    }

    /**
     * Clear chart from previous commands.
     */
    public void clearCharts() {
        placeHolder.getChildren().clear();
        resultDisplay.clear();
        placeHolder.getChildren().add(resultDisplay);
    }
    //@@author
}
