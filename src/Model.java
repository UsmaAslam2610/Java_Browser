
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UsmaAslam
 */
public class Model {
    DefaultListModel<String> fav,his;
     public Model(){
        fav = new DefaultListModel<>();       
        his = new DefaultListModel<>();

    }
    public void writeToMyFile(String str)
    {
        try {
            
            PrintWriter pw = new PrintWriter(new FileWriter(new File("home.txt"),false));
            pw.write(str);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public String readMyFile()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("home.txt")));
            String line = br.readLine();
            br.close();
            return line;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
      public void writeHistoryFile(String url)
    {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File("history.txt"),true));
                    
            SimpleDateFormat formatDate = new SimpleDateFormat(
                     "dd/MM/yy,HH:mm ");
                    Calendar c = Calendar.getInstance();
                   pw.println(url+" - "+formatDate.format(c.getTime()));
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeToFavFile(String str, String add)
    {
        try {
               PrintWriter pw = new PrintWriter(new FileWriter(new File("fav.txt"),true));
               if(str == add)
               {
                   pw.println(str);
               }
               else{
                   pw.println(str + ": "+ add);
               }
                   pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public DefaultListModel<String> readFavFile()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("fav.txt")));
            String line;
              do{                
                line = br.readLine();
                fav.addElement(line);
            }while (line!=null);
            br.close();
            return fav;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public DefaultListModel<String> readHisFile()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("history.txt")));
            String line;
              do{                
                line = br.readLine();
                his.addElement(line);
            }while (line!=null);
            br.close();
            return his;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
