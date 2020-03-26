package vcs;

import java.util.ArrayList;

import utils.Context;
import utils.ErrorCodeManager;
import utils.OperationType;

public class StatusOperation extends VcsOperation {

    /**
     * Status operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public StatusOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the status operation.
     *
     * @param vcs
     *            the vcs
     * @return the return code
     */
    @Override
    public int execute(final Vcs vcs) {
        vcs.getOutputWriter().write("On branch: " + vcs.getCurrentBranch().getName() + "\n");
        vcs.getOutputWriter().write("Staged changes:\n");
        Context context = Context.getInstance();
        // print every command from staging
        for (String content : context.getStaging()) {
            vcs.getOutputWriter().write(content);
        }
        return ErrorCodeManager.OK;
    }

}
