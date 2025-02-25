package com.library.mapper;

import com.github.pagehelper.Page;
import com.library.dto.*;
import com.library.entity.BookBorrows;
import com.library.entity.Books;
import com.library.entity.Reviews;
import com.library.entity.Tags;
import com.library.vo.BookBorrowsVO;
import com.library.vo.BooksVO;
import com.library.vo.ReviewsVO;
import com.library.vo.TagsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BooksMapper {

    /**
     * 添加书籍
     * @param books
     */
    @Insert("insert into books(book_name,author,isbn,price,description,publish_date,create_time,update_time)" +
            " values" +
            "(#{bookName},#{author},#{isbn},#{price},#{description},#{publishDate},#{createTime},#{updateTime})")
    void insert(Books books);

    /**
     * 获取全部书籍
     * @return 书籍列表
     */
    @Select("select * from books")
    List<BooksVO> selectAll();

    /**
     * 根据id获取书籍
     * @param bookId
     * @return 书籍
     */
    @Select("select * from books where book_id = #{bookId}")
    BooksVO selectById(Long bookId);

    /**
     * 更新书籍
     * @param book
     */
    void updateBooks(Books book);

    /**
     * 删除书籍
     * @param bookId
     */
    @Delete("delete from books where book_id = #{bookId}")
    void deleteBooks(Long bookId);

    /**
     * 分页获取书籍列表并根据sort排序
     * @param booksPageQueryDTO
     * @return
     */
    Page<Books> pageQuery(BooksPageQueryDTO booksPageQueryDTO);

    /**
     * 查询书籍和多条件过滤
     * @param booksSearchDTO
     */
    List<BooksVO> searchBooks(BooksSearchDTO booksSearchDTO);

    /**
     * 根据分类id获取书籍
     * @param categoryId
     * @return
     */
    List<BooksVO> getBooksByCategoriesId(Long categoryId);

    /**
     * 添加评论
     * @param reviews
     */
    @Insert("insert into reviews(review_id,book_id,user_id,content,rating,creat_time,parent_review_id)" +
            "values "+
            "(#{reviewId},#{bookId},#{userId},#{content},#{rating},#{createTime},#{parentReviewId})"
    )
    void addReviews(Reviews reviews);

    /**
     * 根据书籍id获取评论
     * @param bookId
     * @return
     */
    @Select("select * from reviews where book_id = #{bookId}")
    List<ReviewsVO> getReviews(Long bookId);

    /**
     * 删除评论
     * @param reviewId
     */
    @Delete("delete from reviews where review_id = #{reviewId}")
    void deleteReviews(Long reviewId);

    /**
     * 添加标签
     * @param tagsDTO
     */
    @Insert("insert into tags(tag_id,tag_name) values (#{tagId},#{tagName})")
    void addTags(TagsDTO tagsDTO);

    /**
     * 添加书籍标签
     * @param booksTagDTO
     */
    @Insert("insert into book_tags(tag_id,book_id) values (#{tagId},#{bookId})")
    void addBooksTag(BooksTagDTO booksTagDTO);

    /**
     * 获取图书的标签列表
     * @param bookId
     */
    @Select("select * from tags where tag_id in (select tag_id from book_tags where book_id = #{bookId})")
    List<TagsVO> getTags(Long bookId);

    /**
     * 获取标签的图书列表
     * @param tagId
     */
    @Select("select * from books where book_id in (select book_id from book_tags where tag_id = #{tagId})")
    List<BooksVO> getBooksByTagId(Long tagId);

    /**
     * 从图书中删除标签
     * @param booksTagDTO
     */
    @Delete("delete from book_tags where tag_id = #{tagId} and book_id = #{bookId}")
    void deleteTag(BooksTagDTO booksTagDTO);

    /**
     * 借阅图书
     * @param bookBorrows
     */
    @Insert("insert into book_borrows(user_id,book_id,borrow_time,return_time,available) values (#{userId},#{bookId},#{borrowTime},#{returnTime},#{available})")
    void borrowBooks(BookBorrows bookBorrows);

    /**
     * 归还图书
     * @param booksBorrows
     */
    @Update("update book_borrows set return_time = #{returnTime},available = #{available} where user_id = #{userId} and book_id = #{bookId}")
    void returnBooks(BookBorrows booksBorrows);

    /**
     * 获取借阅记录
     * @param userId
     */
    @Select("select * from book_borrows where user_id = #{userId}")
    List<BookBorrowsVO> getBorrowRecord(Long userId);
}
