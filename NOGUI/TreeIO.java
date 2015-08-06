/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Christopher Currin
 *  2014
 * 
 */
public class TreeIO{
	
	private List<String> alignmentsList;
	private String nextAlignment="";
	private boolean skip=false;
	private Process shell;
	private File LogFile;
	private String path;
	
	public TreeIO(boolean b) {
		if(b){
        	path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        	path=path.substring(0,path.lastIndexOf(File.separator)+1);
        	}
	    else
	    	path="";
		
        LogFile = new File(path+"Logs"+File.separator+"Log.txt");
	alignmentsList = new ArrayList<String>();

    }
	
    /**
        * Interaction with external application.
        * 
        * @param file		the external aplication file (used to access location)
        * @param cmds		instructions to give external application (using ProcessBuilder)
        * @return			the output of the external application
        */
    
    public String Shell(File file, String [] cmds){
        String output="";
        try { 
                ProcessBuilder pb = new ProcessBuilder(cmds);
                pb.redirectErrorStream(true);
            pb.directory(file.getParentFile());
            StringBuilder sb = new StringBuilder("");
            shell = pb.start();
            String line = "";

            BufferedReader reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));	
                while ((line = reader.readLine())!= null) {
                        sb.append(line + "\n");
                }
                System.out.println(shell.waitFor());//0 is success
            output = sb.toString();
            
            } catch(IOException | InterruptedException ex) {
                logError("Shell: " + ex.toString());
                return "1";
            }
        return output;
    }  
	
    /**
        * See Shell above. This adds a timelimit to the external application request and can alter check interval
        * 
        * @param file			the external aplication file (used to access location)
        * @param cmds			instructions to give external application (using ProcessBuilder)
        * @param timeLimit		maximum amount of time (in seconds) that the external application is allowed to run
        * @param interval		time between checking if external process is alive
        * @return				the output of the external application
        */
    
    public String ShellTimed(File file, String [] cmds, long timeLimit, int interval){
            long startTime = System.currentTimeMillis();
            timeLimit*=1000;
            interval*=1000;
            String output="";
            try { 
                ProcessBuilder pb = new ProcessBuilder(cmds);
                pb.redirectErrorStream(true);
                pb.directory(file.getParentFile());
                StringBuilder sb = new StringBuilder("");
                shell = pb.start();
                String line = "";

                BufferedReader reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
                
                while(true){
                        try{
                                long difference = System.currentTimeMillis()-startTime;
                                if(difference>timeLimit){
                                        shell.destroy();
                                        return "timed";
                                }
                                if(shell.exitValue()>-1)
                                        break;
                        }catch(IllegalThreadStateException itse){
                                //thrown until finished
                                if(interval>0)
                                        Thread.sleep(interval);
                        }
                }
                
                /* if JDK 1.8
                * 
                * while(shell.isAlive()){
                * 		if(System.currentTimeMillis()-startTime>timeLimit){
                                        shell.destroy();
                                        break;
                                }
                                //thrown until finished
                                if(interval>0)
                                        Thread.sleep(interval);
                * }
                */
                        
                while ((line = reader.readLine())!= null) {
                        sb.append(line + "\n");
                }
                System.out.println(shell.waitFor());//0 is success
                output = sb.toString();
        
        } catch(IOException | InterruptedException ex) {
            logError("Shell: " + ex.toString());
            return "1";
        }
    return output;
    }
    
	/**
	 * Method to write String to a File, with option to append or overwrite.
	 * 
	 * @param file		the File to be written to
	 * @param str		the String to written 
	 * @param append	whether to append to File (true) or overwrite (false) 
	 */
    public void Write(File file, String str, boolean append){
    	file.getParentFile().mkdirs();
        Writer writer;
        try{	
            writer = new BufferedWriter(new FileWriter(file, append));	//new writer
            writer.write(str);                                  //write string
            writer.close();
        } catch (IOException ex) {
            logError("Write: " + ex.toString());
        } 
    }
        
    /**
     * Allows FastTree support. 
     * The alignment file should be in the same folder as the FastTree application. 
     * FastTree commands are "FastTree -nt -gtr -nosupport -out TREE_FILE ALIGNMENT_FILE"
     * 
     * @param alignfilein		the name of the alignment file used as FastTree input
     * @param treefileout		the name of the tree file used as FastTree output
     * @return					FastTree output is returned. Useful for error-checking.
     */
    public String FastTree(String alignfilein, String treefileout){
            //alignfilein="Input"+File.separator+"alignment.phy";
            File ftf = new File(path+"FastTree/./FastTreeMP");
            String [] cmds ={ftf.getAbsolutePath(),"-nt","-gtr","-nosupport","-out",
                    ".."+File.separator+"Input"+File.separator+treefileout,
                    ".."+File.separator+"Input"+File.separator+alignfilein+"_CLEAN"};
            return Shell(ftf,cmds);
    }
    
    /**
     * Converts a file to a OS-compatible line-ending character, while making a backup of the original (ending with .bak).
     * 
     * @param filein		name of file (should be located under Input folder)
     * @param endline		the character to end each line with (Linux: \n)
     */
    public void convertFile(String filein, String endline){
    	String line = "";
    	StringBuilder sb = new StringBuilder("");
    	try {
    		File alignfile = new File(path+"Input"+File.separator+filein);
    		
            BufferedReader reader=new BufferedReader(new FileReader(alignfile));
            while((line=reader.readLine())!=null){
            	sb.append(line+endline);
            }
            reader.close();
            
            Files.copy(alignfile.toPath(), new File(path+"Input"+File.separator+filein+".bak").toPath(), StandardCopyOption.REPLACE_EXISTING); //make backup
            
            //filein.replaceAll(" ","");
            
            Write(alignfile,sb.toString(),false);
        } catch (IOException e) {
            logError("convertFile: " + e.toString());
          }
    }
    
    /**
     * Converts names of alignment to a hash-related String because not all characters are compatible with HYPHY. Writes mapped conversions to file under Input folder with "_CODES" at end of the name. 
     * Also converts a file to a OS-compatible line-ending character, while making a backup of the original (ending with .bak). 
     * 
     * @param alignmentName		name of alignment file (should be located under Input folder)
     * @param endline			the character to end each line with (Linux: \n)
     * @return					a Map of which hash-related String corresponds to each actual sequence.
     */
    public Map<String,String> convertNames(String alignmentName, String endline){
    	String hashStr = "";
    	Map <String, String> replace = new HashMap<>();
    	
    	String line = "";
    	StringBuilder newline = new StringBuilder(""), replaceBuilder = new StringBuilder();
    	try {
    		File alignfile = new File(path+"Input"+File.separator+alignmentName);
    		
            BufferedReader reader=new BufferedReader(new FileReader(alignfile));
            char ch='A';
            while((line=reader.readLine())!=null){
            	if(line.length()>0){
	            	if(line.charAt(0)=='>'){ //replace name with letter
	            		hashStr = String.valueOf(Math.abs(line.hashCode()));
	            		for(int num=0;num<10;num++){
	            			hashStr = hashStr.replace(String.valueOf(num), String.valueOf(ch));
	            			ch++;
	            		}
	            		ch='A';
	            		replace.put(hashStr, line.substring(1));//take out ">"
	            		replaceBuilder.append(hashStr+line+"\n");//separate code and original name using ">"
	            		newline.append(">"+hashStr+endline);
	            	}
	            	else{
	            		newline.append(line+endline);
	            	}
            	}
            }
            reader.close();
            
            Files.copy(alignfile.toPath(), new File(path+"Input"+File.separator+alignmentName+".bak").toPath(), StandardCopyOption.REPLACE_EXISTING); //make backup

            Write(new File(path+"Input"+File.separator+alignmentName+"_CLEAN"),newline.toString(),false); //write new alignment file
            Write(new File(path+"Input" + File.separator + alignmentName+"_CODES.txt"),replaceBuilder.toString(),false); //write code file if tree needs to analysed again
        } catch (IOException e) {
            logError("convertNames: " + e.toString());
          }
    	return replace;
    }
    
    /**
     * Method to retrieve the mapped sequence names to their corresponding hash-related Strings located in an appropriately named file (ending with "_CODES.txt").
     * 
     * @param alignmentName		name of alignment file (should be located under Input folder)
     * @return					a Map of which hash-related String corresponds to each actual sequence.
     */
    public Map<String,String> getNameCodes(String alignmentName){
    	Map <String, String> replace = new HashMap<>();
    	
    	String line = "", hashStr="",original="";
    	int loc=0;
    	try {
    		File alignCodeFile = new File(path+"Input"+File.separator+alignmentName+"_CODES.txt");
    		
            BufferedReader reader=new BufferedReader(new FileReader(alignCodeFile));
            while((line=reader.readLine())!=null){
            	loc=line.indexOf(">"); //separated using ">"
            	hashStr=line.substring(0,loc);
            	original=line.substring(loc+1);
            	replace.put(hashStr, original);
            }
            reader.close();
 
        } catch (IOException e) {
            logError("getNameCodes: " + e.toString());
          }
    	return replace;
    }
    
    /**
     * Method to input tree in line form (.nwk)
     * 
     * @param filename 	the name of the file for which tree must be extracted (located under Input folder)
     * @return			tree in String format
     */
    public String getTreeLine(String filename){
            File file = new File(path+"Input"+File.separator+filename);
             String line=null;
                try {
                    BufferedReader reader=new BufferedReader(new FileReader(file));
                    line=reader.readLine();//parse tree to program
                    reader.close();
                } catch (IOException e) {
                    logError("getTreeLine: " + e.toString());
                  }

            return line;
    }//end getTreeLine

    
    
    
    /**
     * Default HyphyResults call which assumes single-threaded approach.
     * See HyphyResults below. 
     * 
     * @param trees				collection of trees to test using HYPHY script
     * @param alignfilename		name of alignment file (must be located under Input folder)
     * @return					output from each SchmodelTest.bf HYPHY script is in a single array. If errors occur during method an empty array is returned.
     */
    public String[] HyphyResults(List<String> trees, String alignfilename){ //default
    	return HyphyResults(trees, alignfilename, 0,0); //single thread
    }
    
    public String[] HyphyResults(List<String> trees, String alignfilename, int multi){//define multithread type
    	return HyphyResults(trees, alignfilename, multi,0);
    }
    
    /**
     * Calls SchmodelTest.bf HYPHY script using Shell method for each tree in a collection. HYPHY program and SchmodelTest.bf script must be located under HYPHY folder.
     * 
     * @param trees				collection of trees to test using HYPHY script
     * @param alignfilename		name of alignment file (must be located under Input folder)
     * @param multi				whether approach should be single-threaded (0), multithreaded quick (1) or multithreaded with progress (2)
     * @param threadpool		number of threads to be used. By default (0), thead pool is number of CPUs (virtual included).
     * @return					output from each SchmodelTest.bf HYPHY script is in a single array. If errors occur during method an empty array is returned.
     */
    public String[] HyphyResults(List<String> trees, String alignfilename, int multi, int threadpool){//define multi thread and size of pool

    	final int MULTITHREAD=multi;
		final int SIZE=trees.size(); //trees.size(); //testing purposes
		int start=0;
		boolean completeOUT = false;
    	File outFile = new File(path+"HYPHY" + File.separator + "Backup Output" + File.separator + "HYPHY_"+alignfilename+".txt");
		String [] OUT = new String[SIZE]; //Output from HYPHY script
		String [] arr = {""}; //empty array to return on skip
		
		long tree_time = System.currentTimeMillis(), elapsedTime, remainingTime; //timing
		//String alignmentReplace = HYPHYAlignmentReplace(alignfilename);//Replace bad characters for alignment file; deprecated with use of convertNames method
		
		/*
		 * If HYPHY has already run and produced output, it will be in a file under HYPHY/Backup Output starting with "HYPHY_". 
		 * In which case the method needs to see how far the progress is for HYPHY and set accordingly.
		 */
		if(outFile.exists()){
			completeOUT=true;
			Log("HYPHY already run. If you want to run the HYPHY script again, delete the appropriate file in HYPHY/Backup Output");
    		String line;
		    int ind=-1;
		    StringBuilder lines = new StringBuilder();
		    try{
		        BufferedReader reader = new BufferedReader(new FileReader(outFile));	
		    	while ((line = reader.readLine())!= null) {
		    		if(line.equals("Likelihoods")){
		    			if(ind==-1){ //first line
		    				ind++;
		    				lines.append(line+"\n");
		    				continue;
		    			}
		    			OUT[ind] = lines.toString();
		    			ind++;
		    			lines = new StringBuilder("Likelihoods\n");
		    		}
		    		else if(line.equals("null")){
		    			completeOUT=false;
		    			ind=SIZE;//prevent start from becoming ind
		    		}
		    		else{
		    			lines.append(line+"\n");
		    		}
		    	}
		    	reader.close();
		    }catch(IOException io){
		    	logError("HyphyResults (outFile exists): " + io.toString());
		    }
		    if(ind==-1){
		    	completeOUT=false;
		    }
		    else if(ind!=SIZE){
		    	
		    	OUT[ind] = lines.toString();
		    	start=ind++;
		    	completeOUT=false;

		    	Log("HYPHY script continuing on remaining roots. Done so far: " + start);
		    }

		}//end if exists
		
		if(!completeOUT){
			    /** Initial Write of Alignment **/ 
			    File alignfile = new File(path+"HYPHY" + File.separator + "alignment");
			   // Write(alignfile,alignmentReplace,false);
			  try {
				Files.copy(new File(path+"Input" + File.separator + alignfilename+"_CLEAN").toPath(), alignfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				logError("HypyResults (not completeOut): " + e.toString());
			}
			        
			    
			    String cmdoutput, sec, min, hour, tim;
			    double day;
			    
																			    
			    File treefile = new File(path+"HYPHY" + File.separator + "tree");//put tree in file under HYPHY;
			    String [] cmds ={"./HYPHYMP","SchmodelTest.bf"};
			    String location="";
			    String replace="";
			    
			   // File from_HYPHYMP;
			   // File to_HYPHYMP;
			    File from_SchmodelTest;
				File to_SchmodelTest;
				File from_Alignment = new File(path+"");
				File to_Alignment = new File(path+"");
				List<HYPHYTask> treethreads = new ArrayList<>();
	
				/**for each tree**/
				String format = String.format("%%0%dd", 2);  //formating for ETA 
			    for (int i=start;i<SIZE;i++) {
			    	if(skip){
			    		skip=false;
			    		return arr;
			    	}
			    	Log((i+1) + " of " + SIZE + ": \t" + (((i+1)*100)/SIZE) + "%");
			    	tree_time=System.currentTimeMillis();
			    	replace=trees.get(i).replaceAll("-","");
		            replace=replace.replace(Character.toString((char) 124), ""); 
			    	if(MULTITHREAD>0){	//MULTITHREAD
				        try{
				        	/**Write tree**/
				        	treefile = new File(path+"HYPHY" + File.separator + "TEMP" + File.separator + alignfilename + i + File.separator + "tree");//put tree in file under HYPHY
				            treefile.getParentFile().mkdirs(); //make folders if not there	                
				            Write(treefile,replace + ";",false); //write tree to file
				            
				            
				        	/**HYPHYMP copy**/
				        	//from_HYPHYMP = new File(path+"HYPHY" + File.separator + "HYPHYMP");
				        	//to_HYPHYMP = new File(path+"HYPHY" + File.separator + "TEMP" + File.separator + alignfilename + i + File.separator + "HYPHYMP");
				        	//Files.copy(from_HYPHYMP.toPath(), to_HYPHYMP.toPath(), StandardCopyOption.REPLACE_EXISTING); 
				        	/**SchmodelTest copy**/
				        	from_SchmodelTest= new File(path+"HYPHY" + File.separator + "SchmodelTest.bf");
				        	to_SchmodelTest = new File(path+"HYPHY" + File.separator + "TEMP" + File.separator + alignfilename + i + File.separator + "SchmodelTest.bf");
				        	Files.copy(from_SchmodelTest.toPath(), to_SchmodelTest.toPath(), StandardCopyOption.REPLACE_EXISTING); 
				        	location = "TEMP" + File.separator + alignfilename + i + File.separator + "SchmodelTest.bf";
				        	/**Alignment copy**/
				        	from_Alignment= new File(path+"HYPHY" + File.separator + "alignment");
				        	to_Alignment = new File(path+"HYPHY" + File.separator + "TEMP" + File.separator + alignfilename + i + File.separator + "alignment");
				        	Files.copy(from_Alignment.toPath(), to_Alignment.toPath(), StandardCopyOption.REPLACE_EXISTING); 
				        }catch(IOException ex){
				        	Display("HyphyResults (multithread file copying): " + ex.toString());
				        	System.exit(-1);
				        }
			        /**...HYPHY Task...**/
			        	cmds[1]=location;
			        	treethreads.add(new HYPHYTask(from_Alignment,cmds));
			        }// end multi thread
			        else{	//SINGLETHREAD
			        	Write(treefile,replace + ";",false); //write tree to file
			        	/**...HYPHY Shell...**/
			        	//cmdoutput = ShellTimed(alignfile,cmds,30,0); //timeout test (in seconds)
			        	cmdoutput = Shell(alignfile,cmds);//shell to HYPHY
			        	OUT[i]=cmdoutput;
			        	if (cmdoutput==null){
			        		logError("HYPHY error for " + i + " of "+ alignfilename);
			        		continue;
			        	}
			        	else if(cmdoutput.equals("1")||cmdoutput.equals("")){
			        		if(skip){
					    		skip=false;
					    		return arr;
					    	}
			        		else{
			        			logError("HYPHY error for " + alignfilename);
			        			return arr;
			        		}
			        	}
			        	else if(cmdoutput.equals("timed")){
			        		Log("Timed out");
				    		return arr;
			        	}
			        	
					logFile(outFile,i+OUT[i]);
			        	
			        	elapsedTime = (System.currentTimeMillis()-tree_time)/1000;
			        	remainingTime = elapsedTime*(SIZE-i);
					    sec = String.format(format, remainingTime % 60);  
					    min = String.format(format, (remainingTime % 3600) / 60);  
					    hour = String.format(format, remainingTime / 3600); 
					    day = Integer.parseInt(hour)/24;
					    tim =  hour + "h " + min + "m " + sec +"s";  
					    if(day>7){
					    	Log("Would take longer than a week to complete. \t Estimated time remaining: " + day + " days ("+tim+")");
				    		//return arr;
					    }
					    
			        }
		        }//end for loop
			    
			    
			    /**start MULTITHREAD version**/
			    if(MULTITHREAD>0){
				    int cpus = Runtime.getRuntime().availableProcessors();
				    if(threadpool==0){
				    	threadpool=cpus;
				    }
					ExecutorService es = Executors.newFixedThreadPool(threadpool);
				    Log("starting " + treethreads.size() + " threads using " + cpus + " processors running " + threadpool + " processes");
			      //returns a list of Futures holding their status and results when all complete
			        List<Future<String>> tasks = new LinkedList<>();
			        try {
			        	if(MULTITHREAD==1){ //quicker MULTITHREAD
			        		tasks = es.invokeAll(treethreads);
			        		Log(tasks.size() +" Responses recieved.");
			        	}else{ //slower MULTITHREAD but with progress update
				        	for(HYPHYTask task: treethreads){
				        		tasks.add(es.submit(task)); 
				        	}
							
							boolean complete =false;
							int progress=0;
							while(!complete){
								
								progress=HYPHYTask.n;
		
								//System.out.println(progress + " _ " + (treethreads.size()));
								if(progress==(treethreads.size())){
									complete=true;
								}
								
								
								
								/*if(progressBar.getValue()==100){
									break;
								}*/
								//Thread.sleep(1000);
							}
			        	}
			        	
			        	
			        } catch (Exception e) {
			        	Thread.currentThread().interrupt();
						logError("HyphyResults (multithread task error): " + e.toString());
			        }
			        es.shutdown();
			        
			        List<String> cmdout = new ArrayList<>();
			        for(Future<String> future: tasks) {
			            try {
							cmdout.add(future.get());
						} catch (InterruptedException | ExecutionException e) {
							logError("HyphyResults (Future get): " + e.toString());
						}
			        }
			        
			        OUT = cmdout.toArray(new String[0]);//convert Collection to String array
			        deleteDirectory(new File(path+"HYPHY" + File.separator + "TEMP"));
			        
			        for(String s:OUT){
				    	logFile(outFile,s);
				    }
			    }//end MULTITHREAD
			    
			    
			    logFile(outFile,"Likelihoods"); //required as if file exists already, OUT is filled when it reaches a Likelihood. Therefore file needs to end with likelihood
			    
	    	}//end exist 

		    
		  //everything is in OUT array by now
    
		   return OUT; 
          
    }//end HyphyAnalysis
    /**
     * Converts some characters in sequence names. Deprecated by convertName method
     * 
     * @param alignfilename		name of alignment file
     * @return					corrected String
     */
    public String HYPHYAlignmentReplace(String alignfilename){ 
    	String line = "";
	    /**copy alignment file to "alignment" in HYPHY**/
	    StringBuilder sb = new StringBuilder();
	    try{
	        BufferedReader reader = new BufferedReader(new FileReader(new File(path+"Input" + File.separator + alignfilename+"_CLEAN")));
	        
	        List<String> lines = new ArrayList<>();
	        while((line = reader.readLine()) != null)
	            {
	            lines.add(line); 
	        }
	        reader.close();
	       
	        //To replace a symbol (for HYPHY)
	        String newtext = "";
	        for(int i=0;i<lines.size();i++){
	        	line= lines.get(i);
	        	if(line.length()>0){
		        	if(line.charAt(0)=='>'){
		        		newtext = line.replaceAll("-","");
		        		newtext = newtext.replace("?","");
		        		newtext = newtext.replace(Character.toString((char) 124),""); //   |
		        		if(newtext.contains(":")){
		        			newtext=newtext.substring(0,newtext.indexOf(":"));
		        		}
		        		lines.remove(i);
		        		
		        		lines.add(i, newtext);
		        	}
	        	}
	        	
	        }
	        for(String l:lines){
	        	sb.append(l + "\n");
	        }
		   }catch(IOException ex){
	    	logError("HYPHYAlignmentReplace: " + ex.toString());
		    }
	    return sb.toString();
    }
    
    /**
     * Generated trees are output to indivudal single line files
     * That is, each rerooted tree exists in a unique .nwk tree file
     * within a folder named after the original filename
     * 
     * @param trees 	the collection of trees to output
     * @param filename 	the folder to which the trees will be output to 
     */
    public void outputIndividualTrees(List<String> trees, String filename){
            File file;
            for (int i=1;i<=trees.size();i++) {
                file = new File(path+"Output" + File.separator + filename
                        + File.separator +filename+"_"+i+".nwk");                //where to output to
                file.getParentFile().mkdirs();			//make directories if they aren't there already
                Write(file,trees.get(i-1) + ";",false);			//write tree
            }
    }//end outputIndividualTrees

    /**
     * Displays a list of trees in a defined file name, with a header
     * The trees are those from the Input folder
     * 
     * @param trees		the collection of trees to output
     * @param filename	the name of the file that trees will be output to
     */
    public void outputTrees(List<String> trees, String filename){
            String strwrite=""; //header
           
            File file = new File(path+"Input" + File.separator + 
                    filename+".txt");
            file.getParentFile().mkdirs();                          //make directories if they aren't there already
            for(Iterator<String> it = trees.iterator();it.hasNext();){	//for each tree (contained in LinkedList)
                strwrite+=it.next() + "\n";			//write tree on a new line
            }
            Write(file,strwrite,false);
    }//end outputTrees
    
    /**
     * Returns a list of trees that have already been rooted from a File
     * 
     * @param rootFile		File where trees are located
     * @return				a list of every tree
     */
    public List<String> getRoots(File rootFile){
    	String line="";
    	List<String> roots = new ArrayList<>();
    	try {
            BufferedReader reader=new BufferedReader(new FileReader(rootFile));
            while ((line = reader.readLine())!= null) {
            	roots.add(line);
            }
            reader.close();
        } catch (IOException e) {
            logError("getRoots: " + e.toString());
        }
    	return roots;
    }
    
    /**
     * Checks whether File is empty (true) or has content (false). Only checks if first line of File contains no characters.
     * 
     * @param file		File to be checked
     * @return			true if empty, false if first line contains something
     */
    public boolean isEmpty(File file){
    	String line="";
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            line=reader.readLine();//parse line to program
            reader.close();
        } catch (IOException e) {
            logError("isEmpty: " + e.toString());
          }
        if(line==null||line.length()<1){
        	return true;
        }else{
        	return false;
        }
    }
    
    /**
     * Every file within a folder (including folders within that) is put into a Collection, but only if the file ends with an appropriate ending.
     * 
     * @param ending		applicable endings of files (separated by ",")
     * @param folder		the folder to have listed
     * @return				a collection of Strings with file names
     */
    public List<String> listFilesForFolder(String ending,final File folder) {
        List<String> files = new ArrayList<>();
        String [] ends = {ending};

        if(!folder.exists()){
        	return files;
        }
        
        if (ending.contains(","))
        	ends = ending.split(",");
        for(String end: ends){									//for each type of ending
        	for (final File fileEntry : folder.listFiles()) {	//for each file in folder
	            if (fileEntry.isDirectory()) {					//if directory
	                listFilesForFolder(end, fileEntry);			//go into directory
	            } else {
	                if(fileEntry.getName().endsWith(end)){		//if ends with specified ending e.g. "nwk"
	                	if(!isEmpty(fileEntry)){				//if file is not empty (produced if fasttree failed)
	                		files.add(fileEntry.getName());		//add file to list of files
	                	}
	                }
	            }
        	}
        }
        return files;
    }
    
    /**
     * A directory and every file/folder within it is deleted
     * 
     * @param directory		directory to be deleted
     * @return				true if deleted, false otherwise
     */
    public static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }
    
    /*
     * Log methods to display what's happening and record to file (can set which file)
     */
    /**
     * Logs a String preceded by the date and time using JTextArea and JScrollPane. Also outputs to the Log.txt file using logFile and also an alignment-specific log file if applicable.
     * 
     * @param str	String to be logged
     */
    public void Log(String str){
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm:ss");
        Date date = new Date();
        String logStr=dateFormat.format(date) + ":\t" + str + "\n";
    	logFile(logStr);
    	if(LogFile!=null){
    		logFile(LogFile,logStr);
    	}
    	System.out.println(logStr);
    }
    
    /**
     * Allows a String array to be logged easily.
     * 
     * @param str	array of Strings to be logged.
     */
    public void Log(String [] str){
    	for(String s:str){
    		Log(s);
    	}
    }
    /**
     * Allows a Collection (e.g. List) of Strings to be logged easily.
     * @param c		collection of Strings to be logged. 
     */
    public void Log(Collection<String> c){
    	Iterator<String> it = c.iterator();
    	while(it.hasNext()){
    		Log(it.next());    	
    	}
    }
    
    /**
     * Logs a String to Log.txt file under Logs folder
     * @param str	String to be logged
     */
    public void logFile(String str){
    	File file = new File(path+"Logs" + File.separator+"Log.txt");
    	Write(file,str+"\n",true);
    }
    /**
     * Logs a String to a specified file
     * @param file		file which will contain the logged String
     * @param str		String to be logged
     */
    public void logFile(File file, String str){
    	Write(file,str + "\n",true);
    }
    
    /**
     * Change the alignment-specific log file which is written to under default Log. 
     * @param f		new File to be set a log destination
     */
    public void setLogFile(File f){
    	LogFile=f;
    }
    
    /**
     * All errors should be submitted here and logged under Logs/Errors.txt
     * @param str	String to be error-logged
     */
    public void logError(String str){
    	File file = new File(path+"Logs" + File.separator+"Errors.txt");
        file.getParentFile().mkdirs();
    	Write(file,str +"\n",true);
    	System.out.println(str);
    }
    
    /*################################################################################
     * Methods to display what's happening
     */
    
    
    /**
     * Pop-up String to be displayed. Typically halts program
     * @param str		String to be displayed
     */
    public void Display(String str){
    	Log(str);
    }
    
    public void populateAlignmentList(List<String> array){
    	alignmentsList = new ArrayList<String>();
    	alignmentsList.addAll(array);
    }
    
    /**
     * The next alignment is gotten from the combo box. Must be called before getNextAlignment. 
     * Could also implement a listener of combobox, but unnecessary for now.
     */
    public void setNextAlignment(){
    	nextAlignment=alignmentsList.get(0);
    	alignmentsList.remove(0);
    }
    /**
     * Retrieves the next alignment and sets it as the current alignment being processed
     * @return		next alignment
     */
    public String getNextAlignment(){
    	return nextAlignment;
    }
    
}//end class