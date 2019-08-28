import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

/**Forms a graph that represents the social network being created
 * @author Bridget
 * @version June 2019
 */
public class Graph {
	
	/**List of all the nodes in this graph.*/
    protected ArrayList<Node> nodeList = new ArrayList<>();
	
	/**Empty Constructor*/
	public Graph() {		
	}
	
    /**Creates a node and adds it to the nodeList
     * @param name - person name
	 * @param dob - person's date of birth
	 * @param suburb - suburb where person lives
     * @return the added node object
     */
    public Node addNode(String name, LocalDate dob, String suburb) {
        Node v = new Node(name, dob, suburb);
        if(!nodeList.contains(v)) {
        	nodeList.add(v);
        	return v;
        }
        return null;
    }
    
    /**Add a new edge to a graph between two nodes (bi-directional when
     * adding).
     * @param from vertex the edge runs from
     * @param to vertex the edge runs to
     * @throws Exception if node cannot be found 
     */
    public void addEdge(Node from, Node to) throws Exception{
    	if (nodeList.contains(from) && nodeList.contains(to)) {
    		/**Creates the two edges between both nodes*/
    		Edge linkTo = new Edge (to);
    		Edge linkFrom = new Edge(from);
    		
    		/**Links the two nodes with an edge*/
    		if (!from.adj.containsKey(to))			
    			from.adj.put(to, linkFrom);  // from ---> to        	

    		if (!to.adj.containsKey(from)) 
    			to.adj.put(from, linkTo);  // from ---> to

    	}else {
    		System.out.println("Nodes do not exist");
    		throw new Exception();
    	}
    }
    
    /**Remove an edge to a graph between two nodes
     * @param from - vertex the edge runs from
     * @param to - vertex the edge runs to
     */
    public void removeEdge(Node from, Node to) {    		
        	from.adj.remove(to);
        	to.adj.remove(from);
    }

    /**Removes any existence of the node object
     * @param node - the node to be removed
     * @throws Exception
     */
    public void removeNode(Node node) throws Exception {
    	if (nodeList.contains(node)) {
    		/**Iterate through entire links with Node and remove*/
    		Iterator<Entry<Node, Edge>> it  = 
    				node.adj.entrySet().iterator();     			
    		while (it.hasNext()) { 
    			/**an entry of the node that is mapped by 
    			 * the iterator position*/
    			Entry<Node, Edge> mapElement = 
    					(Entry<Node, Edge>) it.next(); 
    			Node dest = (Node) mapElement.getKey();
    			/**Remove the edges between nodes (any reference to node)*/
    			removeEdge(dest, node);
    		} 
    		nodeList.remove(node);
    	}else {
    		System.out.println("Node does not exist");
    		throw new Exception();
    	}
    }
    
    
    /**A set of all the neighbours keys of a node
     * @param node
     * @return a set of keys using the keySet method in HashMap
     */
    public Set<Node> getNeighbors(Node node){
    	return node.adj.keySet(); 	
    }

	@Override
	/**return the attributes of the Graph object as a string*/
	public String toString() {
		return "Graph [nodeList=" + nodeList + "]";
	}    
    
}
