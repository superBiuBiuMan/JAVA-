package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MyJframeMenuActionListener菜单监视器
 * @author WmqZj
 * 
 */
public class MyJframeTopMenuActionListener implements ActionListener {
	/**
	 * 鼠标单击Menu的事件，根据单击的是那一个执行相应的代码
	 */
	String selectFile = "选择文件";
	String selectFindFile = "查找文件";
	String selectExit = "退出";

	@Override
	public void actionPerformed(ActionEvent e) {
		// 选择文件菜单项
		if (e.getActionCommand().equals(selectFile)) {
			new MyMenuSelectFile();
		}
		// 查找文件菜单项
		else if (e.getActionCommand().equals(selectFindFile)) {
			new MyFindFile();

		} 
		//退出选项
		else if (e.getActionCommand().equals(selectExit)) {
			System.exit(0);
		}
	}

}
