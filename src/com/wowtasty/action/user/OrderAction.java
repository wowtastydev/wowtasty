package com.wowtasty.action.user;

import com.opensymphony.xwork2.ActionSupport;
import com.wowtasty.mybatis.dao.RestaurantDao;
import com.wowtasty.vo.CuisineListVO;
import com.wowtasty.vo.RestaurantListVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.log4j.Logger;

/**
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class OrderAction extends ActionSupport {
    //Logger

    private static Logger logger = Logger.getLogger(OrderAction.class);

    /**
     * @return the logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * @param aLogger the logger to set
     */
    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }
    /*
     * main page infomations
     */
    private List<RestaurantListVO> newListVO = new ArrayList<>();
    private List<RestaurantListVO> mostListVO = new ArrayList<>();
    private List<CuisineListVO> cuisineListVO = new ArrayList<>();
    private String location = "";
    private String keyword = "";
    private String city = "";
    private String restaurantType = "1";
    private Iterator keywordList = null;
    //request parameter for keyword field autocompletion
    private String q = "";

    /**
     * Call home page
     *
     * @return SUCCESS
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        getLogger().info("Call execute()");
        //get restaurant lists (new, most ordered, type by cousine)
        RestaurantDao resDao = new RestaurantDao();
        setNewListVO(resDao.getNRestaurantList());
        setMostListVO(resDao.getMORestaurantList());
        setCuisineListVO(resDao.getCuisineList());

        return SUCCESS;
    }

    /**
     * Request and return restaurant keyword for input field auto-completion
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String searchKeyword() throws Exception {
        getLogger().info("Call searchKeyword");

        if (getQ() != null && getQ().length() > 0) {
            RestaurantDao resDao = new RestaurantDao();
            TreeSet keywordSet = new TreeSet();
            String[] keyArr = resDao.getKeywordList(getQ()).split(",");

            for (String str : keyArr) {
                //filtering and sorting keywords
                if (str.matches("(?i)^" + getQ() + ".*")) {
                    keywordSet.add(str);
                }
            }

            setKeywordList(keywordSet.iterator());
        }

        return SUCCESS;
    }

    /**
     * search restaurants with location, keyword and city
     *
     * @return SUCCESS or INPUT
     * @throws Exception
     */
    public String searchRestaurant() throws Exception {
        getLogger().info("Call searchRestaurant");
        String returnStr = SUCCESS;
        //@ToDo: test select restaurants list
        //@ToDo: if restaurants list is null or 0>, change returnStr to ERROR and addActionMessage()
        //test
        returnStr = ERROR;
        addActionMessage("Not found");

        return returnStr;
    }

    /**
     * @return the newListVO
     */
    public List<RestaurantListVO> getNewListVO() {
        return newListVO;
    }

    /**
     * @param newListVO the newListVO to set
     */
    public void setNewListVO(List<RestaurantListVO> newListVO) {
        this.newListVO = newListVO;
    }

    /**
     * @return the mostListVO
     */
    public List<RestaurantListVO> getMostListVO() {
        return mostListVO;
    }

    /**
     * @param mostListVO the mostListVO to set
     */
    public void setMostListVO(List<RestaurantListVO> mostListVO) {
        this.mostListVO = mostListVO;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the restaurantType
     */
    public String getRestaurantType() {
        return restaurantType;
    }

    /**
     * @param restaurantType the deliveryType to set
     */
    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    /**
     * @return the q
     */
    public String getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * @return the cuisineListVO
     */
    public List<CuisineListVO> getCuisineListVO() {
        return cuisineListVO;
    }

    /**
     * @param cuisineListVO the cuisineListVO to set
     */
    public void setCuisineListVO(List<CuisineListVO> cuisineListVO) {
        this.cuisineListVO = cuisineListVO;
    }

    /**
     * @return the keywordList
     */
    public Iterator getKeywordList() {
        return keywordList;
    }

    /**
     * @param keywordList the keywordList to set
     */
    public void setKeywordList(Iterator keywordList) {
        this.keywordList = keywordList;
    }
}
