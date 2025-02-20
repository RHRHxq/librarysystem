package com.library.controller.admin;

import com.library.dto.CategoriesDTO;
import com.library.entity.Categories;
import com.library.result.Result;
import com.library.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    /**
     * 添加分类
     * @param categoriesDTO
     */
    @RequestMapping("/categories")
    public void addCategories(@RequestBody CategoriesDTO categoriesDTO) {
        log.info("添加分类：{}", categoriesDTO);
        categoriesService.addCategories(categoriesDTO);
    }

    /**
     * 获取所有分类
     */
    @GetMapping("/categories")
    public Result <List<CategoriesDTO>> getAllCategories() {
        log.info("获取所有分类");
        return categoriesService.getAllCategories();
    }

    /**
     * 获取指定id的分类
     * @param categoryId
     */
    @GetMapping("/categories/{categoryId}")
    public Result <Categories> getCategoriesById(@PathVariable("categoryId") Long categoryId) {
        log.info("获取指定id的分类：{}", categoryId);
        return categoriesService.getCategoriesById(categoryId);
    }

    /**
     * 更新分类
     * @param categoriesDTO
     */
    @PutMapping("/categories")
    public void updateCategories(@RequestBody CategoriesDTO categoriesDTO) {
        log.info("更新分类：{}", categoriesDTO);
        categoriesService.updateCategories(categoriesDTO);
    }

    /**
     * 删除分类
     * @param categoryId
     */
    @DeleteMapping("/categories/{categoryId}")
    public Result deleteCategories(@PathVariable("categoryId") Long categoryId) {
        log.info("删除分类：{}", categoryId);
        categoriesService.deleteCategories(categoryId);
        return Result.success();
    }

    /**
     * 根据分类id统计该分类下的图书数量
     */
    @GetMapping("/categories/count/{categoryid}")
    public Result<Integer> countBooksByCategoryId(@PathVariable("categoryid") Long categoryId) {
        log.info("根据分类id统计该分类下的图书数量：{}", categoryId);
        return categoriesService.countBooksByCategoryId(categoryId);
    }



}
