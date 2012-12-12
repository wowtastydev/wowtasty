package com.wowtasty.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * @author Hak C.
 *
 */
public class FileUtil {
	
	/** Logger */	
	private static Logger logger = Logger.getLogger(FileUtil.class);

	/** config.properties */	
	private Properties config = null;

	/**
     * Contructor : Set up File information from config.properties
     */   
	public FileUtil() {
		config = (Properties)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CONFIG_PROPERTIES);
	}
	
	/**
	 * delete file
	 * @param filePath
	 * @return boolean : true - sucess, false - fail
	 */
	public boolean removeFile(String filePath){
		File file = new File(filePath);
		return file.delete();
	}
	
	/**
	 * delete image and thumbnail image
	 * @param filePath
	 */
	public void removePictureFile(String filePath){
		File file = new File(filePath);
		//delete original file
		file.delete();
		
		//delete thumbnail file
		String filename =file.getName();
		String dir = file.getParent();
		StringBuilder sb = new StringBuilder(dir);
		sb.append(config.getProperty(Constants.CONFIG_DELIMETER));
		sb.append(config.getProperty(Constants.CONFIG_THUMBNAIL_DIR));
		sb.append(config.getProperty(Constants.CONFIG_DELIMETER));
		sb.append(filename);
		
		logger.debug("removePictureFile :" + sb.toString());
		
		File thumbfile = new File(sb.toString());
		thumbfile.delete();
	}
	
	/**
	 * upload image and create thumbnail image
	 * @param inFile : input File
	 * @param type : RESTAURANT/MENU
	 * @param fileName : output file name
	 * @return filepath : output file path
	 */
	public String writePict(File inFile, String type, String fileName){
		String imgPath ="";
		int imgWidth = 0;
		int imgHeight = 0;
		String thumbPath ="";
		int thumbWidth = 0;
		int thumbHeight = 0;
		StringBuilder sb = null;
		
		try{
			
			// Get image upload path and size from config.properties
			if ("RESTAURANT".equals(type)) {
				// RESTAURANT IMAGE
				sb = new StringBuilder(config.getProperty(Constants.CONFIG_REST_PICT_PATH));
				imgPath = sb.append(fileName).toString();
				imgWidth = Integer.parseInt(config.getProperty(Constants.CONFIG_REST_PICT_WIDTH));
				imgHeight = Integer.parseInt(config.getProperty(Constants.CONFIG_REST_PICT_HEIGHT));
				
				sb.setLength(0);
				sb.append(config.getProperty(Constants.CONFIG_REST_PICT_PATH));
				thumbPath = sb.append("thumb_").append(fileName).toString();
				thumbWidth = Integer.parseInt(config.getProperty(Constants.CONFIG_REST_PICT_THUMB_WIDTH));
				thumbHeight = Integer.parseInt(config.getProperty(Constants.CONFIG_REST_PICT_THUMB_HEIGHT));
			} else if ("MENU".equals(type)) {
				// MENU IMAGE
				sb = new StringBuilder(config.getProperty(Constants.CONFIG_MENU_PICT_PATH));
				imgPath = sb.append(fileName).toString();
				imgWidth = Integer.parseInt(config.getProperty(Constants.CONFIG_MENU_PICT_WIDTH));
				imgHeight = Integer.parseInt(config.getProperty(Constants.CONFIG_MENU_PICT_HEIGHT));
				
				sb.setLength(0);
				sb.append(config.getProperty(Constants.CONFIG_MENU_PICT_PATH));
				thumbPath = sb.append("thumb_").append(fileName).toString();
				thumbWidth = Integer.parseInt(config.getProperty(Constants.CONFIG_MENU_PICT_THUMB_WIDTH));
				thumbHeight = Integer.parseInt(config.getProperty(Constants.CONFIG_MENU_PICT_THUMB_HEIGHT));
			}
			// Image Upload
			writePict(inFile, new File(imgPath), imgWidth, imgHeight);
			// Thumbnail Upload
			writePict(inFile, new File(thumbPath), thumbWidth, thumbHeight);
		} catch (Exception e) {
			logger.error("!!!!!FileUtil writePict occurs error:" + e);
        	throw new RuntimeException(e);
		} 
		return imgPath;
	}
	
	/**
	 * Upload image with resizing and changing image type of jpg
	 * @param loadFile : upload image file
	 * @param outFile : output image file
	 * @param width : maximum image width
	 * @param height : maximum image height
	 * @return
	 */
	public void writePict(File loadFile, File outFile, int w, int h) {
		BufferedInputStream bis = null;
		try{
			bis = new BufferedInputStream(new FileInputStream(loadFile));
			BufferedImage bi = ImageIO.read(bis);
			
			int width = bi.getWidth();
			int height = bi.getHeight();

			
			if (w < width) {
				width = w;
			}
			if (h < height) {
				height = h;
			}

			BufferedImage thumbi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			Image img = bi.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
			Graphics2D g2 = thumbi.createGraphics();
			g2.drawImage(img, 0, 0, width, height, null);
			ImageIO.write(thumbi, "jpg", outFile);
			
		} catch (Exception e) {
			logger.error("!!!!!FileUtil writePict occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e) {
				logger.error("!!!!!FileUtil writePict occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * @param dataList : Output data list
	 * @param titles : Title string Array
	 * @return File : Output File
	 */
	public File downloadOrderList(ArrayList<Object> dataList, String[] titles) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		File dir = null;
		File returnFile = null;
		String tempdir = config.getProperty("thumbnailDir");
		try {
			dir = new File(tempdir);
			if(!dir.exists()) {
				dir.mkdir();
			}
			returnFile = new File(tempdir, "temp.xls");
			fw = new FileWriter(returnFile);
			bw = new BufferedWriter(fw);
			
			// Write titles
			for (int i = 0; i < titles.length; i++) {
				bw.write(titles[i]);
				bw.write("\t");
			}

			for (int i = 0; i < dataList.size() ; i++) {
				// Add Output data coding here
//				bw.write(Integer.toString(dataList.get(i).getOrderID()));
//				bw.write("\t");
//				bw.write(dataList.get(i).getPickupTimeyyyymmdd());
//				bw.write("\t");
//				bw.write(dataList.get(i).getDeliveryManName());
//				bw.write("\t");
//				bw.write(dataList.get(i).getReceptionistName());
//				bw.write("\t");
//				bw.write(dataList.get(i).getRestaurantName());
//				bw.write("\t");

			}
		} catch(Exception e) {
			logger.error("!!!!!FileUtil downloadOrderList occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch(Exception e) {
				logger.error("!!!!!FileUtil downloadOrderList occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		
		return returnFile;
	}
}
