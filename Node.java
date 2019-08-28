import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**Node class creates the representations of each person as a Node
 * @author Bridget
 * @version June 2019
 */
public class Node implements Comparable<Node> {
	/**defines the attributes for the class and each person*/
	private String name;
	private LocalDate dateOB;
	private String suburb;
	protected HashMap<Node, Edge> adj = new HashMap<Node, Edge>();
	
	/**stores today's date for the daystilBirthday method*/
	LocalDate today = LocalDate.now();
	
	/**Class constuctor - creates a new node
	 * @param name - person name
	 * @param localDate - person's date of birth
	 * @param suburb - suburb where person lives
	 */
	public Node(String name, LocalDate localDate, String suburb) {
		this.name = name;
		dateOB = localDate;
		this.suburb = suburb;		
	}
	
	/**sets the name for the node object
	 * @param name - person's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**sets the date for the node object
	 * @param date - person's date of birth
	 */
	public void setDate(String date) {
		dateOB = LocalDate.parse(date);
	}
	
	/**sets the suburb for the node object
	 * @param suburb - suburb person lives in
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	
	/**adds to the hashmap adj
	 * @param n - Node being linked to edge
	 * @param e - Edge between two nodes
	 */
	public void setAdj(Node n, Edge e) {
		adj.put(n, e);
	}
	
	/**returns name of node object
	 * @return person's name
	 */
	public String getName() {
		return name;
	}
	
	/**returns suburb of node object
	 * @return - person's suburb
	 */
	public String getSuburb() {
		return suburb;
	}
	
	/**returns date of node object
	 * @return - person's date of birth
	 */
	public LocalDate getDateOB() {
		return dateOB;
	}
	
	/**returns the hashmap of nodes and edges
	 * @return links between nodes and edges
	 */
	public HashMap<Node, Edge> getAdj(){
		return adj;
	}
	
	@Override
	/**return the attributes of the node object as a string */
	public String toString() {
		return "Node [name=" + name + ", dateOB=" + dateOB + ", suburb=" + suburb + "]";
	}
	
	@Override
	/**override the equals method to check is nodes are the same */
	public boolean equals(Object o) {		
		if (this == o) {
			return true;
		}
		if (o == null || o.getClass()!=this.getClass()) {
			return false;
		}
		Node node = (Node) o;
		return (node.name.equals(this.name)  && node.dateOB == this.dateOB && node.suburb.equals(this.suburb));	
	}
	
	/**Changes the name variable to an integer of corresponding ascii values
	 * @return a long version of the name
	 */
	public long nameChange() {		
		String concat = "";
		long result = 0;
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			int ascii = (int) ch;
			concat = concat + Integer.toString(ascii);
		}
		/** 
		 * The below if statement ensures that a very long name is still dealt with
		 * as a long variable (cuts the string at position 9).
		 */
		if (concat.length() >= 8) {
			result = Long.parseLong(concat.substring(0, 8));
		}else {
			result = Long.parseLong(concat);	
		}
		return result;
	}
	
	/**Picks three characters within the date
	 * Changes these characters to its corresponding ascii values
	 * @return an integer of the characters added together
	 */
	public int dateChange() {
		String date = dateOB.toString();
		int result = (int) date.charAt(2) + (int) date.charAt(4) + (int) date.charAt(5);
		return result;
	}
	
	/**Changes the suburb variable to an integer 
	 * of corresponding ascii values (similar to nameChange())
	 * @return a long version of the suburb
	 */
	public long suburbChange() {
		String concat = "";
		long result = 0;
		
		for (int i = 0; i < suburb.length(); i++) {
			int ascii = (int) suburb.charAt(i);
			concat = concat + Integer.toString(ascii);
		}
		if (concat.length() >= 8) {
			result = Long.parseLong(concat.substring(0, 8));
		}else {
			result = Long.parseLong(concat);	
		}
		return result;
	}
	
	@Override
	/**produces a unique hash for the Node object*/
	public int hashCode() {
		int result = Math.abs(((int)nameChange() + (int)suburbChange() + dateChange()) % 1301);
		return result;
	}
	
	/**Finds the number of days between today and a birth date
	 * @param n - Node with localDate that is being checked
	 * @return integer of number of days
	 */
	public long daystilBirthday(Node n) {
		LocalDate timeSpan = n.dateOB.withYear(today.getYear());
		/**changes the year to next year if required*/
		if (timeSpan.isBefore(today) || timeSpan.isEqual(today)) {
			timeSpan = timeSpan.plusYears(1);
		}
		return ChronoUnit.DAYS.between(today, timeSpan);	
	}

	@Override
	/**compares two nodes based off the days til their month and day
	 * uses the daystilBirthday method*/
	public int compareTo(Node o) {
		int num1 = (int) daystilBirthday(o);
		int num2 = (int) daystilBirthday(this);
		return Integer.compare(num2,num1);
	}

}
