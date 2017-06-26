package cn.cie.services.impl;

import cn.cie.entity.*;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.*;
import cn.cie.services.AdminService;
import cn.cie.utils.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by RojerAlone on 2017/6/15.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private KindmapperMapper kindmapperMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagmapperMapper tagmapperMapper;
    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private RedisUtil redisUtil;


    public Result login(String username, String password) {
        if (username == null || password == null) {
            return Result.fail(MsgCenter.EMPTY_LOGIN);
        } else if (username.equals("admin") == false) {
            return Result.fail(MsgCenter.LOGIN_NOT_ALLOW);
        }
        User user = userMapper.selectByName(username);
        // 用户名不存在或者密码错误或者用户已经被删除
        if (user == null || !user.getPassword().equals(PasswordUtil.pwd2Md5(password))
                || user.getStat().equals(User.STAT_DEL)) {
            return Result.fail(MsgCenter.ERROR_LOGIN);
        } else if (user.getStat().equals(User.STAT_RESTRICT)) {
            return Result.fail(MsgCenter.USER_RESTRICT);
        } else {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            redisUtil.putEx(token, String.valueOf(user.getId()), 60 * 60 * 24);
            return Result.success(token);
        }
    }

    public Result getUser(int page) {
        PageUtil pageUtil = new PageUtil(userMapper.selectAllNums(), page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", userMapper.selectByPage(pageUtil.getStartPos(), pageUtil.getSize()));
        map.put("page", pageUtil);
        return Result.success(map);
    }

    public Result restrict(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_RESTRICT);
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail("");
        }
    }

    public Result relieve(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_OK);
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail("");
        }
    }

    public Result delete(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_DEL);
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail("");
        }
    }

    public Result getGames(int page) {
        PageUtil pageUtil = new PageUtil(gameMapper.selectNums(), page);
        List<Game> games = gameMapper.selectByPage(pageUtil.getStartPos(), pageUtil.getSize());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("game", paresGameDTO(games));
        map.put("page", pageUtil);
        return Result.success(map);
    }

    @Transactional
    public Result addGame(Game game, Integer[] kind, MultipartFile header, MultipartFile[] pics, String path) throws IOException {
        // 判断游戏信息
        if (StringUtils.isBlank(game.getCreater()) || StringUtils.isBlank(game.getName())
                || StringUtils.isBlank(game.getDesc()) || StringUtils.isBlank(game.getSystemcfg())
                || game.getPrice() == null || game.getPrice() < 0) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        // 判断文件类型是否合法
        if (!header.getContentType().equalsIgnoreCase("image/jpeg") && !header.getContentType().equalsIgnoreCase("image/png")) {
            return Result.fail(MsgCenter.ERROR_FILE_TYPE);
        }
        for (MultipartFile pic : pics) {
            if (!pic.getContentType().equalsIgnoreCase("image/jpeg") && !pic.getContentType().equalsIgnoreCase("image/png")) {
                return Result.fail(MsgCenter.ERROR_FILE_TYPE);
            }
        }

        gameMapper.insert(game);
        if (kind != null && kind.length > 0) {
            kindmapperMapper.insertKindBatch(game.getId(), Arrays.asList(kind));
        }
        Img image = new Img();
        image.setGame(game.getId());
        String headertype = null;
        if (header.getContentType().equalsIgnoreCase("image/jpeg")) {
            headertype = "jpg";
        } else if (header.getContentType().equalsIgnoreCase("image/png")) {
            headertype = "png";
        }
        image.setImg("/" + game.getId() + "/header." + headertype);
        imgMapper.insert(image);
        FileUtils.copyInputStreamToFile(header.getInputStream(), new File(path + "/" + game.getId(), "header." + headertype));
        int index = 1;
        List<String> imgs = new ArrayList<String>();
        for (MultipartFile pic : pics) {
            String type = null;
            if (pic.getContentType().equalsIgnoreCase("image/jpeg")) {
                type = "jpg";
            } else if (pic.getContentType().equalsIgnoreCase("image/png")) {
                type = "png";
            }
            // 拼接写入数据库的图片信息
            String img = "/" + game.getId() + "/" + index + "." + type;
            imgs.add(img);
            FileUtils.copyInputStreamToFile(pic.getInputStream(), new File(path + "/" + game.getId(), index + "." + type));
            index++;
        }
        imgMapper.insertBatch(game.getId(), imgs);
        return Result.success();
    }

    public Result updateGameInfo(Game game) {
        if (game.getId() == null) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        gameMapper.update(game);
        return Result.success();
    }

    public Result getGameKind(Integer game) {
        if (gameMapper.selectById(game) == null) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        List<Integer> kindIds = kindmapperMapper.selectByGame(game);
        List<Kind> kinds = kindMapper.selectByIds(kindIds);
        return Result.success(kinds);
    }

    @Transactional
    public Result updateGameKind(Integer game, List<Integer> kinds) {
        kindmapperMapper.deleteByGame(game);
        kindmapperMapper.insertKindBatch(game, kinds);
        return Result.success();
    }

    public Result upGame(Integer id, Date date) {
        Game game = gameMapper.selectById(id);
        // 如果没有这个游戏返回参数错误
        if (game == null) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        game.setStat(Game.STAT_OK);
        if (date == null) {
            game.setUtime(new Date());
        } else {
            game.setUtime(date);
        }
        if (1 == gameMapper.update(game)) {
            redisUtil.delete(RedisUtil.NEWESTGAME);    // 上架游戏后将缓存中的最新游戏清空
            return Result.success();
        }
        return Result.fail(MsgCenter.ERROR);
    }

    public Result downGame(Integer id) {
        Game game = gameMapper.selectById(id);
        // 如果没有这个游戏返回参数错误
        if (game == null) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        game.setStat(Game.STAT_DEL);
        if (1 == gameMapper.update(game)) {
            redisUtil.delete(RedisUtil.NEWESTGAME);    // 下架游戏后将缓存中的最新游戏清空
            return Result.success();
        }
        return Result.fail(MsgCenter.ERROR);
    }

    @Transactional
    public Result addKind(String name) {
        Kind kind = kindMapper.selectByName(name);
        if (kind != null) {
            return Result.fail(MsgCenter.NAME_EXISTS);
        }
        kind = new Kind();
        kind.setName(name);
        if (1 == kindMapper.insert(kind)) {
            redisUtil.rpushObject("kinds", Kind.class, kind);   // 添加到缓存中
            return Result.success();
        } else {
            return Result.fail(MsgCenter.ERROR);
        }
    }

    @Transactional
    public Result managerKind(Integer kind, List<Integer> games) {
        kindmapperMapper.deleteByKind(kind);
        kindmapperMapper.insertGameBatch(kind, games);
        return Result.success();
    }

    private List<GameDTO> paresGameDTO(List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<Tag> tags = null;
            List<Kind> kinds = null;
            List<Integer> tagIds = tagmapperMapper.selectByGame(game.getId());     // 获取游戏的标签id
            if (tagIds.size() != 0) {
                tags = tagMapper.selectByIds(tagIds);                               // 根据id获取所有的标签信息
            }
            List<String> img = imgMapper.selectByGame(game.getId());                // 获取所有的图片
            List<Integer> kindIds = kindmapperMapper.selectByGame(game.getId());   // 根据游戏id获取所有的种类id
            if (kindIds.size() != 0) {
                kinds = kindMapper.selectByIds(kindIds);                             // 根据种类id获取种类信息
            }
            GameDTO dto = new GameDTO(game, kinds, tags, img);
            gameDTOS.add(dto);
        }
        return gameDTOS;
    }

}
