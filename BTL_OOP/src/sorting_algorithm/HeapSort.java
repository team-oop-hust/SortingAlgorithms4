package sorting_algorithm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

import base.BaseSort;
import base.Element;

public class HeapSort extends BaseSort {
	private Element[] heapElements;

	public HeapSort(JPanel container) {
		super(container);
//		initStackPos();
//		this.InitRandomArray(15, 1000);
		heapElements = new Element[this.elements.length];
		for (int i = 0; i< this.elements.length; i++) {
			this.heapElements[i] = this.elements[i].clone();
		}
		this.InitHeap(heapElements);
//		initArrayPos();
	}
	

	
	public void InitHeap(Element[] elements) {
		Dimension d = this.container.getSize();
		Point parentPoint = new Point(d.width / 2, d.height / 2);

		int i = 0;
		int row = 1;
		elements[i].setPosition(parentPoint);
		container.add(elements[i].getLabel());

		this.DrawHeap(2*i + 1, parentPoint, elements, row);
		this.DrawHeap(2*i + 2, parentPoint, elements, row);
	}
	
	private void DrawHeap(int i, Point parentPoint, Element[] elements, int row) {
		if (i >= elements.length) return;
		Dimension d = this.container.getSize();
		
		Point leftChildPoint = new Point(parentPoint.x - 250 / row * 2 / 3, parentPoint.y + 80 * 2 / 3);
		Point rightChildPoint = new Point(parentPoint.x + 250 / row * 2 / 3, parentPoint.y + 80 * 2 / 3);
		if (i % 2 == 1) {
			elements[i].setPosition(leftChildPoint);
			container.add(elements[i].getLabel());

			this.DrawHeap(2*i + 1, leftChildPoint, elements, row + 1);
			this.DrawHeap(2*i + 2, leftChildPoint, elements, row + 1);
		} else {
			elements[i].setPosition(rightChildPoint);	
			container.add(elements[i].getLabel());

			this.DrawHeap(2*i + 1, rightChildPoint, elements, row + 1);
			this.DrawHeap(2*i + 2, rightChildPoint, elements, row + 1);
		}

	}
	
	
	public void Move(Element e, Point p, int delay)
	{
		try 
		{
			Point[] waypoint = new Point[3];
			int yTmp = (e.getPosition().y + p.y) / 2;
			waypoint[0] = new Point(e.getPosition().x, yTmp);
			waypoint[1] = new Point(p.x, yTmp);
			waypoint[2] = new Point(p.x, p.y);
			int index = 0;
			while(index < waypoint.length)
			{
				e.setPosition(MoveTowards(e.getPosition(), waypoint[index], 10));
				if(e.getPosition().equals(waypoint[index]))
				{
					index++;
				}
				Thread.sleep(delay);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	
	@Override
	public void Sort() {
		int n = this.elements.length;

	      for (int i = n / 2 - 1; i >= 0; i--)
	          downHeapify(this.elements, n, i);

	      for (int i=n-1; i>0; i--)
	      {
	    	  this.Swap(0,i);
	    	  this.SwapHeap(0,i);
	          downHeapify(this.elements, i, 0);
	      }
	}
	
	private void downHeapify(Element[] elements, int n, int i)
    {
		int largest = i;
	      int l = 2*i + 1;
	      int r = 2*i + 2;

	      if (l < n && elements[l].getValue() > elements[largest].getValue())
	          largest = l;

	      if (r < n && elements[r].getValue() > elements[largest].getValue())
	          largest = r;

	      if (largest != i)
	      {
	    	  this.Swap(i, largest);
	    	  this.SwapHeap(i, largest);

	          downHeapify(elements, n, largest);
	      }
    }
	
	private void SwapHeap(int i1, int i2) {
		Coloring(heapElements[i1], Color.yellow);
		Coloring(heapElements[i2], Color.red);
		Move(heapElements[i1], heapElements[i2], 10);
		
		Element tmp = heapElements[i1];
		heapElements[i1] = heapElements[i2];
		heapElements[i2] = tmp;

		Coloring(heapElements[i1], Color.blue);
		Coloring(heapElements[i2], Color.blue);
	}
	
	@Override
	public void RemoveAllElements() {
		super.RemoveAllElements();
		for (int i=0; i < this.heapElements.length; i++) {
			this.container.remove(this.heapElements[i].getLabel());
		}
		this.heapElements = null;
	}
	
}
