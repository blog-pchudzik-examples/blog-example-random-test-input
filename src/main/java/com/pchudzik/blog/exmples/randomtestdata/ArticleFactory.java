package com.pchudzik.blog.exmples.randomtestdata;

import lombok.RequiredArgsConstructor;

import java.time.Clock;

@RequiredArgsConstructor
class ArticleFactory {
	private final Clock systemClock;

	public ArticleFactory() {
		this(Clock.systemDefaultZone());
	}

	public Article newArticle() {
		return new Article(systemClock);
	}
}
