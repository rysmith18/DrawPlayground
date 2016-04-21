
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author woytek/Ryan Smith
 */
public class JMenuFrame extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    
    DrawingPane dPane = new DrawingPane();
    ToolPanel tPane = new ToolPanel();
    public JMenuFrame() {
        super();
        
        setExtendedState((getExtendedState() & MAXIMIZED_BOTH) == MAXIMIZED_BOTH ? NORMAL : MAXIMIZED_BOTH); //This maximizes the window when it opens.  StackOverflow ftw!!
        
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        JMenu subMenu;
        File DrawFile = new File("DrawFile");
                
        this.setLayout( new BorderLayout() );
        this.setName( "Jay Manue Teeest Frum");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        this.add( tPane, BorderLayout.WEST );
        this.add( dPane, BorderLayout.CENTER );

        menuBar = new JMenuBar();
        
        menu = new JMenu( "File" );
        
        menuItem = new JMenuItem( "Save" );
        menuItem.setActionCommand( "Save" );
        menuItem.addActionListener( this );
        menu.add(menuItem);
        
        menuItem = new JMenuItem( "Open" );
        menuItem.setActionCommand( "Open" );
        menuItem.addActionListener( this );
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Clear");
        menuItem.setActionCommand("Clear");
        menuItem.addActionListener( this );
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Delete");
        menuItem.setActionCommand("Delete");
        menuItem.addActionListener( this );
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem( "Exit" );
        menuItem.setActionCommand( "Quit" );
        menuItem.addActionListener( this );
        menu.add(menuItem);
        
        
        
        
        menuBar.add( menu );
        
        
        
        menuBar.add( Box.createHorizontalGlue() );
        
        menu = new JMenu( "Help" );
        menuItem = new JMenuItem( "About" );
        menuItem.setActionCommand( "MenuAbout" );
        menuItem.addActionListener( this );
        
        menu.add( menuItem );
        
        menuBar.add( menu );
        
        this.setJMenuBar( menuBar );
        
        //this.setSize( new Dimension(this.getPreferredSize() ) ); 
        this.setSize( 600, 600 );
        this.setVisible( true );
    }
    
    public void actionPerformed( ActionEvent e ) {
    	
        switch( e.getActionCommand() ) {
            case "Save":
            	if(dPane != null) {
            		String myFile = "myFile.jpg";
            		accessFile(myFile);
            		BufferedImage image = new BufferedImage(dPane.getWidth(), dPane.getHeight(), BufferedImage.TYPE_INT_RGB);
            		dPane.print(image.getGraphics());
            		try {
            			FileOutputStream stream = new FileOutputStream("bar.dat");
            			ImageIO.write(image, myFile, new File(myFile));
            		}
            		catch(IOException e1) {
            			e1.printStackTrace();
            		}
            		
            	}
                System.out.println( "Your file has been saved." );
                break;
            case "Open":
            	final JFileChooser file = new JFileChooser();
            	int retrn = file.showOpenDialog(JMenuFrame.this);
            	if(retrn == JFileChooser.APPROVE_OPTION) {
            		file.setCurrentDirectory(new File(System.getProperty("user.home")));
            		File fileName = file.getSelectedFile();
            		
            	}
                System.out.println( "File opened" );
                break;
            case "Clear":
            	DrawingPane.myList.clear();
            	repaint();
            	break;
            case "Quit":
                System.exit(0);
                break;
            case "MenuAbout":
            	JOptionPane.showMessageDialog(null, "This program allows the user to draw four different types of shapes on the screen and change the color of those shapes. \n It also allows the user to save their creation as a jpeg image and load it from the disk.");
            	break;
            default:
                System.out.println( "I DON'T KNOW HOW YOU GOT HERE!!!!" );
                System.exit(-1);
                break;
        }
    }
    
    public String accessFile(String myFile) {
    	JFrame frame = new JFrame();
    	String obj = JOptionPane.showInputDialog("Please enter name for file");
    	System.out.println(obj);
    	myFile = obj;
    	return myFile;
    }
}
