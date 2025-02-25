package com.library.controller.admin;

import com.library.constant.JwtClaimsConstant;
import com.library.dto.*;
import com.library.dto.UserInformationDTO;
import com.library.entity.User;
import com.library.mapper.UserMapper;
import com.library.properties.JwtProperties;
import com.library.result.Result;
import com.library.service.UserService;
import com.library.utils.AliOssUtil;
import com.library.utils.JwtUtil;
import com.library.vo.BooksVO;
import com.library.vo.UserInformationVO;
import com.library.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/api/admin")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 注册
     * @param userRegisterDTO
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册：{}", userRegisterDTO);
        userService.register(userRegisterDTO);
        return Result.success();
    }

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }



    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 添加图书到收藏夹
     * @param booksFavoritesDTO
     */
    @PostMapping("/{userid}/favorites")
    public Result addBooksToFavorites(@RequestBody BooksFavoritesDTO booksFavoritesDTO, @PathVariable Long userid) {
        booksFavoritesDTO.setUserId(userid);
        log.info("添加图书到收藏夹：{}", booksFavoritesDTO);
        userService.addBooksToFavorites(booksFavoritesDTO);
        return Result.success();
    }

    /**
     * 获取收藏夹
     */
    @GetMapping("/{userid}/favorites")
    public Result<List<BooksVO>> getFavorites(@PathVariable Long userid) {
        log.info("获取收藏夹：{}", userid);
        List<BooksVO> booksVO = userService.getFavorites(userid);
        return Result.success(booksVO);
    }

    /**
     * 删除收藏夹中的图书
     */
    @DeleteMapping("/{userid}/favorites/{bookid}")
    public Result deleteFavorites(@PathVariable Long userid, @PathVariable Long bookid) {
        log.info("删除收藏夹中的图书：{}", bookid);
        userService.deleteFavorites(userid, bookid);
        return Result.success();
    }

    /**
     * 创建个人资料
     * @param userInformationDTO,userId
     */
    @PutMapping("/userInformation")
    public Result addUserInformation(@RequestBody UserInformationDTO userInformationDTO) {
        log.info("创建个人资料：{}", userInformationDTO);
        userService.addUserInformation(userInformationDTO);
        return Result.success();
    }

    /**
     * 获取别人的个人资料
     * @param id
     */
    @GetMapping("/userInformation/{id}")
    public Result<UserInformationVO> getUserInformation(@PathVariable Long id) {
        log.info("获取别人的个人资料：{}", id);
        UserInformationVO userInformationVO = userService.getUserInformation(id);
        return Result.success(userInformationVO);
    }

    /**
     * 关注他人
     * @param userFollowsDTO
     */
    @PostMapping("/follows")
    public Result addFollows(@RequestBody UserFollowsDTO userFollowsDTO) {
        log.info("关注他人：{}", userFollowsDTO);
        userService.addFollows(userFollowsDTO);
        return Result.success();
    }

    /**
     * 获取用户的关注者列表
     * @param id
     */
    @GetMapping("/follows/follower/{id}")
    public Result<List<Long>> getFollower(@PathVariable Long id) {
        log.info("获取用户的关注者列表：{}", id);
        List<Long> userFollowsDTOList = userService.getFollower(id);
        return Result.success(userFollowsDTOList);
    }

    /**
     * 获取用户‘关注的用户’列表
     * @param id
     */
    @GetMapping("/follows/followed/{id}")
    public Result<List<Long>> getFollowed(@PathVariable Long id) {
        log.info("获取用户‘关注的用户’列表：{}", id);
        List<Long> userFollowsDTOList = userService.getFollowed(id);
        return Result.success(userFollowsDTOList);
    }

    /**
     * 头像上传回显
     */
    @PostMapping("/avatar/{id}")
    public Result<String> avatar(@PathVariable Long id,@RequestParam("file") MultipartFile file) {
        log.info("头像上传回显：{}", file);
        try {
            //原始文件名
           String originalFilename = file.getOriginalFilename();
           //截取后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName,id);


            return Result.success(filePath);
        } catch (IOException e) {
            log.error("头像上传失败:{}",e);
        }
        return Result.error("头像上传失败");
    }


}
