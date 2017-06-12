package com.pchudzik.blog.exmples.randomtestdata;

import java.time.Clock;

public class TestArticleFactory {
	private static Clock fixedTime = new MutableClock();

	public static Article newArticle(Clock clock) {
		return new ArticleFactory(clock).newArticle();
	}

	public static Article newApprovedArticle() {
		return newApprovedArticle(fixedTime);
	}

	public static Article newApprovedArticle(Clock clock) {
		final Article article = newArticle(clock);
		article.sendForApproval();
		return article;
	}
}
