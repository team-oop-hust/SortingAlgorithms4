package window;

import java.awt.*;
import base.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import sorting_algorithm.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

public class MainWindow extends JFrame{

	
	public static MainWindow frame;
	
	final int speed = 10;
	final int distance = 80;
	final int labelSize = 50;
	final int delayFrame = 10; 
	
	private JPanel contentPane;
	private JPanel pnImitiate;
	private JPanel pnCreateArray;
	private JPanel pnSetValueArray;
	
	private JLabel lbTitle;
	private JLabel lbPoint1 = new JLabel();
	private JLabel lbPoint2 = new JLabel();
	private JLabel lbPointM = new JLabel();
	
	private JPanel pnTool;
	private JPanel pnArray;
	private JLabel lbNum, lbMaxNum;
	private JSpinner spNum;
	private JButton btnCreateArray, btnDeleteArray, btnSetZero;
	private JButton btnRandom, btnByHand, btnOpenFile, btnReadFile;
	private JPanel pnCode;
	private JSlider slSize;
	private JScrollPane pnScroll; 
	private DefaultListModel<String> model;
	private ActionListener eInterchangeSort, eSelectionSort, eBubbleSort, eInsertionSort, eShellSort, eHeapSort, eQuickSort, eMergeSort;
	private ChangeListener eSize;
	private JList<String> lsCode;
	private JPanel pnAlgorithm;
	private JRadioButton rdInterchangeSort, rdSelectionSort, rdBubbleSort, rdInsertionSort, rdShellSort, rdHeapSort, rdQuickSort, rdMergeSort;
	private ButtonGroup grSort;
	private JPanel pnControl;
	private JRadioButton rdIncrease, rdDecrease;
	private ActionListener eIncrease, eDecrease;
	private boolean isIncrease = true;
	private JButton btnSort, btnStop;
	private JSlider slSpeed;
    private ChangeListener eSpeed;
	
    public Element[] elements;
	
	
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
		initPanelTool();  
        pnImitiate.repaint();
	}
	
	void initPanelTool()
	{
		pnTool = new JPanel();
		pnTool.setBounds(5, 415, 1350, 287);
		pnTool.setBorder(new TitledBorder(null, "Tool", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnTool);
		
		pnArray = new JPanel();
		pnArray.setBackground(SystemColor.menu);
		pnArray.setBorder(new TitledBorder(null, "D\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnCode = new JPanel();
		pnCode.setBackground(SystemColor.menu);
		pnCode.setBorder(new TitledBorder(null, "Code C/C++", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnAlgorithm = new JPanel();
		pnAlgorithm.setBackground(SystemColor.menu);
		pnAlgorithm.setBorder(new TitledBorder(null, "L\u1EF1a ch\u1ECDn thu\u1EADt to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnControl = new JPanel();
		pnControl.setBackground(SystemColor.menu);
		pnControl.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_pnTool = new GroupLayout(pnTool);
		gl_pnTool.setHorizontalGroup(
			gl_pnTool.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTool.createSequentialGroup()
					.addComponent(pnArray, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnCode, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnAlgorithm, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnControl, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
		);
		gl_pnTool.setVerticalGroup(
			gl_pnTool.createParallelGroup(Alignment.TRAILING)
				.addComponent(pnArray, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
				.addComponent(pnCode, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
				.addComponent(pnAlgorithm, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
				.addComponent(pnControl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
		);
		pnControl.setLayout(null);
		
		initPanelArrayContent();
		pnTool.setLayout(gl_pnTool);
	}
	
	void initPanelArrayContent()
	{
		pnCreateArray = new JPanel();
		pnCreateArray.setBackground(SystemColor.menu);
		pnCreateArray.setBorder(new TitledBorder(null, "Kh\u1EDFi t\u1EA1o m\u1EA3ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnSetValueArray = new JPanel();
		pnSetValueArray.setBackground(SystemColor.menu);
		pnSetValueArray.setBorder(new TitledBorder(null, "T\u1EA1o d\u1EEF li\u1EC7u m\u1EA3ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		GroupLayout gl_pnArray = new GroupLayout(pnArray);
		gl_pnArray.setHorizontalGroup(
			gl_pnArray.createParallelGroup(Alignment.LEADING)
				.addComponent(pnSetValueArray, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
				.addComponent(pnCreateArray, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
		);
		gl_pnArray.setVerticalGroup(
			gl_pnArray.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnArray.createSequentialGroup()
					.addComponent(pnCreateArray, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnSetValueArray, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
		);
		initPanelSetValueArrayContent();
		initPanelCreateArrayContent();
		pnArray.setLayout(gl_pnArray);
		
		
	}
	
	void initPanelSetValueArrayContent()
	{
		btnRandom = new JButton("Ng\u1EABu nhi\u00EAn");
		btnRandom.setBackground(SystemColor.activeCaption);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				createRandom();
			}
		});
		btnRandom.setBounds(15, 27, 120, 25);
		
		btnByHand = new JButton("B\u1EB1ng tay");
		btnByHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				FormInput formInput = new FormInput();
//				formInput.setVisible(true);
//				setState(3);
			}
		});
		btnByHand.setBackground(SystemColor.activeCaption);
		btnByHand.setBounds(160, 27, 120, 25);
		
		btnOpenFile = new JButton("M\u1EDF file");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					Desktop desktop = null;
//				    if (Desktop.isDesktopSupported()) {
//				    	desktop = Desktop.getDesktop();
//				    }
//				    desktop.open(file);
//				} catch (IOException ioe) {
//					//file isn't existed
//				    ioe.printStackTrace();				   
//				}
			}
		});
		btnOpenFile.setBackground(SystemColor.activeCaption);
		btnOpenFile.setBounds(15, 61, 120, 25);
		
		btnReadFile = new JButton("\u0110\u1ECDc file");
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				deleteArrays();
//				try {
//					Scanner in = new Scanner(file);
//					num = Integer.parseInt(in.nextLine());
//					array = new int[num];
//					int pos = 0;
//					while (in.hasNextLine()) {
//						array[pos] = Integer.parseInt(in.nextLine());
//						pos++;
//					}
//					in.close();
//					lbArrays = new JLabel[num];
//				
//					for (int i = 0; i < num; i++) {
//						//create label, set text "0"
//					
//						lbArrays[i] = new JLabel(String.valueOf(array[i]));
//						pnImitiate.add(lbArrays[i]);
//						
//						//set size label
//						lbArrays[i].setSize(50,50);
//						lbArrays[i].setOpaque(true);
//						lbArrays[i].setForeground(Color.BLUE);
//						
//						//set location label
//						if (i == 0)
//							lbArrays[i].setLocation(((int) ((18 - num) * 0.5) * 70) + 100, 150);
//						else
//							lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);
//						
//						//set fonts
//						lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));
//						
//						//set background color
//						lbArrays[i].setBackground(SystemColor.inactiveCaption);
//						
//						//set text alignment center
//						lbArrays[i].setHorizontalAlignment(SwingConstants.CENTER); 
//						lbArrays[i].setVerticalAlignment(SwingConstants.CENTER);
//					}
//					pnImitiate.setVisible(true);
//					pnImitiate.validate();
//					pnImitiate.repaint();
//					setState(2);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
		btnReadFile.setBackground(SystemColor.activeCaption);
		btnReadFile.setBounds(160, 61, 120, 25);
		pnSetValueArray.setLayout(null);
		pnSetValueArray.add(btnOpenFile);
		pnSetValueArray.add(btnRandom);
		pnSetValueArray.add(btnReadFile);
		pnSetValueArray.add(btnByHand);
	}
	
	void initPanelCreateArrayContent()
	{
		lbNum = new JLabel("S\u1ED1 ph\u1EA7n t\u1EED m\u1EA3ng\r\n:");
		lbNum.setBounds(16, 27, 139, 20);
		
		SpinnerModel sm = new SpinnerNumberModel(2, 2, 15, 1);
		spNum = new JSpinner(sm);
		spNum.setBounds(160, 25, 120, 25);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spNum.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		
		btnCreateArray = new JButton("T\u1EA1o m\u1EA3ng");
		btnCreateArray.setBackground(SystemColor.activeCaption);
		btnCreateArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createArrays();
			}
		});
		btnCreateArray.setBounds(160, 59, 120, 25);
		
		btnDeleteArray = new JButton("X\u00F3a m\u1EA3ng");
		btnDeleteArray.setBackground(SystemColor.activeCaption);
		btnDeleteArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveAllElements();		
				setState(0);
			}
		});
		btnDeleteArray.setBounds(160, 95, 120, 25);
		
		btnSetZero = new JButton("\u0110\u1EB7t v\u1EC1 0");
		btnSetZero.setBackground(SystemColor.activeCaption);
		btnSetZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setZero();
			}
		});
		btnSetZero.setBounds(15, 95, 120, 25);
		pnCreateArray.setLayout(null);
		pnCreateArray.add(lbNum);
		pnCreateArray.add(btnSetZero);
		pnCreateArray.add(btnCreateArray);
		pnCreateArray.add(spNum);
		pnCreateArray.add(btnDeleteArray);
		
		lbMaxNum = new JLabel("(T\u1ED1i \u0111a 15)");
		lbMaxNum.setHorizontalAlignment(SwingConstants.CENTER);
		lbMaxNum.setBounds(10, 47, 109, 14);
		pnCreateArray.add(lbMaxNum);
	}
	
//	void Test()
//	{
//		QuickSort rs = new QuickSort(pnImitiate);
//		Thread thread = new Thread(new Runnable() {
//        	public void run() 
//        	{
//        		try
//        		{
//        			rs.Sort();
//        		}
//        		catch(Exception e){
//        		}
//        	}
//        });
//        thread.start();
//	}
	
	void createArrays()
	{
		InitRandomArray(10, 100);
		if(BaseSort.currentSort == null)
		{
			BaseSort.currentSort = new BaseSort(this.pnImitiate);
		}
	}
	
	public void InitRandomArray(int size, int maxValue)
	{
		Dimension d = this.pnImitiate.getSize();
		Point pos = new Point((d.width - distance * (size - 1) - labelSize) / 2, 100);
		RemoveAllElements();
		elements = new Element[size];
		Random random = new Random();
		for(int i = 0 ; i < elements.length; i++)
		{
			elements[i] = new Element(random.nextInt(maxValue));
			elements[i].setPosition(new Point(pos.x, pos.y));
			this.pnImitiate.add(elements[i].getLabel());
			pos.x += distance;
		}
		this.pnImitiate.repaint();
	}
	
	public void setZero()
	{
		QuickSort rs = new QuickSort(pnImitiate);
		Thread thread = new Thread(new Runnable() {
        	public void run() 
        	{
        		try
        		{
        			rs.Sort();
        		}
        		catch(Exception e){
        		}
        	}
        });
        thread.start();
        
        this.pnImitiate.repaint();
//		if(elements == null)
//			return;
//		for(int i = 0 ; i < elements.length; i++)
//		{
//			elements[i].setValue(0);
//		}
	}
	
	public void RemoveAllElements()
	{
		if(BaseSort.currentSort != null)
			BaseSort.currentSort.RemoveAllElements();
		this.pnImitiate.repaint();
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
