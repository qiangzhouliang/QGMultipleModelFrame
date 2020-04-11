# 一 整体框架结构
# 如何使用
直接可以把整个工程直接作为一个开发框架，直接在里面开发，如果想用里面的lib库，可以先将项目跑起来，
然后根据效果使用,建议下载源代码使用
Step 1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.qiangzhouliang:MyApplication:V1.0.1'
	}
```
## 1.1 APP 模块
负责整个APP的功能模块的调度，加载，装配的功能，也是主功能
## 1.2 base_common 模块
&ensp;&ensp;&ensp;&ensp;主要是一些公共的内容，可以提供给各大功能模块都可以使用基础功能实现类，如图片加载，文件下载，动态权限获取，网络请求等基础功能模块  
&ensp;&ensp;&ensp;&ensp;包含了公共UI
## 1.3 base_tools 模块
&ensp;&ensp;&ensp;&ensp;该模块当中主要放的一些工具类，一些第三方的引用都放在此模块下
## 1.4 功能模块
主要是引入的一些可以作为单独模块开发的功能模块
# 二 功能模块
## 2.1 视频播放模块videoplayer
该模块主要负责视频播放的公共module，可以单独开发，可以继承作为所有视频播放的功能模块来开发，调用方法是通过aRouter路由的方式调用  
- [ ] 例如
```
ARouter.getInstance().build(ARouterPath.VIDEO_PLAYER)
                .withString("videoTitle", "首页跳转")
                .navigation()

```
再被调用的地方activity上写上注解@Route(path = XXX)
- [ ] 例如
```
@Route(path = ARouterPath.VIDEO_PLAYER)
class JiaoZiVideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }
}
```
## 2.2 运行时权限和获取
用到的是 https://github.com/lovedise/PermissionGen
## 2.3 添加友盟统计功能
实现APP用户量的监控以及APP错误数据的手机，以便于我们后天排查错误和收集错误，使用时记得修改自己的友盟key
## 2.4 添加文件上传下载功能
实现了文件的上传下载
## 2.5 引入了图表统计模块
可以实现漂亮的图表统计功能
