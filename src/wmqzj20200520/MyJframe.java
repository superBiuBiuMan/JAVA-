package wmqzj20200520;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.glass.events.KeyEvent;

/**
 * JFrame���� MyJFrame����
 *  @author WmqZj
 */
public class MyJframe {
	/** ��������,�����ô��ڱ����� */
	public static JFrame jFrame = new JFrame("plus");
	/** ��߰�ɫ�Ķ��� */
	public static JList list = new JList();
	/** ��¼Jlist����(��¼�����Ŀ�Ķ���) */
	public static Vector<String> listJl = new Vector<String>();

	public static JScrollPane scrollPane = new JScrollPane();
	/** ÿһ��panel��JButton������ */
	public static ArrayList<Integer> panelComAmount = new ArrayList<Integer>();
	/** panel���� ��¼ÿһ��panel */
	public static ArrayList<JPanel> panelArray = new ArrayList<JPanel>();
	/** ��ȡ��ǰpanel�ı�ż�¼ */
	public static int panelIndex = 0;
	/** ��ɫ������ߵ��ı����� */
	public static ArrayList<String> wwrName = new ArrayList<String>();

	public static int jl = 0;
	/** ÿһ��ռ�ݵ�button�ĸ��� */
	public static int eachLine = 0;
	/** ��ʵ��allLine+1 �����ɵ����� */
	public static int allLine = 0;
	/** ���ڿ����ɵ�button������ */
	public static int allAmount = 0;
	/** JFrame���ڵ�X���� */
	public static int X_coordinate = 0;
	/** JFrame���ڵ�Y���� */
	public static int Y_coordinate = 0;
	/** JFrame���ڵĿ�� */
	public static int Width = 0;
	/** JFrame���ڸ߶� */
	public static int Height = 0;
//	/**��ȡ��ǰ��panel*/
//	public static JPanel nowPanel=panelArray.get(panelIndex);
	/** �˵��� */
	public JMenuBar menuBar;
	public JMenu menu;
	public JMenuItem itemSelect;
	public JMenuItem itemFind;
	public JMenuItem itemExit;
	public DropTarget dropTarget;
	public MyJframeTopMenuActionListener menuListener;
	public MyJframeCompPositionListener changePosition;
	/**
	 * ��߰�ɫ�Ķ����Ĺ�����
	 */
	public JScrollPane jsp;

	public MyJframe(boolean ini) {
		// �����ڴ�ռ�
		newAll();
		// ������λ�ñ仯�ļ�����,�Դ�������panelindex
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// ��ֵ�����仯ʱ��ļ�����
				if (e.getValueIsAdjusting()) {
					// ���õ�ǰ����
					MyJframe.panelIndex = MyJframe.list.getSelectedIndex();
					MyJframe.scrollPane.setViewportView(MyJframe.panelArray.get(MyJframe.panelIndex));
					// ��������ѡ��������
					new MyIniReadAndWrite().changeSelected(false);
				}
			}
		});

		// ��ʼ�������ļ�
		new MyIniReadAndWrite().iniIni();
		// ����ѡ��Ĵ���
		list.setSelectedIndex(panelIndex);
		// ������߰�ɫ�����Ĵ���panel
		creatPanel();
		// ��ʼ�������ļ�
		new MyIniReadAndWrite().getInit();
		// �ұ�����Ĭ����ʾpanel
		scrollPane.setViewportView(MyJframe.panelArray.get(panelIndex));
		/*
		 * ��������
		 */
		// ����JFrame
		setJframe();
		// ����JList list
		setJlist();
		// ���ù�����
		setJscrollPane();
		// ����JMenuItem
		setMenu();
		/* ��ӽ� */
		addAll();
		/* ��Ӽ����� */
		addListener();
		// Ϊÿһ��panel�Ĺ�������ʼ��
		new MyScrollPane().iniAll();

	}

	public MyJframe() {

	}

	/**
	 * ������߰�ɫ�����Ĵ���panel
	 */
	public void creatPanel() {
		for (int i = 0; i < listJl.size(); i++) {
			createPanel();
		}
	}

	/**
	 * Ϊ���г�Ա�����ڴ�ռ�
	 */
	public void newAll() {
		// �����ڴ�ռ�
		menuBar = new JMenuBar();
		menu = new JMenu("�ļ�");
		itemSelect = new JMenuItem("ѡ���ļ�", new ImageIcon("images/selectfile.png"));
		itemFind = new JMenuItem("�����ļ�", new ImageIcon("images/findfile.png"));
		itemExit = new JMenuItem("�˳�", new ImageIcon("images/Exit.png"));
		jsp = new JScrollPane(list);
		// �������������Ӽ������ģ���ΪҪ����һ���ֲ���
		menuListener = new MyJframeTopMenuActionListener();
		changePosition = new MyJframeCompPositionListener(jsp);
		dropTarget = new DropTarget(jFrame, DnDConstants.ACTION_COPY_OR_MOVE, new MyDropFileListener(), true);
	}

	/**
	 * ���ò˵���ѡ��
	 */
	public void setMenu() {
		// Ϊ�˵����ļ��������ļ�������ctrl+f ��ݼ�
		itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		// Ϊ�˵����ļ���ѡ���ļ�������ctrl+h ��ݼ�
		itemSelect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
	}

	/**
	 * ��ӽ�ȥ���
	 */
	public void addAll() {
		// ���
		menu.add(itemSelect);
		menu.add(itemFind);
		menu.add(itemExit);
		menuBar.add(menu);
		jFrame.add(scrollPane);
		jFrame.add(jsp);
	}

	/**
	 * ��Ӹ��ּ����¼�
	 */
	public void addListener() {
		itemSelect.addActionListener(menuListener);
		itemFind.addActionListener(menuListener);
		itemExit.addActionListener(menuListener);
		// ����Ҽ�������
		list.addMouseListener(new MyListLeftMouseListener());
		jFrame.addComponentListener(changePosition);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				// ������С��ʱdispose�ô���
				jFrame.dispose();
			}
		});

	}

	/**
	 * ����JFrame�����������
	 */
	public void setJframe() {
		jFrame.setJMenuBar(menuBar);
		// ���û�������
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ����λ��
		jFrame.setBounds(X_coordinate, Y_coordinate, Width, Height);

	}

	/**
	 * ����JList list����
	 */
	public void setJlist() {
		list.setFixedCellHeight(50);
		list.setFixedCellWidth(100);
		// ����list����
		list.setFont(new Font("����", Font.PLAIN, 12));
		// ����list�������
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		// �����������
		list.setCellRenderer(renderer);
//		// list��Ӽ�¼
//		for (int i = 0; i < rName.size(); i++) {
//			listJl.add(rName.get(i));
//
//		}
		// ����list�б����������
		list.setListData(listJl);

		// ����ˮƽ���������ɼ�
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// �����ޱ߿�
		jsp.setBorder(null);
		// ָ����JScrollPane�Ĵ�С(Ĭ��450����λ�ã������趨��߰�ɫ�Ŀ�ߺ�λ��xx460,���������������û����
		jsp.setBounds(0, 0, 100, 450);

	}

	/**
	 * �����ұߵ�JSCrollPane
	 */
	public void setJscrollPane() {
		// �����ޱ߿�
		scrollPane.setBorder(null);
		// ���ù������������ٶ�
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		// ����ˮƽ���������ɼ�
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// ���ô�ֱ���������ɼ�
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		// �ѹ�������ӵ����嵱�У�ͬʱҲ�趨�˿�� �ص��panel
		scrollPane.setBounds(100, 0, 350, 800);
		// �����ұ�ScrollPane�Ĵ�С
		scrollPane.setPreferredSize(new Dimension(350, 800));
		// ����Ĭ����ʾpanel
		scrollPane.setViewportView(panelArray.get(panelIndex));
	}

	/**
	 * ����panel�����ñ���ͼƬ
	 */
	public void createPanel() {
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout());
		pan.setPreferredSize(new Dimension(350, 800));
		MyJframe.jl++;
		pan.addMouseListener(new MyJframeRightMenu());
		MyJframe.panelArray.add(pan);
		// ����Ҫ,��Ȼ�����
		MyJframe.panelComAmount.add(0);

	}
}
