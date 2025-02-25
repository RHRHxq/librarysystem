package com.library.mapper;

import com.library.dto.CategoriesDTO;
import com.library.entity.Categories;
import com.library.vo.CategoriesVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    /**
     * 添加分类
     *
     * @param categoriesDTO
     */
    @Insert("insert into categories(category_id, category_name)" +
            "values " +
            "(#{categoryId},#{categoryName})")
    void insert(CategoriesDTO categoriesDTO);

    /**
     * 获取所有分类
     * @return
     */
    @Select("select * from categories")
    List<CategoriesVO> selectAll();

    /**
     * 根据分类id获取分类
     * @param categoryId
     * @return
     */
    @Select("select * from categories where category_id = #{categoryId}")
    CategoriesVO selectById(Long categoryId);

    /**
     * 更新分类
     * @param categoriesDTO
     */
    void updateById(CategoriesDTO categoriesDTO);

    /**
     * 根据分类id删除分类
     * @param categoryId
     */
    @Delete("delete from categories where category_id = #{categoryId}")
    void deleteById(Long categoryId);

    /**
     * 根据分类id获取分类下的图书数量
     * @param categoryId
     * @return
     */
    @Select("select count(*) from books where book_id in (select book_id from book_categories where category_id = #{categoryId})")
    Integer countBooksByCategoryId(Long categoryId);
}
