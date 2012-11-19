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

/**
 * @author Hak C.
 *
 */
public class FileUtil {
	
	private Properties config = null;

	/**
     * Contructor : Set up File information from config.properties
     */   
	private FileUtil() {
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
		String thumbnailDir = config.getProperty("thumbnailDir");
		File thumbfile = new File(dir + thumbnailDir + filename);
		thumbfile.delete();
	}
	
	/**
	 * upload image and create thumbnail image
	 * @param inFile : input File
	 * @param pathProperty : output file path(restaurantPicturePath/menuPicturePath)
	 */
	public void writePict(File inFile, String pathProperty){
		// upload original piture
		// get image upload path and size from config.properties
		String uploadPath = config.getProperty(pathProperty);
		int width = Integer.parseInt(config.getProperty(pathProperty + "Width"));
		int height = Integer.parseInt(config.getProperty(pathProperty + "Height"));
		writePict(inFile, new File(uploadPath), width, height);
		
		// upload thumbnail piture
		// get thumbnail image upload path and size from config.properties
		String thumbnailDir = config.getProperty("thumbnailDir");
		uploadPath = uploadPath + thumbnailDir;
		width = Integer.parseInt(config.getProperty(pathProperty + "ThumbnailWidth"));
		height = Integer.parseInt(config.getProperty(pathProperty + "ThumbnailHeight"));
		writePict(inFile, new File(uploadPath), width, height);
	}
	
	/**
	 * Upload image with resizing and changing image type of jpg
	 * @param loadFile : upload image file
	 * @param outFile : output image file
	 * @param width : maximum image width
	 * @param height : maximum image height
	 * @return
	 */
	public void writePict(File loadFile, File outFile, int w, int h){
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
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(), e);
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
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		return returnFile;
	}
}
