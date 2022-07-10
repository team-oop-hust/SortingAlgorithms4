package sorting_algorithm;

import base.BaseSort;
import window.MainWindow;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public class QuickSort extends BaseSort {

	public QuickSort(JPanel container) {
		super(container);
	}

	@Override
	public void InitCode() {
		model = new DefaultListModel<String>();
		model.addElement("void QuickSort(int arr[], int low, int high) {");// 0
		model.addElement("     if (low < high) {");// 1
		model.addElement("          int pi = partition(arr, low, high);");// 2
		model.addElement("          sort(arr, low, pi-1);");// 3
		model.addElement("          sort(arr, pi+1, high);");// 4
		model.addElement("	   }");// 5
		model.addElement("}");// 6
		model.addElement("");// 7
		model.addElement("int partition(int arr[], int low, int high) {");// 8
		model.addElement("     int pivot = arr[high];");// 9
		model.addElement("     int i = (low-1);");// 10
		model.addElement("     for (int j = low; j < high; j++) {");// 11
		model.addElement("          if (arr[j] <= pivot) {");// 12
		model.addElement("               i++;");// 13
		model.addElement("               Swap(arr[i], arr[j])");// 14
		model.addElement("     	    }");// 15
		model.addElement("     }");// 16
		model.addElement("    	Swap(arr[i + 1], arr[high])");// 17
		model.addElement("     return i+1;");// 18
		model.addElement("}");// 19
		MainWindow.frame.setCode(model);
	}

	public void SortIncrease() {
		int high = this.elements.length - 1;
		this.Progress(0, high);
	}

	public void SortDecrease() {
		int high = this.elements.length - 1;
		this.Progress(0, high);
	}

	public int partition(int low, int high) throws InterruptedException {
		boolean isIncrease = MainWindow.frame.isIncrease;
		HighlightRow(8);
		HighlightRow(9);
		Coloring(elements[high], pivotColor);
		int pivot = elements[high].getValue();
		HighlightRow(10);
		int i = (low - 1);
		Coloring(elements[i + 1], destColor);
		for (int j = low; j <= high - 1; j++) {
			HighlightRow(11);
			HighlightRow(12);
			Coloring(elements[j], compareColor);
			Thread.sleep(delayFrame * 40);
			Coloring(elements[j], normalColor);
			if ((isIncrease && elements[j].getValue() < pivot) || (!isIncrease && elements[j].getValue() > pivot)) {
				HighlightRow(13);
				i++;
				Coloring(elements[i], normalColor);
				Coloring(elements[i + 1], destColor);
				HighlightRow(14);
				Swap(i, j);
				HighlightRow(15);
			}
			HighlightRow(16);
		}
		Thread.sleep(delayFrame * 40);
		HighlightRow(17);
		Swap(i + 1, high);
		Coloring(elements[high], normalColor);
		HighlightRow(18);
		return (i + 1);
	}

	public void Progress(int low, int high) {
		try {
			if (low < high) {
				HighlightRow(1);
				HighlightRow(2);
				int pi = partition(low, high);
				HighlightRow(3);
				Progress(low, pi - 1);
				HighlightRow(4);
				Progress(pi + 1, high);
				HighlightRow(5);
			}
			HighlightRow(6);
		} catch (Exception e) {

		}
	}
}
