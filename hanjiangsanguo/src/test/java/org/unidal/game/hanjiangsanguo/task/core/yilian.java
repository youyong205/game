package org.unidal.game.hanjiangsanguo.task.core;

import org.junit.Before;
import org.junit.Test;
import org.unidal.game.hanjiangsanguo.task.TaskDriver;
import org.unidal.game.hanjiangsanguo.task.activity.GemActivity;
import org.unidal.game.hanjiangsanguo.task.activity.HeavenActivity;
import org.unidal.game.hanjiangsanguo.task.activity.HeroActivity;
import org.unidal.game.hanjiangsanguo.task.activity.LoginActivity;
import org.unidal.game.hanjiangsanguo.task.activity.SoulActivity;
import org.unidal.game.hanjiangsanguo.task.activity.WorldbossActivity;
import org.unidal.lookup.ComponentTestCase;

public class yilian extends ComponentTestCase {
	static {
		System.setProperty("devMode", "true");
	}

	@Before
	public void before() throws Exception {
		TaskDriver driver = lookup(TaskDriver.class);

		driver.setup("7434081", "7434081", "107", //
		      "practice/gid", "81151", //
		      "general/gid", "81151", "general/id", "106", //
		      "worldboss/list", "-2,60009,-2,-2,43887,-2,136825,128609,66097", "worldboss/mid", "10" //
		);

		driver.go(LoginActivity.ID, "107", "7434081", "7434081");
	}

	@Test
	public void testSoul() throws Exception {
		TaskDriver driver = lookup(TaskDriver.class);
		driver.go(SoulActivity.ID, "150");
	}

	@Test
	public void testGem() throws Exception {
		TaskDriver driver = lookup(TaskDriver.class);
		driver.go(GemActivity.ID, "4", "149");
	}

	@Test
	public void testHeavey() throws Exception {
		TaskDriver driver = lookup(TaskDriver.class);
		driver.go(HeavenActivity.ID, "44");
	}

	@Test
	public void testHero() throws Exception {
		TaskDriver driver = lookup(TaskDriver.class);
		driver.go(HeroActivity.ID);
	}

	@Test
	public void nop() throws Exception {

		TaskDriver driver = lookup(TaskDriver.class);

		driver.go(LoginActivity.ID, "107", "SUPERWYX", "wyx1116");
		driver.go(WorldbossActivity.ID);
	}

}
