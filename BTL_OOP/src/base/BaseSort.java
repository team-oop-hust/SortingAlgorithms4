package base;

import java.awt.Color;
import java.awt.Point;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import window.CompleteSortWindow;
import window.MainWindow;

public class BaseSort {

	public static BaseSort currentSort;
	public static int speed = 10;

	protected Element[] elements;
	protected JPanel container;
	protected boolean isSorting;// kiemtra
	final int distance = 80;
	final int labelSize = 50;
	protected final int delayFrame = 10; // miliseconds

	public static Color normalColor = new Color(51, 173, 255);
	public static Color compareColor = new Color(246, 229, 141);
	public static Color swapForwardColor = new Color(230, 126, 34);
	public static Color swapBackwardColor = new Color(231, 76, 60);
	public static Color completedColor = new Color(153, 255, 153);
	public static Color pivotColor = new Color(192, 57, 43);
	public static Color destColor = new Color(135, 97, 153);
	public static Color bucketColor = new Color(149, 165, 166);

	protected DefaultListModel<String> model;

	public BaseSort() {

	}

	public BaseSort(JPanel container) {
		this.container = container;
		elements = MainWindow.frame.elements;
		currentSort = this;
		Init();
		InitCode();
	}

	public void Init() {

	}

	public void InitCode() {

	}

	public boolean firstCheck() {
		boolean isIncrease = MainWindow.frame.isIncrease;
		for (int i = 0; i < elements.length - 1; i++) {
			if ((isIncrease && elements[i].getValue() > elements[i + 1].getValue())
					|| (!isIncrease && elements[i].getValue() < elements[i + 1].getValue())) {
				return false;
			}
		}
		return true;
	}

	public void Swap(int e1, int e2) {
		try {
			if (e1 == e2) {
				Coloring(elements[e1], compareColor);
				Thread.sleep(delayFrame * 40);
				Coloring(elements[e1], normalColor);
				return;
			}
			if (e1 > e2) {
				Coloring(elements[e1], swapBackwardColor);
				Coloring(elements[e2], swapForwardColor);
			} else {
				Coloring(elements[e2], swapBackwardColor);
				Coloring(elements[e1], swapForwardColor);
			}

			Element tmp = elements[e1];
			elements[e1] = elements[e2];
			elements[e2] = tmp;
			Move(elements[e1], elements[e2]);
			Coloring(elements[e1], normalColor);
			Coloring(elements[e2], normalColor);
		} catch (Exception e) {

		}
	}

	public void Coloring(Element e, Color color) {
		e.setColor(color);
	}

	public void Highlight(int row) {

	}

	public void Move(Element e, Point p) {

	}

	public void Move(Element e, Element f) {
		try {
			Point[] waypoint1 = new Point[3];
			int yTmp = e.getPosition().y + 50;
			waypoint1[0] = new Point(e.getPosition().x, yTmp);
			waypoint1[1] = new Point(f.getPosition().x, yTmp);
			waypoint1[2] = new Point(f.getPosition().x, f.getPosition().y);

			Point[] waypoint2 = new Point[3];
			int yTmp2 = f.getPosition().y - 50;
			waypoint2[0] = new Point(f.getPosition().x, yTmp2);
			waypoint2[1] = new Point(e.getPosition().x, yTmp2);
			waypoint2[2] = new Point(e.getPosition().x, e.getPosition().y);

			int index1 = 0, index2 = 0;
			while (index1 < waypoint1.length || index2 < waypoint2.length) {
				e.setPosition(MoveTowards(e.getPosition(), waypoint1[index1], speed));
				f.setPosition(MoveTowards(f.getPosition(), waypoint2[index2], speed));

				if (e.getPosition().equals(waypoint1[index1])) {
					index1++;
				}
				if (f.getPosition().equals(waypoint2[index2])) {
					index2++;
				}
				Thread.sleep(delayFrame);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Point MoveTowards(Point current, Point target, int maxDistanceDelta) {
		Point result;
		Point a = new Point(target.x - current.x, target.y - current.y);
		double magnitude = Math.sqrt(a.x * a.x + a.y * a.y);
		if (magnitude <= maxDistanceDelta || magnitude == 0f) {
			result = target;
		} else {
			result = new Point(current.x + (int) (a.x * maxDistanceDelta / magnitude),
					current.y + (int) (a.y * maxDistanceDelta / magnitude));
		}
		return result;
	}

	public void MoveFollowWaypoints(Element e, Point[] waypoints, int speed) {
		try {
			int index = 0;
			while (index < waypoints.length) {
				if (e.getPosition().equals(waypoints[index])) {
					index++;
				}
				e.setPosition(MoveTowards(e.getPosition(), waypoints[index], speed));
				Thread.sleep(delayFrame);
			}
		} catch (Exception ex) {

		}
	}

	public void Sort() throws InterruptedException {

		if (!firstCheck()) {
			if (MainWindow.frame.isIncrease) {
				SortIncrease();
			} else {
				SortDecrease();
			}
		}
		for (int i = 0; i < elements.length; i++) {
			Coloring(elements[i], completedColor);
			Thread.sleep(delayFrame * 10);
		}
		HighlightRow(0);
		MainWindow.frame.End();
	}

	public void SortIncrease() {

	}

	public void SortDecrease() {

	}

	public void UpdateSubElements() {

	}

	public JPanel getContainer() {
		return container;
	}

	public Element getElement(int index) {
		return elements[index];
	}

	public void RemoveAllElements() {
		container.removeAll();
		RemoveSubElements();
	}

	public void RemoveSubElements() {

	}

	public int[] getValue() {
		int[] res = new int[elements.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = elements[i].getValue();
		}
		return res;
	}

	public void setElements(Element[] elements) {
		this.elements = elements;
	}

	public void ShowCompleteSortDialog() {
		CompleteSortWindow window = new CompleteSortWindow();
		window.setVisible(true);
	}

	public void HighlightRow(int line) {
		try {
			if (line >= model.size()) {
				return;
			}
			MainWindow.frame.HighlightRow(line);
			Thread.sleep(delayFrame * 2);
		} catch (Exception ex) {

		}
	}
}
