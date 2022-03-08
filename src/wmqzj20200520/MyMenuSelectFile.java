package wmqzj20200520;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import sun.awt.shell.ShellFolder;

/**
 * ���û�ѡ���ˡ�ѡ���ļ�"��һ���˵���ѡ���ʱ�� MyMenuSelectFile�˵���֮ѡ���ļ�
 * 
 * @author WmqZj
 *
 */
public class MyMenuSelectFile {
	public MyMenuSelectFile() {
		// �ļ�ѡ��
		FileDialog fileDialog = new FileDialog(MyJframe.jFrame, "ѡ���ļ�", FileDialog.LOAD);
		// ��ʼ���ļ�ѡ���·��
		fileDialog.setDirectory("C:\\");
		// ����ѡ���ļ��Ի���
		fileDialog.setVisible(true);
		// ��ȡѡ�е��ļ�Ŀ¼
		String path = fileDialog.getDirectory();
		// ��ȡѡ�е��ļ�����(������׺��) :eg:�ļ�.txt
		String fileName = fileDialog.getFile();
		// ���ѡ��Ϊ�棬����ô�������
		if (path != null) {
			// ��ȡ�ļ�·��
			String absolutePath = path + fileDialog.getFile();
			File file = new File(absolutePath);
			// ɾ����ͼ������ʾ�ĺ�׺��
			String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
			try {
				String writeTemp = absolutePath + "###" + newFileName;
				// ������ť
				new MyCreateMySelfButton().createMyButton(new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)),
						newFileName, absolutePath, MyJframe.panelIndex, true, writeTemp);

			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "�����ˣ������Ǹ��ļ���֧�֣�");
				e1.printStackTrace();
			}
		}
	}
}
