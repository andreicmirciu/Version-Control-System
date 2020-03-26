package utils;

import java.util.LinkedList;
import java.util.List;

import vcs.Vcs;

public final class Context {
    private Vcs vcs;
    private static Context instance = null;
    private InputReader inputReader;
    private OutputWriter outputWriter;
    private List<String> staging = new LinkedList<String>();

    /**
     * Context constructor.
     */
    private Context() {
    }

    /**
     * Gets the instance.
     *
     * @return the instance
     */
    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }

    public InputReader getInputReader() {
        return inputReader;
    }

    public List<String> getStaging() {
        return staging;
    }

    public void setStaging(final List<String> staging) {
        this.staging = staging;
    }

    /**
     * Initialise the vcs.
     *
     * @param input
     *            the input file
     * @param output
     *            the output file
     */
    public void init(final String input, final String output) {
        inputReader = new InputReader(input);
        outputWriter = new OutputWriter(output);
        vcs = new Vcs(outputWriter);
    }

    /**
     * Runs the context.
     */
    public void run() {
        String operationString = "";
        AbstractOperation operation;
        OperationParser parser = new OperationParser();
        int exitCode;
        boolean wasError;

        this.vcs.init();

        while (true) {
            operationString = this.inputReader.readLine();
            if (operationString == null || operationString.isEmpty()) {
                continue;
            }
            if (operationString.equals("exit")) {
                return;
            }

            operation = parser.parseOperation(operationString);
            exitCode = operation.accept(vcs);
            wasError = ErrorCodeManager.getInstance().checkExitCode(outputWriter, exitCode);

            if (!wasError && this.isTrackable(operation)) {

                if (operation.getType() == OperationType.CHANGEDIR) {
                    staging.add("\tChanged directory to " + operation.operationArgs.get(0) + "\n");
                }

                if (operation.getType() == OperationType.MAKEDIR) {
                    staging.add("\tCreated directory " + operation.operationArgs.get(0) + "\n");
                }

                if (operation.getType() == OperationType.REMOVE) {
                    if (operation.operationArgs.get(0).equals("-r")) {
                        staging.add("\tRemoved " + operation.operationArgs.get(1) + "\n");
                    } else {
                        staging.add("\tRemoved " + operation.operationArgs.get(0) + "\n");
                    }
                }

                if (operation.getType() == OperationType.TOUCH) {
                    staging.add("\tCreated file " + operation.operationArgs.get(1) + "\n");
                }
                if (operation.getType() == OperationType.WRITETOFILE) {
                    staging.add("\tAdded " + "\"" + operation.operationArgs.get(1) + "\""
                            + " to file " + operation.operationArgs.get(0) + "\n");
                }

            }
        }
    }

    /**
     * Specifies if an operation is vcs trackable or not. You can use it when you
     * implement rollback/checkout -c functionalities.
     *
     * @param abstractOperation
     *            the operation
     * @return whether it's trackable or not
     */
    private boolean isTrackable(final AbstractOperation abstractOperation) {
        boolean result;

        switch (abstractOperation.type) {
        case CHANGEDIR:
        case MAKEDIR:
        case REMOVE:
        case TOUCH:
        case WRITETOFILE:
            result = true;
            break;
        default:
            result = false;
        }

        return result;
    }
}
