import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class DeckBuilderUI extends JFrame{
	
	ArrayList<Card> allCardsList = new ArrayList<Card>();
	ArrayList<Card> confinedCardsList;
	
	ArrayList<CustomDeck> allDecks = new ArrayList<CustomDeck>();
	CustomDeck tempDeck = new CustomDeck();
	
	
	
	JLabel displayCardBackground = new JLabel();
	JLabel displayCardName = new JLabel();
	JLabel displayCardTypes = new JLabel();
	JLabel displayCardImage = new JLabel();
	JLabel displayCardAbilities = new JLabel();
	JLabel displayCardText = new JLabel();
	
	public DeckBuilderUI(){
		
		
		this.setTitle("Custom Card Deck Builder");
		this.setSize(1200,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		
		JList allCards = new JList();
		JButton addCardButton = new JButton("Add to Deck");
		JButton saveDeck = new JButton("Save Deck");
		JList deckCards = new JList();
		String decksLabel[] = {"F","F","F","F","F","F","F","F","F","F"};
		JList decksList = new JList(decksLabel);
		
		
		JLabel cardsLabel = new JLabel("Custom Cards");
		cardsLabel.setBounds(10,10,210,40);											// cards label
		cardsLabel.setFont(new Font("SansSerif",Font.BOLD,20));
		cardsLabel.setVisible(true);
		cardsLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(cardsLabel);
		
		
		
		
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("CreatureHolder.dat"));
			allCardsList = (ArrayList<Card>)in.readObject();
			in.close();
			confinedCardsList = allCardsList;
			String cardsForJList[] = new String[confinedCardsList.size()];
			for(int i = 0;i<confinedCardsList.size();i++){
				cardsForJList[i] = confinedCardsList.get(i).name;					// importing custom cards
			}
			allCards.setListData(cardsForJList);
		}catch(ClassNotFoundException e){
			System.out.println ("CnF");
		}catch (FileNotFoundException e){
			System.out.println ("FnF");
		}catch(IOException e){
			System.out.println (e.toString());
		}
		
		addCardButton.setBounds(220,710,150,39);									// add card button
		addCardButton.setVisible(true);
		addCardButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tempDeck.cards.add(confinedCardsList.get(allCards.getSelectedIndex()));
				deckCards.setListData(tempDeck.sortAndExport());
				deckCards.setVisibleRowCount(tempDeck.cards.size());
			}
		});
		addCardButton.setEnabled(false);
		this.add(addCardButton);
		allCards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		allCards.setLayoutOrientation(JList.VERTICAL_WRAP);
		allCards.setVisibleRowCount(allCardsList.size());
		allCards.setFixedCellHeight(14);
		allCards.setFixedCellWidth(200);											// all cards
		allCards.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				/*String testLabel[] = {"Cards","have","been","Touched","SHADES","SHADES","SHADES","SHADES","SHADES","SHADES"};
				allCards.setListData(testLabel);*/
				if(saveDeck.isEnabled()){
					addCardButton.setEnabled(true);
				}
				setDisplayCard(confinedCardsList.get(allCards.getSelectedIndex()));
			}
		});
		JScrollPane cardsPane = new JScrollPane(allCards);
		cardsPane.setBounds(10,50,210,700);
		cardsPane.setVisible(true);
		this.add(cardsPane);
		
		JCheckBox cardColours[] = new JCheckBox[Card.colours.length];
		int colourStaticRight = 220;
		int colourStaticDown = 50;
		int colourGap = 20;																// card colours
		int colourStaticExtendRight = 120;
		for(int i = 0;i<cardColours.length;i++){
			cardColours[i] = new JCheckBox(Card.colours[i]);		
			cardColours[i].setBounds(colourStaticRight,colourStaticDown + i*colourGap,colourStaticExtendRight,colourGap);
			cardColours[i].setVisible(true);
			cardColours[i].setName("" + i);
			this.add(cardColours[i]);
		}
		
		Card cardTypesClass[] = {new Creature(),new Land(),new Enchantment(),new Artifact()};
		String cardTypeNames[] = {"Creature","Land","Enchantment","Artifact"};
		JCheckBox cardTypes[] = new JCheckBox[cardTypesClass.length];
		int typesStaticRight = 220;
		int typesStaticDown = 50;
		int typesGap = 20;
		int typesStaticExtendRight = 120;
		for(int i = 0;i<cardTypes.length;i++){
			cardTypes[i] = new JCheckBox(cardTypeNames[i]);
			cardTypes[i].setBounds(typesStaticRight,typesStaticDown + (i+cardColours.length+1)*typesGap,typesStaticExtendRight,typesGap);
			cardTypes[i].setVisible(true);
			cardTypes[i].setName("" + i);
			this.add(cardTypes[i]);
		}
		
		JCheckBox creatureKeywords[] = new JCheckBox[Creature.keywords.length];
		int keywordStaticRight = 220;
		int keywordStaticDown = 50;
		int keywordGap = 20;																// creature keywords
		int keywordStaticExtendRight = 120;
		for(int i = 0;i<creatureKeywords.length;i++){
			creatureKeywords[i] = new JCheckBox(Creature.keywords[i]);		
			creatureKeywords[i].setBounds(keywordStaticRight,keywordStaticDown + (i+cardTypeNames.length+cardColours.length+2)*keywordGap,keywordStaticExtendRight,keywordGap);
			creatureKeywords[i].setVisible(true);
			creatureKeywords[i].setName("" + i);
			this.add(creatureKeywords[i]);
		}
		
		JTextField searchInput = new JTextField();
		searchInput.setBounds(10,760,210,40);										// search text
		searchInput.setVisible(true);
		this.add(searchInput);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(220,760,150,39);										// search button
		searchButton.setVisible(true);
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				confinedCardsList = new ArrayList<Card>();
				for(int i = 0;i<allCardsList.size();i++){
					confinedCardsList.add(allCardsList.get(i));		// copying original arraylist
				}
				
				for(int i = 0;i<confinedCardsList.size();i++){
					boolean removeCard = false;
					boolean removeCardOnType = false;
					boolean keepCardOnType = false;
					for(int c = 0;c<cardColours.length;c++){								// card colour
						if(cardColours[c].isSelected()&&!confinedCardsList.get(i).colour[c]){
							removeCard = true;
						}
					}
					if(!confinedCardsList.get(i).name.contains(searchInput.getText())||!confinedCardsList.get(i).flavourText.contains(searchInput.getText())){
						removeCard = true;
					}
					if(confinedCardsList.get(i).getClass()==Creature.class){				// CREATURES
						for(int j = 0;j<creatureKeywords.length;j++){							// creature keywords
							if(creatureKeywords[j].isSelected()&&!((Creature)confinedCardsList.get(i)).activeKeywords.contains((Integer)j)){
								removeCard = true;
							}
						}
					}
					
					// checking card types (artifact etc) - if they contain one of the selected types, they can stay if not already eliminated
					// make use of removeCardOnType and keepCardOnType
					// first if statement checks if a specific box is checked
					// second checks if the card matches the type. if it does, keepCardOnType is true.
					// if it doesn't, removeCardOnType is true.
					for(int t = 0;t<cardTypesClass.length;t++){
						if(cardTypes[t].isSelected()==true){
							if(confinedCardsList.get(i).getClass()==cardTypesClass[t].getClass()){
								keepCardOnType = true;
							}else{
								removeCardOnType = true;
							}
						}
					}
					if(removeCard){
						confinedCardsList.remove(i--);
					}else if(removeCardOnType&&!keepCardOnType){
						confinedCardsList.remove(i--);
					}
				}
				
				String cardsForJList[] = new String[confinedCardsList.size()];
				for(int i = 0;i<confinedCardsList.size();i++){
					cardsForJList[i] = confinedCardsList.get(i).name;
				}
				allCards.setListData(cardsForJList);
				allCards.setVisibleRowCount(confinedCardsList.size());
			}
		});
		this.add(searchButton);
		
		
		
		
		
		
		JTextField deckName = new JTextField("[Deck Name] (load deck)");
		deckName.setBounds(814,10,360,40);											// deck name text
		deckName.setEditable(false);
		deckName.setFont(new Font("SansSerif",Font.BOLD,20));
		deckName.setHorizontalAlignment(JTextField.CENTER);
		deckName.setVisible(true);
		this.add(deckName);
		
		JButton removeCardButton = new JButton("Remove from Deck");
		removeCardButton.setBounds(814,50,150,39);									// remove card button
		removeCardButton.setVisible(true);
		removeCardButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tempDeck.cards.remove(deckCards.getSelectedIndex());
				deckCards.setListData(tempDeck.sortAndExport());
				deckCards.setVisibleRowCount(tempDeck.cards.size());
				
				removeCardButton.setEnabled(false);
				
			}
		});
		this.add(removeCardButton);
		removeCardButton.setEnabled(false);
		
		saveDeck.setBounds(814,590,150,39);											// save deck button
		saveDeck.setVisible(true);
		saveDeck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tempDeck.name = deckName.getText();
				
			}
		});
		this.add(saveDeck);
		saveDeck.setEnabled(false);
		
		
		deckCards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		deckCards.setLayoutOrientation(JList.VERTICAL_WRAP);
		deckCards.setVisibleRowCount(100);
		deckCards.setFixedCellHeight(14);
		deckCards.setFixedCellWidth(200);											// deck cards
		deckCards.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				String testLabel[] = {"Decks","have","been","Touched","COLOURS","COLOURS","COLOURS","COLOURS","COLOURS","COLOURS"};
				//deckCards.setListData(testLabel);
				
				removeCardButton.setEnabled(true);
			}
		});
		JScrollPane deckPane = new JScrollPane(deckCards);
		deckPane.setBounds(965,50,210,580);
		deckPane.setVisible(true);
		this.add(deckPane);
		
		
		
		
		JButton deleteDeck = new JButton("Delete Deck");
		deleteDeck.setBounds(814,640,150,39);											// delete deck button
		deleteDeck.setVisible(true);
		this.add(deleteDeck);
		deleteDeck.setEnabled(false);
		JButton newDeck = new JButton("Create New Deck");
		newDeck.setBounds(814,680,150,39);											// new deck button
		newDeck.setVisible(true);
		newDeck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveDeck.setEnabled(true);
				deckName.setEditable(true);
				tempDeck = new CustomDeck();
				deckName.setText(tempDeck.name);
			}
		});
		this.add(newDeck);
		JButton loadDeck = new JButton("Load Deck");
		loadDeck.setBounds(814,720,150,39);											// load deck button
		loadDeck.setVisible(true);
		this.add(loadDeck);
		loadDeck.setEnabled(false);
		JButton exportDecks = new JButton("Export Decks");
		exportDecks.setBounds(814,760,150,39);											// export decks button
		exportDecks.setVisible(true);
		this.add(exportDecks);
		
		
		//String testLabel[] = {"J","I","H","G","F","E","D","C","B","A"};
		
		decksList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		decksList.setLayoutOrientation(JList.VERTICAL_WRAP);
		decksList.setVisibleRowCount(10);
		decksList.setFixedCellHeight(14);
		decksList.setFixedCellWidth(200);											// decks list
		decksList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				deleteDeck.setEnabled(true);
				loadDeck.setEnabled(true);
				setDisplayCard(tempDeck.cards.get(decksList.getSelectedIndex()));
			}
		});
		JScrollPane decksPane = new JScrollPane(decksList);
		decksPane.setBounds(965,640,210,160);
		decksPane.setVisible(true);
		this.add(decksPane);
		
		this.setVisible(true);
		
		
		/*try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TestDat.dat"));
			out.writeObject("Mesa Unicorn");
			out.close();
		}catch(IOException e){
			System.out.println ("IO");
		}*/
	}
	
	private String[] updateDeckCards(ArrayList<Card> deck){
		String[] incomplete = {"Incomplete","Go Away"};
		return incomplete;
	}
	
	
	
	private void setDisplayCard(Card target){
		
	}
}
