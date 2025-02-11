CREATE TABLE public.roles
(
    id          int4         NOT NULL,
    created_at  timestamp(6) NULL,
    description varchar(255) NOT NULL,
    "name"      varchar(255) NOT NULL,
    updated_at  timestamp(6) NULL,
    CONSTRAINT roles_name_check CHECK (((name)::text = ANY ((ARRAY['USER':: character varying, 'ADMIN':: character varying, 'SUPER_ADMIN':: character varying])::text[])
) ),
	CONSTRAINT roles_pkey PRIMARY KEY (id),
	CONSTRAINT uk_ofx66keruapi6vyqpv6f2or37 UNIQUE (name)
);

INSERT INTO public.roles
    (id, created_at, description, "name", updated_at)
VALUES (1, '2025-02-11 19:12:23.849', 'Default user role', 'USER', '2025-02-11 19:12:23.849');
INSERT INTO public.roles
    (id, created_at, description, "name", updated_at)
VALUES (2, '2025-02-11 19:12:23.899', 'Administrator role', 'ADMIN', '2025-02-11 19:12:23.899');
INSERT INTO public.roles
    (id, created_at, description, "name", updated_at)
VALUES (3, '2025-02-11 19:12:23.912', 'Super Administrator role', 'SUPER_ADMIN', '2025-02-11 19:12:23.912');