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
		model.addElement("void QuickSort(int arr[], int low, int high) {");
		model.addElement("     if (low < high) {");
		model.addElement("          int pi = partition(arr, low, high);");
		model.addElement("          sort(arr, low, pi-1);");
		model.addElement("          sort(arr, pi+1, high);");
		model.addElement("	   }");
		model.addElement("}");
		model.addElement("");
		model.addElement("int partition(int arr[], int low, int high) {");
		model.addElement("     int pivot = arr[high];");
		model.addElement("     int i = (low-1);");
		model.addElement("     for (int j = low; j < high; j++) {");
		model.addElement("          if (arr[j] <= pivot) {");
		model.addElement("               i++;");
		model.addElement("               int temp = arr[i];");
		model.addElement("               arr[i] = arr[j];");
		model.addElement("               arr[j] = temp;");
		model.addElement("     	    }");
		model.addElement("     }");
		model.addElement("     int temp = arr[i+1];");
		model.addElement("     arr[i+1] = arr[high];");
		model.addElement("     arr[high] = temp;");
		model.addElement("     return i+1;");
		model.addElement("}");
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
		Coloring(elements[high], pivotColor);
		int pivot = elements[high].getValue();
		int i = (low - 1);
		Coloring(elements[i + 1], destColor);
		for (int j = low; j <= high - 1; j++) {
			Coloring(elements[j], compareColor);
			Thread.sleep(delayFrame * 40);
			Coloring(elements[j], normalColor);
			if ((isIncrease && elements[j].getValue() < pivot) || (!isIncrease && elements[j].getValue() > pivot)) {
				i++;
				Coloring(elements[i], normalColor);
				Coloring(elements[i + 1], destColor);
				Swap(i, j);
			}
		}
		Thread.sleep(delayFrame * 40);
		Swap(i + 1, high);
		Coloring(elements[high], normalColor);
		return (i + 1);
	}

	public void Progress(int low, int high) {
		try {
			if (low < high) {
				int pi = partition(low, high);
				Progress(low, pi - 1);
				Progress(pi + 1, high);
			}
		} catch (Exception e) {

		}
	}
}
