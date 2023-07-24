
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UsmaAslam
 */
public class buttonView {
    JMenuBar menuBar;
    JMenu favMen, jHomeMenu, jFirewall;
    JMenuItem addFav, viewFav,homepage;
    JMenuItem it1;
    JRadioButton it2;
    JButton[] btnArr;
    JButton Go;
    JButton[] menu;
    String[] menuTxt;
    Image[] icons;
    String[] txt;
    JPanel panBtn;
    JPanel panMenu1;
    JPanel panMenu2;
    JPanel panMenu;
    JPanel upermain;
    String[] btnTxt;
    public buttonView(){
        initbuttonView();
    }
    public void initbuttonView()
    {
        menuBar = new JMenuBar();
        favMen = new JMenu("Favourite");
        addFav = new JMenuItem("Add Favourite");
        viewFav = new JMenuItem("View Favourite");
        jHomeMenu = new JMenu("Set HomePage");
        jFirewall = new JMenu("Set Firewall");
        it1 = new JMenuItem("Add/Delete Keyword");        
        homepage = new JMenuItem("Set HomePage");
        it2 = new JRadioButton("Enable");
        favMen.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        jHomeMenu.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        jFirewall.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        it1.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        it2.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        homepage.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        addFav.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        viewFav.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        
        it2.isEnabled();
        panBtn = new JPanel();
        panMenu = new JPanel();
        upermain = new JPanel();
        panMenu.setLayout(new GridLayout(1, 6));
        panBtn.setLayout(new GridLayout(1, 6));
        Go = new JButton("Go");
      
        btnArr[8] = new JButton(new ImageIcon("forward.jpg"));
        btnArr[9] = new JButton(new ImageIcon("home.jpg"));
        btnArr[10] = new JButton(new ImageIcon("refresh.jpg"));
        btnArr[11] = new JButton(new ImageIcon("history.jpg"));
        btnArr[12] = new JButton(new ImageIcon("search.jpg"));
        int j =0;
        for (int i = 7; i <13 ; i++) {
            btnArr[i].setActionCommand(btnTxt[j]);
            j++;
            btnArr[i].setBackground(java.awt.Color.white);
            panBtn.add(btnArr[i]);
        }
         menuBar.setPreferredSize(new Dimension(1850,40));
        upermain.setPreferredSize(new Dimension(1850,150));
        upermain.add(panMenu);
        upermain.add(panBtn);
        favMen.add(addFav);
        favMen.add(viewFav);
        jFirewall.add(it1);
        jFirewall.add(it2);
        menuBar.add(favMen);
        menuBar.add(jFirewall);
        Go.setPreferredSize(new Dimension(90,40));
    }
}
