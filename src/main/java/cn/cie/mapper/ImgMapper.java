package cn.cie.mapper;

import cn.cie.entity.Img;

import java.util.List;

public interface ImgMapper {
    int insert(Img record);

    List<String> selectByGame(Integer game);
}