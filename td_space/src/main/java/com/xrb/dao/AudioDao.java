package com.xrb.dao;

import java.util.List;

import com.xrb.entity.Audio;

public interface AudioDao {
	
	public List<Audio> findAudiosIsValid(String userId);
	
	public List<Audio> findAudiosIsNotValid(String userId);
	
	public List<Audio> findAudiosIsShow(String userId);
	
	public Audio findAudioById(String audioId);
	
	public Integer addAudio(Audio audio);
	
	public Integer updateAudio(Audio audio);
	
	public Integer renameAudio(Audio audio);
	
	public Integer reAudioShow(Audio audio);
	
	public Integer deleteAudio(String audioId);
	
}
