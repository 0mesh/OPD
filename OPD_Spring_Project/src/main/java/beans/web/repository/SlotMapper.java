package beans.web.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import beans.web.model.Slots;

public class SlotMapper implements RowMapper<Slots> {

	@Override
	public Slots mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Slots slots = new Slots();

		slots.setStartTime(rs.getTime("StartTime"));
		slots.setEndTime(rs.getTime("EndTime"));
		return slots;
	}

	
}
