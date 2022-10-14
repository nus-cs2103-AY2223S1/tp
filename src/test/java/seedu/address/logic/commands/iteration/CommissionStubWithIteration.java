package seedu.address.logic.commands.iteration;

import static seedu.address.testutil.TypicalCustomers.AMY;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.CommissionStub;
import seedu.address.model.commission.Title;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.UniqueIterationList;

class CommissionStubWithIteration extends CommissionStub {
    static final String COMMISSION_TITLE = "Mosquito";
    final ArrayList<Iteration> iterationsAdded = new ArrayList<>();

    CommissionStubWithIteration() {
        super(new seedu.address.testutil.CommissionBuilder()
                .withTitle(COMMISSION_TITLE).toCommissionBuilder(), AMY);
    }

    @Override
    public Title getTitle() {
        return new Title(COMMISSION_TITLE);
    }

    @Override
    public boolean hasIteration(Iteration toAdd) {
        return iterationsAdded.stream().anyMatch(toAdd::isSameIteration);
    }

    @Override
    public void addIteration(Iteration toAdd) {
        iterationsAdded.add(toAdd);
    }

    @Override
    public void removeIteration(Iteration toRemove) {
        iterationsAdded.remove(toRemove);
    }

    @Override
    public UniqueIterationList getIterations() {
        UniqueIterationList result = new UniqueIterationList();
        result.setIterations(iterationsAdded);
        return result;
    }

    @Override
    public ObservableList<Iteration> getIterationList() {
        return FXCollections.observableArrayList(iterationsAdded);
    }

    List<Iteration> getIterationsAsList() {
        return iterationsAdded;
    }
}
