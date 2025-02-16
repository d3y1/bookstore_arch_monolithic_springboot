package com.github.taosoft.bookstore.domain.auth;

/**
 * 角色常量类，目前系统中只有2种角色：用户，管理员
 **/
public interface Role {
    String USER = "ROLE_USER";
    String ADMIN = "ROLE_ADMIN";
}
