import org.w3c.dom.NodeList;

public class XMLExtra {
	
	/**Extra points - part 2
     * inserts an element in a specific location in a tree
     * (XYZ before Harry Potter)
     * @param list all nodes in the tree
     */
    public void insert(NodeList nodeList) {
        boolean found = false;
        Node root = document.getDocumentElement();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node book = nodeList.item(i);
            Element bElement = (Element)(book);
            NodeList bookList = bElement.getChildNodes();
            int j = 0;

            /*loop through the list until the book - "Harry Potter" - is found*/
            while ((!found) && (j < bookList.getLength())) {
                Node n = bookList.item(j);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element name = (Element) n;
                    /* if  name is Harry Potter */
                    if (name.getTextContent().equals("Harry Potter")) {
                        //For testing purposes - prove we reached Harry Potter
                    	//System.out.println(name.getTextContent());
                        /*create new book node */
                        Node newNodeBook = document.createElement("book");
                        /* set attribute value */
                        ((Element) newNodeBook).setAttribute("category",
                                "bonusmarks");
                        /* Name node and its content */
                        Node nameNode = document.createElement("title");
                        Text elementText = document.createTextNode("XYZ");
                        nameNode.appendChild(elementText);
                        /* Author node and its content */
                        Node authorNode = document.createElement("author");
                        Text authorText = document.createTextNode("ABC");
                        nameNode.appendChild(authorText);
                        /* price node and its content */
                        Node priceNode = document.createElement("price");
                        Text priceText = document.createTextNode("99.99");
                        NewPriceNode.appendChild(priceText);

                        /* insert the new node before the current node */
                        newNodeBook.appendChild(nameNode);
                        newNodeBook.appendChild(authorNode);
                        newNodeBook.appendChild(priceNode);
                        root.insertBefore(newNodeBook, book);

                        found = true;
                    }
                }
                j++;
            }
        }
    }

    for (int i=0; i<bookstore.getLength(); i++) {
    	Node newBook = bookstore.item(i);
    	if (newBook.getNodeType() = Node.ELEMENT_NODE) {
    		Element bookElement = (Element) (newBook);
    		String bookTitle = bookElement.getAttribute(“title”);
    		System.out.println(bookTitle);
    	}
    }


}
