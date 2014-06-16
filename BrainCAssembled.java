import java.lang.StringBuilder;
import java.io.File;


public class BrainCAssembled {

    FileToChars readFile;
    StringsToFile writeFile;
    BrainfuckToC brainC;
    StringBuilder iterator;
    
    BrainCAssembled(String input, String output) { 
        brainC = new BrainfuckToC();
        writeFile = new StringsToFile();
        writeFile.append(brainC.prologue());
        translateFile(input);
        exportFile(output);
    }
    void translateFile(String filepath) {
        readFile = new FileToChars(filepath);
        iterator = new StringBuilder();
        interpret(readFile.listString());
    }
    void interpret(String file) {
        if(file.length() > 0) {
            writeFile.append(brainC.translate(file.charAt(0)));
            iterator = new StringBuilder();
            iterator.append(file);
            interpret(iterator.substring(1));
        }
    }
    void exportFile(String exportpath) {
        writeFile.append(brainC.epilogue());
        writeFile.makeFile(exportpath);
    }
}