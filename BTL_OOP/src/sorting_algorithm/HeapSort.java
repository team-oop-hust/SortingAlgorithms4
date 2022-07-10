package sorting_algorithm;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import base.BaseSort;
import base.Element;
import window.MainWindow;

public class HeapSort extends BaseSort {
	private Element[] heapElements;

	public HeapSort(JPanel container) {
		super(container);
		Init();
	}

	public void Init() {
		this.RemoveSubElements();
		heapElements = new Element[this.elements.length];
		for (int i = 0; i < this.elements.length; i++) {
			this.heapElements[i] = this.elements[i].clone();
		}
		this.InitHeap(heapElements);
	}

	@Override
	public void InitCode() {
		model = new DefaultListModel<String>();
		model.addElement("void HeapSort(int arr[]) {"); // 0
		model.addElement("     int n = arr.length;"); // 1
		model.addElement("     for (int i = n / 2 - 1; i >= 0; i--)"); // 2
		model.addElement("          heapify(arr, n, i);"); // 3
		model.addElement("     for (int i = n - 1; i > 0; i--) {"); // 4
		model.addElement("          Swap(arr[i], arr[0]);"); // 5
		model.addElement("          heapify(arr, i, 0);"); // 6
		model.addElement("	    }"); // 7
		model.addElement("}"); // 8
		model.addElement(""); // 9
		model.addElement("void heapify(int arr[], int n, int i) {"); // 10
		model.addElement("     int largest = i;"); // 11
		model.addElement("     int l = 2 * i + 1;"); // 12
		model.addElement("     int r = 2 * i + 2;"); // 13
		model.addElement("     if(l < n && arr[l] > arr[largest])"); // 14
		model.addElement("          largest = l;"); // 15
		model.addElement("     if(l < n && arr[r] > arr[largest])"); // 16
		model.addElement("          largest = r;"); // 17
		model.addElement("     if(largest != i) {"); // 18
		model.addElement("          Swap(arr[largest], arr[i]);"); // 19
		model.addElement("          heapify(arr, n, largest);"); // 20
		model.addElement("	   	}"); // 21
		model.addElement("}");
		MainWindow.frame.setCode(model);
	}

	public void InitHeap(Element[] elements) {
		Dimension d = this.container.getSize();
		Point parentPoint = new Point(d.width / 2, d.height / 2);
		int i = 0;
		int row = 1;
		elements[i].setPosition(parentPoint);
		container.add(elements[i].getLabel());
		this.DrawHeap(2 * i + 1, parentPoint, elements, row);
		this.DrawHeap(2 * i + 2, parentPoint, elements, row);
	}

	private void DrawHeap(int i, Point parentPoint, Element[] elements, int row) {
		if (i >= elements.length)
			return;

		Point leftChildPoint = new Point(parentPoint.x - 250 / row * 2 / 3, parentPoint.y + 80 * 2 / 3);
		Point rightChildPoint = new Point(parentPoint.x + 250 / row * 2 / 3, parentPoint.y + 80 * 2 / 3);
		if (i % 2 == 1) {
			elements[i].setPosition(leftChildPoint);
			container.add(elements[i].getLabel());

			this.DrawHeap(2 * i + 1, leftChildPoint, elements, row + 1);
			this.DrawHeap(2 * i + 2, leftChildPoint, elements, row + 1);
		} else {
			elements[i].setPosition(rightChildPoint);
			container.add(elements[i].getLabel());

			this.DrawHeap(2 * i + 1, rightChildPoint, elements, row + 1);
			this.DrawHeap(2 * i + 2, rightChildPoint, elements, row + 1);
		}

	}

	void Progress(boolean isIncrease) {
		try {
			HighlightRow(1);
			int n = this.elements.length;
			for (int i = n / 2 - 1; i >= 0; i--) {
				HighlightRow(2);
				HighlightRow(3);
				Heapify(elements, n, i, isIncrease);
			}
			for (int i = n - 1; i > 0; i--) {
				HighlightRow(4);
				Coloring(elements[0], compareColor);
				Coloring(elements[i], compareColor);
				Thread.sleep(delayFrame * 20);
				HighlightRow(5);
				this.Swap(0, i);
				this.SwapHeap(0, i);
				HighlightRow(6);
				Heapify(elements, i, 0, isIncrease);
				HighlightRow(7);
			}
			HighlightRow(8);
		} catch (Exception ex) {

		}
	}

	@Override
	public void SortIncrease() {
		Progress(true);
	}

	@Override
	public void SortDecrease() {
		Progress(false);
	}

	private void Heapify(Element[] elements, int n, int i, boolean isIncrease) throws InterruptedException {
		HighlightRow(10);
		HighlightRow(11);
		int largest = i;
		HighlightRow(12);
		int l = 2 * i + 1;
		HighlightRow(13);
		int r = 2 * i + 2;
		HighlightRow(14);
		if (l < n) {
			int num = elements[l].getValue() - elements[largest].getValue();
			if ((isIncrease && num > 0) || (!isIncrease && num < 0))
				largest = l;
		}

		HighlightRow(16);
		if (r < n) {
			int num = elements[r].getValue() - elements[largest].getValue();
			if ((isIncrease && num > 0) || (!isIncrease && num < 0))
				largest = r;
		}

		HighlightRow(18);
		if (largest != i) {
			Coloring(elements[largest], compareColor);
			Coloring(elements[i], compareColor);
			HighlightRow(19);
			Thread.sleep(delayFrame * 20);
			this.Swap(i, largest);
			this.SwapHeap(i, largest);
			HighlightRow(20);
			Heapify(elements, n, largest, isIncrease);
		}
		HighlightRow(21);
	}

	private void SwapHeap(int i1, int i2) {
		if (i1 > i2) {
			Coloring(heapElements[i1], swapBackwardColor);
			Coloring(heapElements[i2], swapForwardColor);
		} else {
			Coloring(heapElements[i1], swapForwardColor);
			Coloring(heapElements[i2], swapBackwardColor);
		}
		Move(heapElements[i1], heapElements[i2]);
		Element tmp = heapElements[i1];
		heapElements[i1] = heapElements[i2];
		heapElements[i2] = tmp;
		Coloring(heapElements[i1], normalColor);
		Coloring(heapElements[i2], normalColor);
	}

	@Override
	public void UpdateSubElements() {
		for (int i = 0; i < elements.length; i++) {
			heapElements[i].setValue(elements[i].getValue());
		}
	}

	@Override
	public void RemoveSubElements() {
		if (heapElements == null) {
			return;
		}
		for (int i = 0; i < this.heapElements.length; i++) {
			this.container.remove(this.heapElements[i].getLabel());
		}
		this.heapElements = null;
	}

}
