package com.library.service;

import com.library.dto.CategoriesDTO;
import com.library.entity.Categories;
import com.library.result.Result;
import com.library.vo.CategoriesVO;

import java.util.List;

public interface CategoriesService {
    /**
     * 添加分类
     * @param categoriesDTO
     */
    void addCategories(CategoriesDTO categoriesDTO);

    /**
     * 获取所有分类
     * @return
     */
    Result<List<CategoriesVO>> getAllCategories();

    /**
     * 根据分类id获取分类
     * @param categoryId
     * @return
     */
    Result<CategoriesVO> getCategoriesById(Long categoryId);

    /**
     * 更新分类
     * @param categoriesDTO
     */
    void updateCategories(CategoriesDTO categoriesDTO);

    /**
     * 根据分类id删除分类
     * @param categoryId
     */
    void deleteCategories(Long categoryId);

    /**
     * 根据分类id获取分类下的图书数量
     * @param categoryId
     * @return
     */
    Result<Integer> countBooksByCategoryId(Long categoryId);
}
