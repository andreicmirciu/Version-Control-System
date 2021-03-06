package filesystem;

import java.util.ArrayList;

import utils.EntityType;
import utils.ErrorCodeManager;
import utils.OperationType;

public final class MkdirOperation extends FileSystemOperation {
    /**
     * Mkdir operation constructor.
     *
     * @param type
     *            type of the operation
     * @param operationArgs
     *            the arguments of the operation
     */
    public MkdirOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the mkdir operation.
     *
     * @param fileSystemSnapshot
     *            the active file system snapshot
     * @return return code
     */
    @Override
    public int execute(final FileSystemSnapshot fileSystemSnapshot) {
        int result = ErrorCodeManager.OK;

        if (operationArgs == null) {
            return ErrorCodeManager.SYS_BAD_PATH_CODE;
        }

        result = fileSystemSnapshot.addEntity(EntityType.DIRECTORY, operationArgs.get(0),
                operationArgs.get(1));

        return result;
    }
}
