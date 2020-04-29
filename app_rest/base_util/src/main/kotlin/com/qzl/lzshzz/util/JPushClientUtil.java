package com.qzl.lzshzz.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.qzl.lzshzz.util.setting.SettingConfig;

import java.util.Collection;

/**
 * @author 强周亮
 * @desc 极光推送实现类
 * @email 2538096489@qq.com
 * @time 2019/11/6 16:00
 * @class JPushClientUtil
 * @package com.qzl.lzshzz.util
 */
public class JPushClientUtil {
    //在极光注册上传应用的 appKey 和 masterSecret
    private static final String appKey = "1853b7c5866c1a0ac16e2d31";//必填，例如466f7032ac604e02fb7bda89
    private static final String masterSecret = "5cd5ba93798ea54df5f12a59";//必填，每个应用都对应一个masterSecret
    private static JPushClient jpush = null;
    /**
     * 上线时要修改的
     */
    private static boolean isOnline = SettingConfig.isOnLineJpush;

    /*
     * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
     * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
     * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒
     */
    public static void pushOffLineToApp(String deviceId) throws Exception {

        jpush = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_android_alias_alert(deviceId);
        try {
            PushResult result = jpush.sendPush(payload);
            System.out.println("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            e.printStackTrace();

        } catch (APIRequestException e) {
            //e.printStackTrace();
        }
    }

    public static PushPayload buildPushObject_android_alias_alert(String deviceId) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                //.setAudience(Audience.all())8a92073264309710016430bbb1490005
                .setAudience(Audience.alias(deviceId))//推送目标，单一推送
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .addExtra("msg", "offline")
                                        .addExtra("isRelease", isOnline + "")
                                        .setAlert("")
                                        //alert设置空字符串时，android端就是静默推送，通知栏不显示。
                                        .build()
                        )
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .addExtra("msg", "offline")
                                        .setAlert("")
                                        .build()
                        ).build()
                ).setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .build())

                .build();
    }

    public static String pushToApp(String userId, String url, String name, String recordId) throws Exception {

        jpush = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_android_alias_alert(userId, url, name, recordId);

        PushResult result = jpush.sendPush(payload);
        System.out.println("" + result);
        return "" + result;
    }

    public static PushPayload buildPushObject_android_alias_alert(String userId, String channel, String name, String recordId) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                //推送目标，单一推送
                .setAudience(Audience.alias(userId))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .addExtra("channel", channel)
                                        .addExtra("name", name)
                                        .addExtra("recordId", recordId)
                                        .addExtra("time", System.currentTimeMillis())
                                        .addExtra("msg", "video")
                                        .addExtra("isRelease", isOnline + "")
                                        .setAlert("")
                                        //alert设置空字符串时，android端就是静默推送，通知栏不显示。
                                        .build()
                        )
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .addExtra("channel", channel)
                                        .addExtra("name", name)
                                        .addExtra("recordId", recordId)
                                        .addExtra("time", System.currentTimeMillis())
                                        .addExtra("msg", "video")
                                        .addExtra("isRelease", isOnline + "")
                                        .setAlert(name + "邀请你视频聊天")
                                        .setSound("ring.mp3")

                                        .build()

                        ).build()

                ).setOptions(Options.newBuilder()
                        .setApnsProduction(isOnline)
                        .build())
                .build();
    }

    /**
     * @return void    返回类型
     * @throws
     * @Title: pushEventNoticeToApp
     * @Description: 发送事件通知推送
     * @author 强周亮
     * @date 2019年1月16日 上午11:37:31
     */
    public static String pushEventNoticeToApp(Collection<String> aliases, String content, String type) {
        jpush = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        PushPayload payload;
        if (aliases == null || aliases.isEmpty()) {
            payload = buildPushAll(content, type);
        } else {
            payload = buildPushEventExtras(aliases, content, type);
        }
        try {
            PushResult result = jpush.sendPush(payload);
            System.out.println("Got result - " + result);
            return "" + result;
        } catch (APIConnectionException e) {
            e.printStackTrace();
            return "";
        } catch (APIRequestException e) {
            return "";
        }
    }

    /**
     * @param @param aliases 通知别名
     * @param @param content 通知内容
     * @param @param type  通知类型
     * @throws
     * @Description: 发送通知推送
     * @author 强周亮
     * @date 2019年1月17日 上午10:07:37
     */
    public static PushPayload buildPushEventExtras(Collection<String> aliases, String content, String type) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                //.setAudience(Audience.all())8a92073264309710016430bbb1490005
                .setAudience(Audience.newBuilder()
                        //.addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias(aliases)).build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .addExtra("msg", type)
                                        .addExtra("content", content)
                                        .addExtra("isRelease", isOnline + "")
                                        .setAlert("")
                                        //alert设置空字符串时，android端就是静默推送，通知栏不显示。
                                        .build()
                        )
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .addExtra("msg", type)
                                        .addExtra("content", content)
                                        .addExtra("isRelease", isOnline + "")
                                        .setAlert("")
                                        .build()
                        ).build()
                ).setOptions(Options.newBuilder()
                        .setApnsProduction(isOnline)
                        .build())
                .build();
    }

    /**
     * @param @param aliases 通知别名
     * @param @param content 通知内容
     * @param @param type  通知类型
     * @throws
     * @Description: 发送通知推送
     * @author 强周亮
     * @date 2019年1月17日 上午10:07:37
     */
    public static PushPayload buildPushAll(String content, String type) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .addExtra("msg", type)
                                        .addExtra("content", content)
                                        .addExtra("isRelease", isOnline + "")
                                        .setAlert("")
                                        //alert设置空字符串时，android端就是静默推送，通知栏不显示。
                                        .build()
                        )
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .addExtra("msg", type)
                                        .addExtra("content", content)
                                        .addExtra("isRelease", isOnline + "")
                                        .setAlert("")
                                        .build()
                        ).build()
                ).setOptions(Options.newBuilder()
                        .setApnsProduction(isOnline)
                        .build())
                .build();
    }

    public static void main(String args[]) {
        try {
            //0ff096eb4a9135e171910d1395b6c293
            pushOffLineToApp("0ff096eb4a9135e171910d1395b6c293");
//			pushOffLineToApp("2520ac57df5d115cbcdd7759957e441f");
//    		Collection<String> aliases = new ArrayList<>();
//    		aliases.add("0ff096eb4a9135e171910d1395b6c293");
//    		aliases.add("2520ac57df5d115cbcdd7759957e441f");
//			pushEventNoticeToApp(aliases,"您有XXX事件待处理","eventNotice");
            System.out.println(isOnline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
