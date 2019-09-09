# MannaComponent
Android组件化开发框架，基于开源的WMRouter路由、LiveEventBus事件总线、Jetpack系列的MVVM组件集成开发


## 组件化基础框架
1. WMRouter提供基础的路由+ServiceLoader两大组件通信功能
2. LiveEventBus提供跨module、跨进程的消息通信


## 组件化整体架构
1. App主工程(壳工程)：集成所有组件功能，无具体的业务逻辑，只提供组件注入接口
2. 顶层业务组件：由项目中不同的业务功能组成，对应于具体的module划分
3. 基础功能组件：对上层业务提供相应的功能服务，常见的登录注册、module注入、分享、推送等
4. 底层服务组件：提供基础支撑，常见的网络请求、路由通信、日志回捞等


    整体架构图如下：
![Image](https://github.com/MannaYang/MannaComponent/blob/master/snapshot/ic_component.png)

## 业务组件拆分
每个团队对业务组件的拆分都有自身的考虑，但是也需要遵守一些规则
1. 避免循环依赖，不能module A依赖module B，而后module B反向依赖module A
2. 业务功能职责划分清晰，不能简单的将一个功能模块单独放入一个module，对应接口、UI、数据可做细分
3. 组件划分使用上，功能单一且可复用，命名规范，对外提供下沉接口实现
4. 其它自己团队的一些方式...

## 组件化开发规范
1. 统一第三方版本管理，集中管控引入项目的第三方库，避免造成版本冲突
2. 统一module资源命名，可通过gradle中加入resourcePrefix，统一资源前缀，规范资源引用
3. module对应的使用文档，包含组件的功能、历史版本记录、对外提供哪些接口访问等
4. 其它自己团队的一些规范...

## 关于该项目的使用
1. 本项目由公司组件化项目中剥离而出，删除了公司项目的相关代码，需要clone下来自行改造，同时也是方便功能扩展
2. 项目中的mvvm使用没有加入DataBinding，在公司项目中也仅仅是在某个module下进行使用，需要自行根据项目需要验证该技术框架
3. 项目中的Rx系列仅保留了版本管控，在公司项目中也仅仅是在某个module下进行使用，可根据实际业务在对应module的ApiService接口中进行扩展
4. 其它依赖库根据实际业务添加...


## 组件化之路
关于组件化已经有很多前辈、大公司在前方探路数年，每个团队也都有符合自身业务的组件化框架，我们要做的更多是在
深入理解业务的基础上做出相对适合的、能够持续交付的、可动态扩展的业务功能和架构，从而满足项目中日益变化的需求...


## 感谢开源
1. MVVM  -- Google开源的一套Jetpack组件
1. WMRouter(https://github.com/meituan/WMRouter)
2. LiveEventBus(https://github.com/JeremyLiao/LiveEventBus)
3. MVVMHabit(https://github.com/goldze/MVVMHabit)
4. 项目中涉及引用的第三方开源库(retrofit、okhttp等)


## 我的个人新球

  免费加入星球一起讨论项目、研究新技术,共同成长!
  
![image](https://github.com/MannaYang/AudioVideoCodec/blob/master/screenshot/xiaomiquan.png)
