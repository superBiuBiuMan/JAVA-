package wmqzj20200520;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * ����ָ��Ҫ��ť
 * 
 */
public class MyCreateMySelfButton {
	/** �������������,�������� */
	String newName = null;
	/** ���ַ� */
	String nullName = "";
	/** ����ͼ��ĳ��� */
	int specialLength = 3;
	public static MyMouseDrag mousedrag = new MyMouseDrag();

	/**
	 * ������Ӧ��ͼ��
	 * 
	 * @param imageIcon -ͼ��
	 * 
	 * @param name      -����
	 * @param filePath  -�ļ�·��
	 * 
	 * @param indexP    -��Ӧ��panel
	 * 
	 * @param writeFile -�Ƿ�д�����ݵ��ļ�
	 * @param writeTemp -д�����ݵ��ļ����ַ���
	 */
	public void createMyButton(ImageIcon imageIcon, String name, String filePath, int indexP, boolean writeFile,
			String writeTemp) {
		JButton myButton = new JButton(name, imageIcon);

		// ���ð�ť����
		myButton.setVerticalTextPosition(JButton.BOTTOM);
		myButton.setHorizontalTextPosition(JButton.CENTER);
		// ��ʾ����
		myButton.setMargin(new Insets(0, 0, 0, 0));
		// �����Ƿ���Ʊ߿�

//		myButton.setBorderPainted(false);
		// ��ȥĬ�ϵı������
//		myButton.setContentAreaFilled(false);
		// ��ȥ����Ŀ�
//		myButton.setFocusPainted(false);
		// ���ð�ť�Ŀ��
		myButton.setPreferredSize(new Dimension(70, 60));

		// ��HTML����ʽ��tooltiptext������ʽ�޸�
		String temp = "<html><strong><font size=4>" + name + "</font> </strong><br>" + filePath + "<br></html>";
		// �����ΪӲ�̷���
		if (filePath.length() != specialLength) {
			// (aΪ����,bΪ·��) ������ʾ
			myButton.setToolTipText(temp);
		}
		// ���ΪӲ�̷���
		else if (filePath.length() == specialLength) {
			myButton.setToolTipText(temp);
		}

		// �����굥���ļ�����
		myButton.addActionListener(new ActionListener() {

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
		// ����϶�������
		myButton.addMouseMotionListener(mousedrag);
		// ����Ҽ�������
		myButton.addMouseListener(new MyButtonRightAndDragFile());
		MyJframe.panelArray.get(indexP).add(myButton);
		// ���ù�����
		new MyScrollPane().reserScroll(indexP);
		// ��Ӱ�ť��ȥָ�����
		MyJframe.panelArray.get(indexP).add(myButton);
		MyJframe.panelArray.get(indexP).validate();
		// �� д������,�� ��д������
		if (writeFile) {
			int temp1 = MyJframe.panelComAmount.get(MyJframe.panelIndex) + 1;
			MyJframe.panelComAmount.set(MyJframe.panelIndex, temp1);
			// д���ļ� ·��+�ָ����#+�ļ���+�ָ�+��� // ·��+�ָ����#+�ļ���
			new MyDataIniReadAndWrite().write(MyJframe.panelComAmount.get(MyJframe.panelIndex), writeTemp);
			new MyIniReadAndWrite().changAmount(MyJframe.panelComAmount.get(MyJframe.panelIndex));
		}
		MyJframe.jFrame.validate();
	}

}
