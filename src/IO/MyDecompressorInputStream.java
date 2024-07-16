package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int index = 0;
        int value = 0;
        while (index < b.length && (value = in.read()) != -1) {
            int count = in.read();
            if (count == -1) {
                throw new IOException("Unexpected end of stream");
            }
            for (int i = 0; i < count; i++) {
                if (index >= b.length) {
                    return index; // Return the number of bytes actually read
                }
                b[index++] = (byte) value;
            }
        }
        return (index == 0 && value == -1) ? -1 : index;
    }
}
