package util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by jianying.lin@foxmail.com on 2016/10/24.
 */
public class FileDirectory {
	
	/*
	 * 获取文件夹下的一层文件和目录
	 * @param folderPath
	 * @return 文件夹下的一层文件和目录的绝对路径
	 */
	public static ArrayList<String> getFileAndDirectoryPathUnderFolder(String folderPath) {
		File folder = new File(folderPath);
		if (!folder.isDirectory()) {
			System.err.println("Please pass the folder path inside the 'getFileAndDirectoryPathUnderFolder' function!!!");
			return null;
		}
		ArrayList<String> fileAndDirectoryPathList = new ArrayList<String>();
		String[] fileList = folder.list();
		for (int i=0; i<fileList.length; i++) {
			fileAndDirectoryPathList.add(filePathJoin(folder.getAbsolutePath(), fileList[i]));
		}
		return fileAndDirectoryPathList;
	}
	
	/*
	 * 将文件夹路径与文件名拼接在一起形成文件路径，能够适应Windows和Linux的拼接
	 * @param folder
	 * @param fileName
	 * @return folder与fileName的拼接
	 */
	public static String filePathJoin(String folder, String fileName) {
		Path path = Paths.get(folder, fileName);
		return path.toString();
	}
}
