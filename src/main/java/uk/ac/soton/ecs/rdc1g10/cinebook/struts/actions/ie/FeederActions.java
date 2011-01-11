package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.ie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.Movie;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.services.ScheduleEntries;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.BaseAction;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

public class FeederActions extends BaseAction implements ServletRequestAware, ServletContextAware {

	private static final long serialVersionUID = 2464741415609680210L;
	private static final String urlFeedSource = "http://rss.imdb.com/daily/born";
	private List<SyndEntry> importedFeedEntries;
	private HttpServletRequest request;
	private ServletContext context;

	@SuppressWarnings("unchecked")
	public String importedFeed() throws IllegalArgumentException,
			FeedException, IOException {
		URL feedSource = new URL(urlFeedSource);
		URLConnection urlc = feedSource.openConnection();
		urlc.setRequestProperty("User-Agent", "Mozilla/5.0");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed importedFeed = input.build(new XmlReader(urlc));
		importedFeedEntries = importedFeed.getEntries();
		return SUCCESS;
	}
	
	public String producedFeed() throws Exception {
		String feedFile = context.getRealPath("pages" + File.separator + "schedule.xml");
		File f = new File(feedFile);
		Double differenceInHours = null;
		if(f.exists()) {
			Long modified = f.lastModified();
			Long now = new Date().getTime();
			differenceInHours =  ((double)(now - modified) / (60 * 60 * 1000));
		}
		if(differenceInHours == null || differenceInHours >= 1) {
			SyndFeed feed = new SyndFeedImpl();
			feed.setFeedType("rss_2.0");
			feed.setTitle("CineBook's current schedule");
			feed.setLink("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + request.getServletPath());
			feed.setDescription("Find out CineBook's current schedule from your favourite RSS reader");
			feed.setPublishedDate(new Date());
			List<Movie> movies = (List<Movie>) ScheduleEntries.getCurrentSchedule();
			List<SyndEntry> exportedFeedEntries = new ArrayList<SyndEntry>();
			for(Movie m : movies) {
				SyndEntry entry = new SyndEntryImpl();
				SyndContent description = new SyndContentImpl();
				entry.setTitle(m.getTitle());
				entry.setLink("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/movies/movieDetails?movieID=" + m.getId());
				entry.setPublishedDate(new Date());
				description.setType("text/html");
				description.setValue(m.getDescription());
				entry.setDescription(description);
				exportedFeedEntries.add(entry);
			}
			feed.setEntries(exportedFeedEntries);
			Writer writer = new FileWriter(feedFile);
			SyndFeedOutput output = new SyndFeedOutput();
			output.output(feed, writer);
			writer.close();
		}
		return SUCCESS;
	}

	public List<SyndEntry> getImportedFeedEntries() {
		return importedFeedEntries;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
}
