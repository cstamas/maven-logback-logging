package org.apache.maven.logging.log4j2x;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.gossip.Log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import org.eclipse.sisu.EagerSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cstamas on 09/01/16.
 */
@Named
@EagerSingleton
public class Bootstrap
{
  private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

  @Inject
  public Bootstrap() {
    try {
      LoggerContext defaultLoggerContext = new LoggerContext();
      new ContextInitializer(defaultLoggerContext).autoConfig();
      ContextSelectorStaticBinder.getSingleton().init(defaultLoggerContext, this);
      Log.configure(defaultLoggerContext);
      log.info("Logback installed");
    }
    catch (Exception e) {
      log.error("Could not install logback", e);
    }
  }
}
