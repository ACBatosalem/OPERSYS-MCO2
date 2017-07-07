
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;

public class GUIDrive extends JFrame implements MouseListener{
	private JPanel contentPane;

	public  JPanel    stationPanel;
	public  JLabel    stationLabel;
	public  JPanel    panel;
	public  JLabel    lblNewLabel;
	public  JButton   addStation1Btn;
	public  JButton   addStation2Btn;
	public  JButton   addStation4Btn;
	public  JButton   addStation3Btn;
	public  JLabel    lblDeployTrainAt;
	public  JButton   deployStation1Btn;
	public  JButton   deployStation2Btn;
	public  JButton   deployStation4Btn;
	public  JButton   deployStation3Btn;
	public  JPanel    panel_1;
	public  JTextArea submittedBy;
	public  JPanel    station1Panel; 
	public  JLabel    station1Lbl;
	public  JTextArea station1Status;
	public  JPanel    station3Panel; 
	public  JLabel    station3Lbl;
	public  JTextArea station3Status;
	public  JPanel    station2Panel; 
	public  JLabel    station2Lbl;
	public  JTextArea station2Status;
	public  JPanel    station4Panel;
	public  JLabel    station4Lbl;
	public  JTextArea station4Status;
	public  JSpinner capacitySpinner; 
	public caltrain  c;	
	
	public ArrayList<JTextArea> stationList;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;

	public static void main(String[] args) {
		GUIDrive gui;
		caltrain c;
		
		gui = new GUIDrive();

		c = new caltrain();
		c.station_init(); // add's 2nd station to the caltrain system
		c.station_init(); // add's 3nd station to the caltrain system
		c.station_init(); // add's 4nd station to the caltrain system
		
		c.setGUI(gui);
		gui.setLogic(c);
		
		gui.setVisible(true);
		
		System.out.println("PROCESS SYNCHRONIZATION WITH SEMAPHORES");
		
	}
	
	/**
	 * Create the frame.
	 */
	public GUIDrive() {
		initComponents();
		
		stationList = new ArrayList<>();
		stationList.add(station1Status);
		stationList.add(station2Status);
		stationList.add(station3Status);
		stationList.add(station4Status);
	}
	
	public void setLogic(caltrain c){
		this.c = c;
	}
	
	public void initComponents(){
		setTitle("PROCESS SYNCHRONIZATION WITH SEMAPHORES");
		
		
		stationPanel = new JPanel();
		stationLabel = new JLabel("Stations");
		panel = new JPanel();
		lblNewLabel = new JLabel("Add Passengers At");
		addStation1Btn = new JButton("Station 1");
		addStation2Btn = new JButton("Station 2");
		addStation4Btn = new JButton("Station 4");
		addStation3Btn = new JButton("Station 3");
		lblDeployTrainAt = new JLabel("Deploy Train Details");
		deployStation1Btn = new JButton("Station 1");
		deployStation2Btn = new JButton("Station 2");
		deployStation4Btn = new JButton("Station 4");
		capacitySpinner = new JSpinner();
		deployStation3Btn = new JButton("Station 3");
		panel_1 = new JPanel();
		submittedBy = new JTextArea();
		station1Panel = new JPanel();
		station1Lbl = new JLabel("Station 1");
		station3Panel = new JPanel();
		station3Lbl = new JLabel("Station 3");
		station2Panel = new JPanel();
		station2Lbl = new JLabel("Station 2");
		station4Panel = new JPanel();
		station4Lbl = new JLabel("Station 4");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 1040);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		stationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		stationPanel.setBackground(new Color(0, 128, 0));
		stationPanel.setBounds(10, 11, 203, 459);
		contentPane.add(stationPanel);
		stationPanel.setLayout(null);
		
		stationLabel.setForeground(new Color(255, 255, 255));
		stationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stationLabel.setFont(new Font("Josefin Sans", Font.PLAIN, 20));
		stationLabel.setBounds(10, 11, 183, 26);
		stationPanel.add(stationLabel);
		
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(new Color(34, 139, 34));
		panel.setBounds(10, 37, 183, 411);
		stationPanel.add(panel);
		panel.setLayout(null);
		
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Josefin Sans", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 127, 20);
		panel.add(lblNewLabel);
		
		addStation1Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		addStation1Btn.setBounds(10, 42, 73, 23);
		addStation1Btn.addMouseListener(this);
		panel.add(addStation1Btn);
		
		addStation2Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		addStation2Btn.setBounds(100, 42, 73, 23);
		addStation2Btn.addMouseListener(this);
		panel.add(addStation2Btn);
		
		addStation4Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		addStation4Btn.setBounds(100, 76, 73, 23);
		addStation4Btn.addMouseListener(this);
		panel.add(addStation4Btn);
		
		addStation3Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		addStation3Btn.setBounds(10, 76, 73, 23);
		addStation3Btn.addMouseListener(this);
		panel.add(addStation3Btn);
		
		lblDeployTrainAt.setForeground(Color.WHITE);
		lblDeployTrainAt.setFont(new Font("Josefin Sans", Font.BOLD, 13));
		lblDeployTrainAt.setBounds(10, 110, 127, 20);
		panel.add(lblDeployTrainAt);
		
		deployStation1Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		deployStation1Btn.setBounds(10, 175, 73, 23);
		deployStation1Btn.addMouseListener(this);
		panel.add(deployStation1Btn);
		
		deployStation2Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		deployStation2Btn.setBounds(100, 175, 73, 23);
		deployStation2Btn.addMouseListener(this);
		panel.add(deployStation2Btn);
		
		deployStation4Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		deployStation4Btn.setBounds(100, 209, 73, 23);
		deployStation4Btn.addMouseListener(this);
		panel.add(deployStation4Btn);
		
		deployStation3Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		deployStation3Btn.setBounds(10, 209, 73, 23);
		deployStation3Btn.addMouseListener(this);
		panel.add(deployStation3Btn);
		
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setBounds(10, 267, 163, 133);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		submittedBy.setForeground(new Color(255, 255, 255));
		submittedBy.setBackground(new Color(0, 128, 0));
		submittedBy.setText("Submitted By:\r\n\r\n Gaba, Janelle Marie\r\n Ngo, Jan Bertel \r\n Ticug, Jonal Ray \r\nOPERSYS S21 Grp11");
		submittedBy.setFont(new Font("Josefin Sans", Font.PLAIN, 15));
		submittedBy.setBounds(10, 11, 143, 111);
		panel_1.add(submittedBy);
		
		lblNewLabel_1 = new JLabel("Capacity: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 141, 73, 23);
		panel.add(lblNewLabel_1);
		
		capacitySpinner.setModel(new SpinnerNumberModel(new Integer(5), new Integer(1), null, new Integer(1)));
		capacitySpinner.setBounds(100, 141, 73, 20);
		panel.add(capacitySpinner);
		
		station1Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station1Panel.setBackground(new Color(0, 128, 0));
		station1Panel.setBounds(223, 11, 277, 224);
		contentPane.add(station1Panel);
		station1Panel.setLayout(null);
		
		station1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		station1Lbl.setForeground(Color.WHITE);
		station1Lbl.setFont(new Font("Josefin Sans", Font.PLAIN, 20));
		station1Lbl.setBounds(10, 11, 257, 26);
		station1Panel.add(station1Lbl);
		
		station3Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station3Panel.setBackground(new Color(0, 128, 0));
		station3Panel.setBounds(223, 246, 277, 224);
		contentPane.add(station3Panel);
		station3Panel.setLayout(null);
		
		station3Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		station3Lbl.setForeground(Color.WHITE);
		station3Lbl.setFont(new Font("Josefin Sans", Font.PLAIN, 20));
		station3Lbl.setBounds(10, 11, 257, 26);
		station3Panel.add(station3Lbl);
		
		station2Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station2Panel.setBackground(new Color(0, 128, 0));
		station2Panel.setBounds(510, 11, 277, 224);
		contentPane.add(station2Panel);
		station2Panel.setLayout(null);
		
		station2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		station2Lbl.setForeground(Color.WHITE);
		station2Lbl.setFont(new Font("Josefin Sans", Font.PLAIN, 20));
		station2Lbl.setBounds(10, 11, 257, 26);
		station2Panel.add(station2Lbl);
		
		station4Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station4Panel.setBackground(new Color(0, 128, 0));
		station4Panel.setBounds(510, 246, 277, 224);
		contentPane.add(station4Panel);
		station4Panel.setLayout(null);
		
		station4Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		station4Lbl.setForeground(Color.WHITE);
		station4Lbl.setFont(new Font("Josefin Sans", Font.PLAIN, 20));
		station4Lbl.setBounds(10, 11, 257, 26);
		station4Panel.add(station4Lbl);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 257, 165);
		station1Panel.add(scrollPane);
		station1Status = new JTextArea();
		station1Status.setEditable(false);
		scrollPane.setViewportView(station1Status);
		
		station1Status.setFont(new Font("Josefin Sans", Font.PLAIN, 15));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 48, 257, 165);
		station2Panel.add(scrollPane_1);
		station2Status = new JTextArea();
		station2Status.setEditable(false);
		scrollPane_1.setViewportView(station2Status);
		
		station2Status.setFont(new Font("Josefin Sans", Font.PLAIN, 15));
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 48, 257, 165);
		station3Panel.add(scrollPane_2);
		
		station3Status = new JTextArea();
		station3Status.setEditable(false);
		scrollPane_2.setViewportView(station3Status);
		
		station3Status.setFont(new Font("Josefin Sans", Font.PLAIN, 15));
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 48, 257, 165);
		station4Panel.add(scrollPane_3);
		station4Status = new JTextArea();
		station4Status.setEditable(false);
		scrollPane_3.setViewportView(station4Status);
		
		station4Status.setFont(new Font("Josefin Sans", Font.PLAIN, 15));
	}

	public void toggleButtons(boolean flag){
		addStation1Btn.setEnabled(flag);
		addStation2Btn.setEnabled(flag);
		addStation3Btn.setEnabled(flag);
		addStation4Btn.setEnabled(flag);
		deployStation1Btn.setEnabled(flag);
		deployStation2Btn.setEnabled(flag);
		deployStation3Btn.setEnabled(flag);
		deployStation4Btn.setEnabled(flag);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		//ALL PASSENGER ADDERS
		if(arg0.getSource().equals(addStation1Btn)){
			
			c.station_wait_for_train(c.getStations().get(0));
			
		}else if(arg0.getSource().equals(addStation2Btn)){
			
			c.station_wait_for_train(c.getStations().get(1));
			
		}else if(arg0.getSource().equals(addStation3Btn)){
			
			c.station_wait_for_train(c.getStations().get(2));
			
		}else if(arg0.getSource().equals(addStation4Btn)){
			
			c.station_wait_for_train(c.getStations().get(3));
			
		}
		
		//ALL TRAIN DEPLOYERS
		else if(arg0.getSource().equals(deployStation1Btn)){
			
			int count = 0;
			try{
				count = Integer.parseInt(capacitySpinner.getValue().toString());
			}catch(Exception e){
				count = 10;
			}
			
			c.station_load_train(c.getStations().get(0), count);
			toggleButtons(false);
			
		}else if(arg0.getSource().equals(deployStation2Btn)){
			
			int count = 0;
			try{
				count = Integer.parseInt(capacitySpinner.getValue().toString());
			}catch(Exception e){
				count = 10;
			}
			
			c.station_load_train(c.getStations().get(1), count);
			toggleButtons(false);
			
		}else if(arg0.getSource().equals(deployStation3Btn)){
			
			int count = 0;
			try{
				count = Integer.parseInt(capacitySpinner.getValue().toString());
			}catch(Exception e){
				count = 10;
			}
			
			c.station_load_train(c.getStations().get(2), count);
			toggleButtons(false);
			
		}else if(arg0.getSource().equals(deployStation4Btn)){
			
			int count = 0;
			try{
				count = Integer.parseInt(capacitySpinner.getValue().toString());
			}catch(Exception e){
				count = 10;
			}
			
			c.station_load_train(c.getStations().get(3), count);
			toggleButtons(false);
			
		}
	}
}

