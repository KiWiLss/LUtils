# LUtils
Android tools
## 一、导入
    allprojects {
        repositories {
            ...
            maven { url 'https://www.jitpack.io' }
        }
    }
dependencies {
            implementation 'com.github.KiWiLss:LUtils:0.0.2'
    }
使用前要调用，获取到 Context：
object LUtilsConfig{
    lateinit var mContext: Context
    fun init(context: Context) {
        mContext = context
    }
二、包含内容
2.1 TextView 相关工具类
2.1.1 SsbUtils
TextView 富文本工具集
2.2 资源相关工具类
2.2.1 安卓资源相关工具类
包含：ResUtils本地资源获取工具类
   KeyboardUtils键盘相关工具类
   DensityUtils屏幕密度工具类
2.3 数字相关
数字相关工具类
2.4 日期相关
日期相关工具类
