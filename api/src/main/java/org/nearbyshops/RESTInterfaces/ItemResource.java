package org.nearbyshops.RESTInterfaces;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.nearbyshops.Globals.Globals;
import org.nearbyshops.Model.Item;
import org.nearbyshops.Model.ItemCategory;


@Path("/Item")
public class ItemResource {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveItem(Item item)
	{	
		int idOfInsertedRow = Globals.itemService.saveItem(item);
		
		item.setItemID(idOfInsertedRow);
		
		if(idOfInsertedRow >=1)
		{
			Response response = Response.status(Status.CREATED)
					.location(URI.create("/api/Item/" + idOfInsertedRow))
					.entity(item)
					.build();
			
			return response;
			
		}else if(idOfInsertedRow <= 0)
		{
			Response response = Response.status(Status.NOT_MODIFIED)
					.entity(null)
					.build();
			
			return response;
		}
		
		
		return null;
	}
	
	
	@PUT
	@Path("/{ItemID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItem(Item item, @PathParam("ItemID")int itemID)
	{
			
		item.setItemID(itemID);
	
		//System.out.println("ItemCategoryID: " + itemCategoryID + " " + itemCategory.getCategoryName()
		//+ " " + itemCategory.getCategoryDescription());
		
		int rowCount = Globals.itemService.updateItem(item); 
		
		if(rowCount >= 1)
		{
			Response response = Response.status(Status.OK)
					.entity(null)
					.build();
			
			return response;
		}
		if(rowCount == 0)
		{
			Response response = Response.status(Status.NOT_MODIFIED)
					.entity(null)
					.build();
			
			return response;
		}

		return null;

	}



	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItemBulk(List<Item> itemList)
	{
		int rowCountSum = 0;

		for(Item item : itemList)
		{
			rowCountSum = rowCountSum + Globals.itemService.updateItem(item);
		}

		if(rowCountSum ==  itemList.size())
		{
			Response response = Response.status(Status.OK)
					.entity(null)
					.build();

			return response;
		}
		else if( rowCountSum < itemList.size() && rowCountSum > 0)
		{
			Response response = Response.status(Status.PARTIAL_CONTENT)
					.entity(null)
					.build();

			return response;
		}
		else if(rowCountSum == 0 ) {

			Response response = Response.status(Status.NOT_MODIFIED)
					.entity(null)
					.build();

			return response;
		}

		return null;
	}
	
	
	@DELETE
	@Path("/{ItemID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("ItemID")int itemID)
	{
		
		int rowCount = Globals.itemService.deleteItem(itemID);
		
		
		if(rowCount>=1)
		{
			Response response = Response.status(Status.OK)
					.entity(null)
					.build();
			
			return response;
		}
		
		if(rowCount == 0)
		{
			Response response = Response.status(Status.NOT_MODIFIED)
					.entity(null)
					.build();
			
			return response;
		}
		
		return null;
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems(
			@QueryParam("ItemCategoryID")Integer itemCategoryID,
			@QueryParam("ShopID")Integer shopID,
			@QueryParam("latCenter") Double latCenter, @QueryParam("lonCenter") Double lonCenter,
			@QueryParam("deliveryRangeMax")Double deliveryRangeMax,
			@QueryParam("deliveryRangeMin")Double deliveryRangeMin,
			@QueryParam("proximity")Double proximity,
			@QueryParam("SortBy") String sortBy,
			@QueryParam("Limit")Integer limit, @QueryParam("Offset")Integer offset)
	
	{
				
		//Marker
		
		List<Item> list = 
				Globals.itemService.getItems(
						itemCategoryID,
						shopID,
						latCenter, lonCenter,
						deliveryRangeMin,
						deliveryRangeMax,
						proximity,
						sortBy,limit,offset
				);
		
		
		GenericEntity<List<Item>> listEntity = new GenericEntity<List<Item>>(list){
			
			};
		
			
			if(list.size()<=0)
			{
				Response response = Response.status(Status.NO_CONTENT)
						.entity(listEntity)
						.build();
				
				return response;
				
			}else
			{
				Response response = Response.status(Status.OK)
						.entity(listEntity)
						.build();
				
				return response;
			}	
	}
	
	
	@GET
	@Path("/{ItemID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItem(@PathParam("ItemID")int itemID)
	{
		System.out.println("itemID=" + itemID);
		 
		
		//marker
		
		Item item = Globals.itemService.getItem(itemID);
		
		if(item!= null)
		{
			Response response = Response.status(Status.OK)
			.entity(item)
			.build();
			
			return response;
			
		} else 
		{
			
			Response response = Response.status(Status.NO_CONTENT)
					.entity(item)
					.build();
			
			return response;
			
		}
	}
	
}
