package greenwhishes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.toedter.calendar.JCalendar;

public class main extends JFrame {

  private JPanel container = new JPanel();
  private JTextField nametf,societetf, datetf ;   
  private JLabel nomlabel = new JLabel("votre nom : ");
  private JLabel societelabel = new JLabel("nom de la societe :");
  private JLabel datelabel = new JLabel("date (facultatif)");
  private JButton b = new JButton ("enregister");

  public main(){      
	//paramettre de la fenetre
    this.setTitle("greenwishes");
    this.setSize(300, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    
    //cr�ation des different �l�ment d'interface
    
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());

    nametf = new JTextField();
        

    Font police = new Font("Arial", Font.BOLD, 14);
    nametf.setFont(police);
    nametf.setPreferredSize(new Dimension(150, 30));
    nametf.setForeground(Color.BLUE);


    societetf = new JTextField();
         
    societetf.setFont(police);
    societetf.setPreferredSize(new Dimension(150, 30));
    societetf.setForeground(Color.BLUE);	
    
    datetf = new JTextField();
    
    datetf.setFont(police);
    datetf.setPreferredSize(new Dimension(150, 30));
    datetf.setForeground(Color.BLUE);
    
    JCalendar calendar = new JCalendar();
    JPanel top = new JPanel();  
    //action du bouton 
    b.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent ae){
    		   //test des champ si ils sont vide
    		   	if (nametf.getText().equals("")) {
					nametf.setBackground(Color.decode("#ff411f"));
    		   		return;
				}else{
					nametf.setBackground(Color.decode("#ffffff"));
				}
    		   	if (societetf.getText().equals("")) {
    		   		societetf.setBackground(Color.decode("#ff411f"));
    		   		return;
				}else{
					societetf.setBackground(Color.decode("#ffffff"));
				}
    		      java.util.Date tempo =  calendar.getDate();
    		      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    		      String date = df.format(tempo);
    		      
    		      //appel fichier qui ecrit dans le fichier excel
    		     WriteExcel test = new WriteExcel(nametf.getText(),societetf.getText(),date);
    		     //recuperation du path du fichier excel
    		     URL url = getClass().getResource("test.xls");
    		      test.setOutputFile(url.getPath());
    		      
    		  		try {
						test.write();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		  		nametf.setText("");
    		  		societetf.setText("");
    		      System.out.println("le r�sultat est dans le fichier bin/test.xls de greenwishes");
    		   }
    		});

    top.add(nomlabel);
    top.add(nametf);
    top.add(societelabel);
    top.add(societetf);
    top.add(datelabel);
    top.add(calendar);
    top.add(b);

    this.setContentPane(top);
    this.setVisible(true);         
  }      

  
//lancement application
  public static void main(String[] args) throws BiffException{
	main toto = new main();
    
  }
}
