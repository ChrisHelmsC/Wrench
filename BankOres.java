package chris_RSBot;

import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Widget;

public class BankOres extends Task<ClientContext>
{
	private int ironIds = 436;												//Identify Items
	private int copperIds = 440;
	private int tinIds = 438;
	private int rubyId = 1619;
	private int emeraldId =1621;
	private int sapphireId = 1623;
	private int diamondId = 1617;
	
	private int totalOres = 0, totalGems = 0, miningLevel;					//Hold inventory/bank info
	public final int[] bankBoothId = {11748};								//Stores bankBooth
	
	public BankOres(ClientContext ctx) 
	{
		super(ctx);
	}

	//Returns true if ruby is in inventory
	private boolean rubyPresent(ClientContext ctx)	
	{
		return !ctx.inventory.select().id(rubyId).isEmpty();
	}
	
	//returns true if emerald is in inventory;
	private boolean emeraldPresent(ClientContext ctx)
	{
		return !ctx.inventory.select().id(emeraldId).isEmpty();
	}
	
	//returns true if sapphire is in inventory;
	public boolean sapphirePresent(ClientContext ctx)
	{
		return !ctx.inventory.select().id(sapphireId).isEmpty();
	}
	
	//returns true if diamond is in inventory;
	public boolean diamondPresent(ClientContext ctx)
	{
		return !ctx.inventory.select().id(sapphireId).isEmpty();
	}
	
	//Add ores in inventory to total count;
	public int addInventoryOres(int currentOres, ClientContext ctx)
	{
		return currentOres + ctx.inventory.select().id(copperIds).count() 
				+ ctx.inventory.select().id(tinIds).count()
				 + ctx.inventory.select().id(ironIds).count();
	}
	
	//add gems in inventory to total count;
	public int addInventoryGems(int currentGems, ClientContext ctx)
	{
		return currentGems + ctx.inventory.select().id(sapphireId).count()
				+ ctx.inventory.select().id(rubyId).count()
				+ctx.inventory.select().id(emeraldId).count()
				+ ctx.inventory.select().id(diamondId).count();
	}
	
	//add ores in bank to total ores
	public int addBankOres(int currentOres, ClientContext ctx)
	{
		return currentOres + ctx.bank.select().id(tinIds).poll().stackSize()
				+ ctx.bank.select().id(ironIds).poll().stackSize()
				+ ctx.bank.select().id(copperIds).poll().stackSize();
	}
	
	//add gems in bank to total gems
	public int addBankGems(int currentGems, ClientContext ctx)
	{
		return currentGems +  ctx.bank.select().id(rubyId).poll().stackSize()
				+ ctx.bank.select().id(emeraldId).poll().stackSize()
				+ ctx.bank.select().id(sapphireId).poll().stackSize()
				+ ctx.bank.select().id(diamondId).poll().stackSize();
	}
	
	//returns true if inventory is full and there is a nearby bank booth
	public boolean activate()													
	{
		return ctx.inventory.count() == 28 &&
				!ctx.objects.select().id(bankBoothId).within(5).isEmpty();				
	}
	
	public void execute()
	{
		
		boolean copper, iron, tin, rubyPresent, emeraldPresent, sapphirePresent, diamondPresent;	//Create Booleans for ores and gems in inventory
	
		rubyPresent = rubyPresent(ctx);																//Set inventory booleans;
		emeraldPresent = emeraldPresent(ctx);
		sapphirePresent = sapphirePresent(ctx);
		diamondPresent = diamondPresent(ctx);
		copper = !ctx.inventory.select().id(copperIds).isEmpty();
		tin = !ctx.inventory.select().id(tinIds).isEmpty();
		iron = !ctx.inventory.select().id(ironIds).isEmpty();
		Component widget = ctx.widgets.widget(320).component(17).component(4);						//Locate mining level widget

		miningLevel = Integer.parseInt(widget.text());												//set miningLevel to value returned from widget
		
		GameObject bankBooths = ctx.objects.select().id(bankBoothId).shuffle().poll();				//Locate random BankBooth, set equal to bankBooths
		
		bankBooths.interact("Bank");																//Interact with bankBooth
		
		try
		{
			Thread.sleep(2300);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
		if(ctx.bank.opened())
		{
			totalOres = addInventoryOres(totalOres, ctx);											//Get totalOres and totalGems in inventory
			totalGems = addInventoryGems(totalGems, ctx);
			
				if(copper)																			//Deposit each ore and gem in inventory, currently not working
				{
					ctx.bank.deposit(copperIds, 28);
				}
				
				if(tin)
				{
					ctx.bank.deposit(tinIds, 28);
				}
				
				if(iron)
				{
					ctx.bank.deposit(ironIds, 28);
					
				}
				if(emeraldPresent)
				{
					ctx.bank.deposit(emeraldId, 20);
				}
				if(rubyPresent)
				{
					ctx.bank.deposit(rubyId, 20);
				}
				if(sapphirePresent)
				{
					ctx.bank.deposit(sapphireId, 20);
				}
				if(diamondPresent)
				{
					ctx.bank.deposit(diamondId, 20);
				}
				
				System.out.printf("\n\nTotal Ores: %d\nTotal Gems: %d\nMining Level: %d", totalOres, totalGems, miningLevel);	//Print info to console		
				
				try
				{
					Thread.sleep(300);
				}
				catch(InterruptedException ex)
				{
					Thread.currentThread().interrupt();
				}
		}
	}
}
