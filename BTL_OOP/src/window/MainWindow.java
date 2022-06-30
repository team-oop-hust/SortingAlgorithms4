package window;

import java.awt.*;
import base.*;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
	
	private File file = new File ("src//array.txt");
	private JPanel pnTool;
	private JPanel pnArray;
	private JLabel lbNum, lbMaxNum;
	private JSpinner spNum;
	private JButton btnCreateArray, btnDeleteArray, btnSetZero;
	private JButton btnRandom, btnByHand, btnOpenFile, btnReadFile;
	private JPanel pnCode;
	private ActionListener eBubbleSort, eHeapSort, eQuickSort, eRadixSort;
	private JPanel pnAlgorithm;
	private JRadioButton rdBubbleSort, rdHeapSort, rdQuickSort, rdRadixSort;
	private ButtonGroup grSort;
	private JPanel pnControl;
	private Thread sortingThread;
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
		setBounds(100, 100, 1376, 782);
		
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
		pnImitiate.setBounds(5, 44, 1355, 430);
		contentPane.add(pnImitiate);
		pnImitiate.setLayout(null);
		initPanelTool();  
        pnImitiate.repaint();
	}
	
	void initPanelTool()
	{
		pnTool = new JPanel();
		pnTool.setBounds(5, 455, 1350, 287);
		pnTool.setBorder(new TitledBorder(null, "Tool", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnTool);
		
		pnArray = new JPanel();
		pnArray.setBackground(SystemColor.menu);
		pnArray.setBorder(new TitledBorder(null, "D\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnCode = new JPanel();
		pnCode.setBackground(SystemColor.menu);
		pnCode.setBorder(new TitledBorder(null, "Code Java", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
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
		initPanelAlgorithm();
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
				createRandomArray();
			}
		});
		btnRandom.setBounds(15, 27, 120, 25);
		
		btnByHand = new JButton("B\u1EB1ng tay");
		btnByHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputWindow inputWindow = new InputWindow();
				inputWindow.setVisible(true);
			}
		});
		btnByHand.setBackground(SystemColor.activeCaption);
		btnByHand.setBounds(160, 27, 120, 25);
		
		btnOpenFile = new JButton("M\u1EDF file");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop desktop = null;
				    if (Desktop.isDesktopSupported()) {
				    	desktop = Desktop.getDesktop();
				    }
				    desktop.open(file);
				} catch (IOException ioe) {
					//file isn't existed
				    ioe.printStackTrace();				   
				}
			}
		});
		btnOpenFile.setBackground(SystemColor.activeCaption);
		btnOpenFile.setBounds(15, 61, 120, 25);
		
		btnReadFile = new JButton("\u0110\u1ECDc file");
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveAllElements();
				try {
					Scanner in = new Scanner(file);
					int num = Integer.parseInt(in.nextLine());
					int[] array = new int[num];
					int pos = 0;
					while (in.hasNextLine()) {
						array[pos] = Integer.parseInt(in.nextLine());
						pos++;
					}
					in.close();
					InitArray(array.length);
					for(int i = 0; i < elements.length; i++)
					{
						elements[i].setValue(array[i]);
					}
					
					pnImitiate.setVisible(true);
					pnImitiate.validate();
					pnImitiate.repaint();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				createArray();
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
		
		btnSetZero = new JButton("Sắp xếp");
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
	
	void initPanelAlgorithm()
	{
		//BubbleSort RadioButton
		pnAlgorithm.setLayout(null);
		rdBubbleSort = new JRadioButton("Bubble Sort");
		rdBubbleSort.setBounds(24, 44, 149, 23);
		rdBubbleSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnAlgorithm.add(rdBubbleSort);
		eBubbleSort = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  if(BaseSort.currentSort == null)
		    	  {
		    		  createArrayFirst();
		    		  return;
		    	  }
		    	  BaseSort.currentSort = new BubbleSort(pnImitiate);
		    	  //lsCode.setSelectedIndex(0);
		      }
		};
		this.rdBubbleSort.addActionListener(eBubbleSort);
		
		// HeapSort RadioButton
		rdHeapSort = new JRadioButton("Heap Sort");
		rdHeapSort.setBounds(24, 94, 149, 23);
		rdHeapSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnAlgorithm.add(rdHeapSort);
		eHeapSort = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  if(BaseSort.currentSort == null)
		    	  {
		    		  createArrayFirst();
		    		  return;
		    	  }
		    	  BaseSort.currentSort = new HeapSort(pnImitiate);
		    	  //lsCode.setSelectedIndex(0);
		      }
		};
		this.rdHeapSort.addActionListener(eHeapSort);
		
		// QuickSort RadioButton
		rdQuickSort = new JRadioButton("Quick Sort");
		rdQuickSort.setBounds(24, 144, 149, 23);
		rdQuickSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnAlgorithm.add(rdQuickSort);
		eQuickSort = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  if(BaseSort.currentSort == null)
		    	  {
		    		  createArrayFirst();
		    		  return;
		    	  }
		    	  BaseSort.currentSort = new QuickSort(pnImitiate);
		    	  //lsCode.setSelectedIndex(0);
		      }
		};
		this.rdQuickSort.addActionListener(eQuickSort);
		
		// RadixSort RadioButton
		rdRadixSort = new JRadioButton("Radix Sort");
		rdRadixSort.setBounds(24, 194, 149, 23);
		rdRadixSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnAlgorithm.add(rdRadixSort);
		eRadixSort = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  if(BaseSort.currentSort == null)
		    	  {
		    		  createArrayFirst();
		    		  return;
		    	  }
		    	  BaseSort.currentSort = new RadixSort(pnImitiate);
		    	  //lsCode.setSelectedIndex(0);
		      }
		};
		this.rdRadixSort.addActionListener(eRadixSort);
		
		grSort = new ButtonGroup();
		grSort.add(rdBubbleSort);
		grSort.add(rdHeapSort);
		grSort.add(rdQuickSort);
		grSort.add(rdRadixSort);
	}
	
	void createArray()
	{
		int num = (Integer)spNum.getValue();
		InitArray(num);
		if(BaseSort.currentSort == null)
		{
			BaseSort.currentSort = new BaseSort(this.pnImitiate);
		}
	}
	
	public void createArray(int[] array)
	{
		InitArray(array.length);
		for(int i = 0; i < elements.length; i++)
		{
			elements[i].setValue(array[i]);
		}
		
		if(BaseSort.currentSort == null)
		{
			BaseSort.currentSort = new BaseSort(this.pnImitiate);
		}
	}
	
	void createArrayFirst()
	{
		System.out.println("Create Array First");
	}

	void createRandomArray()
	{
		if(elements == null)
		{
			createArrayFirst();
			return;
		}
		int maxValue = 1000;
		Random random = new Random();
		for(Element e : elements)
		{
			e.setValue(random.nextInt(maxValue));
		}
	}
	
//	public void InitRandomArray(int size, int maxValue)
//	{
//		Dimension d = this.pnImitiate.getSize();
//		Point pos = new Point((d.width - distance * (size - 1) - labelSize) / 2, 100);
//		RemoveAllElements();
//		elements = new Element[size];
//		Random random = new Random();
//		for(int i = 0 ; i < elements.length; i++)
//		{
//			elements[i] = new Element(random.nextInt(maxValue));
//			elements[i].setPosition(new Point(pos.x, pos.y));
//			this.pnImitiate.add(elements[i].getLabel());
//			pos.x += distance;
//		}
//		this.pnImitiate.repaint();
//	}
	
	public void InitArray(int size)
	{
		Dimension d = this.pnImitiate.getSize();
		Point pos = new Point((d.width - distance * (size - 1) - labelSize) / 2, 100);
		RemoveAllElements();
		elements = new Element[size];
		for(int i = 0 ; i < elements.length; i++)
		{
			elements[i] = new Element(0);
			elements[i].setPosition(new Point(pos.x, pos.y));
			this.pnImitiate.add(elements[i].getLabel());
			pos.x += distance;
		}
		this.pnImitiate.repaint();
	}
	
	public void setZero()
	{
		sortingThread = new Thread(new Runnable() {
        	public void run() 
        	{
        		try
        		{
        			BaseSort.currentSort.Sort();
        		}
        		catch(Exception e){
        		}
        	}
        });
		sortingThread.start();
        
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
		this.elements = null;
		BaseSort.currentSort = null;
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
