
import java.time.LocalDate;
import java.util.*;
/**
 * represents a draft test harness for all tasks in Ass3 (2019) (graph, hashing,
 * reading data from files, PQ, ... etc); you will need to implement all of your
 * test cases in this class
 * 
 *
 * @author Saber Elsayed
 * @version Jan 2019
 * 
 * implemented all the test cases for tasks in Ass3 (2019)
 * 
 * @author Bridget Free
 * @version June 2019
 */
public class TestHarness {

    static Node n = null, n2 = null, n3 = null, n4 = null, n5 = null;

    public static void main(String[] args) {

        /**
         * testing Task 1
         */
        System.out.println(" ------*****------ Task 1 begins ------*****-----");
        testTask1();
        System.out.println(" ------*****------ Task 1 ends ------*****------");
        System.out.print("\n \n");

        System.out.println(" ------*****------ Task 2 begins ------*****-----");
        testTask2();
        System.out.println(" ------*****------ Task 2 ends ------*****-------");
        System.out.print("\n \n");

        System.out.println(" ------*****------ Task 3 begins ------*****-----");
        testTask3();
        System.out.println(" ------*****------ Task 3 ends ------*****-------");
        System.out.print("\n \n");

        System.out.println(" -----*****---- Task 4-5 begins ------*****-----");
        testSocialNetwork();
        System.out.println(" ------*****----- Task 4-5 ends ------*****------");
        System.out.print("\n \n");

        System.out.print("Other things to consider \n"
                + "- code design \n"
                + "- big O \n"
                + "- java doc \n"
                + "- documentation of test cases \n"
                + "- research ... etc\n");
    }

    /**
     * Testing Task1: the basic operations in the Node and Edge classes;
     * below is just a simple test case you can use as a guide
     */
    public static void testTask1() {

        try {
            n = new Node("B", LocalDate.parse("2018-10-30"), "Bonner");
            n2 = new Node("B", LocalDate.parse("2018-09-30"), "Ford");

            System.out.print("Expected: B,2018-10-30,Bonner \t");
            System.out.print("Actual: " + n.toString() + "\t");
            if (n.getName().equals("B")
                    && n.getDateOB().equals(LocalDate.parse("2018-10-30"))
                    && n.getSuburb().equals("Bonner")) {
                System.out.println("Status: Node test --> PASS");
            } else {
                System.out.println("Status: Node test --> FAIL");
            }

        } catch (Exception e) {
            System.out.println("Actual: " + e.getMessage()
                    + " Status: Node test --> FAIL");
        }
        // test Edge class- you can create a separate method if you wish
        Edge edge;
        try {
            edge = new Edge(n3);         
            System.out.print("Expected: " + n2.toString() + "\t");
            System.out.print("Actual: " + edge.toString() + "\t");
            if (edge.getDest().equals(n2)) {
                System.out.println("Status: Edge test --> PASS");
            } else {
                System.out.println("Status: Edge test --> FAIL");
            }
        } catch (Exception e) {
        	System.out.println("Actual: " + e.getMessage()
            + " Status: Edge test --> FAIL");
        }
    }

    /**
     * testing Task 2
     */
    public static void testTask2() {
        try {
            n = new Node("B", LocalDate.parse("2018-10-30"), "Bonner");
            n2 = new Node("B*7", LocalDate.parse("2018-09-30"), "Ford");
            n3 = new Node("Carl", LocalDate.parse("2018-09-30"), "Bonner");
            n4 = new Node("Carl", LocalDate.parse("2018-09-30"), "Ford");
            n5 = new Node("Bridget Emily Free", LocalDate.parse("2000-07-05"), "Ford");
            
            int h1 = n.hashCode();
            int h2 = n2.hashCode();
            int h3 = n3.hashCode();
            int h4 = n4.hashCode();
            int h5 = n5.hashCode();
            
            System.out.println(h1);
            System.out.println(h2);
            System.out.println(h3);
            System.out.println(h4);
            System.out.println(h5);
           
           if ((h1 != h2) && (h1 != h3) && (h1 != h4) && (h1 != h5) &&
        		   (h2 != h3) && (h2 != h4) && (h2 != h5) && (h3 != h4) 
        		   && (h3 != h5) && (h4 != h5)) {
               System.out.println("Status: hashCode test --> PASS");
           }else {
        	   System.out.println("Status: hashCode test --> FAIL");
           }
           
           /*see distribution table on documentation 
            * (Task 2) for futher testing*/
           
        } catch (Exception e) {
            System.out.println("Issue: " + e.getMessage()
                    + " Status: hashCode test --> FAIL");
        }
    }

    /**
     * testing the Graph class
     */
    public static void testTask3() {
        Graph g = new Graph();
        Node v0 = null, v1 = null, v2 = null, v3 = null;
        //test add node
        try {
            v0 = g.addNode("V0", LocalDate.parse("2010-10-30"), "A");
            v1 = g.addNode("V1", LocalDate.parse("2010-10-30"), "B");
            v2 = g.addNode("V2", LocalDate.parse("2010-10-30"), "C");
        	
            if(g.nodeList.size() == 3) {
            	//For testing purposes only
            	//System.out.println(g.nodeList.size());
            	System.out.println("Status: addNode test --> PASS");
            }else {
         	   System.out.println("Status: addNode test --> FAIL");
            }

        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: addNode test --> FAIL");
        }

        // test add edges
        try {
            g.addEdge(v0, v1);
            g.addEdge(v0, v2);
            g.addEdge(v1, v2);
            
            if(g.getNeighbors(v0).size() == 2) {
            	//For testing purposes only
            	//System.out.println(g.getNeighbors(v0).size());
            	System.out.println("Status: addEdge test --> PASS");
            }else {
         	   System.out.println("Status: addEdge test --> FAIL");
            }

        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: addEdge test --> FAIL");
        }
        //test remove Edge
        try {
            g.removeEdge(v0, v2);
            
            if(g.getNeighbors(v0).size() == 1) {
            	//For testing purposes only
            	//System.out.println(g.getNeighbors(v0).size());
            	System.out.println("Status: removeEdge test --> PASS");
            }else {
         	   System.out.println("Status: removeEdge test --> FAIL");
            }

        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: removeEdge test --> FAIL");
        }
        //test remove node
        //time efficiency test (see task 3 speed testing)
        long startTime = System.nanoTime();
        try {
            g.removeNode(v0);
            
            if(g.nodeList.contains(v0)) {
            	System.out.println("Status: removeNode test --> FAIL");
            }else {
            	//For testing purposes only
            	//System.out.println(g.nodeList.contains(v0));
            	System.out.println("Status: removeNode test --> PASS");
            }

        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: removeNode test --> FAIL");
        }
        
        long endTime = System.nanoTime();
        //can be printed to confirm the time taken to complete the removeNode method
        //System.out.println("Removing the node took " + (endTime - startTime) + " nano seconds");
        
        //test getNeighbors
        try {
            Set<Node> setV = g.getNeighbors(v0);
            Set <Node> setV1 = g.getNeighbors(v1);
            Set<Node> s1 = Collections.emptySet();
            //v0 should no longer exist
            if(setV.equals(s1) && setV1.size() == 1) {
            	//For testing purposes only
            	//System.out.println(setV.equals(s1));
            	//System.out.println(setV1.size() == 1);
            	System.out.println("Status: getNeighbors test --> PASS");
            }else {
         	   System.out.println("Status: getNeighbors test --> FAIL");
            }
            
        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: getNeighbors test --> FAIL");
        }

    }

    /**
     * testing the Social Network class
     */
    public static void testSocialNetwork() {
        SocialNetwork driver = null;
        Node v0 = null, v1 = null, v2 = null, v3 = null, 
        		v4 = null, v5 = null; //v5 was a testing node
        
        //time efficiency test (see task 4 speed testing)
        long startTime = System.currentTimeMillis();
        try {
            driver = new SocialNetwork();
            
        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: SocialNetwork creation test --> FAIL");
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Creating the graph took " + (endTime - startTime) + " milliseconds");
        
        System.out.println(" ------*****------ Task 4 begins ------*****-----");
        // test suggestFriends
        List<Node> friendsOffriends = new ArrayList<>();
        
        try {
            //change data set depending on test case (see documentation)
        	v0 = driver.sn.addNode("V0", LocalDate.parse("2010-10-30"), "Belco");
            v1 = driver.sn.addNode("V1", LocalDate.parse("2010-10-30"), "Maj");
            v2 = driver.sn.addNode("V2", LocalDate.parse("2010-10-30"), "Belco");
            v3 = driver.sn.addNode("V3", LocalDate.parse("2010-10-30"), "Gungahlin");
            v4 = driver.sn.addNode("V4", LocalDate.parse("2010-10-30"), "Belco");
            driver.sn.addEdge(v0, v1);
            driver.sn.addEdge(v1, v2);
            driver.sn.addEdge(v1, v3);
            driver.sn.addEdge(v1, v4);
            
        	//create test set of nodes with friends of friends
            friendsOffriends = driver.suggestFriends(v0);

            //should only have two suggested friends (as per test data)
            if (friendsOffriends.size() == 2) {
                System.out.println(friendsOffriends.size());
                System.out.println("Status: suggestFriends test --> PASS\n");
            }else {
            	System.out.println("Status: suggestFriends test --> FAIL\n");
            }
            
        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: suggestFriends test --> FAIL");
        }

        // test getMutualFriends
        try {
        	//change data set depending on test case (see documentation)
        	v0 = driver.sn.addNode("V0", LocalDate.parse("2010-10-30"), "Belco");
            v1 = driver.sn.addNode("V1", LocalDate.parse("2010-10-30"), "Maj");
            v2 = driver.sn.addNode("V2", LocalDate.parse("2010-10-30"), "Belco");
            v3 = driver.sn.addNode("V3", LocalDate.parse("2010-10-30"), "Gungahlin");
            v4 = driver.sn.addNode("V4", LocalDate.parse("2010-10-30"), "Belco");
            driver.sn.addEdge(v0, v1);
            driver.sn.addEdge(v0, v2);
            driver.sn.addEdge(v1, v2);
            driver.sn.addEdge(v1, v3);
            driver.sn.addEdge(v1, v4);
            
            String actualMutual = driver.getMutualFriends(v0, v1);

            if (actualMutual.contains("V2")) {
                System.out.println(actualMutual);
                System.out.println("Status: actualMutual test --> PASS\n");
            }else {
            	System.out.println("Status: actualMutual test --> FAIL\n");
            }
            
        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: actualMutual test --> FAIL");
        }
        
        System.out.println(" ------*****------ Task 4 ends ------*****-------");
        System.out.println(" ------*****------ Task 5 begins ------*****-----");
        
        // check remindBDEvents Task 5
        try {
        	//test the daystilBirthday method
        	System.out.println("Testing daystilBirthday method...");
        	n = new Node("B", LocalDate.parse("2000-06-10"), "Bonner");
        	n3 = new Node("F", LocalDate.parse("2001-06-12"), "Bonner");
        	n2 = new Node("C", LocalDate.parse("1998-07-10"), "Bonner");
        	System.out.println(n.daystilBirthday(n));
        	System.out.println(n3.daystilBirthday(n3));
        	System.out.println(n2.daystilBirthday(n2) + "\n");
        	
        	//test the compareTo method
        	System.out.println("Testing compareTo method...");
        	System.out.println(n.compareTo(n2));
        	System.out.println(n.compareTo(n));
        	System.out.println(n2.compareTo(n3) + "\n");
        	
        	//test the remindBDEvents method
        	System.out.println("Testing remindBDEvents method...");
        	int numFriends = driver.sn.getNeighbors
        			(driver.sn.nodeList.get(10)).size();        	
            String allBirthdays = driver.remindBDEvents
            		(driver.sn.nodeList.get(10)) + "\t";
            int numLines = allBirthdays.split("\r\n|\r|\n").length - 1;		

            System.out.println(allBirthdays);
            System.out.println(numFriends + " " + numLines);
            if(numLines == numFriends) {
            	 System.out.println("Status: remindBDEvents test --> PASS\n");
            }
            System.out.println(" ------*****------ Task 5 ends ------*****-------");
            
            //To get all the hash code data to test distribution 
            //(see histogram Task 2 documentation)
            /*ArrayList<Integer> hashResults = driver.hashTest();            
            for (int i = 0; i < hashResults.size(); i++) {
                 System.out.println(hashResults.get(i));
            }*/
            
        } catch (Exception e) {
        	System.out.println("Issue: " + e.getMessage()
            + " Status: remindBDEvents test --> FAIL");
        }

    }
}
