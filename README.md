# maven-SsmDemo
一个使用maven技术搭建ssm框架（Spring、Spring MVC、mybatis）的简单demo（web）
推荐使用eclipse开发工具，安装maven插件（可能有自带）
一、new Maven Project
 使用webapp模板
二、创建完成后webapp\index.jsp会报错原因为未加载tomcat所以jsp报错
 右键项目名Properties->Java Build Path->Libraries->Add Library->Server Runtime->选择你的tomcat版本
 右键项目名Properties->Project Facets 查看Dynamic Web Module版本以及Java版本。
 如果与实际不符合进行如下操作（例如：本实例使用的版本是Dynamic Web Module 3.1，java1.8）
 1.右键项目名Properties->Java Compiler 改变java版本到1.8
 2.项目切换到Navigator视图，修改.settings\org.eclipse.wst.common.project.facet.core.xml（也可以直接在绝对路径修改）
   <installed facet="java" version="1.8"/>
   <installed facet="jst.web" version="3.1"/>
 3.pom.xml添加如下
 <build>
        <finalName>ssm</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
 4.右键项目Maven->Update Project
 此时再查看下Properties->Project Facets 查看Dynamic Web Module版本以及Java版本。是否正确
三、接着将我们需要导入的包导入，修改pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.ssm</groupId>
  <artifactId>SsmDemo</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>SsmDemo Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <properties>
        <!-- Spring版本号 -->
        <spring.version>4.3.8.RELEASE</spring.version>
    </properties>
    <dependencies>
        <!-- Spring相关包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- AOP相关包 -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>1.8.0</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.0</version>
        </dependency>

        <!-- MyBatis相关包 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.3.0</version>
        </dependency>
        <!-- MySQL相关包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.26</version>
        </dependency>
        <!-- 数据库连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.20</version>
        </dependency>

        <!-- Spring集成MyBatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.2.3</version>
        </dependency>

        <!-- JSP标准标签库 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!-- 日志相关包 -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.21</version>
        </dependency>

        <!-- 单元测试相关包 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <finalName>ssm</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
等待下载完成
此时项目结构
mavenDemo
 src/main/java
 src/main/resources
 src/test/java
 缺少了src/test/resources（自己创建）
四、配置web.xml
 web.xml
 <?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
       version="3.1" metadata-complete="true">
       <!-- 加载spring容器 -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:spring/applicationContext-*.xml</param-value>
	</context-param>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<!-- spring mvc 前端控制器 -->
	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- contextConfigLocation配置springmvc加载的配置文件 如果不配置则会加载/WEB-INF/servlet名称-serlvet.xml(springmvc-spring.xml) -->
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:spring/springmvc.xml</param-value>
		</init-param>
	</servlet>


	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<url-pattern>*.action</url-pattern>
	</servlet-mapping>

	<!-- 配置POST编码过滤器 -->
	<filter>
		<filter-name>CharacterEncodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>utf-8</param-value>
		</init-param>
	</filter>

	<filter-mapping>
		<filter-name>CharacterEncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	<welcome-file-list>
		<welcome-file>index.action</welcome-file>
	</welcome-file-list>
</web-app>
五、配置spring*.xml、mybatis.xml
  src/main/resources下创建这些xml文件，具体看项目里的内容
六、配置静态文件log4j.properties和jdbc.properties
  这个具体配置参数可以参考别人的我这里只是简单的配置了常用的
七、开发代码
  src/main/java底下创建controller、mapper、po、servie、service.impl等包。
  mapper、po可用mybatis-generator自动生成。参考 https://github.com/7victor/mybatis-generator
  controller需要加上@Controller注解、ServiceImpl需要加上@Service注解
  通过@Autowired注解获取Service以及Mapper对象
  通过@RequestMapping("/index")注解来设置控制器的路径
八、运行项目
  右键->Run(或者debug)->Maven Build->Goals中填入-Dmaven.tomcat.port=端口号 tomcat:run
  浏览器http://localhost:8180/SsmDemo/index.action   即可访问
