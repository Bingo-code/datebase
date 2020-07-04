package com.xrb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.xrb.dao.AudioDao;
import com.xrb.dao.ImageDao;
import com.xrb.dao.UserDao;
import com.xrb.entity.Audio;
import com.xrb.entity.Image;
import com.xrb.entity.User;
import com.xrb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private AudioDao audioDao;
	
	@Resource
	private ImageDao imageDao;
	
	@Resource
	private UserDao userDao;
	
	@Override
	public List<Audio> findAudiosIsValid(String userId) {
		return audioDao.findAudiosIsValid(userId);
	}

	@Override
	public List<Audio> findAudiosIsNotValid(String userId) {
		return audioDao.findAudiosIsNotValid(userId);
	}

	@Override
	public List<Audio> findAudiosIsShow(String userId) {
		return audioDao.findAudiosIsShow(userId);
	}

	@Override
	public Integer addAudio(Audio audio) {
		return audioDao.addAudio(audio);
	}

	@Override
	public Integer updateAudio(Audio audio) {
		return audioDao.updateAudio(audio);
	}

	@Override
	public Integer deleteAudio(String audioId) {
		return audioDao.deleteAudio(audioId);
	}

	@Override
	public List<Image> findImagesIsValid(String userId) {
		return imageDao.findImagesIsValid(userId);
	}

	@Override
	public List<Image> findImagesIsNotValid(String userId) {
		return imageDao.findImagesIsNotValid(userId);
	}

	@Override
	public List<Image> findImagesIsShow(String userId) {
		return imageDao.findImagesIsShow(userId);
	}

	@Override
	public Integer addImage(Image image) {
		return imageDao.addImage(image);
	}

	@Override
	public Integer updateImage(Image image) {
		return imageDao.updateImage(image);
	}

	@Override
	public Integer deleteImage(String imageId) {
		return imageDao.deleteImage(imageId);
	}

	@Override
	public User queryUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.queryUserByName(userName);
	}

	@Override
	public Integer addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public Integer deleteUser(String userId) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(userId);
	}

	@Override
	public Integer updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public List<User> queryAllUsers() {
		// TODO Auto-generated method stub
		return userDao.queryAllUsers();
	}

	@Override
	public Audio findAudioById(String audioId) {
		// TODO Auto-generated method stub
		return audioDao.findAudioById(audioId);
	}

	@Override
	public Integer renameAudio(Audio audio) {
		// TODO Auto-generated method stub
		return audioDao.renameAudio(audio);
	}

	@Override
	public Integer reAudioShow(Audio audio) {
		// TODO Auto-generated method stub
		return audioDao.reAudioShow(audio);
	}

	@Override
	public Image findImageById(String imageId) {
		// TODO Auto-generated method stub
		return imageDao.findImageById(imageId);
	}

	@Override
	public Integer renameImage(Image image) {
		// TODO Auto-generated method stub
		return imageDao.renameImage(image);
	}

	@Override
	public Integer reImageShow(Image image) {
		// TODO Auto-generated method stub
		return imageDao.reImageShow(image);
	}

	@Override
	public List<Image> queryImages_18(RowBounds rowBounds, String userId) {
		// TODO Auto-generated method stub
		return imageDao.queryImages_18(rowBounds,userId);
	}
	
}
