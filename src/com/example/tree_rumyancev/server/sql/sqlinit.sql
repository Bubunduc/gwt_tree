CREATE TABLE client_node (
	id bigserial NOT NULL,
	parent_id int8 NOT NULL,
	"name" varchar(256) NOT NULL,
	ip varchar(256) NOT NULL,
	port int4 NOT NULL,
	CONSTRAINT check_valid_port CHECK (((port >= 0) AND (port <= 65535))),
	CONSTRAINT client_node_id_not_null NOT NULL id,
	CONSTRAINT client_node_pkey PRIMARY KEY (id),
	CONSTRAINT client_node_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES public.client_node(id) ON DELETE CASCADE
);