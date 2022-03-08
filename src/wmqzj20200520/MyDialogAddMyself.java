package wmqzj20200520;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sun.awt.shell.ShellFolder;

/**
 * �Զ���Ի��� �Ի������һ��label��һ���ı����2����ť��
 * @author WmqZj
 */
public class MyDialogAddMyself extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	/** �ı������������ַ��� */
	JTextField fileAndWebSitePath;
	JTextField fileAndWebStieName;
	/** ��ȷ������ť */
	JButton button1;
	/** ȡ����ť */
	JButton button2;
	/** ����ѡ���ж�,��-��վ����,��-�Զ������� */
	boolean choice;

	/**
	 * 
	 * @param prentFrame -���ര��
	 * @param title      -����
	 * @param choice     -Ϊ������Զ���·�����룬Ϊ�ٴ��������վ
	 */
	public MyDialogAddMyself(JFrame prentFrame, String title, boolean choice) {
		// ���ø���Ĺ��캯����
		// ������������false��ʾ�������������塣Ϊtrue��ʾ���ܹ�������������
		super(prentFrame, title, true);
		this.choice = choice;
		// ����
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		// ���Label�������ı���
		JLabel labe1 = new JLabel("·��:", new ImageIcon("images/path.png"), JLabel.LEFT);
		JLabel labe2 = new JLabel("����:", new ImageIcon("images/title.png"), JLabel.LEFT);
		fileAndWebSitePath = new JTextField(30);
		fileAndWebStieName = new JTextField(30);
		// ͼƬΪһ�ѹ���
		button1 = new JButton("", new ImageIcon("images/ensure_before.png"));
		// ͼƬΪһ�Ѳ��
		button2 = new JButton("", new ImageIcon("images/cancel_before.png"));

		// ���ð�ť����
		setButton(button1, 30, 30);
		setButton(button2, 30, 30);

		// hoverͼƬ������
		button1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button1.setIcon(new ImageIcon("images/ensure_before.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button1.setIcon(new ImageIcon("images/ensure_after.png"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		// �л�ͼƬ������
		button2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button2.setIcon(new ImageIcon("images/cancel_before.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button2.setIcon(new ImageIcon("images/cancel_after.png"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		// ���ȷ���¼�������
		fileAndWebSitePath.addActionListener(this);
		fileAndWebStieName.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		// ������
		container.add(labe1);
		container.add(fileAndWebSitePath);
		container.add(labe2);
		container.add(fileAndWebStieName);
		container.add(button1);
		container.add(button2);

		// �����Ի��򲼾ִ�С
		pack();
	}

	public MyDialogAddMyself() {
	}

	/**
	 * �¼�����
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// ���������ȡ����ť
		if (event.getSource() == button2) {
			fileAndWebSitePath.setText("");
			fileAndWebStieName.setText("");

			setVisible(false);
		} else {
			// ������Զ���·������
			if (choice) {
				// ��ȡ�û����������
				String name = fileAndWebStieName.getText();
				// ��ȡ�����ľ���·��
				String temp1 = fileAndWebSitePath.getText() + "\\" + name;
				// ��ȡ����·��
				File path = new File(temp1);
				// �ж�webSiteName�Ƿ�Ϊ��
				boolean isNullWebSiteName = temp1 != null && temp1.equals("") != true;
				// �ж�fieldWebsite�Ƿ�Ϊ��
				boolean isNullFieldWebSite = name != null && name.equals("") != true;
				// Ϊ��ʱ������ť
				if (isNullWebSiteName && isNullFieldWebSite) {
					String lnkEnd = ".lnk";
					// ������ʼ��
					String writeTemp = "";
					// ��ȡ��׺��
					String getExtenEnd = name.substring(name.lastIndexOf("."), name.length());
					// �����׺��Ϊlnk
					if (getExtenEnd.equals(lnkEnd)) {
						// ��ȡ��ʵ��ͼ��·����Ҫ�Ĳ���
						try {
							MyGetReallyFilePath getReal = new MyGetReallyFilePath(new File(temp1));
							// ��ȡ��ʵ��ͼ��·��
							String realFileName = getReal.getRealFilename();
							// ����ָ���µ�ֵ
							path = new File(realFileName);
							// ����ָ���µ�ֵ
							temp1 = realFileName;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this, "����ʧ��,����·��Ϊ�����Ƿ���ȷ!ע��,������Ҫ��Ӻ�׺��!");
							e.printStackTrace();
						}

					}
					// ɾ����ͼ������ʾ�ĺ�׺��
					String newFileName = name.substring(0, name.lastIndexOf("."));
					writeTemp = temp1 + "###" + newFileName;
					try {
						new MyCreateMySelfButton().createMyButton(
								new ImageIcon(ShellFolder.getShellFolder(path).getIcon(true)), newFileName, temp1,
								MyJframe.panelIndex, true, writeTemp);
						fileAndWebSitePath.setText("");
						fileAndWebStieName.setText("");
						setVisible(false);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(this, "����ʧ��,����·��Ϊ�����Ƿ���ȷ!ע��,������Ҫ��Ӻ�׺��!");
						e.printStackTrace();
					}

				} else {
					// ��׽���쳣,����������Ϣ��
					JOptionPane.showMessageDialog(this, "��������������Ϣ!");
				}
			}
			// Ϊ�ٴ�����վ����
			else {
				// ��ȡ��վ����
				String webSitePath = fileAndWebSitePath.getText();
				// ��ȡ�û��������վ����
				String webName = fileAndWebStieName.getText();
				// ��վ��ͷ��ʽ1
				String webBeginOne = "https://";
				// ��վ��ͷ��ʽ2
				String webBeginTwo = "http://";
				// �ж�webSiteName�Ƿ�Ϊ��
				boolean isNullWebSiteName = webSitePath != null && webSitePath.equals("") != true;
				// �ж�fieldWebsite�Ƿ�Ϊ��
				boolean isNullFieldWebSite = webName != null && webName.equals("") != true;
				if (isNullWebSiteName && isNullFieldWebSite) {
					if (webSitePath.startsWith(webBeginOne) || webSitePath.startsWith(webBeginTwo)) {
						// ����Ҫ����վ,����
						create(webSitePath, webName);

					} else {
						// ������Ҫ����վ,���http��ȥ���ڽ��д���
						String newWebSitePath = "https://" + webSitePath;
						create(newWebSitePath, webName);
					}

				}
				// ��׽���쳣,����������Ϣ��
				else {
					JOptionPane.showMessageDialog(this, "��������������Ϣ!");

				}
			}
		}
	}

	//
	/**
	 * ����button������
	 * 
	 * @param button -Ҫ���õ�button
	 * @param width  -Ҫ���õĿ�
	 * @param height -Ҫ���õĸ�
	 */
	public void setButton(JButton button, int width, int height) {
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		// �����Ƿ���Ʊ߿�
//		button.setBorderPainted(false);
//		button.setContentAreaFilled(false);
//		button.setFocusPainted(false);
		// ���ð�ť�Ŀ�
		button.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * ������ť�����¼��������
	 * 
	 * @param webSitePath -��վ·��
	 * @param webName     -��վ����
	 */
	public void create(String webSitePath, String webName) {
		JButton buttonTemp = new JButton(webName, new ImageIcon("images/websiteT.png"));
		// ����button����
		setButton(buttonTemp, 70, 60);
		String temp = "<html><strong><font size=4>" + webName + "</font> </strong><br>" + webSitePath + "<br></html>";
		buttonTemp.setToolTipText(temp);
		// ���ù�����
		new MyScrollPane().reserScroll(MyJframe.panelIndex);
		// ����϶�������
		buttonTemp.addMouseMotionListener(MyCreateMySelfButton.mousedrag);
		buttonTemp.addActionListener(new ActionListener() {
			// ��������ҳ
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = ((JButton) e.getSource()).getToolTipText();
				// �ָ��temp[0]Ϊnull,temp[1]�ļ�������,,temp[2]Ϊ·��
				String[] temp = filePath.split("(<[^\\>]+> *)+");
				// ִ�д��ļ�
				String cmd = "rundll32 url.dll,FileProtocolHandler " + temp[2];
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonTemp.addMouseListener(new MyButtonRightAndDragFile());

		// ��ӵ�����
		MyJframe.panelArray.get(MyJframe.panelIndex).add(buttonTemp);
		int temp1 = MyJframe.panelComAmount.get(MyJframe.panelIndex) + 1;
		MyJframe.panelComAmount.set(MyJframe.panelIndex, temp1);
		// д���ļ� ·��+�ָ����#+�ļ���+�ָ�+��� // ����·��+�ָ����+�ļ���+�����������
		String writeTemp = webSitePath + "###" + webName + "###" + "WebSite";
		new MyDataIniReadAndWrite().write(MyJframe.panelComAmount.get(MyJframe.panelIndex), writeTemp);
		new MyIniReadAndWrite().changAmount(MyJframe.panelComAmount.get(MyJframe.panelIndex));
		MyJframe.jFrame.validate();
		setVisible(false);
		// �����ú����ԭ�е����ݲ�����Ϊ����
		fileAndWebSitePath.setText("");
		fileAndWebStieName.setText("");
		setVisible(false);
	}
}