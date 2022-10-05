package tracko.logic.commands;

/**
 * Represents a command that can accept additional user input in multiple iterations before executing
 */
public abstract class MultiLevelCommand extends Command {

    protected boolean isCancelled;
    private boolean isAwaitingInput;

    protected MultiLevelCommand() {
        this(true, false);
    }

    protected MultiLevelCommand(boolean isAwaitingInput, boolean isCancelled) {
        this.isAwaitingInput = isAwaitingInput;
        this.isCancelled = isCancelled;
    }

    public void setAwaitingInput(boolean isAwaitingInput) {
        this.isAwaitingInput = isAwaitingInput;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    @Override
    public boolean isAwaitingInput() {
        return isAwaitingInput;
    }

}
