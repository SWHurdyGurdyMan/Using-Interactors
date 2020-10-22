import acm.program.*;
import acm.graphics.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class UsingInteractors extends GraphicsProgram{
	public void init() {
		boxes = new HashMap<String, GObject>();
		createCanvas();
		addActionListeners();
		addMouseListeners();
	}
	
	public void createCanvas() {
		nameLabel = new JLabel("Name: ");
		nameEntry = new JTextField(MAX_NAME);
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		add(nameLabel, SOUTH);
		add(nameEntry, SOUTH);
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
	}
	//add box to screen
	private void createBox(String nameEntry) {
		GCompound box = new GCompound();
		GRect boxOutline = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel boxLabel = new GLabel(nameEntry);
		box.add(boxOutline, -BOX_WIDTH/2, -BOX_HEIGHT/2);
		box.add(boxLabel, -boxLabel.getWidth()/2, -boxLabel.getHeight()/2);
		add(box, getWidth()/2, getHeight()/2);
		boxes.put(nameEntry, box);
	}
	
	private void removeBox(String nameEntry) {
		GObject removableBoxName = boxes.get(nameEntry);
		if(removableBoxName!= null) {
			remove(removableBoxName);
		}
	}
	
	private void clearAllBoxes() {
		Iterator<String> iterator = boxes.keySet().iterator();
		while(iterator.hasNext()) {
			removeBox(iterator.next());
		}
		boxes.clear();
	}
	
	public void actionPerformed(ActionEvent buttonPress) {
		Object source = buttonPress.getSource();
		if(source == addButton || source == nameEntry) {
			createBox(nameEntry.getText());
		} else if (source == removeButton) {
			removeBox(nameEntry.getText());
		} else if (source == clearButton) {
			clearAllBoxes();
		}
	}
	
	public void mousePressed(MouseEvent press) {
		last = new GPoint(press.getPoint());
		currentObject = getElementAt(last);
	}
	
	public void mouseDragged(MouseEvent drag) {
		if (currentObject != null) {
			currentObject.move(drag.getX() - last.getX(),
			 drag.getY() - last.getY());
			last = new GPoint(drag.getPoint());
			}
	}
	
	public void mouseClicked(MouseEvent click) {
		if (currentObject != null) currentObject.sendToFront();
	}
	
	
	private static final int MAX_NAME = 25;
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	private HashMap<String,GObject> boxes;
	private JLabel nameLabel;
	private JTextField nameEntry;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private GObject currentObject;
	private GPoint last;
}