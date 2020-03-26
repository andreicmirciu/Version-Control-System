package filesystem;

import java.util.ArrayList;

import utils.EntityType;
import utils.ErrorCodeManager;
import utils.OperationType;

public final class RemoveOperation extends FileSystemOperation {
    /**
     * Remove operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public RemoveOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the remove operation.
     *
     * @param fileSystemSnapshot
     *            the active file system snapshot
     * @return return code
     */
    @Override
    public int execute(final FileSystemSnapshot fileSystemSnapshot) {
        if (operationArgs.get(0).equals("rm")) {
            return rm(fileSystemSnapshot);
        } else if (operationArgs.get(0).equals("rmdir")) {
            return rmdir(fileSystemSnapshot, 1);
        }
        return ErrorCodeManager.SYS_BAD_PATH_CODE;
    }

    /**
     * Remove a file.
     *
     * @param fileSystemSnapshot
     *            the active file system snapshot
     * @return return code
     */
    private int rm(final FileSystemSnapshot fileSystemSnapshot) {
        if (operationArgs.get(1).equals("-r")) {
            return rmdir(fileSystemSnapshot, 2);
        }

        String path = operationArgs.get(1);
        FileSystemEntity fse = fileSystemSnapshot.getEntity(path);

        if (fse == null) {
            return ErrorCodeManager.SYS_BAD_PATH_CODE;
        }
        if (fse.getType().equals(EntityType.DIRECTORY)) {
            return ErrorCodeManager.SYS_BAD_CMD_CODE;
        }

        fse.parent.remove(fse.name);

        return ErrorCodeManager.OK;
    }

    /**
     * Remove a directory.
     *
     * @param fileSystemSnapshot
     *            the active file system sbapshot
     * @param argPos
     *            the position of the path in the arguments
     * @return
     */
    private int rmdir(final FileSystemSnapshot fileSystemSnapshot, final int argPos) {
        Directory dir;
        String path = operationArgs.get(argPos);
        FileSystemEntity fse = fileSystemSnapshot.getEntity(path);

        if (fse == null) {
            return ErrorCodeManager.SYS_BAD_PATH_CODE;
        }
        if (!fse.getType().equals(EntityType.DIRECTORY)) {
            return ErrorCodeManager.SYS_BAD_CMD_CODE;
        }

        dir = (Directory) fse;
        dir.parent.remove(fse.name);

        return ErrorCodeManager.OK;
    }
}
