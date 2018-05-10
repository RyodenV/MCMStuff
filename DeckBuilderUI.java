import javax.swing.*;
import java.awt.*;
public class DeckBuilderUI extends JFrame{
	
	
	public DeckBuilderUI(){
		
		this.setTitle("Custom Card Deck Builder");
		this.setSize(1200,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		String cardLabels[] = { "A", "B", "C", "D", "E", "F" ,"G","H","I","J"};
		JList allCards = new JList(cardLabels); //data has type Object[]
		allCards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		allCards.setLayoutOrientation(JList.VERTICAL_WRAP);
		allCards.setVisibleRowCount(10);
		allCards.setFixedCellHeight(14);
		allCards.setFixedCellWidth(200);											// all cards
		JScrollPane cardsPane = new JScrollPane(allCards);
		cardsPane.setBounds(10,50,210,700);
		cardsPane.setVisible(true);
		this.add(cardsPane);
		
		JTextField searchInput = new JTextField();
		searchInput.setBounds(10,760,210,40);										// search text
		searchInput.setVisible(true);
		this.add(searchInput);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(220,760,150,39);										// search button
		searchButton.setVisible(true);
		this.add(searchButton);
		
		JButton addCardButton = new JButton("Add to Deck");
		addCardButton.setBounds(220,710,150,39);									// add card button
		addCardButton.setVisible(true);
		this.add(addCardButton);
		
		JCheckBox whiteBox = new JCheckBox("White");
		whiteBox.setBounds(220,50,150,20);
		whiteBox.setVisible(true);
		this.add(whiteBox);
		JCheckBox blackBox = new JCheckBox("Black");
		blackBox.setBounds(220,70,150,20);
		blackBox.setVisible(true);
		this.add(blackBox);
		JCheckBox redBox = new JCheckBox("Red");
		redBox.setBounds(220,90,150,20);
		redBox.setVisible(true);													// colours
		this.add(redBox);
		JCheckBox blueBox = new JCheckBox("Blue");
		blueBox.setBounds(220,110,150,20);
		blueBox.setVisible(true);
		this.add(blueBox);
		JCheckBox greenBox = new JCheckBox("Green");
		greenBox.setBounds(220,130,150,20);
		greenBox.setVisible(true);
		this.add(greenBox);
		JCheckBox colourlessBox = new JCheckBox("Colourless");
		colourlessBox.setBounds(220,150,150,20);
		colourlessBox.setVisible(true);
		this.add(colourlessBox);
		
		/*JCheckBox hasteBox = new JCheckBox("Haste");
		hasteBox.setBounds(220,190,150,20);
		hasteBox.setVisible(true);
		this.add(hasteBox);
		JCheckBox vigilanceBox = new JCheckBox("Vigilance");
		vigilanceBox.setBounds(220,210,150,20);
		vigilanceBox.setVisible(true);
		this.add(vigilanceBox);
		JCheckBox flyingBox = new JCheckBox("Flying");								// creature passive effects
		flyingBox.setBounds(220,230,150,20);
		flyingBox.setVisible(true);												MAKE AUTOMATIC
		this.add(flyingBox);
		JCheckBox trampleBox = new JCheckBox("Trample");
		trampleBox.setBounds(220,250,150,20);
		trampleBox.setVisible(true);
		this.add(trampleBox);*/
		
		String deckLabels[] = { "A", "B", "C", "D", "E", "F" ,"G","H","I","J"};
		JList deckCards = new JList(deckLabels); //data has type Object[]
		deckCards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		deckCards.setLayoutOrientation(JList.VERTICAL_WRAP);
		deckCards.setVisibleRowCount(10);
		deckCards.setFixedCellHeight(14);
		deckCards.setFixedCellWidth(200);											// deck cards
		JScrollPane deckPane = new JScrollPane(deckCards);
		deckPane.setBounds(965,300,210,450);
		deckPane.setVisible(true);
		this.add(deckPane);
		
		JButton removeCardButton = new JButton("Remove from Deck");
		removeCardButton.setBounds(805,710,150,39);									// remove card button
		removeCardButton.setVisible(true);
		this.add(removeCardButton);
		
		String decksLabel[] = { "A", "B", "C", "D", "E", "F" ,"G","H","I","J"};
		JList decksList = new JList(decksLabel); //data has type Object[]
		decksList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		decksList.setLayoutOrientation(JList.VERTICAL_WRAP);
		decksList.setVisibleRowCount(10);
		decksList.setFixedCellHeight(14);
		decksList.setFixedCellWidth(200);											// deck cards
		JScrollPane decksPane = new JScrollPane(deckCards);
		decksPane.setBounds(965,760,210,100);
		decksPane.setVisible(true);
		this.add(decksPane);
		
		JButton saveDeck = new JButton("Save Deck");
		saveDeck.setBounds(805,710,150,39);									// remove card button
		saveDeck.setVisible(true);
		this.add(saveDeck);
		
		this.setVisible(true);

	}
}
