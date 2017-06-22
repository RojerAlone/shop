package cn.cie.mapper;

import cn.cie.entity.Img;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImgMapper {
    int insert(Img record);

    int insertBatch(@Param(value = "game") Integer game, @Param(value = "imgs") List<String> imgs);

    List<String> selectByGame(Integer game);
}