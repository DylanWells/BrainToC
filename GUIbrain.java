//////////////
// MOUSE INPUT
//////////////
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;
//////////////
// GRAPHICS
//////////////
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
//////////////
// LAYOUT
//////////////
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.SwingUtilities; 
//////////////
// FILES
//////////////
import java.io.File;
import javax.swing.JFileChooser;
import java.io.IOException;

public class GUIbrain extends JPanel implements MouseListener,MouseMotionListener
{   
    public static BrainCAssembled brainTranToC;
    
    // Import button location
    public static final double importXborderL = 145;
    public static final double importXborderR = 250;
    public static final double importYborderT = 430;
    public static final double importYborderB = 465;
    // Export button location
    public static final double exportXborderL = 250;
    public static final double exportXborderR = 355;
    public static final double exportYborderT = 430;
    public static final double exportYborderB = 465;
    // Drawing coordinates
    public static final int impButX = (int)importXborderL;
    public static final int impButY = (int)importYborderT;
    public static final int expButX = (int)exportXborderL;
    public static final int expButY = (int)exportYborderT;
    public static final int errorX = 145;
    public static final int errorY = 340;
    /////////////////
    // Directory path
    public static final String path = System.getProperty("user.dir");
    // Translate files
    public static String brainFilePath;
    public static String cFilePath; 
    // Image paths
    public static final String background = path + "/image/braincBackground.png";
    public static final String exportError = path + "/image/exportError.png";
    // Import button paths
    public static final String imDefault = path + "/image/button/import/importDefault.png";    
    public static final String imHover = path + "/image/button/import/importHover.png";
    public static final String imPress = path + "/image/button/import/importPress.png";
    public static final String imChose = path + "/image/button/import/importChose.png";
    public static final String imChoseHover = path + "/image/button/import/importChoseHover.png";
    // Export button paths
    public static final String exDefault = path + "/image/button/export/exportDefault.png";    
    public static final String exHover = path + "/image/button/export/exportHover.png";
    public static final String exPress = path + "/image/button/export/exportPress.png";
    /////////////////
    // Images
    public static final ImageIcon bground = new ImageIcon(background);
    public static final ImageIcon exError = new ImageIcon(exportError);
    // Import button states
    public static final ImageIcon imDef = new ImageIcon(imDefault);    
    public static final ImageIcon imHov = new ImageIcon(imHover);
    public static final ImageIcon imPre = new ImageIcon(imPress);
    public static final ImageIcon imCho = new ImageIcon(imChose);
    public static final ImageIcon imChoHov = new ImageIcon(imChoseHover);
    // Export button states
    public static final ImageIcon exDef = new ImageIcon(exDefault);    
    public static final ImageIcon exHov = new ImageIcon(exHover);
    public static final ImageIcon exPre = new ImageIcon(exPress);
    // To paint or not to paint
    public static boolean expError; 
    public static boolean isImHover;
    public static boolean isImPress;
    public static boolean isImChose;
    public static boolean isExHover;
    public static boolean isExPress;
    
  //////////////////////////////////////////////////////
 ////////////////////IMAGE LOGIC///////////////////////
//////////////////////////////////////////////////////     
    // This method determines if an input file exists by checking path
    public static boolean isExportError() {
        return (brainFilePath==null || brainFilePath == "");
    }
    // This method checks the bools that determine which import button to display
    public static ImageIcon importState() {
        if(isImChose) {
            if(isImPress)
                return imPre;
            if(isImHover)
                return imChoHov;
            return imCho;
        }
        if(isImPress)
            return imPre;
        if(isImHover)
            return imHov;
        return imDef;          
    }
    // This method checks the bools that determine which export button to display
    public static ImageIcon exportState() {
        if(isExPress)
            return exPre;
        if(isExHover)
            return exHov;
        return exDef;   
    }
    // This method determines if the mouse is within the import button area
    public static boolean importHover(double xPos,double yPos) {
        int check = 0;
        if((xPos > importXborderL) && (xPos < importXborderR)) 
            check+=1;
        if((yPos > importYborderT) && (yPos < importYborderB)) 
            check+=1;
        return check == 2;
    }
    // This method determines if the mouse is within the export button area
    public static boolean exportHover(double xPos,double yPos) {
        int check = 0;
        if((xPos > exportXborderL) && (xPos < exportXborderR)) 
            check+=1;
        if((yPos > exportYborderT) && (yPos < exportYborderB)) 
            check+=1;
        return check == 2;
    }
    ///////////////////////////////////////////////////////
    public final void translateImport(String readFrom,String writeTo) {
        GUIbrain doIt = new GUIbrain(readFrom,writeTo);
    } 
  //////////////////////////////////////////////////////
 ////////////////////MAIN CONSTRUCTOR//////////////////
//////////////////////////////////////////////////////         
    GUIbrain() {
        addMouseListener(this);
        addMouseMotionListener(this);
        createGUI(this);
    }
    // This constructor calls on the translator
    /// Uses file paths to grab files
    GUIbrain(String inPath, String outPath) {
        addMouseListener(this);
        addMouseMotionListener(this);
        brainTranToC = new BrainCAssembled(inPath,outPath);
        createGUI(this);
        repaint();
    }
  //////////////////////////////////////////////////////
 ////////////////////GUI INIT//////////////////////////
//////////////////////////////////////////////////////  
    public static void createGUI(GUIbrain draw) {
        JFrame window = new JFrame("BrainToC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(500,520));
        window.getContentPane().add(draw);
        window.setGlassPane(draw);
        //window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        draw.setFocusable(true);
        draw.setVisible(true);
    }    
  //////////////////////////////////////////////////////
 ///////////////////GRAPHICS LOOP//////////////////////
//////////////////////////////////////////////////////      
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        bground.paintIcon(this,g,0,0);
        importState().paintIcon(this,g,impButX,impButY);
        exportState().paintIcon(this,g,expButX,expButY);
        if(isExHover)
            if(isExportError())
                exError.paintIcon(this,g,errorX,errorY);
    }  
  //////////////////////////////////////////////////////
 ///////////////////MAIN METHOD////////////////////////
//////////////////////////////////////////////////////    
    public static void main(String[] args)
    {   
        // Begin program loop
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()  {
                GUIbrain gui = new GUIbrain();
            }
        });
    }
  //////////////////////////////////////////////////////
 ///////////////////MOUSE EVENTS///////////////////////
//////////////////////////////////////////////////////  
@Override
public void mouseMoved(MouseEvent e)  {
    Point mouse = e.getPoint();
    isImHover = importHover(mouse.getX(),mouse.getY());
    isExHover = exportHover(mouse.getX(),mouse.getY());
    isImChose = !isExportError();
    if(!isImHover) {
        isImPress = false;
    }
    if(!isExHover) {
        isExPress = false;
    }   
    repaint();          
    }  
@Override
public void mouseDragged(MouseEvent e) {
    System.out.println("Mouse dragged");
    Point mouse = e.getPoint();
    isImPress = importHover(mouse.getX(),mouse.getY());
    isExPress = exportHover(mouse.getX(),mouse.getY());
    isImChose = !isExportError();
    repaint();
    }    
@Override
public void mousePressed(MouseEvent e) {
    System.out.println("Mouse pressed");

    Point mouse = e.getPoint();
    isImPress = importHover(mouse.getX(),mouse.getY());
    isExPress = exportHover(mouse.getX(),mouse.getY());
    isImChose = !isExportError();
    repaint();
    }

// This is where the file dialog appears
@Override
public void mouseReleased(MouseEvent e) {
    System.out.println("Mouse released");
    Point mouse = e.getPoint();
    isImPress = false;
    isExPress = false;
    isImHover = importHover(mouse.getX(),mouse.getY());
    isExHover = exportHover(mouse.getX(),mouse.getY());
    isImChose = !isExportError();
    repaint();
 
    if(isImHover) { // import dialog activated
        isImChose = false;
        System.out.println(isImChose);
        System.out.println(isExportError());

        JFileChooser chooseImport = new JFileChooser();
        int validImport = chooseImport.showOpenDialog(null);
        if(!isExportError()) {
            brainFilePath = "";
            isImChose = isExportError();
            isImHover = importHover(mouse.getX(),mouse.getY());
            isImPress = isImHover;
            System.out.println(brainFilePath);
            System.out.println(isExportError());
            repaint();
        }        
        if(validImport == JFileChooser.APPROVE_OPTION) {
            File holdImport = new File(chooseImport.getSelectedFile().getAbsolutePath());
            System.out.println(holdImport.toString());
            if(holdImport.exists()) {
                brainFilePath = holdImport.toString();
                isImHover = importHover(mouse.getX(),mouse.getY());
                isImPress = isImHover;
                isImChose = isExportError();
                repaint();
            }
            else {
                isImHover = importHover(mouse.getX(),mouse.getY());
                isImPress = isExportError();
                repaint();
            } 
        }
    }
 
    if(isExHover) { // export dialog activated
        if(isImChose) {
            JFileChooser chooseExport = new JFileChooser();
            int validExport = chooseExport.showSaveDialog(null);
            if(validExport == JFileChooser.APPROVE_OPTION)  {
                File holdExport = new File(chooseExport.getSelectedFile().getAbsolutePath());
                System.out.println(holdExport.toString());
                cFilePath = holdExport.toString();
                translateImport(brainFilePath,cFilePath);
                isImHover = importHover(mouse.getX(),mouse.getY());
                isImPress = isImHover;
                repaint();
             }
            else {
                isImHover = importHover(mouse.getX(),mouse.getY());
                isImPress = false;               
                repaint();            
            }   
        }
    }
}
          
@Override
public void mouseEntered(MouseEvent e) {
    System.out.println("Mouse entered");
    repaint();
    }
@Override   
public void mouseExited(MouseEvent e) {
    System.out.println("Mouse exited");
    repaint();
    }
@Override
public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse clicked");
    Point mouse = e.getPoint();
    isImPress = false;
    isExPress = false;
    if(isImHover) {
        if(isImChose) {
            brainFilePath = "";
            isImChose = isExportError();
            isImHover = importHover(mouse.getX(),mouse.getY());
            isImPress = isImHover;
            System.out.println(brainFilePath);
            System.out.println(isExportError());
            repaint();
        } 
    }
    repaint();
    } 
}// End of program

