package com.example.caref.audit;

import com.example.caref.security.util.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author TDJ
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        final String uname = SecurityUtils.getCurrentUserLogin();
        return Optional.ofNullable(uname);
    }

}
