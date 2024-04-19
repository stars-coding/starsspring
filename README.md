# Stars Spring

> 作者：Stars

基于Spring框架的简易实现。


## 类图展示

- 类图一

![starsspring-1](https://github.com/stars-coding/starsspring/blob/master/image/starsspring-1.png)

- 类图二

![starsspring-2](https://github.com/stars-coding/starsspring/blob/master/image/starsspring-2.png)

- 类图三

![starsspring-3](https://github.com/stars-coding/starsspring/blob/master/image/starsspring-3.png)


## 核心实现

- IoC

&emsp;&emsp;控制反转（IoC）是一种软件设计原则，
它将应用程序中对象的创建、组装和管理的责任转移到了容器中，
从而降低了组件之间的耦合度。在传统的编程模式中，
对象的创建和依赖关系的管理通常由对象自己或者工厂类来负责，
而在 IoC 模式下，这些责任被转移到了 IoC 容器中。

&emsp;&emsp;IoC 容器是一个具有依赖注入功能的容器，
它负责管理应用程序中的对象（Bean），
并且负责将对象之间的依赖关系注入到对象中。
IoC 容器通过读取配置文件或者通过注解等方式来获取对象的配置信息，
然后根据配置信息来创建对象、管理对象的生命周期、处理对象之间的依赖关系等。

![IoC容器架构](https://github.com/stars-coding/starsspring/blob/master/image/IoC容器架构.png)

- AOP

&emsp;&emsp;AOP（面向切面编程）是一种软件设计思想，
它将程序的业务逻辑与横切关注点（Cross-Cutting Concerns）进行分离，
通过在代码中定义切面（Aspect）来实现对横切关注点的模块化和集中化管理。

- Bean生命周期

&emsp;&emsp;1. 生成 Bean 定义对象：扫描资源文件，生成 BeanDefinition 对象。

&emsp;&emsp;2. Bean 工厂扩展处理器：利用对外提供的 BeanFactoryPostProcessor 接口，修改 BeanDefinition 对象的属性。

&emsp;&emsp;&emsp;&emsp;1. 形式：@Value注解。

&emsp;&emsp;3. 实例化前置处理器：检查 BeanPostProcessor 对象是否实现 InstantiationAwareBeanPostProcessor 接口，
重写 postProcessBeforeInstantiation() 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：在方法中自定义业务逻辑，创建代理对象等。

&emsp;&emsp;4. 实例化 Bean：利用反射技术。

&emsp;&emsp;&emsp;&emsp;1. 方法：createBeanInstance() 方法。

&emsp;&emsp;5. 实例化后置处理器：检查 BeanPostProcessor 对象是否实现 InstantiationAwareBeanPostProcessor 接口，
重写 postProcessAfterInstantiation() 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：决定是否继续执行属性赋值或直接返回 Bean 对象。

&emsp;&emsp;6. 对 Bean 属性赋值前修改：检查 BeanPostProcessor 对象是否实现 InstantiationAwareBeanPostProcessor 接口，
重写 postProcessPropertyValues() 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：自定义修改属性值的业务逻辑。

&emsp;&emsp;7. Bean 属性赋值：利用反射技术。

&emsp;&emsp;&emsp;&emsp;1. 方法：applyPropertyValues() 方法。

&emsp;&emsp;8. Aware 感知标记：检查 Bean 是否实现了 BeanFactoryAware、BeanClassLoaderAware、BeanNameAware 接口，
赋予接口方法对应的能力。

&emsp;&emsp;&emsp;&emsp;1. 具体：赋予 BeanFactory、BeanClassLoader、BeanName。

&emsp;&emsp;9. 初始化前置处理器：执行外部 BeanPostProcessor 对象的 postProcessBeforeInitialization() 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：自定义初始化业务逻辑。

&emsp;&emsp;10. 初始化-重写方法：检查 Bean 是否实现了 InitializingBean 接口，重写 afterPropertiesSet() 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：自定义修改属性值等初始化业务逻辑。

&emsp;&emsp;11. 初始化-自定义方法：在 Bean 类中，自定义 init-method 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：自定义初始化业务逻辑。

&emsp;&emsp;12. 初始化后置处理器：执行外部 BeanPostProcessor 对象的 postProcessAfterInitialization() 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：自定义初始化业务逻辑。

&emsp;&emsp;13. 使用 Bean：对外返回使用 Bean。

&emsp;&emsp;&emsp;&emsp;1. 单例：放入一级缓存单例池（放进 IoC 容器中），返回供使用。

&emsp;&emsp;&emsp;&emsp;2. 非单：直接返回供使用。

&emsp;&emsp;14. 销毁 Bean-重写方法：检查 Bean 是否实现了 DisposableBean 接口，重写 destroy() 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：自定义销毁 Bean 的业务逻辑。

&emsp;&emsp;15. 销毁 Bean-自定义方法：在 Bean 类中，自定义 destroy-method 方法。

&emsp;&emsp;&emsp;&emsp;1. 内容：自定义销毁 Bean 的业务逻辑。

&emsp;&emsp;16. 销毁 Bean：销毁 Bean 对象。

&emsp;&emsp;&emsp;&emsp;1. 彻底销毁 Bean。

- 三级缓存

&emsp;&emsp;由于 IoC 容器的存在，需对外提供获取 Bean 的一致化接口，
需要一个只存成熟 Bean 的容器（拿来 Bean 可以直接用，成熟的）。
将成熟 Bean 与 未成熟 Bean 进行分离。

&emsp;&emsp;场景一：只用一个缓存存放 Bean，存放成熟的 Bean。（系统中存在两种 Bean：成熟 Bean，不成熟 Bean）

![场景一](https://github.com/stars-coding/starsspring/blob/master/image/场景一.png)

&emsp;&emsp;分析一：优点：对外统一获取成熟的 Bean，拿来直接用。缺点：存在循环依赖。

&emsp;&emsp;场景二：需要解决循环依赖。用两个缓存存放 Bean，分开存放成熟的 Bean 和不成熟的 Bean。
（系统中存在两种 Bean：成熟 Bean，不成熟 Bean）

![场景二-1](https://github.com/stars-coding/starsspring/blob/master/image/场景二-1.png)

![场景二-2](https://github.com/stars-coding/starsspring/blob/master/image/场景二-2.png)

&emsp;&emsp;分析二：优点：解决了循环依赖。缺点：系统可能存在 AOP 场景，无法满足。

&emsp;&emsp;场景三：需要满足 AOP 场景。

![场景三](https://github.com/stars-coding/starsspring/blob/master/image/场景三.png)

&emsp;&emsp;分析三：优点：满足 AOP 场景。
缺点：循环依赖和 AOP 导致赋值的 Bean 和单例池中的 Bean 不一致
（属性赋值的最终结果一定是一个成熟的 Bean，也就是属性对应的 Bean 一定要走完整个生命周期。
但是对于循环依赖 + AOP 场景，这里存在单例池中的 Bean 与属性赋值最终的 Bean 存在不一致问题。
我们想要的结果是赋值的 Bean 要和单例池中的 Bean 保持一致）。

![分析三](https://github.com/stars-coding/starsspring/blob/master/image/分析三.png)

&emsp;&emsp;场景四：解决循环依赖和 AOP 同时存在导致属性赋值的 Bean 和单例池中的 Bean 不一致问题。

![场景四-1](https://github.com/stars-coding/starsspring/blob/master/image/场景四-1.png)

![场景四-2](https://github.com/stars-coding/starsspring/blob/master/image/场景四-2.png)

&emsp;&emsp;分析四：优点：解决循环依赖和 AOP 同时存在导致属性赋值的 Bean 和单例池中的 Bean 不一致问题；
既满足单纯循环依赖存在的场景，又可以满足单纯 AOP 存在的场景，还可以满足二者同时存在的场景。
缺点：如果存在交叉循环依赖（A-->B, A-->C, B-->A, C-->A），是否可能存在重复生产 Bean 呢？

&emsp;&emsp;场景五：如果存在交叉循环依赖（A-->B, A-->C, B-->A, C-->A），是否可能存在重复生产 Bean 的情况。

![场景五-1](https://github.com/stars-coding/starsspring/blob/master/image/场景五-1.png)

![场景五-2](https://github.com/stars-coding/starsspring/blob/master/image/场景五-2.png)

&emsp;&emsp;分析五：优点：如果存在交叉循环依赖（A-->B, A-->C, B-->A, C-->A），
不存在重复生产 Bean 的情况（底层逻辑：二级缓存早期单例池保证了未成熟 Bean 的单例）。
缺点：提前 AOP 时，是需要原始 Bean 参与的。但提前 AOP 步骤中貌似没有考虑到原始 Bean 的获取途径，存在流程断链。

![分析五](https://github.com/stars-coding/starsspring/blob/master/image/分析五.png)

&emsp;&emsp;场景六：解决提前 AOP 时，对原始 Bean 的获取问题。引入三级缓存，
存储原始 Bean 对象等信息，为提前 AOP 提供便利。

![场景六-1](https://github.com/stars-coding/starsspring/blob/master/image/场景六-1.png)

![场景六-2](https://github.com/stars-coding/starsspring/blob/master/image/场景六-2.png)

&emsp;&emsp;分析六：分析五中提到，在提前 AOP 环节，对于原始 Bean 获取不明确，
也可能存在流程断链。所以，引入三级缓存，解决了这个可能存在的断链。
但是，三级缓存功能不仅仅如此，试想一下，如果没有这个三级缓存存储原始 Bean 信息，
那么这里将会重新创建 Bean 来获取是否需要 AOP。对于重新创建 Bean，
这就又是一个循环依赖场景，可见，三级缓存真正的作用在于打破循环。对于出现循环依赖并提前 AOP 时，
原始 Bean 的初始化阶段不应该再次执行 AOP 了。所以，这个系统中目前存在重复 AOP 场景。优点：打破循环。缺点：重复 AOP。

![分析六](https://github.com/stars-coding/starsspring/blob/master/image/分析六.png)

&emsp;&emsp;场景七：解决重复 AOP 问题。引入早期代理缓存，标记是否已经提前 AOP。

![场景七](https://github.com/stars-coding/starsspring/blob/master/image/场景七.png)

&emsp;&emsp;分析七：利用早期代理缓存进行是否提前 AOP 的标记缓存，解决了重复进行 AOP 的问题。优点：解决重复 AOP 问题。
