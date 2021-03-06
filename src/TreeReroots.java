
/**
 * @author Christopher Currin
 * @version 0.9
 * @date 28/09/2014
 *	@startdate 27/04/2014
 */
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TreeReroots {
    
    final private TreeIO io;
    static boolean skip=false;
    private String path;
    private boolean build;

    /*
     * class instantiator
     */    
    public TreeReroots(boolean gui, boolean hpc,boolean b){
    	build=b;
    	io = new TreeIO(gui, hpc, build);
        run();
}//end TreeReroots

    /**
     * Remove any numbers from tree as they aren't necessary for HyPhy
     * 
     * @param tl 	treeline in char format
     * @return 		the tree with no numbers in String format
     */
    public String getNameTree(char [] tl){
            String nnt = "("; //no number tree
            int i=0;
            while(tl[i]!=';'){				//while not end of tree (tree ends with ';'
                    if(tl[i]==':'||tl[i]==')'){
                        i++;//move one char up from :
                        //ignore none sequence characters (except brackets and commas)
                        while(Character.isDigit(tl[i])||tl[i]=='.'||tl[i]==':'||tl[i]=='-'){ 				
                            i++;
                         }
                    }
                    else{
                        i++;
                    }
                    
                    if(tl[i]!=':'&&tl[i]!=';')
                        nnt+=tl[i]; //add char which is not number
            }
            
            return nnt;
    }//end getNoNumTree
    
    /**
     * Removes brackets of tree
     * 
     * @param btl 	broken tree line (no numbers) in char format
     * @return 		tree with only contents and commas
     */
    public String getEmptyTree(char [] btl){
            String etl = "";
            for(int i=0;i<btl.length;i++){
                    if(btl[i]=='('||btl[i]==')'){
                            continue;//skip iteration of 'for' loop. i.e. do nothing
                    }else{
                            etl+=btl[i]; //add char to String
                    }
            }
            return etl;
    }//end getEmptyTree


    /**
     * go through tree and ensure balanced brackets 
     * have one for char input and string input
     * @param 	btl the tree in String (or char array) format
     * @return	0 if brackets are balanced. >0 if too many open brackets; <0 if too many close brackets 
     */
    public int countBrackets(char [] btl){
            int bcnt=0;
            for(int i=0;i<btl.length;i++)
                    if(btl[i]=='('){
                            bcnt++;
                    }
                    else if(btl[i]==')'){
                            bcnt--;
            }
            return bcnt;
    }//end countBrackets
    public int countBrackets(String tl){
            int bcnt=0;
            for(int i=0;i<tl.length();i++)
                    if(tl.charAt(i)==('(')){
                            bcnt++;
                    }
                    else if(tl.charAt(i)==')'){
                            bcnt--;
            }
            return bcnt;
    }//end countBrackets
    public int countUnderscores(String tl){
        int under=0;
        while(tl.contains("_")){
            under++;
            tl=tl.substring(tl.indexOf('_')+1);
        }
        return under;
    }
    public List<String> getAllData(BinaryTree<String> tree){
        List<String> list = tree.getLRNIterator();
        Collections.sort(list);
        return list;
    }
    /**
     * FastTree produces trifurcated trees, so this alters it to a binary (bifurcated) tree.
     * 
     * @param treeline	the tree in String format
     * @param meth		the method by which the tree should be bifurcated (see switch case)
     * @return			the binary tree is returned
     */
    public String convertToBinary(String treeline,int meth){
        String newtree = "";
        String [] treepart = new String[3];
        int bcnt=0; //bracket count
        int ccnt=0;
        int i = 0;
        int ind=1;
        for (char c: treeline.toCharArray()){
            if(c=='('){ //open bracket
                bcnt++;
            }
            else if(c==')'){
                bcnt--;
            }
            else if(c==','){
                if(bcnt==1){
                    treepart[ccnt]=treeline.substring(ind,i);
                    ind=i+1;
                    ccnt++;
                	//break;
                }
            }
            i++;
        }
        treepart[ccnt]=treeline.substring(ind,treeline.length()-1);
        if(treepart[2]!=null){
	        switch(meth){
	        	case 0: newtree="("+treepart[0]+",("+treepart[1] + "," + treepart[2]+"))"; //(A,(B,C))
	        			break;
	        	case 1: newtree="("+treepart[0]+",("+treepart[2] + "," + treepart[1]+"))"; //(A,(C,B))
						break;
	        	case 2: newtree="(("+treepart[0]+","+treepart[1]+"),"+treepart[2] + ")"; //((A,B),C)
						break;
	        	case 3: newtree="(("+treepart[0]+","+treepart[2]+"),"+treepart[1] + ")"; //((A,C),B)
						break;
	        }
        }
        else{
        	newtree=treeline;
        }
        return newtree;
    }
    
    /**
     * Converts a tree in String format to BinaryTree format. 
     * Static method present in BinaryTree class
     * 
     * @param treeline	tree in string format
     * @return 			tree in BinaryTree format
     * @see				BinaryTree
     */
    public BinaryTree<String> generateTree(String treeline) {
        return generateTree(treeline,false);
    }
    public BinaryTree<String> generateTree(String treeline,boolean withID){
        BinaryTree<String> tree;
        // assign a unique value to each node
        if(withID) {
            try {
                // sleep for 1 millisecond to ensure unique reference from time
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tree = new BinaryTree<>(String.valueOf(System.currentTimeMillis()));
        }else {
            tree = new BinaryTree<>("");
        }
            int i=0; //index
            int bcnt=0; //bracket count

            if(treeline.charAt(treeline.length()-1)==';'){ //remove ; of initial tree
                    treeline=treeline.substring(0,treeline.length()-1);
            }

            while(i<treeline.length()){
                char ch = treeline.charAt(i);
                    if(treeline.charAt(i)=='('){ //open bracket
                            bcnt++;
                    }
                    else if(treeline.charAt(i)==')'){ //close bracket
                            bcnt--;
                            if(bcnt==1&&treeline.charAt(i+1)==','){     //if close bracket is end of left subtree
                                    tree.addLeft(generateTree(treeline.substring(1,i+1),withID));	//split on comma - up 1 char from ')'
                                    tree.addRight(generateTree(treeline.substring(i+2,treeline.length()-1),withID));
                                    tree.left().addParent(tree);
                                    tree.right().addParent(tree);
                                    return tree;
                            }//end if
                            if(bcnt==1&&treeline.charAt(i+1)=='_'){     //if close bracket is end of left subtree
                                int c = i+2;
                                while(c<treeline.length()&&isNumber(treeline.charAt(c))){
                                    c++;
                                }
                                if(treeline.charAt(c)==',') {
                                    tree.addLeft(generateTree(treeline.substring(1, c), withID));
                                    //tree.left().setData(treeline.substring(i+2,c));//split on comma - up 1 char from ')'
                                    if(treeline.contains("_")&&isNumber(treeline.charAt(treeline.length()-2))) {
                                        int underIndex = treeline.lastIndexOf('_');
                                        tree.addRight(generateTree(treeline.substring(c + 1, underIndex - 1), withID));
                                        tree.setData(treeline.substring(underIndex+1,treeline.length()));
                                    }else {
                                        tree.addRight(generateTree(treeline.substring(c + 1, treeline.length() - 1), withID));
                                    }
                                    tree.left().addParent(tree);
                                    tree.right().addParent(tree);
                                    return tree;
                                }
                                i=c-1; //compensate for i++ later by decrementing
                            }//end if
                    }
                    else if (treeline.charAt(i)==',') { //comma
                        if (bcnt == 1) {        //if this comma separates outer subtrees
                            tree.addLeft(generateTree(treeline.substring(1, i), withID));            //split on comma
                            if(treeline.contains("_")){
                                int underIndex = treeline.lastIndexOf('_');
                                tree.addRight(generateTree(treeline.substring(i + 1, underIndex-1), withID));
                                int c = underIndex+1;
                                while(c<treeline.length()&&isNumber(treeline.charAt(c))){
                                    c++;
                                }
                                tree.setData(treeline.substring(underIndex+1,c));
                            }else{
                                tree.addRight(generateTree(treeline.substring(i + 1, treeline.length() - 1), withID));
                            }
                            tree.left().addParent(tree);        //assign parent to left and right tree
                            tree.right().addParent(tree);
                            return tree;
                        }//end if
                    }
                    else if(bcnt==0){ //if there are no other elements (only sequence)
                        if(treeline.contains("_")){
                            int underIndex = treeline.indexOf('_');
                            int c = underIndex+1;
                            while(c<treeline.length()&&isNumber(treeline.charAt(c))){
                                c++;
                            }
                            tree.setData(treeline.substring(0,underIndex-1));
                        }else {
                            tree.setData(treeline);
                        }
                            return tree;
                    }
                    i++;
            }//end while
            return tree;
    }
    
    /**
     * Compare input of tree against reference string such as 
     * generated tree of program (converted to string)
     * 
     * @param bt	tree in BinaryTree format
     * @param tl	tree in String format
     */
    public void checkGeneratedTree(BinaryTree<String> bt, String tl){
            String tree = bt.toString();
            String treeWithIDs = bt.toStringWithID();
            if(!tree.equals(tl)&&!treeWithIDs.equals(tl)){
                io.Display("generated tree is not same as input\n");
                throw new UnsupportedOperationException("generated tree is not same as input");

            }

    }

    public boolean isNumber(String strValue){
        try{
            Long.parseLong(strValue);
        }catch(NumberFormatException e1){
            try{
                Integer.parseInt(strValue);
            }catch(NumberFormatException e2){
                return false;
            }
        }
        return true;
    }
    public boolean isNumber(char c){
        return isNumber(String.valueOf(c));
    }

    /**
     * Returns BinaryTree object of sequence from a root tree. 
     * Similar to generateTree, but bases off an existing tree for hierarchy structure. 
     * Static method present in BinaryTree class 
     * 
     * @param root		the BinaryTree which has the sequence
     * @param name		the sequence to be found
     * @return			the sequence is return as a BinaryTree object with full hierarchy structure (parent)
     */
    public BinaryTree<String> findNameInTree(BinaryTree<String> root, String name){
            BinaryTree<String> tree = null;

            if(root!=null){
            	//System.out.println(root.toString() + " | " + name + " - " + root.toString().equals(name));
                    if(root.toString().equals(name)){
                            tree=root;
                            return tree;
                    }
                    else{
                            tree=findNameInTree(root.left(), name);
                            if(tree!=null&&tree.toString().equals(name)){
                                return tree;
                            }
                            else{
                                tree=findNameInTree(root.right(), name);
                                return tree;
                            }
                    }
            }
            return tree;
    }

    /**
     * Reroot a tree around a node of the tree, keeping topology.
     * Static method present in BinaryTree class
     *
     * @param root 	tree which is to be rerooted
     * @param node 	node of tree with which the rerooting will revolve
     * @return 		tree rooted on node
     */
    public BinaryTree<String> reroot(BinaryTree<String> root, BinaryTree<String> node){
    		if(root==null){
    			return new BinaryTree<>("NOT FOUND");
    		}
            if(node==root.right()||node==root.left()||node==root){
                return root;
            }
            boolean con = contains(root, node);
            if(!con){
                io.logError("NOT FOUND: " + node.toString() +" in " + root.toString());
                return new BinaryTree<>("NOT FOUND");
            }
            int initialNUnderscores = countUnderscores(root.toStringWithID());
            List<String> initialAllData = getAllData(root);
            BinaryTree<String> newtree = new BinaryTree<>("");		//new tree root for construction
            BinaryTree<String> s = node; 						//search tree
            BinaryTree<String> prevs = node; 					//previous search tree

            newtree.addLeft(s);
            newtree.addRight(new BinaryTree<>(""));
            BinaryTree<String> t = newtree; //traversal tree

            while(s.parent()!=null){
                    s=s.parent();	//go up a branch
                    t.setData(s.getData());
                    t=t.right();


                if(s.left()==prevs){
                        t.addLeft(s.right());	//swap left and right branches (if left needs changing)
                    }
                    else{
                        t.addLeft(s.left());
                    }
                    t.left().replaceParent(t);

                    if(s.parent()==root){ //get rid of original root
                        if(s.parent().left()==s){
                                t.addRight(s.parent().right());
                                t.right().replaceParent(t);
                        }
                        else{
                                t.addRight(s.parent().left());
                                t.right().replaceParent(t);
                        }
                        t.setData(s.parent().getData());
                        break; //break if parent reached
                    }
                    else{
                        t.addRight(new BinaryTree<>(""));
                        t.right().addParent(t);
                    }
                    prevs=s; //for next iteration
            }//end while loop

            newtree.right().replaceParent(newtree);
            newtree.left().replaceParent(newtree);
            String newString = newtree.toStringWithID();
            // attempt to reduce side-effects, but not completely effective if reroot run multiple times
            // check if empty leafs were added
            List<BinaryTree<String>> iterator = newtree.getFullNodeIterator();
            for (int i = 0, iteratorSize = iterator.size(); i < iteratorSize; i++) {
                BinaryTree<String> binaryTree = iterator.get(i);
                if(binaryTree.getData().equals("")&&binaryTree.left()==null&&binaryTree.right()==null){
                    if(binaryTree.parent().right() == binaryTree){
                        binaryTree.parent().replaceRight(binaryTree.parent().left().right());
                        binaryTree.parent().replaceLeft(binaryTree.parent().left().left());
                    }else{
                        binaryTree.parent().replaceRight(binaryTree.parent().right().right());
                        binaryTree.parent().replaceLeft(binaryTree.parent().right().left());
                    }
                }
            }

            String newString2 = newtree.toStringWithID();
            int newNUnderscores = countUnderscores(newtree.toStringWithID());
            List<String> newAllData = getAllData(newtree);

            if(initialNUnderscores!=newNUnderscores||!initialAllData.equals(newAllData)){
                throw new IndexOutOfBoundsException("rerooting error, loss of data");
            }
            return newtree;
    }

    /**
     * Check if node is contained within root tree.
     *
     * @param root		tree which is rooted
     * @param node		node to be found in root
     * @return			true if root contains node, false otherwise
     */
    public boolean contains(BinaryTree<String> root, BinaryTree<String> node){
            if (root == null)
                    return false;
            if (root == node)
                    return true;
            else{
                    boolean c;
                    c=contains(root.left(), node);
                    if(!c)
                            c = contains(root.right(),node);
                    return c;
            }
    }//end contains

    /**
     * Generates all possible reroots of a tree while keeping the same topology
     *
     * @param btree		initial BinaryTree containing the tree
     * @param names		array of all sequences in the tree
     * @return			a Set is returned that contains all possible reroots
     */
    public Set<String> allReroots(BinaryTree<String> btree, String [] names){
            Set<String> reroots = new LinkedHashSet<>();    //only unique trees, maintain order
            BinaryTree<String> node;
            BinaryTree<String> nodem=new BinaryTree<>("");
            BinaryTree<String> root;
            String bts = btree.toString();					//store binarytree as string
            String no;										//store node as string
            BinaryTree<String> mark = btree.deepClone();	//having a separate tree that keeps track of what has been traversed already makes this super efficient
            BinaryTree<String> original = btree.deepClone();//having a separate tree that keeps track of what has been traversed already makes this super efficient
            int p=1;                                        //progress index
            int underBtree = countUnderscores(btree.toStringWithID());
            int under=0;
            for(String s: names){
                    //node = findNameInTree(btree, s); 			//find node
                    node=btree.find(s);
                    no=node.toString();							//keep node as string in memory
                    bts=btree.toString();						//keep tree as string in memory
                    root=reroot(btree,node);					//reroot tree
                    under = countUnderscores(root.toStringWithID());

                    reroots.add(root.toStringWithID().intern());//add to collection
                    btree=original.deepClone();               	//reset rerooting hierarchies

                    while(node.parent()!=null){
                            node=findNameInTree(btree,no);			//restore hierarchies of stored node (generateTree wouldn't have parent values)
                            nodem=findNameInTree(mark,no);

                            try{
                                // is a number and not a leaf node
                                Long.parseLong(nodem.getData());
                                nodem.setData("X");
                            }catch(NumberFormatException e){

                            }
                            if(nodem.getData().equals("")){			//set node as visited (X)
                            	nodem.setData("X");
                            }

                            node=node.parent();						//change to parent of this
                            nodem=nodem.parent();
                            if(nodem==null||nodem.getData().equals("X")){
                                break;
                            }

                            root=reroot(btree,node);				//reroot tree
                            under = countUnderscores(root.toStringWithID());
                            reroots.add(root.toStringWithID().intern());	//add to collection

                            btree=original.deepClone();				//go to previous tree
                            no=node.toString();                     //update string to parent
                            if(nodem.getData().equals("X")){ 		//ignores parents if already visited
                            	break;
                            }

                    }

                    io.setProg((int)(p*100)/names.length);
                    p++;
            }
            reroots.remove("NOT FOUND");

            return reroots;
    }

    /**
     * Generates the best tree(s) from all the trees and their HYPHY script results.
     * The tree with the lowest AIC value is considered the best root.
     * The consequent nodes have likelihood values generated from their tree's HYPHY SchmodelTest.bf script results.
     * (i.e. the tree with the node sequence as the root has it's value result assigned to the node).
     *
     * @param alignmentName		the name of the alignment (used to output results under correct name)
     * @param trees				all the trees in String format
     * @param originalTree		the orginal BinaryTree that the roots were based off of
     * @param OUT				the results produced by SchmodelTest.bf HYPHY script
     * @return					a List of the best tree(s)
     */
    public List<String> optimalTreeGenerator(String alignmentName, List<String> trees, BinaryTree<String> originalTree, String [] OUT){
    	List<String> finalTrees = new ArrayList<>();
    	/** split OUT **/
        double [][] GTR = new double[OUT.length][2]; //Likelihood, AIC
      	double [][] stGTR = new double[OUT.length][2];
      	double [][] NREV = new double[OUT.length][2];
      	//[i] for tree number
      	//[i][0] for tree's likelihood score
      	//[i][1] for tree's AIC score

      	String findLike = "----\nLog Likelihood = ";
      	int lenLike=findLike.length();
      	String findAIC = "(all parameters): ";
      	int lenAIC=findAIC.length();

      	int ndx = 0, ndx2=0;
      	for(int i=0;i<OUT.length;i++){
      		ndx = OUT[i].indexOf(findLike,ndx2)+lenLike;
          	ndx2=OUT[i].indexOf(";",ndx);
          	GTR[i][0]=Double.parseDouble(OUT[i].substring(ndx, ndx2));

          	ndx = OUT[i].indexOf(findLike,ndx2)+lenLike;
          	ndx2=OUT[i].indexOf(";",ndx);
          	stGTR[i][0]=Double.parseDouble(OUT[i].substring(ndx, ndx2));

          	ndx = OUT[i].indexOf(findLike,ndx2)+lenLike;
          	ndx2=OUT[i].indexOf(";",ndx);
          	NREV[i][0]=Double.parseDouble(OUT[i].substring(ndx, ndx2));

          	ndx=OUT[i].indexOf(findAIC,ndx2)+lenAIC;
          	ndx2 = OUT[i].indexOf(";",ndx);
          	GTR[i][1]=Double.parseDouble(OUT[i].substring(ndx,ndx2));

          	ndx=OUT[i].indexOf(findAIC,ndx2)+lenAIC;
          	ndx2 = OUT[i].indexOf(";",ndx);
          	stGTR[i][1]=Double.parseDouble(OUT[i].substring(ndx,ndx2));

          	ndx=OUT[i].indexOf(findAIC,ndx2)+lenAIC;
          	ndx2 = OUT[i].indexOf(";",ndx);
          	NREV[i][1]=Double.parseDouble(OUT[i].substring(ndx,ndx2));

          	ndx2=0;
      	}

      	/*** SCORING ***/
      double highest=GTR[0][0], lowest=GTR[0][1], value=0.0, like=0.0, aic = 0.0;
      char meth = ' ', methA= ' '; //method
      int n=0,nA=0; //tree number with highest likelihood
      /*** highest likelihood score ***/
      for(int i=0;i<OUT.length;i++){
    	  value = Math.max(Math.max(GTR[i][0], stGTR[i][0]),NREV[i][0]);
    	  if(value>highest){
    		  highest = value;
    		  n=i;
    		  if(value==GTR[i][0]){
    			  meth='G';
    		  }
    		  else if(value==stGTR[i][0]){
    			  meth='S';
    		  }
    		  else{
    			  meth='N';
    		  }
    	  }
      }
      like=highest;

      /*** lowest AIC score ***/
      for(int i=0;i<OUT.length;i++){
    	  value = Math.min(Math.min(GTR[i][1], stGTR[i][1]),NREV[i][1]);
    	  if(value<lowest){
    		  lowest = value;
    		  nA=i;
    		  if(value==GTR[i][1]){
    			  methA='G';
    		  }
    		  else if(value==stGTR[i][1]){
    			  methA='S';
    		  }
    		  else{
    			  methA='N';
    		  }
    	  }
      }
      aic=lowest;

      List<String> valueArray = new ArrayList<>();
      List<Integer> indexArray = new ArrayList<>();
      valueArray.add("Likelihood multiple highest value: ");
      for(int i=0;i<OUT.length;i++){
    	  if(GTR[i][0]==like){
    		  valueArray.add("Index: "+ i + " \tMethod: " + "GTR");
    		  //indexArray.add(i);
    	  }
    	  if(stGTR[i][0]==like){
    		  valueArray.add("Index: "+ i + " \tMethod: " + "stGTR");
    		  //indexArray.add(i);
    	  }
    	  if(NREV[i][0]==like){
    		  valueArray.add("Index: "+ i + " \tMethod: " + "NREV");
    		  //indexArray.add(i);
    	  }
      }
      valueArray.add("AIC duplicate lowest value: ");
      for(int i=0;i<OUT.length;i++){
    	  if(GTR[i][1]==aic){
    		  valueArray.add("Index: "+ i + " \tMethod: " + "GTR");
    		  indexArray.add(i);
    	  }
    	  if(stGTR[i][1]==aic){
    		  valueArray.add("Index: "+ i + " \tMethod: " + "stGTR");
    		  indexArray.add(i);
    	  }
    	  if(NREV[i][1]==aic){
    		  valueArray.add("Index: "+ i + " \tMethod: " + "NREV");
    		  indexArray.add(i);
    	  }
      }
      String likeStr= "Likelihood: \t" + like + "\tmethod: " + meth + "\tindex: " + n;
      io.Log(likeStr);
      String AICStr = "AIC: \t" + aic + "\tmethod: " + methA + "\tindex: " + nA;
      io.Log(AICStr);
      io.Log(valueArray);
      String name = alignmentName.substring(0,alignmentName.indexOf('.'));

      File oldLog = io.getLogFile();
      io.setLogFile(new File(path+"Output"+File.separator+name+File.separator+"Summary.txt"));
      io.Log(likeStr+"\n"+AICStr);
      for(String svalue: valueArray){//output which method was used and duplicate values
    	  io.Log(svalue);
      }


      //use difference to like for branch lengths
      double [] finalLike = new double[OUT.length];
      if(methA=='G'){
    	  for (int i=0;i<finalLike.length;i++){
    		 // finalLike[i]=GTR[i][0];
    		  finalLike[i]=like-NREV[i][0];//want likelihood of NREV anyway
    	  }
      }
      else if(methA=='S'){
    	  for (int i=0;i<finalLike.length;i++){
    		  finalLike[i]=like-stGTR[i][0];
    	  }
      }
      else{
    	  for (int i=0;i<finalLike.length;i++){
        		  finalLike[i]=like-NREV[i][0];
        	  }
      }

      String tree;
      BinaryTree<String> node;
      io.Log("Start assigning likelihood values to tree");
      int prog=1;
      List<BinaryTree<String>> binaryTreeList = new ArrayList<>(trees.size());
        for (String tree1 : trees) {
            binaryTreeList.add(generateTree(tree1));
        }
      for(int index: indexArray){
            io.Log("script output for: " + index + "\n" + OUT[index]);
            if(NREV[index][1]-GTR[index][1]>5)
                io.Log("strong support for rooting");
            else if(NREV[index][1]-GTR[index][1]>2)
                io.Log("moderate support for rooting");
            else
                io.Log("not enough evidence to strongly support rooting");

            tree = trees.get(index);
            node = generateTree(tree);
            checkGeneratedTree(node,tree);
            /*
            * because node is pointer, no return value required
            * originalTree used because rerooted tree may be mirrored instead and not seen as the same
            *
            */


            assignLike(node, originalTree, binaryTreeList, finalLike);
            finalTrees.add(node.toString());
            io.setProg((prog++*100)/indexArray.size());
      }
      io.setLogFile(oldLog);

      return finalTrees;

    }


    /**
     * Likelihood values are assigned to the originalTree.
     *  The original originalTree is used and then a copy rerooted on the node to compare to the list of trees already rerooted.
     *  Original originalTree used as rerooting will be same as before.
     *  Rerooting a different originalTree may produce same topology, but different absolute orientation, so harder to find equal.
     *  The value for the originalTree is then set as the node's value.
     *  Because node is pointer, no return value required.
     * @param node		the node to be assigned a value
     * @param values	array of values corresponding to trees index
     */
    public void assignLike(BinaryTree<String> node, BinaryTree<String> originalTree, List<BinaryTree<String>> binaryTreeList, double[] values){
        List<BinaryTree<String>> nullFinds = new ArrayList<>();
        Map<String,Double> nullFindsStr = new HashMap<>();
        BinaryTree<String> nodeClean = generateTree(node.toString()); // shouldn't be any difference between nodeClean and nodeCleanDeep
        BinaryTree<String> nodeCleanDeep = node.deepClone();

        Map<String,Integer> mapNode = nodeCleanDeep.assignDepth().getLRNDepthIterator();
        Map<String,Integer> mapOrigTree = originalTree.assignDepth().getLRNDepthIterator();


        int nSequences = (getEmptyTree(node.toString().toCharArray()).split(",")).length;

        // assign all values
        for(int i=0;i<binaryTreeList.size();i++){	//run through the list of trees
            boolean extraDepth=true;
            BinaryTree<String> tree = binaryTreeList.get(i);
            // check
            BinaryTree<String> temp = node.find(tree.left().getData());
            if(temp==null||temp.getData()==null){
                temp = node.find(tree.getData());
                extraDepth=false;
            }
            if(temp==null||tree.getData()==null){
                nullFinds.add(tree);
                nullFindsStr.put(tree.toStringWithID(),values[i]);
            }
            if(temp!=null&&temp.getData()!=null){
                if(isNumber(temp.getData()))
                    // current data is unique identifier
                    if(extraDepth) {
                        if (temp.right().right() != null) {
                            if (!isNumber(temp.right().right().getData()))
                                // terminal/leaf
                                temp.right().right().setData(temp.right().right().getData() + ":" + values[i]);
                            else
                                temp.right().right().setData(":" + values[i]);
                        }else {
                            temp.setData(":" + values[i]);
                        }
                    }else {
                        temp.right().setData(":" + values[i]);
                    }
                else
                    // data is just sequence
                    temp.setData(temp.getData() + ":" + values[i]);
            }
            int nNames = (getEmptyTree(node.toString().toCharArray()).split(",")).length;
            if(nSequences!=nNames)
                System.exit(-1);
        }

    }
    
    public static void UPDATE(String next){
    	
    }
    
    /**
     * Allows sequences that are being skipped to not be put last anymore
     * @see TreeIO actionPerformed (e=reset)
     * 
     */
    public static void RESET(){
    	skip=false;
    }
    
    /**
     * Running method.
     *  Alignment files in Input are used to generate a corresponding tree file using FastTree. 
     *  The alignment to be processed is selected from a combobox. 
     *  The alignment then has it's tree rerooted in all possible ways (with same topology).
     *  The rerooted trees are assigned likelihood values using a HYPHY script. 
     *  These likelihood values are used to populate the best tree.
     *  The tree(s) is output to Output under an appropriately named folder. 
     *  Files relating to alignment under Input are deleted. 
     */
    public final void run(){
        try{
            long start_time_total=System.currentTimeMillis();
            long timebefore;
            boolean avail =true; //files available
            io.Log("Start");
            List<String> alignments = new ArrayList<>();
            List<String> skipnames = new ArrayList<>();
            List<String> excludeList = new ArrayList<>();
            if(build){
                path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
                path=path.substring(0,path.lastIndexOf(File.separator)+1);
                }
            else
                path="";
            while(avail){ //allows alignments to be added without restarting program
                io.Log("\n");
                alignments = new ArrayList<>();

                try{
                    alignments = io.listFilesForFolder("phy,fasta,fas",new File(path+"Input"));
                }catch(Exception e){
                    io.logError("listFilesforFolder(phy,fasta,fas): " + e.toString());
                }
                if(alignments.size()<1||alignments.size()==excludeList.size()){
                    avail=false;
                    break;
                }
                String tree="";
                List<Map<String,String>> replacements = new ArrayList<>();
                File treeFile;
                String nameTemp;
                String returnShellVal="";
                for(String alignment: alignments){
                        if(excludeList.contains(alignment)){
                            continue;
                        }
                        if(alignment.endsWith("phy")){
                            tree = alignment.substring(0,alignment.length()-3)+"nwk";
                        }
                        else if(alignment.endsWith("fasta")){
                                tree = alignment.substring(0,alignment.length()-5)+"nwk";
                        }
                        else if(alignment.endsWith("fas")){
                                tree = alignment.substring(0,alignment.length()-3)+"nwk";
                        }
                        else{
                                io.Log("check: " + alignment +". Attempting anyway");
                                tree = alignment.substring(0,alignment.lastIndexOf("."))+".nwk";
                        }
                        treeFile = new File(path+"Input"+File.separator+tree);
                        nameTemp = alignment.substring(0,alignment.indexOf('.'));
                        io.setLogFile(new File(path+"Output"+File.separator+nameTemp+File.separator+"Details.txt"));
                        if(!treeFile.exists()||io.isEmpty(treeFile)){
                            replacements.add(io.convertNames(alignment,"\n"));	//relatively quick (<1s) to convert to linux format
                            timebefore = System.currentTimeMillis();
                            io.Log("FastTree for " + alignment);
                            returnShellVal = io.FastTree(alignment,tree); //FastTree implementation
                            io.Log("done in "+ String.valueOf((System.currentTimeMillis()-timebefore)/1000) + " seconds" );
                        }
                        else{
                                Map<String,String> temp = io.getNameCodes(alignment); //need to have different method otherwise will convert already converted sequence names
                                replacements.add(temp);
                        }
                        if(io.isEmpty(treeFile)){
                                io.Log("FastTree FAILED for " + alignment);
                                io.Log("ERROR: \n" + returnShellVal);
                                io.Log("For more info, try FastTree -nt -gtr -nosupport -out " + tree + " " + alignment);
                                excludeList.add(alignment);
                        }
                }
                List<String> files = new ArrayList<>();
                try{
                    // Start by inputting tree file from Input folder
                    files = io.listFilesForFolder("nwk",new File(path+"Input"));
                }catch(Exception e){
                    io.logError("listFilesforFolder (nwk): " + e.toString());
                }

                String currentAlignment="";

                if(io.getNextAlignment().equals("")){
                    io.populateAlignmentList(files);
                    io.setNextAlignment();
                    currentAlignment=io.getNextAlignment();
                    files.remove(currentAlignment);
                    io.populateAlignmentList(files);
                }
                else{
                    io.setNextAlignment();
                    currentAlignment=io.getNextAlignment();
                    files.remove(currentAlignment);
                    if(skip){
                        for(int i=0;i<skipnames.size();i++){ //put any names that were skipped on purpose to the end of the list (to be done last)
                            String skippedTree=skipnames.get(i);
                            if(files.contains(skippedTree)){
                                files.remove(skippedTree);
                                files.add(skippedTree);
                            }//end if
                        }//end for
                    }//end if
                    else{
                        skipnames.clear();
                    }
                    io.populateAlignmentList(files);
                }//end else


                String name = currentAlignment.substring(0,currentAlignment.indexOf('.'));
                io.setLogFile(new File(path+"Output"+File.separator+name+File.separator+"Details.txt"));

                io.Log(currentAlignment);



                String treeline = io.getTreeLine(currentAlignment);

                io.Log("retrieved from file");

                //int treelength = treeline.length();

                treeline = getNameTree(treeline.toCharArray());
                io.Log("numbers removed");

                treeline = convertToBinary(treeline,0);
                io.Log("converted to binary");

                //array of items in the tree
                String [] names = getEmptyTree(treeline.toCharArray()).split(","); //have all the sequences in an array
                if(names.length<=1){
                        io.Log("Not enough elements in tree");
                        io.logError("Not enough elements in tree for " + name);
                        continue;
                }
                io.Log("sequences extracted: " + String.valueOf(names.length));



                BinaryTree<String> btree = generateTree(treeline,true);
                io.Log("tree generated");
                checkGeneratedTree(btree,treeline);
                io.Log("and checked");

                List<String> reroots = new ArrayList<>();
                io.setProg(0);
                File rootFile = new File(path+"Input" +File.separator+currentAlignment + "_REROOTS.txt");
                //if(rootFile.exists()){
                //    reroots = io.getRoots(rootFile); //for testing
                //}
                //else{
                    io.Log("Start Rerooting");
                    long r_time = System.currentTimeMillis();
                    Set<String> rerootset = allReroots(btree, names);
                    double r_time_total=(System.currentTimeMillis()-r_time)/1000;
                    io.Log("Rerooting done in " + r_time_total + " seconds");
                    reroots.addAll(rerootset);
                    io.outputTrees(reroots,currentAlignment+"_REROOTS"); //all reroots one file
                    io.outputRerootedTrees(reroots,name);
               // }

                io.Log("rerootings: " + reroots.size()+" expected: " + (names.length*2-3));

                long h_time  = System.currentTimeMillis();

                String alignmentString = "";
                int r=0; //replacement index (in sync with alignment but not files)
                for(String alignment: alignments){ //quick enough to check
                    if(alignment.substring(0,alignment.lastIndexOf(".")).equals(name)){
                        alignmentString=alignment;
                        break;
                    }
                    r++;
                }

                if(alignmentString.equals("")){
                    io.Log("error for " + currentAlignment);
                    continue;
                }

                io.Log("Start HYPHY");
                String [] OUT = io.HyphyResults(reroots, alignmentString);	//single
                long h_time_total=(System.currentTimeMillis()-h_time)/1000;
                io.Log("HYPHY done in " + h_time_total + " seconds");

                if(OUT[0]==null){
                    io.Log("error?");
                    excludeList.add(currentAlignment);
                    skip=true;
                    continue;
                }
                else if(OUT[0].contains("Error")){
                    io.Log("Alignment " + currentAlignment + " has an error from HYPHY script: \n" + OUT[0]);
                    for(String s:OUT){
                            if(!s.contains("Error")){
                               io.Log("not every OUT index has error");
                               break;
                            }
                    }
                    continue;
                }
                else if(OUT[0].equals("")||OUT[OUT.length-1]==null){
                    io.Log("Alignment skipped");
                    excludeList.add(currentAlignment);
                    skip=true;
                    continue;
                }else if(OUT[0].equals("break")){
                    break;
                }else{
                    io.Log("no obvious errors in OUT. Length: " + OUT.length);
                }

                List<String> finalTree = optimalTreeGenerator(currentAlignment,reroots, btree, OUT);
                List<String> keyList = new ArrayList<>();
                keyList.addAll(replacements.get(r).keySet());
                String treeReplace="";
                for(int f=0;f<finalTree.size();f++){
                    treeReplace=finalTree.get(f);
                    for(int k=0;k<keyList.size();k++){
                        treeReplace=treeReplace.replace(keyList.get(k), replacements.get(r).get(keyList.get(k)));
                    }
                    //finalTree.remove(f);
                    //finalTree.add(treeReplace); //alternative instead of set
                    finalTree.set(f,treeReplace);
                }

                io.outputIndividualTrees(finalTree, name);
                io.Log("DONE! See Output folder for tree(s)");

                try { //restore original file only when done with it
                    File bakFile = new File(path+"Input"+File.separator+alignments.get(r)+".bak");
                    File codeFile = new File(path+"Input"+File.separator+alignments.get(r)+"_CODES.txt");
                    File alignmentFileClean = new File(path+"Input"+File.separator+alignments.get(r)+"_CLEAN");
                    File alignmentFile = new File(path+"Input"+File.separator+alignments.get(r));
                    File treeFileDel = new File(path+"Input"+File.separator+currentAlignment);

                    Files.copy(bakFile.toPath(),new File(path+"Output"+File.separator+name+File.separator+alignments.get(r)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    rootFile.delete();
                    bakFile.delete();
                    codeFile.delete();
                    alignmentFileClean.delete();
                    alignmentFile.delete();
                    treeFileDel.delete();
                } catch (IOException e) {
                        io.logError("File deletion: " + e.toString());
                } //make backup
                io.setLogFile(null);
            }//end while


            double time_total=(System.currentTimeMillis()-start_time_total)/1000;
            io.Log("Completed in " + time_total + " seconds");
        }catch(Exception e){
            io.Display("Unforeseen error in run, program has been halted: \n" + e.getStackTrace().toString());
            e.printStackTrace();
            System.exit(-1);
        }
    }//#####################################################################
}//end class
