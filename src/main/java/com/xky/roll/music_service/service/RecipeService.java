package com.xky.roll.music_service.service;

import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_service.pojo.Recipepojo;
@Transactional(value="masterTransactionManager")
public interface RecipeService {
	public JSONObject getPartientId(String patientId);
	
	public void insertRepice(Recipepojo pojo);
	

	public void updateRepice(Recipepojo pojo);
	
	 
	public Recipepojo getRecipe(String recipe);
}
