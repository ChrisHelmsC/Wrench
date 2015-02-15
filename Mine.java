package chris_RSBot;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;


public class Mine extends Task<ClientContext>
{
	public final int[] ironDepositId = {13446, 13444, 13445};		//Set deposit IDs
	public final int[] copperDepositId = {13450, 13451, 13452,};
	public final int[] tinDepositId = {13447, 13449, 13448};
	
	public Mine(ClientContext ctx)
	{
		super(ctx);
	}
	
	@Override
	//Activates if inventory is not full, there is a deposit nearby, 
	//the player is not currently animated(mining), and the player is not
	//currently in motion.
	public boolean activate()						
	{
		return ctx.inventory.select().count() < 28 
		&& !ctx.objects.select().id(copperDepositId).isEmpty() 
		&& ctx.players.local().animation() == -1
		&& !ctx.players.local().inMotion();
	}
	
	public void execute()
	{
		GameObject IronDeposit = ctx.objects.nearest().id(copperDepositId).poll();	//create a game object for the deposit
		if(IronDeposit.inViewport())												//Check if deposit is in viewport
		{
			IronDeposit.interact("Mine");											//Mine game object
			try
			{
				Thread.sleep(1000);													//Sleep
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
		}
		else																		//If object is not in view, walk and turn camera towards it
		{
			ctx.movement.step(IronDeposit);
			ctx.camera.turnTo(IronDeposit);
		}
		try
		{
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
		
	}
}
