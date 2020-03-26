package vcs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import utils.Context;
import utils.ErrorCodeManager;
import utils.OperationType;

public class CheckoutOperation extends VcsOperation {
    /**
     * Checkout operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public CheckoutOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the checkout operation.
     *
     * @param vcs
     *            the vcs
     * @return the return code
     */
    @Override
    public int execute(final Vcs vcs) {
        int result = ErrorCodeManager.OK;
        Context context = Context.getInstance();
        if (!context.getStaging().isEmpty()) {
            result = ErrorCodeManager.VCS_STAGED_OP_CODE;
            return result;
        }
        // checkout on a previous commit from current branch
        if (this.operationArgs.get(1).equals("-c")) {
            // get commit id
            Integer commitId = Integer.parseInt(this.operationArgs.get(2));
            List<Commit> commitsToRemove = new LinkedList<Commit>();
            List<Integer> foundIds = new ArrayList<Integer>();
            for (Commit c : vcs.getCurrentBranch().getCommits()) {
                if (c.getId() == commitId) {
                    foundIds.add(c.getId());
                }
            }
            if (foundIds.isEmpty()) {
                result = ErrorCodeManager.VCS_BAD_PATH_CODE;
                return result;
            }
            for (Commit c : vcs.getCurrentBranch().getCommits()) {
                if (c.getId() > commitId) {
                    commitsToRemove.add(c);
                }
            }
            vcs.getCurrentBranch().getCommits().removeAll(commitsToRemove);
            for (Commit c : vcs.getCurrentBranch().getCommits()) {
                if (c.getId() == commitId) {
                    c.setHead(true);
                    vcs.setActiveSnapshot(c.getSnapshot().cloneFileSystem());
                } else {
                    c.setHead(false);
                }
            }
            // checkout on another branch
        } else {
            List<String> branchNames = new ArrayList<String>();
            for (Branch b : vcs.getBranches()) {
                if (b.getName().equals(this.operationArgs.get(1))) {
                    branchNames.add(b.getName());
                }
            }
            if (branchNames.isEmpty()) {
                result = ErrorCodeManager.VCS_BAD_CMD_CODE;
                return result;
            }
            Branch branch = new Branch(this.operationArgs.get(1));
            vcs.setCurrentBranch(branch);
        }
        return result;
    }

}
