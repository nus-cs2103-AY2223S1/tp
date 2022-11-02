package seedu.rc4hdb.testutil;

import seedu.rc4hdb.model.resident.Resident;

import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.fields.Day;

import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * A utility class to help with building BookingDescriptor objects.
 */
public class BookingDescriptorBuilder {

    private BookingDescriptor descriptor;

    public BookingDescriptorBuilder() {
        descriptor = new BookingDescriptor();
    }

    /**
     * Initializes the BookingDescriptor with the data of {@code booking}.
     */
    public BookingDescriptorBuilder(Booking booking) {
        descriptor = new BookingDescriptor();
        descriptor.setVenueName(booking.getVenueName());
        descriptor.setDayOfWeek(booking.getDayOfWeek());
        descriptor.setHourPeriod(booking.getHourPeriod());
        descriptor.setResident(booking.getResident());
    }

    /**
     * Sets the {@code VenueName} of the {@code BookingDescriptor} that we are building.
     */
    public BookingDescriptorBuilder withVenueName(String name) {
        descriptor.setVenueName(new VenueName(name));
        return this;
    }

    /**
     * Sets the {@code DayOfWeek} of the {@code BookingDescriptor} that we are building.
     */
    public BookingDescriptorBuilder withDay(String day) {
        descriptor.setDayOfWeek(new Day(day));
        return this;
    }

    /**
     * Sets the {@code HourPeriod} of the {@code BookingDescriptor} that we are building.
     */
    public BookingDescriptorBuilder withHourPeriod(String hourPeriod) {
        descriptor.setHourPeriod(new HourPeriod(hourPeriod));
        return this;
    }

    /**
     * Sets the {@code Resident} of the {@code BookingDescriptor} that we are building.
     */
    public BookingDescriptorBuilder withResident(Resident resident) {
        descriptor.setResident(resident);
        return this;
    }


    public BookingDescriptor build() {
        return descriptor;
    }

}
