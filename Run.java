package chris_RSBot;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name ="Mining", description ="Mines and banks in Varrock")

public class Run extends PollingScript<ClientContext>
{
	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
	public void start()
	{
		taskList.addAll(Arrays.asList(new WalkToMine(ctx), new Mine(ctx), new WalkToBank(ctx), new BankOres(ctx), new RunFromRat(ctx)));
	}
	
	@Override
	public void poll()
	{
		for(Task task : taskList)
		{
			if(task.activate())
			{
				task.execute();
			}
		}
	}
}