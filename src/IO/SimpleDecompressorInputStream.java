package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private final InputStream my_in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.my_in = in;
    }

    @Override
    public int read() throws IOException {
        return my_in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int index = 0;
        while (index < b.length) {
            int value = my_in.read(); // read byte value
            int count = my_in.read(); // read byte count

            for (int i = 0; i < count; i++) {
                b[index++] = (byte) value;
            }
        }
        return b.length;
    }
}


//public class SimpleDecompressorInputStream extends InputStream {
//    InputStream my_input;
//    int my_counter;
//    int cur_b;
//    byte[] buffer;
//
//    public SimpleDecompressorInputStream(InputStream in) throws IOException { //  in= 10 5 6
//        this.my_input = in;
//        this.my_counter = 0;
//        this.cur_b=1;
//        this.buffer = new byte[24];
//        this.my_input.read(this.buffer, 0, 24); // Read the first 24 bytes
//
//    }
//
//    @Override
//    public int read() throws IOException {
//        if (this.my_input.read()==-1)
//            return -1;
//        if (this.my_counter==0) {
//            this.my_counter = this.my_input.read();
//            this.my_counter--;
//            if (this.cur_b==0)
//                this.cur_b=1;
//            else if (this.cur_b==1)
//                this.cur_b=0;
//            return this.cur_b;
//        }
//        else {
//            this.my_counter--;
//            return this.cur_b;
//            }
//        }
//    }
//
//
//
//
