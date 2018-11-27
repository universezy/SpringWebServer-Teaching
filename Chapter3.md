# Chapter 3 —— 认识IoC

在开始学习SpringMVC架构之前，我们先来认识IoC——控制反转(Inversion of Control)。

何为IoC？或者说，怎么理解控制反转，由字面翻译意思：把控制权反过来。

我们通常操作一个对象的过程是：声明-创建(可能还需要初始化设置一大堆参数)-使用，就像这样：
```java
package com.zengyu.demo;

public class Obj {
	private int id;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void doSth() {
		System.out.println("My id is " + id + " , my name is " + name);
	}
}
```

```java
package com.zengyu.demo;

public class IoCDemo {
	private Obj obj;

	public void method() {
		obj = new Obj();
		obj.setId(1);
		obj.setName("IoC Demo");
		obj.doSth();
	}
}
```

如果我们有五十处甚至一百处都需要使用这个对象，也就是说我们得写五十甚至一百遍的new和调用setter，如果将来我们要修改所有的setter的入参，那么就需要去所有使用对象的地方改代码，如果增加或删除了一些内部属性，也需要去所有地方增加或者删除setter，这简直是灾难！

于是我们就想，如果我们使用的每个实例，命名是一样的，属性也是一样的，为什么不把相同的操作封装起来呢？这就稍微有了工厂模式的意思了，但是，还能不能再简化一下？我们需要一个对象的实例，目的是用它做些什么，而不关心它怎么诞生，怎么初始化，但是现在这个对象的控制权在创建它的地方，就不得不去做这些复杂的事。

于是，IoC的概念便诞生了。我们把对象的生死大权交给容器，这个容器就是Spring容器，由它来负责各种对象的创建和回收，我们只需要在声明对象的时候，告诉容器："喂，这个对象给我来一份。"，具体的代码就像这样：

定义实例的属性：
```xml
<bean id="obj" class="com.zengyu.demo.Obj">
    <property name="id" value="1"/>
    <property name="name" value="IoC Demo"/>
</bean>
```

然后使用实例：
```java
package com.zengyu.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCDemo {
	private Obj obj;

	public void method() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		obj = (Obj) context.getBean("obj");
		obj.doSth();
	}
}
```

看到这里，读者便会质疑，你这不就类似于工厂模式？还不是在new，只是省去了setter，是的，我们先通过"将内部属性用外部xml文件来配置"的方式进行了第一步优化，这就相当于把控制权交给了Framework，这里用到的Java的反射机制。接下来是第二步，我们使用"[@Autowired](https://www.w3cschool.cn/wkspring/rw2h1mmj.html)"注解，同样的，注解本质上也是使用的反射机制：
```java
package com.zengyu.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class IoCDemo {
	@Autowired
	private Obj obj;

	public void method() {
		obj.doSth();
	}
}
```

这就完了？是的，这就完了，已经简化地不能再简化了，不过我这里跳过了好几个知识点，只是为了尽可能说明IoC带来的便利性才过早地将注解摆出来，勾起读者的兴趣。后面的内容也是基于注解来优化代码结构的，不再对中间跳过的知识点进行介绍，感兴趣的读者可以参考一些网站上的资源，比如[W3School的教程](https://www.w3cschool.cn/wkspring/f8pc1hae.html)。

关于IoC，我这里仅介绍了关于创建实例的特性，其他的例如生命周期的管理，作用域等，也带来了极大的便利性，你也可以通过上面的教程链接去扩展学习。

使用IoC，我们只需要声明即可使用对象的实例，省去了中间所有的复杂操作，使类不再过度依赖类，进行高度解耦。另一方面，Spring Framework将IoC发挥出了非常棒的效果，我们可以仅仅通过几个注解来告知Framework如何把几个对象组装在一起，来完成我们的服务器。

---

- [首页](README.md)

- [Chapter 1 —— 吐槽网上教程](Chapter1.md)

- [Chapter 2 —— 准备工作](Chapter2.md)

- Chapter 3 —— 认识IoC

- [Chapter 4 —— 剖析SpringMVC架构](Chapter4.md)

- [Chapter 5 —— 编写工程](Chapter5.md)

- [Chapter 6 —— 后记](Chapter6.md)

- [Issues —— 问题或反馈](https://github.com/frogfans/SpringWebServer-Teaching/issues)
