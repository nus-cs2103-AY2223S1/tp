package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    private final Person person;

    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Label phone;

    @FXML
    private HBox nokDetails;

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


    public PersonViewPanel(Person person) {
        super(FXML);
        if (person == null) {
            this.person = null;
            return;
        }
        this.person = person;
//        setPersonalDetails();
//        setHospitalisationDetails();
//        setAppointmentDetails();
    }

    private void setPersonalDetails() {
        name.setText(person.getName().fullName);
        email.setText(person.getEmail().toString());
        phone.setText(person.getPhone().toString());
        ContactCard contactCard = new ContactCard(person);
        nokDetails.getChildren().add(contactCard.getRoot());
    }

    private void setHospitalisationDetails() {
        patientType.setText(person.getPatientType().toString());
        person.getHospitalWing().ifPresentOrElse(hw -> hospitalWing.setText(hw.toString()), () ->
                hospitalWing.setVisible(false));
        person.getFloorNumber().ifPresentOrElse(fn -> floorNumber.setText(fn.toString()), () ->
                floorNumber.setVisible(false));
        person.getWardNumber().ifPresentOrElse(wn -> wardNumber.setText(wn.toString()), () ->
                wardNumber.setVisible(false));
    }

    private void setAppointmentDetails() {
        upcomingAppointment.setText(person.getUpcomingAppointment().toString());
        ObservableList<PastAppointment> pastAppointmentsObservableList =
                new ObservableListWrapper<>(person.getPastAppointments());
        pastAppointments.setItems(pastAppointmentsObservableList);
        pastAppointments.setCellFactory(item -> new PastAppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PastAppointmentListViewCell extends ListCell<PastAppointment> {
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
}
