package wmqzj20200520;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

/**
 * ����϶��ƶ�λ�� MyMouseDrag����϶�ʵ��
 * 
 * @author WmqZj
 */
public class MyMouseDrag implements MouseMotionListener {
	public static JButton two = null;
	public static boolean selected = false;

	@Override
	public void mouseDragged(MouseEvent e) {
		if (selected == false) {
			two = (JButton) e.getSource();
			selected = true;

		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
