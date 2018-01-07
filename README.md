1、修正了部分mapper接口无效的问题。
2、UserController为管理员简单API
3、HelloController为授权认证和权限分配测试

新特性
1、集成了通用mapper接口(文档地址：https://gitee.com/free/Mapper/blob/master/wiki/mapper3/5.Mappers.md)，直接调用XxxMapper即可（如果未找到合适的SQL方法，自行在该接口中自定义）

2、支持Druid监控功能（默认开启），地址:localhost:8080/druid，账号/密码:admin/admin

3、定时任务（关闭，未做扩充）

4、EhCache缓存（默认开启，清缓存接口user/removeCache）以及自带Transactional事务管理处理多步数据操作

5、多环境：application-dev.yml为默认开发环境配置，可另建yml以自定义自己的开发环境

6、统一异常处理，装载异常用CustomException，自定义异常种类在
ResponseResultEnum

7、使用AOP处理请求。格式
{
    "code": 0,                        //状态码
    "msg": "success",           //提示信息
    "data": {                         //结果集
        "userId": 6,
        "userName": "admin",
        "password": "admin",
        "privLevel": 1,
        "userEmail": null,
        "isPush": 0
    }
}
，装载函数ResponseResultUtil


8、Pagehelper分页
返回数据格式PageInfo<Object>
{
"pageNum":1,  //当前页
"pageSize":4,  //每页的数量
"size":4,         //当前页的数量
"startRow":1,  //当前页面第一个元素在数据库中的行号
"endRow":4,   //当前页面最后一个元素在数据库中的行号
"total":11,     //总记录数
"pages":3,    //总页数
"list":[{object1},{object2}],      //结果集
"prePage":0,        //前一页
"nextPage":2,      //下一页
"firstPage":1,        //第一页 
"lastPage":3,         //最后一页
"isFirstPage":true,       //是否为第一页
"isLastPage":false,       //是否为最后一页
"hasPreviousPage":false,      //是否有前一页
"hasNextPage":true,             //是否有下一页
"navigatePages":8,               //导航页码数
"navigatepageNums":[1,2,3],    //所有导航页号
"navigateFirstPage":1,    //导航第一页号
"navigateLastPage":3     //导航最后一页号
}

9、Spring Security功能
(1)   配置。在yml->securityconfig配置即可，配置说明看注释

(2)   登录认证。示例中/hello需认证。'/'，/admin/**目录无需验证
       登录成功：跳转首页或登录之前要进入的页面，若访问页面权限不足，则返回json
       登录失败：返回json

(3)   remember me。保存登录用户token

(4)   权限控制。暂定一个用户一个角色，三个角色ROLE_SUPER/ROLE_ADMIN/ROLE_USER。配置也在yml中，示例中/hello/**需ROLE_USER或ROLE_ADMIN；/whoim需ROLE_SUPER
