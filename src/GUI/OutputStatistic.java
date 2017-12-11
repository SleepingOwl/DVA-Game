package GUI;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class OutputStatistic{

    private static long id = 0;

    BufferedWriter bW;

    public OutputStatistic() throws IOException{

        BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\Kursach\\DVA-Game\\src\\out\\"+Calendar.getInstance().getTime().toString().replace(" ","_").replace(':','-')+".txt")));
        bW.write("#\tисходное_направление\tполученнное_направление\tскорость\tразмер\n");

    }

    public void write(String str) throws IOException{
        bW.write(str);
    }

    public void close() throws IOException{
        bW.close();
    }
}
