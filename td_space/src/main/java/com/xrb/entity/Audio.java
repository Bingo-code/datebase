package com.xrb.entity;

public class Audio {
	
	private String audioId;
	private String audioName;
	private String audioType;
	private String audioSize;
	private boolean audioShow;
	private boolean audioValid;
	private String audioTime;
	private String userId;
	
	
	public Audio() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Audio(String audioId, String audioName, String audioType, String audioSize, String audioTime, String userId) {
		super();
		this.audioId = audioId;
		this.audioName = audioName;
		this.audioType = audioType;
		this.audioSize = audioSize;
		this.audioTime = audioTime;
		this.userId = userId;
	}

	public String getAudioId() {
		return audioId;
	}
	public void setAudioId(String audioId) {
		this.audioId = audioId;
	}
	public String getAudioName() {
		return audioName;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	
	public String getAudioType() {
		return audioType;
	}
	public void setAudioType(String audioType) {
		this.audioType = audioType;
	}
	public String getAudioSize() {
		return audioSize;
	}
	public void setAudioSize(String audioSize) {
		this.audioSize = audioSize;
	}
	public boolean isAudioShow() {
		return audioShow;
	}
	public void setAudioShow(boolean audioShow) {
		this.audioShow = audioShow;
	}
	public boolean isAudioValid() {
		return audioValid;
	}
	public void setAudioValid(boolean audioValid) {
		this.audioValid = audioValid;
	}
	
	public String getAudioTime() {
		return audioTime;
	}
	public void setAudioTime(String audioTime) {
		this.audioTime = audioTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Audio [audioId=" + audioId + ", audioName=" + audioName + ", audioType=" + audioType + ", audioSize="
				+ audioSize + ", audioShow=" + audioShow + ", audioValid=" + audioValid + ", audioTime=" + audioTime
				+ ", userId=" + userId + "]";
	}
	
	
	
	
}
