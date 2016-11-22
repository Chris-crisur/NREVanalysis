
public class NREVanalysis{

	public static void main(String[] args){
        boolean dist = false; //use true when creating distributable JAR
        boolean gui = false;
        boolean hpc = false;
        if(args.length==1)
            gui = Boolean.parseBoolean(args[0]);
        else if(args.length>=2){
            gui = Boolean.parseBoolean(args[0]);
            hpc = Boolean.parseBoolean(args[1]);
        }
        if(hpc)
            gui = false;

        System.out.println("gui:" + gui +"\nhpc:"+hpc);

        new TreeReroots(gui,hpc,dist);
    }

}
