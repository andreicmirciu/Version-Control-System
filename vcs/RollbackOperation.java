package vcs;

import java.util.ArrayList;

import utils.Context;
import utils.ErrorCodeManager;
import utils.OperationType;

public class RollbackOperation extends VcsOperation {
    /**
     * Status operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public RollbackOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the rollback operation.
     *
     * @param vcs
     *            the vcs
     * @return the return code
     */
    @Override
    public int execute(final Vcs vcs) {
        Context context = Context.getInstance();
        context.getStaging().clear();
        int size = vcs.getCurrentBranch().getCommits().size();
        // set active snapshot to last commit version
        vcs.setActiveSnapshot(vcs.getCurrentBranch().getCommits().get(size - 1).getSnapshot());
        return ErrorCodeManager.OK;
    }

}
