package cn.cie.mapper;

import cn.cie.entity.Orderitem;

import java.util.List;

public interface OrderitemMapper {

    int insert(Orderitem record);

    List<Orderitem> selectByIds(List<Integer> ids);

    int update(Orderitem record);

}