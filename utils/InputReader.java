package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public final class InputReader {
    private String filename;
    private BufferedReader bufferedReader;

    /**
     * Input reader constructor.
     *
     * @param filename
     *            the file name
     */
    public InputReader(final String filename) {
        this.setFilename(filename);
        bufferedReader = new BufferedReader(getReaderForFile(filename));
    }

    /**
     * Reads a line from the file.
     *
     * @return the line
     */
    public String readLine() {
        String line = null;

        try {
            line = this.bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    private Reader getReaderForFile(final String file) {
        if (file == null || file.isEmpty()) {
            return new InputStreamReader(System.in);
        } else {
            try {
                return new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
