package wmqzj20200520;

import javax.swing.JOptionPane;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 * ��������� MyMain���������
 * 
 * @author WmqZj
 */

public class MyMain {
	public static void main(String[] args) {
		try {
			// ���ñ����Խ��ı䴰�ڱ߿���ʽ����
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�����ˣ�������jdk�汾ԭ���뻻�߰汾ԭ��");
		}
		// ������ʼ������
		new MyJframe(true);
		// ��������ͼ��
		new MyCreateSystemTray();
		// ��ȡ�����ļ�
		MyFileExistsRead myfileExists = new MyFileExistsRead();
		myfileExists.fileExists();
	}
}
