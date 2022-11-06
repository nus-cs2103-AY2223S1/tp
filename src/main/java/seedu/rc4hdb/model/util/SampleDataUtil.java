package seedu.rc4hdb.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.resident.fields.Tag;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Resident[] getSampleResidents() {
        return new Resident[] {
            new Resident(
                    new Name("Jon Tan"), new Phone("98113234"), new Email("jontan99@gmail.com"), new Room("03-10"),
                    new Gender("M"), new House("D"), new MatricNumber("A0051772A"), getTagSet("BlockHead")
            ),
            new Resident(
                    new Name("Ng Kai Ling"), new Phone("98841523"), new Email("ngkailing@gmail.com"), new Room("04-05"),
                    new Gender("F"), new House("A"), new MatricNumber("A0003381B"), getTagSet("Scholar")
            ),
            new Resident(
                    new Name("Kate Lim"), new Phone("90184402"), new Email("katelim@gmail.com"), new Room("02-01"),
                    new Gender("F"), new House("U"), new MatricNumber("A0065912C"), getTagSet("SportsCapt")
            ),
            new Resident(
                    new Name("Ong Kai Wen"), new Phone("95430211"), new Email("kaiwen@gmail.com"), new Room("03-04"),
                    new Gender("M"), new House("U"), new MatricNumber("A0063112C"), getTagSet("ViceHead")
            ),
            new Resident(
                    new Name("Lee Wei Ling"), new Phone("98770211"), new Email("weiwen95@gmail.com"), new Room("02-04"),
                    new Gender("F"), new House("L"), new MatricNumber("A0071273D"), getTagSet("RCFellow")
            ),
            new Resident(
                    new Name("Joshua Li"), new Phone("91670290"), new Email("j0shli@gmail.com"), new Room("03-09"),
                    new Gender("M"), new House("N"), new MatricNumber("A0088134E"), getTagSet("PublicityHead")
            ),
            new Resident(
                    new Name("Melvin Chua"), new Phone("91615761"), new Email("melvchua@gmail.com"), new Room("03-11"),
                    new Gender("M"), new House("A"), new MatricNumber("A0062330F"), getTagSet("TechLead")
            ),

            new Resident(
                    new Name("Aaron Tong"), new Phone("91745290"), new Email("aarontng@gmail.com"), new Room("02-02"),
                    new Gender("M"), new House("D"), new MatricNumber("A0040160G"), getTagSet("HouseHead")
            ),

            new Resident(
                    new Name("Isabelle Hua"), new Phone("94357150"), new Email("isahua@gmail.com"), new Room("02-03"),
                    new Gender("F"), new House("L"), new MatricNumber("A0086237H"), getTagSet("Scholar")
            ),

            new Resident(
                    new Name("Armaan Haj"), new Phone("91674412"), new Email("armaanhaj@gmail.com"), new Room("02-07"),
                    new Gender("M"), new House("L"), new MatricNumber("A0732156I"), getTagSet("FirstAid")
            ),

            new Resident(
                    new Name("Ng Xin Yi"), new Phone("99494460"), new Email("ngxy@gmail.com"), new Room("04-01"),
                    new Gender("F"), new House("A"), new MatricNumber("A0002334J"), getTagSet("ViceHead")
            ),

            new Resident(
                    new Name("Dinesh"), new Phone("98484241"), new Email("dinesh@gmail.com"), new Room("02-05"),
                    new Gender("M"), new House("U"), new MatricNumber("A0210521K"), getTagSet("RCFellow")
            ),

            new Resident(
                    new Name("Nur Farah"), new Phone("91834331"), new Email("farahnur@gmail.com"), new Room("04-06"),
                    new Gender("F"), new House("N"), new MatricNumber("A0203046L"), getTagSet("HouseHead")
            ),

            new Resident(
                    new Name("Chen Ming Xuan"), new Phone("91526188"), new Email("chenmx@gmail.com"), new Room("02-08"),
                    new Gender("M"), new House("U"), new MatricNumber("A0567112M"), getTagSet("TechDev")
            ),
        };
    }

    public static Booking[] getSampleHallBookings() {
        return new Booking[] {
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[0], new HourPeriod("8-11"),
                    new Day("MON")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[5], new HourPeriod("13-14"),
                    new Day("MON")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[4], new HourPeriod("20-21"),
                    new Day("MON")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[10], new HourPeriod("11-12"),
                    new Day("TUE")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[7], new HourPeriod("15-18"),
                    new Day("TUE")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[3], new HourPeriod("9-11"),
                    new Day("WED")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[0], new HourPeriod("16-19"),
                    new Day("WED")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[2], new HourPeriod("14-20"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[13], new HourPeriod("10-13"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[6], new HourPeriod("8-10"),
                    new Day("FRI")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[9], new HourPeriod("18-21"),
                    new Day("FRI")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[8], new HourPeriod("12-15"),
                    new Day("SAT")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[6], new HourPeriod("16-18"),
                    new Day("SAT")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[3], new HourPeriod("8-11"),
                    new Day("SUN")
            ),
            new RecurrentBooking(
                    new VenueName("Hall"), getSampleResidents()[12], new HourPeriod("19-22"),
                    new Day("SUN")
            ),
        };
    }

    public static Booking[] getSampleMeetingBookings() {
        return new Booking[] {
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[5], new HourPeriod("13-16"),
                    new Day("MON")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[4], new HourPeriod("18-21"),
                    new Day("MON")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[0], new HourPeriod("11-12"),
                    new Day("TUE")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[1], new HourPeriod("15-18"),
                    new Day("TUE")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[3], new HourPeriod("9-11"),
                    new Day("WED")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[9], new HourPeriod("13-16"),
                    new Day("WED")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[13], new HourPeriod("19-22"),
                    new Day("WED")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[3], new HourPeriod("18-20"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[2], new HourPeriod("11-13"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[11], new HourPeriod("9-12"),
                    new Day("FRI")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[6], new HourPeriod("16-20"),
                    new Day("FRI")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[4], new HourPeriod("12-15"),
                    new Day("SAT")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[7], new HourPeriod("8-10"),
                    new Day("SUN")
            ),
            new RecurrentBooking(
                    new VenueName("Meeting Room"), getSampleResidents()[8], new HourPeriod("17-22"),
                    new Day("SUN")
            ),
        };
    }

    public static Booking[] getSampleSeminarBookings() {
        return new Booking[]{
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[3], new HourPeriod("9-20"),
                    new Day("MON")
            ),
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[13], new HourPeriod("8-15"),
                    new Day("TUE")
            ),
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[5], new HourPeriod("16-22"),
                    new Day("TUE")
            ),
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[8], new HourPeriod("9-16"),
                    new Day("WED")
            ),
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[11], new HourPeriod("8-12"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[11], new HourPeriod("13-17"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[11], new HourPeriod("19-22"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Seminar Room"), getSampleResidents()[0], new HourPeriod("11-20"),
                    new Day("FRI")
            ),
        };
    }

    public static Booking[] getSampleLoungeBookings() {
        return new Booking[]{
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[5], new HourPeriod("19-22"),
                    new Day("MON")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[13], new HourPeriod("21-23"),
                    new Day("TUE")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[8], new HourPeriod("18-21"),
                    new Day("WED")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[11], new HourPeriod("16-19"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[0], new HourPeriod("20-23"),
                    new Day("THU")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[12], new HourPeriod("18-19"),
                    new Day("FRI")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[3], new HourPeriod("19-22"),
                    new Day("FRI")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[12], new HourPeriod("8-10"),
                    new Day("SAT")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[9], new HourPeriod("14-17"),
                    new Day("SAT")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[1], new HourPeriod("18-19"),
                    new Day("SAT")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[0], new HourPeriod("19-22"),
                    new Day("SAT")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[6], new HourPeriod("9-11"),
                    new Day("SUN")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[3], new HourPeriod("12-14"),
                    new Day("SUN")
            ),
            new RecurrentBooking(
                    new VenueName("Lounge"), getSampleResidents()[9], new HourPeriod("17-19"),
                    new Day("SUN")
            ),
        };
    }


    public static ReadOnlyResidentBook getSampleResidentBook() {
        ResidentBook sampleRb = new ResidentBook();
        for (Resident sampleResident : getSampleResidents()) {
            sampleRb.addResident(sampleResident);
        }
        return sampleRb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Venue[] getSampleVenues() {
        Venue hall = new Venue(new VenueName("Hall"));
        Venue meetingRoom = new Venue(new VenueName("Meeting Room"));
        Venue seminarRoom = new Venue(new VenueName("Seminar Room"));
        Venue lounge = new Venue(new VenueName("Lounge"));
        for (Booking sampleBooking : getSampleHallBookings()) {
            hall.addBooking(sampleBooking);
        }
        for (Booking sampleBooking : getSampleMeetingBookings()) {
            meetingRoom.addBooking(sampleBooking);
        }
        for (Booking sampleBooking : getSampleSeminarBookings()) {
            seminarRoom.addBooking(sampleBooking);
        }
        for (Booking sampleBooking : getSampleLoungeBookings()) {
            lounge.addBooking(sampleBooking);
        }

        Venue[] sampleVenue = new Venue[] {hall, meetingRoom, seminarRoom, lounge};
        return sampleVenue;

    }

    public static ReadOnlyVenueBook getSampleVenueBook() {
        VenueBook sampleVb = new VenueBook();
        for (Venue sampleVenue : getSampleVenues()) {
            sampleVb.addVenue(sampleVenue);
        }
        return sampleVb;
    }

}
