package com.shangde.edu.dis.web.action;

import java.io.IOException;


import com.shangde.common.action.CommonAction;
import com.shangde.edu.dis.utils.FileUtil;

/**
 * 文件上传处理,暂时不用,图片上传组建，已单独项目运行
 * 
 * @author Libg
 * 
 */
public class FileUploadAction extends CommonAction {
	
	private String path;

	/**
	 * 上传帖子图片
	 * 
	 * @throws IOException
	 */
	public void img() throws IOException {

		String savePath = (String) this.getSession("kindeditorSavePath");
		if (savePath == null || savePath == "") {
			savePath = "dis/temp";
		}

		String str = FileUtil.kindeditorUpload(savePath, getServletRequest(),
				getServletResponse());

		this.getOut().println(str);

	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
