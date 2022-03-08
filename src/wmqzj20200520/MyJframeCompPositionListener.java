package wmqzj20200520;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JScrollPane;

/**
 * ������λ�øı��ʱ��ʹ��λ����Ϣ���Ա�д�����ݺͱ�֤��������������ʾ MyJFrameCompλ�ñ仯�ļ�����
 * 
 * @author WmqZj
 */
public class MyJframeCompPositionListener implements ComponentListener {
	public JScrollPane jsp;

	/**
	 * ��ʼ��
	 * 
	 * @param jsp -ָ����ı��jsp
	 */
	public MyJframeCompPositionListener(JScrollPane jsp) {
		this.jsp = jsp;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {

	}

	/**
	 * �����ƶ�
	 */
	@Override
	public void componentMoved(ComponentEvent arg0) {
		// ��ȡ�ı���JFramex����
		MyJframe.X_coordinate = MyJframe.jFrame.getLocationOnScreen().x;
		// ��ȡ�ı���JFramey����
		MyJframe.Y_coordinate = MyJframe.jFrame.getLocationOnScreen().y;
		// д���ļ���λ��
		new MyIniReadAndWrite().changeJframePosition();
	}

	/**
	 * ����λ�ñ�����
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		// ��ȡ�ı���JFrame�߶�
		MyJframe.Height = MyJframe.jFrame.getHeight();
		// ��ȡ�ı���JFrame���
		MyJframe.Width = MyJframe.jFrame.getWidth();
		// ����jsp�µĿ�ȣ������·���
		jsp.setBounds(0, 0, 100, MyJframe.Height-70);
		MyJframe.scrollPane.setBounds(100, 0, MyJframe.Width - 115, 800);
		int getWi1 = MyJframe.scrollPane.getWidth() - MyJframe.scrollPane.getVerticalScrollBar().getWidth() - 43;
		MyJframe.eachLine = (int) Math.ceil((getWi1 / 70) * 1.0);
		// ���ù�����
		new MyScrollPane().reserScroll(MyJframe.panelIndex);
		// �ļ�д��������
		new MyIniReadAndWrite().changeJframe();

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}
}
