package org.unidal.game.hanjiangsanguo.task;

import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.unidal.game.hanjiangsanguo.task.activity.TaskActivity;
import org.unidal.lookup.ContainerHolder;

public class TaskDriver extends ContainerHolder implements Initializable, LogEnabled {

	private ThreadLocal<TaskContext> m_context = new ThreadLocal<TaskContext>() {
		@Override
		protected TaskContext initialValue() {
			return lookup(TaskContext.class);
		}
	};

	private Logger m_logger;

	public TaskContext getContext() {
		return m_context.get();
	}

	public TaskContext setup(String username, String password, String server, String... params) {
		getContext().setAttribute("user", "username", username);
		getContext().setAttribute("user", "password", password);
		getContext().setAttribute("user", "server", server);

		return setupContext(params);
	}

	public void reset() {
		m_context.remove();

		m_context.set(lookup(TaskContext.class));
	}

	@Override
	public void initialize() throws InitializationException {
	}

	private TaskContext setupContext(String... params) {
		int len = params.length;

		if (len % 2 != 0) {
			throw new IllegalArgumentException("Parameters is not paired!");
		}

		for (int i = 0; i < len; i += 2) {
			String key = params[i];
			String value = params[i + 1];
			int pos = key.indexOf('/');
			String category;
			String name;

			if (pos > 0) {
				category = key.substring(0, pos);
				name = key.substring(pos + 1);
			} else {
				category = "system";
				name = key;
			}

			getContext().setAttribute(category, name, value);
		}

		return getContext();
	}

	public void go(String name, String... args){
		try {
	      TaskActivity activity = lookup(TaskActivity.class, name);
	      TaskArguments arguments = new TaskArguments(args);

	      if (!activity.execute(getContext(), arguments)) {
	      	m_logger.error("faile to do activity " + name);
	      }
      } catch (Exception e) {
	      e.printStackTrace();
      }
	}

	@Override
	public void enableLogging(Logger logger) {
		m_logger = logger;
	}

}
