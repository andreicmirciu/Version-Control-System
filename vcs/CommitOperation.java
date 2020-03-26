package vcs;

import java.util.ArrayList;
import java.util.List;

import filesystem.FileSystemSnapshot;
import utils.Context;
import utils.ErrorCodeManager;
import utils.IDGenerator;
import utils.OperationType;

public class CommitOperation extends VcsOperation {
    /**
     * Commit operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public CommitOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the commit operation.
     *
     * @param vcs
     *            the vcs
     * @return the return code
     */
    @Override
    public int execute(final Vcs vcs) {
        int result = ErrorCodeManager.OK;
        Context context = Context.getInstance();
        FileSystemSnapshot snapshot = new FileSystemSnapshot(vcs.getOutputWriter());
        if (context.getStaging().size() == 0) {
            result = ErrorCodeManager.VCS_BAD_CMD_CODE;
            return result;
        }
        // get previous commits from current branch
        List<Commit> commits = vcs.getCurrentBranch().getCommits();
        // get commit message
        String message = String.join(" ", this.operationArgs.subList(2, operationArgs.size()));
        // create a new commit
        Commit newCommit = new Commit();
        newCommit.setId(IDGenerator.generateCommitID());
        newCommit.setMessage(message);
        newCommit.setHead(true);
        newCommit.setSnapshot(snapshot);
        for (Commit c : vcs.getCurrentBranch().getCommits()) {
            c.setHead(false);
        }
        commits.add(newCommit);
        // add this commit to vcs current branch
        vcs.getCurrentBranch().setCommits(commits);
        context.getStaging().clear();
        return result;
    }

}
