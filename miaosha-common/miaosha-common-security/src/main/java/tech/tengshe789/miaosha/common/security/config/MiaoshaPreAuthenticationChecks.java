package tech.tengshe789.miaosha.common.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @program: -miaosha
 * @author: tEngSHe789
 * @create: 2019-03-31 22:34
 **/
@Slf4j
public class MiaoshaPreAuthenticationChecks implements UserDetailsChecker {
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Override
	public void check(UserDetails user) {
		if (!user.isAccountNonLocked()) {
			log.debug("User account is locked");

			throw new LockedException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.locked",
				"User account is locked"));
		}

		if (!user.isEnabled()) {
			log.debug("User account is disabled");

			throw new DisabledException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.disabled",
				"User is disabled"));
		}

		if (!user.isAccountNonExpired()) {
			log.debug("User account is expired");

			throw new AccountExpiredException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.expired",
				"User account has expired"));
		}
	}
}
