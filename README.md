# BookStore后端：以单体架构实现

## 运行程序

以下几种途径，可以运行程序，浏览最终的效果：
- 通过Makefile脚本方式运行：

>```bash
># 进入工程根目录
>$ cd bookstore_arch_monolithic_springboot
>
># 容器方式运行
>$ make start
>```
>
> 然后在浏览器访问：[http://localhost:8080](http://localhost:8080)，系统预置了一个用户（user:tao，pw:123456）。
>
> 默认会使用HSQLDB的内存模式作为数据库，并在系统启动时自动初始化好了Schema，完全开箱即用。但这同时也意味着当程序运行结束时，所有的数据都将不会被保留。


- 通过Docker容器方式运行：

>```bash
> $ docker run -d -p 8080:8080 --name bookstore taosoft/bookstore:monolithic 
>```
>
> 然后在浏览器访问：[http://localhost:8080](http://localhost:8080)，系统预置了一个用户（user:tao，pw:123456）。
>
> 默认会使用HSQLDB的内存模式作为数据库，并在系统启动时自动初始化好了Schema，完全开箱即用。但这同时也意味着当程序运行结束时，所有的数据都将不会被保留。

- 通过Git上的源码，以Maven运行：

>```bash
># 进入工程根目录
>$ cd bookstore_arch_monolithic_springboot
>
># 编译打包
># 采用Maven Wrapper，此方式只需要机器安装有JDK 8或以上版本即可
>$ mvn package
>
># 运行程序，地址为localhost:8080
>$ java -jar target/bookstore_arch_monolithic_springboot-1.0.0-SNAPSHOT.jar
>```
>
>然后在浏览器访问：[http://localhost:8080](http://localhost:8080)，系统预置了一个用户（user:tao，pw:123456），也可以注册新用户来测试。

- 通过Git上的源码，在IDE环境中运行：

> - 以IntelliJ IDEA为例，Git克隆本项目后，在File -> Open菜单选择本项目所在的目录，或者pom.xml文件，以Maven方式导入工程。
>
> - IDEA将自动识别出这是一个SpringBoot工程，并定位启动入口为BookstoreApplication，待IDEA内置的Maven自动下载完所有的依赖包后，运行该类即可启动。
>
> - 如你使用其他的IDE，没有对SpringBoot的直接支持，亦可自行定位到BookstoreApplication，这是一个带有main()方法的Java类，运行即可。
>
> - 可通过IDEA的Maven面板中Lifecycle里面的package来对项目进行打包、发布。
>
> - 在IDE环境中修改配置（如数据库等）会更加简单，具体可以参考工程中application.yml中的内容。


## 工程结构

BookStore单体架构后端参考DDD的分层模式和设计原则，整体分为以下四层：
1. Resource：对应DDD中的User Interface层，负责向用户显示信息或者解释用户发出的命令。
2. Application：对应DDD中的Application层，负责定义软件本身对外暴露的能力，即软件本身可以完成哪些任务，并负责对内协调领域对象来解决问题。
3. Domain：对应DDD中的Domain层，负责实现业务逻辑，即表达业务概念，处理业务状态信息以及业务规则这些行为，此层是整个项目的重点。
4. Infrastructure：对应DDD中的Infrastructure层，向其他层提供通用的技术能力，譬如持久化能力、远程服务通讯、工具集，等等。
