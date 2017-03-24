import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.RETURN;

import junit.framework.Test;

public class ReadCsv {
	public List<String[]>getList(){
	List<String[]> arrString = new ArrayList<String[]>();
	File csv = new File("E:\\inputgit.csv");  // CSV文件路径
    BufferedReader br = null;
    try
    {
        br = new BufferedReader(new FileReader(csv));
    } catch (FileNotFoundException e)
    {
        e.printStackTrace();
    }
    String line = "";
    String everyLine = "";
    try {
        
            
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                String [] temp = everyLine.split(",");
                //System.out.println(everyLine);
                //System.out.println(temp[0]);
                arrString.add(temp);
            }
            //System.out.println("csv表格中所有行数："+arrString.size());
    } catch (IOException e)
    {
        e.printStackTrace();
    }    	
	return arrString;
}
}
