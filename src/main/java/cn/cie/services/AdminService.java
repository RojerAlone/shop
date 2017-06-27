package cn.cie.services;

import cn.cie.entity.Game;
import cn.cie.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/15.
 * 管理员相关
 */
public interface AdminService {

    /**
     * 管理员登陆
     * @param username
     * @param password
     * @return
     */
    Result login(String username, String password);

    /**
     * 获取所有的用户
     * @param page 当前的页数
     * @return User Page
     */
    Result getUser(int page);

    /**
     * 限制账户操作
     * @param uid
     * @return
     */
    Result restrict(Integer uid);

    /**
     * 解除账户限制
     * @param uid
     * @return
     */
    Result relieve(Integer uid);

    /**
     * 删除用户
     * @param uid
     * @return
     */
    Result delete(Integer uid);

    /**
     * 获取所有游戏
     * @param page 获取游戏的页数
     * @return GameDTO Page
     */
    Result getGames(int page);

    /**
     * 添加游戏
     * @param game 游戏实体，需要游戏名字、开发者、描述、配置、价格
     * @param kind 游戏种类，数组类型
     * @param header 游戏图片
     * @param pics 至少5张游戏截图
     * @param path 图片存储路径
     * @return
     */
    Result addGame(Game game, Integer[] kind, MultipartFile header, MultipartFile[] pics, String path) throws IOException;

    /**
     * 更新游戏信息
     * @param game
     * @return
     */
    Result updateGameInfo(Game game);

    /**
     * 获取游戏种类
     * @param game
     * @return
     */
    Result getGameKind(Integer game);

    /**
     * 更新游戏的种类
     * @param game
     * @param kinds
     * @return
     */
    Result updateGameKind(Integer game, List<Integer> kinds);

    /**
     * 将游戏上架
     * @param id
     * @param date 上架时间
     * @return
     */
    Result upGame(Integer id, Date date);

    /**
     * 下架游戏
     * @param id
     * @return
     */
    Result downGame(Integer id);

    /**
     * 添加游戏种类
     * @param name
     * @return
     */
    Result addKind(String name);

    /**
     * 管理种类对应的游戏
     * @param kind
     * @param games
     * @return
     */
    Result managerKind(Integer kind, List<Integer> games);

    /**
     * 获取所有的游戏
     * @return
     */
    Result getAllGames();
}
