CREATE TABLE public.client_node (
	id bigserial NOT NULL,
	parent_id int8 NULL,
	"name" varchar(256) NULL,
	ip varchar(256) NULL,
	port int4 NULL,
	CONSTRAINT check_valid_port CHECK (((port >= 1) AND (port <= 65535))),
	CONSTRAINT client_node_id_not_null NOT NULL id,
	CONSTRAINT client_node_pkey PRIMARY KEY (id),
	CONSTRAINT client_node_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES public.client_node(id) ON DELETE CASCADE
);