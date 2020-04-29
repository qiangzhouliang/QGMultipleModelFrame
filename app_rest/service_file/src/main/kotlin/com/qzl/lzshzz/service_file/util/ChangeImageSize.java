package com.qzl.lzshzz.service_file.util;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @ClassName: ChangeImageSize
 * @Description:  (图片处理-缩略图处理)  
 * @author hanjp  
 * @date 2018年7月10日 下午7:10:05 
 */
public class ChangeImageSize {
	/**
	 * @Title: scale
	 * @Description:  (缩放图像) 
	 * @author hanjp   
	 * @param @param srcImageFile	源图像文件二进制流
	 * @param @param result			缩放后的图像地址
	 * @param @param scale			缩放比例
	 * @param @param flag			缩放选择:true 放大; false 缩小
	 * @param @return    参数  
	 * @return byte[]    返回类型
	 * @date 2018年7月10日 下午8:06:42  
	 * @throws
	 */
	
	public static byte[] scale(byte[] srcImageFile, String result, int scale, boolean flag)
    {   
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
        	BufferedImage src= ImageIO.read(new ByteArrayInputStream(srcImageFile));
        	// 得到源图宽
            int width = src.getWidth(); 
            // 得到源图长
            int height = src.getHeight(); 
            if (flag)
            {
                // 放大
                width = width * scale;
                height = height * scale;
            }
            else
            {
                // 缩小
            	int  size = 150;
            	if(width>=size){
            		height = height/(width/size);
                    width = size;
            	}
            }
           Image image =src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
           BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
           Graphics g = tag.getGraphics();
           // 绘制缩小后的图
           g.drawImage(image, 0, 0, null);
           g.dispose();
           // 输出到文件流
           ImageIO.write(tag, "JPEG", bos);
            //blob = new SerialBlob(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}

           return  bos.toByteArray();
    }
	
	/**
	 * 
	 * @Title: scale  
	 * @Description:  (缩放图像) 
	 * @author hanjp   
	 * @param @param srcImageFile	源图像文件地址
	 * @param @param result			缩放后的图像地址
	 * @param @param scale			缩放比例
	 * @param @param flag			缩放选择:true 放大; false 缩小;
	 * @return void    返回类型
	 * @date 2018年7月10日 下午8:05:34  
	 * @throws
	 */
	
    public static void scale(String srcImageFile, String result, int scale, boolean flag)
    {
        try
       {
        	// 读入文件
           BufferedImage src = ImageIO.read(new File(srcImageFile));
            // 得到源图宽
            int width = src.getWidth(); 
            // 得到源图长
            int height = src.getHeight();
            if (flag)
            {
                // 放大
                width = width * scale;
                height = height * scale;
            }
            else
            {
                // 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null); 
            g.dispose();
            // 输出到文件流
            ImageIO.write(tag, "JPG", new File(result));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
	/**
	 * 
	 * @Title: compressPicByQuality  
	 * @Description:  (压缩图片,通过压缩图片质量，保持原图大小) 
	 * @author hanjp   
	 * @param @param imgByte
	 * @param @param quality  0-1之间的数字
	 * @param @return    参数  
	 * @return byte[]    返回类型
	 * @date 2018年7月10日 下午8:04:39  
	 * @throws
	 */
    
	public static byte[] compressPicByQuality(byte[] imgByte, float quality) {
		byte[] inByte = null;
		try {
			ByteArrayInputStream byteInput = new ByteArrayInputStream(imgByte);
			BufferedImage image = ImageIO.read(byteInput);

			// 如果图片空，返回空
			if (image == null) {
				return null;
			}
			// 得到指定Format图片的writer
			// 得到迭代器
			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
			// 得到writer
			ImageWriter writer = (ImageWriter) iter.next();

			// 得到指定writer的输出参数设置(ImageWriteParam )
			ImageWriteParam iwp = writer.getDefaultWriteParam();
			// 设置可否压缩
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			// 设置压缩质量参数
			iwp.setCompressionQuality(quality); 

			iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

			ColorModel colorModel = ColorModel.getRGBdefault();
			// 指定压缩时使用的色彩模式
			iwp.setDestinationType(
					new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

			// 开始打包图片，写入byte[]
			// 取得内存输出流
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			IIOImage iIamge = new IIOImage(image, null, null);

			// 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
			// 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
			writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
			writer.write(null, iIamge, iwp);
			inByte = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			System.out.println("write errro");
			e.printStackTrace();
		}
		return inByte;
	}
	public static byte[] compressPic(byte[] imageByte, int width, int height, boolean gp) {
		byte[] inByte = null;
		try {
			ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);
			Image img = ImageIO.read(byteInput);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				return inByte;
			} else {
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (gp == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					// 输出的图片宽度
					newWidth = width; 
					// 输出的图片高度
					newHeight = height; 
				}
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

				ImageWriter imgWrier;
				ImageWriteParam imgWriteParams;
				// 指定写图片的方式为 jpg
				imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
				imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
				// // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
				// imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
				// // 这里指定压缩的程度，参数qality是取值0~1范围内，
				// imgWriteParams.setCompressionQuality((float)45217/imageByte.length);
				//
				// imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
				// ColorModel colorModel = ColorModel.getRGBdefault();
				// // 指定压缩时使用的色彩模式
				// imgWriteParams.setDestinationType(new
				// javax.imageio.ImageTypeSpecifier(colorModel, colorModel
				// .createCompatibleSampleModel(100, 100)));
				// 将压缩后的图片返回字节流
				ByteArrayOutputStream out = new ByteArrayOutputStream(imageByte.length);
				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(tag, null, null), imgWriteParams);
				out.flush();
				out.close();
				byteInput.close();
				inByte = out.toByteArray();

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return inByte;
	}
}