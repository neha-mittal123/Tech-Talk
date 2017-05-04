package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Dto.PendingRequestDto;
import Dto.TechTalkDto;

public class PendingRequestDao {

	private PreparedStatement pst1;
	private static Connection cn;
	private PreparedStatement pst2;
	ArrayList<PendingRequestDto> dto=new ArrayList<>();
	
	
	


	public PendingRequestDao() throws Exception {
		super();
		Class.forName("com.mysql.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","123456");
		pst1 = cn.prepareStatement("select * from pendingrequests_details");
		pst2=cn.prepareStatement("delete from  pendingrequests_details where s_id=?");
	}



	public ArrayList<PendingRequestDto> getPendingRequest() throws Exception {
		try (ResultSet rst = pst1.executeQuery()){
			while(rst.next()){
			 dto.add(new PendingRequestDto(rst.getInt(1), rst.getDate(2), rst.getString(3), rst.getString(4), rst.getString(5)));
			 System.out.println(dto);
			}
			
		}
		return dto;
	}
	
	public int deletePendingRequest(int id) throws SQLException{
		
		int c=0;
		pst2.setInt(1, id);;
		c=pst2.executeUpdate();
		System.out.println(c);
		return c;
	}
	
}
