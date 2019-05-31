#Specifies the number of optimization passes to be performed
-optimizationpasses 0

#Specifies not to generate mixed-case class names while obfuscating. By default, obfuscated class names can contain a mix of upper-case characters and lower-case characters
-dontusemixedcaseclassnames

#Specifies not to ignore non-public library classes. As of version 4.5, this is the default setting.
-dontskipnonpubliclibraryclasses

#Specifies not to preverify the processed class files. By default, class files are preverified if they are targeted at Java Micro Edition or at Java 6 or higher
-dontpreverify

#Specifies to print any warnings about unresolved references and other important problems, but to continue processing in any case
-ignorewarnings

#Specifies not to ignore package visible library class members (fields and methods).
-dontskipnonpubliclibraryclassmembers

-optimizations !code/simplification/arithmetic,!class/merging/*,!code/allocation/variable,!class/unboxing/enum

-printusage usage.txt
-printmapping mapping.txt
-printseeds seeds.txt

-keepattributes *Annotation*,SourceFile,LineNumberTable,InnerClasses
-keepattributes *JavascriptInterface*

#members of public
-keepclasseswithmembers class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {*;}

-keep class * implements java.io.Serializable {*;}

-keep class * implements CharSequence {*;}

#class kept by android and java
-keep public class * extends android.app.Activity
-keep public class * extends android.view.ViewGroup
-keep public class * extends android.app.ActivityGroup{
    *;
}
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference


-keep class android.content.ServiceConnection

-keep public class * extends android.view.** {
    *;
}
-keep public class * implements android.view.** {
    *;
}

-keep public class * implements java.util.Observer {
    *;
}
-keep public class * extends android.widget.**{
   *;
}

-keep public class * implements android.widget.** {
    *;
}

-keep class * extends android.content.ServiceConnection {
    *;
}


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature


# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.**{*;}

-keep class android.support.v4.**{*;}


-keep class org.apache.** {*;}
-dontwarn org.apache.**

#WebChromeClient openFileChooser
-keepclassmembers class * extends android.webkit.WebChromeClient{
   public void openFileChooser(...);
}


# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**

# glide 的混淆代码
#com.bumptech.glide
-keep class com.bumptech.glide.** {*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$**{
  **[] $VALUES;
  public *;
}

-keep class kotlin.** { *; }
-keep class org.jetbrains.** { *; }

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}
#testin sdk
-keep class cn.testin.analysis.** {*;}
-keep class android.support.v4.view.** {*;}
-keep class android.support.v7.widget.** {*;}

#arouter 混淆配置 start
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
# -keep class * implements com.alibaba.android.arouter.facade.template.IProvider
#arouter 混淆配置 end

# 去除log
-assumenosideeffects class android.util.Log {
   public static *** d(...);
   public static *** v(...);
   public static *** println(...);
}

-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** v(...);
    public static *** println(...);
}

-assumenosideeffects class android.util.Log {
    public static *** i(...);
    public static *** v(...);
    public static *** println(...);
}

-assumenosideeffects class android.util.Log {
    public static *** w(...);
    public static *** v(...);
    public static *** println(...);
}
