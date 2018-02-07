package foo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOpt {
    public static void main(String[] args) throws IOException{
    	FileWriter fw = new FileWriter("D:\\demo1.txt",false);
    	BufferedWriter writer = new BufferedWriter(fw);
    	for(long i=50000000000L;i<50010000000L;i++){
    		writer.write(String.valueOf(i));
    		writer.newLine();
    	}
    	writer.flush();
    	writer.close();
    	fw.close();
    }
}
