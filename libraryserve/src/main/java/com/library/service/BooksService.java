package com.library.service;

import com.library.dto.*;
import com.library.entity.Books;
import com.library.entity.Reviews;
import com.library.entity.Tags;
import com.library.result.PageResult;

import java.util.List;

public interface BooksService {
    /**
     * 新增书籍
     * @param booksDTO
     */
    void addBooks(BooksDTO booksDTO);

    /**
     * 获取所有书籍
     * @return 书籍列表
     */
    List<Books> getAllBooks();

    /**
     * 根据书籍id获取书籍
     * @param booksGetByIdDTO
     * @return 书籍
     */
    Books getBooksById(BooksGetByIdDTO booksGetByIdDTO);

    /**
     * 更新书籍
     * @param booksDTO
     */
    void updateBooks(BooksDTO booksDTO);

    /**
     * 根据id删除书籍
     */
    void deleteBooks(Long bookId);

    /**
     * 分页获取书籍列表并根据sort排序
     * @param booksPageQueryDTO
     * @return
     */
    PageResult pageQuery(BooksPageQueryDTO booksPageQueryDTO);


    /**
     * 根据书籍名、作者、ISBN、出版日期、价格范围搜索书籍
     * @param booksSearchDTO
     * @return
     */
    List<Books> searchBooks(BooksSearchDTO booksSearchDTO);

    /**
     * 根据分类id获取书籍列表
     * @param categoryId
     * @return
     */
    List<Books> getBooksByCategoriesId(Long categoryId);

    /**
     * 添加书籍评论
     * @param reviewsDTO
     */
    void addReviews(ReviewsDTO reviewsDTO);

    /**
     * 根据书籍id获取书籍评论
     * @param bookId
     * @return
     */
    List<Reviews> getReviews(Long bookId);

    /**
     * 根据评论id删除评论
     * @param reviewId
     */
    void deleteReviews(Long reviewId);

    /**
     * 添加书籍标签
     * @param tagsDTO
     */
    void addTags(TagsDTO tagsDTO);

    /**
     * 添加书籍标签
     * @param booksTagDTO
     */
    void addTag(BooksTagDTO booksTagDTO);

    /**
     * 获取图书的标签列表
     * @param bookId
     */
    List<Tags> getTags(Long bookId);

    /**
     * 获取标签的图书列表
     * @param tagId
     */
    List<Books> getBooksByTagId(Long tagId);

    /**
     * 从图书中删除标签
     * @param booksTagDTO
     */
    void deleteTag(BooksTagDTO booksTagDTO);
}
