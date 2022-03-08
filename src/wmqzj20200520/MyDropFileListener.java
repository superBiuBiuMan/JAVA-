package wmqzj20200520;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import sun.awt.shell.ShellFolder;

/**
 * �ļ�ק�е������� MyDropListener�϶��ļ�
 * 
 * @author WmqZj
 */
public class MyDropFileListener implements DropTargetListener {

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		boolean isAccept = false;
		try {
			if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				// ������קĿ������
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				isAccept = true;

				// ���ļ����ϵ���ʽ��ȡ����
				List<File> files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				// ���ļ�·��������ı�����
				if (files != null && files.size() > 0) {
					for (File file : files) {
						String tempPath = null;
						String fileName = null;
						// ��ȡ·��
						tempPath = file.getAbsolutePath();
						// �ļ�������
						fileName = file.getName();
						if (file.isDirectory() == true && tempPath.length() != 3) {
							tempPath = tempPath + "\\";
//							System.out.println("�ҵ��ļ���");
							String writeTemp = tempPath + "###" + fileName;
							new MyCreateMySelfButton().createMyButton(
									new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)), fileName, tempPath,
									MyJframe.panelIndex, true, writeTemp);

						}
						// �ж��Ƿ�Ϊ�̷�
						else if (tempPath.length() == 3) {
							FileSystemView fsv = FileSystemView.getFileSystemView();
							String tempName = fsv.getSystemDisplayName(file);
							fileName = tempName;
//							System.out.println("�ҵ��̷�");
							String writeTemp = tempPath + "###" + fileName;

							new MyCreateMySelfButton().createMyButton(
									new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)), fileName, tempPath,
									MyJframe.panelIndex, true, writeTemp);

						} else {
							// ��ȡ��׺�������Ƿ����� .lnk ��β����������β
							String getExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
							String lnkEnd = ".lnk";
							// ˵����.lnk��β��
							if (getExtension.equals(lnkEnd)) {
								// ��ȡ��ʵ��ͼ��·����Ҫ�Ĳ���
								MyGetReallyFilePath getReal = null;
								try {
									getReal = new MyGetReallyFilePath(new File(tempPath));
								} catch (IOException | ParseException e) {

									e.printStackTrace();
								}
								// ��ȡ��ʵ��ͼ��·��
								String realFileName = getReal.getRealFilename();
								// ɾ����ͼ������ʾ�ĺ�׺��
								String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
								// Ҫд���ļ�������
								String writeTemp = realFileName + "###" + newFileName;
								// ʹ���µ�Ŀ¼λ�û�ȡͼ��
								File trueFile = new File(realFileName);
								// д���ļ� ·��+�ָ����#+�ļ���+�ָ�+��� // ·��+�ָ����#+�ļ���file
								new MyCreateMySelfButton().createMyButton(
										new ImageIcon(ShellFolder.getShellFolder(trueFile).getIcon(true)), newFileName,
										realFileName, MyJframe.panelIndex, true, writeTemp);
								// tempPath��ΪrealFileName
							}
							// ������׺����β�� .exe .txt�ȵ�
							else {
								// д���ļ�
								// ɾ����ͼ������ʾ�ĺ�׺��
								String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
								// Ҫд���ļ���ֵ
								String writeTemp = tempPath + "###" + newFileName;

								new MyCreateMySelfButton().createMyButton(
										new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)), newFileName,
										tempPath, MyJframe.panelIndex, true, writeTemp);
							}

						}

					}
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(MyJframe.jFrame, "�����ˣ�������!");
			e.printStackTrace();
		}

		// ����˴���ק�������Ǳ����ܵ�, �����������ק��ɣ�������ܻῴ����קĿ�귵��ԭλ��, ����Ӿ�����Ϊ�ǲ�֧����ק�Ĵ���Ч����
		if (isAccept) {
			dtde.dropComplete(true);
		}
	}

//	/**
//	 * ��ȡ��ݷ�ʽָ���Ŀ��
//	 * 
//	 * @param shortcutPath ��ݷ�ʽ·��
//	 * @return
//	 */
//	private static String getShortcutTarget(String shortcutPath) {
//		String folder = shortcutPath.substring(0, shortcutPath.lastIndexOf("\\"));
//		String name = shortcutPath.substring(shortcutPath.lastIndexOf("\\") + 1, shortcutPath.length());
//
//		JShellLink link = new JShellLink(folder, name);
//		try {
//			link.load();
//		} catch (RuntimeException e) {
//			return null;
//		}
//		String targetPath = link.getPath();
//		return targetPath;
//	}

}
