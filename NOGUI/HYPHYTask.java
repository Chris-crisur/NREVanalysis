import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;


public class HYPHYTask implements Callable<String> {
	static volatile int n=0;
	boolean done=false;
	File file;
	String [] cmds;
	String output;
	
	
	public HYPHYTask(File f, String [] cs) {
            file=f;
            cmds=cs;
    }
	
	public boolean getCompleted(){
		return done;
	}
	
	public String call() throws Exception{
        try {
            ProcessBuilder pb = new ProcessBuilder(cmds);
            pb.directory(file.getParentFile());
            StringBuilder sb = new StringBuilder("");
            Process shell = pb.start();
            shell.waitFor();
            
            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));	
        	while ((line = reader.readLine())!= null) {
        		sb.append(line + "\n");
        	}
           output = sb.toString();
               
        } catch(IOException | InterruptedException ex) {
        	System.out.println(ex.toString());
        }
        n++;
        done=true;
        return output;
    }
}
