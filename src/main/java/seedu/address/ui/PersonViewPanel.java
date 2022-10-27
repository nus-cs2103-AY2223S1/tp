package seedu.address.ui;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOSPITAL_WING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPCOMING_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WARD_NUMBER;


/**
 * Panel which has the detailed view of a patient.
 */
public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    private final Person person;

    private CommandTextEditor commandTextEditor;

    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Label phone;

    @FXML
    private Label nokName;

    @FXML
    private Label nokRelationship;

    @FXML
    private Label nokContact;

    @FXML
    private Label patientType;

    @FXML
    private Label hospitalWing;

    @FXML
    private Label floorNumber;

    @FXML
    private Label wardNumber;

    @FXML
    private Label upcomingAppointment;

    @FXML
    private ListView<PastAppointment> pastAppointments;

    @FXML
    private FlowPane medications;

    /**
     * Generates a Person View Panel.
     * @param person Person to generate the panel about.
     */
    public PersonViewPanel(Person person, CommandTextEditor commandTextEditor) {
        super(FXML);
        if (person == null) {
            this.person = null;
            return;
        }
        this.person = person;
        this.commandTextEditor = commandTextEditor;
        setPersonalDetails();
        setHospitalisationDetails();
        setAppointmentDetails();
    }

    private void setPersonalDetails() {
        name.setText(person.getName().fullName);
        name.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_NAME));
        email.setText(person.getEmail().toString());
        email.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_EMAIL));
        phone.setText(person.getPhone().toString());
        phone.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_PHONE));
        nokName.setText("Name: " + person.getNextOfKin().getNextOfKinName());
        nokName.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_NEXT_OF_KIN));
        nokRelationship.setText("Relationship: " + person.getNextOfKin().getNextOfKinRelationship());
        nokName.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_NEXT_OF_KIN));
        nokContact.setText("Contact: " + person.getNextOfKin().getNextOfKinContact());
        nokName.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_NEXT_OF_KIN));
        if (person.getMedications().size() > 0) {
            medications.getChildren().add(new Label(person.getMedicationString()));
            medications.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_MEDICATION));
        }
    }

    private void setHospitalisationDetails() {
        patientType.setText(person.getPatientType().toString());
        patientType.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_PATIENT_TYPE));
        person.getHospitalWing().ifPresentOrElse(hw -> {
            hospitalWing.setText(hw.toString());
            hospitalWing.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_HOSPITAL_WING));
        }, () -> hospitalWing.setVisible(false));
        person.getFloorNumber().ifPresentOrElse(fn -> {
            floorNumber.setText(fn.toString());
            floorNumber.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_FLOOR_NUMBER));
        }, () -> floorNumber.setVisible(false));
        person.getWardNumber().ifPresentOrElse(wn -> {
            wardNumber.setText(wn.toString());
            wardNumber.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_WARD_NUMBER));
        }, () -> wardNumber.setVisible(false));
    }

    private void setAppointmentDetails() {
        upcomingAppointment.setText(person.getUpcomingAppointment().get().toString());
        upcomingAppointment.setOnMouseClicked(event -> commandTextEditor.editText(PREFIX_UPCOMING_APPOINTMENT));
        ObservableList<PastAppointment> pastAppointmentsObservableList =
                new ObservableListWrapper<>(person.getPastAppointments());
        pastAppointments.setItems(pastAppointmentsObservableList);
        pastAppointments.setCellFactory(item -> new PastAppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PastAppointmentListViewCell extends ListCell<PastAppointment> {
        @Override
        protected void updateItem(PastAppointment pastAppointment, boolean empty) {
            super.updateItem(pastAppointment, empty);

            if (empty || pastAppointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PastAppointmentCard(pastAppointment, getIndex() + 1).getRoot());
            }
        }
    }

    @FunctionalInterface
    public interface CommandTextEditor {
        void editText(Prefix prefix);
    }
}
