package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class BranchOperation extends VcsOperation {
    /**
     * Branch operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public BranchOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the branch operation.
     *
     * @param vcs
     *            the vcs
     * @return the return code
     */
    @Override
    public int execute(final Vcs vcs) {
        int result = ErrorCodeManager.OK;
        // create a new Branch
        Branch newBranch = new Branch(this.operationArgs.get(1));
        for (Branch b : vcs.getBranches()) {
            if (b.getName().equals(newBranch.getName())) {
                result = ErrorCodeManager.VCS_BAD_CMD_CODE;
                return result;
            }
        }
        // add this branch to vcs
        vcs.getBranches().add(newBranch);
        return result;
    }

}
