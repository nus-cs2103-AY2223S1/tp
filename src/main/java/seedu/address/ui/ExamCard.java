package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import seedu.address.model.exam.Exam;


/**
 * ExamCard represents an Exam Card which contains details of the Exam and the position in the
 * listView.
 */
public class ExamCard extends UiPart<Region> {
    private static final String FXML = "ExamListCard.fxml";

    public final Exam exam;

    @FXML
    private Label id;

    @FXML
    private Label examDescription;

    @FXML
    private Label examDate;

    @FXML
    private Label moduleCode;

    @FXML
    private ProgressBar percentageCompleted;

    @FXML
    private Label progressMessage;


    /**
     * Constructor of the ExamCard. Sets the exam and the position.
     *
     * @param exam The exam being set.
     * @param position The position of the exam in the listView.
     */
    public ExamCard(Exam exam, int position) {
        super(FXML);
        this.exam = exam;
        id.setText(position + ". ");
        moduleCode.setText("Module Code: " + exam.getModule().getModuleCode().moduleCode);
        examDescription.setText(exam.getDescription().description);
        examDate.setText("Date: " + exam.getExamDate().examDate);
        if (!exam.hasTasks()) {
            percentageCompleted.setPrefWidth(0);
        } else {
            percentageCompleted.setPadding(new Insets(0, 5, 0, 0));
        }

        percentageCompleted.setProgress(exam.getPercentageCompleted());
        percentageCompleted.setStyle("-fx-accent:limegreen");
        progressMessage.setText(exam.generateProgressMessage());

    }
}
