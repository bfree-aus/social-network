import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**Reads the file data.txt, constructs the graph, and 
 * then conducts certain social media operations (eg. add/remove Friend)
 * @author Bridget
 * @version June 2019
 */
public class SocialNetwork {

	/**define attribute for SocialNetwork Class - Graph*/
	public Graph sn;
	/**counter to process the file correctly - nodes then edges*/
	int numRead = 0;

	/**Constructor - creates a new instance of the Graph and runs the 
	 * processFile method twice (for nodes then edges using readtimes)
	 */
	public SocialNetwork(){
		sn = new Graph();
		for (int i = 0; i<2; i++) {
			numRead++;
			try {
				processFile();
			} catch (Exception e) {
				System.out.println("Cannot process file");
				e.printStackTrace();
			}
		}
	}

	/**Reads data file and builds graph accordingly
	 * @throws Exception
	 */
	public void processFile() throws Exception{
		/**Uses a buffered reader to read data file*/
		File file = new File("data.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		int lineNumber = -1;
		String currentLine;
		String[] splittedValues;
		
		/**loop that runs until the file is complete that either 
		 * adds all the nodes or all the edges depending on readtimes*/
		while ((currentLine = br.readLine()) != null) {
			splittedValues = currentLine.split("\t|\\,");
			if (numRead == 1) {
				sn.addNode(splittedValues[0], 
						LocalDate.parse(splittedValues[1]), splittedValues[2]);			
			}else{	
				lineNumber++;	
				for (int j = 3; j < splittedValues.length; j++) {				
					Node n = sn.nodeList.get(lineNumber);
					splittedValues[j] = splittedValues[j].replace(" ", "");
					sn.addEdge(n, sn.nodeList.get
							(Integer.parseInt(splittedValues[j])-1));
				}
			}
		}			
		br.close();			
	}
	

	/**Adds a friend to the current person (x)
	 * @param x - current person node
	 * @param newFriend - friend to add
	 * @throws Exception
	 */
	public void addFriend(Node x, Node newFriend) throws Exception {
		sn.addEdge(x, newFriend);
	}

	/**Removes a friend from the current person(x)
	 * @param x - current person node
	 * @param newFriend - friend to remove 
	 */
	public void removeFriend(Node x, Node newFriend) {
		//remove a friend from the current person(x)
		sn.removeEdge(x, newFriend);
	}

	/**List of suggested friends being a friend of a friend
	 * or living in the same suburb
	 * @param currentPerson - person to find all suggested friends of
	 * @return list of suggested friends
	 */
	public List<Node> suggestFriends(Node currentPerson){
		/**Instantiates list to be filled with all suggested friends*/
		List<Node> suggestFriends = new ArrayList<Node>();	
		
		/**traverse the entire list of friends of currentPerson*/
		for (Map.Entry<Node, Edge> currentEntry : currentPerson.adj.entrySet()) {
			Node friend = currentEntry.getKey();
			/**traverse the entire list of friends of friend*/
			for (Map.Entry<Node, Edge> friendEntry : friend.adj.entrySet()) {
				Node friendOffriend = friendEntry.getKey();
				/**if share same suburb and not already friends add to list*/
				if (friendOffriend.getSuburb().equals(currentPerson.getSuburb()) 
						&& !currentPerson.adj.keySet().contains(friendOffriend)
						&& !currentPerson.equals(friendOffriend)) {
					suggestFriends.add(friendOffriend);
				}
			}
		}

		return suggestFriends;
	}
	
	/**Return string with number and names of mutual friends of two friends
	 * @param x - friend 1
	 * @param y - friend 2
	 * @return string of all the mutual friends
	 */
	public String getMutualFriends(Node x, Node y) {
		/**instantiates 2 new hashsets of all the friends of both x and y*/
		Set<Node> xFriends = new HashSet<Node>(x.adj.keySet());
		Set<Node> yFriends = new HashSet<Node>(y.adj.keySet());

		/**retains only those friends that are also in yFriends*/
		xFriends.retainAll(yFriends);

		return xFriends.toString();
	}

	/**Task 5
	 * all the friends of the input node sorted 
	 * based on the periods until their DoB
	 * @param currentPerson - input node
	 * @return string of friends in order from closest to furthest birthday
	 */
	public String remindBDEvents(Node currentPerson) {
		/** */
		PriorityQueue<Node> pq = new PriorityQueue<Node>(); 
		System.out.println("Hey " + currentPerson.getName() + ":-->");	

		/** */
		for (Map.Entry<Node, Edge> currentEntry : currentPerson.adj.entrySet()) {			
			Node n = currentEntry.getKey();
			pq.add(n);
		}
		
		/**find today's date and create a string to be filled*/
		LocalDate today = currentPerson.today;
		StringBuilder sb = new StringBuilder();
		
		/**loop until pq is empty*/
		while (!pq.isEmpty()) {
			/**returns the node at the head of pq*/
		    Node currentNode = pq.poll();
		    
		    /**gets the dateOB of that node and changes the year
		     * so it is the next birthday*/
		    LocalDate friendDate = currentNode.getDateOB();
			LocalDate nextBDay = friendDate.withYear(today.getYear());
			
			/**changes the year to next year if required*/
			if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
				nextBDay = nextBDay.plusYears(1);
			}
			
			/**Finds the period between today and birthday*/
			Period p = Period.between(today, nextBDay);
		    
			int years = p.getYears();
			int months = p.getMonths();
			int days = p.getDays();
			
			/**adds to stringbuilder within formulised output*/
			sb.append(currentNode.getName() + " has a birthday after " 
			+ years + " Year, " + months + " Months, " + days + " days\n");
		}

		return sb.toString();
	}
	
	/**Used to collate all hash codes into list to then test distribution
	 * See task 2 documentation for histogram
	 * @return list of hash codes
	 */
	public ArrayList<Integer> hashTest() {
		ArrayList<Integer> hashTest = new ArrayList<Integer>();
		for (Node nodes : sn.nodeList) {
			hashTest.add(nodes.hashCode());
		}
		return hashTest;
	}

}
