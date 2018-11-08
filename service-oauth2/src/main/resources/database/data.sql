INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('fooClientIdPassword', 'secret', 'foo,read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);


INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('sampleClientId', 'secret', 'read,write,foo,bar',
	'implicit', null, null, 36000, 36000, null, false);


INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('barClientIdPassword', 'secret', 'bar,read,write',
	'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);

--update OAUTH_CLIENT_DETAILS set client_secret = '$2a$10$SZbJZnuNXUs97Q7SZGrkQe5ckBatLQzGL5gPYfH8ME7cwbXJfoyCa';
--insert into t_user (id,username,password,create_date,create_user) values(1,'root','$2a$10$SZbJZnuNXUs97Q7SZGrkQe5ckBatLQzGL5gPYfH8ME7cwbXJfoyCa',sysdate,'管理员');