package com.wowtasty.vo;

import com.wowtasty.mybatis.vo.RestaurantMenuOptionVO;

/**
 *
 * @author Seunghon Kim <sh.kim at live.com>
 */
public class RestaurantMenuOptionEVO extends RestaurantMenuOptionVO {
    private String menuName = "";
    private Float menuPrice = 0.00f;

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

    /**
     * @return the menuPrice
     */
    public Float getMenuPrice() {
        return menuPrice;
    }

    /**
     * @param menuPrice the menuPrice to set
     */
    public void setMenuPrice(Float menuPrice) {
        this.menuPrice = menuPrice;
    }
}
