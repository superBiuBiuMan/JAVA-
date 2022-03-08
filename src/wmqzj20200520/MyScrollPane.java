package wmqzj20200520;

import java.awt.Dimension;

/**
 * 使得窗口会随着组件的添加而出现滚动条 MyScrollPane滚动条
 * 
 * @author WmqZj
 */

public class MyScrollPane {

	/**
	 * 窗口启动的时候初始化滚动条
	 */
	public void iniAll() {
		for (int i = 0; i < MyJframe.panelComAmount.size(); i++) {
			// 获取当前窗口的button数量;
			int temp = MyJframe.panelComAmount.get(i);
			if (temp > MyJframe.allAmount) {
				if (temp % MyJframe.eachLine == 0) {
					// 计算第几行
					temp = temp / MyJframe.eachLine;
					temp = temp - MyJframe.allLine;
				} else {
					// 计算第几行
					temp = (temp / MyJframe.eachLine) + 1;
					temp = temp - MyJframe.allLine;
				}
				// 重新定义窗口的Dimension,以便有滚动条显示
				MyJframe.panelArray.get(i).setPreferredSize(new Dimension(351, (800 + 69 * temp)));
			}

		}
	}

	/**
	 * 当窗口发生改变或者数量超出滚动的时候重置滚动条
	 * 
	 * @param indexP -要重置滚动条的panel
	 */
	public void reserScroll(int indexP) {
		// 获取当前窗口的button数量
		int tempbb = MyJframe.panelComAmount.get(indexP) + 1;
		// 获取行数
		if (tempbb > MyJframe.allAmount) {
			if (tempbb % MyJframe.eachLine == 0) {
				// 计算第几行
				tempbb = tempbb / MyJframe.eachLine;
				tempbb = tempbb - MyJframe.allLine;
			} else {
				// 计算第几行
				tempbb = (tempbb / MyJframe.eachLine) + 1;
				tempbb = tempbb - MyJframe.allLine;
			}
			// 重新定义窗口的Dimension,以便有滚动条显示
			MyJframe.panelArray.get(indexP).setPreferredSize(new Dimension(351, (800 + 69 * tempbb)));
		}
	}
}
