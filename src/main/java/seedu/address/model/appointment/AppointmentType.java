package seedu.address.model.appointment;

import java.util.Locale;

/**
 * Describes the type of {@code Appointment}.
 * Guarantee: Can only be P(ast) or U(pcoming).
 */
public enum AppointmentType {
    PAST {
        @Override
        public String toString() {
            return "P";
        }
    },
    UPCOMING {
        @Override
        public String toString() {
            return "U";
        }
    };

    /**
     * Returns an {@code AppointmentType} enum.
     * @param strAppointmentType the string appointment type to be parsed
     * @return the appointment type enum
     */
    public static AppointmentType parseAppointmentType(String strAppointmentType) {
        String capsAppointmentType = strAppointmentType.toUpperCase(Locale.ROOT);
        AppointmentType[] appointmentTypes = AppointmentType.values();
        for (AppointmentType at : appointmentTypes) {
            if (capsAppointmentType.equals(at.name()) || capsAppointmentType.equals(at.toString())) {
                return at;
            }
        }
        return null;
    }
}
