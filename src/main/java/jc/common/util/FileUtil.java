package jc.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletResponse;

/**
 * util of file
 * @author JC
 * @Date 2019年11月17日
 * @since 1.0.0
 */
public class FileUtil {

	public static interface SUFFIX{
		//图片后缀
		public static final String PNG = "png";
		public static final String JPG = "jpg";
		public static final String JPEG = "jpeg";
		//视频后缀
		public static final String MP4 = "mp4";
		//文件后缀
		public static final String XLS = "xls";
		public static final String XLSX = "xlsx";
		//压缩包后缀
		public static final String ZIP = "zip";
		//
		public static final String HTML = "html";
	}
	/**
	 * 判断是否是excel文件
	 * @param suffix:xls，xlsx
	 * @return
	 */
	public static boolean isExcel(String suffix) {
		suffix = suffix.toLowerCase();
		if(!SUFFIX.XLS.equals(suffix)&&!SUFFIX.XLSX.equals(suffix)) {
			return false;
		}
		return true;
	}
	/**
	 * 判断是否是mp4文件
	 * @param suffix 后缀
	 * @return
	 */
	public static boolean isMP4(String suffix) {
		suffix = suffix.toLowerCase();
		if(!SUFFIX.MP4.equals(suffix)) {
			return false;
		}
		return true;
	}
	/**
	 * inputStream转文件
	 * @param ins
	 * @param file
	 * @return
	 */
	public static boolean inputstreamToFile(InputStream ins,File file){
		OutputStream os;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 判断是否是jpt或者png文件
	 * @param suffix
	 * @return
	 */
	public static boolean isImage(String suffix) {
		suffix = suffix.toLowerCase();
		if(!SUFFIX.JPG.equals(suffix)&&!SUFFIX.PNG.equals(suffix)) {
			return false;
		}
		
		return true;
	}
    /**
     * 是否zip结尾
     * @param fileName
     * @return
     */
    public static boolean isEndWithZip(String fileName) {
        if(fileName != null && !"".equals(fileName.trim())) {
            if(fileName.endsWith(".ZIP")||fileName.endsWith(".zip")){
                return true;
            }
        }
        return false;
    }    
	/**
	 * 下载文件
	 * @param file
	 * @param response
	 */
	public static void downLoadFile(File file,HttpServletResponse response){
		try{
	        BufferedInputStream br = new BufferedInputStream(file.toURI().toURL().openStream());
	        byte[] buf = new byte[1024]; 
	        int len = 0; 
	        response.reset(); 
	        response.setContentType("application/x-msdownload"); //下载的文件类型
	        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName()); 
	        OutputStream out = response.getOutputStream(); 
	        while ((len = br.read(buf)) > 0){ 
	        	out.write(buf, 0, len); 
	        }
	        br.close(); 
	        out.close();
	    }catch (MalformedURLException e){
	        e.printStackTrace();
	    }catch (IOException e){
	        e.printStackTrace();
	    }
	}
    /**
     * 把zip文件解压到指定的文件夹
     * @param zipFilePath   zip文件路径, 如 "D:/test/aa.zip"
     * @param saveFileDir   解压后的文件存放路径, 如"D:/test/"
     */
    public static List<File> decompressZipToPicture(String zipFilePath,String saveFileDir) {
    	List<File> list = new ArrayList<File>();
        if(isEndWithZip(zipFilePath)) {
            ZipFile file;
			try {
				file = new ZipFile(zipFilePath);
	            Enumeration<? extends ZipEntry> entitys = file.entries();
	            while(entitys.hasMoreElements()) {
	            	ZipEntry zipEntry = entitys.nextElement();
	            	InputStream is = file.getInputStream(zipEntry);
	            	String entryFileName = zipEntry.getName();
	            	String entryFilePath = saveFileDir + entryFileName;
	            	String suffix = entryFilePath.substring(entryFilePath.indexOf(".")+1,entryFilePath.length());
	            	File entryFile = new File(entryFilePath);
	            	if(zipEntry.isDirectory()) {
	            		File dir = new File(entryFilePath.substring(0,entryFilePath.length()-1));
                    	if(dir.exists()) {
                    		continue;
                    	}
                    	dir.mkdir();
                    	continue;
	            	}
	            	if(!FileUtil.isImage(suffix)) {
	            		continue;
	            	}
	            	if(!entryFile.exists()) {
	            		entryFile.createNewFile();
	            	}
	            	System.out.println("entryFileName="+entryFileName+",fileName="+entryFile.getName());
	            	FileUtil.inputstreamToFile(is, entryFile);
	            	list.add(entryFile);
	            }
			} catch (IOException e) {
			}
        }
        return list;
    }
    /**
     * 判断是否是html文件
     * @param suffix
     * @return
     */
	public static boolean isHtml(String suffix) {
		suffix = suffix.toLowerCase();
		if(!SUFFIX.HTML.equals(suffix)&&!SUFFIX.HTML.equals(suffix)) {
			return false;
		}
		return true;
	}
	/**
	 * 获取文件名后缀
	 * @param fileName 文件名
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if(!fileName.contains(".")) {
			return "";
		}
		String suffix = fileName.substring(fileName.indexOf(".")+1, fileName.length());
		return suffix;
	}
    /**
     * 读取字符内容
     * @Description 读取HTML文件，获取字符内容
     * @param file 文件
     * @param charset 如：UTF-8
     * @return
     */
    public static String getFileContent(File file, String charset){

        String line = null;   
        StringBuilder sb = new StringBuilder(); 
        BufferedReader reader = null;

        try {  
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
            while ((line = reader.readLine()) != null) {   
                sb.append(line + "\n");
            } 
        } catch (IOException e) {   
            e.printStackTrace();
            throw new RuntimeException("读取文件，获取字符内容异常");
        } finally {   
            try {   
                reader.close();   
            } catch (IOException e) {  
                e.printStackTrace();
                throw new RuntimeException("关闭流异常");
            }   
        }   
        return sb.toString();
    }	
    
    /**
	 * Recursively list files in srcDir.
	 *
	 * @return ArrayList with String paths of File under srcDir (relative to srcDir)
	 */
	public static String[] listFiles(File srcDir) {
		ArrayList<String> result = new ArrayList<String>();
		if ((null != srcDir) && srcDir.canRead()) {
			listFiles(srcDir, null, result);
		}
		return result.toArray(new String[0]);
	}
	
	private static void listFiles(final File baseDir, String dir, ArrayList<String> result) {
		final String dirPrefix = (null == dir ? "" : dir + "/");
		final File dirFile = (null == dir ? baseDir : new File(baseDir.getPath() + "/" + dir));
		final String[] files = dirFile.list();
		for (int i = 0; i < files.length; i++) {
			File f = new File(dirFile, files[i]);
			String path = dirPrefix + files[i];
			if (f.isDirectory()) {
				listFiles(baseDir, path, result);
			} else {
				result.add(path);
			}
		}
	}
	
	/**
	 * 下载url文件
	 * @param url
	 * @param path
	 */
	public static void downloadPicture(String url, String path) {
		try {
			DataInputStream dataInputStream = new DataInputStream(new URL(url).openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
				e.printStackTrace();
		} 
	}

}
