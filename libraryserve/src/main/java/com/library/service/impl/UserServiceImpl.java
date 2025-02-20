package com.library.service.impl;

import com.library.constant.MessageConstant;
import com.library.constant.StatusConstant;
import com.library.dto.*;
import com.library.entity.Books;
import com.library.exception.AccountLockedException;
import com.library.exception.AccountNotFoundException;
import com.library.exception.PasswordErrorException;
import com.library.mapper.UserMapper;
import com.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import com.library.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 员工登录
     *
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);
        log.info("hello");
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

//        if (user.getStatus() == StatusConstant.DISABLE) {
//            //账号被锁定
//            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//        }

        //3、返回实体对象
        return user;
    }

    /**
     * 添加图书到收藏夹
     */
    public void addBooksToFavorites(BooksFavoritesDTO booksFavoritesDTO) {
        userMapper.addBooksToFavorites(booksFavoritesDTO);
    }

    /**
     * 获取收藏夹
     */
    public List<BooksDTO> getFavorites(Long userid) {
        return userMapper.getFavorites(userid);
    }

    /**
     * 删除收藏夹中的图书
     * @param userid
     * @param bookid
     */
    public void deleteFavorites(Long userid, Long bookid) {
        userMapper.deleteFavorites(userid, bookid);
    }

    /**
     * 创建个人资料
     * @param userInformationDTO,userId
     */
    public void addUserInformation(UserInformationDTO userInformationDTO) {
        userMapper.addUserInformation(userInformationDTO);
    }

    /**
     * 注册
     * @param userRegisterDTO
     */
    public void register(UserRegisterDTO userRegisterDTO) {
        User user=new User();
        BeanUtils.copyProperties(userRegisterDTO,user);
        user.setCreateTime(LocalDateTime.now());
        userMapper.register(user);
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    public UserInformationDTO getUserInformation(Long id) {
        UserInformationDTO userInformation=userMapper.getUserInformation(id);
        return userInformation;
    }

    /**
     * 关注他人
     * @param userFollowsDTO
     */
    public void addFollows(UserFollowsDTO userFollowsDTO) {
        userMapper.addFollows(userFollowsDTO);
    }

    /**
     * 获取用户的关注者列表
     * @param id
     */
    public List<Long> getFollower(Long id) {
        return userMapper.getFollower(id);
    }

    /**
     * 获取用户‘关注的用户’列表
     * @param id
     */
    public List<Long> getFollowed(Long id) {
        return userMapper.getFollowed(id);
    }

}
