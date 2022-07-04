package sorting_algorithm;

import base.BaseSort;
import base.Element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

public class QuickSort extends BaseSort {
	public QuickSort(JPanel container) {
		super(container);
	}
	
	public void SortIncrease() {
		int high = this.elements.length -1;
		this.Progress(0, high);
	}
	
	public int partition(int low, int high)
	{
		Coloring(elements[high], Color.red);
	    int pivot = elements[high].getValue();
	    int i = (low - 1);
	    for(int j = low; j <= high - 1; j++)
	    {
	        if (elements[j].getValue() < pivot)
	        {
	            i++;
	            Swap(i, j);
	        }
	    }
	    Swap(i + 1, high);
	    Coloring(elements[high], Color.blue);
	    return (i + 1);
	}

	public void Progress(int low, int high) {
		
		if (low < high)
	    {
	        int pi = partition(low, high);
			Progress(low, pi - 1);
			Progress(pi + 1, high);
	    }
	}
}
