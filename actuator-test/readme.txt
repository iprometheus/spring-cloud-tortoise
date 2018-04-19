本项目是在项目中添加监控模块——actuator
在应用启动的时候，会显示出监控的指标和指标的访问地址，如下所示：
2018-04-19 21:03:31.769  INFO 26208 --- [           main] s.b.a.e.w.s.WebMvcEndpointHandlerMapping : Mapped "{[/actuator/health],methods=[GET],produces=[application/vnd.spring-boot.actuator.v2+json || application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.web.servlet.AbstractWebMvcEndpointHandlerMapping$OperationHandler.handle(javax.servlet.http.HttpServletRequest,java.util.Map<java.lang.String, java.lang.String>)
2018-04-19 21:03:31.771  INFO 26208 --- [           main] s.b.a.e.w.s.WebMvcEndpointHandlerMapping : Mapped "{[/actuator/info],methods=[GET],produces=[application/vnd.spring-boot.actuator.v2+json || application/json]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.web.servlet.AbstractWebMvcEndpointHandlerMapping$OperationHandler.handle(javax.servlet.http.HttpServletRequest,java.util.Map<java.lang.String, java.lang.String>)
2018-04-19 21:03:31.773  INFO 26208 --- [           main] s.b.a.e.w.s.WebMvcEndpointHandlerMapping : Mapped "{[/actuator],methods=[GET],produces=[application/vnd.spring-boot.actuator.v2+json || application/json]}" onto protected java.util.Map<java.lang.String, java.util.Map<java.lang.String, org.springframework.boot.actuate.endpoint.web.Link>> org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping.links(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)

下面介绍一些actuator的一些原生端点：
应用配置类（在应用启动的时候就已经基本确定了其返回内容， 可以说是一个静态报告）：
/autoconfig: 该端点用来获取应用的自动化配置报告， 其中包括所有自动化配置的候选项。
    positiveMatches中返回的是条件匹配成功的自动化配置。
    negativeMatches中返回的是条件匹配不成功的自动化配置。
/beans: 该端点用来获取应用上下文中创建的所有Bean。
    每个Bean 中都包含了下面这些信息。
    bean: Bean 的名称。
    scope: Bean 的作用域。
    type: Bean 的 Java 类型。
    resource: class 文件的具体路径。
    dependencies: 依赖的 Bean 名称。
/confgprops: 该端点用来获取应用中配置的属性信息报告。
/env: 该端点与/confgprops不同它用来获取应用所有可用的环境属性报告。 包括环境变量、N属性、应用的配置属性、命令行中的参数。
/mappings: 该端点用来返回所有Spring MVC的控制器映射关系报告。
/info: 该端点用来返回 一些应用自定义的信息。 默认清况下， 该瑞点只会返回 一个空的JSON内容。我们可以在app巨ca巨on.properties配置文件中通过info前缀来设置一些属性

度量指标（动态变化的，这些端点提供了应用程序在运行过程中的一些快照信息，比如内存使用情况、 HTTP请求统计、外部资源指标等）：
/metrics: 该端点用来返回当前应用的各类重要度量指标，比如内存信息、线程信息、垃圾回收信息等。
    有如下这些重要的度量值：
    系统信息：包括处理器数量 processors、运行时间up巨me和instance.uptime、系统平均负载 sys七emload.average。
    mem.*: 内存概要信息，包括分配给应用的总内存数量以及当前空闲的内存数量。这些信息来自java.lang.Runtime。
    heap.* : 堆内 存 使用情况。 这 些 信 息 来 自 java.lang.management.MemoryMXBean 接口中 getHeapMemoryUsage 方法获取的 java.lang.management.MemoryUsage。
    nonheap.*: 非堆内存使用情况。 这些信息来自 java. lang.management.MemoryMXBean接口中ge七NonHeapMemoryUsage方法获取的java.lang.management.MemoryUsage。
    threads.*: 线程使用情况，包括线程数、守护线程数(daemon)线程峰值(peak)等， 这些数据均来自java.lang.management.ThreadMXBean。
    classes.*: 应用加载和卸载的类统计。这些数据均来自java.lang.management．ClassLoadingMXBean。
    gc.*: 垃圾收集器的详细信息， 包括垃圾回收次数gc.ps—scavenge.count、垃圾回收消耗时间 gc.ps_scavenge.time、 标记－清除算法的次数 gc.psmarksweep.count、 标记－清除算法的消耗时间gc.ps_marksweep.time。这些数据均来自java.lang.managemen七.GarbageCollec七orMXBean。
    httpsessions.* : Tomcat容 器 的会话 使用情况。 包 括最大会话 数httpsessions.max和活跃会话数httpsessions.ac巨ve。 该度量指标信息仅在引入嵌入式Tomcat作为应用容器的时候才会提供。
    gauge.*: HTTP请求的性能指标之一，它主要用来反映一个绝对数值。 比如上面示例中的gauge.response.hello: 5, 它表示上 一 次hello请求的延迟时间为5毫秒。
    counter.*: HTTP 请求的性能指标之一，它主要作为计 数器来使用，记录了增加量和减少量。 上述示例中的counter.s七红us.200.hello: 11, 它代表了 hello请求返回200状态的次数为11。
/health: 它用来获取应用的各类 健康指标信息。在spring-boot-starter-actuator模块中自带实现了 一些常用资源的健康指标检测器。(可以自定义,通过重载Healthindicator)
           检测器                  功能
    DiskSpaceHealthlndicator   低磁盘空间检测
    DataSourceHealthlndicator  检测DataSource的连接是否可用
    MongoHealthlndicator       检测Mango数据库是否可用
    RabbitHealthlndicator      检测Rabbit服务器是否可用
    RedisHealthlndicator       检测Redis服务器是否可用
    SolrHealthlndicator        检测Solr服务器是否可用
/dump: 该端点用来暴露程序运行中的线程信息。它使用 java.lang.ranagerent．ThreadMXBean 的 durpAllThreads 方法来返回所有含有同步信息的活动线程详情。
/trace: 该端点用来返回基本的 HTTP 跟踪信息。 默认情况下， 跟踪信息的存储采用org.springfrarework.boo七.ac七uate.trace.InMeroryTraceRepository实现的内存方式， 始终保留最近的100条请求记录。

操作控制类（拥有更强大的控制能力，如果要使用它们的话，需要通过属性来配置开启操作）：
在原生端点中， 只提供了一个用来关闭应用的端点： /shutdown (在后续我们引入了Eureka之后， 会引入更多控制端点）。 可以通过如下配置开启它：
endpoints.shutdown.enabled=true
在配置了上述属性之后，只需要访问该应用的/shutdown端点就能实现关闭该应用的远程操作。