package org.nearbyshops.BackupDAOsTwo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import org.nearbyshops.JDBCContract;
import org.nearbyshops.ModelRoles.Deprecated.Distributor;


public class DistributorService {

	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();	
	}
	
	
	
	public int saveDistributor(Distributor distributor)
	{	
		
		Connection conn = null;
		Statement stmt = null;
		int rowIdOfInsertedRow = -1;

		String insertItemCategory = "INSERT INTO "
				+ Distributor.TABLE_NAME
				+ "("  
				+ Distributor.DISTRIBUTOR_NAME + ","
				+ Distributor.PASSWORD
				+ ") VALUES("
				+ "'" + distributor.getDistributorName()	+ "'" + ","
				+ "'" + distributor.getPassword() + "'" +
				")";
		
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			rowIdOfInsertedRow = stmt.executeUpdate(insertItemCategory,Statement.RETURN_GENERATED_KEYS);
			
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
	

	public int updateDistributor(Distributor distributor)
	{	
		String updateStatement = "UPDATE " + Distributor.TABLE_NAME
				+ " SET "
				+ Distributor.DISTRIBUTOR_NAME + " = " + "'" + distributor.getDistributorName() + "'" + ","
				+ Distributor.PASSWORD + " = " + "'" + distributor.getPassword() + "'"
				+ " WHERE DELIVERY_GUY_SELF_ID = "
				+ distributor.getDistributorID();
		
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
	

	public int deleteDistributor(int distributorID)
	{
		
		String deleteStatement = "DELETE FROM " + Distributor.TABLE_NAME + " WHERE DELIVERY_GUY_SELF_ID = "
				+ distributorID;
		
		
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
	
	
	
	
	
	public ArrayList<Distributor> readAllDistributor()
	{
		String query = "SELECT * FROM " + Distributor.TABLE_NAME;
		ArrayList<Distributor> distributorList = new ArrayList<Distributor>();
		
		
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
				
				Distributor distributor = new Distributor();
				
				distributor.setDistributorID(rs.getInt(Distributor.DISTRIBUTOR_ID));
				distributor.setDistributorName(rs.getString(Distributor.DISTRIBUTOR_NAME));
	
				distributorList.add(distributor);
				
			}
			
			
			
			
			System.out.println("Total Distributor queried " + distributorList.size());	
			
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
		
								
		return distributorList;
	}

	
	public Distributor getDistributor(int distributorID)
	{
		
		String query = "SELECT * FROM " + Distributor.TABLE_NAME
						+ " WHERE DELIVERY_GUY_SELF_ID = " + distributorID;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	
		Distributor distributor = null;
		
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				distributor = new Distributor();
				distributor.setDistributorID(rs.getInt(Distributor.DISTRIBUTOR_ID));
				distributor.setDistributorName(rs.getString(Distributor.DISTRIBUTOR_NAME));
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
	
		return distributor;
	}

	public Distributor getDistributorPassword(Integer distributorID, String username)
	{


		String query = "";


		if(distributorID!=null)
		{
			query = "SELECT * FROM " + Distributor.TABLE_NAME
					+ " WHERE DELIVERY_GUY_SELF_ID = " + distributorID;

		}

		else if(username!=null)
		{
			query = "SELECT * FROM " + Distributor.TABLE_NAME
					+ " WHERE " +  Distributor.DISTRIBUTOR_NAME + " = " + "'" + username + "'";

		}



		if(query.equals(""))
		{
			return null;
		}



		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;


		Distributor distributor = null;

		try {

			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);

			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				distributor = new Distributor();
				distributor.setDistributorID(rs.getInt(Distributor.DISTRIBUTOR_ID));
				distributor.setDistributorName(rs.getString(Distributor.DISTRIBUTOR_NAME));
				distributor.setPassword(rs.getString(Distributor.PASSWORD));
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

		return distributor;
	}
}
