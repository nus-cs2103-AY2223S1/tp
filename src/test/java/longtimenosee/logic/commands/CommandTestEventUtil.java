package longtimenosee.logic.commands;


import static longtimenosee.logic.parser.CliSyntax.PREFIX_DATE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_END_TIME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_START_TIME;

/**
 * Contains utility static values for event class parser testing.
 */
public class CommandTestEventUtil {
    //Event: Meet Amy from 12 to 1pm on 5th March 2021
    public static final String VALID_DESC_AMY = "Meet Amy for lunch";
    public static final String VALID_START_AMY = "12:00";
    public static final String VALID_END_AMY = "13:00";
    public static final String VALID_DATE_AMY = "2021-03-05";
    public static final String VALID_NAME_AMY = "Amy Ban";
    //Event: Meet Bob from 7 to 8pm on 1st January 2021
    public static final String VALID_DESC_BOB = "Meet Bob for dinner";
    public static final String VALID_START_BOB = "19:00";
    public static final String VALID_END_BOB = "19:00";
    public static final String VALID_DATE_BOB = "2021-01-01";
    public static final String VALID_NAME_BOB = "Bob Choo";

    public static final String INVALID_DESC = "Meet AM_8 for lunch";
    public static final String INVALID_START = "8:00";
    public static final String INVALID_END = "000";
    public static final String INVALID_DATE = "2021-01-1";
    public static final String FUTURE_INVALID_DATE = "3000-01-01";
    public static final String INVALID_NAME = "sw@g";



    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESC_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESC_BOB;
    public static final String START_DESC_AMY = " " + PREFIX_START_TIME + VALID_START_AMY;
    public static final String START_DESC_BOB = " " + PREFIX_START_TIME + VALID_START_BOB;
    public static final String END_DESC_AMY = " " + PREFIX_END_TIME + VALID_END_AMY;
    public static final String END_DESC_BOB = " " + PREFIX_END_TIME + VALID_END_BOB;
    public static final String DATE_DESC_AMY = " " + PREFIX_DATE + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + PREFIX_DATE + VALID_DATE_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + INVALID_NAME;
    public static final String INVALID_START_DESC = " " + PREFIX_START_TIME + INVALID_START;
    public static final String INVALID_END_DESC = " " + PREFIX_END_TIME + INVALID_END;
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + INVALID_DATE;
    public static final String INVALID_FUTURE_DATE_DESC = " " + PREFIX_DATE + FUTURE_INVALID_DATE;
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + INVALID_DESC;




}
