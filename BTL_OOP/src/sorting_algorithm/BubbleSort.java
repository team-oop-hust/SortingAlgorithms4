package sorting_algorithm;

import base.BaseSort;
import window.MainWindow;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

public class BubbleSort extends BaseSort {

	public BubbleSort(JPanel container) {
		super(container);
	}

	@Override
	public void InitCode() {
		boolean isIncrease = MainWindow.frame.isIncrease;
		model = new DefaultListModel<String>();
		model.addElement("void BubbleSort(int a[],int n) {");
		model.addElement("     for (int i = 0 ; i < n - 1 ; i++)");
		model.addElement("          for (int j = n - 1; j > i ; j--)");
		if (isIncrease)
			model.addElement("               if(a[j] < a[j - 1])");
		else
			model.addElement("               if(a[j] > a[j - 1])");
		model.addElement("                    Swap(a[j], a[j - 1]");
		model.addElement("}");
		MainWindow.frame.setCode(model);
	}

	@Override
	public void SortIncrease() {
		Progress(true);
	}

	@Override
	public void SortDecrease() {
		Progress(false);
	}

	void Progress(boolean isIncrease) {
		try {
			for (int i = 0; i < elements.length - 1; i++) {
				HighlightRow(1);
				boolean swapped = false;
				for (int j = 0; j < elements.length - 1; j++) {
					HighlightRow(2);
					Coloring(elements[j], compareColor);
					Coloring(elements[j + 1], compareColor);
					HighlightRow(3);
					Thread.sleep(delayFrame * 40);
					if ((isIncrease && elements[j].getValue() > elements[j + 1].getValue())
							|| (!isIncrease && elements[j].getValue() < elements[j + 1].getValue())) {
						HighlightRow(4);
						this.Swap(j, j + 1);
						swapped = true;
					}
					Coloring(elements[j], normalColor);
					Coloring(elements[j + 1], normalColor);
				}
				if (swapped == false)
					break;

			}
			HighlightRow(5);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
