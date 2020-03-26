package vcs;

import java.util.LinkedList;
import java.util.List;

public class Branch {
    private String name;
    private List<Commit> commits;

    public Branch(final String name) {
        this.name = name;
        this.commits = new LinkedList<Commit>();
    }

    /**
     * Gets the name of the branch.
     *
     * @return the name of the branch
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the branch.
     *
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets all commits of a branch.
     *
     * @return a list representing all commits of a branch
     */
    public List<Commit> getCommits() {
        return commits;
    }

    /**
     * Sets all commits of a branch.
     *
     */
    public void setCommits(final List<Commit> commits) {
        this.commits = commits;
    }

}
