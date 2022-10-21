package seedu.address.ui;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public interface PopUpPanel {

    public Command generateCommand() throws ParseException;

    public boolean checkAllPartsFilled();

}
