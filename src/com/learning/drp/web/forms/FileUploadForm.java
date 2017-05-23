package com.learning.drp.web.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileUploadForm extends ActionForm  {

	private FormFile file;

	public FormFile getfile() {
		return file;
	}

	public void setfile(FormFile file) {
		this.file = file;
	}
}
