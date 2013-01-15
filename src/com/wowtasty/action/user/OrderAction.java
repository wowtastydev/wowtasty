package com.wowtasty.action.user;

import com.opensymphony.xwork2.ActionSupport;
import com.wowtasty.mybatis.dao.RestaurantDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.RestaurantDeliveryAreaVO;
import com.wowtasty.mybatis.vo.RestaurantPictVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.vo.CuisineListVO;
import com.wowtasty.vo.RestaurantEVO;
import com.wowtasty.vo.RestaurantListVO;
import com.wowtasty.vo.RestaurantMenuEVO;
import com.wowtasty.vo.RestaurantMenuOptionEVO;
import com.wowtasty.vo.RestaurantSearchConditionVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.log4j.Logger;

/**
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class OrderAction extends ActionSupport {

    //serialVersionUID
    private static final long serialVersionUID = 1L;
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
    // codemaster map
    private Map<String, List<CodeMasterVO>> codeMap = new HashMap<>();
    // main page infomations
    private List<RestaurantListVO> newListVO = new ArrayList<>();
    private List<RestaurantListVO> mostListVO = new ArrayList<>();
    private List<CuisineListVO> cuisineListVO = new ArrayList<>();
    private List<RestaurantEVO> restListVO = new ArrayList<>();
    private List<RestaurantPictVO> restPictListVO = new ArrayList<>();
    private List<RestaurantDeliveryAreaVO> restdaListVO = new ArrayList();
    private List<RestaurantMenuEVO> restMenuListVO = new ArrayList();
    private List<RestaurantMenuOptionEVO> restMenuOptionListVO = new ArrayList();
    private RestaurantEVO reVO = new RestaurantEVO();
    private RestaurantMenuOptionEVO rmoeVO = new RestaurantMenuOptionEVO();
    // search condition VO
    private RestaurantSearchConditionVO rscVO = new RestaurantSearchConditionVO();
    private List<CodeMasterVO> cityList = new ArrayList<>();
    private List<CodeMasterVO> cuisineTypeList = new ArrayList<>();
    private Iterator keywordList = null;
    //request prameter for orderMenu
    private String restaurantID = "";
    private Integer menuID = 0;
    //request parameter for keyword field autocompletion javascript
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
        // codes from session
        codeMap = (Map<String, List<CodeMasterVO>>) SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
        // set up dropdown menu from codes
        setCityList(codeMap.get(Constants.KEY_CD_CITY));
        //get restaurant lists (new, most ordered, type by cousine)
        RestaurantDao resDao = new RestaurantDao();
        setNewListVO(resDao.getNRestaurantList());
        setMostListVO(resDao.getMORestaurantList());
        setCuisineListVO(resDao.getCuisineList());

        return SUCCESS;
    }

    /**
     * Request and return restaurant keyword for keyword field auto-completion
     *
     * @return SUCCESS
     * @throws Exception
     */
    public String searchKeyword() throws Exception {
        getLogger().info("Call searchKeyword");

        if (getQ() != null && getQ().length() > 0) {
            RestaurantDao resDao = new RestaurantDao();
            TreeSet<String> keywordSet = new TreeSet<>();
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
     * search restaurants by location ,keyword and city
     *
     * @return SUCCESS or INPUT
     * @throws Exception
     */
    public String searchRestaurant() throws Exception {
        getLogger().info("Call searchRestaurant");
        String returnStr = SUCCESS;
        RestaurantDao resDao = new RestaurantDao();
        // Check keyword param value with textfield default value
        if (rscVO.getKeyword().equals("Restaurant/Cuisine (Option)")) {
            rscVO.setKeyword("");
        }
        // Check preOrderDate and preOrderTime patterns
        if (!rscVO.getPreOrderDate().equals("") && !rscVO.getPreOrderDate().matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.]\\d\\d$")) {
            returnStr = ERROR;
            addActionMessage("Preorder Date format is not matched.");
            if (!rscVO.getPreOrderTime().equals("") && !rscVO.getPreOrderTime().matches("\\w[00-23]:\\w[00-59]")) {
                addActionMessage("Preorder Time format is not matched.");
            }
            return returnStr;
        }
        // Check search conditions
        if (!"".equals(rscVO.getLocation()) && "".equals(rscVO.getPostalCode())) {
            returnStr = ERROR;
            this.addActionError(this.getText("E0007"));
            return returnStr;
        } else if (!"".equals(rscVO.getCity())) {
            //Search restaurant by city
            setRestListVO(resDao.getRestaurantByCity(getRscVO()));
            setCuisineListVO(resDao.getCuisineListByCity(getRscVO()));
        } else if (!"".equals(rscVO.getPostalCode())) {
            //Search restaurant by postalcode and(or) keyword
            setRestListVO(resDao.getRestaurantByAddr(getRscVO()));
            setCuisineListVO(resDao.getCuisineListByAddr(getRscVO()));
        }

        if (getRestListVO().isEmpty() || getRestListVO().size() < 1) {
            returnStr = ERROR;
            addActionMessage("No restaurant has been found. \n Please, choose a city");
        }
        return returnStr;
    }

    /**
     * search restaurants by cuisine type
     *
     * @return SUCCESS or INPUT
     * @throws Exception
     */
    public String searchRestaurantByCuisine() throws Exception {
        getLogger().info("Call searchRestaurantByCuisine");

        RestaurantDao resDao = new RestaurantDao();
        setRestListVO(resDao.getRestaurantByCuisine(getRscVO()));
        setCuisineListVO(resDao.getCuisineListByCuisine(getRscVO()));
        return SUCCESS;
    }

    /**
     * view restaurant info
     *
     * @return
     * @throws Exception
     */
    public String viewRestaurant() throws Exception {
        getLogger().info("Call viewRestaurant");

        //Map object for Menu List and Menu Option List
        Map<String, Object> resultObj = new HashMap<>();
        RestaurantDao resDao = new RestaurantDao();
        setReVO(resDao.getRestaurantInfo(getRscVO().getRestaurantID()));
        setRestPictListVO(resDao.getRestaurantImage(getRscVO().getRestaurantID()));
        setRestdaListVO(resDao.getRestaurantDeliveryArea(getRscVO().getRestaurantID()));
        resultObj = resDao.getRestaurantMenu(getRscVO().getRestaurantID());
        setRestMenuListVO((List<RestaurantMenuEVO>) resultObj.get("RESTAURANT_MENU"));
        setRestMenuOptionListVO((List<RestaurantMenuOptionEVO>) resultObj.get("RESTAURANT_MENU_OPTION"));
        return SUCCESS;
    }

    /**
     * view restaurant menu and option
     *
     * @return
     * @throws Exception
     */
    public String viewMenu() throws Exception {
        getLogger().info("Call viewMenu");

        RestaurantDao resDao = new RestaurantDao();
        setRestMenuOptionListVO(resDao.getRestaurantMenuByID(this.getRestaurantID(), this.getMenuID()));
        getLogger().info("restaurantID: "+this.getRestMenuOptionListVO().size());
        return SUCCESS;
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

    /**
     * @return the restListVO
     */
    public List<RestaurantEVO> getRestListVO() {
        return restListVO;
    }

    /**
     * @param restListVO the restListVO to set
     */
    public void setRestListVO(List<RestaurantEVO> restListVO) {
        this.restListVO = restListVO;
    }

    /**
     * @return the rscVO
     */
    public RestaurantSearchConditionVO getRscVO() {
        return rscVO;
    }

    /**
     * @param rscVO the rscVO to set
     */
    public void setRscVO(RestaurantSearchConditionVO rscVO) {
        this.rscVO = rscVO;
    }

    /**
     * @return the cityList
     */
    public List<CodeMasterVO> getCityList() {
        return cityList;
    }

    /**
     * @param cityList the cityList to set
     */
    public void setCityList(List<CodeMasterVO> cityList) {
        this.cityList = cityList;
    }

    /**
     * @return the cuisineTypeList
     */
    public List<CodeMasterVO> getCuisineTypeList() {
        return cuisineTypeList;
    }

    /**
     * @param cuisineTypeList the cuisineTypeList to set
     */
    public void setCuisineTypeList(List<CodeMasterVO> cuisineTypeList) {
        this.cuisineTypeList = cuisineTypeList;
    }

    /**
     * @return the restPictListVO
     */
    public List<RestaurantPictVO> getRestPictListVO() {
        return restPictListVO;
    }

    /**
     * @param restPictListVO the restPictListVO to set
     */
    public void setRestPictListVO(List<RestaurantPictVO> restPictListVO) {
        this.restPictListVO = restPictListVO;
    }

    /**
     * @return the restdaListVO
     */
    public List<RestaurantDeliveryAreaVO> getRestdaListVO() {
        return restdaListVO;
    }

    /**
     * @param restdaListVO the restdaListVO to set
     */
    public void setRestdaListVO(List<RestaurantDeliveryAreaVO> restdaListVO) {
        this.restdaListVO = restdaListVO;
    }

    /**
     * @return the restMenuListVO
     */
    public List<RestaurantMenuEVO> getRestMenuListVO() {
        return restMenuListVO;
    }

    /**
     * @param restMenuListVO the restMenuListVO to set
     */
    public void setRestMenuListVO(List<RestaurantMenuEVO> restMenuListVO) {
        this.restMenuListVO = restMenuListVO;
    }

    /**
     * @return the reVO
     */
    public RestaurantEVO getReVO() {
        return reVO;
    }

    /**
     * @param reVO the reVO to set
     */
    public void setReVO(RestaurantEVO reVO) {
        this.reVO = reVO;
    }

    /**
     * @return the restMenuOptionListVO
     */
    public List<RestaurantMenuOptionEVO> getRestMenuOptionListVO() {
        return restMenuOptionListVO;
    }

    /**
     * @param restMenuOptionListVO the restMenuOptionListVO to set
     */
    public void setRestMenuOptionListVO(List<RestaurantMenuOptionEVO> restMenuOptionListVO) {
        this.restMenuOptionListVO = restMenuOptionListVO;
    }

    /**
     * @return the rmoeVO
     */
    public RestaurantMenuOptionEVO getRmoeVO() {
        return rmoeVO;
    }

    /**
     * @param rmoeVO the rmoeVO to set
     */
    public void setRmoeVO(RestaurantMenuOptionEVO rmoeVO) {
        this.rmoeVO = rmoeVO;
    }

    /**
     * @return the restaurantID
     */
    public String getRestaurantID() {
        return restaurantID;
    }

    /**
     * @param restaurantID the restaurantID to set
     */
    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    /**
     * @return the menuID
     */
    public Integer getMenuID() {
        return menuID;
    }

    /**
     * @param menuID the menuID to set
     */
    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }
}
