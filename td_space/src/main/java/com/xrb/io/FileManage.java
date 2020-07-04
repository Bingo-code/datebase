package com.xrb.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileManage {
	private static FileManage fileManage;
	public static synchronized FileManage getFileManage() {
		if(fileManage==null) {
			fileManage = new FileManage();
		}
		return fileManage;
	}
	public static Integer addFile(MultipartFile file, String writePath, boolean replace) {
		try {
			
			Integer addInteger = 1;
			File writeFile = new File(writePath);
			
			if(!writeFile.exists()) {
				writeFile.createNewFile();
			}else if(replace) {
				addInteger = 2;
			}else{
				return 0;
			}
			
			FileOutputStream write = new FileOutputStream(writeFile,false);
			InputStream read = file.getInputStream();
			byte[] len = new byte[10*1024*1024];
			int n = 0;
			
			while ((n=read.read(len)) != -1) {
				write.write(len, 0, n);
			}
			
			write.close();
			read.close();
			
			return addInteger;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public static int delFile(String delFilePath) {
		File target = new File(delFilePath);
		if(target.exists()) {
			target.delete();
			return 1;
		}else {
			return 0;
		}
	}
	
	public static boolean renameFile(String oldFilePath,String newFilePath ) {
		File oldFile = new File(oldFilePath);
		File newFile = new File(newFilePath);
		
		return oldFile.renameTo(newFile);
	}
}
