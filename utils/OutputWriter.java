package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public final class OutputWriter {
    private String filename;
    private BufferedWriter bufferedWriter;

    /**
     * Output writer constructor.
     *
     * @param filename
     *            the file name
     */
    public OutputWriter(final String filename) {
        this.setFilename(filename);
        bufferedWriter = new BufferedWriter(getWriterForFile(filename));
    }

    /**
     * Writes the content.
     *
     * @param content
     *            the content
     */
    public void write(final String content) {
        try {
            this.bufferedWriter.write(content);
            this.bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Writer getWriterForFile(final String file) {
        if (file == null || file.isEmpty()) {
            return new OutputStreamWriter(System.out);
        } else {
            try {
                return new FileWriter(file);
            } catch (Exception e) {
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
