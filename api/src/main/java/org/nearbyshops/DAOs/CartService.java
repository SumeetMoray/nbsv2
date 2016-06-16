package org.nearbyshops.DAOs;

import org.nearbyshops.ContractClasses.CartContract;
import org.nearbyshops.ContractClasses.JDBCContract;
import org.nearbyshops.Model.Cart;

import java.sql.*;
import java.util.ArrayList;


public class CartService {

	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();	
	}
	



	
	public int saveCart(Cart cart)
	{

		Connection conn = null;
		Statement stmt = null;
		int rowIdOfInsertedRow = -1;

		String insertItemCategory = "INSERT INTO "
				+ CartContract.TABLE_NAME
				+ "("  
				+ CartContract.SHOP_ID + ","
				+ CartContract.END_USER_ID + ""
				+ " ) VALUES ( "
				+ "" + cart.getShopID()	+ ","
				+ "" + cart.getEndUserID() + ""
				+ ")";
		
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
	

	public int updateCart(Cart cart)
	{	
		String updateStatement = "UPDATE " + CartContract.TABLE_NAME
				+ " SET "
				+ CartContract.END_USER_ID + " = "
				+ "" + cart.getEndUserID() + ""
				+ ","
				+ "" + CartContract.SHOP_ID + " = "
				+ "" + cart.getShopID() + ""
				+ " WHERE " + CartContract.CART_ID + " = "
				+ cart.getCartID();
		
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
	

	public int deleteCart(int cartID)
	{
		
		String deleteStatement = "DELETE FROM " + CartContract.TABLE_NAME + " WHERE " + CartContract.CART_ID + " = "
				+ cartID;
		
		
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
	
	
	
	
	
	public ArrayList<Cart> readCarts(int endUserID,int shopID)
	{
		String query = "SELECT * FROM " + CartContract.TABLE_NAME;

		boolean isFirst = true;

		if(endUserID > 0)
		{
			query = query + " WHERE " + CartContract.END_USER_ID + " = " + endUserID;

			isFirst = false;
		}

		if(shopID > 0 )
		{
			if(isFirst)
			{
				query = query + " WHERE " + CartContract.SHOP_ID + " = " + shopID;

			}else
			{
				query = query + " AND " + CartContract.SHOP_ID + " = " + shopID;

			}

		}



		ArrayList<Cart> cartsList = new ArrayList<Cart>();
		
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

				Cart cart = new Cart();

				cart.setCartID(rs.getInt(CartContract.CART_ID));
				cart.setEndUserID(rs.getInt(CartContract.END_USER_ID));
				cart.setShopID(rs.getInt(CartContract.SHOP_ID));

				cartsList.add(cart);
				
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
		
								
		return cartsList;
	}

	
	public Cart readCart(int cartID)
	{
		
		String query = "SELECT * FROM " + CartContract.TABLE_NAME
						+ " WHERE " + CartContract.CART_ID + " = " + cartID;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Cart cart = null;
		
		try {
			
			conn = DriverManager.getConnection(JDBCContract.CURRENT_CONNECTION_URL,
					JDBCContract.CURRENT_USERNAME,JDBCContract.CURRENT_PASSWORD);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				cart = new Cart();
				cart.setCartID(rs.getInt(CartContract.CART_ID));
				cart.setShopID(rs.getInt(CartContract.SHOP_ID));
				cart.setEndUserID(rs.getInt(CartContract.END_USER_ID));
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
	
		return cart;
	}	
}