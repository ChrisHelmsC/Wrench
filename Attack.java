package chris_RSBot;
import java.util.Iterator;

import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.ClientContext;

public class Attack extends Task<ClientContext>
{
	int[] ChickenIds = {2692, 2693, 2819, 2820, 2821};
	int[] FeatherIds = {314};
	int[] cowIds = {2805, 2806,2808, 2807, 2809};
	
	public Attack(ClientContext ctx)
	{
		super(ctx);
	}
	
	public boolean activate()
	{
		return !ctx.npcs.select().id(ChickenIds).isEmpty() 
				&& ctx.players.local().animation() ==-1
				&& !ctx.players.local().inCombat()
				&& !ctx.players.local().inMotion();
	}
	
	public void execute()
	{
		Iterator<Npc> chicken = ctx.npcs.select().id(ChickenIds).within(4).iterator();
		Npc killChicken;
		
		boolean found = false;
		
		while(!found&&chicken.hasNext())
		{
			killChicken = chicken.next();
			if(!killChicken.inCombat()&&!ctx.players.local().inCombat())
			{
				if(killChicken.inViewport())
				{
					killChicken.interact("Attack");
					try
					{
						Thread.sleep(3500);
					}
					catch(InterruptedException ex)
					{
						Thread.currentThread().interrupt();
					}
					found = true;
				}
				else
				{
					ctx.movement.step(killChicken);
					ctx.camera.turnTo(killChicken);
				}
			}
		}
	}
}
