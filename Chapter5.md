# Chapter 5 —— 编写工程

终于，我们要开始落实到工程代码上了，接下来可能会花费读者大量的时间来学习本章内容，所以在开始阅读本章内容前，请确保你有连续的可以专心学习的时间。

---
## 一、 从简易的Demo开始

### 1. 创建工程

eclipse创建一个Maven工程，选择webapp类型，具体步骤参考我的另一篇教程《[使用Maven构建Java Web工程的教程](https://github.com/frogfans/MavenBuildJavaWeb-Teaching)》。

为了便于读者和本文对照，我建议读者将"Group Id"设置为"com.example"，"Artifact Id"设为"springdemo"，"Package"会自动生成。

### 2. 依赖资源

然后在pom.xml中依赖Spring相关、MySQL相关、JDBC相关，JSON相关的jar包，得到这样：
```
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.example</groupId>
	<artifactId>springdemo</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>springdemo Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<dependencies>
		<!-- junit -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
		<!-- servlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<!-- spring-context -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>
		<!-- spring-beans -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>
		<!-- spring-web -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>
		<!-- spring-webmvc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>
		<!-- spring-jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>
		<!-- spring-transaction -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>
		<!-- mysql-connector -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>6.0.6</version>
		</dependency>
		<!-- fastjson -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.47</version>
		</dependency>
	</dependencies>
	<build>
		<finalName>springdemo</finalName>
	</build>
</project>
```

### 3. 实现Controller

在src/main/java这个文件夹下创建一个名为"com.example.springdemo"的package。

在package下创建一个名为"DemoController"的class。

给DemoController加上@Controller注解，告诉Framework这是一个Controller。

再给DemoController加上@RequestMapping注解，括号里设置url，例如：@RequestMapping(value = "/demo")(省略value则默认是value的值)。

添加一个处理请求的方法，同样加上@RequestMapping注解并设置url和请求方法，例如：@RequestMapping(value = "/test", method = RequestMethod.GET)，那么此方法映射的请求url相对路径就是Controller的路径再拼接方法的路径，接收的请求方法是GET类型。

完整示例：
```
package com.example.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		System.out.println("test message");
	}
}
```

### 4. 配置xml文件

找到"/webapp/WEB-INF"下的web.xml文件，定义一个DispatcherServlet，这里用框架自带的：
```
<servlet>
	<servlet-name>demo</servlet-name>
	<servlet-class>
		org.springframework.web.servlet.DispatcherServlet
	</servlet-class>
	<load-on-startup>1</load-on-startup>
</servlet>
```

名字叫"demo"，"load-on-startup"表示启动顺序，大于或等于0时表示Spring容器启动时便启动这个servlet，数字越小的，启动优先级越高。

然后定义这个名叫"demo"的DispatcherServlet负责分发哪些url的请求，这里我们让它分发所有的请求：
```
<servlet-mapping>
	<servlet-name>demo</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```

负责请求分发的servlet定义好了，我们还要告诉它处理这些请求的Controller在哪里，因此我们需要创建一个用这里的servlet名称为前缀，"-servlet"为后缀的xml文件，即"demo-servlet.xml"，并在里面告诉容器去哪里找Controller：
```
<context:component-scan base-package="com.example.springdemo" />
```

这一行代码，告诉容器扫描"com.example.springdemo"这个包下面所有的Controller，其实不止是Controller，其他的组件也是在这个扫描范围内的。

完整示例：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
   http://www.springframework.org/schema/beans     
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
   http://www.springframework.org/schema/context
   http://www.springframework.org/schema/context/spring-context-3.0.xsd">

	<context:component-scan base-package="com.example.springdemo" />

</beans>
```

接下来回想我们在第三章从xml文件获取bean时用到的"ApplicationContext"，同样，我们即使不在代码中直接出现这个获取操作，也需要通过xml文件来定义这个操作。

创建一个名为"applicationContext"的xml文件，里面就可以写各种复用的bean了。由于我们将要用到很多注解，因此为了让容器认识这些注解，还得在这个文件里加入一行：
```
<context:annotation-config />
```

完整示例：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">

	<context:annotation-config />

</beans>
```

写了这个加载bean的文件，但是容器不知道要去调用这个文件啊，所以又回到web.xml中，加上这两段：
```
<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/applicationContext.xml</param-value>
</context-param>

<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

告诉容器，先调用这个文件去执行相关设置，然后监听我加载bean的需求。

至此，完整的web.xml示例：
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app id="WebApp_ID" version="2.4"
	xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
   http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">

		<display-name>Archetype Created Web Application</display-name>

		<!-- spring-servlet -->
		<servlet>
			<servlet-name>demo</servlet-name>
			<servlet-class>
				org.springframework.web.servlet.DispatcherServlet
			</servlet-class>
			<load-on-startup>1</load-on-startup>
		</servlet>

		<servlet-mapping>
			<servlet-name>demo</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>

		<!-- spring-config -->
		<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/applicationContext.xml</param-value>
		</context-param>

		<listener>
			<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		</listener>
</web-app>
```

工程目录结构为：

![](assets/ch5-1.jpg)

### 5. 运行

工程右键，Run As - Run on Server，选择你的Tomcat服务器，如果一切顺利的话，eclipse会自动弹出网页，如果没有弹出，可以在浏览器输入：http://localhost:8080/springdemo/。

![](assets/ch5-2.jpg)

此时显示的便是"/webapp"目录下的index.jsp文件的页面。

我们尝试输入刚才在Controller中定义的请求处理对应的url：http://localhost:8080/springdemo/demo/test

控制台输出了方法中的内容：

![](assets/ch5-3.jpg)

会报错404找不到页面，这是必然的，因为我们没有写这个url对应的页面，我们只需要有一个可以处理请求的入口就行了。

---
## 二、 接收前端请求



---
## 三、 返回服务端的响应



---
## 四、 按照MVC架构分包



---
## 五、 使用注解来组合模块



---
## 六、 编写数据库



---
## 七、 定义接口文档



---
## 八、 测试接口



---
## 九、 总结回顾




---

- [首页](README.md)

- [Chapter 1 —— 吐槽网上教程](Chapter1.md)

- [Chapter 2 —— 准备工作](Chapter2.md)

- [Chapter 3 —— 认识IoC](Chapter3.md)

- [Chapter 4 —— 剖析SpringMVC架构](Chapter4.md)

- Chapter 5 —— 编写工程

- [Chapter 6 —— 后记](Chapter6.md)
