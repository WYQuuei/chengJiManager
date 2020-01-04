package cj.encapsulation;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//用来装[成绩单明细]的容器
public class ChengJiDetail {

	private int id;
	private String kemu;
	private double score;
	private ChengJi chengJi;
	private Date time;
	
	public ChengJiDetail() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKemu() {
		return kemu;
	}

	public void setKemu(String kemu) {
		this.kemu = kemu;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ChengJi getChengJi() {
		return chengJi;
	}

	public void setChengJi(ChengJi chengJi) {
		this.chengJi = chengJi;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
