package com.xrb.entity;

public class Image {
	
	private String imageId;
	private String imageName;
	private boolean imageShow;
	private boolean imageValid;
	private String imageType;
	private String imageSize;
	private String imageTime;
	private String userId;
	
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Image(String imageId, String imageName, String imageType, String imageSize, String imageTime,
			String userId) {
		super();
		this.imageId = imageId;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageSize = imageSize;
		this.imageTime = imageTime;
		this.userId = userId;
	}


	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public boolean isImageShow() {
		return imageShow;
	}
	public void setImageShow(boolean imageShow) {
		this.imageShow = imageShow;
	}
	public boolean isImageValid() {
		return imageValid;
	}
	public void setImageValid(boolean imageValid) {
		this.imageValid = imageValid;
	}
	
	public String getImageType() {
		return imageType;
	}


	public void setImageType(String imageType) {
		this.imageType = imageType;
	}


	public String getImageSize() {
		return imageSize;
	}


	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}


	public String getImageTime() {
		return imageTime;
	}


	public void setImageTime(String imageTime) {
		this.imageTime = imageTime;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imageName=" + imageName + ", imageShow=" + imageShow + ", imageValid="
				+ imageValid + ", imageType=" + imageType + ", imageSize=" + imageSize + ", imageTime=" + imageTime
				+ ", userId=" + userId + "]";
	}

	
	
	
	
}
