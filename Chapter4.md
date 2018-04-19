# Chapter 4 —— 剖析SpringMVC架构

## 前言

接下来我们开始学习SpringMVC架构，它是整个后端工程的核心思想，在此之前，你需要确保自己已经掌握了MVC的思想。

## 一、 架构图

![](assets/ch4-1.png)

---
## 二、 剖析

### 1. 从层次来说

首先，整个架构大致可以看成一个B/S架构(把数据库也算进了服务端，因为相对用户来说，它不在乎你服务端还要和谁进行交互)，我们只需关注广义的"S"部分中的"Server"部分。(这幅架构图同样完全适用于C/S架构)

服务器端，自上而下，大致分为四个层次：

- Servlet
- Controller
- Service
- Dao

其中，Servlet层包括DispatcherServlet和Handler Mapping，由Spring框架来完成，但我们需要在xml中对其配置。

开发者需要完成的是剩下三个层次以及一些相关的xml文件的配置。

---
### 2. 从功能来说

**DispatcherServlet** 可以理解成一个海关，所有的前端请求和后端响应都从这里经过。

**Handler Mapping** 是一个出入境大厅的导航图，告诉入境的请求应该往哪里去。

**Controller** 则是拿着XXX请求接待处站在门口等候请求的向导，他仅负责把请求带到目的地或者把响应带回出境口。

**Service** 是业务负责人，根据请求去安排相关Dao操作数据库，然后把结果整理或筛选或打包后交给Controller。

**Dao** 就是一个个数据库操作员，每一个Dao都负责一大类业务。

**Model** 对结果进行包装或者转换，以便其他层次能识别分辨这些结果。

**web.xml** 是整个Server的管理局，所有的层次都是他说了算。

**applicationContext.xml** 中可以存放各种配置参数信息，例如Dao操作数据库的认证信息：数据库地址、端口、数据库名、账号、密码等。

---
### 3. 从数据流向来说

**DispatcherServlet** 拦截前端请求，然后交给Handler Mapping进行分发。

**Handler Mapping** 是请求信息和Controller的映射关系，按照请求信息，分发给对应的Controller。

**Controller** 需要加上@Controller注解，告诉Spring框架这是一个Controller。使用@RequestMapping注解可以设置映射的请求的信息。使用@RequestParam注解可以获取请求包含的参数。然后将请求的数据交给Service处理。

**Service** 需要加上@Service注解，告诉Spring框架这是一个Service。Service根据收到的数据，选择合适的Dao来处理数据。

**Dao** 需要加上@Repository注解，告诉Spring框架这是一个Dao。Dao根据收到的数据，拿到数据库的认证信息，进行数据库操作，并将结果交给Model进行包装。

**Model** 将原始结果进行初次处理，包括数据类型的转换、数据组合等，处理完之后交给Dao。

**Dao** 将数据交给Service。

**Service** 把来自一个或多个Dao的数据进行再次处理，然后打包为最后结果交给Controller。

**Controller** 需要使用@ResponseBody注解告诉Spring框架它要返回响应体，然后将来自Service的结果通过DispatcherServlet传给前端。

---

- [首页](README.md)

- [Chapter 1 —— 吐槽网上教程](Chapter1.md)

- [Chapter 2 —— 准备工作](Chapter2.md)

- [Chapter 3 —— 认识IoC](Chapter3.md)

- Chapter 4 —— 剖析SpringMVC架构

- [Chapter 5 —— 编写工程](Chapter5.md)

- [Chapter 6 —— 后记](Chapter6.md)

- [Issues —— 问题或反馈](https://github.com/frogfans/SpringWebServer-Teaching/issues)
