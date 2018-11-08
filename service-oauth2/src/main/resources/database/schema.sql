--------------- H2 ---------------

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

drop table if exists t_user;
CREATE TABLE IF NOT EXISTS t_user
(
ID BIGINT NOT NULL,
username VARCHAR(50) NOT NULL,
password VARCHAR(70) NOT NULL,
CREATE_DATE TIMESTAMP NOT NULL,
CREATE_USER VARCHAR(20) NOT NULL
);

--drop table if exists oauth_client_token;
--drop table if exists oauth_access_token;
--drop table if exists oauth_refresh_token;
--drop table if exists oauth_code;
--drop table if exists oauth_approvals;
--drop table if exists ClientDetails;
--drop table if exists t_upload_file;
--drop table if exists t_user_account;
--drop table if exists t_user_info;
--drop table if exists t_user_permission;
--drop table if exists t_user_role;
create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
--
create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(255)
);
--
create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);
--
create table if not exists oauth_code (
  code VARCHAR(255), authentication LONGVARBINARY
);

create table if not exists oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);
--
create table if not exists ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);