package com.pchudzik.blog.exmples.randomtestdata;


import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.testng.Assert.assertEquals;

@Test
public class ArticleTest {
	public void should_update_last_modification_date_when_updating_article_content() {
		//given
		final MutableClock mutableClock = new MutableClock();
		final Article article = TestArticleFactory.newArticle(mutableClock);

		final String newContent = "new content";
		mutableClock.setCurrentTime("2017-06-10T17:00:00.00Z");

		//when
		article.updateContent(newContent);

		//then
		assertEquals(article.getLastUpdateTime(), LocalDateTime.now(mutableClock));
	}

	public void should_reject_change_after_sending_for_approval() {
		//given
		final Article approvedArticle = TestArticleFactory.newApprovedArticle();

		//when
		catchException(approvedArticle).updateContent("any content");

		//then
		assertEquals(caughtException().getClass(), IllegalStateException.class);
	}
}