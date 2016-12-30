package org.nearbyshops.DAOsPreparedRoles;

import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.Globals.Globals;
import org.nearbyshops.ModelEndPoints.EndUserEndPoint;
import org.nearbyshops.ModelRoles.Deprecated.Distributor;
import org.nearbyshops.ModelRoles.EndUser;

import java.sql.*;
import java.util.ArrayList;


public class EndUserDAOPrepared {


	private HikariDataSource dataSource = Globals.getDataSource();

	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();	
	}
	
	
	
	public int saveEndUser(EndUser endUser)
	{	
		
		Connection connection = null;
		PreparedStatement statement = null;
		int rowIdOfInsertedRow = -1;

		String insert = "INSERT INTO "
				+ EndUser.TABLE_NAME
				+ "("  
				+ EndUser.END_USER_NAME + ","
				+ EndUser.USERNAME + ","
				+ EndUser.PASSWORD + ","

				+ EndUser.ABOUT + ","
				+ EndUser.PROFILE_IMAGE_URL + ","

				+ EndUser.IS_ENABLED + ","
				+ EndUser.IS_WAITLISTED + ") VALUES(?,?,? ,?,? ,?,?)";
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(insert,PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1,endUser.getName());
			statement.setString(2,endUser.getUsername());
			statement.setString(3,endUser.getPassword());

			statement.setString(4,endUser.getAbout());
			statement.setString(5,endUser.getProfileImageURL());

			statement.setBoolean(6,endUser.getEnabled());
			statement.setBoolean(7,endUser.getWaitlisted());

			rowIdOfInsertedRow = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

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
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
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
				+ EndUser.END_USER_NAME + " = ?,"
				+ EndUser.USERNAME + " = ?,"
				+ Distributor.PASSWORD + " = ?,"

				+ EndUser.ABOUT + " = ?,"
				+ EndUser.PROFILE_IMAGE_URL + " = ?,"

				+ EndUser.IS_ENABLED + " = ?,"
				+ EndUser.IS_WAITLISTED + " = ?,"

				+ EndUser.UPDATED + " = ?"
				+ " WHERE " + EndUser.END_USER_ID + " = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement);

			statement.setString(1,endUser.getName());
			statement.setString(2,endUser.getUsername());
			statement.setString(3,endUser.getPassword());

			statement.setString(4,endUser.getAbout());
			statement.setString(5,endUser.getProfileImageURL());

			statement.setBoolean(6,endUser.getEnabled());
			statement.setBoolean(7,endUser.getWaitlisted());

			statement.setTimestamp(8,new Timestamp(System.currentTimeMillis()));

			statement.setInt(9,endUser.getEndUserID());

			updatedRows = statement.executeUpdate();
			
			
			System.out.println("Total rows updated: " + updatedRows);	
			
			//conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;
		
	}



	public int updateEndUserNoPassword(EndUser endUser)
	{
		String updateStatement = "UPDATE " + EndUser.TABLE_NAME
				+ " SET "
				+ EndUser.END_USER_NAME + " = ?,"
				+ EndUser.USERNAME + " = ?,"

				+ EndUser.ABOUT + " = ?,"
				+ EndUser.PROFILE_IMAGE_URL + " = ?,"

				+ EndUser.IS_ENABLED + " = ?,"
				+ EndUser.IS_WAITLISTED + " = ?,"

				+ EndUser.UPDATED + " = ?"
				+ " WHERE " + EndUser.END_USER_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement);

			statement.setString(1,endUser.getName());
			statement.setString(2,endUser.getUsername());
//			statement.setString(3,distributor.getPassword());

			statement.setString(3,endUser.getAbout());
			statement.setString(4,endUser.getProfileImageURL());

			statement.setBoolean(5,endUser.getEnabled());
			statement.setBoolean(6,endUser.getWaitlisted());

			statement.setTimestamp(7,new Timestamp(System.currentTimeMillis()));

			statement.setInt(8,endUser.getEndUserID());

			updatedRows = statement.executeUpdate();


			System.out.println("Total rows updated: " + updatedRows);

			//conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally

		{

			try {

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;

	}


	public int deleteEndUser(int EndUserID)
	{
		
		String deleteStatement = "DELETE FROM " + EndUser.TABLE_NAME
				+ " WHERE " + EndUser.END_USER_ID  + " = ?";
		
		
		Connection connection= null;
		PreparedStatement statement = null;
		int rowsCountDeleted = 0;
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(deleteStatement);

			statement.setInt(1,EndUserID);

			rowsCountDeleted = statement.executeUpdate();
			
			System.out.println(" Deleted Count: " + rowsCountDeleted);	
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		return rowsCountDeleted;
	}
	
	
	
	
	
	public ArrayList<EndUser> getEndUsers(
			Integer EndUserID,
			Boolean isEnabled,
			Boolean isWaitlisted,
			String sortBy,
			Integer limit, Integer offset)
	{

		boolean isfirst = true;

		String queryNormal = "SELECT * FROM " + EndUser.TABLE_NAME;


		if(EndUserID != null)
		{

			queryNormal = queryNormal + " WHERE "
					+ EndUser.TABLE_NAME
					+ "."
					+ EndUser.END_USER_ID + " = " + EndUserID;

			isfirst = false;
		}


		if(isEnabled !=null) {
			String queryPartEnabled = "";

			queryPartEnabled = EndUser.TABLE_NAME
					+ "."
					+ EndUser.IS_ENABLED + " = " + isEnabled;


			if (isfirst) {
				queryNormal = queryNormal + " WHERE " +queryPartEnabled;
			}
			else
			{
				queryNormal = queryNormal + " AND " + queryPartEnabled;
			}


			isfirst = false;
		}


		if(isWaitlisted != null)
		{

			String queryPartWaitlisted = "";

			queryPartWaitlisted = EndUser.TABLE_NAME
					+ "."
					+ EndUser.IS_WAITLISTED + " = " + isWaitlisted;


			if (isfirst) {
				queryNormal = queryNormal + " WHERE " + queryPartWaitlisted;
			}
			else
			{
				queryNormal = queryNormal + " AND " + queryPartWaitlisted;
			}


			isfirst = false;

		}




		if(sortBy!=null)
		{
			if(!sortBy.equals(""))
			{
				String queryPartSortBy = " ORDER BY " + sortBy;

				queryNormal = queryNormal + queryPartSortBy;
//				queryJoin = queryJoin + queryPartSortBy;
			}
		}



		if(limit != null)
		{

			String queryPartLimitOffset = "";

			if(offset>0)
			{
				queryPartLimitOffset = " LIMIT " + limit + " " + " OFFSET " + offset;

			}else
			{
				queryPartLimitOffset = " LIMIT " + limit + " " + " OFFSET " + 0;
			}


			queryNormal = queryNormal + queryPartLimitOffset;
//			queryJoin = queryJoin + queryPartLimitOffset;
		}







		ArrayList<EndUser> endUserList = new ArrayList<EndUser>();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(queryNormal);
			
			while(rs.next())
			{
				
				EndUser endUser = new EndUser();

				endUser.setEndUserID(rs.getInt(EndUser.END_USER_ID));
				endUser.setName(rs.getString(EndUser.END_USER_NAME));
				endUser.setUsername(rs.getString(EndUser.USERNAME));
				endUser.setPassword(rs.getString(EndUser.PASSWORD));

				endUser.setAbout(rs.getString(EndUser.ABOUT));
				endUser.setProfileImageURL(rs.getString(EndUser.PROFILE_IMAGE_URL));

				endUser.setEnabled(rs.getBoolean(EndUser.IS_ENABLED));
				endUser.setWaitlisted(rs.getBoolean(EndUser.IS_WAITLISTED));

				endUser.setCreated(rs.getTimestamp(EndUser.CREATED));
				endUser.setUpdated(rs.getTimestamp(EndUser.UPDATED));

				endUserList.add(endUser);
			}

			
			
			
			
			System.out.println("Total Distributor queried " + endUserList.size());
			
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
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
								
		return endUserList;
	}



	public EndUserEndPoint getEndpointMetadata(
			Integer EndUserID,
			Boolean isEnabled,
			Boolean isWaitlisted)
	{

		boolean isfirst = true;

		String queryNormal = "SELECT count(*) as item_count FROM " + EndUser.TABLE_NAME;


		if(EndUserID != null)
		{

			queryNormal = queryNormal + " WHERE "
					+ EndUser.TABLE_NAME
					+ "."
					+ EndUser.END_USER_ID + " = " + EndUserID;

			isfirst = false;
		}


		if(isEnabled !=null) {
			String queryPartEnabled = "";

			queryPartEnabled = EndUser.TABLE_NAME
					+ "."
					+ EndUser.IS_ENABLED + " = " + isEnabled;


			if (isfirst) {
				queryNormal = queryNormal + " WHERE " +queryPartEnabled;
			}
			else
			{
				queryNormal = queryNormal + " AND " + queryPartEnabled;
			}


			isfirst = false;
		}


		if(isWaitlisted != null)
		{

			String queryPartWaitlisted = "";

			queryPartWaitlisted = EndUser.TABLE_NAME
					+ "."
					+ EndUser.IS_WAITLISTED + " = " + isWaitlisted;


			if (isfirst) {
				queryNormal = queryNormal + " WHERE " + queryPartWaitlisted;
			}
			else
			{
				queryNormal = queryNormal + " AND " + queryPartWaitlisted;
			}


			isfirst = false;

		}



		EndUserEndPoint endPoint = new EndUserEndPoint();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(queryNormal);

			while(rs.next())
			{
				endPoint.setItemCount(rs.getInt("item_count"));
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

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return endPoint;
	}







	public EndUser getEndUser(int EndUserID)
	{
		
		String query = "SELECT * FROM " + EndUser.TABLE_NAME
						+ " WHERE DELIVERY_GUY_SELF_ID = " + EndUserID;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
	
		EndUser endUser = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next())
			{
				endUser = new EndUser();

				endUser.setEndUserID(rs.getInt(EndUser.END_USER_ID));
				endUser.setName(rs.getString(EndUser.END_USER_NAME));
				endUser.setUsername(rs.getString(EndUser.USERNAME));
				endUser.setPassword(rs.getString(EndUser.PASSWORD));

				endUser.setAbout(rs.getString(EndUser.ABOUT));
				endUser.setProfileImageURL(rs.getString(EndUser.PROFILE_IMAGE_URL));
				endUser.setEnabled(rs.getBoolean(EndUser.IS_ENABLED));
				endUser.setWaitlisted(rs.getBoolean(EndUser.IS_WAITLISTED));


				endUser.setCreated(rs.getTimestamp(EndUser.CREATED));
				endUser.setUpdated(rs.getTimestamp(EndUser.UPDATED));
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
			
				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				
				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return endUser;
	}

	/*
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



		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;


		Distributor distributor = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(query);

			while(rs.next())
			{
				distributor = new Distributor();

				distributor.setDistributorID(rs.getInt(Distributor.DISTRIBUTOR_ID));
				distributor.setDistributorName(rs.getString(Distributor.DISTRIBUTOR_NAME));
				distributor.setUsername(rs.getString(Distributor.USERNAME));
				distributor.setPassword(rs.getString(Distributor.PASSWORD));
				distributor.setEnabled(rs.getBoolean(Distributor.IS_ENABLED));
				distributor.setWaitlisted(rs.getBoolean(Distributor.IS_WAITLISTED));
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

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return distributor;
	}*/




	private void logMessage(String message)
	{
		System.out.println(message);
	}



	// method to authenticate Distributor
	public EndUser checkEndUser(Integer endUserID, String username, String password)
	{


		logMessage("Checking End User");


		boolean isFirst = true;

		String query = "SELECT * FROM " + EndUser.TABLE_NAME;

		if(endUserID!=null)
		{
			query = query + " WHERE " + EndUser.END_USER_ID + " = " + endUserID;

			isFirst = false;
		}

		if(username!=null)
		{
			String queryPartUsername = EndUser.USERNAME + " = '" + username + "'";

			if(isFirst)
			{
				query = query + " WHERE " + queryPartUsername;

				isFirst = false;
			}
			else
			{
				query = query + " AND " + queryPartUsername;
			}
		}


		if(password!=null)
		{
			String queryPartPassword = Distributor.PASSWORD + " = '" + password + "'";

			if(isFirst)
			{
				query = query + " WHERE " + queryPartPassword;
			}
			else
			{
				query = query + " AND " + queryPartPassword;
			}
		}


		logMessage(query);


		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;


		//Distributor distributor = null;
		EndUser endUser = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(query);

			while(rs.next())
			{
				endUser = new EndUser();

				logMessage("Inside WHile check Distributor");

				endUser.setEndUserID(rs.getInt(EndUser.END_USER_ID));
				endUser.setName(rs.getString(EndUser.END_USER_NAME));
				endUser.setUsername(rs.getString(EndUser.USERNAME));
				endUser.setPassword(rs.getString(EndUser.PASSWORD));

				endUser.setAbout(rs.getString(EndUser.ABOUT));
				endUser.setProfileImageURL(rs.getString(EndUser.PROFILE_IMAGE_URL));

				endUser.setWaitlisted(rs.getBoolean(EndUser.IS_WAITLISTED));
				endUser.setEnabled(rs.getBoolean(EndUser.IS_ENABLED));


				endUser.setCreated(rs.getTimestamp(EndUser.CREATED));
				endUser.setUpdated(rs.getTimestamp(EndUser.UPDATED));
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

				if(statement!=null)
				{statement.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if(connection!=null)
				{connection.close();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return endUser;
	}
}
