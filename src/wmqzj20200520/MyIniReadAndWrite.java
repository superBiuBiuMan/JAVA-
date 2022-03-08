package wmqzj20200520;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

/**
 * 读取写入配置项 MyIni读取写入配置项
 * 
 * @author WmqZj
 */
public class MyIniReadAndWrite {
	File filePath;
	Wini ini;
	/** 0为非首次启动,1为首次启动 */
	int i = 0;

	/**
	 * 初始化数据
	 */
	public MyIniReadAndWrite() {
		filePath = new File("C:\\Myini.ini");
		if (filePath.exists() == false) {
			try {
				filePath.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			i = 1;
		}
		try {
			ini = new Wini(filePath);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化数据
	 */
	public void iniIni() {
		// 首次启动,写入并读入数据
		if (i == 1) {
			ini.put("MyMainOption", "是否开机启动", 0);
			ini.put("MyMainOption", "窗口数量", 2);
			// 参数依次为要写入的节名称,名称,值
			ini.put("MyMainOption", "现行选中窗口", 0);
			ini.put("Other", "MyItem", "[常用软件, 系统工具]");
			// 当前窗口0的button数量为0 初始值
			ini.put("Amount", "0", "0");
			// 当前窗口1的button数量为0 初始值
			ini.put("Amount", "1", "0");
			// 每一行占据的button的个数 初始值
			ini.put("JFrame", "eachLine", 4);
			// 其实是allLine+1 窗口可容纳的可容纳的行数 初始值
			ini.put("JFrame", "allLine", 6);
			// 窗口可容纳的button的数量 初始值
			ini.put("JFrame", "allAmount", 28);
			// JFrame窗口的初始X坐标 初始值
			ini.put("JFrame", "X_coordinate", 400);
			// JFrame窗口的初始Y坐标 初始值
			ini.put("JFrame", "Y_coordinate", 250);
			// JFrame窗口的初始宽度 初始值
			ini.put("JFrame", "Width", 465);
			// JFrame窗口的初始高度 初始值
			ini.put("JFrame", "Height", 525);
			getIni();
			try {
				ini.store();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 非首次启动,读入数据
		else {
			getIni();
		}
	}

	/**
	 * 获取Myini的数据
	 */
	public void getIni() {
		String name = ini.get("Other", "MyItem");
		// 删除左中括号
		name = name.replace("[", "");
		// 删除右中括号
		name = name.replace("]", "");
		String[] temp = name.split(",");
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
			// 添加到左边白色东东
			MyJframe.listJl.add(temp[i]);
		}
		// 读取现行选中窗口
		MyJframe.panelIndex = Integer.parseInt(ini.get("MyMainOption", "现行选中窗口"));
		/* 读取 */
		// 每一行占据的button的个数
		MyJframe.eachLine = Integer.parseInt(ini.get("JFrame", "eachLine"));
		// 其实是allLine+1 可容纳的数量
		MyJframe.allLine = Integer.parseInt(ini.get("JFrame", "allLine"));
		// 窗口可容纳的button的数量
		MyJframe.allAmount = Integer.parseInt(ini.get("JFrame", "allAmount"));
		// JFrame窗口的X坐标
		MyJframe.X_coordinate = Integer.parseInt(ini.get("JFrame", "X_coordinate"));
		// JFrame窗口的Y坐标
		MyJframe.Y_coordinate = Integer.parseInt(ini.get("JFrame", "Y_coordinate"));
		// JFrame窗口的宽度
		MyJframe.Width = Integer.parseInt(ini.get("JFrame", "Width"));
		// JFrame窗口高度;
		MyJframe.Height = Integer.parseInt(ini.get("JFrame", "Height"));

	}

	/**
	 * 左边变色添加选项并写入数据文件
	 * 
	 * @param temp -新添加的文件名
	 */
	public void changeMyItem(String temp) {
		List<String> name = new ArrayList<String>();
		for (int i = 0; i < MyJframe.listJl.size(); i++) {
			name.add(MyJframe.listJl.get(i));
		}
		ini.put("Other", "MyItem", name);
		ini.put("Amount", String.valueOf((MyJframe.panelArray.size() - 1)), 0);
		ini.put("MyMainOption", "窗口数量", MyJframe.panelArray.size());

		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 删除，重命名选项后修改文件数据
	 * 
	 * @param choice -是否删除整个列表 ,为真,则删除左边整个列表中选择的那个列表
	 */
	public void changeMyItem(boolean choice) {
		List<String> name = new ArrayList<String>();
		for (int i = 0; i < MyJframe.listJl.size(); i++) {
			name.add(MyJframe.listJl.get(i));
		}
		ini.put("Other", "MyItem", name);
		ini.put("MyMainOption", "窗口数量", MyJframe.panelArray.size());
		// choice为真,删除左边整个列表
		if (choice == true) {
			ini.remove("Amount");
			// 重新写入Amount的值
			for (int i = 0; i < MyJframe.panelComAmount.size(); i++) {
				ini.put("Amount", String.valueOf(i), String.valueOf(MyJframe.panelComAmount.get(i)));
			}
		}

		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 改变当前选择的panel编号
	 * 
	 * @param choice
	 */
	public void changeSelected(boolean choice) {
		if (choice == true) {
			// 要写入的节名称,名称,值
			ini.put("MyMainOption", "现行选中窗口", (MyJframe.panelIndex - 1));
		} else {
			// 要写入的节名称,名称,值
			ini.put("MyMainOption", "现行选中窗口", MyJframe.panelIndex);
		}
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 获取窗口组件数量并放置在MyJFrame.panelComAmount
	 */
	public void getInit() {
		for (int i = 0; i < MyJframe.panelArray.size(); i++) {
			String sl = ini.get("Amount", String.valueOf(i));
			MyJframe.panelComAmount.set(i, Integer.parseInt(sl));
		}

	}

	/**
	 * 当窗口的按钮数量发生变化,重新写入
	 * 
	 * @param amount -重新写入的新数量值
	 */
	public void changAmount(int amount) {

		ini.put("Amount", String.valueOf(MyJframe.panelIndex), String.valueOf(amount));
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 当窗口的位置,大小发生变化,重写写入文件
	 */
	public void changeJframe() {
		// 每一行占据的button的个数 初始值
		ini.put("JFrame", "eachLine", MyJframe.eachLine);
		// 其实是allLine+1 可容纳的数量 初始值
		ini.put("JFrame", "allLine", MyJframe.allLine);
		// 窗口可容纳的button的数量 初始值
		ini.put("JFrame", "allAmount", MyJframe.allAmount);
		// JFrame窗口的初始宽度 初始值
		ini.put("JFrame", "Width", MyJframe.Width);
		// JFrame窗口的初始高度 初始值
		ini.put("JFrame", "Height", MyJframe.Height);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/**
	 * 当改变窗口的坐标时候，重新写入文件
	 */
	public void changeJframePosition() {
		// JFrame窗口的初始X坐标 初始值
		ini.put("JFrame", "X_coordinate", MyJframe.X_coordinate);
		// JFrame窗口的初始Y坐标 初始值
		ini.put("JFrame", "Y_coordinate", MyJframe.Y_coordinate);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
