package database.entities;
// Generated 2016-11-27 13:31:43 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;

/**
 * WeighthistoryId generated by hbm2java
 */
public class WeighthistoryId implements java.io.Serializable {

	private static final long serialVersionUID = -2578570921900472283L;
	private User user;
	private Date date;

	public WeighthistoryId() {
	}

	public WeighthistoryId(User user, Date date) {
		this.user = user;
		this.date = date;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof WeighthistoryId))
			return false;
		WeighthistoryId castOther = (WeighthistoryId) other;

		return ((this.getUser() == castOther.getUser()) || (this.getUser() != null && castOther.getUser() != null
				&& this.getUser().equals(castOther.getUser())))
				&& ((this.getDate() == castOther.getDate()) || (this.getDate() != null && castOther.getDate() != null
						&& this.getDate().equals(castOther.getDate())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUser() == null ? 0 : this.getUser().hashCode());
		result = 37 * result + (getDate() == null ? 0 : this.getDate().hashCode());
		return result;
	}

}
