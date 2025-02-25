package com.library.service;

import com.library.dto.*;
import com.library.entity.User;
import com.library.vo.BooksVO;
import com.library.vo.UserInformationVO;

import java.util.List;

public interface UserService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    User login(UserLoginDTO employeeLoginDTO);


    /**
     * 添加图书到收藏夹
     * @param booksFavoritesDTO
     */
    void addBooksToFavorites(BooksFavoritesDTO booksFavoritesDTO);

    /**
     * 获取收藏夹
     * @param userid
     * @return
     */
    List<BooksVO> getFavorites(Long userid);

    /**
     * 删除收藏夹中的图书
     * @param userid
     * @param bookid
     */
    void deleteFavorites(Long userid, Long bookid);

    /**
     * 创建个人资料
     * @param userInformationDTO,userId
     */
    void addUserInformation(UserInformationDTO userInformationDTO);

    /**
     * 注册
     * @param userRegisterDTO
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 获取个人资料
     * @param id
     * @return
     */
    UserInformationVO getUserInformation(Long id);

    /**
     * 关注他人
     * @param userFollowsDTO
     */
    void addFollows(UserFollowsDTO userFollowsDTO);

    /**
     * 获取用户的关注者列表
     * @param id
     */
    List<Long> getFollower(Long id);

    /**
     * 获取用户‘关注的用户’列表
     * @param id
     */
    List<Long> getFollowed(Long id);
}
