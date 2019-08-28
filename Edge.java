/**Edge class creates a new edge with the supplied destination as a Node
 * @author Bridget
 * @version June 2019
 */
public class Edge {
	
	/**the destination node attribute*/
	private Node dest;

	/**Constructor - stores the destination in the edge
	 * @param dest the destinatino node
	 */
	public Edge(Node dest) {
		this.setDest(dest);
	}

	/**Set the destinatino
	 * @param dest destination node
	 */
	public void setDest(Node dest) {
		this.dest = dest;
	}
	
	/**Get the destination
	 * @return destination of type Node
	 */
	public Node getDest() {
		return dest;
	}

	@Override
	/**return the attributes of the edge object as a string*/
	public String toString() {
		return "Edge [dest=" + dest + "]";
	}
	
}
