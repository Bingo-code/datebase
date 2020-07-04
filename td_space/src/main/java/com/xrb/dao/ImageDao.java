package com.xrb.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xrb.entity.Image;

public interface ImageDao {
	
	public List<Image> findImagesIsValid(String userId);
	
	public List<Image> findImagesIsNotValid(String userId);
	
	public List<Image> findImagesIsShow(String userId);
	
	public List<Image> queryImages_18(RowBounds rowBounds,String userId);
	
	public Image findImageById(String imageId);
	
	public Integer addImage(Image image);
	
	public Integer updateImage(Image image);
	
	public Integer renameImage(Image image);
	
	public Integer reImageShow(Image image);
	
	public Integer deleteImage(String imageId);
}
