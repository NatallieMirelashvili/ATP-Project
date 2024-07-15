package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        int curByte = b[0];
        int count = 1;

        for (int i = 1; i < b.length; i++) {
            if (b[i] == curByte && count < 255) {
                count++;
            } else {
                out.write(curByte);
                out.write(count);
                curByte = b[i];
                count = 1;
            }
        }

        // Write the last sequence
        out.write(curByte);
        out.write(count);
    }
}
