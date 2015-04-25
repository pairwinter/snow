package com.pairwinter.snow.utils;

import com.pairwinter.snow.utils.datapage.OrderBy;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damon on 9/2/14.
 */
public class SnowUtils {
    public static List<OrderBy> buildOrderByList(String orderBy, String orderDirection){
        List<OrderBy> orderByList = null;
        if(!StringUtils.isEmpty(orderBy) && !StringUtils.isEmpty(orderDirection)){
            orderByList = new ArrayList<OrderBy>();
            orderByList.add(new OrderBy(orderBy,orderDirection));
        }
        return orderByList;
    }
}
