package com.library.mapper;

import com.library.dto.BooksDTO;
import com.library.dto.BooksFavoritesDTO;
import com.library.dto.UserFollowsDTO;
import com.library.dto.UserInformationDTO;
import com.library.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
   // @Select("select * from user where username = #{username} ")
    User getByUsername(String username);

    @Insert("insert into book_favorites(book_id,user_id) values (#{bookId},#{userId})")
    void addBooksToFavorites(BooksFavoritesDTO booksFavoritesDTO);

    /**
     * 根据用户id查询收藏的书籍
     * @param userid
     * @return
     */
    @Select("select * from books where book_id in (select book_id from book_favorites where user_id = #{userid})")
    List<BooksDTO> getFavorites(Long userid);

    /**
     * 根据用户id和书籍id删除收藏的书籍
     * @param userid
     * @param bookid
     */
    @Delete("delete from book_favorites where user_id = #{userid} and book_id = #{bookid}")
    void deleteFavorites(Long userid, Long bookid);

    /**
     * 创建个人资料
     * @param userInformationDTO,userId
     */
    @Update("update user set avatar_path = #{avatarPath},email = #{email},phone = #{phone},name = #{name},gender = #{gender},birthday = #{birthday},bio = #{bio},interests = #{interests} where id = #{id}")
    void addUserInformation(UserInformationDTO userInformationDTO);

    /**
     * 注册
     * @param user
     */
    @Insert("insert into user(username,password,create_time) values (#{username},#{password},#{createTime})")
    void register(User user);

    /**
     * 根据用户id查询个人资料
     *
     * @param id
     * @return
     */
    @Select("select id,avatar_path,name,gender,birthday,bio,interests from user where id = #{id}")
    UserInformationDTO getUserInformation(Long id);

    /**
     * 关注他人
     * @param userFollowsDTO
     */
    @Insert("insert into user_follows(follower_id,followed_id) values (#{followerId},#{followedId})")
    void addFollows(UserFollowsDTO userFollowsDTO);

    @Select("select follower_id from user_follows where followed_id=#{Id}")
    List<Long> getFollower(Long id);

    /**
     * 获取用户‘关注的用户’列表
     * @param id
     */
    @Select("select followed_id from user_follows where follower_id=#{Id}")
    List<Long> getFollowed(Long id);
}
