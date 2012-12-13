package com.wowtasty.mybatis.dao;


import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.vo.CuisineListVO;
import com.wowtasty.vo.RestaurantListVO;
import java.util.ArrayList;
import java.util.List;
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
     * get most ordered restaurant list
     *
     * @return List <RestaurantListVO>
     */
    public List<CuisineListVO> getCuisineList() {
        SqlSession sqlSession = factory.openSession();
        List<CuisineListVO> resultObj = new ArrayList<>();
        try {
            resultObj = sqlSession.selectList("restaurant.selectCuisineType");
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
}
