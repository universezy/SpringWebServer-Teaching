# Spring快速上手攻略之搭建纯后端web工程

## 摘要

本文讲述作者初学Spring时经历的痛苦和折磨，以及发誓要整理出本文来帮助初学Spring的朋友，同时分享一些心得体会。

阅读此文请保持足够的耐心，跟我一起发牢骚，跟我一起学知识。

大纲：Maven+Tomcat+Servlet+JDBC+MySQL+Postman+RESTful

---
## 一、 吐槽

### 1. 网上教程相互抄袭

抄袭现象在博客界可以说是非常泛滥了，但是你在原作者允许的条件下，加上转载说明，原文出处，表明非原创，也不是什么问题。

同样一篇教程内容，出现在不同网站不同作者，抄也就算了，拜托抄袭者用点心好吗？你能不能把超链接设置一下，而不是直接复制文本？链接点不开你不知道吗？图片加载不出来你不知道吗？自己实际操作过吗？到底行不行试过吗？

---
### 2. 网上教程差异性过大

就拿Spring教程来说，百度找几篇不同的教程，从工程结构，包结构，到命名，方法调用，注解使用，以及配置文件的编写，参差不齐，差异性过大，导致参考性非常小，初学者从每篇文章中能提取到仅有的一点点有价值的东西，把多篇的凑一起，然后应用于个人项目中，然后你会发现怎么也跑不起来了。

甚至同样一个beans的xml文件，不同的教程教你往不同的文件夹下扔，看得读者想骂人，到底该相信谁的？

---
### 3. 网上教程答非所需

作者我学习Spring是为了做纯后端开发，配合前端做接口和数据库处理。而网上教程实现的整体功能往往根本不是纯业务请求处理。例如：

#### (1)
```
public class MainApp {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
      obj.getMessage();
   }
}
```

控制台输出：
```
Your Message : Hello World!
```

照着敲，不错，你也能输出这段话，可是这跟前端请求没有什么关系。顶多让你见识一下依赖注入的动态加载JavaBean。

#### (2)
```
@Controller
@RequestMapping("/hello")
public class HelloController{
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
}
```

```
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
   <title>Hello World</title>
</head>
<body>
   <h2>${message}</h2>
</body>
</html>
```
看到这里就慌了，Excuse me，jsp？这跟想象中的请求处理不一样呢！

---
### 4. 网上教程过于空洞

网上搜"Spring教程"，各种各样的资源中，有讲Spring框架的，有讲MVC模型的，有讲IoC的，有讲Bean的，有讲生命周期的，花大量时间全部过一遍后，再回到自己工程，无从下手啊，没有注解API文档，不知道怎么用，web.xml没有规范模版，不知道要设置哪些参数，搞半天不会还是不会，网上的内容说得天花乱坠，也没有落实到点子上——实现一个具体的纯后端Demo。

一会儿教你写controller，一会儿写Service，一会儿写一大堆xml，配置各种不认识的标签，最后连xml文件往哪里丢都不知道，Tomcat一启动就是鲜红的Exception。

---
## 二、 攻略

### 1. 准备工作

如果读者使用过servlet，那么对其中的
```
@Override
public void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
}
```
深有体会，心里肯定早就在想，Spring也应该有一个这样类似的方法来处理请求吧？如果没有使用过，我建议读者可以先去学学servlet了解一些基本的东西，因为Spring也是对这些servlet进行了包装，后面教程会详细介绍。

本文教程案例基于：操作系统Ubuntu 16.04 LTS，集成开发环境Eclipse，项目管理工具Maven，服务器Tomcat。

如果你使用的不是Ubuntu系统，不必担心，你只需配置好java环境就行。

如果你使用的不是Eclipse，比如IntelliJ IDEA，也不必担心，你只需在IDE差异性操作的时候会用就行。

如果没有安装Maven，可以参考我的另一篇文章《[Ubuntu下Maven的配置](https://blog.csdn.net/zy13608089849/article/details/79725469)》。
有了Maven，最直观的体验在于依赖第三方jar包不需要手动下载导入，而是统一在一个文件中管理，并且很轻松地修改版本，同时，在打包Spring工程导出文件时，Maven可以省很多事。

如果没有安装Tomcat，可以参考我的另一篇文章《[Ubuntu下搭建Tomcat服务器](https://blog.csdn.net/zy13608089849/article/details/79730550)》。
如果你用的不是Tomcat，也不必担心，你只需会使用你的服务器工具就行。

---
### 2. 创建Maven工程

先参考我的另一篇文章《[使用Maven构建Java Web工程的教程]()》，此处不再赘述。

---
### 3. 添加Spring依赖


---
## 三、 源码

[SpringWebServer-Teaching](https://github.com/frogfans/SpringWebServer-Teaching)

---
## 四、 心得

- 时间充足的情况下，网上那些"空洞"的教程还是好好看，熟悉那些基本概念、模型。

- 急于完成项目的情况下，可以按照本文快速搭建一个Spring工程模版。

- 网上的教学资源很有限，并且有些已经过时了，有的简直胡言乱语，读者应该辩证性地去阅读理解，而不是盲目照葫芦画瓢，要有质疑精神。
