package chris_RSBot;

import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.ClientContext;

public class BankOres extends Task<ClientContext>
{
	private int ironIds = 436;
	private int copperIds = 440;
	private int tinIds = 438;
	private int rubyId = 1619;
	private int emeraldId =1621;
	public BankOres(ClientContext ctx) 
	{
		super(ctx);
	}

	public final int[] bankBoothId = {11748};
	
	
	private boolean rubyPresent(ClientContext ctx)
	{
		return !ctx.inventory.select().id(rubyId).isEmpty();
	}
	
	private boolean emeraldPresent(ClientContext ctx)
	{
		return !ctx.inventory.select().id(emeraldId).isEmpty();
	}
	
	public boolean activate()
	{
		return ctx.inventory.count() == 28 &&
				!ctx.objects.select().id(bankBoothId).within(5).isEmpty();				
	}
	
	public void execute()
	{
		
		boolean copper, iron, tin, rubyPresent, emeraldPresent;		
		rubyPresent = rubyPresent(ctx);
		emeraldPresent = emeraldPresent(ctx);
		GameObject bankBooths = ctx.objects.select().id(bankBoothId).shuffle().poll();
		copper = !ctx.inventory.select().id(copperIds).isEmpty();
		tin = !ctx.inventory.select().id(tinIds).isEmpty();
		iron = !ctx.inventory.select().id(ironIds).isEmpty();
		bankBooths.interact("Bank");
		
		try
		{
			Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
			if(copper)
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
