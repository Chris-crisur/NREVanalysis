
import java.util.LinkedList;
import java.util.List;
public class BinaryTree<T> implements Cloneable {
    private T data;
    private List<T> iterator;
    //private String striterator;
    private BinaryTree<T> pt, //pointer to parent
                                            lt, // Pointer to left subtree
                                    rt; // Pointer to right subtree
    // ------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------
    // Constructor: creates new node with parent and left and right subtrees 
            public BinaryTree (T value, BinaryTree<T> parent, BinaryTree<T> left, BinaryTree<T> right){
                            pt=parent;
                            lt = left;
                            rt = right;
                            data = value;
                            } // Constructor

            // Constructor: creates new node with left and right subtrees
            public BinaryTree (T value, BinaryTree<T> left, BinaryTree<T> right){
                    this(value, null, left, right);
                    } // Constructor

            // Constructor: creates new node
            public BinaryTree (T value){
                    this(value, null, null, null);
            } // Constructor
            public BinaryTree (){
                    this(null,null,null,null);
            }
   // ------------------------------------------------------------------
   // static methods
   // ------------------------------------------------------------------

            //Converts a tree in String format to BinaryTree format
            public static BinaryTree<String> generateTree(String treeline){
                    BinaryTree<String> tree = new BinaryTree<>("");
                    int i=0; //index
                    int bcnt=0; //bracket count

                    if(treeline.charAt(treeline.length()-1)==';'){ //remove ; of initial tree
                            treeline=treeline.substring(0,treeline.length()-1);
                    }

                    while(i<treeline.length()){ 
                            if(treeline.charAt(i)=='('){ //open bracket
                                    bcnt++;
                            }
                            else if(treeline.charAt(i)==')'){ //close bracket
                                    bcnt--;
                                    if(bcnt==1&&treeline.charAt(i+1)==','){ //if close bracket is end of left subtree
                                            tree.addLeft(generateTree(treeline.substring(1,i+1)));	//split on comma - up 1 char from ')'
                                            tree.addRight(generateTree(treeline.substring(i+2,treeline.length()-1)));
                                            tree.left().addParent(tree);
                                            tree.right().addParent(tree);
                                            return tree;
                                    }//end if
                            }
                            else if (treeline.charAt(i)==','){ //comma
                                    if(bcnt==1){		//if this comma separates outer subtrees
                                            tree.addLeft(generateTree(treeline.substring(1,i)));			//split on comma
                                            tree.addRight(generateTree(treeline.substring(i+1,treeline.length()-1)));
                                            tree.left().addParent(tree);		//assign parent to left and right tree
                                            tree.right().addParent(tree);
                                            return tree;
                                    }//end if
                            }
                            else if(bcnt==0){ //if there are no other elements (only sequence)
                                    tree.setData(treeline); 
                                    return tree;
                            }
                            i++;
                    }//end while
                    return tree;
            }
            
            //Similar to generateTree, but bases off an existing tree for hierarchy structure
            public static BinaryTree<String> findNameInTree(BinaryTree<String> root, String name){
                BinaryTree<String> tree = new BinaryTree<>("");
                
                if(root!=null){
                        if(root.toString().equals(name)){
                                tree=root;
                                return tree;
                        }
                        else{
                                tree=findNameInTree(root.left(), name);
                                if(tree.toString().equals(name)){
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
            
            //Reroot a tree around a node of the tree, keeping topology
            public static BinaryTree<String> reroot(BinaryTree<String> root, BinaryTree<String> node){

                    boolean con = contains(root, node);
                    if(!con){
                    	return new BinaryTree<>("NOT FOUND");
                    }
                    if(node==root.right()||node==root.left()||node==root){
                        return root;
                    }

                    BinaryTree<String> newtree = new BinaryTree<>("");		//new tree root for construction
                    BinaryTree<String> s = node; 						//search tree
                    BinaryTree<String> prevs = node; 					//previous search tree	

                    newtree.addLeft(s);
                    newtree.addRight(new BinaryTree<>(""));
                    BinaryTree<String> t = newtree; //traversal tree

                    while(s.parent()!=null){
                            s=s.parent();	//go up a branch
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

                    return newtree;
            }

            //Check if node is contained within root tree. 
            public static boolean contains(BinaryTree<String> root, BinaryTree<String> node){
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
            
    // ------------------------------------------------------------------
    // private methods
    // ------------------------------------------------------------------

    // post-order traversal of tree taking in the nodes' data and accompanying symbols
                    private void LRNIterator(BinaryTree<T> root, T opensym, T closesym, T sepsym){ 
                            if(root!=null){
                                    if(root.getData()==""){
                                            iterator.add(opensym);
                                            LRNIterator(root.left(), opensym, closesym, sepsym);
                                            iterator.add(sepsym);
                                            LRNIterator(root.right(), opensym, closesym, sepsym);
                                            iterator.add(closesym);
                                    }
                                    else{				
                                            iterator.add(root.getData());
                                    }
                            }
                    } // LRNIterator

            // post-order traversal of tree taking in the nodes' data	
            @SuppressWarnings("unused") //got irritated by eclipse saying it wasn't used locally
            private void LRNIterator(BinaryTree<T> root){ 
                    if (root != null){ 
                            LRNIterator(root.left());
                            LRNIterator(root.right());
                            iterator.add(root.getData());	
                    }
            } // LRNIterator

            //string iterator with defined symbols and used for toString()
            private String stringLRNIterator(BinaryTree<T> root){ 
                    String strit="";
                    if(root!=null){
                            if(root.getData()==""||root.getData()=="X"){
                                    strit+="(";
                                    strit+=stringLRNIterator(root.left());
                                    strit+=",";
                                    strit+=stringLRNIterator(root.right());
                                    strit+=")";
                                    return strit;
                            }
                            else if(root.getData().toString().charAt(0)==':'){
                            	strit+="(";
                                strit+=stringLRNIterator(root.left());
                                strit+=",";
                                strit+=stringLRNIterator(root.right());
                                strit+=")";
                                strit+=(String)root.getData();
                                return strit;
                            }
                            else{				
                                    strit+=(String)root.getData();
                                    return strit;
                            }
                    }
                    else{
                            return strit;
                    }

            } // LRNIterator

    // ------------------------------------------------------------------
    // public methods
    // ------------------------------------------------------------------
            
    //superficial copy
    public BinaryTree<T> copy(){
            return new BinaryTree<T>(data, pt, lt, rt);
    }

    //untested
    public BinaryTree<T> deepCopy(){
            BinaryTree<T> p = pt.copy();
            BinaryTree<T> l = new BinaryTree<>();
            BinaryTree<T> r = new BinaryTree<>();
            BinaryTree<T> bt = new BinaryTree<T>(data);
            if(l!=null){
                    l = lt.copy();
                    bt.addLeft(l.deepCopy());
            }
            if(r!=null){
                    r = rt.copy();
                    bt.addRight(r.deepCopy());
            }
            bt.addParent(p);

            return bt;
    }

    // Return the parent of a tree
    public BinaryTree<T> parent() {
            return pt;
    }

    // Return the left subtree of a tree
    public BinaryTree<T> left() {
            return lt; 
    }

    // Return the right subtree of a tree
    public BinaryTree<T> right() {
            return rt; 
    }

    // Add parent of node
    public void addParent (BinaryTree<T> parent){	
            if (pt != null)
                    throw new UnsupportedOperationException("subtree already present");
            pt = parent;
    } // addLeft

    // Add a left subtree to a node
    public void addLeft (BinaryTree<T> left){	
            if (lt != null)
                    throw new UnsupportedOperationException("subtree already present");
            lt = left;
    } // addLeft

    // Add a right subtree to a node
    public void addRight (BinaryTree<T> right){
            if (rt != null)
                    throw new UnsupportedOperationException("subtree already present");
            rt = right;
    } // addRight

    // replace parent
    public void replaceParent (BinaryTree<T> parent){	
            pt = parent;
    } // addLeft


    // Replace a left subtree
    public void replaceLeft(BinaryTree<T> left){
            lt=left;
    }

    //Replace a right subtree
    public void replaceRight(BinaryTree<T> right){
            rt=right;
    }

    // Access the data value in the root node of a tree
    public T getData (){
    return data; 
    }

    //Set the data
    public void setData(T value){
            data=value;
    }

    //generate an iterator of left-right-node traversal (with desginated symbols)
    public List<T> getLRNIterator(BinaryTree<T> root, T opensym, T closesym, T sepsym){
            iterator = new LinkedList<>();
            LRNIterator(root, opensym, closesym, sepsym);
            return iterator;
    }

    //get number of elements in tree
    public int getSize(BinaryTree<T> root){
            int cnt= sizeTree(root);
            cnt=(cnt+1)/2; //take into account "" parents
            return cnt;
    }

    public int sizeTree(BinaryTree<T> root){
            int cnt=0;
            if(root!=null){
                    cnt=1+sizeTree(root.left())+sizeTree(root.right());
            }
            return cnt;
    }

    //return tree in String format
    //uses string iterator
    public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(stringLRNIterator(this));
            return builder.toString();
    }

    //attempt to clone tree (should be typecasted when used)
    protected Object clone() throws CloneNotSupportedException {
    return super.clone();
    }
	
} // class BinaryTree
