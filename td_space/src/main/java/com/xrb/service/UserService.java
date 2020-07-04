package com.xrb.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xrb.entity.Audio;
import com.xrb.entity.Image;
import com.xrb.entity.User;

public interface UserService {
	//用户
	
	public User queryUserByName(String userName);
    
    public Integer addUser(User user);
    
    public Integer deleteUser(String userId);
    
    public Integer updateUser(User user);
	   
 	public List<User> queryAllUsers();
	
	//音频
	
	public List<Audio> findAudiosIsValid(String userId);
	
	public List<Audio> findAudiosIsNotValid(String userId);
	
	public List<Audio> findAudiosIsShow(String userId);
	
	public Audio findAudioById(String audioId);
	
	public Integer addAudio(Audio audio);
	
	public Integer updateAudio(Audio audio);
	
	public Integer renameAudio(Audio audio);
	
	public Integer reAudioShow(Audio audio);
	
	public Integer deleteAudio(String audioId);
	
	//相片
	
	public List<Image> findImagesIsValid(String userId);
	
	public List<Image> findImagesIsNotValid(String userId);
	
	public List<Image> findImagesIsShow(String userId);
	
	public Image findImageById(String imageId);
	
	public List<Image> queryImages_18(RowBounds rowBounds, String userId);
	
	public Integer addImage(Image image);
	
	public Integer updateImage(Image image);
	
	public Integer renameImage(Image image);
	
	public Integer reImageShow(Image image);
	
	public Integer deleteImage(String imageId);
	
}
