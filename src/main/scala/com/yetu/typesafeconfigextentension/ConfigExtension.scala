package com.yetu.typesafeconfigextentension

import com.typesafe.config.{ConfigValueFactory, ConfigValueType, Config}
import scala.collection.JavaConversions._

trait ConfigExtension {

  import scala.language.implicitConversions

  class SubstitutedConfig(config: Config) {
    /**
     * Some config entries in reference.conf use the variable ${application.environmentUrl} that is defined in application.conf
     * As reference.conf gets parsed before application.conf we now replace the value place holder after config is parsed
     */
    def substitutePropertyValues(propertyName: String): Config = {
      val replacement = config.getString(propertyName)
      val target = s"$${$propertyName}"
      var rawConfig = config
      rawConfig.entrySet()
        .filter(_.getValue.valueType() == ConfigValueType.STRING)
        .foreach { entry => {
        val value = entry.getValue.unwrapped().asInstanceOf[String].replace(target, replacement)
        rawConfig = rawConfig.withValue(entry.getKey, ConfigValueFactory.fromAnyRef(value))
      }
      }
      rawConfig
    }

  }

  implicit def configExtension(config: Config): SubstitutedConfig = new SubstitutedConfig(config)
}