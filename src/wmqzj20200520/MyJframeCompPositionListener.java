package wmqzj20200520;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JScrollPane;

/**
 * 当窗口位置改变的时候，使得位置信息可以被写入数据和保证滚动条的正常显示 MyJFrameComp位置变化的监视器
 * 
 * @author WmqZj
 */
public class MyJframeCompPositionListener implements ComponentListener {
	public JScrollPane jsp;

	/**
	 * 初始化
	 * 
	 * @param jsp -指定会改变的jsp
	 */
	public MyJframeCompPositionListener(JScrollPane jsp) {
		this.jsp = jsp;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {

	}

	/**
	 * 窗口移动
	 */
	@Override
	public void componentMoved(ComponentEvent arg0) {
		// 获取改变后的JFramex坐标
		MyJframe.X_coordinate = MyJframe.jFrame.getLocationOnScreen().x;
		// 获取改变后的JFramey坐标
		MyJframe.Y_coordinate = MyJframe.jFrame.getLocationOnScreen().y;
		// 写入文件新位置
		new MyIniReadAndWrite().changeJframePosition();
	}

	/**
	 * 窗口位置被调整
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		// 获取改变后的JFrame高度
		MyJframe.Height = MyJframe.jFrame.getHeight();
		// 获取改变后的JFrame宽度
		MyJframe.Width = MyJframe.jFrame.getWidth();
		// 设置jsp新的宽度，并重新放置
		jsp.setBounds(0, 0, 100, MyJframe.Height-70);
		MyJframe.scrollPane.setBounds(100, 0, MyJframe.Width - 115, 800);
		int getWi1 = MyJframe.scrollPane.getWidth() - MyJframe.scrollPane.getVerticalScrollBar().getWidth() - 43;
		MyJframe.eachLine = (int) Math.ceil((getWi1 / 70) * 1.0);
		// 重置滚动条
		new MyScrollPane().reserScroll(MyJframe.panelIndex);
		// 文件写入新数据
		new MyIniReadAndWrite().changeJframe();

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}
}
