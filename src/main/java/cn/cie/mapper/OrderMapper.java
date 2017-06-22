package cn.cie.mapper;

import cn.cie.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {

    int insert(Order record);

    Order selectById(Integer id);

    List<Order> selectByUidAndStat(@Param(value = "uid") Integer uid, @Param(value = "stat") Byte stat);

    List<Order> selectByUidAndStatAndPage(@Param(value = "uid") Integer uid, @Param(value = "stat") Byte stat,
                                          @Param(value = "startPos") Integer startPos, @Param(value = "size") Integer size);

    int getOrderNumsByUidAndStat(@Param(value = "uid") Integer uid, @Param(value = "stat") Byte stat);

    int updateStatByDate(@Param(value = "oldstat") Byte oldstat, @Param(value = "newstat") Byte newstat, @Param(value = "date") Date date);

    int update(Order record);

}