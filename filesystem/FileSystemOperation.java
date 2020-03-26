package filesystem;

import java.util.ArrayList;

import utils.AbstractOperation;
import utils.OperationType;
import vcs.Vcs;

public abstract class FileSystemOperation extends AbstractOperation {
    /**
     * File system operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public FileSystemOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the operation.
     *
     * @param fileSystemSnapshot
     *            the active file system snapshot
     * @return the return code
     */
    public abstract int execute(FileSystemSnapshot fileSystemSnapshot);

    /**
     * Accepts the vcs visitor.
     *
     * @param vcs
     *            the vcs visitor
     * @return the return code
     */
    @Override
    public final int accept(final Vcs vcs) {
        return vcs.visit(this);
    }
}
