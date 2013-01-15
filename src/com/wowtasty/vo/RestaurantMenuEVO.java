package com.wowtasty.vo;

import com.wowtasty.mybatis.vo.RestaurantMenuVO;


/**
 * Extends Restaurant Menu Mapping VO
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class RestaurantMenuEVO extends RestaurantMenuVO {
    private String categoryName = "";
    private String menuName = "";

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
