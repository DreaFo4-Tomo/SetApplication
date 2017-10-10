package com.SetApplication;

//Import the packages we need utilize.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The application implements a calculator of set.
 * 
 * For any given sets A and B,it can find their union,difference,intersection,
 * as well as the cardinality of each set.
 * 
 * It can also check whether an element belongs to a specific set or not.
 * 
 * The set only hold integer value.
 * 
 * @author Owner
 * 
 */ 

public class SetApp {

	private JFrame frame;

	private String info1 = "Please input numbers with comma separating";
	private String info2 = "Please input A number";
	Set<Integer> A = new HashSet<Integer>();
	Set<Integer> B = new HashSet<Integer>();
	Set<Integer> R = new HashSet<Integer>();
	int ELE = 00;
	int size1,size2;
	private Font myFont;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetApp window = new SetApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();//Exception handling.
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SetApp() {
		myFont = new Font("",Font.ITALIC, 18);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frame = new JFrame("A Simple Set App");
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Divide the frame into two panels.
		JPanel upperPanel=new JPanel();
		JPanel lowerPanel=new JPanel();

		JPanel APanel=new JPanel();
		JPanel BPanel=new JPanel();
		JPanel EPanel=new JPanel();
		JPanel ResultPanel=new JPanel();

		//Create labels.
		JLabel setA = new JLabel("Set A : ");
		setA.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel setB = new JLabel("Set B : ");
		setB.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel eleE = new JLabel("Element E : ");
		eleE.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel Result = new JLabel("Result : ");
		Result.setHorizontalAlignment(SwingConstants.CENTER);

		//Format the upper panel.
		//Set the Grid-layout of APanel.

		APanel.setLayout(new GridLayout(1,2));

		//Initialize the text-field.
		JTextField fieldOfA,fieldOfB,fieldOfE,fieldOfOutput;
		fieldOfA = new JTextField(); 

		fieldOfA.setHorizontalAlignment(JTextField.CENTER);
		fieldOfA.setBackground(Color.lightGray);
		fieldOfA.setText(info1);
		fieldOfA.setFont(myFont);
		fieldOfA.setEditable(true);
		fieldOfA.addFocusListener(new MyFocusListener(info1, fieldOfA,A));

		APanel.add(setA);
		APanel.add(fieldOfA);
		upperPanel.add(APanel);


		//SET B Panel
		BPanel.setLayout(new GridLayout(1,2));

		fieldOfB = new JTextField(); 

		fieldOfB.setHorizontalAlignment(JTextField.CENTER);   
		fieldOfB.setBackground(Color.lightGray);
		fieldOfB.setText(info1);
		fieldOfB.setFont(myFont);
		fieldOfB.setEditable(true);
		fieldOfB.addFocusListener(new MyFocusListener(info1, fieldOfB,B));

		BPanel.add(setB);
		BPanel.add(fieldOfB);
		upperPanel.add(BPanel);

		//Element e panel.
		EPanel.setLayout(new GridLayout(1,2));

		fieldOfE = new JTextField();

		fieldOfE.setText(info2);
		fieldOfE.setFont(myFont);

		Set<Integer> tempSet = new HashSet<>();
		fieldOfE.addFocusListener(new MyFocusListener(info2, fieldOfE,tempSet));

		fieldOfE.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e1) {
				//Deal with the input.
				if (Character.isDigit(e1.getKeyChar())) {
					ELE = (int)e1.getKeyChar();
					return;
				}
				else 
					e1.consume();
			}
		});
		fieldOfE.setHorizontalAlignment(JTextField.CENTER);   
		fieldOfE.setBackground(Color.lightGray);
		fieldOfE.setEditable(true);

		EPanel.add(eleE);
		EPanel.add(fieldOfE);
		upperPanel.add(EPanel); 

		//Result panel.
		ResultPanel.setLayout(new GridLayout(1,2));

		fieldOfOutput = new JTextField();
		fieldOfOutput.setHorizontalAlignment(JTextField.CENTER);   
		fieldOfOutput.setBackground(Color.GRAY);
		fieldOfOutput.setForeground(Color.red);
		fieldOfOutput.setFont(myFont);
		fieldOfOutput.setEditable(false);

		ResultPanel.add(Result);
		ResultPanel.add(fieldOfOutput);
		upperPanel.add(ResultPanel);	

		//upperPanel's overall layout 
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS)); 
		upperPanel.setBackground(Color.BLACK);


		//Format the lower panel.
		//Add the button to the panel.
		Button Intersection = new Button("Intersection");
		Intersection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Obtain the event source.
				Object obj = e.getSource();
				if ((Button)obj == Intersection)
				{
					R.addAll(A);
					R.retainAll(B);
					fieldOfOutput.setText(R.toString());

				}
			}
		});
		lowerPanel.add(Intersection);  

		Button Union = new Button("Union");
		Union.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Obtain the event source.
				Object obj = e.getSource();
				if ((Button)obj == Union)
				{
					R.addAll(A);
					R.addAll(B);
					fieldOfOutput.setText(R.toString());
				}
			}
		});
		lowerPanel.add(Union);


		Button Difference = new Button("Difference");
		Difference.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Obtain the event source.
				Object obj = e.getSource();
				if ((Button)obj == Difference)
				{
					R.addAll(A);
					R.removeAll(B);
					fieldOfOutput.setText(R.toString());
				}
			}
		});
		lowerPanel.add(Difference);

		Button elementBelonging = new Button("elementBelonging");
		elementBelonging.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Obtain the event source.
				Object obj = e.getSource();
				if ((Button)obj == elementBelonging)
				{
					if(ELE == 00)
						fieldOfOutput.setText("Please input an element.");
					else {
						if(A.contains(ELE)==true&&B.contains(ELE)==true)
							fieldOfOutput.setText("The element belongs to set A and set B.");
						else if(B.contains(ELE)==true&&A.contains(ELE)==false)
							fieldOfOutput.setText("The element belongs to set B.");
						else if(A.contains(ELE)==true&&B.contains(ELE)==false)
							fieldOfOutput.setText("The element belongs to set A.");
						else if(B.contains(ELE)==false&&A.contains(ELE)==false)
							fieldOfOutput.setText("The element doesn't belong to either of them.");
					}
				}
			}
		});
		lowerPanel.add(elementBelonging);


		Button findCardinality = new Button("FindCardinality");
		findCardinality.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Obtain the event source.
				Object obj = e.getSource();
				if ((Button)obj == findCardinality)
				{
					size1 = A.size();
					size2 = B.size();
					fieldOfOutput.setText("The cardinality of the set A and B is : "+size1+","+size2);
				}
			}
		});
		lowerPanel.add(findCardinality);

		Button setBelonging = new Button("setBelonging");
		setBelonging.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Obtain the event source.
				Object obj = e.getSource();
				if ((Button)obj == setBelonging)
				{
					if(A==null||B==null)
						fieldOfOutput.setText("Please input numbers of the two sets.");

					if(A.containsAll(B)==true&&B.containsAll(A)==true)
						fieldOfOutput.setText("B belongs to A,and A belongs to B.");
					else if(A.containsAll(B)==true&&B.containsAll(A)==false)
						fieldOfOutput.setText("B belongs to A");
					else if(B.containsAll(A)==true&&A.containsAll(B)==false)
						fieldOfOutput.setText("A belongs to B");
					else if(B.containsAll(A)==false&&A.containsAll(B)==false)
						fieldOfOutput.setText("B doesn't belong to A,and A doesn't belong to B");
				}
			}
		});
		lowerPanel.add(setBelonging);

		lowerPanel.setLayout(new GridLayout(4,2));
		//The overall layout.
		frame.getContentPane().add(upperPanel);
		frame.getContentPane().add(lowerPanel);

		GridLayout gridLayout = new GridLayout(2,1);
		gridLayout.setVgap(30);
		frame.getContentPane().setLayout(gridLayout); 
	}
}

class MyFocusListener implements FocusListener {
	String info;
	JTextField jtf;
	Set set;
	public MyFocusListener(String info, JTextField jtf,Set<Integer> Aset) {
		this.info = info;
		this.jtf = jtf;
		set = Aset;
	}
	@Override
	public void focusGained(FocusEvent e) {//When the panel gain focus,the texts will disappear.
		String temp = jtf.getText();
		if(temp.equals(info)){
			jtf.setText("");
			jtf.setFont(new Font("",Font.PLAIN, 18));
			jtf.setForeground(Color.black);
		}
	}
	@Override
	public void focusLost(FocusEvent e) {//if the panel is blank when it loses focus,the texts will show up.
		String temp = jtf.getText();

		if(temp.equals("")){
			jtf.setText(info);
			jtf.setFont(new Font("",	 Font.ITALIC, 18));
			jtf.setForeground(Color.LIGHT_GRAY);
		}else if(!temp.equals(info)){
			String text = jtf.getText();
			System.out.println(text);
			String[] nums = text.split(",");
			for(int i=0;i<nums.length;i++) {
				set.add(Integer.valueOf(nums[i]));
			}
		}
	}
}

