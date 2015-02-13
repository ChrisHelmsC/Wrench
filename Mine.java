package chris_RSBot;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;


public class Mine extends Task<ClientContext>
{
	public final int[] ironDepositId = {13446, 13444, 13445};
	public final int[] copperDepositId = {13450, 13451, 13452, 134447, 13449, 13448};
	public final int[] tinDepositId = {134447, 13449, 13448};
	
	public Mine(ClientContext ctx)
	{
		super(ctx);
	}
	
	@Override
	public boolean activate()
	{
		return ctx.inventory.select().count() < 28 
		&& !ctx.objects.select().id(copperDepositId).isEmpty() 
		&& ctx.players.local().animation() == -1
		&& !ctx.players.local().inMotion();
	}
	
	public void execute()
	{
		GameObject IronDeposit = ctx.objects.nearest().id(copperDepositId).poll();
		if(IronDeposit.inViewport())
		{
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
			IronDeposit.interact("Mine");
			try
			{
				Thread.sleep(1400);
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
		}
		else
		{
			ctx.movement.step(IronDeposit);
			ctx.camera.turnTo(IronDeposit);
		}
	}
}
