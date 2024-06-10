/**
 * Module definition for the FillTheFormAPI application.
 */

module FillTheFormAPI.main {
    requires com.google.api.client;
    requires com.google.api.client.auth;
    requires com.google.api.client.json.gson;
    requires com.google.api.services.script;
    requires google.api.client;
    requires io.swagger.v3.oas.models;
    requires org.apache.tomcat.embed.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires spring.webmvc;
    requires spring.beans;
    exports pl.edu.pwr.pkuchnowski.filltheformapi;
    opens pl.edu.pwr.pkuchnowski.filltheformapi to spring.core;
    opens pl.edu.pwr.pkuchnowski.filltheformapi.configuration to spring.core, spring.beans, spring.context;
    opens pl.edu.pwr.pkuchnowski.filltheformapi.controller to spring.core, spring.beans, spring.context;
    opens pl.edu.pwr.pkuchnowski.filltheformapi.service to spring.core, spring.beans, spring.context;
    exports pl.edu.pwr.pkuchnowski.filltheformapi.controller to spring.web;
    exports pl.edu.pwr.pkuchnowski.filltheformapi.response;
    exports pl.edu.pwr.pkuchnowski.filltheformapi.service;
    exports pl.edu.pwr.pkuchnowski.filltheformapi.authorization;
}