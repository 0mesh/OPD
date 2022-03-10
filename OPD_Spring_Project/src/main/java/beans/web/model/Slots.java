package beans.web.model;

import java.sql.Time;

public class Slots {

	private Time startTime;
	private Time endTime;

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Slots [startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
