package com.wowtasty.mybatis.dao;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.vo.CuisineListVO;
import com.wowtasty.vo.RestaurantListVO;
import com.wowtasty.vo.RestaurantSearchConditionVO;
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
     * @return List <RestaurantMasterVO>
     */
    public List<RestaurantMasterVO> getRestaurantByAddr(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantMasterVO> resultObj = new ArrayList<>();
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
     * @return List <RestaurantMasterVO>
     */
    public List<RestaurantMasterVO> getRestaurantByCity(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantMasterVO> resultObj = new ArrayList<>();
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
     * @return List <RestaurantMasterVO>
     */
    public List<RestaurantMasterVO> getRestaurantByCuisine(RestaurantSearchConditionVO vo) {
        SqlSession sqlSession = factory.openSession();
        List<RestaurantMasterVO> resultObj = new ArrayList<>();
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
}
