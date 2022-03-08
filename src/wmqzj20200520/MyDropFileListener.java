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
 * 文件拽托到窗体上 MyDropListener拖动文件
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
				// 接收拖拽目标数据
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				isAccept = true;

				// 以文件集合的形式获取数据
				List<File> files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				// 把文件路径输出到文本区域
				if (files != null && files.size() > 0) {
					for (File file : files) {
						String tempPath = null;
						String fileName = null;
						// 获取路径
						tempPath = file.getAbsolutePath();
						// 文件夹名称
						fileName = file.getName();
						if (file.isDirectory() == true && tempPath.length() != 3) {
							tempPath = tempPath + "\\";
//							System.out.println("找到文件夹");
							String writeTemp = tempPath + "###" + fileName;
							new MyCreateMySelfButton().createMyButton(
									new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)), fileName, tempPath,
									MyJframe.panelIndex, true, writeTemp);

						}
						// 判断是否为盘符
						else if (tempPath.length() == 3) {
							FileSystemView fsv = FileSystemView.getFileSystemView();
							String tempName = fsv.getSystemDisplayName(file);
							fileName = tempName;
//							System.out.println("找到盘符");
							String writeTemp = tempPath + "###" + fileName;

							new MyCreateMySelfButton().createMyButton(
									new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)), fileName, tempPath,
									MyJframe.panelIndex, true, writeTemp);

						} else {
							// 获取后缀名，看是否是以 .lnk 结尾还是其他结尾
							String getExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
							String lnkEnd = ".lnk";
							// 说明是.lnk结尾的
							if (getExtension.equals(lnkEnd)) {
								// 获取真实的图标路径必要的步骤
								MyGetReallyFilePath getReal = null;
								try {
									getReal = new MyGetReallyFilePath(new File(tempPath));
								} catch (IOException | ParseException e) {

									e.printStackTrace();
								}
								// 获取真实的图标路径
								String realFileName = getReal.getRealFilename();
								// 删除在图标中显示的后缀名
								String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
								// 要写入文件的数据
								String writeTemp = realFileName + "###" + newFileName;
								// 使用新的目录位置获取图标
								File trueFile = new File(realFileName);
								// 写入文件 路径+分割符号#+文件名+分割+编号 // 路径+分割符号#+文件名file
								new MyCreateMySelfButton().createMyButton(
										new ImageIcon(ShellFolder.getShellFolder(trueFile).getIcon(true)), newFileName,
										realFileName, MyJframe.panelIndex, true, writeTemp);
								// tempPath改为realFileName
							}
							// 其他后缀名结尾的 .exe .txt等等
							else {
								// 写入文件
								// 删除在图标中显示的后缀名
								String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
								// 要写入文件的值
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
			JOptionPane.showMessageDialog(MyJframe.jFrame, "出错了，请重试!");
			e.printStackTrace();
		}

		// 如果此次拖拽的数据是被接受的, 则必须设置拖拽完成（否则可能会看到拖拽目标返回原位置, 造成视觉上以为是不支持拖拽的错误效果）
		if (isAccept) {
			dtde.dropComplete(true);
		}
	}

//	/**
//	 * 获取快捷方式指向的目标
//	 * 
//	 * @param shortcutPath 快捷方式路径
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
