package com.shangde.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageHelper {
	public static int CUS_PHOTO_WIDTH = 120;
	public static int CUS_PHOTO_HEIGHT = 120;

	private static BufferedImage makeThumbnail(Image img, int width, int height) {
		BufferedImage tag = new BufferedImage(width, height, 1);
		Graphics g = tag.getGraphics();
		g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, null);
		g.dispose();
		return tag;
	}

	private static void saveSubImage(BufferedImage image,
			Rectangle subImageBounds, File subImageFile, int left, int top)
			throws IOException {

		String fileName = subImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf(46) + 1);
		BufferedImage subImage = new BufferedImage(ImageHelper.CUS_PHOTO_WIDTH,
				ImageHelper.CUS_PHOTO_WIDTH, 1);
		Graphics g = subImage.getGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, CUS_PHOTO_WIDTH, CUS_PHOTO_WIDTH);
		g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y,
				subImageBounds.width, subImageBounds.height), left, top, null);
		// if ((subImageBounds.width > image.getWidth()) ||
		// (subImageBounds.height > image.getHeight())) {
		// int left = subImageBounds.x;
		// int top = subImageBounds.y;
		// if (image.getWidth() < subImageBounds.width)
		// left = (subImageBounds.width - image.getWidth()) / 2;
		// if (image.getHeight() < subImageBounds.height)
		// top = (subImageBounds.height - image.getHeight()) / 2;
		// g.setColor(Color.white);
		// g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);
		// g.drawImage(image, left, top, null);
		// ImageIO.write(image, formatName, subImageFile);
		// System.out.println("if is running left:" + left + " top: " + top);
		// }
		// else {
		// g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y,
		// subImageBounds.width, subImageBounds.height), 0, 0, null);
		// System.out.println("else is running");
		// }
		g.dispose();
    	File file = new File(subImageFile.getPath());
    	if(!file.exists()) {
    		file.mkdirs();
    	}
		ImageIO.write(subImage, formatName, subImageFile);
	}

	public static void cut(String srcImageFile, String descDir, Rectangle rect,
			int[] intParms) throws IOException {
		Image image = ImageIO.read(new File(srcImageFile));
		BufferedImage bImage = makeThumbnail(image, intParms[0], intParms[1]);

		saveSubImage(bImage, rect, new File(descDir),
				intParms[2] < 0 ? -intParms[2] : 0,
				intParms[3] < 0 ? -intParms[3] : 0);
	}

	public static void cut(File srcImageFile, File descDir, Rectangle rect,
			int[] intParms) throws IOException {
		Image image = ImageIO.read(srcImageFile);
		BufferedImage bImage = makeThumbnail(image, intParms[0], intParms[1]);

		saveSubImage(bImage, rect, descDir, intParms[2] < 0 ? -intParms[2] : 0,
				intParms[3] < 0 ? -intParms[3] : 0);
	}
}