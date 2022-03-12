package com.sahandilshan.smartlearn;

public class Mat_info {
    String filename, fileurl;

    public Mat_info() {
    }

    @Override
    public String toString() {
        return "Mat_info{" +
                "filename='" + filename + '\'' +
                ", fileurl='" + fileurl + '\'' +
                '}';
    }

    public Mat_info(String filename, String fileurl) {
        this.filename = filename;
        this.fileurl = fileurl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
