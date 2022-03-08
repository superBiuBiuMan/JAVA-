package wmqzj20200520;

import java.awt.Component;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * ɾ����ť 
 * MyDeleteComponentɾ����ť
 * @author WmqZj
 */
public class MyDeleteComponent {
	static JButton ChButton;

	/**
	 * ��ȡҪɾ���İ�ť
	 * 
	 * @param button -Ҫɾ���İ�ť
	 */
	public MyDeleteComponent(JButton button) {
		ChButton = button;
	}

	/**
	 * ɾ����ť��ִ��һϵ�У�����˵ɾ���ļ������²��ֵ�
	 */
	public void deleteComponent() {
		MyJframe.panelArray.get(MyJframe.panelIndex).remove(ChButton);
//		MyJFrame����.panelArray.get(MyJFrame����.panelIndex).repaint();
		// ɾ���ļ���¼,Ȼ�� ��д����
		deleteFile();
		// ˢ�����,���²���
		restore();
		// �������ù�����
		new MyScrollPane().reserScroll(MyJframe.panelIndex);
		;
//		new MyScrollPane������().reserScroll(MyJFrame����.panelIndex);
//		MyJFrame����.jFrame.validate();
	}

	public MyDeleteComponent() {
	}

	/**
	 * ɾ���ļ���¼,Ȼ�� ��дini����ÿ��panel���ڵ�����
	 */
	public void deleteFile() {
		// �����ж��Ƿ���·������Ŀ¼
		File file;
		new MyDataIniReadAndWrite().remove(MyJframe.panelIndex);
		Component[] items1 = MyJframe.panelArray.get(MyJframe.panelIndex).getComponents();
		// �����ǵ��ı�����
		String[] special = { "�������(������ĵ���)", "������(������)", "˯��(��Ϣ���~)", "д�ְ�(�ǼǱʼǰ�~)", "�ҵĵ���(���ҵĵ���)", "����(��������Ŷ~)",
				"�ػ�(����ػ�Ŷ~)", "ע���(�༭ע���)", "��ͼ��(�������»��»���~)" };
		for (int i = 0; i < items1.length; i++) {
			// ָ��Ҫ��ָ�,0Ϊ��,1Ϊ����,2λ·����
			String[] splitAfter = ((JButton) items1[i]).getToolTipText().split("(<[^\\>]+> *)+");
			file = new File(splitAfter[2]);
			// ��ΪĿ¼�����ļ����ݵ�ʱ�򣬶���������ͼ���ʱ��
			if (file.isFile() || file.isDirectory()) {
				new MyDataIniReadAndWrite().write((i + 1), (splitAfter[2] + "###" + splitAfter[1]));
			} else {
				// �ж��Ƿ���Specialͼ��
				if (isHave(special, splitAfter[1])) {
					new MyDataIniReadAndWrite().write((i + 1),
							(splitAfter[2] + "###" + splitAfter[1] + "###" + "Special"));
				}
				// �ж��Ƿ���WebSiteͼ��
				else if (splitAfter[2].matches("[a-zA-z]+://[^\\s]*")) {
					new MyDataIniReadAndWrite().write((i + 1),
							(splitAfter[2] + "###" + splitAfter[1] + "###" + "Website"));
				}
				// ��������ǣ���ô���ǲ����ڵ��ļ�·�������ļ����Ż᷵��false��������������д������
				else {
					// ���ò����ڵ�ͼ��Ϊnull
					((JButton) items1[i]).setIcon(new ImageIcon("images/null.png"));
					new MyDataIniReadAndWrite().write((i + 1), (splitAfter[2] + "###" + splitAfter[1]));
				}
			}
		}
		// ���ļ�д���µĴ�������
		new MyIniReadAndWrite().changAmount(items1.length);
		// ����д��ÿһ��panel��button����
		MyJframe.panelComAmount.set(MyJframe.panelIndex, items1.length);

	}

	/**
	 * ˢ�����,���²���
	 */
	public void restore() {
		// ��ȡ��ǰpanel���������
		Component[] items1 = MyJframe.panelArray.get(MyJframe.panelIndex).getComponents();
		// �Ƴ�
		MyJframe.panelArray.get(MyJframe.panelIndex).removeAll();
		for (int i = 0; i < items1.length; i++) {

			MyJframe.panelArray.get(MyJframe.panelIndex).add(((JButton) items1[i]));
		}
		// ˢ��
		MyJframe.scrollPane.setViewportView(MyJframe.panelArray.get(MyJframe.panelIndex));
	}

	/**
	 * �����ַ���s�Ƿ������special����
	 * 
	 * @param special -�������ַ�������
	 * @param checkStr       -Ҫ�����ַ���
	 * @return -�����ǰ��� �����ǲ�����
	 */
	public boolean isHave(String[] special, String checkStr) {
		for (int i = 0; i < special.length; i++) {
			if (special[i].equals(checkStr)) {
				return true;
			}
		}
		return false;

	}
}
