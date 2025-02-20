package com.library.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.library.dto.*;
import com.library.entity.Books;
import com.library.entity.Reviews;
import com.library.entity.Tags;
import com.library.mapper.BooksMapper;
import com.library.result.PageResult;
import com.library.service.BooksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BooksServicelmpl implements BooksService {

    @Autowired
    private BooksMapper booksMapper;

    /**
     * 添加书籍
     *
     * @param booksDTO
     */
    public void addBooks(BooksDTO booksDTO) {
        Books books = Books.builder()
                .bookId(booksDTO.getBookId())
                .bookName(booksDTO.getBookName())
                .author(booksDTO.getAuthor())
                .isbn(booksDTO.getIsbn())
                .price(booksDTO.getPrice())
                .publishDate(booksDTO.getPublishDate())
                .description(booksDTO.getDescription())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now()).build();

        booksMapper.insert(books);
    }

    /**
     * 查询所有书籍
     *
     * @return 书籍列表
     */
    public List<Books> getAllBooks() {
        return booksMapper.selectAll();
    }

    /**
     * 根据id查询书籍
     * @param booksGetByIdDTO
     * @return 单本书
     */
    public Books getBooksById(BooksGetByIdDTO booksGetByIdDTO) {
        return booksMapper.selectById(booksGetByIdDTO.getBookId());
    }

    /**
     * 更新书籍
     * @param booksDTO
     */
    public void updateBooks(BooksDTO booksDTO) {
        Books book=new Books();
        BeanUtils.copyProperties(booksDTO,book);
        book.setUpdateTime(LocalDateTime.now());
        booksMapper.updateBooks(book);
    }

    /**
     * 根据id删除书籍
     */
    public void deleteBooks(Long bookId) {
        booksMapper.deleteBooks(bookId);
    }

    /**
     * 分页获取书籍列表并根据sort排序
     * @param booksPageQueryDTO
     * @return
     */
    public PageResult pageQuery(BooksPageQueryDTO booksPageQueryDTO){
        PageHelper.startPage(booksPageQueryDTO.getPage(),booksPageQueryDTO.getPageSize());
        Page<Books> page =booksMapper.pageQuery(booksPageQueryDTO);
        long total=page.getTotal();
        List<Books> records=page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 查询书籍和多条件过滤
     * @param booksSearchDTO
     */
    public List<Books> searchBooks(BooksSearchDTO booksSearchDTO) {
        return booksMapper.searchBooks(booksSearchDTO);
    }

    /**
     * 根据分类id查询书籍
     * @param categoryId
     * @return
     */
    public List<Books> getBooksByCategoriesId(Long categoryId) {
        return booksMapper.getBooksByCategoriesId(categoryId);
    }

    /**
     * 添加书籍评论
     * @param reviewsDTO
     */
    public void addReviews(ReviewsDTO reviewsDTO) {
        Reviews reviews=new Reviews();
        BeanUtils.copyProperties(reviewsDTO,reviews);
        reviews.setCreateTime(LocalDateTime.now());
        booksMapper.addReviews(reviews);
    }

    /**
     * 根据书籍id查询评论
     * @param bookId
     * @return
     */
    public List<Reviews> getReviews(Long bookId) {
        return booksMapper.getReviews(bookId);
    }

    /**
     * 根据评论id删除评论
     * @param reviewId
     * @return
     */
    public void deleteReviews(Long reviewId) {
        booksMapper.deleteReviews(reviewId);
    }

    /**
     * 添加标签
     * @param tagsDTO
     */
    public void addTags(TagsDTO tagsDTO) {
        booksMapper.addTags(tagsDTO);
    }

    /**
     * 添加书籍标签
     * @param booksTagDTO
     */
    public void addTag(BooksTagDTO booksTagDTO) {
        booksMapper.addBooksTag(booksTagDTO);
    }

    /**
     * 获取图书的标签列表
     * @param bookId
     */
    public List<Tags> getTags(Long bookId) {
        return booksMapper.getTags(bookId);
    }

    /**
     * 获取标签的图书列表
     * @param tagId
     */
    public List<Books> getBooksByTagId(Long tagId) {
        return booksMapper.getBooksByTagId(tagId);
    }

    /**
     * 从图书中删除标签
     * @param booksTagDTO
     */
    public void deleteTag(BooksTagDTO booksTagDTO) {
        booksMapper.deleteTag(booksTagDTO);
    }
}
