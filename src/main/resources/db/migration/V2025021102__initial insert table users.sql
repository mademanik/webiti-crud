CREATE TABLE public.users
(
    id         int4         NOT NULL,
    created_at timestamp(6) NULL,
    email      varchar(100) NOT NULL,
    full_name  varchar(255) NOT NULL,
    "password" varchar(255) NOT NULL,
    updated_at timestamp(6) NULL,
    role_id    int4         NOT NULL,
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

ALTER TABLE public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles (id);

INSERT INTO public.users
    (id, created_at, email, full_name, "password", updated_at, role_id)
VALUES (1, '2025-02-11 19:14:09.986', 'super.admin@email.com', 'Super Admin',
        '$2a$10$jzyYs2n8KXN9oNnCWxMqZ.pKZ/uCFdYT4wpBJjuKVNZAL3lKmS.tu', '2025-02-11 19:14:09.986', 3);