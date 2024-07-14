package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream my_out;
    int my_length;
    int cur_b;
    public int first_b;
    int counter;

    public MyCompressorOutputStream(OutputStream out) { //out=00011 11110
        this.my_out = out;
        this.my_length=0;
        this.cur_b=0;
        this.counter=0;
        this.first_b=2;
    }

    @Override
    public void write(int b) throws IOException {
        //init cur_b with the first value;
        if (first_b==2) {
            first_b=b;
            cur_b=b;
        }
        if (cur_b==b){
            counter++;
        }
        else {
            my_out.write(counter);
            counter=1;
            cur_b=b;
            }
        my_length++;
    }

    @Override
    public void write(byte []b) throws IOException {
        for (int i=0; i<b.length; i++){
            write(b[i]);
        }
        my_out.write(cur_b); //add the last int
    }

}
