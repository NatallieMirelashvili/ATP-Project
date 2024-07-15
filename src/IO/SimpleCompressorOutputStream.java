package IO;

import java.io.IOException;
import java.io.OutputStream;


public class SimpleCompressorOutputStream extends OutputStream {
    private final OutputStream my_out;
    private int cur_b;
    private int counter;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.my_out = out;
        this.cur_b = -1; // Initialize cur_b to an invalid value
        this.counter = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if (cur_b == -1) {
            cur_b = b;
            counter = 1;
        } else if (cur_b == b && counter < 255) {
            counter++;
        } else {
            my_out.write(cur_b);
            my_out.write(counter);
            cur_b = b;
            counter = 1;
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (byte value : b) {
            write(value);
        }
        // Flush remaining data
        if (cur_b != -1) {
            my_out.write(cur_b);
            my_out.write(counter);
        }
        my_out.flush();
    }
}



//public class SimpleCompressorOutputStream extends OutputStream {
//    private OutputStream out;
//    private int curByte;
//    private int count;
//    private int mode;
//
//    public SimpleCompressorOutputStream(OutputStream out) {
//        this.out = out;
//        this.curByte = 0;
//        this.count = 0;
//        this.mode = 0;
//    }
//
//    @Override
//    public void write(int b) throws IOException {
//        if (mode == 0) {
//            out.write(b);
//        } else {
//            if (curByte == b) {
//                count++;
//            } else {
//                out.write(count);
//                curByte = b;
//                count = 1;
//            }
//        }
//    }
//
//    @Override
//    public void write(byte[] b) throws IOException {
//        for (int i = 0; i < 6; i++) {
//            mode = 0;
//            int number = ByteBuffer.wrap(b).getInt();
//            write(number);
//        }
//        for (int i = 24; i < b.length; i++) {
//            mode = 1;
//            write(b[i]);
//        }
//        out.write(count); // write the last operation
//    }
//
//    @Override
//    public void write(byte[] b, int off, int len) throws IOException {
//        throw new UnsupportedOperationException("Not implemented");
//    }
//
//    @Override
//    public void flush() throws IOException {
//        out.flush();
//    }
//
//    @Override
//    public void close() throws IOException {
//        out.close();
//    }
//}
////
////package IO;
////
////import java.io.IOException;
////import java.io.OutputStream;
////import java.nio.ByteBuffer;
////
////public class SimpleCompressorOutputStream extends OutputStream {
////    OutputStream my_out;
////    int cur_b;
////    int counter;
////    int my_case;
////
////    public SimpleCompressorOutputStream (OutputStream out) { //out=00011 11110
////        this.my_out = out;
////        this.cur_b=0;
////        this.counter=0;
////    }
////
////    @Override
////    public void write(int b) throws IOException {
////        if (my_case==0){
////            my_out.write(b);
////        }
////        else {
////            if (cur_b == b) {
////                counter++;
////            } else {
////                my_out.write(counter);
////                cur_b = b;
////                counter=0;
////                counter++;
////            }
////        }
////    }
////
////    @Override
////    public void write(byte []b) throws IOException {
////        for (int i=0; i<6; i++){
////            my_case = 0;
////            int number = ByteBuffer.wrap(b).getInt();
////            write(number);
////        }
////        for (int i=24; i<b.length; i++){
////            my_case =1;
////            write(b[i]);
////        }
////        my_out.write(counter); //add the last operation
////    }
////
////}
