package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream my_out;
    int cur_b;
    int counter;

    public SimpleCompressorOutputStream (OutputStream out) { //out=00011 11110
        this.my_out = out;
        this.cur_b=0;
        this.counter=0;
    }

    @Override
    public void write(int b) throws IOException {
        if (cur_b==b){
            counter++;
        } else {
            my_out.write(counter);
            cur_b=b;
            counter++;
        }
    }

    @Override
    public void write(byte []b) throws IOException {
        for (int i=0; i<b.length; i++){
            write(b[i]);
        }
        my_out.write(counter); //add the last operation
    }

}
