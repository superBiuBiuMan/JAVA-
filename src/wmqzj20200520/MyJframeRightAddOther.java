package wmqzj20200520;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * �������
 * 
 * @author WmqZj
 */
public class MyJframeRightAddOther extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/*** ��ť���ػ��� */
	JButton buttonClose;
	/** ��ť������ */
	JButton buttonReboot;
	/*** ��ť��˯�ߡ� */
	JButton buttonSleep;
	/** ��ť��ע��� */
	JButton buttonRegedit;
	/** ��ť���������� */
	JButton buttonCalc;
	/*** ��ť���ҵĵ��ԡ� */
	JButton buttonComputer;
	/** ��ť��д�ְ塿 */
	JButton buttonWrite;
	/** ��ť����ͼ�塿 */
	JButton buttonDraw;
	/** ��ť��������塿 */
	JButton buttonControl;
	/** ������е�λ�õĸɻ� */
	int x = (MyJframe.jFrame.getWidth() - 210) / 2;
	int y = (MyJframe.jFrame.getHeight() - 190) / 2;
	Point topLeft = MyJframe.jFrame.getLocationOnScreen();

	public MyJframeRightAddOther() {
		// ���þ���
		this.setBounds(x + topLeft.x - 50, y + topLeft.y - 20, 380, 320);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("�뵥��������ӵĹ��ܰ�~");
		// ���ò��ɵ��ڴ��ڴ�С
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		buttonClose = new JButton("�ػ�", new ImageIcon("images/shutdown.png"));
		buttonReboot = new JButton("����", new ImageIcon("images/reboot.png"));
		buttonSleep = new JButton("˯��", new ImageIcon("images/sleep.png"));
		buttonCalc = new JButton("������", new ImageIcon("images/calc.png"));
		buttonRegedit = new JButton("ע���", new ImageIcon("images/regedit.png"));
		buttonComputer = new JButton("�ҵĵ���", new ImageIcon("images/computer.png"));
		buttonWrite = new JButton("д�ְ�", new ImageIcon("images/write.png"));
		buttonDraw = new JButton("��ͼ��", new ImageIcon("images/draw.png"));
		buttonControl = new JButton("�������", new ImageIcon("images/control.png"));

		setButton(buttonClose);
		setButton(buttonReboot);
		setButton(buttonSleep);
		setButton(buttonCalc);
		setButton(buttonRegedit);
		setButton(buttonComputer);
		setButton(buttonWrite);
		setButton(buttonDraw);
		setButton(buttonControl);

		this.add(buttonClose);
		this.add(buttonReboot);
		this.add(buttonSleep);
		this.add(buttonCalc);
		this.add(buttonRegedit);
		this.add(buttonComputer);
		this.add(buttonWrite);
		this.add(buttonControl);
		this.add(buttonDraw);

	}

	/**
	 * ���ð�ť
	 * 
	 * @param button -Ҫ���õİ�ť
	 */
	public void setButton(JButton button) {
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		// �����Ƿ���Ʊ߿�
//		button.setBorderPainted(false);
//		button.setContentAreaFilled(false);
//		button.setFocusPainted(false);
		// ���ð�ť�Ŀ�
		button.setPreferredSize(new Dimension(86, 86));
		button.addActionListener(this);
	}

	/**
	 * ��¡��ť
	 * 
	 * @param name   -��ť����
	 * @param tip    -��ʾ��Ϣ
	 * @param button -Ҫ���õİ�ť
	 * @param width  -Ҫ���õĿ�
	 * @param height -��
	 * @param cmd    -dos����
	 */
	public void clone(String name, String tip, JButton button, int width, int height, String cmd) {
		// ��ֱ���־���
		button.setVerticalTextPosition(JButton.BOTTOM);
		// ˮƽ���־���
		button.setHorizontalTextPosition(JButton.CENTER);
//		// �����Ƿ���Ʊ߿�
//		button.setBorderPainted(false);
//		button.setContentAreaFilled(false);
//		button.setFocusPainted(false);
		// ���ð�ť�Ŀ�
		button.setPreferredSize(new Dimension(width, height));
		// ��Ӽ���
		button.addActionListener(new ActionListener() {
			String shutDownNow = "�ػ�(����ػ�Ŷ~)";
			String rebooNow = "����(��������Ŷ~)";
			String sleepNow = "˯��(��Ϣ���~)";

			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = ((JButton) e.getSource()).getToolTipText();
				// �ָ��temp[0]Ϊnull,temp[1]�ļ�������,,temp[2]Ϊ·��
				String[] splitAfter = filePath.split("(<[^\\>]+> *)+");
				if (splitAfter[1].equals(shutDownNow) || splitAfter[1].equals(rebooNow)
						|| splitAfter[1].equals(sleepNow)) {
					Object[] options = { "ȷ��", "ȡ��" };
					int select = JOptionPane.showOptionDialog(MyJframe.jFrame, "��ȷ��ִ�С� " + splitAfter[1] + "����", "��ʾ��",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon("images/cancel.png"), options, options[1]);
					// ȷ�ϰ���
					if (select == 0) {
						// ִ�д��ļ�
						String cmd = "rundll32 url.dll,FileProtocolHandler " + splitAfter[2];
						try {
							Runtime.getRuntime().exec(cmd);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(MyJframe.jFrame, "�����ˣ������ԣ�");
							e1.printStackTrace();
						}
					}
				} else {
					// ִ�д��ļ�
					String cmd = splitAfter[2];
					try {
						Runtime.getRuntime().exec(cmd);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MyJframe.jFrame, "�����ˣ������ԣ�");
						e1.printStackTrace();
					}
				}

			}
		});
		// ��Ӽ���
		button.addMouseMotionListener(MyCreateMySelfButton.mousedrag);
		button.addMouseListener(new MyButtonRightAndDragFile());

		String webStyle = "<html><strong><font size=4>" + name + "(" + tip + ")" + "</font> </strong><br>" + cmd
				+ "<br></html>";
		button.setToolTipText(webStyle);
		// ��ӵ�����
		MyJframe.panelArray.get(MyJframe.panelIndex).add(button);
		// �������õ�ǰpanel������������Ϊ��ǰ�ļ���1
		int temp1 = MyJframe.panelComAmount.get(MyJframe.panelIndex) + 1;
		MyJframe.panelComAmount.set(MyJframe.panelIndex, temp1);
		// д���ļ� ����·��+�ָ����+�ļ���+�����������
		String writeTemp = cmd + "###" + name + "(" + tip + ")" + "###" + "Special";
		new MyDataIniReadAndWrite().write(MyJframe.panelComAmount.get(MyJframe.panelIndex), writeTemp);
		new MyIniReadAndWrite().changAmount(MyJframe.panelComAmount.get(MyJframe.panelIndex));
		MyJframe.jFrame.validate();

	}

	/**
	 * ��������ť��ʱ��,"��¡һ����ť����ǰpanel"
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button;
		switch (((JButton) e.getSource()).getText()) {
		case "�ػ�":
			button = new JButton("�ػ�", new ImageIcon("images/shutdown.png"));
			// �ػ�
			clone("�ػ�", "����ػ�Ŷ~", button, 70, 60, "shutdown /p");
			break;
		case "����":
			button = new JButton("����", new ImageIcon("images/reboot.png"));
			// ����
			clone("����", "��������Ŷ~", button, 70, 60, "shutdown /r");
			break;
		case "˯��":
			button = new JButton("˯��", new ImageIcon("images/sleep.png"));
			// ˯��
			clone("˯��", "��Ϣ���~", button, 70, 60, "shutdown /h");
			break;
		case "ע���":
			button = new JButton("ע���", new ImageIcon("images/regedit.png"));
			// ��ע���
			clone("ע���", "�༭ע���", button, 70, 60, "regedit");
			break;
		case "������":
			button = new JButton("������", new ImageIcon("images/calc.png"));
			// ������
			clone("������", "������", button, 70, 60, "calc");
			break;
		case "�ҵĵ���":
			button = new JButton("�ҵĵ���", new ImageIcon("images/computer.png"));
			// �ҵĵ���
			clone("�ҵĵ���", "���ҵĵ���", button, 70, 60, "explorer.exe");
			break;
		case "д�ְ�":
			button = new JButton("д�ְ�", new ImageIcon("images/write.png"));
			// д�ְ�
			clone("д�ְ�", "�ǼǱʼǰ�~", button, 70, 60, "write");
			break;
		case "��ͼ��":
			button = new JButton("��ͼ��", new ImageIcon("images/draw.png"));
			// ��ͼ��
			clone("��ͼ��", "�������»��»���~", button, 70, 60, "mspaint");
			break;
		case "�������":
			button = new JButton("�������", new ImageIcon("images/control.png"));
			// �������
			clone("�������", "������ĵ���", button, 70, 60, "control");
			break;
		default:
			break;
		}
		// ���ù�����
		new MyScrollPane().reserScroll(MyJframe.panelIndex);

	}

}