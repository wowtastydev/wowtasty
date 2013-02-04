package com.wowtasty.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.wowtasty.mybatis.vo.BillDetailVO;
import com.wowtasty.mybatis.vo.BillMasterVO;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.vo.ReportingExcelVO;

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
	public static final String CONFIG_EXCEL_PATH = "excelTempPath";
	
	/** constants */
	public static final String PREFIX_THUMB = "thumb_";
	public static final String STR_TEMP = ".temp";
	public static final String WOWTASTY_DIR = "wowtasty/";
	public static final String RESTAURANT_DIR = "restaurant/";
	public static final String MENU_DIR = "menu/";
	public static final String DELIVERY_DIR = "delivery/";

	/** config.properties */	
	private static Properties config = (Properties)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CONFIG_PROPERTIES);
	
	/** code master */
	private static Map<String, List<CodeMasterVO>> codeMap 
		= (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);

	/**
	 * delete image and thumbnail image
	 * @param dirType : RESTAURANT/MENU/WOWTASTY/DELIVERY directory
	 * @param filename : filename
	 */
	public static void deletePict(String dirType, String fileName){
		String dirPath = "";
		String imgPath ="";
		String thumbPath ="";
		File imgFile = null;
		File thumbFile = null;
		StringBuilder sb = new StringBuilder(config.getProperty(CONFIG_PICT_PATH));
		
		try{
			// Get image upload pathfrom config.properties
			dirPath = sb.append(dirType).toString();
			
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
	 * @param dirType : RESTAURANT/MENU/WOWTASTY/DELIVERY directory
	 * @param fileName : output file name
	 */
	public static void writePict(File inFile, String dirType, String fileName){
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
			dirPath = sb.append(dirType).toString();
			
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
	 * upload image and create thumbnail image
	 * @param inFileName : input File name
	 * @param dirType : RESTAURANT/MENU/WOWTASTY/DELIVERY directory
	 * @param fileName : output file name
	 */
	public static void copyPict(String inFileName, String dirType, String fileName){
		String dirPath = "";
		String imgPath ="";
		int imgWidth = 0;
		int imgHeight = 0;
		String thumbPath ="";
		int thumbWidth = 0;
		int thumbHeight = 0;
		StringBuilder sb = new StringBuilder(config.getProperty(CONFIG_PICT_PATH));
		String inImgPath = "";
			
		try{
			// Get image upload pathfrom config.properties
			dirPath = sb.append(dirType).toString();
			
			// Original file
			sb.setLength(0);
			imgPath = sb.append(dirPath).append(fileName).toString();
			
			// Thumbnail file
			sb.setLength(0);
			thumbPath = sb.append(dirPath).append(PREFIX_THUMB).append(fileName).toString();
			
			// Original image path
			sb.setLength(0);
			inImgPath = sb.append(dirPath).append(inFileName).toString();
			
			// Image/Thumbnail with, height
			imgWidth = Integer.parseInt(config.getProperty(CONFIG_PICT_WIDTH));
			imgHeight = Integer.parseInt(config.getProperty(CONFIG_PICT_HEIGHT));
			thumbWidth = Integer.parseInt(config.getProperty(CONFIG_THUMB_WIDTH));
			thumbHeight = Integer.parseInt(config.getProperty(CONFIG_THUMB_HEIGHT));
				
			// Image Upload
			writePict(new File(inImgPath), new File(imgPath), imgWidth, imgHeight);
			// Thumbnail Upload
			writePict(new File(inImgPath), new File(thumbPath), thumbWidth, thumbHeight);
		} catch (Exception e) {
			logger.error("!!!!!FileUtil copyPict occurs error:" + e);
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
	 * @param master : Bill Master
	 * @param list : Bill Detail List
	 * @return File : Output File
	 */
	public static File downloadBillList(BillMasterVO master, List<BillDetailVO> list) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		File dir = null;
		File returnFile = null;
		String tempdir = config.getProperty(CONFIG_EXCEL_PATH);
		try {
			dir = new File(tempdir);
			if(!dir.exists()) {
				dir.mkdir();
			}
			returnFile = new File(tempdir, "temp.xls");
			fw = new FileWriter(returnFile);
			bw = new BufferedWriter(fw);
			
			// write header
			bw.write("Invoice Number");
			bw.write("\t");
			bw.write(master.getBillingID());
			bw.write("\t");
			bw.write("Company Name");
			bw.write("\t");
			bw.write(master.getCompanyName());
			bw.write("\t");
			bw.write("Account Number");
			bw.write("\t");
			bw.write(master.getBillAccountNO());
			bw.write("\t");
			bw.write("From");
			bw.write("\t");
			bw.write(master.getBillFrom().toString());
			bw.write("\t");
			bw.write("To");
			bw.write("\t");
			bw.write(master.getBillTo().toString());
			bw.write("\n");
			bw.write("\n");
			
			// Write titles
			bw.write("Order ID");
			bw.write("\t");
			bw.write("Order Time");
			bw.write("\t");
			bw.write("Delivery Time");
			bw.write("\t");
			bw.write("Payment Type");
			bw.write("\t");
			bw.write("Delivery Type");
			bw.write("\t");
			bw.write("Delivery Company ID");
			bw.write("\t");
			bw.write("Delivery Company Type");
			bw.write("\t");
			bw.write("DeliveryMan ID");
			bw.write("\t");
			bw.write("Order Member ID");
			bw.write("\t");
			bw.write("Item");
			bw.write("\t");
			bw.write("Food Price");
			bw.write("\t");
			bw.write("Food Tax Price");
			bw.write("\t");
			bw.write("Food Price With Tax");
			bw.write("\t");
			bw.write("CommissionPrice");
			bw.write("\t");
			bw.write("Delivery Price");
			bw.write("\t");
			bw.write("Delivery Tax Price");
			bw.write("\t");
			bw.write("Delivery Price With Tax");
			bw.write("\t");
			bw.write("Tip");
			bw.write("\t");
			bw.write("Total Price");
			bw.write("\t");
			bw.write("Sales Price");
			bw.write("\t");
			bw.write("Order Status");
			bw.write("\n");
			for (int i = 0; i < list.size() ; i++) {
				// Add Output data
				bw.write(list.get(i).getOrderID());
				bw.write("\t");
				bw.write(StringConvertUtil.convertDate2String(list.get(i).getOrderTime()));
				bw.write("\t");
				bw.write(StringConvertUtil.convertDate2String(list.get(i).getDeliveryTime()));
				bw.write("\t");
				bw.write(CodeUtil.getCdName(codeMap, Constants.KEY_CD_PAYMENT_TYPE, list.get(i).getPaymentType()));
				bw.write("\t");
				bw.write(CodeUtil.getCdName(codeMap, Constants.KEY_CD_DELIVERY_TYPE, list.get(i).getDeliveryType()));
				bw.write("\t");
				bw.write(list.get(i).getDeliveryCompanyID());
				bw.write("\t");
				bw.write(CodeUtil.getCdName(codeMap, Constants.KEY_CD_DELIVERYCOMPANY_TYPE, list.get(i).getDeliveryCompanyType()));
				bw.write("\t");
				bw.write(list.get(i).getDeliverymanID());
				bw.write("\t");
				bw.write(list.get(i).getOrderMemberID());
				bw.write("\t");
				bw.write(list.get(i).getTotalMenuName());
				bw.write("\t");
				if (list.get(i).getFoodTotalPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getFoodTotalPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getFoodTaxPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getFoodTaxPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getFoodPriceWithTax() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getFoodPriceWithTax().toString());
				}
				bw.write("\t");
				if (list.get(i).getCommissionPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getCommissionPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getDeliveryPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getDeliveryPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getDeliveryTaxPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getDeliveryTaxPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getDeliveryPriceWithTax() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getDeliveryPriceWithTax().toString());
				}
				bw.write("\t");
				if (list.get(i).getTipPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getTipPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getTotalPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getTotalPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getTotalPriceWithTax() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getTotalPriceWithTax().toString());
				}
				bw.write("\t");
				bw.write(CodeUtil.getCdName(codeMap, Constants.KEY_CD_ORDER_STATUS, list.get(i).getOrderStatus()));
				bw.write("\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("!!!!!FileUtil downloadBillList occurs error:" + e);
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
				logger.error("!!!!!FileUtil downloadBillList occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnFile;
	}
	
	
	/**
	 * @param list : Reporting data List
	 * @return File : Output File
	 */
	public static File downloadReportList(String yearMonth, List<ReportingExcelVO> list) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		File dir = null;
		File returnFile = null;
		String tempdir = config.getProperty(CONFIG_EXCEL_PATH);
		try {
			dir = new File(tempdir);
			if(!dir.exists()) {
				dir.mkdir();
			}
			returnFile = new File(tempdir, "temp.xls");
			fw = new FileWriter(returnFile);
			bw = new BufferedWriter(fw);
			
			// write header
			bw.write("Report YearMonth");
			bw.write("\t");
			bw.write(yearMonth);
			bw.write("\n");
			bw.write("\n");
			
			// Write titles
			bw.write("Billing ID");
			bw.write("\t");
			bw.write("Order ID");
			bw.write("\t");
			bw.write("Restaurant ID");
			bw.write("\t");
			bw.write("Restaurant Name");
			bw.write("\t");
			bw.write("Order Time");
			bw.write("\t");
			bw.write("Delivery Time");
			bw.write("\t");
			bw.write("Delivery Day");
			bw.write("\t");
			bw.write("Delivery Date");
			bw.write("\t");
			bw.write("Delivery Hour");
			bw.write("\t");
			bw.write("Delivery Type");
			bw.write("\t");
			bw.write("Delivery Company ID");
			bw.write("\t");
			bw.write("Delivery Company Name");
			bw.write("\t");
			bw.write("Delivery Company Type");
			bw.write("\t");
			bw.write("DeliveryMan ID");
			bw.write("\t");
			bw.write("DeliveryMan Name");
			bw.write("\t");
			bw.write("Payment Type");
			bw.write("\t");
			bw.write("Order Member ID");
			bw.write("\t");
			bw.write("Item");
			bw.write("\t");
			bw.write("Food Price");
			bw.write("\t");
			bw.write("Food Tax Price");
			bw.write("\t");
			bw.write("Food Price With Tax");
			bw.write("\t");
			bw.write("CommissionPrice");
			bw.write("\t");
			bw.write("Delivery Price");
			bw.write("\t");
			bw.write("Delivery Tax Price");
			bw.write("\t");
			bw.write("Delivery Price With Tax");
			bw.write("\t");
			bw.write("Tip");
			bw.write("\t");
			bw.write("Total Price");
			bw.write("\t");
			bw.write("Sales Price");
			bw.write("\t");
			bw.write("Order Status");
			bw.write("\n");
			for (int i = 0; i < list.size() ; i++) {
				// Add Output data
				bw.write(list.get(i).getBillingID());
				bw.write("\t");
				bw.write(list.get(i).getOrderID());
				bw.write("\t");
				bw.write(list.get(i).getRestaurantID());
				bw.write("\t");
				bw.write(list.get(i).getRestaurantName());
				bw.write("\t");
				bw.write(StringConvertUtil.convertDate2String(list.get(i).getOrderTime()));
				bw.write("\t");
				bw.write(StringConvertUtil.convertDate2String(list.get(i).getDeliveryTime()));
				bw.write("\t");
				bw.write(list.get(i).getDeliveryDay());
				bw.write("\t");
				bw.write(list.get(i).getDeliveryDate().toString());
				bw.write("\t");
				bw.write(list.get(i).getDeliveryHour().toString());
				bw.write("\t");
				bw.write(list.get(i).getDeliveryTypeName());
				bw.write("\t");
				bw.write(list.get(i).getDeliveryCompanyID());
				bw.write("\t");
				bw.write(list.get(i).getDeliveryCompanyName());
				bw.write("\t");
				bw.write(list.get(i).getDeliveryCompanyTypeName());
				bw.write("\t");
				bw.write(list.get(i).getDeliverymanID());
				bw.write("\t");
				bw.write(list.get(i).getDeliverymanName());
				bw.write("\t");
				bw.write(list.get(i).getPaymentTypeName());
				bw.write("\t");
				bw.write(list.get(i).getOrderMemberID());
				bw.write("\t");
				bw.write(list.get(i).getTotalMenuName());
				bw.write("\t");
				if (list.get(i).getFoodTotalPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getFoodTotalPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getFoodTaxPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getFoodTaxPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getFoodPriceWithTax() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getFoodPriceWithTax().toString());
				}
				bw.write("\t");
				if (list.get(i).getCommissionPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getCommissionPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getDeliveryPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getDeliveryPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getDeliveryTaxPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getDeliveryTaxPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getDeliveryPriceWithTax() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getDeliveryPriceWithTax().toString());
				}
				bw.write("\t");
				if (list.get(i).getTipPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getTipPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getTotalPrice() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getTotalPrice().toString());
				}
				bw.write("\t");
				if (list.get(i).getTotalPriceWithTax() == null) {
					bw.write("0.00");
				} else {
					bw.write(list.get(i).getTotalPriceWithTax().toString());
				}
				bw.write("\t");
				bw.write(list.get(i).getOrderStatusName());
				bw.write("\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("!!!!!FileUtil downloadReportList occurs error:" + e);
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
				logger.error("!!!!!FileUtil downloadReportList occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnFile;
	}
}
