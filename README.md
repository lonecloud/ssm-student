# ssm-student

## 运行环境

    JDK1.8+Maven

**技术栈**
- mysql
- maven
- Spring
- SpringMVC
- mybatis
- JSP
- JQuery
- BootStrap
- bootStrap-table

**实现功能**
- 登录
- 注册
- 学生的数据的CRUD(增删改查)
- 学生数据的导入，导出（基于POI的Excel的数据解析）

**后期实现功能**

- 前后端分离
- 邮件系统
- 注册认证
- 短信API接入
- ......不仅仅局限于这些

## 执行

1. 下载代码
```shell
git clone https://github.com/lonecloud/ssm-student
```
2. 升级数据库运行student.sql
3. 运行maven脚本
```shell
mvn compile
mvn tomcat7:run
```
访问地址：http://localhost:8080/student


