package database.entities;
// Generated 2016-11-11 15:44:57 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import logic.diet.DietDayConfiguration;

/**
 * Diet generated by hbm2java
 */
public class Diet extends Entity  {

	private static final long serialVersionUID = 9006767713019298026L;
	private Integer id;
	private int userId;
	private String name;
	private Date validFrom;
	private Date validTo;
	private int dailyReq;
	private Set<Dietday> dietdays = new HashSet<Dietday>(0);

	public Diet() {
	}

	public Diet(int userId, String name, Date validFrom, Date validTo, int dailyReq) {
		this.userId = userId;
		this.name = name;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.dailyReq = dailyReq;
	}

	public Diet(int userId, String name, Date validFrom, Date validTo, int dailyReq, Set<Dietday> dietdays) {
		this.userId = userId;
		this.name = name;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.dailyReq = dailyReq;
		this.dietdays = dietdays;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public int getDailyReq() {
		return this.dailyReq;
	}

	public void setDailyReq(int dailyReq) {
		this.dailyReq = dailyReq;
	}

	public Set<Dietday> getDietdays() {
		return this.dietdays;
	}

	public void setDietdays(Set<Dietday> dietdays) {
		this.dietdays = dietdays;
	}
	
	/* MANUAL CODE REGION */
	
	public void setDietDayProperties(DietDayConfiguration conf){
		//TODO FIX/IMPOROVE
		setDailyReq(conf.getDailyCaloriesReq());
	}
}
