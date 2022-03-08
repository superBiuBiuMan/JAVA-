package wmqzj20200520;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import sun.awt.shell.ShellFolder;

/**
 * 当用户选择了“选择文件"这一个菜单栏选项的时候 MyMenuSelectFile菜单栏之选择文件
 * 
 * @author WmqZj
 *
 */
public class MyMenuSelectFile {
	public MyMenuSelectFile() {
		// 文件选择
		FileDialog fileDialog = new FileDialog(MyJframe.jFrame, "选择文件", FileDialog.LOAD);
		// 初始化文件选择的路径
		fileDialog.setDirectory("C:\\");
		// 弹出选择文件对话框
		fileDialog.setVisible(true);
		// 获取选中的文件目录
		String path = fileDialog.getDirectory();
		// 获取选中的文件名称(包含后缀名) :eg:文件.txt
		String fileName = fileDialog.getFile();
		// 如果选择为真，则调用创建窗口
		if (path != null) {
			// 获取文件路径
			String absolutePath = path + fileDialog.getFile();
			File file = new File(absolutePath);
			// 删除在图标中显示的后缀名
			String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
			try {
				String writeTemp = absolutePath + "###" + newFileName;
				// 创建按钮
				new MyCreateMySelfButton().createMyButton(new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)),
						newFileName, absolutePath, MyJframe.panelIndex, true, writeTemp);

			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "出错了，可能是该文件不支持！");
				e1.printStackTrace();
			}
		}
	}
}
