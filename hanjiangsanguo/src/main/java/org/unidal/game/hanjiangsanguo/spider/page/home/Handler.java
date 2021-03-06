package org.unidal.game.hanjiangsanguo.spider.page.home;

import java.io.IOException;

import javax.servlet.ServletException;

import org.unidal.game.hanjiangsanguo.AccountManager;
import org.unidal.game.hanjiangsanguo.BossManager;
import org.unidal.game.hanjiangsanguo.spider.SpiderPage;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

public class Handler implements PageHandler<Context> {
	@Inject
	private JspViewer m_jspViewer;
	
	@Inject
	private BossManager m_bossManager;
	
	@Inject
	private AccountManager m_manager;
	
	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "home")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "home")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);

		model.setAction(Action.VIEW);
		model.setPage(SpiderPage.HOME);
		
		m_manager.empty();
		m_bossManager.empty();

		if (!ctx.isProcessStopped()) {
		   m_jspViewer.view(ctx, model);
		}
	}
}
