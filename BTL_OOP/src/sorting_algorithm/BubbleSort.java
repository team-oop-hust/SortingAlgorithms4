package sorting_algorithm;

import base.BaseSort;
import base.Element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

public class BubbleSort extends BaseSort{
	
	public BubbleSort(JPanel container) {
		super(container);
	}
	
	public void SortIncrease() {
		try {
		for (int i=0; i<elements.length-1; i++) {
			boolean swapped = false;
			for (int j=0; j<elements.length-1; j++) {
				Coloring(elements[j], Color.red);
				Coloring(elements[j+1], Color.red);
				if (elements[j].getValue()>elements[j+1].getValue()) {
					this.Swap(j, j+1);
					swapped = true;
				}
				else Thread.sleep(400);
				Coloring(elements[j], Color.blue);
				Coloring(elements[j+1], Color.blue);
			}
			if (swapped==false) break;
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		ResetValue();
	}
	
	void ResetValue()
	{
		for(Element e: elements)
		{
			e.setValue(e.getValue());
		}
	}
	
	
	public void Swap(Element e1, Element e2)
	{
		
	}
}
