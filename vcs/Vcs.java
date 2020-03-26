package vcs;

import java.util.LinkedList;
import java.util.List;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.IDGenerator;
import utils.OutputWriter;
import utils.Visitor;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private Branch currentBranch;
    private List<Branch> branches;

    /**
     * Vcs constructor.
     *
     * @param outputWriter
     *            the output writer
     */
    public Vcs(final OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(final Branch currentBranch) {
        this.currentBranch = currentBranch;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(final List<Branch> branches) {
        this.branches = branches;
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    public void setActiveSnapshot(final FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        this.branches = new LinkedList<Branch>();
        // create master branch
        Branch masterBranch = new Branch("master");
        // create first commit
        Commit firstCommit = new Commit(IDGenerator.generateCommitID(), "First commit");
        firstCommit.setSnapshot(this.activeSnapshot);
        firstCommit.setHead(true);
        masterBranch.getCommits().add(firstCommit);
        branches.add(masterBranch);
        currentBranch = masterBranch;
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation
     *            the file system operation
     * @return the return code
     */
    @Override
    public int visit(final FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation
     *            the vcs operation
     * @return return code
     */
    @Override
    public int visit(final VcsOperation vcsOperation) {
        return vcsOperation.execute(this);
    }

}
