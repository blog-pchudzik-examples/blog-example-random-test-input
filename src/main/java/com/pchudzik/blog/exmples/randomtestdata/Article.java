package com.pchudzik.blog.exmples.randomtestdata;

import com.google.common.base.Preconditions;
import lombok.Getter;

import java.time.Clock;
import java.time.LocalDateTime;

class Article {
	private final Clock clock;

	@Getter
	private LocalDateTime lastUpdateTime;
	private State articleState;

	private String content;

	public Article(Clock clock) {
		this.clock = clock;

		this.articleState = State.IN_PROGRESS;
		this.lastUpdateTime = getCurrentDateTime();
	}

	public void updateContent(String newContent) {
		Preconditions.checkState(articleState.canSave());
		this.content = newContent;
		this.lastUpdateTime = getCurrentDateTime();
	}

	public void sendForApproval() {
		Preconditions.checkState(articleState.canSendForApproval());
		this.lastUpdateTime = getCurrentDateTime();
		this.articleState = State.WAITING_FOR_APPROVAL;
	}

	private LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now(clock);
	}

	private enum State {
		APPROVED, REJECTED, IN_PROGRESS, WAITING_FOR_APPROVAL;

		boolean canSave() {
			return this == IN_PROGRESS || this == REJECTED;
		}

		boolean canSendForApproval() {
			return this == State.IN_PROGRESS;
		}
	}
}
