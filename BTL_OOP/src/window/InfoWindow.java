package window;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class InfoWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoWindow frame = new InfoWindow();
					frame.setVisible(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoWindow() {
		setResizable(false);
		setBounds(100, 100, 550, 330);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new TitledBorder(null, "Thông tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JTextArea txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		txtInfo.setFont(new Font("Arial", Font.PLAIN, 14));
		txtInfo.setBackground(SystemColor.menu);
		txtInfo.setText("Bài tập lớn: Mô tả thuật toán sắp xếp\r\n"
				+ "Đối tượng: Bubble, Quick, Heap, Radix Sort\r\n"
				+ "Môn học: Lập trình hướng đối tượng\r\n"
				+ "Kỳ học: 20212\r\n"
				+ "Mã lớp: 132641\r\n"
				+ "GVHD: Trần Nhật Hòa\r\n"
				+ "Nhóm: 1\r\n"
				+ "Hoàng Anh - 20200015\r\n"
				+ "Vũ Thế Phương - 20204681\r\n"
				+ "Đinh Việt Hoàn - 20204652\r\n"
				+ "Đỗ Hải Nam - 20204590\r\n"
				+ "Nguyễn Mạnh Đức - 20173039\r\n"
				+ "Link sản phẩm: https://github.com/team-oop-hust/SortingAlgorithms5.git\r\n"
				+ "Tham khảo: https://github.com/thiennhank9/MoPhongThuatToanSapXep.git");
		contentPane.add(txtInfo);
	}
}