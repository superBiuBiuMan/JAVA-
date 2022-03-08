package wmqzj20200520;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 创建托盘图标右下角 MyCreateSystemTray创建托盘右键
 * 
 * @author WmqZj
 */
public class MyCreateSystemTray implements ActionListener, MouseListener {
	PopupMenu popupMenu;
	/**
	 * SystemTray 类表示桌面的系统托盘
	 */
	SystemTray systemTray;
	MenuItem item1;
	MenuItem item2;
	MenuItem item3;
	TrayIcon trayIcon;
	/**
	 * 防止点击关于本软件弹出窗口
	 */
	boolean isTf;
	String selectExit = "退出程序";
	String aboutSoftWare = "关于本软件";
	String openframe = "打开主面板";

	public MyCreateSystemTray() {
		initPopupMenu();
		initSystemTray();
//		isTf = false;
	}

	public static void main(String[] args) {
		new MyCreateSystemTray();
	}

	/**
	 * 初始化托盘菜单右键
	 */
	public void initPopupMenu() {
		popupMenu = new PopupMenu();
		item2 = new MenuItem("关于本软件");
		item3 = new MenuItem("打开主面板");
		item1 = new MenuItem("退出程序");

		popupMenu.add(item2);
		popupMenu.add(item3);
		popupMenu.add(item1);

		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
	}

	/**
	 * 初始化托盘
	 */
	public void initSystemTray() {
		// 获取表示桌面托盘区的 SystemTray 实例
		systemTray = SystemTray.getSystemTray();
		// 判断系统是否支持托盘
		if (SystemTray.isSupported()) {
			// 创建带指定图像、工具提示和弹出菜单的 TrayIcon
			Image imageicon = new ImageIcon("images/tray.png").getImage();
			// 设置鼠标放在托盘图标上面显示的文字信息
			trayIcon = new TrayIcon(imageicon, "启动我,快~", popupMenu);
			trayIcon.addMouseListener(this);
			try {
				systemTray.add(trayIcon);
			} catch (AWTException e) {
				JOptionPane.showMessageDialog(null, "托盘菜单初始化失败!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 选择了退出
		if (e.getActionCommand().equals(selectExit)) {
			System.exit(0);
		}
		// 选择了关于本软件
		else if (e.getActionCommand().equals(aboutSoftWare)) {
			// 设置展示文本信息
			String textMessage = "<html>" + "本项目的创作灵感来源于VStart" + "<br>" + "代码:【张杰】 美工:【万梦奇】" + "<br></html>";
			JOptionPane.showMessageDialog(null, textMessage);
		}
		// 打开主面板
		else if (e.getActionCommand().equals(openframe)) {
			// 恢复界面展示
			MyJframe.jFrame.setExtendedState(JFrame.NORMAL);// 关键代码
			MyJframe.jFrame.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 鼠标单击弹出窗口
		if (e.getButton() == MouseEvent.BUTTON1) {
			// 恢复界面展示
			MyJframe.jFrame.setExtendedState(JFrame.NORMAL);
			MyJframe.jFrame.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}