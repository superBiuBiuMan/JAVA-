package wmqzj20200520;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.lang3.ArrayUtils;

import sun.awt.shell.ShellFolder;

/**
 * �����ļ�
 * 
 * @author WmqZj
 *
 */
public class MyFindFile {
	/** JFrame */
	JFrame jFrame = new JFrame("��������Ҫ���ҵ����ݡ�");
	/** �� JPanel */
	JPanel panelTop = new JPanel();
	/** ��JPanel */
	JPanel panelBottom = new JPanel();
	/** �ײ����� */
	Container floor;
	/** ͼƬ��ǩ */
	JLabel labe1 = new JLabel("����:", new ImageIcon("images/title.png"), JLabel.LEFT);
	/** ����� */
	JTextField inputName = new JTextField(30);
	/** ������ */
	JScrollPane scrollPane = new JScrollPane(panelBottom);
	/** ������ȡ������ */
	String getName;
	/** ���ڵ�λ��,ʹ����� */
	int a = (MyJframe.jFrame.getWidth() - 210) / 2;
	int b = (MyJframe.jFrame.getHeight() - 190) / 2;
	Point topLeft = MyJframe.jFrame.getLocationOnScreen();
	/** ������ */
	public boolean isTf = false;
	public int count = 0;
	/** ��¼����panel�е�button������ */
	Component[] buttonAll = null;

	public MyFindFile() {
		setJframe();
	}

	/**
	 * ��������
	 */
	public void setJframe() {
		// �����ޱ߿�
		scrollPane.setBorder(null);
		// ���ù������������ٶ�
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		// ����ˮƽ���������ɼ�
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// �ѹ�������ӵ����嵱�У�ͬʱҲ�趨�˿�� �ص��panel
		scrollPane.setBounds(0, 35, 350, 200);
		// ����ScrollPane�Ĵ�С
		scrollPane.setPreferredSize(new Dimension(365, 200));
		// ����Ĭ����ʾpanel
		scrollPane.setViewportView(panelBottom);
		// ͨ������panelBottom�����ù�����
		panelBottom.setPreferredSize(new Dimension(365, 200));
		jFrame.setBounds(a + topLeft.x - 50, b + topLeft.y, 365, 270);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// ����Ϊ�ղ���,�������2��panel
		jFrame.setLayout(null);
		// ��С���ɸı�
		jFrame.setResizable(false);
		// ����panel��λ��
		panelTop.setBounds(0, 0, 350, 35);
		jFrame.add(panelTop);
		jFrame.add(scrollPane);
		jFrame.setVisible(true);
		// ������
		panelTop.add(labe1);
		panelTop.add(inputName);
		jFrame.setVisible(true);
		// ��Ӽ�����,���û��ı����������ݵ�ʱ�������Ӧ�ķ���
		inputName.addKeyListener(new KeyListener() {

			/** ���̰��£�Ȼ���ͷŵ��ô˷��� */
			@Override
			public void keyTyped(KeyEvent e) {

			}

			/** �ͷ�ĳ����ʱ���ô˷��� */
			@Override
			public void keyReleased(KeyEvent e) {
				// �Ƴ�֮ǰ���������
				panelBottom.removeAll();
				// ��д����Ĭ����ʾpanel ��ֹ���ְ�ť����ʧ�����
				scrollPane.setViewportView(panelBottom);
				jFrame.validate();
				// ��ȡ���������
				getName = inputName.getText();
				// ���ı�������Ϊ��,�����û������˶���ɾ������ʱ��,����
				String nullText = "";
				if (getName.equals(nullText)) {
					// ��������,�����й�����������Ϊ�յ�ʱ��,���¹�����count,,�Żῴ�����������
					if (e.getKeyCode() == 8) {
						count++;
						if (isTf && count >= 2) {
							jFrame.setVisible(false);
							panelBottom.removeAll();
							// ��ʼ��ֵ
							isTf = false;
							count = 0;
						}
					}
				}
				// �����ݲ�Ϊ�յ�ʱ��,��������(getName != null || getName.equals("")==true)
				else {
					// ÿ�ε��ö���ʼ��
					count = 0;
					buttonAll = null;
					// ��ȡ����panel���������button
					for (int i = 0; i < MyJframe.panelArray.size(); i++) {
						buttonAll = ArrayUtils.addAll(buttonAll, MyJframe.panelArray.get(i).getComponents());
					}
					// ��������
					for (int i = 0; i < buttonAll.length; i++) {
						String findName = ((JButton) buttonAll[i]).getText();
						// �Զ��巽�������Ƿ����,�������,�������ڵ�λ��,�����,�򷵻�-1
						int result1 = new MyFindStringIgnoreCase().ignoreCaseIndexOf(findName, getName, 0);
						// ������ҵ�λ��
						if (result1 != -1) {
							// �ȿ�¡��ǰ��ť
							JButton button = cloneButton(((JButton) buttonAll[i]));
							// ����ӽ�ȥpanel
							panelBottom.add(button);
							// Ϊ�˱����ж��ͼ�꣬���Լ������ң�ֱ��ѭ������
							// break;
						}
					}
					isTf = true;
				}
				panelBottom.revalidate();
				// ���ù�����
				reserScroll();
			}

			/** ����ĳ����δ�ͷ�ʱ���ô˷��� */
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

	}

	/**
	 * ��¡���ҵ��İ�ť
	 * 
	 */
	public JButton cloneButton(JButton button) {
		String text = button.getToolTipText();
		// �ָ��temp[0]Ϊnull,temp[1]�ļ�������,,temp[2]Ϊ·��������վ������������
		String[] temp = text.split("(<[^\\>]+> *)+");
		switch (temp[1]) {
		case "�ػ�(����ػ�Ŷ~)":
			button = new JButton("�ػ�", new ImageIcon("images/shutdown.png"));
			break;
		case "����(��������Ŷ~)":
			button = new JButton("����", new ImageIcon("images/reboot.png"));
			break;
		case "˯��(��Ϣ���~)":
			button = new JButton("˯��", new ImageIcon("images/sleep.png"));
			break;
		case "ע���(�༭ע���)":
			button = new JButton("ע���", new ImageIcon("images/regedit.png"));
			break;
		case "������(������)":
			button = new JButton("������", new ImageIcon("images/calc.png"));
			break;
		case "�ҵĵ���(���ҵĵ���)":
			button = new JButton("�ҵĵ���", new ImageIcon("images/computer.png"));
			break;
		case "д�ְ�(�ǼǱʼǰ�~)":
			button = new JButton("д�ְ�", new ImageIcon("images/write.png"));
			break;
		case "��ͼ��(�������»��»���~)":
			button = new JButton("��ͼ��", new ImageIcon("images/draw.png"));
			break;
		case "�������(������ĵ���)":
			button = new JButton("�������", new ImageIcon("images/control.png"));
			break;
		default:
			// �������վ
			File file = new File(temp[2]);
			// ��վ��ͷ��ʽ1
			String webBeginOne = "http://";
			// ��վ��ͷ��ʽ2
			String webBeginTwo = "https://";
			if (temp[2].startsWith(webBeginOne) || temp[2].startsWith(webBeginTwo)) {
				button = new JButton(temp[1], new ImageIcon("images/websiteT.png"));
			}
			// ������
			else {
				try {
					button = new JButton(temp[1], new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)));
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
			}
			break;
		}
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setPreferredSize(new Dimension(70, 60));
		// ����toolTip
		button.setToolTipText(text);
		// ��ӵ����¼�
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = ((JButton) e.getSource()).getToolTipText();
				// �ָ��temp[0]Ϊnull,temp[1]�ļ�������,,temp[2]Ϊ·��
				String[] temp = filePath.split("(<[^\\>]+> *)+");
				// ִ�д��ļ�
				String cmd = "rundll32 url.dll FileProtocolHandler file://" + temp[2];
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return button;
	}

	/**
	 * �����ڰ�ť��������������ʱ�����ù�����
	 * 
	 */
	public void reserScroll() {
		// �ܹ��������ɵ�����
		int allAmount = 12;
		// ÿһ�пο�ռ�ݵ�button������
		int eachLine = 4;
		// �������ɵ����� 3����4��֪��
		int allLine = 3;
		// ��ȡ��ǰ���ڵ�button����
		int tempbb = panelBottom.getComponents().length;
		// ��ȡ����
		if (tempbb > allAmount) {
			if (tempbb % eachLine == 0) {
				// ����ڼ���
				tempbb = tempbb / eachLine;
				tempbb = tempbb - allLine;
			} else {
				// ����ڼ���
				tempbb = (tempbb / eachLine) + 1;
				tempbb = tempbb - allLine;
			}
			// ���¶��崰�ڵ�Dimension,�Ա��й�������ʾ
			panelBottom.setPreferredSize(new Dimension(351, (200 + 69 * tempbb)));
		}
	}

}
