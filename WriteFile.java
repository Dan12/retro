//Thuis section writes new highscore to file

//import statments
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;



public class WriteFile {
    
    public WriteFile(){
    append_to_file = false;
}

private String path;


public WriteFile(String file_path) {
path = file_path;

}

public WriteFile( String file_path, boolean append_value ) {
path = file_path;

}

public void writeToFile (String textLine) throws IOException {

FileWriter write =  new FileWriter(path, append_to_file);
PrintWriter print_line = new PrintWriter(write);

print_line.printf("%s" + "\n", textLine);

print_line.close();

}
boolean append_to_file;
}
