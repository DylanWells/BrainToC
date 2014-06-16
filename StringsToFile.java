import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;

public class StringsToFile
{
    private BufferedWriter infile;
    private FileWriter outfile;
    private static ArrayList<String> textBody;
    
    StringsToFile() {
        textBody = new ArrayList<String>();
    }
    public void append(String text) {
        textBody.add(text);      
    }

    public void makeFile(String path) {
        try {
            outfile = new FileWriter(path);
        } catch(IOException ex) { 
            System.out.println(ex.getMessage());
        }
        infile = new BufferedWriter(outfile);
        bufferList(textBody,textBody.size());
        try {
            infile.close();
        } catch(IOException ex) { 
            System.out.println(ex.getMessage());
        }
    }
    
    private void bufferList(ArrayList<String> buffer,int size) {
    
        if(size != 0) {
            try {
                infile.write(buffer.get(buffer.size()-size));
            } catch(IOException ex) { System.out.println(ex.getMessage()); }
            bufferList(buffer,size-1);
        }
    }

    public static void main(String[] args)
    {
        StringsToFile test = new StringsToFile();
        test.append("YAY");
    }

}
    