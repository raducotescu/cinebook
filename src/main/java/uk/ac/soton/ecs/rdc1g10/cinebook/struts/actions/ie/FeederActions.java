package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.ie;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.BaseAction;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeederActions extends BaseAction {

	private static final long serialVersionUID = 2464741415609680210L;
	private static final String urlFeedSource = "http://rss.imdb.com/daily/born";
	private List<SyndEntry> importedFeedEntries;
	private SyndFeed importedFeed;

	@SuppressWarnings("unchecked")
	public String importedFeed() throws IllegalArgumentException,
			FeedException, IOException {
		URL feedSource = new URL(urlFeedSource);
		URLConnection urlc = feedSource.openConnection();
		urlc.setRequestProperty("User-Agent", "Mozilla/5.0");
		SyndFeedInput input = new SyndFeedInput();
		importedFeed = input.build(new XmlReader(urlc));
		importedFeedEntries = importedFeed.getEntries();
		return SUCCESS;
	}

	public List<SyndEntry> getImportedFeedEntries() {
		return importedFeedEntries;
	}

	public SyndFeed getImportedFeed() {
		return importedFeed;
	}
}
