package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Dto.TechTalkDto;

public class TechTalkDao {

	private PreparedStatement pst1;
	private static Connection cn;
	ArrayList<TechTalkDto> dto=new ArrayList<>();
	private PreparedStatement pst2;
	
	


	public TechTalkDao() throws Exception {
		super();
		Class.forName("com.mysql.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","123456");
		pst1 = cn.prepareStatement("select * from techtalk_details");
		pst2=cn.prepareStatement("insert into pendingrequests_details values(?,?,?,?,?)");
	}



	public ArrayList<TechTalkDto> getTechTalk() throws Exception {
		try (ResultSet rst = pst1.executeQuery()){
			while(rst.next()){
			 dto.add(new TechTalkDto(rst.getInt(1), rst.getDate(2), rst.getString(3), rst.getString(4), rst.getString(5)));
			 System.out.println(dto);
			}
		}
		return dto;
	}
	public int addTechTalk(int id,Date date,String title,String description,String presenter) throws Exception{
		System.out.println("in dao createEmployee");
		
		pst2.setInt(1, id);
		pst2.setDate(2, (java.sql.Date) date);
		pst2.setString(3,title);
		pst2.setString(4, description);
		pst2.setString(5, presenter);
		int rst=pst2.executeUpdate();
		System.out.println("in add techtalk");
		if(rst==1)
		return 1;
		return 0;
	}
	

}
