

# 图书管理系统（librarysystem）

## 项目概述

本项目是一个名为 `librarysystem` 的图书馆系统，主要用于管理图书馆相关业务。

## 项目架构

项目采用 Maven 多模块项目结构，主要包含以下几个模块：

### 1. 父项目（librarysystem）

这是整个项目的父模块，负责管理项目的依赖版本、子模块以及一些公共配置。它的主要作用是统一管理各个子模块的依赖版本，避免版本冲突，同时方便对整个项目进行构建和部署。

### 2. 公共模块（common）

该模块包含一些公共的工具类、常量类以及通用的配置等，供其他模块使用。它可以减少代码的重复，提高代码的复用性。

### 3. POJO 模块（pojo）

主要用于定义项目中使用的各种数据对象（Plain Old Java Objects），如实体类、DTO（Data Transfer Object）等。这些类主要用于数据的传输和存储，将业务逻辑与数据结构分离，提高代码的可维护性。

### 4. 服务模块（libraryserve）

这是项目的核心模块，负责实现具体的业务逻辑，包括处理用户请求、与数据库交互、生成接口文档等。它依赖于其他模块，通过调用公共模块的工具类和使用 POJO 模块的数据对象来完成业务功能。

## 技术栈

项目使用了以下主要技术栈：

### 1. 后端框架

- **Spring Boot**：版本为 2.7.3，用于快速构建独立的、生产级的 Spring 应用程序。它简化了 Spring 应用的配置和部署，提供了自动配置、嵌入式服务器等功能，让开发者可以更专注于业务逻辑的实现。
- **Spring MVC**：作为 Spring 框架的一部分，用于构建 Web 应用程序，处理 HTTP 请求和响应。它提供了注解驱动的编程模型，使得开发 Web 应用变得更加简单和灵活。
- **MyBatis**：版本为 2.2.0，是一款优秀的持久层框架，用于与数据库进行交互。它支持 SQL 映射文件和注解两种方式来编写 SQL 语句，提供了灵活的数据库操作方式。

### 2. 数据库

- **MySQL**：作为项目的数据库管理系统，用于存储和管理图书馆系统的数据。
- **Druid**：版本为 1.2.1，是阿里巴巴开源的数据库连接池，提供了强大的监控和扩展功能，能够提高数据库访问的性能和稳定性。

### 3. 其他依赖

- **Lombok**：版本为 1.18.30，用于减少 Java 代码中的样板代码，如 getter、setter 方法等，提高开发效率。
- **Fastjson**：版本为 1.2.76，是一个高性能的 JSON 处理库，用于处理 JSON 数据的序列化和反序列化。
- **PageHelper**：版本为 1.3.0，是一个 MyBatis 的分页插件，用于实现数据库查询的分页功能。
- **Spring Boot Starter WebSocket**：用于实现 WebSocket 通信，实现实时交互功能。
- **JWT（JSON Web Token）**：版本为 0.9.1，用于实现用户身份认证和授权。
- **Aliyun SDK OSS**：版本为 3.10.2，用于与阿里云对象存储服务（OSS）进行交互，实现文件的上传和下载等功能。

## API 接口介绍

以下是一些主要的接口信息，[API接口文档](./图书管理系统后端.md)：

### 图书管理系统

#### 1. 书籍管理模块

##### 1.图书基础管理

- 说明：该功能模块主要是对图书进行一些基础的管理，对图书进行一些增删改查以及一些特殊的查询功能。
- API端点：
  - `POST /api/admin/books `:  新增书籍（管理员可以添加新的书籍，信息包括：书名，作者，isbn等，期中对isbn等数据具有一定的格式要求）
  - `GET/api/admin/books` :  获取全部书籍``
  - `GET/api/admin/books/{bookId}` : 根据id获取书籍
  - `PUT/api/admin/books` : 根据id修改书籍
  - `DELETE/api/admin/books/{bookId}` : 根据id删除书籍
  - `GET/api/admin/books/page` : 根据分类排序并分页查询书籍
  - `GET/api/admin/books/searchbooks` : 查询书籍和多条件过滤

##### 2.图书评论与评分

- 说明：该功能模块主要是管理书籍的评论和评分相关内容

- API端点：
  - `POST/api/admin/books/reviews` : 添加评论和回复评论（parentReviewID参数为0就是自己评论，不为0 则是回复他人）
  - `GET/api/admin/books/reviews/{bookId}` : 获取图书评论列表
  - `DELETE/api/admin/books/reviews/{reviewId}`  : 删除评论

##### 2.标签管理

- 说明：用户可以自己自定义标签并实现标签的搜索和过滤功能

- API端点：
  - `POST/api/admin/books/tags` : 创建标签
  - `POST/api/admin/books/addtag` : 为图书添加标签
  - `GET/api/admin/books/gettags/{bookId}` : 获取某本图书的所有标签
  - `GET/api/admin/books/getbookbytagid/{tagId}` : 获取某个标签下的所有图书
  - `DELETE/api/admin/books/deletetag` : 从图书中删除标签

##### 3.图书的借阅与归还

- 说明：用户可以进行图书的借阅与归还

- API端点：
  - `POST/api/admin/books/borrow` : 图书借阅
  - `PUT/api/admin/books/return` : 图书归还
  - `GET/api/admin/books/getborrowrecord/{userId}` : 查看借阅历史

#### 2.分类管理模块

- 说明：对图书的分类进行管理以及根据分类进行图书统计

- API端点：
  - `POST/api/admin/categories` : 增加新分类
  - `GET/api/admin/categories` : 获取所有分类
  - `GET/api/admin/categories/{categoryId}` : 获取指定id的分类
  - `PUT/api/admin/categories` : 更新分类信息
  - `DELETE/api/admin/categories/{categoryId}` : 删除分类
  - `GET/api/admin/books/getbooksbycategoriesid/{categoryId}` : 获取某分类下的书籍
  - `GET/api/admin/categories/count/{categories}` : 统计该分类下的书籍数量

#### 3.用户功能模块

- 说明：实现用户的功能，包括注册、登录、收藏、管理个人信息、关注以及头像的上传回显

- API端点：
  - `POST/api/admin/register` : 注册（密码加盐加密）
  - `POST/api/admin/login` : 登录（jwt令牌的实现）
  - `POST/api/admin/{userid}/favorites` : 添加书籍到收藏
  - `GET/api/admin/{userid}/favorites` : 获取收藏列表
  - `DELETE/api/admin/{userid}/favorites/{bookid}` : 删除收藏的书籍
  - `PUT/api/admin/userInformation` : 创建个人资料
  - `GET/api/admin/userInformation/{id}` : 获取个人资料
  - `POST/api/admin/follows` : 关注用户
  - `GET/api/admin/follows/follower/{id}` : 获取某个用户的粉丝列表
  - `GET/api/admin/follows/followed/{id}` : 获取某个用户的关注列表
  - `POST/api/admin/avatar` : 头像上传回显





## 项目环境

1. JDK 17 

2. Maven 3.9.9 

3. MySQL 数据库。

4. 克隆项目到本地：

   ```sh
   git clone <https://github.com/RHRHxq/librarysystem.git>
   ```

## 联系方式

如果在使用过程中遇到任何问题或有任何建议，欢迎联系项目开发者：

- **qq**:2864867655