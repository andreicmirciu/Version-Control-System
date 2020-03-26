package vcs;

import filesystem.FileSystemSnapshot;

public class Commit {
    private int id;
    private String message;
    private FileSystemSnapshot snapshot;
    private boolean head;

    public Commit() {

    }

    public Commit(final int id, final String message) {
        this.id = id;
        this.message = message;
    }

    /**
     * Gets the id of the commit.
     *
     * @return the id of the commit
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the commit.
     *
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the message of the commit.
     *
     * @return the message of the commit
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the commit.
     *
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the filesystem snapshot of the commit.
     *
     * @return the filesystem snapshot of the commit
     */
    public FileSystemSnapshot getSnapshot() {
        return snapshot;
    }

    /**
     * Sets the filesystem snapshot of the commit.
     *
     */
    public void setSnapshot(final FileSystemSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * Check if commit is head.
     *
     * @return commit is head or not
     */
    public boolean isHead() {
        return head;
    }

    /**
     * Sets a commit head value.
     *
     */
    public void setHead(final boolean head) {
        this.head = head;
    }

}
