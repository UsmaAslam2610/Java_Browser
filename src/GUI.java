
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.MenuListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UsmaAslam
 */
public class GUI {
    JFrame fMain;
    JTextField tfAddress;
    JEditorPane jepMain;
    JPanel srchPan;
    btnHandler hnd;
    JFrame homeMain;
    buttonView btn;
    JTextField tfHadd;
    Stack<String> stPre;    
    Stack<String> stNxt;
    Model mod;
    ArrayList urlArray;
    Integer i;
    String preURL ;
    public GUI(){
        initGui();
    }
    private void initGui(){
        urlArray = new ArrayList();
        i = 0;
        preURL = "";
        fMain = new JFrame("Browser");
        fMain.setLayout(new FlowLayout());
        mod = new Model();
        jepMain = new JEditorPane();
        jepMain.setEditable(false);
        tfAddress = new JTextField(154);
        btn = new buttonView();
        stPre=new Stack<String>();
        stNxt=new Stack<String>();
        srchPan= new JPanel();
        hnd = new btnHandler(this);
         for (int i = 0; i < btn.txt.length; i++) {
            btn.btnArr[i].addActionListener((ActionListener)hnd);
        }
        for (int i = 7; i <13 ; i++) {
            btn.btnArr[i].addActionListener((ActionListener) hnd);
        }
        btn.homepage.addActionListener((ActionListener)hnd);
        btn.jHomeMenu.add(btn.homepage);
        btn.menuBar.add(btn.jHomeMenu);
        btn.addFav.addActionListener((ActionListener)hnd);
        btn.favMen.add(btn.addFav);
        btn.menuBar.add(btn.favMen);
        btn.viewFav.addActionListener((ActionListener)hnd);
        btn.favMen.add(btn.viewFav);
        btn.menuBar.add(btn.favMen);
        srchPan.setLayout(new FlowLayout());
        URL();
        loadPage("https://google.com/");
        tfAddress.setPreferredSize(new Dimension(1715,40));
        srchPan.setPreferredSize(new Dimension(1805,50));
        srchPan.add(tfAddress);
        srchPan.add(btn.Go);
        jepMain.setPreferredSize(new Dimension(1850,670));
        Container c = fMain.getContentPane();
        c.setLayout(new FlowLayout());
        btn.btnArr[0].setEnabled(false);
        btn.btnArr[1].setEnabled(false);
        btn.btnArr[7].setEnabled(false);                
        btn.btnArr[8].setEnabled(false);
        fMain.add(btn.menuBar);
        fMain.add(btn.upermain);
        fMain.add(srchPan,BorderLayout.CENTER);
        fMain.add(jepMain);
        fMain.add(new JScrollPane(jepMain));
        fMain.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        fMain.setLocationRelativeTo(null);
        fMain.setVisible(true);
        fMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void loadPage(String url){
        try{
             if(!(url.equals(mod.readMyFile())))
               {
                 mod.writeHistoryFile(url);
               }
             if(url.equals(mod.readMyFile()))
               {
                 btn.btnArr[0].setEnabled(false);
                 btn.btnArr[7].setEnabled(false);
               }
             else
             {
                 btn.btnArr[0].setEnabled(true);
                 btn.btnArr[7].setEnabled(true);
             }
            jepMain.setPage(url);
            tfAddress.setText(url);
            
        }
        catch(IOException ioexp){
            JOptionPane.showMessageDialog(null,"page not found","bad url",JOptionPane.ERROR_MESSAGE);    
        }
    }
     public void URL(){
        jepMain.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
                 if(!stPre.isEmpty()&& stPre.lastElement().equals(e.getURL().toString()))
                        stPre.push(e.getURL().toString());
                 loadPage(e.getURL().toString());
                }
            }
        });

         btn.Go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(!stPre.isEmpty()&& stPre.lastElement().equals(tfAddress.getText()))
                    stPre.push(tfAddress.getText());
                loadPage(tfAddress.getText());
            }
        });

        tfAddress = new JTextField(154);
        tfAddress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!stPre.isEmpty()&& stPre.lastElement().equals(e.getActionCommand().toString()))
                    stPre.push(e.getActionCommand().toString());
            loadPage(e.getActionCommand());
            }
        });
    }
    
      
}
