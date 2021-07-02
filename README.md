# LUtils
Android tools ,如果觉得有帮助, 喜欢的话希望给个 Star 或 Fork ^_^ ，谢谢

​
## 一、导入
```java
    allprojects {
        repositories {
            ...
            maven { url 'https://www.jitpack.io' }
        }
    }
​
​
dependencies {
            implementation 'com.github.KiWiLss:LUtils:1.1.0'
    }
```
​
最新版:
​

 ![](https://www.jitpack.io/v/KiWiLss/LUtils.svg)

使用前要调用，获取到 Context, 可以在 Application 里调用：
```
object LUtilsConfig{
​
    lateinit var mContext: Context
    fun init(context: Context) {
        mContext = context
    }
```
## 二、包含内容
### 2.1 TextView 相关工具类
##### 2.1.1 SsbUtils
[TextView 富文本工具集](https://www.yuque.com/bibly/selhsz/vu6f52)


### 2.2 资源相关工具类
#### 2.2.1 [安卓资源相关工具类](https://www.yuque.com/bibly/selhsz/txmw31)
包含：ResUtils本地资源获取工具类
   KeyboardUtils键盘相关工具类
   DensityUtils_屏幕密度工具类_
### 2.3 数字相关
[数字相关工具类](https://www.yuque.com/bibly/selhsz/ncde4q)

[随机数相关](https://www.yuque.com/bibly/selhsz/fqq76l)
### 2.4 日期相关
[日期相关工具类](https://www.yuque.com/bibly/selhsz/odrol4)

### 2.5 路由跳转相关
[使用 Fragment 处理 onActivityResult](https://www.yuque.com/bibly/selhsz/dzzsyk#Sr423)

### 2.6 json 解析相关
[Gson解析封装](https://www.yuque.com/bibly/selhsz/gxumid)

### 2.7 数据库相关
[SharedPreferences封装](https://www.yuque.com/bibly/selhsz/sd2w3g)

### 2.8 字符相关
[CharSequence 相关扩展收集](https://www.yuque.com/bibly/selhsz/rkn7so)