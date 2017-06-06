package cn.cie.mapper;

import cn.cie.entity.Order;
import cn.cie.entity.Ordermapper;

import java.util.List;

public interface OrdermapperMapper {
    int insert(Ordermapper record);

    List<Integer> selectByOrder(Integer order);
}