
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UsmaAslam
 */
 public class btnHandler implements ActionListener
{
    GUI refg;
    JFrame favFr,hisFr,searchCount;
    boolean Pre, Next;
    public btnHandler(GUI gg) {
        this.refg = gg;
        Pre = Next=false;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String btnText = e.getActionCommand();
        if(btnText == "Home" || btnText == "home"){
            Model mod = new Model();
            if(refg.stPre.isEmpty())
                refg.stPre.push(mod.readMyFile());
            else if(!refg.stPre.isEmpty()&& refg.stPre.lastElement()!= mod.readMyFile())
                refg.stPre.push(mod.readMyFile());
            refg.loadPage(mod.readMyFile());
        }
        else if(btnText == "ref" || btnText == "Refresh")
            refg.loadPage(refg.tfAddress.getText());
        else if(btnText == "Set HomePage")
        {
            Model mod = new Model();
             try {
              String url =JOptionPane.showInputDialog("HomePage URL: ");
            if(!url.isEmpty())
                mod.writeToMyFile(url);
            else
                mod.writeToMyFile("https://google.com/");
            } 
            catch (Exception ex) {
            }
        }
        else if(btnText == "Add Favourite")
         {
             Model mod = new Model();
             String add = refg.tfAddress.getText();
             try {
              String title =JOptionPane.showInputDialog("Enter Title for Web Page: ");
            if(!title.isEmpty())
            {
                mod.writeToFavFile(title,add);
            }
            else
                mod.writeToFavFile(add,add);
            } 
            catch (Exception ex) {
            }
        }
        else if(btnText == "View Favourite")
        {
            Model mod = new Model();
            try {
                favFr = new JFrame("View Favourites");       
                JList<String> list = new JList<>(mod.readFavFile());
                list.setBounds(200,200,500,500);
                favFr.add(list);
                favFr.setSize(700,600);
                favFr.setLayout(new FlowLayout());  
                favFr.setVisible(true);  
                favFr.setLocationRelativeTo(null);
                favFr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                list.setSelectionMode(1);
                list.addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            String[] tokens = list.getSelectedValue().toString().split(" ");
                            String link = tokens[tokens.length-1];
                            refg.stPre.push(link);
                            refg.loadPage(link);
                        }
                    }
                    });
            }
            catch (Exception ex) {
            }
        }
        else if (btnText == "History" || btnText == "his" )
        {
            Model mod = new Model();
           
            try {
                hisFr = new JFrame("View History");
                JList<String> list = new JList<>(mod.readHisFile());
                list.setBounds(200,200,500,500);
                hisFr.add(list); 
                hisFr.setSize(700,600);  
                hisFr.setLayout(new FlowLayout());  
                hisFr.setVisible(true);  
                hisFr.setLocationRelativeTo(null);
                hisFr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
                list.setSelectionMode(1);
                list.addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        try{
                           if(!e.getValueIsAdjusting()) {
                            String[] tokens = list.getSelectedValue().toString().split(" - ");
                            String link = tokens[0];
                            refg.stPre.push(link);
                            refg.loadPage(link);
                        }
                       } catch (Exception ex) {
                        }
                       
                    }
                    });
            }
            catch (Exception ex) {
            }
        }
        else if (btnText == "Next" ||btnText == "nxt" )
        {
             if(!refg.stNxt.empty())
            {
                String url ="";
                if(!refg.stNxt.empty()){
                url = refg.stNxt.pop();
                refg.stPre.push(url);
                refg.loadPage(url);}
            }
             if(refg.stNxt.empty())
             {
                refg.btn.btnArr[1].setEnabled(true);
                refg.btn.btnArr[8].setEnabled(true);
             }
    
        }
        else if (btnText == "Previous" ||btnText == "pre" )
        {
            refg.btn.btnArr[1].setEnabled(true);
            refg.btn.btnArr[8].setEnabled(true);
            if(!refg.stPre.empty())
            {
                String url ="";
                if(!refg.stPre.empty()){
                    url = refg.stPre.pop();
                refg.stNxt.push(url);
                refg.loadPage(url);}
            }
            else
                refg.loadPage(refg.mod.readMyFile());
        }
        else if (btnText == "Search" ||btnText == "srch" )
        {
            String word =JOptionPane.showInputDialog("Enter the Word you want to search: ");
            String str = refg.jepMain.getText();
            int count =0;
            int startFrom = 0;  
            for(; ;)  
            {  

                int index = str.indexOf(word, startFrom);  

                if(index >= 0)  
                {  
                    count = count + 1;   
                    startFrom = index + 1;  
                }
                else  
                {  
                    break;  
                } 
            }
            searchCount = new JFrame("SearchCount");
            JPanel pan = new JPanel();
            JTextField tfSearch = new JTextField(60);
            tfSearch.setEditable(false);
            tfSearch.setText("Total Count Of your Searched Word is: "+count);
            pan.setLayout(new FlowLayout());
            pan.add(tfSearch); 
            searchCount.add(pan);
            searchCount.add(new JScrollPane(pan));
            searchCount.setSize(700,600);  
            searchCount.setLayout(new FlowLayout());  
            searchCount.setVisible(true);  
            searchCount.setLocationRelativeTo(null);
            searchCount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        }
    }
}
