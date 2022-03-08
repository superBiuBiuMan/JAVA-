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
 * JFrame窗口 MyJFrame窗口
 *  @author WmqZj
 */
public class MyJframe {
	/** 创建窗口,并设置窗口标题名 */
	public static JFrame jFrame = new JFrame("plus");
	/** 左边白色的东东 */
	public static JList list = new JList();
	/** 记录Jlist东西(记录左边项目的东西) */
	public static Vector<String> listJl = new Vector<String>();

	public static JScrollPane scrollPane = new JScrollPane();
	/** 每一个panel的JButton的数量 */
	public static ArrayList<Integer> panelComAmount = new ArrayList<Integer>();
	/** panel数组 记录每一个panel */
	public static ArrayList<JPanel> panelArray = new ArrayList<JPanel>();
	/** 获取当前panel的编号记录 */
	public static int panelIndex = 0;
	/** 白色东东左边的文本内容 */
	public static ArrayList<String> wwrName = new ArrayList<String>();

	public static int jl = 0;
	/** 每一行占据的button的个数 */
	public static int eachLine = 0;
	/** 其实是allLine+1 可容纳的数量 */
	public static int allLine = 0;
	/** 窗口可容纳的button的数量 */
	public static int allAmount = 0;
	/** JFrame窗口的X坐标 */
	public static int X_coordinate = 0;
	/** JFrame窗口的Y坐标 */
	public static int Y_coordinate = 0;
	/** JFrame窗口的宽度 */
	public static int Width = 0;
	/** JFrame窗口高度 */
	public static int Height = 0;
//	/**获取当前的panel*/
//	public static JPanel nowPanel=panelArray.get(panelIndex);
	/** 菜单栏 */
	public JMenuBar menuBar;
	public JMenu menu;
	public JMenuItem itemSelect;
	public JMenuItem itemFind;
	public JMenuItem itemExit;
	public DropTarget dropTarget;
	public MyJframeTopMenuActionListener menuListener;
	public MyJframeCompPositionListener changePosition;
	/**
	 * 左边白色的东东的滚动条
	 */
	public JScrollPane jsp;

	public MyJframe(boolean ini) {
		// 分配内存空间
		newAll();
		// 添加左边位置变化的监视器,以此来设置panelindex
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// 当值发生变化时候的监视器
				if (e.getValueIsAdjusting()) {
					// 设置当前索引
					MyJframe.panelIndex = MyJframe.list.getSelectedIndex();
					MyJframe.scrollPane.setViewportView(MyJframe.panelArray.get(MyJframe.panelIndex));
					// 设置现行选中项索引
					new MyIniReadAndWrite().changeSelected(false);
				}
			}
		});

		// 初始化配置文件
		new MyIniReadAndWrite().iniIni();
		// 设置选择的窗口
		list.setSelectedIndex(panelIndex);
		// 根据左边白色数量的创建panel
		creatPanel();
		// 初始化配置文件
		new MyIniReadAndWrite().getInit();
		// 右边设置默认显示panel
		scrollPane.setViewportView(MyJframe.panelArray.get(panelIndex));
		/*
		 * 设置属性
		 */
		// 设置JFrame
		setJframe();
		// 设置JList list
		setJlist();
		// 设置滚动条
		setJscrollPane();
		// 设置JMenuItem
		setMenu();
		/* 添加进 */
		addAll();
		/* 添加监视器 */
		addListener();
		// 为每一个panel的滚动条初始化
		new MyScrollPane().iniAll();

	}

	public MyJframe() {

	}

	/**
	 * 根据左边白色数量的创建panel
	 */
	public void creatPanel() {
		for (int i = 0; i < listJl.size(); i++) {
			createPanel();
		}
	}

	/**
	 * 为所有成员分配内存空间
	 */
	public void newAll() {
		// 分配内存空间
		menuBar = new JMenuBar();
		menu = new JMenu("文件");
		itemSelect = new JMenuItem("选择文件", new ImageIcon("images/selectfile.png"));
		itemFind = new JMenuItem("查找文件", new ImageIcon("images/findfile.png"));
		itemExit = new JMenuItem("退出", new ImageIcon("images/Exit.png"));
		jsp = new JScrollPane(list);
		// 放在最后这种添加监视器的，因为要传入一部分参数
		menuListener = new MyJframeTopMenuActionListener();
		changePosition = new MyJframeCompPositionListener(jsp);
		dropTarget = new DropTarget(jFrame, DnDConstants.ACTION_COPY_OR_MOVE, new MyDropFileListener(), true);
	}

	/**
	 * 设置菜单栏选项
	 */
	public void setMenu() {
		// 为菜单栏文件【查找文件】设置ctrl+f 快捷键
		itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		// 为菜单栏文件【选择文件】设置ctrl+h 快捷键
		itemSelect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
	}

	/**
	 * 添加进去组件
	 */
	public void addAll() {
		// 添加
		menu.add(itemSelect);
		menu.add(itemFind);
		menu.add(itemExit);
		menuBar.add(menu);
		jFrame.add(scrollPane);
		jFrame.add(jsp);
	}

	/**
	 * 添加各种监视事件
	 */
	public void addListener() {
		itemSelect.addActionListener(menuListener);
		itemFind.addActionListener(menuListener);
		itemExit.addActionListener(menuListener);
		// 添加右键监听器
		list.addMouseListener(new MyListLeftMouseListener());
		jFrame.addComponentListener(changePosition);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				// 窗口最小化时dispose该窗口
				jFrame.dispose();
			}
		});

	}

	/**
	 * 设置JFrame各种组件属性
	 */
	public void setJframe() {
		jFrame.setJMenuBar(menuBar);
		// 设置基础属性
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置位置
		jFrame.setBounds(X_coordinate, Y_coordinate, Width, Height);

	}

	/**
	 * 设置JList list属性
	 */
	public void setJlist() {
		list.setFixedCellHeight(50);
		list.setFixedCellWidth(100);
		// 设置list字体
		list.setFont(new Font("黑体", Font.PLAIN, 12));
		// 设置list字体剧中
		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 设置字体居中
		list.setCellRenderer(renderer);
//		// list添加记录
//		for (int i = 0; i < rName.size(); i++) {
//			listJl.add(rName.get(i));
//
//		}
		// 设置list列表的文字数据
		list.setListData(listJl);

		// 设置水平滚动条不可见
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// 设置无边框
		jsp.setBorder(null);
		// 指定了JScrollPane的大小(默认450）和位置，就是设定左边白色的宽高和位置xx460,不过后期这个好像没有用
		jsp.setBounds(0, 0, 100, 450);

	}

	/**
	 * 设置右边的JSCrollPane
	 */
	public void setJscrollPane() {
		// 设置无边框
		scrollPane.setBorder(null);
		// 设置滚动条滚动的速度
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		// 设置水平滚动条不可见
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// 设置垂直滚动条不可见
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		// 把滚动条添加到窗体当中，同时也设定了宽高 重点和panel
		scrollPane.setBounds(100, 0, 350, 800);
		// 设置右边ScrollPane的大小
		scrollPane.setPreferredSize(new Dimension(350, 800));
		// 设置默认显示panel
		scrollPane.setViewportView(panelArray.get(panelIndex));
	}

	/**
	 * 创建panel并设置背景图片
	 */
	public void createPanel() {
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout());
		pan.setPreferredSize(new Dimension(350, 800));
		MyJframe.jl++;
		pan.addMouseListener(new MyJframeRightMenu());
		MyJframe.panelArray.add(pan);
		// 必须要,不然会错误
		MyJframe.panelComAmount.add(0);

	}
}
