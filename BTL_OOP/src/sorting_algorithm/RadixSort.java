package sorting_algorithm;

import base.BaseSort;
import base.Element;
import window.MainWindow;
import java.awt.Font;
import java.awt.Point;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class RadixSort extends BaseSort {

	Point[] bucketPos;
	Point[] arrayPos;
	JLabel[] labelBucket;

	public RadixSort(JPanel container) {
		super(container);
		Init();
	}

	@Override
	public void Init() {
		initBucketPos();
		initArrayPos();
	}

	@Override
	public void InitCode() {
		boolean isIncrease = MainWindow.frame.isIncrease;
		model = new DefaultListModel<String>();
		model.addElement("void RadixSort(int arr[]) {");// 0
		model.addElement("     int n = arr.length;");// 1
		model.addElement("     int m = getMax(arr, n)");// 2
		model.addElement("     for (int exp = 1; m / exp > 0; exp *= 10)");// 3
		model.addElement("          countSort(arr, exp);");// 4
		model.addElement("}");// 5
		model.addElement("");// 6
		model.addElement("void countSort(int arr[], int exp) {");// 7
		model.addElement("     int n = arr.length;");// 8
		model.addElement("     int output[] = new int[n];");// 9
		model.addElement("     int count[] = new int[10];");// 10
		model.addElement("     for (int i = 0; i < n; i++) ");// 11
		model.addElement("          count[(arr[i] / exp) % 10]++;");// 12
		model.addElement("     for (int i = 0; i < n; i++) ");// 13
		model.addElement("          count[i] += count[i - 1];");// 14
		if (isIncrease)
			model.addElement("     for (i = n - 1; i >= 0; i--) {");// 15
		else
			model.addElement("     for (i = 0; i < n; i++) {");// 15
		model.addElement("          output[count[(arr[i] / exp) % 10] - 1] = arr[i];");// 16
		model.addElement("          count[(arr[i] / exp) % 10]--;");// 17
		model.addElement("     }");// 18
		model.addElement("     for (int i = 0; i < n; i++) ");// 19
		if(isIncrease)
		model.addElement("          arr[i] = output[i];");// 20
		else
		model.addElement("          arr[i] = output[n - i - 1];");// 20
		model.addElement("}");// 21
		MainWindow.frame.setCode(model);
	}

	void initBucketPos() {
		this.RemoveSubElements();
		bucketPos = new Point[10];
		labelBucket = new JLabel[10];
		int x = (container.getSize().width - 950) / 2;
		int y = 280;
		for (int i = 0; i < 10; i++) {
			bucketPos[i] = new Point(x, y);
			labelBucket[i] = new JLabel("" + i);
			labelBucket[i].setSize(90, 160);
			labelBucket[i].setOpaque(true);
			labelBucket[i].setLocation(x - 20, y - 90);
			labelBucket[i].setFont(new Font("Helvetica", Font.BOLD, 17));
			labelBucket[i].setBackground(bucketColor);
			labelBucket[i].setHorizontalAlignment(SwingConstants.CENTER);
			labelBucket[i].setVerticalAlignment(SwingConstants.BOTTOM);
			container.add(labelBucket[i]);
			x += 100;
		}
	}

	void initArrayPos() {
		arrayPos = new Point[elements.length];
		for (int i = 0; i < arrayPos.length; i++) {
			arrayPos[i] = new Point(elements[i].getPosition());
		}
	}

	@Override
	public void SortIncrease() {
		container.repaint();
		HighlightRow(1);
		HighlightRow(2);
		int m = getMax(elements);
		for (int exp = 1; m / exp > 0; exp *= 10) {
			HighlightRow(3);
			HighlightRow(4);
			Highlight((int) Math.log10(exp));
			countSort(exp, true);
		}
		HighlightRow(5);
		ResetValue();
	}

	@Override
	public void SortDecrease() {
		container.repaint();
		HighlightRow(1);
		HighlightRow(2);
		int m = getMax(elements);
		for (int exp = 1; m / exp > 0; exp *= 10) {
			HighlightRow(3);
			HighlightRow(4);
			Highlight((int) Math.log10(exp));
			countSort(exp, false);
		}
		HighlightRow(5);
		ResetValue();
	}

	int getMax(Element[] arr) {
		int mx = arr[0].getValue();
		for (int i = 1; i < arr.length; i++) {
			int num = arr[i].getValue();
			if (num > mx)
				mx = num;
		}
		return mx;
	}

	void countSort(int exp, boolean isIncrease) {
		HighlightRow(7);
		HighlightRow(8);
		int length = elements.length;
		HighlightRow(9);
		Element[] output = new Element[length];
		int i;
		HighlightRow(10);
		int[] count = new int[10];

		for (i = 0; i < length; i++) {
			HighlightRow(11);
			HighlightRow(12);
			int index = (elements[i].getValue() / exp) % 10;
			count[index]++;
			MoveToBucket(elements[i], index);
		}

		for (i = 0; i < 10; i++) {
			bucketPos[i].y += count[i] * 60;
		}

		for (i = 1; i < 10; i++) {
			HighlightRow(13);
			HighlightRow(14);
			count[i] += count[i - 1];
		}

		if (isIncrease) {
			for (i = length - 1; i >= 0; i--) {
				HighlightRow(15);
				HighlightRow(16);
				HighlightRow(17);
				int index = count[(elements[i].getValue() / exp) % 10] - 1;
				output[index] = elements[i];
				count[(elements[i].getValue() / exp) % 10]--;
			}
			for (i = 0; i < length; i++) {
				HighlightRow(19);
				HighlightRow(20);
				elements[i] = output[i];
				MoveToArray(elements[i], i);
			}
		} else {
			for (i = 0; i < length; i++) {
				HighlightRow(15);
				HighlightRow(16);
				HighlightRow(17);
				int index = count[(elements[i].getValue() / exp) % 10] - 1;
				output[index] = elements[i];
				count[(elements[i].getValue() / exp) % 10]--;
			}
			for (i = 0; i < length; i++) {
				HighlightRow(19);
				HighlightRow(20);
				elements[i] = output[length - i - 1];
				MoveToArray(elements[i], i);
			}
		}

	}

	void MoveToBucket(Element e, int index) {
		try {
			Coloring(e, swapForwardColor);
			Thread.sleep(delayFrame * 10);
			Move(e, bucketPos[index]);
			bucketPos[index].y -= 60;
		} catch (Exception ex) {

		}
	}

	void MoveToArray(Element e, int indexArray) {
		try {
			while (!e.getPosition().equals(arrayPos[indexArray])) {

				e.setPosition(MoveTowards(e.getPosition(), arrayPos[indexArray], speed));

				Thread.sleep(delayFrame);
			}
			Thread.sleep(delayFrame * 10);
			Coloring(e, normalColor);
		} catch (Exception ex) {

		}
	}

	@Override
	public void Move(Element e, Point p) {
		try {
			Point[] waypoint = new Point[3];
			int yTmp = (e.getPosition().y + p.y) / 2;
			waypoint[0] = new Point(e.getPosition().x, yTmp);
			waypoint[1] = new Point(p.x, yTmp);
			waypoint[2] = new Point(p.x, p.y);
			int index = 0;
			while (index < waypoint.length) {
				e.setPosition(MoveTowards(e.getPosition(), waypoint[index], speed));
				if (e.getPosition().equals(waypoint[index])) {
					index++;
				}
				Thread.sleep(delayFrame);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Highlight(int row) {
		for (Element e : elements) {
			int num = e.getValue();
			String tmp = Integer.toString(num);
			String hl, tmp2 = "";
			if (tmp.length() == row) {
				hl = "0";
			} else if (tmp.length() < row) {
				hl = "0";
				for (int i = 0; i < row - tmp.length() - 1; i++) {
					tmp += "0";
				}
			} else {
				hl = Character.toString(tmp.charAt(tmp.length() - row - 1));
				if (row != tmp.length() - 1) {
					tmp2 = tmp.substring(0, tmp.length() - row - 1);
				}

				if (row == 0) {
					tmp = "";
				} else {
					tmp = tmp.substring(tmp.length() - row);
				}
			}

			e.setLabelText("<html>" + tmp2 + "<font color='yellow'>" + hl + "</font>" + tmp + "</html>");
		}
	}

	void ResetValue() {
		for (Element e : elements) {
			e.setValue(e.getValue());
		}
	}

	@Override
	public void RemoveSubElements() {
		if (labelBucket == null) {
			return;
		}
		for (int i = 0; i < 10; i++) {
			this.container.remove(labelBucket[i]);
		}
		labelBucket = null;
	}
}
