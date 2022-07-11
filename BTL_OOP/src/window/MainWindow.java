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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

public class MainWindow extends JFrame {
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

	private File file = new File("src//array.txt");
	private JPanel pnTool;
	private JPanel pnArray;
	private JLabel lbNum, lbMaxNum;
	private JSpinner spNum;
	private JButton btnCreateArray, btnDeleteArray, btnSetZero;
	private JButton btnRandom, btnByHand, btnOpenFile, btnReadFile;
	private JButton btnTeamInfo;
	private JPanel pnCode;
	private JSlider slSize;
	private JScrollPane pnScroll;
	private ActionListener eBubbleSort, eHeapSort, eQuickSort, eRadixSort;
	private ChangeListener eSize;
	private JList<String> lsCode;
	private JPanel pnAlgorithm;
	private JRadioButton rdBubbleSort, rdHeapSort, rdQuickSort, rdRadixSort;
	private ButtonGroup grSort;
	private JPanel pnControl;
	private JRadioButton rdIncrease, rdDecrease;
	private ActionListener eIncrease, eDecrease;
	public boolean isIncrease = true;
	private JButton btnSort, btnStop;
	private JSlider slSpeed;
	private ChangeListener eSpeed;
	private Thread sortingThread;
	public Element[] elements;

	public MainWindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		InitializeComponent();
	}

	private void InitializeComponent() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		setLookAndFeel();
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Mô phỏng thuật toán");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1376, 822);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbTitle = new JLabel("MÔ PHỎNG THUẬT TOÁN");
		lbTitle.setBackground(SystemColor.menu);
		lbTitle.setBounds(5, 5, 1286, 28);
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lbTitle);

		pnImitiate = new JPanel();
		pnImitiate.setBackground(SystemColor.menu);
		pnImitiate.setBorder(
				new TitledBorder(null, "Khung chạy mô phỏng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnImitiate.setBounds(5, 44, 1355, 430);
		contentPane.add(pnImitiate);
		pnImitiate.setLayout(null);
		initPanelTool();

		btnTeamInfo = new JButton("Info");
		btnTeamInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoWindow form = new InfoWindow();
				form.setVisible(true);
			}
		});
		btnTeamInfo.setBackground(SystemColor.activeCaption);
		btnTeamInfo.setBounds(1294, 5, 61, 31);
		contentPane.add(btnTeamInfo);

		setState(0);
		pnImitiate.repaint();
	}

	public static void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}
	}

	public void setState(int state) {
		switch (state) {
			case 0: // first state, haven't created arrays.
				btnCreateArray.setEnabled(true);
				btnDeleteArray.setEnabled(elements != null);
				btnSetZero.setEnabled(false);

				btnRandom.setEnabled(false);
				btnByHand.setEnabled(true);
				btnOpenFile.setEnabled(true);
				btnReadFile.setEnabled(true);

				rdBubbleSort.setEnabled(false);
				rdHeapSort.setEnabled(false);
				rdQuickSort.setEnabled(false);
				rdRadixSort.setEnabled(false);

				rdIncrease.setEnabled(false);
				rdDecrease.setEnabled(false);

				btnSort.setEnabled(false);
				btnStop.setEnabled(false);
				break;

			case 1: // created arrays, be waiting to set value arrays.
				btnDeleteArray.setEnabled(true);
				btnSetZero.setEnabled(true);

				btnRandom.setEnabled(true);
				btnByHand.setEnabled(true);
				btnOpenFile.setEnabled(true);
				btnReadFile.setEnabled(true);

				rdBubbleSort.setEnabled(true);
				rdHeapSort.setEnabled(true);
				rdQuickSort.setEnabled(true);
				rdRadixSort.setEnabled(true);

				rdIncrease.setEnabled(true);
				rdDecrease.setEnabled(true);

				btnSort.setEnabled(false);
				btnStop.setEnabled(false);
				break;

			case 2: // be set values, ready to sort
				btnDeleteArray.setEnabled(true);
				btnSetZero.setEnabled(true);

				btnRandom.setEnabled(true);

				rdBubbleSort.setEnabled(true);
				rdHeapSort.setEnabled(true);
				rdQuickSort.setEnabled(true);
				rdRadixSort.setEnabled(true);

				rdIncrease.setEnabled(true);
				rdDecrease.setEnabled(true);

				btnSort.setEnabled(true);
				btnStop.setEnabled(false);
				break;

			case 3: // sorting
				btnCreateArray.setEnabled(false);
				btnDeleteArray.setEnabled(false);
				btnSetZero.setEnabled(false);

				btnRandom.setEnabled(false);
				btnByHand.setEnabled(false);
				btnOpenFile.setEnabled(false);
				btnReadFile.setEnabled(false);

				rdIncrease.setEnabled(false);
				rdDecrease.setEnabled(false);

				rdBubbleSort.setEnabled(false);
				rdHeapSort.setEnabled(false);
				rdQuickSort.setEnabled(false);
				rdRadixSort.setEnabled(false);

				btnSort.setEnabled(false);
				btnStop.setEnabled(true);
				break;

			case 4: // sort done
				btnCreateArray.setEnabled(true);
				btnDeleteArray.setEnabled(true);
				btnSetZero.setEnabled(true);

				btnRandom.setEnabled(true);
				btnByHand.setEnabled(true);
				btnOpenFile.setEnabled(true);
				btnReadFile.setEnabled(true);

				rdBubbleSort.setEnabled(true);
				rdHeapSort.setEnabled(true);
				rdQuickSort.setEnabled(true);
				rdRadixSort.setEnabled(true);

				rdIncrease.setEnabled(true);
				rdDecrease.setEnabled(true);

				btnSort.setEnabled(true);
				btnStop.setEnabled(true);
				break;
			default:
				btnCreateArray.setEnabled(true);
				btnDeleteArray.setEnabled(false);
				btnSetZero.setEnabled(false);

				btnRandom.setEnabled(false);
				btnByHand.setEnabled(true);
				btnOpenFile.setEnabled(true);
				btnReadFile.setEnabled(true);

				rdBubbleSort.setEnabled(true);
				rdHeapSort.setEnabled(true);
				rdQuickSort.setEnabled(true);
				rdRadixSort.setEnabled(true);

				rdIncrease.setEnabled(true);
				rdDecrease.setEnabled(true);

				btnSort.setEnabled(false);
				btnStop.setEnabled(false);
		}
	}

	void initPanelTool() {
		pnTool = new JPanel();
		pnTool.setBounds(5, 475, 1350, 297);
		contentPane.add(pnTool);

		pnArray = new JPanel();
		pnArray.setBackground(SystemColor.menu);
		pnArray.setBorder(new TitledBorder(null, "Dữ liệu", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		pnCode = new JPanel();
		pnCode.setBackground(SystemColor.menu);
		pnCode.setBorder(new TitledBorder(null, "Code Java", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		pnAlgorithm = new JPanel();
		pnAlgorithm.setBackground(SystemColor.menu);
		pnAlgorithm.setBorder(new TitledBorder(null, "L\u1EF1a ch\u1ECDn thu\u1EADt to\u00E1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		pnControl = new JPanel();
		pnControl.setBackground(SystemColor.menu);
		pnControl.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
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
								.addComponent(pnControl, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)));
		gl_pnTool.setVerticalGroup(
				gl_pnTool.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnArray, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(pnCode, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(pnAlgorithm, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(pnControl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE));

		initPanelArrayContent();
		initPanelAlgorithm();
		initPanelCodeContent();
		initPanelControlContent();
		pnTool.setLayout(gl_pnTool);
	}

	void initPanelArrayContent() {
		pnCreateArray = new JPanel();
		pnCreateArray.setBackground(SystemColor.menu);
		pnCreateArray.setBorder(new TitledBorder(null, "Kh\u1EDFi t\u1EA1o m\u1EA3ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		pnSetValueArray = new JPanel();
		pnSetValueArray.setBackground(SystemColor.menu);
		pnSetValueArray.setBorder(new TitledBorder(null, "T\u1EA1o d\u1EEF li\u1EC7u m\u1EA3ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		GroupLayout gl_pnArray = new GroupLayout(pnArray);
		gl_pnArray.setHorizontalGroup(
				gl_pnArray.createParallelGroup(Alignment.LEADING)
						.addComponent(pnSetValueArray, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
						.addComponent(pnCreateArray, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE));
		gl_pnArray.setVerticalGroup(
				gl_pnArray.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_pnArray.createSequentialGroup()
								.addComponent(pnCreateArray, GroupLayout.PREFERRED_SIZE, 143,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(pnSetValueArray, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)));
		initPanelSetValueArrayContent();
		initPanelCreateArrayContent();
		pnArray.setLayout(gl_pnArray);

	}

	void initPanelSetValueArrayContent() {
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
					// file isn't existed
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
					for (int i = 0; i < elements.length; i++) {
						elements[i].setValue(array[i]);
					}
					InitSort();
					BaseSort.currentSort.UpdateSubElements();
					setState(2);
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

	void initPanelCreateArrayContent() {
		lbNum = new JLabel("Số phần tử của mảng\r\n:");
		lbNum.setBounds(16, 27, 139, 20);

		SpinnerModel sm = new SpinnerNumberModel(2, 2, 15, 1);
		spNum = new JSpinner(sm);
		spNum.setBounds(160, 25, 120, 25);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spNum.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

		btnCreateArray = new JButton("Tạo Mảng");
		btnCreateArray.setBackground(SystemColor.activeCaption);
		btnCreateArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createArray();
			}
		});
		btnCreateArray.setBounds(160, 59, 120, 25);

		btnDeleteArray = new JButton("Xóa Mảng");
		btnDeleteArray.setBackground(SystemColor.activeCaption);
		btnDeleteArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveAllElements();
				setState(0);
			}
		});
		btnDeleteArray.setBounds(160, 95, 120, 25);

		btnSetZero = new JButton("Đặt về 0");
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

	void initPanelAlgorithm() {
		// BubbleSort RadioButton
		pnAlgorithm.setLayout(null);
		rdBubbleSort = new JRadioButton("Bubble Sort");
		rdBubbleSort.setBounds(24, 44, 149, 23);
		rdBubbleSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnAlgorithm.add(rdBubbleSort);
		eBubbleSort = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				BaseSort.currentSort.RemoveSubElements();
				BaseSort.currentSort = new BubbleSort(pnImitiate);
				pnImitiate.repaint();
				// lsCode.setSelectedIndex(0);
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
				BaseSort.currentSort.RemoveSubElements();
				BaseSort.currentSort = new HeapSort(pnImitiate);
				pnImitiate.repaint();
				// lsCode.setSelectedIndex(0);
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
				BaseSort.currentSort.RemoveSubElements();
				BaseSort.currentSort = new QuickSort(pnImitiate);
				pnImitiate.repaint();
				// lsCode.setSelectedIndex(0);
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
				BaseSort.currentSort.RemoveSubElements();
				BaseSort.currentSort = new RadixSort(pnImitiate);
				pnImitiate.repaint();
				// lsCode.setSelectedIndex(0);
			}
		};
		this.rdRadixSort.addActionListener(eRadixSort);

		grSort = new ButtonGroup();
		grSort.add(rdBubbleSort);
		grSort.add(rdHeapSort);
		grSort.add(rdQuickSort);
		grSort.add(rdRadixSort);
		this.rdBubbleSort.setSelected(true);
	}

	void initPanelControlContent() {
		pnControl.setLayout(null);

		slSpeed = new JSlider();
		slSpeed.setOrientation(SwingConstants.VERTICAL);
		slSpeed.setBounds(267, 21, 32, 244);
		slSpeed.setMinimum(1);
		slSpeed.setMaximum(27);
		slSpeed.setValue(6);
		BaseSort.speed = 6;
		eSpeed = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				BaseSort.speed = slSpeed.getValue();
			}
		};
		slSpeed.addChangeListener(eSpeed);
		pnControl.add(slSpeed);

		rdIncrease = new JRadioButton("Sắp xếp tăng dần");
		rdIncrease.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdIncrease.setBounds(30, 42, 200, 23);
		eIncrease = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// set Increase or Decrease
				isIncrease = true;
				UpdateCode();
			}
		};
		rdIncrease.addActionListener(eIncrease);
		pnControl.add(rdIncrease);

		rdDecrease = new JRadioButton("Sắp xếp giảm dần");
		rdDecrease.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdDecrease.setBounds(30, 87, 200, 23);
		eDecrease = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// set Increase or Decrease
				isIncrease = false;
				UpdateCode();
			}
		};
		rdDecrease.addActionListener(eDecrease);
		pnControl.add(rdDecrease);

		ButtonGroup grDirect = new ButtonGroup();
		grDirect.add(rdDecrease);
		grDirect.add(rdIncrease);
		rdIncrease.setSelected(true);

		btnSort = new JButton("Sắp xếp");
		btnSort.setBackground(SystemColor.activeCaption);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start();
			}
		});
		btnSort.setBounds(52, 140, 120, 25);
		pnControl.add(btnSort);

		btnStop = new JButton("||");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Stop();
				} catch (Exception ex) {

				}
			}
		});
		btnStop.setBackground(SystemColor.activeCaption);
		btnStop.setBounds(52, 192, 120, 25);
		pnControl.add(btnStop);
	}

	void initPanelCodeContent() {
		slSize = new JSlider();
		slSize.setMinimum(13);
		slSize.setMaximum(20);
		slSize.setValue(0);
		slSize.setBounds(20, 21, 466, 26); // default 10, 21, 486, 26
		pnCode.add(slSize);

		pnScroll = new JScrollPane();
		pnScroll.setBounds(15, 53, 476, 223); // default 10, 53, 486, 223
		pnCode.add(pnScroll);
		lsCode = new JList<String>();
		lsCode.setBorder(new LineBorder(new Color(0, 0, 0)));
		lsCode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsCode.setFont(new Font("Monospaced", Font.BOLD, 14));
		lsCode.setBounds(20, 20, 466, 300);
		pnScroll.setViewportView(lsCode);

		eSize = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lsCode.setFont(new Font("Monospaced", Font.BOLD, slSize.getValue()));
				lsCode.repaint();
			}
		};
		slSize.addChangeListener(eSize);
		pnCode.setLayout(null);
	}

	void createArray() {
		int num = (Integer) spNum.getValue();
		InitArray(num);
		InitSort();
		setState(1);
	}

	public void createArray(int[] array) {
		InitArray(array.length);
		for (int i = 0; i < elements.length; i++) {
			elements[i].setValue(array[i]);
		}
		InitSort();
		setState(2);
	}

	void InitSort() {
		int type = getTypeSort();
		if (type == 0) {
			BaseSort.currentSort = new BubbleSort(this.pnImitiate);
		} else if (type == 1) {
			BaseSort.currentSort = new HeapSort(this.pnImitiate);
		} else if (type == 2) {
			BaseSort.currentSort = new QuickSort(this.pnImitiate);
		} else if (type == 3) {
			BaseSort.currentSort = new RadixSort(this.pnImitiate);
		} else {
			BaseSort.currentSort = new BaseSort(this.pnImitiate);
		}
	}

	void createArrayFirst() {
		System.out.println("Create Array First");
	}

	void createRandomArray() {
		if (elements == null) {
			createArrayFirst();
			return;
		}
		int maxValue = 1000;
		Random random = new Random();
		for (Element e : elements) {
			e.setValue(random.nextInt(maxValue));
		}
		BaseSort.currentSort.UpdateSubElements();
		setState(2);
	}

	public void InitArray(int size) {
		Dimension d = this.pnImitiate.getSize();
		Point pos = new Point((d.width - distance * (size - 1) - labelSize) / 2, 100);
		RemoveAllElements();
		elements = new Element[size];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = new Element(0);
			elements[i].setPosition(new Point(pos.x, pos.y));
			this.pnImitiate.add(elements[i].getLabel());
			pos.x += distance;
		}
		this.pnImitiate.repaint();
	}

	public void setZero() {
		if (elements == null)
			return;
		for (int i = 0; i < elements.length; i++) {
			elements[i].setValue(0);
		}
		BaseSort.currentSort.UpdateSubElements();
		setState(2);
		this.pnImitiate.repaint();
	}

	public void RemoveAllElements() {
		if (BaseSort.currentSort != null)
			BaseSort.currentSort.RemoveAllElements();
		this.elements = null;
		this.pnImitiate.repaint();
	}

	void Start() {
		setState(3);
		sortingThread = new Thread(new Runnable() {
			public void run() {
				try {
					BaseSort.currentSort.Sort();
				} catch (Exception e) {
				}
			}
		});
		sortingThread.start();
		this.pnImitiate.repaint();
	}

	void Stop() throws InterruptedException {
		this.sortingThread.stop();
		this.sortingThread = null;
		this.RemoveAllElements();
		HighlightRow(0);
		pnImitiate.repaint();
		setState(0);
	}

	public void End() {
		CompleteSortWindow wnd = new CompleteSortWindow();
		wnd.setVisible(true);
		setState(0);
	}

	int getTypeSort() {
		if (this.rdBubbleSort.isSelected()) {
			return 0;
		}
		if (this.rdHeapSort.isSelected()) {
			return 1;
		}
		if (this.rdQuickSort.isSelected()) {
			return 2;
		}
		if (this.rdRadixSort.isSelected()) {
			return 3;
		}
		return -1;
	}

	public void HighlightRow(int line) {
		lsCode.setSelectedIndex(line);
		lsCode.ensureIndexIsVisible(line);
		contentPane.repaint();
	}

	public void setCode(DefaultListModel<String> model) {
		lsCode.setModel(model);
		lsCode.setSelectedIndex(0);
	}

	public void UpdateCode() {
		BaseSort.currentSort.InitCode();
	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight() - 35) / 2);
		frame.setLocation(x, y);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainWindow();
					frame.setExtendedState(JFrame.NORMAL); // set JFrame full screen
					centreWindow(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
