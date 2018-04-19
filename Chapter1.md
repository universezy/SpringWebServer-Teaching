# Chapter 1 —— 吐槽网上教程

## 前言

说起网上的教程就是气，下面一一道来为什么气。

## 一、 网上教程相互抄袭

抄袭现象在博客界可以说是非常泛滥了，但是你在原作者允许的条件下，加上转载说明，原文出处，表明非原创，也不是什么问题。

同样一篇教程内容，出现在不同网站不同作者，抄也就算了，拜托抄袭者用点心好吗？你能不能把超链接设置一下，而不是直接复制文本？链接点不开你不知道吗？图片加载不出来你不知道吗？自己实际操作过吗？到底行不行试过吗？

---
## 二、 网上教程差异性过大

就拿Spring教程来说，百度找几篇不同的教程，从工程结构，包结构，到命名，方法调用，注解使用，以及配置文件的编写，参差不齐，差异性过大，导致参考性非常小，初学者从每篇文章中能提取到仅有的一点点有价值的东西，把多篇的凑一起，然后应用于个人项目中，然后你会发现怎么也跑不起来了。

甚至同样一个beans的xml文件，不同的教程教你往不同的文件夹下扔，看得读者想骂人，到底该相信谁的？

---
## 三、 网上教程答非所需

作者我学习Spring是为了做纯后端开发，配合前端做接口和数据库处理。而网上教程实现的整体功能往往不是纯业务请求处理。例如：

### 1.
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

---
### 2.
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
## 四、 网上教程过于空洞

网上搜"Spring教程"，各种各样的资源中，有讲Spring框架的，有讲MVC模型的，有讲IoC的，有讲Bean的，有讲生命周期的，花大量时间全部过一遍后，再回到自己工程，无从下手啊，没有注解API文档，不知道怎么用，web.xml没有规范模板，不知道要设置哪些参数，搞半天不会还是不会，网上的内容说得天花乱坠，也没有落实到点子上——实现一个具体的纯后端Demo。

一会儿教你写Controller，一会儿写Service，一会儿写一大堆xml，配置各种不认识的标签，最后连xml文件往哪里丢都不知道，Tomcat一启动就是鲜红的Exception。

---

- [首页](README.md)

- Chapter 1 —— 吐槽网上教程

- [Chapter 2 —— 准备工作](Chapter2.md)

- [Chapter 3 —— 认识IoC](Chapter3.md)

- [Chapter 4 —— 剖析SpringMVC架构](Chapter4.md)

- [Chapter 5 —— 编写工程](Chapter5.md)

- [Chapter 6 —— 后记](Chapter6.md)

- [Issues - 问题或反馈](https://github.com/frogfans/SpringWebServer-Teaching/issues)
