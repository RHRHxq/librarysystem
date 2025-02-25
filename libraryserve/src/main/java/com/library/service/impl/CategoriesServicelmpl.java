package com.library.service.impl;

import com.library.dto.CategoriesDTO;
import com.library.entity.Categories;
import com.library.mapper.CategoriesMapper;
import com.library.result.Result;
import com.library.service.CategoriesService;
import com.library.vo.CategoriesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoriesServicelmpl implements CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    /**
     * 添加分类
     * @param categoriesDTO
     */
    public void addCategories(CategoriesDTO categoriesDTO) {
        categoriesMapper.insert(categoriesDTO);
    }

    /**
     * 获取所有分类
     * @return
     */
    public Result<List<CategoriesVO>> getAllCategories() {

        return Result.success(categoriesMapper.selectAll());
    }

    /**
     * 根据分类id获取分类
     * @param categoryId
     * @return
     */
    public Result<CategoriesVO> getCategoriesById(Long categoryId) {

        return Result.success(categoriesMapper.selectById(categoryId));
    }

    /**
     * 更新分类
     * @param categoriesDTO
     */
    public void updateCategories(CategoriesDTO categoriesDTO) {
        categoriesMapper.updateById(categoriesDTO);
    }

    /**
     * 根据分类id删除分类
     * @param categoryId
     */
    public void deleteCategories(Long categoryId) {
        categoriesMapper.deleteById(categoryId);
    }

    /**
     * 根据分类id统计书籍数量
     * @param categoryId
     * @return
     */
    public Result<Integer> countBooksByCategoryId(Long categoryId) {
        Integer count = categoriesMapper.countBooksByCategoryId(categoryId);
        return Result.success(count);
    }
}
