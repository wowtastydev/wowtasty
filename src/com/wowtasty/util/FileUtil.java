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

import org.apache.log4j.Logger;

/**
 * @author Hak C.
 *
 */
public class FileUtil {
	
	/** Logger */	
	private static Logger logger = Logger.getLogger(FileUtil.class);
	
	/**Config property name*/
	public static final String CONFIG_PICT_PATH = "picturePath";
	public static final String CONFIG_PICT_WIDTH = "pictureWidth";
	public static final String CONFIG_PICT_HEIGHT = "pictureHeight";
	public static final String CONFIG_THUMB_WIDTH = "thumbnailWidth";
	public static final String CONFIG_THUMB_HEIGHT = "thumbnailHeight";
	
	/** constants */
	public static final String PREFIX_THUMB = "thumb_";
	public static final String STR_TEMP = ".temp";
	public static final String WOWTASTY_DIR = "wowtasty/";
	public static final String RESTAURANT_DIR = "restaurant/";
	public static final String MENU_DIR = "menu/";
	public static final String DELIVERY_DIR = "delivery/";

	/** config.properties */	
	private static Properties config = (Properties)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CONFIG_PROPERTIES);

	/**
	 * delete image and thumbnail image
	 * @param filename : filename
	 */
	public static void deletePict(String dir, String fileName){
		String dirPath = "";
		String imgPath ="";
		String thumbPath ="";
		File imgFile = null;
		File thumbFile = null;
		StringBuilder sb = new StringBuilder(config.getProperty(CONFIG_PICT_PATH));
		
		try{
			// Get image upload pathfrom config.properties
			dirPath = sb.append(dir).toString();
			
			// Original file
			sb.setLength(0);
			imgPath = sb.append(dirPath).append(fileName).toString();
			
			// Thumbnail file
			sb.setLength(0);
			thumbPath = sb.append(dirPath).append(PREFIX_THUMB).append(fileName).toString();
			
			//delete original file
			imgFile = new File(imgPath);
			imgFile.delete();
			
			//delete thumbnail file
			thumbFile = new File(thumbPath);
			thumbFile.delete();
			
		} catch (Exception e) {
			logger.error("!!!!!FileUtil deletePict occurs error:" + e);
        	throw new RuntimeException(e);
		} 
	}
	
	/**
	 * upload image and create thumbnail image
	 * @param inFile : input File
	 * @param dir : RESTAURANT/MENU/WOWTASTY/DELIVERY directory
	 * @param fileName : output file name
	 */
	public static void writePict(File inFile, String dir, String fileName){
		String dirPath = "";
		String imgPath ="";
		int imgWidth = 0;
		int imgHeight = 0;
		String thumbPath ="";
		int thumbWidth = 0;
		int thumbHeight = 0;
		StringBuilder sb = new StringBuilder(config.getProperty(CONFIG_PICT_PATH));
			
		try{
			// Get image upload pathfrom config.properties
			dirPath = sb.append(dir).toString();
			
			// Original file
			sb.setLength(0);
			imgPath = sb.append(dirPath).append(fileName).toString();
			
			// Thumbnail file
			sb.setLength(0);
			thumbPath = sb.append(dirPath).append(PREFIX_THUMB).append(fileName).toString();
			
			// Image/Thumbnail with, height
			imgWidth = Integer.parseInt(config.getProperty(CONFIG_PICT_WIDTH));
			imgHeight = Integer.parseInt(config.getProperty(CONFIG_PICT_HEIGHT));
			thumbWidth = Integer.parseInt(config.getProperty(CONFIG_THUMB_WIDTH));
			thumbHeight = Integer.parseInt(config.getProperty(CONFIG_THUMB_HEIGHT));
				
			// Image Upload
			writePict(inFile, new File(imgPath), imgWidth, imgHeight);
			// Thumbnail Upload
			writePict(inFile, new File(thumbPath), thumbWidth, thumbHeight);
		} catch (Exception e) {
			logger.error("!!!!!FileUtil writePict occurs error:" + e);
        	throw new RuntimeException(e);
		} 
	}
	
	/**
	 * rename image and thumbnail image
	 * @param orgFileName : original image file name
	 * @param newFileName : new image file name
	 */
	public static void renameRestImage(String orgFileName, String newFileName){
		String imgPath ="";
		String thumbPath ="";
		String newImgPath ="";
		String newThumbPath ="";
		// Get image upload path from config.properties
		StringBuilder sb = new StringBuilder(config.getProperty(CONFIG_PICT_PATH));
		String dir = sb.append(RESTAURANT_DIR).toString();
		
		File file = null;
		
		try{
			// Get image upload path and size from config.properties
			imgPath = sb.append(orgFileName).toString();
			sb.setLength(0);
			newImgPath= sb.append(dir).append(newFileName).toString();
			
			sb.setLength(0);
			thumbPath = sb.append(dir).append(PREFIX_THUMB).append(orgFileName).toString();
			sb.setLength(0);
			newThumbPath= sb.append(dir).append(PREFIX_THUMB).append(newFileName).toString();
			
			// Rename file to temp file
			file = new File(imgPath);
			if(file.exists()) {
				file.renameTo(new File(newImgPath));
			}
			file = new File(thumbPath);
			if(file.exists()) {
				file.renameTo(new File(newThumbPath));
			}
			
		} catch (Exception e) {
			logger.error("!!!!!FileUtil renameImage occurs error:" + e);
        	throw new RuntimeException(e);
		} 
	}
	
	/**
	 * delete temp image and thumbnail image
	 * @param fileName : image file name
	 */
	public static void deleteRestTempImage(String fileName){
		String imgPath ="";
		String thumbPath ="";
		// Get image upload path from config.properties
		StringBuilder sb = new StringBuilder(config.getProperty(CONFIG_PICT_PATH));
		String dir = sb.append(RESTAURANT_DIR).toString();
		File file = null;
		
		try{
			imgPath = sb.append(fileName).append(STR_TEMP).toString();
			
			sb.setLength(0);
			thumbPath = sb.append(dir).append(PREFIX_THUMB).append(fileName).append(STR_TEMP).toString();
			
			// Delete temp file
			file = new File(imgPath);
			if(file.exists()) {
				file.delete();
			}
			file = new File(thumbPath);
			if(file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			logger.error("!!!!!FileUtil deleteRestTempImage occurs error:" + e);
        	throw new RuntimeException(e);
		} 
	}
	
	/**
	 * Upload image with resizing and changing image type of jpg
	 * @param loadFile : upload image file
	 * @param outFile : output image file
	 * @param width : maximum image width
	 * @param height : maximum image height
	 * @return
	 */
	public static void writePict(File loadFile, File outFile, int w, int h) {
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
			// If there is the same name file, delete and write
			if (outFile.exists()) {
				outFile.delete();
			}
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
	public static File downloadOrderList(ArrayList<Object> dataList, String[] titles) {
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
