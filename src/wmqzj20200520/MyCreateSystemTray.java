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
 * ��������ͼ�����½� MyCreateSystemTray���������Ҽ�
 * 
 * @author WmqZj
 */
public class MyCreateSystemTray implements ActionListener, MouseListener {
	PopupMenu popupMenu;
	/**
	 * SystemTray ���ʾ�����ϵͳ����
	 */
	SystemTray systemTray;
	MenuItem item1;
	MenuItem item2;
	MenuItem item3;
	TrayIcon trayIcon;
	/**
	 * ��ֹ������ڱ������������
	 */
	boolean isTf;
	String selectExit = "�˳�����";
	String aboutSoftWare = "���ڱ����";
	String openframe = "�������";

	public MyCreateSystemTray() {
		initPopupMenu();
		initSystemTray();
//		isTf = false;
	}

	public static void main(String[] args) {
		new MyCreateSystemTray();
	}

	/**
	 * ��ʼ�����̲˵��Ҽ�
	 */
	public void initPopupMenu() {
		popupMenu = new PopupMenu();
		item2 = new MenuItem("���ڱ����");
		item3 = new MenuItem("�������");
		item1 = new MenuItem("�˳�����");

		popupMenu.add(item2);
		popupMenu.add(item3);
		popupMenu.add(item1);

		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
	}

	/**
	 * ��ʼ������
	 */
	public void initSystemTray() {
		// ��ȡ��ʾ������������ SystemTray ʵ��
		systemTray = SystemTray.getSystemTray();
		// �ж�ϵͳ�Ƿ�֧������
		if (SystemTray.isSupported()) {
			// ������ָ��ͼ�񡢹�����ʾ�͵����˵��� TrayIcon
			Image imageicon = new ImageIcon("images/tray.png").getImage();
			// ��������������ͼ��������ʾ��������Ϣ
			trayIcon = new TrayIcon(imageicon, "������,��~", popupMenu);
			trayIcon.addMouseListener(this);
			try {
				systemTray.add(trayIcon);
			} catch (AWTException e) {
				JOptionPane.showMessageDialog(null, "���̲˵���ʼ��ʧ��!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ѡ�����˳�
		if (e.getActionCommand().equals(selectExit)) {
			System.exit(0);
		}
		// ѡ���˹��ڱ����
		else if (e.getActionCommand().equals(aboutSoftWare)) {
			// ����չʾ�ı���Ϣ
			String textMessage = "<html>" + "����Ŀ�Ĵ��������Դ��VStart" + "<br>" + "����:���Žܡ� ����:�������桿" + "<br></html>";
			JOptionPane.showMessageDialog(null, textMessage);
		}
		// �������
		else if (e.getActionCommand().equals(openframe)) {
			// �ָ�����չʾ
			MyJframe.jFrame.setExtendedState(JFrame.NORMAL);// �ؼ�����
			MyJframe.jFrame.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// ��굥����������
		if (e.getButton() == MouseEvent.BUTTON1) {
			// �ָ�����չʾ
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