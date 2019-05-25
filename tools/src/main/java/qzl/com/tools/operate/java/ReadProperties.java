package qzl.com.tools.operate.java;


import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    private static Properties defaultProperty;
    private static Properties configProperty;
    static {
		initConfig();
    }

	private static void initConfig() {
		configProperty = new Properties();
		InputStream stream =
				ReadProperties.class.getResourceAsStream("/assets/config.properties");
		try {
			configProperty.load(stream);
		} catch (Exception e) {

		}
		configProperty.putAll(System.getProperties());
		if (getConfigPropertyByBoolean("isRelease")){
			//如果是发布版本
			init("release");
		}else {
			//如果是调试版本
			init("debug");
		}
	}

	/**
	 * @desc 配置参数的名称
	 * @author 强周亮
	 * @time 2019-01-24 11:51
	 */
	static void init(String str) {
        defaultProperty = new Properties();
        InputStream stream =
        		ReadProperties.class.getResourceAsStream("/assets/"+str+".properties");
		try {
			defaultProperty.load(stream);
		} catch (Exception e) {

		}
		defaultProperty.putAll(System.getProperties());
    }
	/**
	 * @param propertyName :对象名称
	 * @return 对象值
	 * 取得配置文件的对象值
	 */
	private static Boolean getConfigPropertyByBoolean(String propertyName) {
		return Boolean.valueOf(String.valueOf(ReadProperties.configProperty.get(propertyName)));
	}
	/**
	 * @param propertyName :对象名称
	 * @return 对象值
	 * 取得配置文件的对象值-公共config里面的属性值
	 */
	public static String getConfigPropertyByStr(String propertyName) {
		return String.valueOf(ReadProperties.configProperty.get(propertyName));
	}
    /**
	 * @param propertyName :对象名称
	 * @return 对象值
	 * 取得配置文件的对象值
	 */
	public static String getPropertyByStr(String propertyName) {
		return String.valueOf(ReadProperties.defaultProperty.get(propertyName));
	}
	/**
	 * @param propertyName :对象名称
	 * @return 对象值
	 * 取得配置文件的对象值
	 */
	public static int getPropertyByInt(String propertyName) {
		return Integer.parseInt(ReadProperties.getPropertyByStr(propertyName));
	}
   
}
