package org.nearbyshops.DAOsPreparedRoles;

import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.Globals.Globals;
import org.nearbyshops.ModelRoles.ShopStaff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ShopStaffDAOPrepared {


	private HikariDataSource dataSource = Globals.getDataSource();


	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}



	public int saveShopStaff(ShopStaff shopStaff) {

		Connection connection = null;
		PreparedStatement statement = null;
		int rowIdOfInsertedRow = -1;

		String insert = "INSERT INTO "
				+ ShopStaff.TABLE_NAME
				+ "("

				+ ShopStaff.STAFF_NAME + ","
				+ ShopStaff.USER_NAME + ","
				+ ShopStaff.PASSWORD + ","

				+ ShopStaff.SHOP_ID + ","

				+ ShopStaff.ABOUT + ","
				+ ShopStaff.PROFILE_IMAGE_URL + ","

				+ ShopStaff.PHONE_NUMBER + ","
				+ ShopStaff.DESIGNATION + ","
				+ ShopStaff.IS_ENABLED + ","
				+ ShopStaff.ACCOUNT_PRIVATE + ","

				+ ShopStaff.GOVERNMENT_ID_NAME + ","
				+ ShopStaff.GOVERNMENT_ID_NUMBER + ","

				+ ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP + ","
				+ ShopStaff.UPDATE_STOCK + ","

				+ ShopStaff.CANCEL_ORDERS + ","
				+ ShopStaff.CONFIRM_ORDERS + ","
				+ ShopStaff.SET_ORDERS_PACKED + ","
				+ ShopStaff.HANDOVER_TO_DELIVERY + ","
				+ ShopStaff.MARK_ORDERS_DELIVERED + ","
				+ ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY + ","
				+ ShopStaff.ACCEPT_RETURNS + ""

				+ ") VALUES(?,?,?, ?, ?,? ,?,?,?,? ,?,? ,?,? ,?,?,?,?,?,?,?)";

		try {

			connection = dataSource.getConnection();

			statement = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);

			int i = 0;
			statement.setString(++i, shopStaff.getStaffName());
			statement.setString(++i, shopStaff.getUsername());
			statement.setString(++i, shopStaff.getPassword());

			statement.setObject(++i,shopStaff.getShopID());

			statement.setString(++i,shopStaff.getAbout());
			statement.setString(++i,shopStaff.getProfileImageURL());

			statement.setString(++i,shopStaff.getPhone());
			statement.setString(++i,shopStaff.getDesignation());
			statement.setObject(++i,shopStaff.getEnabled());
			statement.setObject(++i,shopStaff.isAccountPrivate());

			statement.setString(++i,shopStaff.getGovtIDName());
			statement.setString(++i,shopStaff.getGovtIDNumber());

			statement.setObject(++i,shopStaff.isAddRemoveItemsFromShop());
			statement.setObject(++i,shopStaff.isUpdateStock());

			statement.setObject(++i,shopStaff.isCancelOrders());
			statement.setObject(++i,shopStaff.isConfirmOrders());
			statement.setObject(++i,shopStaff.isSetOrdersPacked());
			statement.setObject(++i,shopStaff.isHandoverToDelivery());
			statement.setObject(++i,shopStaff.isMarkOrdersDelivered());
			statement.setObject(++i,shopStaff.isAcceptPaymentsFromDelivery());
			statement.setObject(++i,shopStaff.isAcceptReturns());


			rowIdOfInsertedRow = statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				rowIdOfInsertedRow = rs.getInt(1);
			}


			System.out.println("Key autogenerated SaveDistributor: " + rowIdOfInsertedRow);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rowIdOfInsertedRow;
	}


	public int updateShopStaff(ShopStaff shopStaff) {

		String updateStatement = "UPDATE " + ShopStaff.TABLE_NAME
				+ " SET "

				+ ShopStaff.STAFF_NAME + "=?,"
				+ ShopStaff.USER_NAME + "=?,"
				+ ShopStaff.PASSWORD + "=?,"

				+ ShopStaff.SHOP_ID + "=?,"

				+ ShopStaff.ABOUT + "=?,"
				+ ShopStaff.PROFILE_IMAGE_URL + "=?,"

				+ ShopStaff.PHONE_NUMBER + "=?,"
				+ ShopStaff.DESIGNATION + "=?,"
				+ ShopStaff.IS_ENABLED + "=?,"
				+ ShopStaff.ACCOUNT_PRIVATE + "=?,"

				+ ShopStaff.GOVERNMENT_ID_NAME + "=?,"
				+ ShopStaff.GOVERNMENT_ID_NUMBER + "=?,"

				+ ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP + "=?,"
				+ ShopStaff.UPDATE_STOCK + "=?,"

				+ ShopStaff.CANCEL_ORDERS + "=?,"
				+ ShopStaff.CONFIRM_ORDERS + "=?,"
				+ ShopStaff.SET_ORDERS_PACKED + "=?,"
				+ ShopStaff.HANDOVER_TO_DELIVERY + "=?,"
				+ ShopStaff.MARK_ORDERS_DELIVERED + "=?,"
				+ ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY + "=?,"
				+ ShopStaff.ACCEPT_RETURNS + "=?"

				+ " WHERE " + ShopStaff.STAFF_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			int i = 0;
			statement.setString(++i, shopStaff.getStaffName());
			statement.setString(++i, shopStaff.getUsername());
			statement.setString(++i, shopStaff.getPassword());

			statement.setObject(++i,shopStaff.getShopID());

			statement.setString(++i,shopStaff.getAbout());
			statement.setString(++i,shopStaff.getProfileImageURL());

			statement.setString(++i,shopStaff.getPhone());
			statement.setString(++i,shopStaff.getDesignation());
			statement.setObject(++i,shopStaff.getEnabled());
			statement.setObject(++i,shopStaff.isAccountPrivate());

			statement.setString(++i,shopStaff.getGovtIDName());
			statement.setString(++i,shopStaff.getGovtIDNumber());


			statement.setObject(++i,shopStaff.isAddRemoveItemsFromShop());
			statement.setObject(++i,shopStaff.isUpdateStock());

			statement.setObject(++i,shopStaff.isCancelOrders());
			statement.setObject(++i,shopStaff.isConfirmOrders());
			statement.setObject(++i,shopStaff.isSetOrdersPacked());
			statement.setObject(++i,shopStaff.isHandoverToDelivery());
			statement.setObject(++i,shopStaff.isMarkOrdersDelivered());
			statement.setObject(++i,shopStaff.isAcceptPaymentsFromDelivery());
			statement.setObject(++i,shopStaff.isAcceptReturns());


			statement.setObject(++i,shopStaff.getStaffID());

			updatedRows = statement.executeUpdate();


			System.out.println("Total rows updated: " + updatedRows);

			//conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;
	}



	public int updateStaffBySelf(ShopStaff shopStaff) {

		String updateStatement = "UPDATE " + ShopStaff.TABLE_NAME
				+ " SET "

				+ ShopStaff.STAFF_NAME + "=?,"
				+ ShopStaff.USER_NAME + "=?,"
				+ ShopStaff.PASSWORD + "=?,"

//				+ ShopStaff.SHOP_ID + "=?,"

				+ ShopStaff.ABOUT + "=?,"
				+ ShopStaff.PROFILE_IMAGE_URL + "=?,"

				+ ShopStaff.PHONE_NUMBER + "=?,"
				+ ShopStaff.DESIGNATION + "=?,"

				+ ShopStaff.GOVERNMENT_ID_NAME + "=?,"
				+ ShopStaff.GOVERNMENT_ID_NUMBER + "=?"

				+ " WHERE " + ShopStaff.STAFF_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		int updatedRows = -1;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement, PreparedStatement.RETURN_GENERATED_KEYS);


			int i = 0;
			statement.setString(++i, shopStaff.getStaffName());
			statement.setString(++i, shopStaff.getUsername());
			statement.setString(++i, shopStaff.getPassword());

//			statement.setObject(++i,shopStaff.getShopID());

			statement.setString(++i,shopStaff.getAbout());
			statement.setString(++i,shopStaff.getProfileImageURL());

			statement.setString(++i,shopStaff.getPhone());
			statement.setString(++i,shopStaff.getDesignation());

			statement.setString(++i,shopStaff.getGovtIDName());
			statement.setString(++i,shopStaff.getGovtIDNumber());

			statement.setObject(++i,shopStaff.getStaffID());

			updatedRows = statement.executeUpdate();


			System.out.println("Total rows updated: " + updatedRows);

			//conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updatedRows;

	}


	public int deleteShopStaff(int shopStaffID) {

		String deleteStatement = "DELETE FROM " + ShopStaff.TABLE_NAME
				+ " WHERE "
				+ ShopStaff.STAFF_ID + " = ?";


		Connection connection = null;
		PreparedStatement statement = null;
		int rowsCountDeleted = 0;
		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(deleteStatement);

			statement.setInt(1, shopStaffID);

			rowsCountDeleted = statement.executeUpdate();

			System.out.println(" Deleted Count: " + rowsCountDeleted);

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return rowsCountDeleted;
	}


	public ArrayList<ShopStaff> getShopStaffForShopAdmin(Boolean isEnabled, Integer shopID) {


		boolean isFirst = true;

		String query = "SELECT "

				+ ShopStaff.STAFF_ID + ","
				+ ShopStaff.STAFF_NAME + ","
				+ ShopStaff.USER_NAME + ","
				+ ShopStaff.PASSWORD + ","

				+ ShopStaff.SHOP_ID + ","

				+ ShopStaff.ABOUT + ","
				+ ShopStaff.PROFILE_IMAGE_URL + ","

				+ ShopStaff.PHONE_NUMBER + ","
				+ ShopStaff.DESIGNATION + ","
				+ ShopStaff.IS_ENABLED + ","
				+ ShopStaff.ACCOUNT_PRIVATE + ","

				+ ShopStaff.GOVERNMENT_ID_NAME + ","
				+ ShopStaff.GOVERNMENT_ID_NUMBER + ","
				+ ShopStaff.TIMESTAMP_CREATED + ","

				+ ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP + ","
				+ ShopStaff.UPDATE_STOCK + ","

				+ ShopStaff.CANCEL_ORDERS + ","
				+ ShopStaff.CONFIRM_ORDERS + ","
				+ ShopStaff.SET_ORDERS_PACKED + ","
				+ ShopStaff.HANDOVER_TO_DELIVERY + ","
				+ ShopStaff.MARK_ORDERS_DELIVERED + ","
				+ ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY + ","
				+ ShopStaff.ACCEPT_RETURNS + ""

				+ " FROM " + ShopStaff.TABLE_NAME;




		if(isEnabled !=null)
		{
			query = query + " WHERE " + ShopStaff.IS_ENABLED + " = ?";

			isFirst = false;
		}



		if(shopID !=null)
		{

			String queryPart = ShopStaff.SHOP_ID + " = ?";

			if(isFirst)
			{

				query = query + " WHERE " + queryPart;

				isFirst = false;
			}
			else
			{
				query = query + " AND " + queryPart;
			}
		}



//		logMessage(query);

		ArrayList<ShopStaff> shopStaffList = new ArrayList<ShopStaff>();


		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(query);

			int i = 0;
			if(isEnabled!=null)
			{
				statement.setObject(++i,isEnabled);
			}

			if(shopID!=null)
			{
				statement.setObject(++i,shopID);
			}


			rs = statement.executeQuery();

			while (rs.next()) {

				ShopStaff shopStaff = new ShopStaff();

				shopStaff.setStaffID(rs.getInt(ShopStaff.STAFF_ID));
				shopStaff.setStaffName(rs.getString(ShopStaff.STAFF_NAME));
				shopStaff.setUsername(rs.getString(ShopStaff.USER_NAME));
				shopStaff.setPassword(rs.getString(ShopStaff.PASSWORD));

				shopStaff.setShopID(rs.getInt(ShopStaff.SHOP_ID));

				shopStaff.setAbout(rs.getString(ShopStaff.ABOUT));
				shopStaff.setProfileImageURL(rs.getString(ShopStaff.PROFILE_IMAGE_URL));

				shopStaff.setPhone(rs.getString(ShopStaff.PHONE_NUMBER));
				shopStaff.setDesignation(rs.getString(ShopStaff.DESIGNATION));
				shopStaff.setEnabled(rs.getBoolean(ShopStaff.IS_ENABLED));
				shopStaff.setAccountPrivate(rs.getBoolean(ShopStaff.ACCOUNT_PRIVATE));

				shopStaff.setGovtIDName(rs.getString(ShopStaff.GOVERNMENT_ID_NAME));
				shopStaff.setGovtIDNumber(rs.getString(ShopStaff.GOVERNMENT_ID_NUMBER));
				shopStaff.setTimestampCreated(rs.getTimestamp(ShopStaff.TIMESTAMP_CREATED));

				shopStaff.setAddRemoveItemsFromShop(rs.getBoolean(ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP));
				shopStaff.setUpdateStock(rs.getBoolean(ShopStaff.UPDATE_STOCK));

				shopStaff.setCancelOrders(rs.getBoolean(ShopStaff.CANCEL_ORDERS));
				shopStaff.setConfirmOrders(rs.getBoolean(ShopStaff.CONFIRM_ORDERS));
				shopStaff.setSetOrdersPacked(rs.getBoolean(ShopStaff.SET_ORDERS_PACKED));
				shopStaff.setHandoverToDelivery(rs.getBoolean(ShopStaff.HANDOVER_TO_DELIVERY));
				shopStaff.setMarkOrdersDelivered(rs.getBoolean(ShopStaff.MARK_ORDERS_DELIVERED));
				shopStaff.setAcceptPaymentsFromDelivery(rs.getBoolean(ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY));
				shopStaff.setAcceptReturns(rs.getBoolean(ShopStaff.ACCEPT_RETURNS));

				shopStaffList.add(shopStaff);
			}


			logMessage("Staff List Size : " + String.valueOf(shopStaffList.size()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return shopStaffList;
	}




	public ShopStaff getShopIDforShopStaff(int shopStaffID) {

		String query = "SELECT " + ShopStaff.STAFF_ID + "," + ShopStaff.SHOP_ID + ""
					+ " FROM " + ShopStaff.TABLE_NAME
					+ " WHERE " + ShopStaff.STAFF_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;


		//Distributor distributor = null;
		ShopStaff shopStaff = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(query);

			statement.setObject(1,shopStaffID);

			rs = statement.executeQuery();

			while (rs.next()) {

				shopStaff = new ShopStaff();

				shopStaff.setStaffID(rs.getInt(ShopStaff.STAFF_ID));
				shopStaff.setShopID(rs.getInt(ShopStaff.SHOP_ID));
			}


			//System.out.println("Total itemCategories queried " + itemCategoryList.size());


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return shopStaff;
	}



	public ShopStaff getShopStaffForSelf(int shopStaffID) {

		String query = "SELECT "

				+ ShopStaff.STAFF_ID + ","
				+ ShopStaff.STAFF_NAME + ","
				+ ShopStaff.USER_NAME + ","
				+ ShopStaff.PASSWORD + ","

				+ ShopStaff.SHOP_ID + ","

				+ ShopStaff.ABOUT + ","
				+ ShopStaff.PROFILE_IMAGE_URL + ","

				+ ShopStaff.PHONE_NUMBER + ","
				+ ShopStaff.DESIGNATION + ","
				+ ShopStaff.IS_ENABLED + ","
				+ ShopStaff.ACCOUNT_PRIVATE + ","

				+ ShopStaff.GOVERNMENT_ID_NAME + ","
				+ ShopStaff.GOVERNMENT_ID_NUMBER + ","
				+ ShopStaff.TIMESTAMP_CREATED + ","

				+ ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP + ","
				+ ShopStaff.UPDATE_STOCK + ","

				+ ShopStaff.CANCEL_ORDERS + ","
				+ ShopStaff.CONFIRM_ORDERS + ","
				+ ShopStaff.SET_ORDERS_PACKED + ","
				+ ShopStaff.HANDOVER_TO_DELIVERY + ","
				+ ShopStaff.MARK_ORDERS_DELIVERED + ","
				+ ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY + ","
				+ ShopStaff.ACCEPT_RETURNS + ""

				+ " FROM " + ShopStaff.TABLE_NAME
				+ " WHERE " + ShopStaff.STAFF_ID + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;


		//Distributor distributor = null;
		ShopStaff shopStaff = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(query);

			statement.setObject(1,shopStaffID);

			rs = statement.executeQuery();

			while (rs.next()) {

				shopStaff = new ShopStaff();


				shopStaff.setStaffID(rs.getInt(ShopStaff.STAFF_ID));
				shopStaff.setStaffName(rs.getString(ShopStaff.STAFF_NAME));
				shopStaff.setUsername(rs.getString(ShopStaff.USER_NAME));
				shopStaff.setPassword(rs.getString(ShopStaff.PASSWORD));

				shopStaff.setShopID(rs.getInt(ShopStaff.SHOP_ID));

				shopStaff.setAbout(rs.getString(ShopStaff.ABOUT));
				shopStaff.setProfileImageURL(rs.getString(ShopStaff.PROFILE_IMAGE_URL));


				shopStaff.setPhone(rs.getString(ShopStaff.PHONE_NUMBER));
				shopStaff.setDesignation(rs.getString(ShopStaff.DESIGNATION));
				shopStaff.setEnabled(rs.getBoolean(ShopStaff.IS_ENABLED));
				shopStaff.setAccountPrivate(rs.getBoolean(ShopStaff.ACCOUNT_PRIVATE));

				shopStaff.setGovtIDName(rs.getString(ShopStaff.GOVERNMENT_ID_NAME));
				shopStaff.setGovtIDNumber(rs.getString(ShopStaff.GOVERNMENT_ID_NUMBER));
				shopStaff.setTimestampCreated(rs.getTimestamp(ShopStaff.TIMESTAMP_CREATED));


				shopStaff.setAddRemoveItemsFromShop(rs.getBoolean(ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP));
				shopStaff.setUpdateStock(rs.getBoolean(ShopStaff.UPDATE_STOCK));

				shopStaff.setCancelOrders(rs.getBoolean(ShopStaff.CANCEL_ORDERS));
				shopStaff.setConfirmOrders(rs.getBoolean(ShopStaff.CONFIRM_ORDERS));
				shopStaff.setSetOrdersPacked(rs.getBoolean(ShopStaff.SET_ORDERS_PACKED));
				shopStaff.setHandoverToDelivery(rs.getBoolean(ShopStaff.HANDOVER_TO_DELIVERY));
				shopStaff.setMarkOrdersDelivered(rs.getBoolean(ShopStaff.MARK_ORDERS_DELIVERED));
				shopStaff.setAcceptPaymentsFromDelivery(rs.getBoolean(ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY));
				shopStaff.setAcceptReturns(rs.getBoolean(ShopStaff.ACCEPT_RETURNS));

			}


			//System.out.println("Total itemCategories queried " + itemCategoryList.size());	


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return shopStaff;
	}



	public boolean checkUsernameExists(String username)
	{

		String query = "SELECT " + ShopStaff.USER_NAME
				+ " FROM " + ShopStaff.TABLE_NAME
				+ " WHERE " + ShopStaff.USER_NAME + " = ?";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		System.out.println("Checked Username : " + username);

//		ShopAdmin shopAdmin = null;



		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(query);

			statement.setObject(1,username);

			rs = statement.executeQuery();


			while(rs.next())
			{

				return true;
			}


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

		return false;
	}



	public void logMessage(String message)
	{
		System.out.println(message);
	}



	public ShopStaff checkShopStaff(String username, String password)
	{


		logMessage("Checking Staff");


		boolean isFirst = true;

		String query = "SELECT "
						+ ShopStaff.STAFF_ID + ","
						+ ShopStaff.USER_NAME + ","
						+ ShopStaff.PASSWORD + ","
						+ ShopStaff.IS_ENABLED  + ","

						+ ShopStaff.SHOP_ID  + ","

						+ ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP + ","
						+ ShopStaff.UPDATE_STOCK + ","

						+ ShopStaff.CANCEL_ORDERS + ","
						+ ShopStaff.CONFIRM_ORDERS + ","
						+ ShopStaff.SET_ORDERS_PACKED + ","
						+ ShopStaff.HANDOVER_TO_DELIVERY + ","
						+ ShopStaff.MARK_ORDERS_DELIVERED + ","
						+ ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY + ","
						+ ShopStaff.ACCEPT_RETURNS + ""

						+ " FROM " + ShopStaff.TABLE_NAME;

//		if(staffID!=null)
//		{
//			query = query + " WHERE " + Staff.STAFF_ID + " = " + staffID;
//
//			isFirst = false;
//		}

		if(username!=null)
		{
			String queryPartUsername = ShopStaff.USER_NAME + " = ?";

			query = query + " WHERE " + queryPartUsername;

			isFirst = false;
		}


		if(password!=null)
		{
			String queryPartPassword = ShopStaff.PASSWORD + " = ?";

			if(isFirst)
			{
				query = query + " WHERE " + queryPartPassword;
			}
			else
			{
				query = query + " AND " + queryPartPassword;
			}
		}


//		logMessage(query);


		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;


		//Distributor distributor = null;
		ShopStaff shopStaff = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(query);

			int i = 0;

			if(username!=null)
			{
				statement.setObject(++i,username);
			}

			if(password!=null)
			{
				statement.setObject(++i,password);
			}


			rs = statement.executeQuery();

			while(rs.next())
			{
				shopStaff = new ShopStaff();

				shopStaff.setStaffID(rs.getInt(ShopStaff.STAFF_ID));
				shopStaff.setUsername(rs.getString(ShopStaff.USER_NAME));
				shopStaff.setPassword(rs.getString(ShopStaff.PASSWORD));
				shopStaff.setEnabled(rs.getBoolean(ShopStaff.IS_ENABLED));

				shopStaff.setShopID(rs.getInt(ShopStaff.SHOP_ID));

				shopStaff.setAddRemoveItemsFromShop(rs.getBoolean(ShopStaff.ADD_REMOVE_ITEMS_FROM_SHOP));
				shopStaff.setUpdateStock(rs.getBoolean(ShopStaff.UPDATE_STOCK));

				shopStaff.setCancelOrders(rs.getBoolean(ShopStaff.CANCEL_ORDERS));
				shopStaff.setConfirmOrders(rs.getBoolean(ShopStaff.CONFIRM_ORDERS));
				shopStaff.setSetOrdersPacked(rs.getBoolean(ShopStaff.SET_ORDERS_PACKED));
				shopStaff.setHandoverToDelivery(rs.getBoolean(ShopStaff.HANDOVER_TO_DELIVERY));
				shopStaff.setMarkOrdersDelivered(rs.getBoolean(ShopStaff.MARK_ORDERS_DELIVERED));
				shopStaff.setAcceptPaymentsFromDelivery(rs.getBoolean(ShopStaff.ACCEPT_PAYMENTS_FROM_DELIVERY));
				shopStaff.setAcceptReturns(rs.getBoolean(ShopStaff.ACCEPT_RETURNS));

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

		return shopStaff;
	}
}
