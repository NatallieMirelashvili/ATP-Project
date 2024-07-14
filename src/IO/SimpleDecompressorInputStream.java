package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream my_input;
    int my_counter;
    int cur_b;

    public SimpleDecompressorInputStream(InputStream in) { //  in= 10 5 6
        this.my_input = in;
        this.my_counter = 0;
        this.cur_b=1;
    }

    @Override
    public int read() throws IOException {
        if (this.my_input.read()==-1)
            return -1;
        if (this.my_counter==0) {
            this.my_counter = this.my_input.read();
            this.my_counter--;
            if (this.cur_b==0)
                this.cur_b=1;
            else if (this.cur_b==1)
                this.cur_b=0;
            return this.cur_b;
        }
        else {
            this.my_counter--;
            return this.cur_b;
            }
        }
    }




