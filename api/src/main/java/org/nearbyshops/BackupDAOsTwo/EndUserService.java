package org.nearbyshops.BackupDAOsTwo;

import org.nearbyshops.JDBCContract;
import org.nearbyshops.ModelRoles.EndUser;

import java.sql.*;
import java.util.ArrayList;


public class EndUserService {

	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();	
	}
	
	
	
	public int saveEndUser(EndUser endUser)
	{	
		
		Connection conn = null;
		Statement stmt = null;
		int rowIdOfInsertedRow = -1;

		String insertEndUser = "INSERT INTO "
				+ EndUser.TABLE_NAME
				+ "("
				+ EndUser.USERNAME + ","
				+ EndUser.PASSWORD
				+ ") VALUES("
				+ "'" + endUser.getUsername() + "'" + ","
				+ "'" + endUser.getPassword() + "'" + ")";
		
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			rowIdOfInsertedRow = stmt.executeUpdate(insertEndUser,Statement.RETURN_GENERATED_KEYS);
			
			ResultSet rs = stmt.getGeneratedKeys();

			if(rs.next())
			{
				rowIdOfInsertedRow = rs.getInt(1);
			}
			
			
			
			System.out.println("Key autogenerated SaveDistributor: " + rowIdOfInsertedRow);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
			try {
			
				if(stmt!=null)
				{stmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(conn!=null)
				{conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return rowIdOfInsertedRow;
	}
	

	public int updateEndUser(EndUser endUser)
	{	
		String updateStatement = "UPDATE " + EndUser.TABLE_NAME
				+ " SET "
				+ EndUser.USERNAME + " = " + "'" + endUser.getUsername() + "'" + ","
				+ EndUser.PASSWORD + " = " + "'" + endUser.getPassword() + "'"
				+ " WHERE "
				+ EndUser.END_USER_ID  + " = " + endUser.getEndUserID();
		
		Connection conn = null;
		Statement stmt = null;
		int updatedRows = -1;
		
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			updatedRows = stmt.executeUpdate(updateStatement);
			
			
			System.out.println("Total rows updated: " + updatedRows);	
			
			//conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		
		{
			
			try {
			
				if(stmt!=null)
				{stmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(conn!=null)
				{conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;
		
	}
	

	public int deleteEndUser(int endUserID)
	{
		
		String deleteStatement = "DELETE FROM " + EndUser.TABLE_NAME + " WHERE DELIVERY_GUY_SELF_ID = "
				+ endUserID;
		
		
		Connection conn= null;
		Statement stmt = null;
		int rowsCountDeleted = 0;
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			rowsCountDeleted = stmt.executeUpdate(deleteStatement);
			
			System.out.println(" Deleted Count: " + rowsCountDeleted);	
			
			conn.close();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		
		{
			
			try {
			
				if(stmt!=null)
				{stmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(conn!=null)
				{conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return rowsCountDeleted;
	}
	
	
	
	
	
	public ArrayList<EndUser> getEndUser()
	{
		String query = "SELECT * FROM " + EndUser.TABLE_NAME;
		ArrayList<EndUser> endUsersList = new ArrayList<EndUser>();
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL
					,JDBCContract.CURRENT_USERNAME
					, JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{

				EndUser endUser = new EndUser();

				endUser.setEndUserID(rs.getInt(EndUser.END_USER_ID));
				endUser.setUsername(rs.getString(EndUser.USERNAME));

				endUsersList.add(endUser);
			}
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		finally
		
		{
			
			try {
					if(rs!=null)
					{rs.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			try {
			
				if(stmt!=null)
				{stmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(conn!=null)
				{conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
								
		return endUsersList;
	}

	
	public EndUser getEndUser(int endUserID)
	{
		
		String query = "SELECT * FROM " + EndUser.TABLE_NAME
						+ " WHERE DELIVERY_GUY_SELF_ID = " + endUserID;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	
		//Distributor distributor = null;
		EndUser endUser = null;
		
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				endUser = new EndUser();
				endUser.setEndUserID(rs.getInt(EndUser.END_USER_ID));
				endUser.setUsername(rs.getString(EndUser.USERNAME));
			}
			
			
			//System.out.println("Total itemCategories queried " + itemCategoryList.size());	
	
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		
		{
			
			try {
					if(rs!=null)
					{rs.close();}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			try {
			
				if(stmt!=null)
				{stmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(conn!=null)
				{conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return endUser;
	}




	public EndUser getEndUserPassword(Integer endUserID, String username)
	{


		String query = "";


		if(endUserID!=null)
		{
			query = "SELECT * FROM " + EndUser.TABLE_NAME
					+ " WHERE DELIVERY_GUY_SELF_ID = " + endUserID;

		}

		else if(username!=null)
		{
			query = "SELECT * FROM " + EndUser.TABLE_NAME
					+ " WHERE " +  EndUser.USERNAME + " = " + "'" + username + "'";

		}



		if(query.equals(""))
		{
			return null;
		}



		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;


//		Distributor distributor = null;
		EndUser endUser = null;

		try {

			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);

			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				endUser = new EndUser();

				endUser.setEndUserID(rs.getInt(EndUser.END_USER_ID));
				endUser.setUsername(rs.getString(EndUser.USERNAME));
				endUser.setPassword(rs.getString(EndUser.PASSWORD));
			}


			//System.out.println("Total itemCategories queried " + itemCategoryList.size());



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if(rs!=null)
				{rs.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(stmt!=null)
				{stmt.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(conn!=null)
				{conn.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return endUser;
	}




}
