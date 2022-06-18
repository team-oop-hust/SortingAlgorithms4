package window;

import java.awt.*;
import base.*;
import java.awt.Dialog.ModalExclusionType;
import sorting_algorithm.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame{

	
	private static MainWindow frame;
	
	private JPanel contentPane;
	private JPanel pnImitiate;
	private JLabel lbTitle;
	private JLabel lbPoint1 = new JLabel();
	private JLabel lbPoint2 = new JLabel();
	private JLabel lbPointM = new JLabel();
	
	
	
	public MainWindow()
	{
		InitializeComponent();
	}
	
	private void InitializeComponent()
	{
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("M\u00F4 ph\u1ECFng thu\u1EADt to\u00E1n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1376, 742);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbTitle = new JLabel("M\u00D4 PH\u1ECENG THU\u1EACT TO\u00C1N");
		lbTitle.setBackground(SystemColor.menu);
		lbTitle.setBounds(5, 5, 1286, 28);
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lbTitle);
		
		pnImitiate = new JPanel();
		pnImitiate.setBackground(SystemColor.menu);
		pnImitiate.setBorder(new TitledBorder(null, "Khung ch\u1EA1y m\u00F4 ph\u1ECFng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnImitiate.setBounds(5, 44, 1355, 360);
		contentPane.add(pnImitiate);
		pnImitiate.setLayout(null);
		
		lbPoint1.setSize(50, 25);
        lbPoint1.setOpaque(true);
        lbPoint1.setLocation(50, 50);
        lbPoint1.setFont(new Font("Helvetica", Font.BOLD, 17));
        lbPoint1.setBackground(SystemColor.menu);
        lbPoint1.setHorizontalAlignment(SwingConstants.CENTER);
        lbPoint1.setVerticalAlignment(SwingConstants.CENTER);
        pnImitiate.add(lbPoint1);
        pnImitiate.add(lbPoint2);
        lbPoint2.setSize(50, 25);
        lbPoint2.setOpaque(true);
        lbPoint2.setLocation(50, 50);
        lbPoint2.setFont(new Font("Helvetica", Font.BOLD, 17));
        lbPoint2.setBackground(SystemColor.menu);
        lbPoint2.setHorizontalAlignment(SwingConstants.CENTER);
        lbPoint2.setVerticalAlignment(SwingConstants.CENTER);
        pnImitiate.add(lbPointM);
        lbPointM.setSize(50, 25);
        lbPointM.setOpaque(true);
        lbPointM.setLocation(50, 50);
        lbPointM.setFont(new Font("Helvetica", Font.BOLD, 17));
        lbPointM.setBackground(SystemColor.menu);
        lbPointM.setHorizontalAlignment(SwingConstants.CENTER);
        lbPointM.setVerticalAlignment(SwingConstants.CENTER);
        Test();
        pnImitiate.repaint();
	}
	
	void Test()
	{
		RadixSort rs = new RadixSort(pnImitiate);
		Thread thread = new Thread(new Runnable() {
        	public void run() 
        	{
        		try
        		{
        			rs.radixsort();
        		}
        		catch(Exception e){
        		}
        	}
        });
        thread.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainWindow();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //set JFrame full screen
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
