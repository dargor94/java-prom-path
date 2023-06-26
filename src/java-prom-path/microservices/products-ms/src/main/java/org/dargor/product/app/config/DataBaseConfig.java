package org.dargor.product.app.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

public class DataBaseConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server1() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9011");
    }

}
