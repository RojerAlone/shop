package cn.cie.mapper;

import cn.cie.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    int insert(Order record);

    Order selectById(Integer id);

    List<Order> selectByUidAndStat(@Param(value = "uid") Integer uid, @Param(value = "stat") Byte stat);

    int update(Order record);

}