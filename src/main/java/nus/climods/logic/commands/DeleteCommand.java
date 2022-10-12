//package nus.climods.logic.commands;
//
//import static java.util.Objects.requireNonNull;
//
//import java.util.List;
//
//import nus.climods.logic.commands.exceptions.CommandException;
//import nus.climods.model.Model;
//import nus.climods.model.module.UserModule;
//
///**
// * Deletes a person identified using it's displayed index from the address book.
// */
//public class DeleteCommand extends Command {
//
//    public static final String COMMAND_WORD = "rm";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD
//        + " <Module Code> : Deletes the Module as indicated by the user. ";
//
//    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";
//    public static final String MESSAGE_DELETE_MODULE_FAILED = "Module does not exist: %1$s";
//
//    private final UserModule target;
//
//    public DeleteCommand(UserModule target) {
//        this.target = target;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        List<UserModule> lastShownList = model.getFilteredUserModuleList();
//        String msg = target.getUserModuleCode();
//
//        System.out.println(lastShownList.contains(target));
//
//        if (!lastShownList.contains(target)) {
//            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_FAILED, msg));
//        }
//
//        model.deleteUserModule(target);
//        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, msg));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//            || (other instanceof DeleteCommand // instanceof handles nulls
//            && target.equals(((DeleteCommand) other).target)); // state check
//    }
//}
