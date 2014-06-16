//File reading
import java.io.File;
import java.io.Reader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//Exceptions
import java.io.IOException;
import java.lang.NullPointerException;
import java.io.FileNotFoundException;

//Utilities
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.lang.StringBuffer;

public class FileToChars 
{
    // File reading variables
    private File file;
    private Charset charset;
    private Reader takeChar;
    private InputStream input;
    
    // List 
    private int charCode;
    private CharList list;
    private CharList firstItem; ///////
    private StringBuffer compose;
    
    // If: Object instantiated without filepath arg
    // Then: Remind user of class how it should be used
    FileToChars()
    {
        System.out.println("\nFileToChars requires filepath arg:");
        System.out.println("\tFileToChars(String filepath)\n");
    }
        
    // Constructor does the following:
    // 1 - Receives filepath
    // 2 - Instantiate list and StringBuffer
    // 3 - Tries to open file
    // 4 - Prepare to read it
    // 5 - File -> List -> String
    FileToChars(String filepath) // 1
    {
        // 2
        list = new CharList();
        compose = new StringBuffer();
        
        // 3
        try {
            file = new File(filepath);  
        } catch(NullPointerException ex) {
            System.out.println(ex.getMessage());
            }
            
        // 4
        charset = StandardCharsets.UTF_8;
        try {
            input = new FileInputStream(file);
        } catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            }
        takeChar = new InputStreamReader(input,charset);
        
        // 5
        fill(list);
        buildString(list);
    }
        /////////////////////
        /* PRIVATE METHODS */
        /////////////////////        
        private void fill(CharList next) {
            try {
                if((charCode = takeChar.read()) != -1) {
                    next.data = (char) charCode;
                    next.tail = new CharList();
                    fill(next.tail);
                    }
                }   catch(IOException ex) {
                    System.out.println(ex.getMessage());
                    }         
        }         
        // Constructs string from list
        // Utility for public method listString() below
        private void buildString(CharList item) {
            if(item.data != '\0') {
                compose.append(item.data);
                buildString(item.tail);
            }
        }        
        /////////////////////
        /* PUBLIC METHODS */
        /////////////////////
        
        // If you want a string, it's yours.
        public String listString() { 
            buildString(list);
            return compose.toString(); 
        }        
        // Next 2 methods afford safe traversing of list items to           strangers
        public CharList getFirstItem() {
            return list;
        }
        public CharList getNextItem(CharList currentChar) {
            return currentChar.tail;
        }
        // Safe access to chars held by list items
        public char getChar(CharList listItem) {
            return listItem.data;
        }
    // Two-component list: 
    // Each item contains a char (data), and access to the next char (tail)
    // Intended primarily for use with recursion
    private class CharList
    {
        private char data;
        private CharList tail;
        
        CharList() { data = '\0'; }    
    }

// End class
} 