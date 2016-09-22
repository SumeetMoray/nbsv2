package org.nearbyshops.DAOsPrepared;

import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.ContractClasses.ShopContract;
import org.nearbyshops.ContractClasses.ShopItemContract;
import org.nearbyshops.Globals.Globals;
import org.nearbyshops.Model.Item;
import org.nearbyshops.Model.ItemCategory;
import org.nearbyshops.ModelEndPoints.ItemEndPoint;
import org.nearbyshops.ModelStats.ItemStats;
import org.nearbyshops.Utility.GeoLocation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemDAO {


	private HikariDataSource dataSource = Globals.getDataSource();



	private GeoLocation center;
	private GeoLocation[] minMaxArray;
	private GeoLocation pointOne;
	private GeoLocation pointTwo;


	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
		
	
	public int saveItem(Item item)
	{
		
		
		Connection connection = null;
		PreparedStatement statement = null;
		int idOfInsertedRow = 0;

		String insertItemCategory = "INSERT INTO " 
				+ Item.TABLE_NAME
				+ "("
				+ Item.ITEM_NAME + ","
				+ Item.ITEM_DESC + ","
				+ Item.ITEM_IMAGE_URL + ","

				+ Item.ITEM_CATEGORY_ID + ","
				+ Item.QUANTITY_UNIT + ","
				+ Item.ITEM_DESCRIPTION_LONG

				+ ") VALUES(?,?,? ,?,?,?)";
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(insertItemCategory,PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1,item.getItemName());
			statement.setString(2,item.getItemDescription());
			statement.setString(3,item.getItemImageURL());

			statement.setInt(4,item.getItemCategoryID());
			statement.setString(5,item.getQuantityUnit());
			statement.setString(6,item.getItemDescriptionLong());


			idOfInsertedRow = statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			
			if(rs.next())
			{
				idOfInsertedRow = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			
			try {
			
				if(statement!=null)
				{statement.close();}
			
			} 
			catch (SQLException e) {
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
		
		return idOfInsertedRow;
		
	}
	
	
	public int updateItem(Item item)
	{

		String updateStatement = "UPDATE " + Item.TABLE_NAME

				+ " SET "

				+ " " + Item.ITEM_NAME + " = ?,"
				+ " " + Item.ITEM_DESC + " = ?,"
				+ " " + Item.ITEM_IMAGE_URL + " = ?,"

				+ " " + Item.ITEM_CATEGORY_ID + " = ?,"
				+ " " + Item.QUANTITY_UNIT + " = ?,"
				+ " " + Item.ITEM_DESCRIPTION_LONG + " = ?"

				+ " WHERE " + Item.ITEM_ID + " = ?";


		Connection connection = null;
		PreparedStatement statement = null;
		
		int rowCountUpdated = 0;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(updateStatement);

			statement.setString(1,item.getItemName());
			statement.setString(2,item.getItemDescription());
			statement.setString(3,item.getItemImageURL());

			statement.setInt(4,item.getItemCategoryID());
			statement.setString(5,item.getQuantityUnit());
			statement.setString(6,item.getItemDescriptionLong());
			statement.setInt(7,item.getItemID());

			rowCountUpdated = statement.executeUpdate();
			System.out.println("Total rows updated: " + rowCountUpdated);	
			
			
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
		
		return rowCountUpdated;
	}
	
	
	
	public int deleteItem(int itemID)
	{

		String deleteStatement = "DELETE FROM " + Item.TABLE_NAME + " WHERE " + Item.ITEM_ID + " = ?";

		Connection connection= null;
		PreparedStatement statement = null;
		int rowCountDeleted = 0;
		try {
			
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(deleteStatement);
			statement.setInt(1,itemID);

			rowCountDeleted = statement.executeUpdate();
			
			System.out.println("Rows Deleted: " + rowCountDeleted);
			
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

		return rowCountDeleted;
	}

	
	
	public List<Item> readAllItems()
	{
		
		String query = "SELECT * FROM ITEM";
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next())
			{
				Item item = new Item();
				
				item.setItemID(rs.getInt("ITEM_ID"));
				item.setItemName(rs.getString("ITEM_NAME"));
				item.setItemDescription(rs.getString("ITEM_DESC"));
				item.setItemImageURL(rs.getString("ITEM_IMAGE_URL"));
				item.setItemCategoryID(rs.getInt("ITEM_CATEGORY_ID"));


				
				itemList.add(item);
				
				
			}
			
			
			
			System.out.println("Total item queried " + itemList.size());	
			
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

		
		return itemList;
		
	}






	public List<ItemStats> getStats(
			Integer itemCategoryID, Integer shopID,
			Double latCenter, Double lonCenter,
			Double deliveryRangeMin,Double deliveryRangeMax,
			Double proximity
	)
	{


		String queryJoin = "SELECT DISTINCT "
				+ "min(" + ShopItemContract.ITEM_PRICE + ") as min_price" + ","
				+ "max(" + ShopItemContract.ITEM_PRICE + ") as max_price" + ","
				+ "count(" + ShopItemContract.TABLE_NAME + "." + ShopItemContract.ITEM_ID + ") as shop_count" + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_ID
				+ " FROM "
				+ ShopContract.TABLE_NAME  + "," + ShopItemContract.TABLE_NAME + ","
				+ Item.TABLE_NAME + "," + ItemCategory.TABLE_NAME
				+ " WHERE "
				+ ShopContract.TABLE_NAME + "." + ShopContract.SHOP_ID
				+ "="
				+ ShopItemContract.TABLE_NAME + "." + ShopItemContract.SHOP_ID
				+ " AND "
				+ ShopItemContract.TABLE_NAME + "." + ShopItemContract.ITEM_ID
				+ "="
				+ Item.TABLE_NAME + "." + Item.ITEM_ID
				+ " AND "
				+ Item.TABLE_NAME + "." + Item.ITEM_CATEGORY_ID
				+ "="
				+ ItemCategory.TABLE_NAME + "." + ItemCategory.ITEM_CATEGORY_ID;



		if(shopID != null)
		{
			queryJoin = queryJoin + " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.SHOP_ID + " = " + shopID;

		}

		if(itemCategoryID != null)
		{
			queryJoin = queryJoin + " AND "
					+ Item.TABLE_NAME
					+ "."
					+ Item.ITEM_CATEGORY_ID + " = " + itemCategoryID;

		}



		// Applying filters


		if(latCenter != null && latCenter != null)
		{
			// Applying shop visibility filter. Gives all the shops which are visible at the given location defined by
			// latCenter and lonCenter. For more information see the API documentation.

			queryJoin = queryJoin
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_MAX
					+ " >= " + latCenter
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_MIN
					+ " <= " + latCenter
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_MAX
					+ " >= " + lonCenter
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_MIN
					+ " <= " + lonCenter;

			//+ " BETWEEN " + latmax + " AND " + latmin;
		}



		if(deliveryRangeMin !=null||deliveryRangeMax!=null){

			// apply delivery range filter

			queryJoin = queryJoin
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.DELIVERY_RANGE
					+ " BETWEEN " + deliveryRangeMin + " AND " + deliveryRangeMax;

			//+ " <= " + deliveryRange;
		}




		// proximity cannot be greater than the delivery range if the delivery range is supplied. Otherwise this condition is
		// not required.
		if(proximity !=null && (deliveryRangeMax== null || (deliveryRangeMax !=null && proximity <= deliveryRangeMax)))
		{
			// generate bounding coordinates for the shop based on the required location and its

			center = GeoLocation.fromDegrees(latCenter,lonCenter);
			minMaxArray = center.boundingCoordinates(proximity,6371.01);

			pointOne = minMaxArray[0];
			pointTwo = minMaxArray[1];

			double latMin = pointOne.getLatitudeInDegrees();
			double lonMin = pointOne.getLongitudeInDegrees();
			double latMax = pointTwo.getLatitudeInDegrees();
			double lonMax = pointTwo.getLongitudeInDegrees();


			// Make sure that shop center lies between the bounding coordinates generated by proximity bounding box

			queryJoin = queryJoin

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_CENTER
					+ " < " + latMax

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_CENTER
					+ " > " + latMin

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_CENTER
					+ " < " + lonMax

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_CENTER
					+ " > " + lonMin;
		}


		queryJoin = queryJoin + " group by " + ShopItemContract.TABLE_NAME + "." + ShopItemContract.ITEM_ID;


		//
		/*

		Applying filters Ends

		 */



		ArrayList<ItemStats> itemStatsList = new ArrayList<>();


		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {

			connection = dataSource.getConnection();
			statement = connection.createStatement();

			rs = statement.executeQuery(queryJoin);

			while(rs.next())
			{
				ItemStats itemStats = new ItemStats();

				itemStats.setMax_price(rs.getDouble("max_price"));
				itemStats.setMin_price(rs.getDouble("min_price"));
				itemStats.setShopCount(rs.getInt("shop_count"));
				itemStats.setItemID(rs.getInt("ITEM_ID"));

				itemStatsList.add(itemStats);
			}



			//System.out.println("Item By CategoryID " + itemList.size());

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


		return itemStatsList;

	}
	
	

	
	public List<Item> getItems(
					Integer itemCategoryID, Integer shopID,
					Double latCenter, Double lonCenter,
					Double deliveryRangeMin,Double deliveryRangeMax,
					Double proximity,
					String sortBy,
					Integer limit, Integer offset
	) {
		String query = "";
		
		String queryNormal = "SELECT * FROM " + Item.TABLE_NAME;
		
		
		String queryJoin = "SELECT "
				+ "min(" + ShopItemContract.ITEM_PRICE + ") as min_price" + ","
				+ "max(" + ShopItemContract.ITEM_PRICE + ") as max_price" + ","
				+ "count(" + ShopItemContract.TABLE_NAME + "." + ShopItemContract.ITEM_ID + ") as shop_count" + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_CATEGORY_ID + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_ID + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_IMAGE_URL + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_NAME + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_DESC + ","

				+ Item.TABLE_NAME + "." + Item.QUANTITY_UNIT + ","
				+ Item.TABLE_NAME + "." + Item.DATE_TIME_CREATED + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_DESCRIPTION_LONG + ""

				+ " FROM " 
				+ ShopContract.TABLE_NAME  + "," + ShopItemContract.TABLE_NAME + "," 
				+ Item.TABLE_NAME + "," + ItemCategory.TABLE_NAME
				+ " WHERE " 
				+ ShopContract.TABLE_NAME + "." + ShopContract.SHOP_ID 
				+ "="
				+ ShopItemContract.TABLE_NAME + "." + ShopItemContract.SHOP_ID
				+ " AND "
				+ ShopItemContract.TABLE_NAME + "." + ShopItemContract.ITEM_ID
				+ "="
				+ Item.TABLE_NAME + "." + Item.ITEM_ID
				+ " AND "
				+ Item.TABLE_NAME + "." + Item.ITEM_CATEGORY_ID
				+ "="
				+ ItemCategory.TABLE_NAME + "." + ItemCategory.ITEM_CATEGORY_ID;
		
		

		if(shopID != null)
		{
				queryJoin = queryJoin + " AND "
						+ ShopContract.TABLE_NAME 
						+ "."
						+ ShopContract.SHOP_ID + " = " + shopID; 	
			
		}
		
		if(itemCategoryID != null)
		{
			queryJoin = queryJoin + " AND "
					+ Item.TABLE_NAME
					+ "."
					+ Item.ITEM_CATEGORY_ID + " = " + itemCategoryID;
			
			
			
			//" WHERE ITEM_CATEGORY_ID = " + itemCategoryID
			
			queryNormal = queryNormal + " WHERE "
					+ Item.TABLE_NAME
					+ "."
					+ Item.ITEM_CATEGORY_ID + " = " + itemCategoryID;
			
		}



		// Applying filters

		if(latCenter !=null && lonCenter !=null)
		{
			// Applying shop visibility filter. Gives all the shops which are visible at the given location defined by
			// latCenter and lonCenter. For more information see the API documentation.


			String queryPartLatLonBounding = "";

			queryPartLatLonBounding = queryPartLatLonBounding
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_MAX
					+ " >= " + latCenter
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_MIN
					+ " <= " + latCenter
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_MAX
					+ " >= " + lonCenter
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_MIN
					+ " <= " + lonCenter;

			//+ " BETWEEN " + latmax + " AND " + latmin;

			String queryPartlatLonCenter = "";

			queryPartlatLonCenter = queryPartlatLonCenter + " 6371.01 * acos( cos( radians("
					+ latCenter + ")) * cos( radians( lat_center) ) * cos(radians( lon_center ) - radians("
					+ lonCenter + "))"
					+ " + sin( radians(" + latCenter + ")) * sin(radians(lat_center))) <= delivery_range ";



			queryJoin = queryJoin + " AND " + queryPartlatLonCenter;


		}



		if(deliveryRangeMin !=null  ||deliveryRangeMax !=null){

			// apply delivery range filter

			queryJoin = queryJoin
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.DELIVERY_RANGE
					+ " BETWEEN " + deliveryRangeMin + " AND " + deliveryRangeMax;

			//+ " <= " + deliveryRange;
		}




		// proximity cannot be greater than the delivery range if the delivery range is supplied. Otherwise this condition is
		// not required.
		if(proximity !=null)
		{



			//proximity !=null && (deliveryRangeMax == null || (deliveryRangeMax !=null && proximity <= deliveryRangeMax))



			// generate bounding coordinates for the shop based on the required location and its
			center = GeoLocation.fromDegrees(latCenter,lonCenter);
			minMaxArray = center.boundingCoordinates(proximity,6371.01);

			pointOne = minMaxArray[0];
			pointTwo = minMaxArray[1];

			double latMin = pointOne.getLatitudeInDegrees();
			double lonMin = pointOne.getLongitudeInDegrees();
			double latMax = pointTwo.getLatitudeInDegrees();
			double lonMax = pointTwo.getLongitudeInDegrees();


			// Make sure that shop center lies between the bounding coordinates generated by proximity bounding box

			String queryPartProximityBounding = "";

			queryPartProximityBounding = queryPartProximityBounding

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_CENTER
					+ " < " + latMax

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_CENTER
					+ " > " + latMin

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_CENTER
					+ " < " + lonMax

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_CENTER
					+ " > " + lonMin;



			String queryPartProximity = "";

			// filter using Haversine formula using SQL math functions
			queryPartProximity = queryPartProximity
					+ " (6371.01 * acos(cos( radians("
					+ latCenter
					+ ")) * cos( radians("
					+ ShopContract.LAT_CENTER
					+ " )) * cos(radians( "
					+ ShopContract.LON_CENTER
					+ ") - radians("
					+ lonCenter
					+ "))"
					+ " + sin( radians("
					+ latCenter
					+ ")) * sin(radians("
					+ ShopContract.LAT_CENTER
					+ ")))) <= "
					+ proximity ;


			queryJoin = queryJoin + " AND " + queryPartProximity;


		}


		// all the non-aggregate columns which are present in select must be present in group by also.
		queryJoin = queryJoin

				+ " group by "
				+ Item.TABLE_NAME + "." + Item.ITEM_CATEGORY_ID + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_ID + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_IMAGE_URL + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_NAME + ","
				+ Item.TABLE_NAME + "." + Item.ITEM_DESC;


		// + ShopItemContract.TABLE_NAME + "." + ShopItemContract.ITEM_ID
		//





		if(sortBy!=null)
		{
			if(!sortBy.equals(""))
			{
				String queryPartSortBy = " ORDER BY " + sortBy;

				queryNormal = queryNormal + queryPartSortBy;
				queryJoin = queryJoin + queryPartSortBy;
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
			queryJoin = queryJoin + queryPartLimitOffset;
		}






		/*

		Applying filters Ends

		 */





		boolean isJoinQuery = false;

		if(shopID != null || (latCenter!= null && lonCenter!=null))
		{
			query = queryJoin;
			isJoinQuery = true;

		}else
		{
			query = queryNormal;
		}
		
		
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next())
			{
				Item item = new Item();

				item.setItemID(rs.getInt(Item.ITEM_ID));
				item.setItemName(rs.getString(Item.ITEM_NAME));
				item.setItemDescription(rs.getString(Item.ITEM_DESC));

				item.setItemImageURL(rs.getString(Item.ITEM_IMAGE_URL));
				item.setItemCategoryID(rs.getInt(Item.ITEM_CATEGORY_ID));

				item.setItemDescriptionLong(rs.getString(Item.ITEM_DESCRIPTION_LONG));
				item.setDateTimeCreated(rs.getTimestamp(Item.DATE_TIME_CREATED));
				item.setQuantityUnit(rs.getString(Item.QUANTITY_UNIT));


				if(isJoinQuery)
				{
					ItemStats itemStats = new ItemStats();
					itemStats.setMax_price(rs.getDouble("max_price"));
					itemStats.setMin_price(rs.getDouble("min_price"));
					itemStats.setShopCount(rs.getInt("shop_count"));
					item.setItemStats(itemStats);
				}

				itemList.add(item);
			}
			
			
			
			System.out.println("Item By CategoryID " + itemList.size());	
			
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

		return itemList;
	}



	public ItemEndPoint getEndPointMetadata(
			Integer itemCategoryID, Integer shopID,
			Double latCenter, Double lonCenter,
			Double deliveryRangeMin,Double deliveryRangeMax,
			Double proximity)
	{


		String query = "";

		String queryNormal = "SELECT "
				+ "count( DISTINCT " + Item.ITEM_ID + ") as item_count" + ""
				+ " FROM " + Item.TABLE_NAME;


		String queryJoin = "SELECT "

				+ "count( DISTINCT " + Item.TABLE_NAME + "." + Item.ITEM_ID + ") as item_count" + ""

				+ " FROM "
				+ ShopContract.TABLE_NAME  + "," + ShopItemContract.TABLE_NAME + ","
				+ Item.TABLE_NAME + "," + ItemCategory.TABLE_NAME
				+ " WHERE "
				+ ShopContract.TABLE_NAME + "." + ShopContract.SHOP_ID
				+ "="
				+ ShopItemContract.TABLE_NAME + "." + ShopItemContract.SHOP_ID
				+ " AND "
				+ ShopItemContract.TABLE_NAME + "." + ShopItemContract.ITEM_ID
				+ "="
				+ Item.TABLE_NAME + "." + Item.ITEM_ID
				+ " AND "
				+ Item.TABLE_NAME + "." + Item.ITEM_CATEGORY_ID
				+ "="
				+ ItemCategory.TABLE_NAME + "." + ItemCategory.ITEM_CATEGORY_ID;



		if(shopID != null)
		{
			queryJoin = queryJoin + " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.SHOP_ID + " = " + shopID;

		}

		if(itemCategoryID != null)
		{
			queryJoin = queryJoin + " AND "
					+ Item.TABLE_NAME
					+ "."
					+ Item.ITEM_CATEGORY_ID + " = " + itemCategoryID;



			//" WHERE ITEM_CATEGORY_ID = " + itemCategoryID

			queryNormal = queryNormal + " WHERE "
					+ Item.TABLE_NAME
					+ "."
					+ Item.ITEM_CATEGORY_ID + " = " + itemCategoryID;

		}



		// Applying filters

		if(latCenter !=null && lonCenter !=null)
		{
			// Applying shop visibility filter. Gives all the shops which are visible at the given location defined by
			// latCenter and lonCenter. For more information see the API documentation.


			/*
			*
			* Caution : Do not delete the commented out bounding code. It might / may be useful in future.
			*
			* */

//			String queryPartLatLonBounding = "";
//
//			queryPartLatLonBounding = queryPartLatLonBounding
//					+ " AND "
//					+ ShopContract.TABLE_NAME
//					+ "."
//					+ ShopContract.LAT_MAX
//					+ " >= " + latCenter
//					+ " AND "
//					+ ShopContract.TABLE_NAME
//					+ "."
//					+ ShopContract.LAT_MIN
//					+ " <= " + latCenter
//					+ " AND "
//					+ ShopContract.TABLE_NAME
//					+ "."
//					+ ShopContract.LON_MAX
//					+ " >= " + lonCenter
//					+ " AND "
//					+ ShopContract.TABLE_NAME
//					+ "."
//					+ ShopContract.LON_MIN
//					+ " <= " + lonCenter;

			//+ " BETWEEN " + latmax + " AND " + latmin;

			String queryPartlatLonCenter = "";

			queryPartlatLonCenter = queryPartlatLonCenter + " 6371.01 * acos( cos( radians("
					+ latCenter + ")) * cos( radians( lat_center) ) * cos(radians( lon_center ) - radians("
					+ lonCenter + "))"
					+ " + sin( radians(" + latCenter + ")) * sin(radians(lat_center))) <= delivery_range ";



			queryJoin = queryJoin + " AND " + queryPartlatLonCenter;


		}



		if(deliveryRangeMin !=null  ||deliveryRangeMax !=null){

			// apply delivery range filter

			queryJoin = queryJoin
					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.DELIVERY_RANGE
					+ " BETWEEN " + deliveryRangeMin + " AND " + deliveryRangeMax;

			//+ " <= " + deliveryRange;
		}




		// proximity cannot be greater than the delivery range if the delivery range is supplied. Otherwise this condition is
		// not required.
		if(proximity !=null)
		{



			//proximity !=null && (deliveryRangeMax == null || (deliveryRangeMax !=null && proximity <= deliveryRangeMax))



			/*// generate bounding coordinates for the shop based on the required location and its
			center = GeoLocation.fromDegrees(latCenter,lonCenter);
			minMaxArray = center.boundingCoordinates(proximity,6371.01);

			pointOne = minMaxArray[0];
			pointTwo = minMaxArray[1];

			double latMin = pointOne.getLatitudeInDegrees();
			double lonMin = pointOne.getLongitudeInDegrees();
			double latMax = pointTwo.getLatitudeInDegrees();
			double lonMax = pointTwo.getLongitudeInDegrees();


			// Make sure that shop center lies between the bounding coordinates generated by proximity bounding box

			String queryPartProximityBounding = "";

			queryPartProximityBounding = queryPartProximityBounding

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_CENTER
					+ " < " + latMax

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LAT_CENTER
					+ " > " + latMin

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_CENTER
					+ " < " + lonMax

					+ " AND "
					+ ShopContract.TABLE_NAME
					+ "."
					+ ShopContract.LON_CENTER
					+ " > " + lonMin;
*/


			String queryPartProximity = "";

			// filter using Haversine formula using SQL math functions
			queryPartProximity = queryPartProximity
					+ " (6371.01 * acos(cos( radians("
					+ latCenter
					+ ")) * cos( radians("
					+ ShopContract.LAT_CENTER
					+ " )) * cos(radians( "
					+ ShopContract.LON_CENTER
					+ ") - radians("
					+ lonCenter
					+ "))"
					+ " + sin( radians("
					+ latCenter
					+ ")) * sin(radians("
					+ ShopContract.LAT_CENTER
					+ ")))) <= "
					+ proximity ;


			queryJoin = queryJoin + " AND " + queryPartProximity;


		}



		/*

		Applying filters Ends

		 */





//		boolean isJoinQuery = false;

		if(shopID != null || (latCenter!= null && lonCenter!=null))
		{
			query = queryJoin;
//			isJoinQuery = true;

		}else
		{
			query = queryNormal;
		}



//		ArrayList<Item> itemList = new ArrayList<Item>();


		ItemEndPoint endPoint = new ItemEndPoint();


		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next())
			{
				endPoint.setItemCount(rs.getInt("item_count"));
			}

			System.out.println("Item Count : " + endPoint.getItemCount());

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

		return endPoint;
	}
	
			
	public Item getItem(int ItemID)
	{
		
		String query = "SELECT * FROM " +  Item.TABLE_NAME
				+ " WHERE " + Item.ITEM_ID + " = " + ItemID;
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		
		//ItemCategory itemCategory = new ItemCategory();
		Item item = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			
			rs = statement.executeQuery(query);
			
			
			
			while(rs.next())
			{
				item = new Item();
				item.setItemID(rs.getInt(Item.ITEM_ID));
				item.setItemName(rs.getString(Item.ITEM_NAME));
				item.setItemDescription(rs.getString(Item.ITEM_DESC));

				item.setItemImageURL(rs.getString(Item.ITEM_IMAGE_URL));
				item.setItemCategoryID(rs.getInt(Item.ITEM_CATEGORY_ID));

				item.setItemDescriptionLong(rs.getString(Item.ITEM_DESCRIPTION_LONG));
				item.setDateTimeCreated(rs.getTimestamp(Item.DATE_TIME_CREATED));
				item.setQuantityUnit(rs.getString(Item.QUANTITY_UNIT));
			
				System.out.println("Get Item by ID : " + item.getItemID());
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

		return item;
	}
	
}