package com.example.demo.file.exception;

import com.example.demo.exception.BaseException;

public class ImageException extends BaseException {
    private static final long serialVersionUID = -1245278309306401290L;

	protected ImageException(String code) {
        super("image."+code);
    }

    public  static ImageException notDetermineFileType()  {
        return new ImageException("not.determine.fileType");
    }

}
