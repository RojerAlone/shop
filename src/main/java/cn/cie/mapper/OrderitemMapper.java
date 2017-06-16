package cn.cie.mapper;

import cn.cie.entity.Orderitem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderitemMapper {

    int insert(Orderitem record);

    List<Orderitem> selectByIds(@Param(value = "ids") List<Integer> ids);

    int update(Orderitem record);

}