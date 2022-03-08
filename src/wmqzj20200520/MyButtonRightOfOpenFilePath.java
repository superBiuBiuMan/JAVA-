package wmqzj20200520;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 * 右键按钮之打开所在文件夹
 * MyOpenFilePath右键按钮之打开所在文件夹
 *@author WmqZj
 */
public class MyButtonRightOfOpenFilePath {
	public static JButton button;

	public MyButtonRightOfOpenFilePath(JButton button) {
		MyButtonRightOfOpenFilePath.button = button;
	}
	/**
	 * 右键按钮之打开所在文件夹
	 */
	public static void openFileLocation() {
		String temp = button.getToolTipText();
		// 分割后temp_1[0]为null,temp_1[1]文件的名称,,temp_1[2]为路径
		String[] splitAfter = temp.split("(<[^\\>]+> *)+");
		System.out.println(temp+"temp");
		File file = new File(splitAfter[2]);
		// 如果得到temp_1[2]是一个目录
		if (file.isDirectory()) {
			String cmd = "rundll32 url.dll FileProtocolHandler file://" + splitAfter[2];
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "出错了，请重试！");
				e1.printStackTrace();
			}
		} else {
			int lastAppear = splitAfter[2].lastIndexOf("\\");
			//cmd命令
			String executeCmd = splitAfter[2].substring(0, lastAppear);
			String cmd = "rundll32 url.dll FileProtocolHandler file://" + executeCmd;
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "出错了，请重试！");
				e1.printStackTrace();
			}

		}
	}
}
