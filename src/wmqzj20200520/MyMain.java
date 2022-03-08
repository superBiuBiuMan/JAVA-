package wmqzj20200520;

import javax.swing.JOptionPane;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 * 主程序入口 MyMain主程序入口
 * 
 * @author WmqZj
 */

public class MyMain {
	public static void main(String[] args) {
		try {
			// 设置本属性将改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "出错了，可能是jdk版本原因！请换高版本原因！");
		}
		// 创建初始化窗口
		new MyJframe(true);
		// 创建托盘图标
		new MyCreateSystemTray();
		// 读取数据文件
		MyFileExistsRead myfileExists = new MyFileExistsRead();
		myfileExists.fileExists();
	}
}
