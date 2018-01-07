package com.bisheng.util.QRCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.bisheng.util.constant.QRConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import sun.misc.BASE64Encoder;

/**
 * 生成二维码工具类
 * 
 * @author lihao
 */
@SuppressWarnings("restriction")
public class QRCodeManager {
	private static BASE64Encoder encoder = new sun.misc.BASE64Encoder();

	/**
	 * 生成二维码,并放入输出流中
	 * 
	 * @param contents
	 *            二维码内容
	 */
	public static String createQRCode(String contents) throws Exception {
		@SuppressWarnings("rawtypes")
		Map<EncodeHintType, Comparable> hints = new HashMap<EncodeHintType, Comparable>();
		hints.put(EncodeHintType.CHARACTER_SET, QRConstants.CHARACTER_SET);// 字符集
		// 纠错等级(L<M<Q<H),纠错能力越高,可存储的信息越少
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, QRConstants.MARGIN);// 边距
		BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, QRConstants.WIDTH,
				QRConstants.HEIGHT, hints);
		BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
		image.flush();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.flush();
		ImageIO.write(image, QRConstants.FORMAT, baos);
		String imageBase64QRCode = encoder.encodeBuffer(baos.toByteArray()).trim();

		return imageBase64QRCode;
	}
	
	/**
	 * 生成带logo的二维码，并将内容输出到二维码
	 * @param request
	 * @param contents
	 * @param presstext
	 * @throws WriterException 
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	public static String createLogoQRCode(HttpServletRequest request, String contents, String presstext) throws WriterException, IOException {
			Map<EncodeHintType, Comparable> hints = new HashMap<EncodeHintType, Comparable>();
			hints.put(EncodeHintType.CHARACTER_SET, QRConstants.CHARACTER_SET);// 字符集
			// 纠错等级(L<M<Q<H),纠错能力越高,可存储的信息越少
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			hints.put(EncodeHintType.MARGIN, QRConstants.MARGIN);// 边距
			BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, QRConstants.WIDTH,
					QRConstants.HEIGHT, hints);
			BufferedImage bim = MatrixToImageWriter.toBufferedImage(matrix);
			
			// 获取logo文件
			HttpSession session = request.getSession();
			String logopath = session.getServletContext().getRealPath("/resources/img/avatar.jpg");
			File logoPic = new File(logopath);
			//File logoPic = new File("D:/avatar.jpg");// TODO 测试代码
			
			// 读取二维码图片，并构建绘图对象
			BufferedImage image = bim;
			Graphics2D g = image.createGraphics();

			// 读取Logo图片
			BufferedImage logo = ImageIO.read(logoPic);
			// 设置logo的大小,为二维码图片的20%
			int widthLogo = logo.getWidth(null) > image.getWidth()/5 ? (image.getWidth()/5) : logo.getWidth(null),
				heightLogo = logo.getHeight(null) > image.getHeight()/5 ? (image.getHeight()/5) : logo.getWidth(null);

			// logo放在中心
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;

			// 开始绘制图片
			g.drawImage(logo, x, y, widthLogo, heightLogo, null);
			g.dispose();

			if (StringUtils.isNotBlank(presstext)) {
				// 新的图片，把带logo的二维码下面加上文字
				BufferedImage outImage = new BufferedImage(QRConstants.WIDTH, QRConstants.HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D outg = outImage.createGraphics();
				// 画二维码到新的面板
				outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
				// 画文字到新的面板
				outg.setColor(Color.BLACK);
				outg.setFont(new Font("黑体", Font.BOLD, 30)); // 字体、字型、字号
				// TODO 此处的宽高是硬编码,需注意
				outg.drawString(presstext, 32, image.getHeight() - 6); // 画文字
				outg.dispose();
				outImage.flush();
				image = outImage;
			}
			logo.flush();
			image.flush();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.flush();
			ImageIO.write(image, "png", baos);

			// 二维码生成的路径
			//ImageIO.write(image, "png", new File("D:TDC-" + new Date().getTime() + "test.png"));

			String imageBase64QRCode = encoder.encodeBuffer(baos.toByteArray()).trim();
			baos.close();
			return imageBase64QRCode;
	}
	
	public static void main(String[] args) {
		try {
			createLogoQRCode(null,"开心就好","31010000001001");
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
