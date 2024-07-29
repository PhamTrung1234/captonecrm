package crm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm.config.MySqlConfig;
import crm.dto.StatusDTO;

public class StatusRepository {
         public List<StatusDTO> getAllStatus() {
			String sql = "SELECT * FROM status";
			List<StatusDTO> listStatus = new ArrayList<StatusDTO>();
			
			try {
				Connection connection = MySqlConfig.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rSet = ps.executeQuery();
				
				while(rSet.next()) {
					StatusDTO status = new StatusDTO(rSet.getInt("id"), rSet.getString("name"));
					listStatus.add(status);
				}
			} catch (Exception e) {
				System.out.println("loi lay status "+e.getMessage());
			}
			return listStatus;
		}
}
