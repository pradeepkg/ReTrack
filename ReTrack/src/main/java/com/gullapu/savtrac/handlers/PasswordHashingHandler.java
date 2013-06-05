package com.gullapu.savtrac.handlers;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.gullapu.savtrac.pojo.User;

public class PasswordHashingHandler extends AbstractHandler {

	private PasswordEncoder encoder;

	/**
	 * @return the encoder
	 */
	public PasswordEncoder getEncoder() {
		return encoder;
	}

	/**
	 * @param encoder the encoder to set
	 */
	public void setEncoder(PasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	public void handle(Object... params) throws HandlerException {
		if (params == null | params.length < 1 || params[0] == null || !(params[0] instanceof User)) {
			throw new HandlerException("The handler requires a valid User input.");
		}

		final User user = (User) params[0];
		if (null != user.getPassword()) {
			user.setPassword(encoder.encode(user.getPassword()));
		}
	}

}
