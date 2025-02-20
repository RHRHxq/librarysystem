package com.library.controller.admin;

import com.library.dto.*;
import com.library.entity.Books;
import com.library.entity.Reviews;
import com.library.entity.Tags;
import com.library.result.PageResult;
import com.library.result.Result;
import com.library.service.BooksService;
import com.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class BooksController {

    @Autowired
    private BooksService booksService;
    /**
     * 新增书籍
     *
     * @param booksDTO
     * @return
     */
    @PostMapping("/books")
    public Result<String> addBooks(@RequestBody BooksDTO booksDTO){
        log.info("新增书籍：{}", booksDTO);
        booksService.addBooks(booksDTO);
        return Result.success();
    }

    /**
     * 获取全部书籍
     *
     * @param
     * @return 书籍列表
     */
    @GetMapping("/books")
    public Result<List<Books>> getAllBooks(){
        log.info("获取全部书籍");
        List<Books> books= booksService.getAllBooks();
        return Result.success(books);
    }

    /**
     * 获取单本图书
     * @param bookId
     * @return 单本书
     */
    @GetMapping("/books/{bookId}")
    public Result<Books> getBooksById(@PathVariable("bookId") Long bookId){
        BooksGetByIdDTO booksGetByIdDTO = new BooksGetByIdDTO();
        booksGetByIdDTO.setBookId(bookId);
        log.info("获取单本图书：{}", booksGetByIdDTO);
        Books book = booksService.getBooksById(booksGetByIdDTO);
        return Result.success(book);
    }

    /**
     * 更新书籍信息
     */
    @PutMapping("/books")
    public Result<String> updateBooks(@RequestBody BooksDTO booksDTO){
        log.info("更新书籍信息：{}", booksDTO);
        booksService.updateBooks(booksDTO);
        return Result.success();
    }

    /**
     * 根据id删除书籍
     */
    @DeleteMapping("/books/{bookId}")
    public Result<String> deleteBooks(@PathVariable("bookId") Long bookId){
        log.info("删除书籍:{}",bookId);
        booksService.deleteBooks(bookId);
        return Result.success();
    }

    /**
     * 分页获取书籍列表并根据sort排序
     * @param booksPageQueryDTO
     * @return
     */
    @GetMapping("/books/page")
    public Result<PageResult> page(BooksPageQueryDTO booksPageQueryDTO){
        log.info("分页获取书籍列表：{}", booksPageQueryDTO);
        PageResult pageResult = booksService.pageQuery(booksPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 查询书籍和多条件过滤
     * @param booksSearchDTO
     */
    @GetMapping("/books/searchbooks")
    public Result<List<Books>> search(BooksSearchDTO booksSearchDTO){
        log.info("查询书籍和多条件过滤：{}", booksSearchDTO);
        List<Books> books = booksService.searchBooks(booksSearchDTO);
        return Result.success(books);
    }

    /**
     * 根据分类id获取该分类下的书籍
     * @param categoryId
     */
    @GetMapping("/books/getbooksbycategoriesid/{categoryId}")
    public Result<List<Books>> getBooksByCategoriesId(@PathVariable("categoryId") Long categoryId){
        log.info("根据分类id获取该分类下的书籍：{}", categoryId);
        List<Books> books = booksService.getBooksByCategoriesId(categoryId);
        return Result.success(books);
    }


    /**
     * 添加评论
     * @param reviewsDTO
     */
    @PostMapping("/books/reviews")
    public Result<String> addReviews(@RequestBody ReviewsDTO reviewsDTO){
        log.info("添加评论：{}", reviewsDTO);
        booksService.addReviews(reviewsDTO);
        return Result.success();
    }

    /**
     * 获取书籍评论
     * @param bookId
     */
    @GetMapping("/books/reviews/{bookId}")
    public Result<List<Reviews>> getReviews(@PathVariable("bookId") Long bookId){
        log.info("获取书籍评论：{}", bookId);
        List<Reviews> reviews = booksService.getReviews(bookId);
        return Result.success(reviews);
    }

    /**
     * 删除评论
     * @param reviewId
     */
    @DeleteMapping("/books/reviews/{reviewId}")
    public Result<String> deleteReviews(@PathVariable("reviewId") Long reviewId){
        log.info("删除评论：{}", reviewId);
        booksService.deleteReviews(reviewId);
        return Result.success();
    }

    /**
     * 添加标签
     * @param tagsDTO
     */
    @PostMapping("/books/tags")
    public Result<String> addTags(@RequestBody TagsDTO tagsDTO){
        log.info("添加标签：{}", tagsDTO);
        booksService.addTags(tagsDTO);
        return Result.success();
    }

    /**
     * 为图书添加标签
     * @param booksTagDTO
     */
    @PostMapping("/books/addtag")
    public Result<String> addTag(@RequestBody BooksTagDTO booksTagDTO){
        log.info("为图书添加标签：{}", booksTagDTO);
        booksService.addTag(booksTagDTO);
        return Result.success();
    }

    /**
     * 获取图书的标签列表
     * @param bookId
     */
    @GetMapping("/books/gettags/{bookId}")
    public Result<List<Tags>> getTags(@PathVariable("bookId") Long bookId){
        log.info("获取图书的标签列表：{}", bookId);
        List<Tags> tags = booksService.getTags(bookId);
        return Result.success(tags);
    }

    /**
     * 获取标签的图书列表
     * @param tagId
     */
    @GetMapping("/books/getbookbytagid/{tagId}")
    public Result<List<Books>> getBooksByTagId(@PathVariable("tagId") Long tagId){
        log.info("获取标签的图书列表：{}", tagId);
        List<Books> books = booksService.getBooksByTagId(tagId);
        return Result.success(books);
    }

    /**
     * 从图书中删除标签
     * @param booksTagDTO
     */
    @DeleteMapping("/books/deletetag")
    public Result<String> deleteTag(@RequestBody BooksTagDTO booksTagDTO){
        log.info("从图书中删除标签：{}", booksTagDTO);
        booksService.deleteTag(booksTagDTO);
        return Result.success();
    }



}
