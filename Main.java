
public class Main{

	public static void main(String[] args){
	//use true when creating distributable JAR
	boolean dist = false;
	boolean gui = false;
	boolean hpc = false;
	if(args.length==1)
            gui = args[0];
        else if(args.length==2)
            gui = args[0];
            hpc = args[1];
            
        new TreeReroots(gui,hpc,dist);
    }

}
