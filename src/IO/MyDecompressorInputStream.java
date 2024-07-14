package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyDecompressorInputStream extends InputStream {
    InputStream my_input;
    int my_length;
    MyCompressorOutputStream my_out;
    int cur_b;

    public MyDecompressorInputStream(InputStream in) { //  in= 10 5 6
        this.my_input=in;
        this.my_length=0;
        this.cur_b=my_out.first_b;
    }

    @Override
    public int read() throws IOException {
        return cur_b;
    }

    @Override
    public int read(byte[] b) throws IOException {
        for (int i=0; i<b.length; i++){
            for (int j=0; j<b[i]; i++){
                read();
            }
        }
    }
}
