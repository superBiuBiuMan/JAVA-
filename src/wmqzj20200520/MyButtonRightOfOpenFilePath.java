package wmqzj20200520;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 * �Ҽ���ť֮�������ļ���
 * MyOpenFilePath�Ҽ���ť֮�������ļ���
 *@author WmqZj
 */
public class MyButtonRightOfOpenFilePath {
	public static JButton button;

	public MyButtonRightOfOpenFilePath(JButton button) {
		MyButtonRightOfOpenFilePath.button = button;
	}
	/**
	 * �Ҽ���ť֮�������ļ���
	 */
	public static void openFileLocation() {
		String temp = button.getToolTipText();
		// �ָ��temp_1[0]Ϊnull,temp_1[1]�ļ�������,,temp_1[2]Ϊ·��
		String[] splitAfter = temp.split("(<[^\\>]+> *)+");
		System.out.println(temp+"temp");
		File file = new File(splitAfter[2]);
		// ����õ�temp_1[2]��һ��Ŀ¼
		if (file.isDirectory()) {
			String cmd = "rundll32 url.dll FileProtocolHandler file://" + splitAfter[2];
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "�����ˣ������ԣ�");
				e1.printStackTrace();
			}
		} else {
			int lastAppear = splitAfter[2].lastIndexOf("\\");
			//cmd����
			String executeCmd = splitAfter[2].substring(0, lastAppear);
			String cmd = "rundll32 url.dll FileProtocolHandler file://" + executeCmd;
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "�����ˣ������ԣ�");
				e1.printStackTrace();
			}

		}
	}
}
