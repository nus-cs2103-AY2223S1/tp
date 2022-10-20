package seedu.phu.ui;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import seedu.phu.model.internship.ApplicationProcess;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.Statistic;

/**
 * A UI component that displays a stacked bar chart regarding the {@code Internship}.
 */
public class StackedBarPanel extends UiPart<Node> {
    private static final String NO_DATA_MESSAGE = "No Data Found";
    private static final String FXML = "StackedBarPanel.fxml";
    private final ObservableList<Internship> internships;
    private final Statistic statistic;
    private final HashMap<ApplicationProcess.ApplicationProcessState, ColumnConstraints> constraints;
    private final HashMap<ApplicationProcess.ApplicationProcessState, Label> labels;

    @FXML
    private ColumnConstraints appliedConstraint;

    @FXML
    private ColumnConstraints assessmentConstraint;

    @FXML
    private ColumnConstraints interviewConstraint;

    @FXML
    private ColumnConstraints offerConstraint;

    @FXML
    private ColumnConstraints acceptedConstraint;

    @FXML
    private ColumnConstraints rejectedConstraint;

    @FXML
    private ColumnConstraints noDataConstraint;

    @FXML
    private Label appliedLabel;

    @FXML
    private Label assessmentLabel;

    @FXML
    private Label interviewLabel;

    @FXML
    private Label offerLabel;

    @FXML
    private Label acceptedLabel;

    @FXML
    private Label rejectedLabel;

    @FXML
    private Label noDataLabel;

    /**
     * Constructs a new StackedBarPanel representing the list
     * of internships.
     *
     * @param list an ObservableList.
     */
    public StackedBarPanel(ObservableList<Internship> list) {
        super(FXML);
        requireNonNull(list);
        internships = list;
        constraints = new HashMap<>();
        labels = new HashMap<>();
        statistic = new Statistic(list);
        init();
    }

    private void init() {
        //initialize constraints
        constraints.put(ApplicationProcess.ApplicationProcessState.ACCEPTED, acceptedConstraint);
        constraints.put(ApplicationProcess.ApplicationProcessState.ASSESSMENT, assessmentConstraint);
        constraints.put(ApplicationProcess.ApplicationProcessState.APPLY, appliedConstraint);
        constraints.put(ApplicationProcess.ApplicationProcessState.REJECTED, rejectedConstraint);
        constraints.put(ApplicationProcess.ApplicationProcessState.INTERVIEW, interviewConstraint);
        constraints.put(ApplicationProcess.ApplicationProcessState.OFFER, offerConstraint);

        //initialize label
        labels.put(ApplicationProcess.ApplicationProcessState.ACCEPTED, acceptedLabel);
        labels.put(ApplicationProcess.ApplicationProcessState.ASSESSMENT, assessmentLabel);
        labels.put(ApplicationProcess.ApplicationProcessState.APPLY, appliedLabel);
        labels.put(ApplicationProcess.ApplicationProcessState.REJECTED, rejectedLabel);
        labels.put(ApplicationProcess.ApplicationProcessState.INTERVIEW, interviewLabel);
        labels.put(ApplicationProcess.ApplicationProcessState.OFFER, offerLabel);

        //set initial Bar
        updateBar();

        //add event listener
        internships.addListener((ListChangeListener<Internship>) c -> {
            updateBar();
        });
    }

    private void updateBar() {
        if (internships.isEmpty()) {
            handleNoData();
            return;
        }
        updateWidth();
        updateText();
    }

    private void updateWidth() {
        Map<ApplicationProcess.ApplicationProcessState, Double> width = statistic.getWidth();

        ApplicationProcess.ApplicationProcessState[] states = ApplicationProcess.ApplicationProcessState.values();

        for (ApplicationProcess.ApplicationProcessState s : states) {
            constraints.get(s).setPercentWidth(width.get(s));
        }

        noDataConstraint.setPercentWidth(0);
        noDataLabel.setText("");
    }

    private void handleNoData() {
        ApplicationProcess.ApplicationProcessState[] states = ApplicationProcess.ApplicationProcessState.values();

        for (ApplicationProcess.ApplicationProcessState s : states) {
            constraints.get(s).setPercentWidth(0);
        }

        noDataConstraint.setPercentWidth(100);
        noDataLabel.setText(NO_DATA_MESSAGE);
    }


    private void updateText() {
        Map<ApplicationProcess.ApplicationProcessState, Integer> num = statistic.getGroupedData();

        ApplicationProcess.ApplicationProcessState[] states = ApplicationProcess.ApplicationProcessState.values();

        for (ApplicationProcess.ApplicationProcessState s : states) {
            int amount = num.get(s);
            labels.get(s).setText(String.valueOf(amount));
        }
    }
}
