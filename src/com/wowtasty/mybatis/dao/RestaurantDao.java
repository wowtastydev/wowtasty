package com.wowtasty.mybatis.dao;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantDeliveryAreaVO;
import com.wowtasty.mybatis.vo.RestaurantPictVO;
import com.wowtasty.util.Constants;
import com.wowtasty.vo.CuisineListVO;
import com.wowtasty.vo.RestaurantEVO;
import com.wowtasty.vo.RestaurantListVO;
import com.wowtasty.vo.RestaurantMenuEVO;
import com.wowtasty.vo.RestaurantMenuOptionEVO;
import com.wowtasty.vo.RestaurantSearchConditionVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class RestaurantDao {

    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(RestaurantDao.class);
    /**
     * SQL Session Factory
     */
    SqlSessionFactory factory = null;

    /**
     * Constructor
     */
    public RestaurantDao() {
        factory = FactoryService.getFactory();
    }

    public String getKeywordList(String keyIn) {
        SqlSession sqlSession = factory.openSession();
        String resultStr = "";
        try {
            resultStr = sqlSession.selectOne("restaurant.selectKeyword", keyIn);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getKeywordList occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getKeywordLIst occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultStr;
    }

    /**
     * get new restaurant list
     *
     * @return List <RestaurantListVO>
     */
    public List<RestaurantListVO> getNRestaurantList() {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantListVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectNRestaurant");
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getNewRestaurantList occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getNewRestaurantList occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * get most ordered restaurant list
     *
     * @return List <RestaurantListVO>
     */
    public List<RestaurantListVO> getMORestaurantList() {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantListVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectMORestaurant");
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getNewRestaurantList occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getNewRestaurantList occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * get cuisine list
     *
     * @return List <RestaurantListVO>
     */
    public List<CuisineListVO> getCuisineList() {
        SqlSession sqlSession = factory.openSession();
        List<CuisineListVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectCuisineType", Constants.KEY_CD_CUISINE_TYPE);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getCuisineList occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getCuisineList occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * search restaurant by address and keyword
     *
     * @return List <RestaurantEVO>
     */
    public List<RestaurantEVO> getRestaurantByAddr(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantEVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectRestaurantByAddr", vo);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getRestaurantByAddr occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getRestaurantByAddr occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * search restaurant by city
     *
     * @return List <RestaurantEVO>
     */
    public List<RestaurantEVO> getRestaurantByCity(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantEVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectRestaurantByCity", vo);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao selectRestaurantByCity occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao selectRestaurantByCity occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * search restaurant by cuisine
     *
     * @return List <RestaurantEVO>
     */
    public List<RestaurantEVO> getRestaurantByCuisine(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantEVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectRestaurantByCuisine", vo);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao selectRestaurantByCuisine occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao selectRestaurantByCuisine occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * get cuisine list by address and/or keyword
     *
     * @return List <RestaurantListVO>
     */
    public List<CuisineListVO> getCuisineListByAddr(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<CuisineListVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectCuisineTypeByAddr", vo);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getCuisineListByAddr occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getCuisineListByAddr occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * get cuisine list by city
     *
     * @return List <RestaurantListVO>
     */
    public List<CuisineListVO> getCuisineListByCity(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<CuisineListVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectCuisineTypeByCity", vo);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getCuisineListByCity occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getCuisineListByCity occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * get cuisine list by cuisine
     *
     * @return List <RestaurantListVO>
     */
    public List<CuisineListVO> getCuisineListByCuisine(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<CuisineListVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectCuisineTypeByCuisine", vo);
        } catch (Exception e) {
            logger.error("!!!!!RestaurantDao getCuisineListByCuisine occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getCuisineListByCuisine occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * get restaurant info
     *
     * @return RestaurantEVO
     */
    public RestaurantEVO getRestaurantInfo(String restaurantID){
        SqlSession sqlSession = factory.openSession();
        RestaurantEVO resultObj = new RestaurantEVO();
        try{
            resultObj = sqlSession.selectOne("restaurant.selectRestaurantByID", restaurantID);
        } catch (Exception e){
            logger.error("!!!!!RestaurantDao getRestaurantInfo occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getRestaurantInfo occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

     /**
     * get restaurant delivery area
     *
     * @return RestaurantEVO
     */
    public List<RestaurantDeliveryAreaVO> getRestaurantDeliveryArea(String restaurantID){
        SqlSession sqlSession = factory.openSession();
        List<RestaurantDeliveryAreaVO> resultObj = new ArrayList<>();
        try{
            resultObj = sqlSession.selectList("restaurant.selectRestaurantDeliveryArea", restaurantID);
        } catch (Exception e){
            logger.error("!!!!!RestaurantDao getRestaurantDeliveryArea occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getRestaurantDeliveryArea occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

     /**
     * get restaurant images
     *
     * @return RestaurantEVO
     */
    public List<RestaurantPictVO> getRestaurantImage(String restaurantID){
        SqlSession sqlSession = factory.openSession();
        List<RestaurantPictVO> resultObj = new ArrayList<>();
        try{
            resultObj = sqlSession.selectList("restaurant.selectRestaurantImage", restaurantID);
        } catch (Exception e){
            logger.error("!!!!!RestaurantDao getRestaurantImage occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getRestaurantImage occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

     /**
     * get restaurant Menu, Category and option
     *
     * @return RestaurantEVO
     */
    public Map<String, Object> getRestaurantMenu(String restaurantID){
        SqlSession sqlSession = factory.openSession();
        Map<String, Object> resultObj = new HashMap<>();
        List<RestaurantMenuEVO> restMenuList = new ArrayList<>();
        List<RestaurantMenuOptionEVO> restMenuOptionList = new ArrayList<>();
        try{
            //get restaurant menu and category
            restMenuList = sqlSession.selectList("restaurant.selectRestaurantMenu", restaurantID);
            resultObj.put("RESTAURANT_MENU", restMenuList);
            //get restaurant menu option
            restMenuOptionList = sqlSession.selectList("restaurant.selectRestaurantMenuOption", restaurantID);
            resultObj.put("RESTAURANT_MENU_OPTION", restMenuOptionList);

        } catch (Exception e){
            logger.error("!!!!!RestaurantDao getRestaurantMenu occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getRestaurantMenu occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }

    /**
     * get restaurant Menu and option by menuID
     *
     * @return RestaurantMenuOptionVO
     */
    public List<RestaurantMenuOptionEVO> getRestaurantMenuByID(String restaurantID, Integer menuID){
        SqlSession sqlSession = factory.openSession();
        Map<String, Object> inputObj = new HashMap<>();
        inputObj.put("restaurantID", restaurantID);
        inputObj.put("menuID", menuID);
        List<RestaurantMenuOptionEVO> resultObj = new ArrayList<>();
        try{
            resultObj = sqlSession.selectList("restaurant.selectRestaurantMenuByID", inputObj);

        } catch (Exception e){
            logger.error("!!!!!RestaurantDao getRestaurantMenuByID occurs error:" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                logger.error("!!!!!RestaurantDao getRestaurantMenuByID occurs error:" + e);
                throw new RuntimeException(e);
            }
        }
        return resultObj;
    }
}
