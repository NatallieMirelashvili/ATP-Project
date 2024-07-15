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
        while (index < b.length) {
            byte value = (byte) in.read();
            byte count = (byte) in.read();
            for (int i = 0; i < count; i++) {
                b[index++] = value;
            }
        }
        return b.length;
    }
}
