package GUI;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class OutputStatistic{

    BufferedWriter bW;

    public OutputStatistic() throws IOException{

        bW = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\Kursach\\DVA-Game\\src\\out\\"+Calendar.getInstance().getTime().toString().replace(" ","_").replace(':','-')+".txt")));
        bW.write(String.format("%-2s %-10s %-6s %-20s %-20s","#","скорость(мс)","размер","исходное_направление","полученнное_направление\n"));
        bW.flush();
    }

    public void write(String str) throws IOException{
        bW.write(str);
        bW.flush();
    }

    public void close() throws IOException{
        bW.close();
    }
}
