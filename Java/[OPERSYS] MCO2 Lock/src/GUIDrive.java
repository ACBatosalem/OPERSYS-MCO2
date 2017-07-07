
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

public class GUIDrive extends JFrame implements MouseListener {
	private JPanel contentPane;

	public JPanel stationPanel;
	public JLabel stationLabel;
	public JPanel panel;
	public JLabel lblNewLabel;
	public JButton addStation1Btn;
	public JButton addStation2Btn;
	public JButton addStation4Btn;
	public JButton addStation3Btn;
	public JLabel lblDeployTrainAt;
	public JButton deployStation1Btn;
	public JPanel panel_1;
	public JTextArea submittedBy;
	public JPanel station1Panel;
	public JLabel station1Lbl;
	public JTextArea station1Status;
	public JPanel station3Panel;
	public JLabel station3Lbl;
	public JTextArea station3Status;
	public JPanel station2Panel;
	public JLabel station2Lbl;
	public JTextArea station2Status;
	public JPanel station4Panel;
	public JLabel station4Lbl;
	public JTextArea station4Status;
	public JTextArea station5Status;
	public JTextArea station6Status;
	public JTextArea station7Status;
	public JTextArea station8Status;
	public JSpinner capacitySpinner;
	public caltrain c;

	public ArrayList<JTextArea> stationList;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JPanel station5Panel;
	private JLabel lblStation;
	private JScrollPane scrollPane_4;
	private JPanel station6Panel;
	private JLabel lblStation_1;
	private JScrollPane scrollPane_5;
	private JPanel station7Panel;
	private JLabel lblStation_2;
	private JScrollPane scrollPane_6;
	private JPanel station8Panel;
	private JLabel lblStation_3;
	private JScrollPane scrollPane_7;
	private JButton addStation5Btn;
	private JButton addStation6Btn;
	private JButton addStation8Btn;
	private JButton addStation7Btn;
	private JLabel lblNewLabel_2;

	public static void main(String[] args) {
		GUIDrive gui;
		caltrain c;

		gui = new GUIDrive();

		c = new caltrain();
		c.station_init(); // add's 2nd station to the caltrain system
		c.station_init(); // add's 3nd station to the caltrain system
		c.station_init(); // add's 4th station to the caltrain system
		c.station_init(); // add's 5th station to the caltrain system
		c.station_init(); // add's 6th station to the caltrain system
		c.station_init(); // add's 7th station to the caltrain system
		c.station_init(); // add's 8th station to the caltrain system

		c.setGUI(gui);
		gui.setLogic(c);

		gui.setVisible(true);

		System.out.println("PROCESS SYNCHRONIZATION WITH LOCKS");

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
		stationList.add(station5Status);
		stationList.add(station6Status);
		stationList.add(station7Status);
		stationList.add(station8Status);

		
	}

	public void setLogic(caltrain c) {
		this.c = c;
	}

	public void initComponents() {
		setTitle("PROCESS SYNCHRONIZATION WITH LOCKS");

		stationPanel = new JPanel();
		stationLabel = new JLabel("Stations");
		panel = new JPanel();
		lblNewLabel = new JLabel("Add Passengers At");
		addStation1Btn = new JButton("Station 1");
		addStation2Btn = new JButton("Station 2");
		addStation4Btn = new JButton("Station 4");
		addStation3Btn = new JButton("Station 3");
		lblDeployTrainAt = new JLabel("Deploy Train Details");
		deployStation1Btn = new JButton("Deploy!");
		capacitySpinner = new JSpinner();
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
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		stationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		stationPanel.setBackground(new Color(0, 128, 0));
		stationPanel.setBounds(10, 11, 203, 695);
		contentPane.add(stationPanel);
		stationPanel.setLayout(null);

		stationLabel.setForeground(new Color(255, 255, 255));
		stationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stationLabel.setFont(new Font("Josefin Sans", Font.PLAIN, 20));
		stationLabel.setBounds(10, 11, 183, 26);
		stationPanel.add(stationLabel);

		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(new Color(34, 139, 34));
		panel.setBounds(10, 37, 183, 652);
		stationPanel.add(panel);
		panel.setLayout(null);

		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Josefin Sans", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 146, 20);
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
		lblDeployTrainAt.setBounds(10, 226, 146, 20);
		panel.add(lblDeployTrainAt);

		deployStation1Btn.setFont(new Font("Josefin Sans", Font.PLAIN, 10));
		deployStation1Btn.setBounds(64, 301, 73, 23);
		deployStation1Btn.addMouseListener(this);
		panel.add(deployStation1Btn);

		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setBounds(10, 502, 163, 133);
		panel.add(panel_1);
		panel_1.setLayout(null);

		submittedBy.setForeground(new Color(255, 255, 255));
		submittedBy.setBackground(new Color(0, 128, 0));
		submittedBy.setText(
				"Submitted By:\r\n\r\n Gaba, Janelle Marie\r\n Ngo, Jan Bertel \r\n Ticug, Jonal Ray \r\nOPERSYS S21 Grp11");
		submittedBy.setFont(new Font("Josefin Sans", Font.PLAIN, 15));
		submittedBy.setBounds(10, 11, 143, 111);
		panel_1.add(submittedBy);

		lblNewLabel_1 = new JLabel("Capacity: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 257, 83, 23);
		panel.add(lblNewLabel_1);

		capacitySpinner.setModel(new SpinnerNumberModel(new Integer(5), new Integer(1), null, new Integer(1)));
		capacitySpinner.setBounds(100, 257, 73, 20);
		panel.add(capacitySpinner);

		addStation5Btn = new JButton("Station 5");
		addStation5Btn.setFont(new Font("Dialog", Font.PLAIN, 10));
		addStation5Btn.setBounds(10, 111, 73, 23);
		addStation5Btn.addMouseListener(this);
		panel.add(addStation5Btn);

		addStation6Btn = new JButton("Station 6");
		addStation6Btn.setFont(new Font("Dialog", Font.PLAIN, 10));
		addStation6Btn.setBounds(100, 111, 73, 23);
		addStation6Btn.addMouseListener(this);
		panel.add(addStation6Btn);

		addStation8Btn = new JButton("Station 8");
		addStation8Btn.setFont(new Font("Dialog", Font.PLAIN, 10));
		addStation8Btn.setBounds(100, 146, 73, 23);
		addStation8Btn.addMouseListener(this);
		panel.add(addStation8Btn);

		addStation7Btn = new JButton("Station 7");
		addStation7Btn.setFont(new Font("Dialog", Font.PLAIN, 10));
		addStation7Btn.setBounds(10, 146, 73, 23);
		addStation7Btn.addMouseListener(this);
		panel.add(addStation7Btn);

		JLabel lblTrainsDeployed = new JLabel("Trains Deployed");
		lblTrainsDeployed.setBounds(10, 370, 146, 20);
		panel.add(lblTrainsDeployed);
		lblTrainsDeployed.setForeground(Color.WHITE);
		lblTrainsDeployed.setFont(new Font("Dialog", Font.BOLD, 13));

		lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(20, 407, 109, 76);
		panel.add(lblNewLabel_2);

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
		station3Panel.setBounds(799, 11, 277, 224);
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
		station4Panel.setBounds(225, 246, 277, 224);
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
		
		station5Panel = new JPanel();
		station5Panel.setLayout(null);
		station5Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station5Panel.setBackground(new Color(0, 128, 0));
		station5Panel.setBounds(510, 246, 277, 224);
		contentPane.add(station5Panel);

		lblStation = new JLabel("Station 5");
		lblStation.setHorizontalAlignment(SwingConstants.CENTER);
		lblStation.setForeground(Color.WHITE);
		lblStation.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblStation.setBounds(10, 11, 257, 26);
		station5Panel.add(lblStation);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 48, 257, 165);
		station5Panel.add(scrollPane_4);
		station5Status = new JTextArea();
		station5Status.setEditable(false);
		scrollPane_4.setViewportView(station5Status);

		station6Panel = new JPanel();
		station6Panel.setLayout(null);
		station6Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station6Panel.setBackground(new Color(0, 128, 0));
		station6Panel.setBounds(799, 246, 277, 224);
		contentPane.add(station6Panel);
		

		lblStation_1 = new JLabel("Station 6");
		lblStation_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStation_1.setForeground(Color.WHITE);
		lblStation_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblStation_1.setBounds(10, 11, 257, 26);
		station6Panel.add(lblStation_1);
		
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 48, 257, 165);
		station6Panel.add(scrollPane_5);
		station6Status = new JTextArea();
		station6Status.setEditable(false);
		scrollPane_5.setViewportView(station6Status);

		station7Panel = new JPanel();
		station7Panel.setLayout(null);
		station7Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station7Panel.setBackground(new Color(0, 128, 0));
		station7Panel.setBounds(368, 482, 277, 224);
		contentPane.add(station7Panel);

		lblStation_2 = new JLabel("Station 7");
		lblStation_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblStation_2.setForeground(Color.WHITE);
		lblStation_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblStation_2.setBounds(10, 11, 257, 26);
		station7Panel.add(lblStation_2);

		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 48, 257, 165);
		station7Panel.add(scrollPane_6);
		station7Status = new JTextArea();
		station7Status.setEditable(false);
		scrollPane_6.setViewportView(station7Status);

		station8Panel = new JPanel();
		station8Panel.setLayout(null);
		station8Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		station8Panel.setBackground(new Color(0, 128, 0));
		station8Panel.setBounds(658, 482, 277, 224);
		contentPane.add(station8Panel);

		lblStation_3 = new JLabel("Station 8");
		lblStation_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblStation_3.setForeground(Color.WHITE);
		lblStation_3.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblStation_3.setBounds(10, 11, 257, 26);
		station8Panel.add(lblStation_3);

		scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(10, 48, 257, 165);
		station8Panel.add(scrollPane_7);
		station8Status = new JTextArea();
		station8Status.setEditable(false);
		scrollPane_7.setViewportView(station8Status);
	}

	public void toggleButtons(boolean flag) {
		addStation1Btn.setEnabled(flag);
		addStation2Btn.setEnabled(flag);
		addStation3Btn.setEnabled(flag);
		addStation4Btn.setEnabled(flag);
		addStation5Btn.setEnabled(flag);
		addStation6Btn.setEnabled(flag);
		addStation7Btn.setEnabled(flag);
		addStation8Btn.setEnabled(flag);
		deployStation1Btn.setEnabled(flag);

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

		// ALL PASSENGER ADDERS
		if (arg0.getSource().equals(addStation1Btn)) {

			c.station_wait_for_train(c.getStations().get(0));

		} else if (arg0.getSource().equals(addStation2Btn)) {

			c.station_wait_for_train(c.getStations().get(1));

		} else if (arg0.getSource().equals(addStation3Btn)) {

			c.station_wait_for_train(c.getStations().get(2));

		} else if (arg0.getSource().equals(addStation4Btn)) {

			c.station_wait_for_train(c.getStations().get(3));

		} else if (arg0.getSource().equals(addStation5Btn)) {

			c.station_wait_for_train(c.getStations().get(4));

		} else if (arg0.getSource().equals(addStation6Btn)) {

			c.station_wait_for_train(c.getStations().get(5));

		} else if (arg0.getSource().equals(addStation7Btn)) {

			c.station_wait_for_train(c.getStations().get(6));

		} else if (arg0.getSource().equals(addStation8Btn)) {

			c.station_wait_for_train(c.getStations().get(7));

		}

		// ALL TRAIN DEPLOYERS
		else if (arg0.getSource().equals(deployStation1Btn)) {

			int count = 0;
			try {
				count = Integer.parseInt(capacitySpinner.getValue().toString());
			} catch (Exception e) {
				count = 10;
			}

			c.station_load_train(c.getStations().get(0), count);
			toggleButtons(false);

		}
	}
}
