package de.mannodermaus.example.common.test.parallel

import org.junit.platform.engine.ConfigurationParameters
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy
import java.lang.IllegalArgumentException

// Used as Configuration Parameter for parallel execution.
@Suppress("unused")
class CustomParallelExecutionStrategy : ParallelExecutionConfigurationStrategy {
    override fun createConfiguration(configurationParameters: ConfigurationParameters): ParallelExecutionConfiguration {
        val count = configurationParameters.get("custom.love")
                .orElseThrow { IllegalArgumentException() }
                .count { it == '❤' }

        return object : ParallelExecutionConfiguration {
            override fun getParallelism(): Int {
                return count
            }

            override fun getMinimumRunnable(): Int {
                return count
            }

            override fun getMaxPoolSize(): Int {
                return count
            }

            override fun getCorePoolSize(): Int {
                return count
            }

            override fun getKeepAliveSeconds(): Int {
                return 10
            }
        }
    }
}
