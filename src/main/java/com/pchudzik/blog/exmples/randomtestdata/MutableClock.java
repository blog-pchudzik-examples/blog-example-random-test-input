package com.pchudzik.blog.exmples.randomtestdata;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class MutableClock extends Clock {
	private Instant currentTime;

	public MutableClock() {
		this("2017-06-10T15:00:00.00Z");
	}

	public MutableClock(String instant) {
		this.currentTime = Instant.parse(instant);
	}

	public void setCurrentTime(String instant) {
		this.currentTime = Instant.parse(instant);
	}

	@Override
	public ZoneId getZone() {
		return ZoneId.systemDefault();
	}

	@Override
	public Clock withZone(ZoneId zone) {
		return new MutableClock(currentTime.toString());
	}

	@Override
	public Instant instant() {
		return currentTime;
	}
}
