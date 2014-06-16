import java.lang.StringBuilder;
import java.util.HashMap;

// This class designs an object that provides:
// 1 - Per char translation of Brainfuck to C
// 2 - A standard C file template to build around a string
public class BrainfuckToC {
        private HashMap<String,String> brainMap;
        private StringBuilder keyString, bodySection;
        
        BrainfuckToC() {
            // Prepare the map!
            brainMap = new HashMap<String,String>();
            StringBuilder bodySection = new StringBuilder();
            brainMap.put(">",new String("\t\t++p;\n"));
            brainMap.put("<",new String("\t\t--p;\n"));
            brainMap.put("+",new String("\t\t++*p;\n"));
            brainMap.put("-",new String("\t\t--*p;\n"));
            brainMap.put(".",new String("\t\t*p = putchar(*p);\n"));
            brainMap.put(",",new String("*p = getchar();"));
            brainMap.put("[",new String("\n\twhile(*p) {\n"));
            brainMap.put("]",new String("\t}\n\n"));
        }
        
        // Public access to translation services
        public String translate(char input) {
        
            // Store input char as a string:
            StringBuilder keyString = new StringBuilder();
            keyString.append(input);
            String in = new String(keyString);
            
            // Check if it's actually Brainfuck:
            if(brainMap.containsKey(in))
                return brainMap.get(in);
                
            // If it isn't:
            else
                return null;
        }
        
        public void toBody(String add) {
            bodySection.append(add);
        }
        
        /* C FILE GENERATOR METHODS */
        
        public String prologue() {
            String includes = "#include <stdio.h>\n\n";
            String startMain = "int main(void)\n{\n\n";
            String brainFuckInit = "char P[30000],Q[9999],*q=Q,*x=q,*p=P;\n\n";
            
            return includes + startMain + brainFuckInit;
       }
       
        public String getBody() {
            return bodySection.toString();
        }
        
        public String epilogue() {
            return "\n\n}\n\n";
        }
                    
}
                


