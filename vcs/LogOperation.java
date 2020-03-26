package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class LogOperation extends VcsOperation {
    /**
     * Log operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public LogOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the log operation.
     *
     * @param vcs
     *            the vcs
     * @return the return code
     */
    @Override
    public int execute(final Vcs vcs) {
        // print all commits from current branch
        for (int i = 0; i < vcs.getCurrentBranch().getCommits().size(); i++) {
            vcs.getOutputWriter().write(
                    "Commit id: " + vcs.getCurrentBranch().getCommits().get(i).getId() + "\n");
            if (i == vcs.getCurrentBranch().getCommits().size() - 1) {
                vcs.getOutputWriter().write("Message: "
                        + vcs.getCurrentBranch().getCommits().get(i).getMessage() + "\n");
            } else {
                vcs.getOutputWriter().write("Message: "
                        + vcs.getCurrentBranch().getCommits().get(i).getMessage() + "\n\n");
            }
        }
        return ErrorCodeManager.OK;
    }

}
