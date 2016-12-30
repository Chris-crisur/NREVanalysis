
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BinaryTree<T> implements Cloneable,Serializable {
    private T data;
    private int depth;
    private List<T> iterator;
    private BinaryTree<T> pt, //pointer to parent
                          lt, // Pointer to left subtree
                          rt; // Pointer to right subtree
    // ------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------
    // Constructor: creates new node with parent and left and right subtrees
            public BinaryTree (T value, int depth, BinaryTree<T> parent, BinaryTree<T> left, BinaryTree<T> right){
                            pt=parent;
                            lt = left;
                            rt = right;
                            data = value;
                            this.depth=depth;
                            } // Constructor

            // Constructor: creates new node with left and right subtrees
            public BinaryTree (T value, BinaryTree<T> left, BinaryTree<T> right){
                    this(value, 0, null, left, right);
                    assignDepth(this,0);
                    } // Constructor

            // Constructor: creates new node
            //public BinaryTree (T value){
            //        this(value, null, null, null);
            //} // Constructor
            public BinaryTree (){
                    this(null,0,null,null,null);
            }
            //Converts a tree in String format to BinaryTree format

    /**
     * TODO: fix (does not create tree correctly)
     * @param tree
     */
    public BinaryTree(T tree){
            this(tree,0,null,null,null);
            String treeline = (String)tree;
            if(!treeline.equals("")) {
                int i = 0; //index
                int bcnt = 0; //bracket count

                if (treeline.charAt(treeline.length() - 1) == ';') { //remove ; of initial tree
                    treeline = treeline.substring(0, treeline.length() - 1);
                }

                while (i < treeline.length()) {
                    if (treeline.charAt(i) == '(') { //open bracket
                        bcnt++;
                    } else if (treeline.charAt(i) == ')') { //close bracket
                        bcnt--;
                        if (bcnt == 1 && treeline.charAt(i + 1) == ',') { //if close bracket is end of left subtree
                            this.addLeft(new BinaryTree<T>((T) treeline.substring(1, i + 1)));    //split on comma - up 1 char from ')'
                            this.addRight(new BinaryTree<T>((T) treeline.substring(i + 2, treeline.length() - 1)));
                            this.left().addParent(this);
                            this.right().addParent(this);
                        }//end if
                    } else if (treeline.charAt(i) == ',') { //comma
                        if (bcnt == 1) {        //if this comma separates outer subtrees
                            this.addLeft(new BinaryTree<T>((T) treeline.substring(1, i)));            //split on comma
                            this.addRight(new BinaryTree<T>((T) treeline.substring(i + 1, treeline.length() - 1)));
                            this.left().addParent(this);        //assign parent to left and right tree
                            this.right().addParent(this);
                        }//end if
                    } else if (bcnt == 0) { //if there are no other elements (only sequence)
                        this.data = (T) treeline;
                    }
                    i++;
                }//end while
            }
        assignDepth(this,0);
    }

    private BinaryTree<T> generateTree(String treeline){
        BinaryTree<T> tree = new BinaryTree<>();
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
                tree.setData((T)treeline);
                return tree;
            }
            i++;
        }//end while
        return tree;
    }

            public BinaryTree<T> find(String name){
                return find(this,name);
            }
            private BinaryTree<T> find(BinaryTree<T> root, String name){
                BinaryTree<T> tree = new BinaryTree<>();
                
                if(root!=null){
                        if(root.toString().equals(name)
                                ||root.toStringWithID().equals(name)
                                ||root.getData().equals(name)){
                                return root;
                        }
                        else{
                                tree=find(root.left(), name);
                                if(tree!=null){
                                    return tree;
                                }
                                else{
                                    tree=find(root.right(), name);
                                    return tree;
                                }
                        }
                }
                return root;
        }
            
            //Reroot a tree around a node of the tree, keeping topology
            public BinaryTree<T> reroot(BinaryTree<T> node){
                BinaryTree<T> rerootedTree = reroot(this,node);
                assignDepth(rerootedTree,0);
                return rerootedTree;
            }
            private BinaryTree<T> reroot(BinaryTree<T> root, BinaryTree<T> node){

                    if(node==root.right()||node==root.left()||node==root){
                        return root;
                    }
                    boolean con = contains(root, node);
                    if(!con){
                        throw new IndexOutOfBoundsException("NOT FOUND");
                    }

                    BinaryTree<T> newtree = new BinaryTree<T>();		//new tree root for construction
                    BinaryTree<T> s = node; 						//search tree
                    BinaryTree<T> prevs = node; 					//previous search tree

                    newtree.addLeft(s);
                    newtree.addRight(new BinaryTree<T>());
                    BinaryTree<T> t = newtree; //traversal tree

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
                                t.addRight(new BinaryTree<>());
                                t.right().addParent(t);
                            }
                            prevs=s; //for next iteration
                    }//end while loop

                    newtree.right().replaceParent(newtree);
                    newtree.left().replaceParent(newtree);

                    return newtree;
            }

            //Check if node is contained within root tree. 
            public boolean contains(BinaryTree<T> root, BinaryTree<T> node){
                if (root == null)
                    return false;
                if (root == node)
                    return true;
                else{
                    boolean c = contains(root.left(), node);
                    if(!c)
                            c = contains(root.right(),node);
                    return c;
                }
            }//end contains

    /**
     *
     * @return root, for chaining of commands
     */
    public BinaryTree<T> assignDepth(){
        assignDepth(this,0);
        return this;
    }

    // ------------------------------------------------------------------
    // private methods
    // ------------------------------------------------------------------

    private void assignDepth(BinaryTree<T> root, int depth) {
        if (root != null) {
            root.depth = depth;
            assignDepth(root.left(), depth + 1);
            assignDepth(root.right(), depth + 1);
        }
    }//end contains

    // post-order traversal of tree taking in the nodes' data and accompanying symbols
        private void LRNIterator(BinaryTree<T> root, T opensym, T closesym, T sepsym){
                if(root!=null){
                        if(root.getData()==""){
                                LRNIterator(root.left(), opensym, closesym, sepsym);
                                LRNIterator(root.right(), opensym, closesym, sepsym);
                        }
                        else{
                                iterator.add(root.getData());
                        }
                }
        } // LRNIterator

        // post-order traversal of tree taking in the nodes' data
        private void LRNIterator(BinaryTree<T> root){
            if (root != null){
                LRNIterator(root.left());
                LRNIterator(root.right());
                iterator.add(root.getData());
            }
        } // LRNIterator
        private void LRNFullNodeStringIterator(BinaryTree<T> root, List<String> iterator){
            if (root != null){
                LRNFullNodeStringIterator(root.left(),iterator);
                LRNFullNodeStringIterator(root.right(),iterator);
                iterator.add(root.toString());
            }
        }
        private void LRNFullNodeIterator(BinaryTree<T> root, List<BinaryTree<T>> iterator){
            if (root != null){
                LRNFullNodeIterator(root.left(),iterator);
                LRNFullNodeIterator(root.right(),iterator);
                iterator.add(root);
            }
        }
        private void LRNDepthIterator(BinaryTree<T> root, Map<T,Integer> iterator){
            if (root != null){
                LRNDepthIterator(root.left(),iterator);
                LRNDepthIterator(root.right(),iterator);
                iterator.put(root.getData(),root.depth);
            }
        }
        public boolean isNumber(T value){
            String strValue = (String) value;
            try{
                Long.parseLong(strValue);
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }
        //string iterator with defined symbols and used for toString()
        private String stringLRNIterator(BinaryTree<T> root, boolean withID){
                String strit="";
                if(root!=null){
                        if(root.getData()==""||root.getData()=="X"||isNumber(root.getData())){
                                strit+="(";
                                strit+=stringLRNIterator(root.left(),withID);
                                strit+=",";
                                strit+=stringLRNIterator(root.right(),withID);
                                strit+=")";
                                if(withID&&isNumber(root.getData()))
                                    strit+="_"+(String)root.getData();
                                return strit;
                        }
                        else if(root.getData()!=null&&root.getData().toString().charAt(0)==':'){
                            strit+="(";
                            strit+=stringLRNIterator(root.left(),withID);
                            strit+=",";
                            strit+=stringLRNIterator(root.right(),withID);
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

    //generate an iterator of nodes using left-right-node traversal (with designated symbols)
    public List<T> getLRNIterator(T opensym, T closesym, T sepsym){
            iterator = new LinkedList<>();
            LRNIterator(this, opensym, closesym, sepsym);
            return iterator;
    }
    //generate an iterator of nodes using left-right-node traversal (without designated symbols)
    public List<T> getLRNIterator(){
            iterator = new LinkedList<>();
            LRNIterator(this);
            return iterator;
    }
    //generate an iterator of sub-trees using left-right-node traversal (without designated symbols)
    public List<BinaryTree<T>> getFullNodeIterator(){
        List<BinaryTree<T>> iterator = new LinkedList<>();
        LRNFullNodeIterator(this,iterator);
        return iterator;
    }
    //generate an iterator of depths using left-right-node traversal (without designated symbols)
    public Map<T,Integer> getLRNDepthIterator(){
        Map<T,Integer> iterator = new HashMap<>();
        LRNDepthIterator(this,iterator);
        return iterator;
    }

    //get number of elements in tree
    public int getSize(BinaryTree<T> root){
            int cnt= sizeTree(root);
            cnt=(cnt+1)/2; //take into account "" parents
            return cnt;
    }

    public int size(){
        return sizeTree(this);
    }

    private int sizeTree(BinaryTree<T> root){
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
            builder.append(stringLRNIterator(this,false));
            return builder.toString();
    }
    public String toStringWithID() {
        StringBuilder builder = new StringBuilder();
        builder.append(stringLRNIterator(this,true));
        return builder.toString();
    }

    protected BinaryTree<T> clone() throws CloneNotSupportedException {
        return (BinaryTree<T>) super.clone();
    }

    protected BinaryTree<T> deepClone(){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (BinaryTree<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
	
} // class BinaryTree
