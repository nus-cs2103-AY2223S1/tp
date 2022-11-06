package bookface.logic.parser;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.commons.core.Messages.MESSAGE_INVALID_DATE_PARSE;
import static bookface.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookface.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.TUESDAY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import bookface.commons.core.Messages;
import bookface.logic.commands.LoanCommand;
import bookface.logic.parser.exceptions.ParseException;
import bookface.testutil.TypicalIndexes;

public class LoanCommandParserTest {
    private final LoanCommandParser parser = new LoanCommandParser();

    /**
     * Creates a date with the amount of days added to today's date
     * @param days the number of days to be added to today's date (can be negative)
     * @return a date with the amount of days added to today's date
     */
    private Date createDate(int days) {
        return new Date(new java.util.Date().getTime() + TimeUnit.DAYS.toMillis(days));
    }

    //Below getDateOnDay code from
    //https://stackoverflow.com/questions/5944760/how-to-get-date-of-last-friday-from-specified-date
    //with a small modification
    /**
     * Return last day of week before specified date.
     * @param date - reference date.
     * @param day - DoW field from Calendar class.
     * @param weeks - number of weeks to adjust by
     * @return last day of week before specified date.
     */
    private static Date getDateOnDay(Date date, int day, int weeks) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, weeks);
        cal.set(Calendar.DAY_OF_WEEK, day);
        return cal.getTime();
    }

    @Test
    public void parse_withoutDate_success() {
        //contains whitespace
        assertParseSuccess(parser, "    1   1   ",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK));

        //contains no whitespace (normal input)
        assertParseSuccess(parser, "1 1",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK));
    }

    @Test
    public void parse_withDate_success() {
        //Tests for tomorrow
        assertParseSuccess(parser, "1 1 tomorrow",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK, createDate(1)));

        //Tests for yesterday
        assertParseSuccess(parser, "1 1 yesterday",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK, createDate(-1)));

        //Tests for today
        assertParseSuccess(parser, "1 1 today",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK, createDate(0)));

        //Tests for next week
        assertParseSuccess(parser, "1 1 next week",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK, createDate(7)));

        //Tests for last week
        assertParseSuccess(parser, "1 1 last week",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK, createDate(-7)));

        //Tests for last Friday
        assertParseSuccess(parser, "1 1 last Friday",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK,
                        getDateOnDay(createDate(0), FRIDAY, -1)));

        //Tests for next Tuesday
        assertParseSuccess(parser, "1 1 next Tuesday",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK,
                        getDateOnDay(createDate(0), TUESDAY, 1)));

        //Tests for three sundays later
        assertParseSuccess(parser, "1 1 three sundays later",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK,
                        getDateOnDay(createDate(0), SUNDAY, 3)));

        //Tests for two saturdays ago
        assertParseSuccess(parser, "1 1 two saturdays ago",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK,
                        getDateOnDay(createDate(0), SATURDAY, -2)));
    }

    @Test
    public void parse_yyyymmdd_success() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withResolverStyle(ResolverStyle.SMART);
        Date date;
        try {
            LocalDate ld = LocalDate.parse("2022-10-25", formatter);
            date = java.sql.Date.valueOf(ld);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }
        assertParseSuccess(parser, "1 1 2022-10-25",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK, date));
    }

    @Test
    public void parse_ddmmyyyy_success() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter = formatter.withResolverStyle(ResolverStyle.SMART);
        Date date;
        try {
            LocalDate ld = LocalDate.parse("31/10/2022", formatter);
            date = java.sql.Date.valueOf(ld);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }
        assertParseSuccess(parser, "1 1 31/10/2022",
                new LoanCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_BOOK, date));
    }


    @Test
    public void parse_invalidDateFormat_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_DATE_PARSE, LoanCommand.MESSAGE_USAGE);

        //Book is not a date format
        assertParseFailure(parser, "1 1 book", expectedMessage);

        //99 is not a date format
        assertParseFailure(parser, "1 1 99", expectedMessage);
    }

    @Test
    public void parse_emptyArg_failure() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneArg_failure() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroUserIndex_failure() {
        assertParseFailure(parser, "0 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroBookIndex_failure() {
        assertParseFailure(parser, "1 0", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE));
    }
}
