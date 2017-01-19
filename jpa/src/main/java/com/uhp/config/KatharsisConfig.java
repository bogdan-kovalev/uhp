package com.uhp.config;

import io.katharsis.spring.boot.v3.KatharsisConfigV3;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Bogdan Kovalev.
 */
@Configuration
@Import(KatharsisConfigV3.class)
public class KatharsisConfig {
}
