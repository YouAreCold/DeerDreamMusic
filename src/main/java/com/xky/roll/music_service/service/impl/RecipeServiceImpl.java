package com.xky.roll.music_service.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xky.roll.music_service.mapper.RecipeMapper;
import com.xky.roll.music_service.pojo.Recipepojo;
import com.xky.roll.music_service.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	private RecipeMapper recipeMapper;

	@Override
	public JSONObject getPartientId(String patientId) {
		JSONObject object = new JSONObject();
		Recipepojo vo = new Recipepojo();
		vo.setPatientId(patientId);
		List<Recipepojo> list = recipeMapper.select(vo);
		List<String> key = new ArrayList<String>();
		List<Recipepojo> recipepojolist = new ArrayList<Recipepojo>();
		for(Recipepojo po : list){      //循环出单个病人有多少张处方，用hisorder来衡量
			if(!key.contains(po.getRecipegroupId())){
				recipepojolist.add(po);
				key.add(po.getRecipegroupId());
			}
		}
		JSONArray cflist = new JSONArray();
		for(Recipepojo hisorder : recipepojolist){   //查询单个处方下面有多少处方明细
			JSONArray cfdellist = new JSONArray();
			JSONObject objectRecipe = new JSONObject();
			objectRecipe.accumulate("recipeCode", hisorder.getRecipegroupId());  //处方编码
			objectRecipe.accumulate("recipeType", hisorder.getCflb());  //处方类型
			objectRecipe.accumulate("orderCreateTime", hisorder.getRecipeTime());  //订单创建时间
			objectRecipe.accumulate("hospitalId", "");  //音乐平台ID
			objectRecipe.accumulate("hospitalName", "南方医科大学深圳音乐平台");  //音乐平台名称
			objectRecipe.accumulate("deptId", hisorder.getDepId());  //科室ID
			objectRecipe.accumulate("deptName", hisorder.getDepName());  //科室ID
			objectRecipe.accumulate("docId", hisorder.getDocId());  //医生ID
			objectRecipe.accumulate("docName", "");  //医生名称
			objectRecipe.accumulate("patientId", hisorder.getPatientId());  //患者ID
			objectRecipe.accumulate("patientAge", hisorder.getAge());  //患者年龄
			objectRecipe.accumulate("patientName", hisorder.getPatientName());  //患者姓名
			objectRecipe.accumulate("patientTelephone", hisorder.getPhone());  //患者手机号码
			objectRecipe.accumulate("patientSex", hisorder.getSex());  //患者性别
			objectRecipe.accumulate("patientCerType", "1");  //证件类型
			objectRecipe.accumulate("patientCerNum", hisorder.getIdcard());  //患者证件号码
			objectRecipe.accumulate("recipeContent", "");  //处方内容，明文
			objectRecipe.accumulate("recipeSecretContent", "");  //处方内容，签名后的密文
			objectRecipe.accumulate("keyID", "");  //电子签名key唯一值
			objectRecipe.accumulate("herbalUse", "");  //中药用法
			objectRecipe.accumulate("herbalPreparation", "");  //中药制法
			objectRecipe.accumulate("herbalRemark", "");  //中药使用备注
			objectRecipe.accumulate("amount", "");  //处方金额
			objectRecipe.accumulate("diagnosisC", "");  //中医诊断
			objectRecipe.accumulate("diagnosisK", "");  //中医证型
			objectRecipe.accumulate("diagnosisW", "");  //西医诊断
			
			Recipepojo repicedel = new Recipepojo();
			repicedel.setRecipegroupId(hisorder.getRecipegroupId());
			List<Recipepojo> dellist = recipeMapper.select(repicedel);
			if(dellist!=null && dellist.size() > 0){
				for(Recipepojo pojo : dellist){
					JSONObject objectRecipedel = new JSONObject();
					objectRecipedel.accumulate("hospitalMediCode", pojo.getYpbm()); //音乐平台药品编码
					objectRecipedel.accumulate("hospitalMediName", ""); //音乐平台药品名称
					objectRecipedel.accumulate("spec", pojo.getYlunits()); //规格
					objectRecipedel.accumulate("eachQuantity", pojo.getYl()); //用量
					objectRecipedel.accumulate("doseUnit", pojo.getJlunits()); //剂量单位
					objectRecipedel.accumulate("dose", ""); //中药剂数
					objectRecipedel.accumulate("advice", ""); //医生嘱托
					objectRecipedel.accumulate("freqName", pojo.getPl()); //频率
					objectRecipedel.accumulate("usage", pojo.getGyfsbm()); //用法  给药方式编码
					objectRecipedel.accumulate("useUnit", pojo.getYlunit()); //用量单位
					objectRecipedel.accumulate("totalUseQuantity", pojo.getPl()); //总量
					objectRecipedel.accumulate("unitPrice", ""); //单价
					objectRecipedel.accumulate("realMoney", 100); //应收金额
					cfdellist.element(objectRecipedel);
				}
			}
			objectRecipe.accumulate("recipeMediList", cfdellist);
			cflist.add(objectRecipe);
		}
		object.accumulate("status", "1");
		object.accumulate("msg", "查询成功");
		object.accumulate("data", cflist);
	 
		return object;
	}

	@Override
	public void insertRepice(Recipepojo pojo) {
		recipeMapper.insert(pojo);
	}

	@Override
	public void updateRepice(Recipepojo pojo) {
		recipeMapper.updateByPrimaryKey(pojo);
	}

	@Override
	public Recipepojo getRecipe(String recipe) {
		Recipepojo jo = new Recipepojo();
		jo.setRecipeId(recipe);
		return recipeMapper.selectOne(jo);
	}
	
	 

}
