package wmqzj20200520;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JFrame窗体的右键监视器菜单 MyJFrameRightMouseListener窗口右键项的监视器
 * 
 * @author WmqZj
 */
public class MyJframeRightMouseListener implements ActionListener {
	String selectAddFile = "添加文件";
	String selectAddWebSite = "添加网站";
	String selectAddOther = "添加其他";
	String selectAddMySelf = "手动添加";

	@Override
	public void actionPerformed(ActionEvent e) {
		int x = (MyJframe.jFrame.getWidth() - 210) / 2;
		int y = (MyJframe.jFrame.getHeight() - 190) / 2;
		Point topLeft = MyJframe.jFrame.getLocationOnScreen();
		// 添加文件
		if (e.getActionCommand().equals(selectAddFile)) {
			new MyMenuSelectFile();
		}
		// 添加网站
		else if (e.getActionCommand().equals(selectAddWebSite)) {
			// 最后一个参数为false代表为网站输入,为true为自定义输入
			MyDialogAddMyself dialog = new MyDialogAddMyself(MyJframe.jFrame, "请填写信息~", false);
			// 设置为居中显示
			dialog.setBounds(x + topLeft.x, y + topLeft.y, 210, 190);
			dialog.setVisible(true);
		}
		// 添加其他
		else if (e.getActionCommand().equals(selectAddOther)) {
			new MyJframeRightAddOther();
		}
		// 手动添加
		else if (e.getActionCommand().equals(selectAddMySelf)) {
			// 最后一个参数为false代表为网站输入,为true为自定义输入
			MyDialogAddMyself dialog = new MyDialogAddMyself(MyJframe.jFrame, "请填写信息~", true);
			// 设置为居中显示
			dialog.setBounds(x + topLeft.x, y + topLeft.y, 210, 190);
			dialog.setVisible(true);
		}

	}

}
